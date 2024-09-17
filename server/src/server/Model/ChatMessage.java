package server.Model;

/**
 *
 * @author hungkiller
 */
public class ChatMessage {
    public String from;
    public String to;
    public String mess;

    public ChatMessage(String from, String to, String mess) {
        this.from = from;
        this.to = to;
        this.mess = mess;
    }

    @Override
    public String toString() {
        return "ChatMessage{" + "from=" + from + ", to=" + to + ", content=" + mess + '}';
    }
}
