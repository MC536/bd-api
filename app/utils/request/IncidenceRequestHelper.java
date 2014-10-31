package utils.request;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 06/10/14.
 */
public class IncidenceRequestHelper {

    public static LinkedHashMap get(JsonNode data) {
        LinkedHashMap res = new LinkedHashMap();

        JsonNode tokenNode = data.findValue("token");
        String token = (tokenNode != null) ? tokenNode.asText() : "";
        res.put("token", token);

        JsonNode idNode = data.findValue("id");
        String id = (idNode != null) ? idNode.asText() : "";
        res.put("id", id);

        return res;
    }

    public static LinkedHashMap create(JsonNode data) {
        LinkedHashMap res = new LinkedHashMap();

        JsonNode tokenNode = data.findValue("token");
        String token = (tokenNode != null) ? tokenNode.asText() : "";
        res.put("token", token);

        JsonNode locationIdNode = data.findValue("locationId");
        String locationId = (locationIdNode != null) ? locationIdNode.asText() : "";
        res.put("locationId", locationId);

        JsonNode diseaseIdNode = data.findValue("diseaseId");
        String diseaseId = (diseaseIdNode != null) ? diseaseIdNode.asText() : "";
        res.put("diseaseId", diseaseId);

        JsonNode valueNode = data.findValue("value");
        String value = (valueNode != null) ? valueNode.asText() : "";
        res.put("value", value);

        return res;
    }

    public static LinkedHashMap disableOrRemove(JsonNode data) {
        LinkedHashMap res = new LinkedHashMap();

        JsonNode tokenNode = data.findValue("token");
        String token = (tokenNode != null) ? tokenNode.asText() : "";
        res.put("token", token);

        JsonNode idNode = data.findValue("id");
        String id = (idNode != null) ? idNode.asText() : "";
        res.put("id", id);

        return res;
    }
}
