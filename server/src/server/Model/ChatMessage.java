package server.Model;

/**
 *
 * @author hungkiller
 */
public class ChatMessage {
    public String from;
    public String to;
    public String content;

    public ChatMessage(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ChatMessage{" + "from=" + from + ", to=" + to + ", content=" + content + '}';
    }
}
