package utils.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 06/10/14.
 */
public class UserRequestHelper {

    public static LinkedHashMap me(JsonNode data) {
        LinkedHashMap res = new LinkedHashMap();

        JsonNode tokenNode = data.findValue("token");
        String token = (tokenNode != null) ? tokenNode.asText() : "";
        res.put("token", token);

        return res;
    }

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

        JsonNode nameNode = data.findValue("name");
        String name = (nameNode != null) ? nameNode.asText() : "";
        res.put("name", name);

        JsonNode emailNode = data.findValue("email");
        String email = (emailNode != null) ? emailNode.asText() : "";
        res.put("email", email);

        JsonNode passwordNode = data.findValue("password");
        String password = (passwordNode != null) ? passwordNode.asText() : "";
        res.put("password", password);

        return res;
    }

    public static LinkedHashMap login(JsonNode data) {
        LinkedHashMap res = new LinkedHashMap();

        JsonNode emailNode = data.findValue("email");
        String email = (emailNode != null) ? emailNode.asText() : "";
        res.put("email", email);

        JsonNode passwordNode = data.findValue("password");
        String password = (passwordNode != null) ? passwordNode.asText() : "";
        res.put("password", password);

        return res;
    }

    public static LinkedHashMap token(JsonNode data) {
        LinkedHashMap res = new LinkedHashMap();

        JsonNode codeNode = data.findValue("code");
        String code = (codeNode != null) ? codeNode.asText() : "";
        res.put("code", code);

        JsonNode keyNode = data.findValue("key");
        String key = (keyNode != null) ? keyNode.asText() : "";
        res.put("key", key);

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
}
