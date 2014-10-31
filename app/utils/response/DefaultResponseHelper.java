package utils.response;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.LinkedHashMap;
import play.libs.Json;

/**
 * Created by joaochencci on 30/07/14.
 */
public class DefaultResponseHelper {

	/**
	 * Response for /user/logout
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode defaultSuccess() {
		LinkedHashMap resJson = new LinkedHashMap();

		resJson.put("result", "success");
		resJson.put("message", "OK");

		return Json.toJson(resJson);
	}
}
