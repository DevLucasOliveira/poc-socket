import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) {
        ServerSocket socketServer = null;
        Socket socketClient = null;
        Scanner entrada = null;
        PrintStream saida = null;
        String msg = null;

        final int PORTA = 9876;

        // Bind - solicitar uma porta ao SO

        try {
            socketServer = new ServerSocket(PORTA);
        } catch (Exception e) {
            System.out.println("Porta " + PORTA + " em uso");
            return;
        }

        // Aguardar um pedido de conexão -listen/accept
        do {

            try {
                System.out.println("Aguardando uma conexão . . .");
                socketClient = socketServer.accept();
                System.out.println("Conectado com " + socketClient.getInetAddress().getHostAddress());

                entrada = new Scanner(socketClient.getInputStream());
            } catch (Exception e) {
                System.out.println("Erro no processo de conexão");
                return;
            }

            // Comunicação
            try {
                do {
                    msg = entrada.nextLine();
                    System.out.println("Usuário: " + msg);
                } while (!msg.equals("exit") && !msg.equals("fechar"));
            } catch (Exception e) {
                System.out.println("Erro durante a comunicação com o cliente.");
            }

        } while (!msg.equals("fechar"));

        // Encerrar a coneão
        try {
            socketClient.close();
            socketServer.close();
            System.out.println("Conexao encerrada");
        } catch (Exception e) {
            System.out.println("Erro ao encerrar as conexões");
        }

    }

}