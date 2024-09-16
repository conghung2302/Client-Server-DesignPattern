package client.Core;

import client.Model.Message;
import java.io.*;
import java.net.*;
import java.util.Observable;
import java.util.Observer;
import com.google.gson.Gson;

public class Client extends Observable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader consoleInput;

    public Client(Observer obs) {
        this.addObserver(obs);
    }

    public Client() {
    }

    public void SendMess(String mess) {
        out.println(mess);
        out.flush();
    }

    @Override
    public void notifyObservers(Object org) {
        setChanged();
        super.notifyObservers(org);
    }

    public boolean StartConnect(String serverAddress, int port) {
        try {
            socket = new Socket(serverAddress, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void StartThreadHandle() {
        new Thread(new IncomingMessagesHandler()).start();
    }

    // Thread to handle incoming messages from the server
    private class IncomingMessagesHandler implements Runnable {

        @Override
        public void run() {
            try {
                String receivedMessage;
                while ((receivedMessage = in.readLine()) != null) {
                    Message message = ClientManager.gson.fromJson(receivedMessage, Message.class);
                    System.out.println("Received: " + message.content);
                    notifyObservers(receivedMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
