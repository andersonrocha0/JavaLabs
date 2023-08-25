import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class MyServerRunnable implements Runnable {

    Map<String, MyRunnable> runnableMap;

    MyServerRunnable(int port, Map<String, MyRunnable> threadsToTurnOff) {
        this.port = port;
        this.runnableMap = threadsToTurnOff;
    }

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private final int port;

    @Override
    public void run() {
        try {
            System.out.println("Starting server");
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (".".equals(inputLine)) {
                    out.println("good bye");
                    this.stop();
                    break;
                }
                if (runnableMap.containsKey(inputLine)) {
                    runnableMap.get(inputLine).setRunning(false);
                }
                out.println(inputLine);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
