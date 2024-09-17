package server.Core;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.*;
import java.util.*;
import server.Action.MessageType;
import server.Action.Status;
import server.Model.ChatMessage;

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
    public static void broadcastMessage(String message) {
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.sendMessage(message);
        }
    }

    public static void processMessage(String request, ClientHandler sender) {
        
        JsonObject obj = JsonParser.parseString(request).getAsJsonObject();
        MessageType action = MessageType.valueOf(obj.get("type").getAsString());
        
        JsonObject content = obj.get("content").getAsJsonObject();
        
       
        System.out.println("server " + obj);
        switch (action) {
            case LOGIN:
                
                sender.clientName = content.get("username").getAsString();
                sender.sendMessage(obj.toString());

//                if (username.equals("hung")) {
//                    sender.clientName = username;
//                    sender.sendMessage(gson.toJson(new Message("Login succesful", MessageType.LOGIN, Status.OK)));
//                } else {
//                    sender.sendMessage(gson.toJson(new Message("Sai username", MessageType.LOGIN, Status.ERROR)));
//                }
                break;

            case CHAT:
                content.addProperty("from", sender.clientName);
                obj.add("content", content);
                broadcastMessage(obj.toString());
                break;
                
            
            default:
                throw new AssertionError();
        }
    }

    // Remove a client when they disconnect
    public static void removeClient(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
        System.out.println("Number: " + clientHandlers.size());
    }
}


