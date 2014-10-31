package utils.request;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 06/10/14.
 */
public class VectorRequestHelper {

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

        JsonNode familyNode = data.findValue("family");
        String family = (familyNode != null) ? familyNode.asText() : "";
        res.put("family", family);

        JsonNode popularNameNode = data.findValue("popularName");
        String popularName = (popularNameNode != null) ? popularNameNode.asText() : "";
        res.put("popularName", popularName);

        JsonNode scientificNameNode = data.findValue("scientificName");
        String scientificName = (scientificNameNode != null) ? scientificNameNode.asText() : "";
        res.put("scientificName", scientificName);

        JsonNode bestTemperatureNode = data.findValue("bestTemperature");
        String bestTemperature = (bestTemperatureNode != null) ? bestTemperatureNode.asText() : "";
        res.put("bestTemperature", bestTemperature);

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

    public static LinkedHashMap logout(JsonNode data) {
        LinkedHashMap res = new LinkedHashMap();

        JsonNode tokenNode = data.findValue("token");
        String token = (tokenNode != null) ? tokenNode.asText() : "";
        res.put("token", token);

        return res;
    }

    public static LinkedHashMap changePassword(JsonNode data) {
        LinkedHashMap res = new LinkedHashMap();

        JsonNode tokenNode = data.findValue("token");
        String token = (tokenNode != null) ? tokenNode.asText() : "";
        res.put("token", token);

        JsonNode passwordNode = data.findValue("password");
        String password = (passwordNode != null) ? passwordNode.asText() : "";
        res.put("password", password);

        JsonNode newPasswordNode = data.findValue("newPassword");
        String newPassword = (newPasswordNode != null) ? newPasswordNode.asText() : "";
        res.put("newPassword", newPassword);

        return res;
    }

    public static LinkedHashMap recoverPassword(JsonNode data) {
        LinkedHashMap res = new LinkedHashMap();

        JsonNode emailNode = data.findValue("email");
        String email = (emailNode != null) ? emailNode.asText() : "";
        res.put("email", email);

        return res;
    }

    public static LinkedHashMap completeRecover(JsonNode data) {
        LinkedHashMap res = new LinkedHashMap();

        JsonNode secretNode = data.findValue("secret");
        String secret = (secretNode != null) ? secretNode.asText() : "";
        res.put("secret", secret);

        JsonNode passwordNode = data.findValue("password");
        String password = (passwordNode != null) ? passwordNode.asText() : "";
        res.put("password", password);

        return res;
    }

    public static LinkedHashMap changeRole(JsonNode data) {
        LinkedHashMap res = new LinkedHashMap();

        JsonNode tokenNode = data.findValue("token");
        String token = (tokenNode != null) ? tokenNode.asText() : "";
        res.put("token", token);

        JsonNode userData = data.get("user");
        JsonNode userIdNode = userData.findValue("id");
        String userId = (userIdNode != null) ? userIdNode.asText() : "";
        res.put("userId", userId);

        JsonNode roleData = data.get("role");
        JsonNode roleIdNode = roleData.findValue("id");
        String roleId = (roleIdNode != null) ? roleIdNode.asText() : "";
        res.put("roleId", roleId);

        return res;
    }
}
