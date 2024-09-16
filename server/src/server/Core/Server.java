package server.Core;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.util.*;
import server.Model.Message;
import server.Model.MessageType;
import server.Model.Status;

public class Server {

    private static Set<ClientHandler> clientHandlers = new HashSet<>();
    public static Gson gson = new Gson();  // Gson instance

    public Server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(2302);
        System.out.println("Chat server started...");

        while (true) {
            Socket socket = serverSocket.accept(); // Accept client connections
            ClientHandler clientHandler = new ClientHandler(socket);
            clientHandlers.add(clientHandler);
            new Thread(clientHandler).start(); // Start a new thread for each client
        }
    }



    // Broadcast the message to all clients
    public static void broadcastMessage(Message message, ClientHandler sender) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler != sender) {
                clientHandler.sendMessage(gson.toJson(message));
            }
        }
    }

    public static void processMessage(Message message, ClientHandler sender) {
        System.out.println("server " + message);
        switch (message.type) {
            case LOGIN:
                String username = (String) message.content;
                if (username.equals("hung")) {
                    sender.clientName = username;
                    sender.sendMessage(gson.toJson(new Message("Login succesful", MessageType.LOGIN, Status.OK)));
                } else {
                    sender.sendMessage(gson.toJson(new Message("Sai username", MessageType.LOGIN, Status.ERROR)));
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    // Remove a client when they disconnect
    public static void removeClient(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
    }
}
