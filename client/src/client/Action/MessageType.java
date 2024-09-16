package client.Action;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author hungkiller
 */
public enum MessageType {
    @SerializedName("CHAT")
    CHAT,
    @SerializedName("JOIN")
    JOIN,
    @SerializedName("LEAVE")
    LEAVE,
    @SerializedName("LOGIN")
    LOGIN,
    
    @SerializedName("EXIT")
    EXIT
}
