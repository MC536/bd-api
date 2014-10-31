package utils.response;

import com.fasterxml.jackson.databind.JsonNode;
import models.disease.DiseaseTO;
import models.user.UserTO;
import models.vector.VectorTO;
import play.libs.Json;

import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 29/07/14.
 */
public class DiseaseResponseHelper {

	/**
	 * Response for /vector/get
	 *
	 * @param disease
	 * 		Get vector
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode getSuccess(DiseaseTO disease) {
		LinkedHashMap resJson = new LinkedHashMap();
		LinkedHashMap diseaseJson = new LinkedHashMap();

        diseaseJson.put("id", disease.getId());
        diseaseJson.put("popuparName", disease.getPopularName());
        diseaseJson.put("scientificName", disease.getScientificName());

		resJson.put("result", "success");
		resJson.put("disease", diseaseJson);

		return Json.toJson(resJson);
	}

	/**
	 * Response for /disease/create
	 *
	 * @param disease
	 * 		Created disease
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode createSuccess(DiseaseTO disease) {
		LinkedHashMap resJson = new LinkedHashMap();
		LinkedHashMap diseaseJson = new LinkedHashMap();

        diseaseJson.put("id", disease.getId());
        diseaseJson.put("popularName", disease.getPopularName());
        diseaseJson.put("scientificName", disease.getScientificName());

		resJson.put("result", "success");
		resJson.put("disease", diseaseJson);

		return Json.toJson(resJson);
	}

	/**
	 * Response for /disease/disable
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode disableSuccess() {
		LinkedHashMap resJson = new LinkedHashMap();

		resJson.put("result", "success");
		resJson.put("message", "Disease disabled successfully!");

		return Json.toJson(resJson);
	}

	/**
	 * Response for /disease/remove
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode removeSuccess() {
		LinkedHashMap resJson = new LinkedHashMap();

		resJson.put("result", "success");
		resJson.put("message", "Disease removed successfully!");

		return Json.toJson(resJson);
	}
}
