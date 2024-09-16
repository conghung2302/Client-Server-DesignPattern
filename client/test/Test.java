
import com.google.gson.JsonObject;


/**
 *
 * @author hungkiller
 */
public class Test {
    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "John Doe");
        jsonObject.addProperty("age", 30);
        jsonObject.addProperty("city", "New York");
        
        JsonObject obj2 = new JsonObject();
        obj2.add("user", jsonObject);
        
        System.out.println(obj2.entrySet());
    }
}
