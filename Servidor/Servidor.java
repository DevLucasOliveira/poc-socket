import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) {
        ServerSocket socketServer = null;
        final int PORTA = 9876;
        Socket socketClient = null;
        Scanner entrada;

        // Bind - solicitar uma porta ao SO
        try {
            socketServer = new ServerSocket(PORTA);
        } catch (Exception e) {
            System.out.println("Porta " + PORTA + " em uso");
            return;
        }

        // Aguardar um pedido de conexão -listen/accept
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
            String msg = entrada.nextLine();
            System.out.println("Recebido: "+msg);            
        } catch (Exception e) {
            System.out.println("Erro durante a comunicação com o cliente.");
        }

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