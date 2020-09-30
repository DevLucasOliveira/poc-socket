import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        Socket socketClient = null;
        Scanner teclado = null;
        Scanner entrada = null;
        PrintStream saida = null;
        String msg = null;

        final String IPADDRES = "127.0.0.1";
        final int PORTA = 9876;


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
            do {
                System.out.print("Digite uma mensagem: ");
                msg = teclado.nextLine();
                saida.println(msg);
            } while(!msg.equals("exit") && !msg.equals("fechar"));
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