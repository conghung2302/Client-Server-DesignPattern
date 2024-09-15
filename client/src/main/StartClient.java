package main;

import client.Core.Client;
import client.Interface.Login;

public class StartClient {
    public static Client client;
    public String ip = "localhost";
    public int port = 2302;
    public StartClient() {
        
        client = new Client();
        if (client.StartConnect("localhost", port)) {
            client.StartThreadHandle();
            new Login().setVisible(true);
        }
        
    }
    
    public static void main(String[] args) {
        new StartClient();
    }
    
}
