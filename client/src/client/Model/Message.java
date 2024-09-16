package client.Model;

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
    
}
