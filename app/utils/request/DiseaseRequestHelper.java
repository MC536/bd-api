package utils.request;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 06/10/14.
 */
public class DiseaseRequestHelper {

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

        JsonNode popularNameNode = data.findValue("popularName");
        String popularName = (popularNameNode != null) ? popularNameNode.asText() : "";
        res.put("popularName", popularName);

        JsonNode scientificNameNode = data.findValue("scientificName");
        String scientificName = (scientificNameNode != null) ? scientificNameNode.asText() : "";
        res.put("scientificName", scientificName);

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
