package utils.request;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 06/10/14.
 */
public class ExtractorRequestHelper {

    public static LinkedHashMap standardInput(JsonNode data) {
        LinkedHashMap res = new LinkedHashMap();

        JsonNode tokenNode = data.findValue("token");
        String token = (tokenNode != null) ? tokenNode.asText() : "";
        res.put("token", token);

        JsonNode entityNode = data.findValue("entity");
        String entity = (entityNode != null) ? entityNode.asText() : "";
        res.put("entity", entity);

        return res;
    }
}
