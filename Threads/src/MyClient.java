import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        return in.readLine();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        var client = new MyClient();
        client.startConnection("127.0.0.1", 5555);
        String response = client.sendMessage("PaTaPaRa");
        Thread.sleep(10000);
        String response2 = client.sendMessage("JuBaLuBa");
        String response3 = client.sendMessage(".");
        System.out.println(response);
        System.out.println(response2);
        System.out.println(response3);
        client.stopConnection();
    }

}
