import java.io.*;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    private static final Pattern ADDITION_PATTERN = Pattern.compile("(\\d+)\\s*[+]\\s*(\\d+)");

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("server", 8888);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            int counter = 0;
            while (counter < 10) {
                writer.write("ping" + "\n");
                writer.flush();
                String input = reader.readLine();
                Matcher matcher = ADDITION_PATTERN.matcher(input);
                if (matcher.find()) {
                    int result = Integer.valueOf(matcher.group(1)) + Integer.valueOf(matcher.group(2));
                    System.out.println("Client: " + result);
                    counter++;
                }
            }
        }
        System.out.println("Client: STOP");
    }
}
