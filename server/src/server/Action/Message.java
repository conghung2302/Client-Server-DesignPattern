package server.Action;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author hungkiller
 */
public class Message<T>{



    public T content;
    public MessageType type;
    public Status status;

    public Message(T content, MessageType type, Status status) {
        this.content = content;
        this.type = type;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" + "content=" + content + ", type=" + type + ", status=" + status + '}';
    }
    
    
    
    public static void main(String[] args) {
        
        Gson gson = new Gson();
        Message<ArrayList<Integer>> mess = new Message<>( new ArrayList<>(Arrays.asList(1, 2, 3, 4)), MessageType.CHAT, Status.OK);
        
        System.out.println(gson.toJson(mess));
    }
}
