package utils.response;

import com.fasterxml.jackson.databind.JsonNode;
import models.user.UserTO;
import models.vector.VectorTO;
import play.libs.Json;

import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 29/07/14.
 */
public class VectorResponseHelper {

	/**
	 * Response for /vector/get
	 *
	 * @param vector
	 * 		Get vector
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode getSuccess(VectorTO vector) {
		LinkedHashMap resJson = new LinkedHashMap();
		LinkedHashMap vectorJson = new LinkedHashMap();
        LinkedHashMap diseaseJson = new LinkedHashMap();

        diseaseJson.put("name", vector.getDisease().getPopularName());

        vectorJson.put("id", vector.getId());
        vectorJson.put("family", vector.getFamily());
        vectorJson.put("popuparName", vector.getPopularName());
        vectorJson.put("scientificName", vector.getScientificName());
        vectorJson.put("bestTemperature", vector.getBestTemperature());
        vectorJson.put("disease", diseaseJson);

		resJson.put("result", "success");
		resJson.put("vector", vectorJson);

		return Json.toJson(resJson);
	}

	/**
	 * Response for /vector/create
	 *
	 * @param vector
	 * 		Created vector
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode createSuccess(VectorTO vector) {
		LinkedHashMap resJson = new LinkedHashMap();
		LinkedHashMap vectorJson = new LinkedHashMap();

        vectorJson.put("id", vector.getId());
        vectorJson.put("family", vector.getFamily());
        vectorJson.put("popuparName", vector.getPopularName());
        vectorJson.put("scientificName", vector.getScientificName());
        vectorJson.put("bestTemperature", vector.getBestTemperature());

		resJson.put("result", "success");
		resJson.put("vector", vectorJson);

		return Json.toJson(resJson);
	}

	/**
	 * Response for /user/disable
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode disableSuccess() {
		LinkedHashMap resJson = new LinkedHashMap();

		resJson.put("result", "success");
		resJson.put("message", "Vector disabled successfully!");

		return Json.toJson(resJson);
	}

	/**
	 * Response for /user/remove
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode removeSuccess() {
		LinkedHashMap resJson = new LinkedHashMap();

		resJson.put("result", "success");
		resJson.put("message", "Vector removed successfully!");

		return Json.toJson(resJson);
	}
}
