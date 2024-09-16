package client.Model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author hungkiller
 */
public enum Status {
    @SerializedName("OK")
    OK, 
    @SerializedName("ERROR")
    ERROR
}
