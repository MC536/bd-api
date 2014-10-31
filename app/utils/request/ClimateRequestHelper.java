package utils.request;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 06/10/14.
 */
public class ClimateRequestHelper {

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

        JsonNode nameNode = data.findValue("name");
        String name = (nameNode != null) ? nameNode.asText() : "";
        res.put("name", name);

        JsonNode minTemperatureNode = data.findValue("minTemperature");
        String minTemperature = (minTemperatureNode != null) ? minTemperatureNode.asText() : "";
        res.put("minTemperature", minTemperature);

        JsonNode maxTemperatureNode = data.findValue("maxTemperature");
        String maxTemperature = (maxTemperatureNode != null) ? maxTemperatureNode.asText() : "";
        res.put("maxTemperature", maxTemperature);

        JsonNode rangeTemperatureNode = data.findValue("rangeTemperature");
        String rangeTemperature = (rangeTemperatureNode != null) ? rangeTemperatureNode.asText() : "";
        res.put("rangeTemperature", rangeTemperature);

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
