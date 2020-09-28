import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        Socket socketClient = null;
        final int PORTA = 9876;
        final String IPADDRES = "127.0.0.1";
        PrintStream saida = null;
        Scanner teclado = null;

        // Solicitar uma conexão com o servidor
        try {
            socketClient = new Socket(IPADDRES, PORTA);
            System.out.println("Conectado com o servidor");

            saida = new PrintStream(socketClient.getOutputStream());
            teclado = new Scanner(System.in);
        } catch (Exception e) {
            System.out.println("Erro ao conectar com o servidor");
        }

        // Comunicação
        try {
            String msg = teclado.nextLine();
            saida.println(msg);
        } catch (Exception e) {
            System.out.println("Erro na etapa de comunicação");
        }

        // Encerra a conexão
        try {
            socketClient.close();
            System.out.println("Conexao encerrada");
        } catch (Exception e) {
            System.out.println("Erro ao encerrar a conexão");
        }

    }
}