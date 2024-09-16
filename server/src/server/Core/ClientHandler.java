package server.Core;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import static server.Core.Server.gson;
import server.Action.Message;
import server.Action.MessageType;
import server.Action.Status;

class ClientHandler implements Runnable {

    public Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public String clientName;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("A client Connected");
//            out.println("Enter your name:");
//            clientName = in.readLine();
//            System.out.println(clientName + " has joined the chat.");
//            Server.broadcastMessage(gson.toJson(jsonElement), this);

            String receivedMessage;
            while ((receivedMessage = in.readLine()) != null) {
                Message message = gson.fromJson(receivedMessage, Message.class);
                System.out.println(message.content);
                Server.processMessage(message, this);
            }

        } catch (IOException e) {
//            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Server.broadcastMessage(new Message(clientName, MessageType.EXIT, Status.OK), this);
            Server.removeClient(this);
        }
    }

    // Send message to this client
    public void sendMessage(String message) {
        out.println(message);
        out.flush();
    }
}
