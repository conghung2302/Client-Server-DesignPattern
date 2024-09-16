package server.Model;

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
    LOGIN
}
