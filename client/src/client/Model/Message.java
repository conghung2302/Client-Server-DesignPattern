package client.Model;

/**
 *
 * @author hungkiller
 */
public class Message {

    private String message;
    private MessageType type;

    public Message(String message, MessageType type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getType() {
        return type;
    }
}
