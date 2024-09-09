import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class Server {
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8888);
        try (Socket socket = serverSocket.accept();
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            while (true) {
                if (reader.readLine() == null)
                    break;
                String simpleMath = RANDOM.nextInt(0, 10) + " + " + RANDOM.nextInt(0, 10);
                System.out.println("Server: " + simpleMath);
                writer.write(simpleMath + "\n");
                writer.flush();
                Thread.sleep(1000);
            }
        }
        // connection closed
        System.out.println("Server: STOP");
    }
}
