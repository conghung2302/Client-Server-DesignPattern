package client.Core;

import client.Core.Client;
import client.Interface.Login;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ClientManager {
    public static Client client;
    public static Gson gson = new Gson();
    public String ip = "localhost";
    public int port = 2302;
    public ClientManager() {
        
        
        client = new Client();
        if (client.StartConnect("localhost", port)) {
            client.StartThreadHandle();
            new Login().setVisible(true);
        }
    }
    
    public void LeaveLogin() {
        client.SendMess(ip);
    }
    
}
