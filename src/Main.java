import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    ArrayList<Client> users = new ArrayList<>();
    ServerSocket server;
    Socket socket;
    Main() throws IOException {
        server = new ServerSocket(1234);
    }
    public static void main(String[] args) throws IOException {
        System.out.println("Waiting...");
        new Main().run();
    }
    public void run() throws IOException {
        while (true){
            Socket socket = server.accept();
            System.out.println("New client connected!");
            users.add(new Client(socket, this));
        }
    }
    void sndmessage(String nick, String msg){
        for (Client client : users) {
            client.getmsg(nick, msg);
        }
    }
}
