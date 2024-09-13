package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String clientName;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Ask the client for their name
            out.println("Enter your name:");
            clientName = in.readLine();
            System.out.println(clientName + " has joined the chat.");
            Server.broadcastMessage(clientName + " has joined the chat.", this);

            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println(clientName + ": " + message);
                Server.broadcastMessage(clientName + ": " + message, this);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(clientName + " has left the chat.");
            Server.broadcastMessage(clientName + " has left the chat.", this);
            Server.removeClient(this);
        }
    }

    // Send message to this client
    public void sendMessage(String message) {
        out.println(message);
    }
}
