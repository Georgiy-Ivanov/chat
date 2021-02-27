import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class Client implements Runnable {
    String nick;
    Main server;
    Socket socket;
    Scanner in;
    PrintStream out;

    public Client(Socket socket, Main server){
        this.socket = socket;
        this.server = server;
        new Thread(this).start();
    }
    void getmsg(String nick, String msg){
        out.println(nick + ": " + msg);
    }

    public void run() {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            in = new Scanner(is);
            out = new PrintStream(os);
            out.println("Enter your nickname:");
            this.nick = in.nextLine();
            String input = in.nextLine();
            while (!input.equals("bye")) {
                server.sndmessage(nick, input);
                //out.println(input + "-" + input + "-" + input.substring(input.length() / 2) + "...");
                input = in.nextLine();
            }
            server.sndmessage(nick, "Disconnected!");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}