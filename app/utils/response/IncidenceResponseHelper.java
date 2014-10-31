package utils.response;

import com.fasterxml.jackson.databind.JsonNode;
import models.disease.DiseaseTO;
import models.incidence.IncidenceTO;
import play.libs.Json;

import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 29/07/14.
 */
public class IncidenceResponseHelper {

	/**
	 * Response for /incidence/get
	 *
	 * @param incidence
	 * 		Get vector
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode getSuccess(IncidenceTO incidence) {
		LinkedHashMap resJson = new LinkedHashMap();
		LinkedHashMap incidenceJson = new LinkedHashMap();

        incidenceJson.put("id", incidence.getId());
        incidenceJson.put("value", incidence.getValue());
        incidenceJson.put("disease", incidence.getDisease().getPopularName());
        incidenceJson.put("location", incidence.getLocation().getName());

		resJson.put("result", "success");
		resJson.put("incidence", incidenceJson);

		return Json.toJson(resJson);
	}

	/**
	 * Response for /incidence/create
	 *
	 * @param incidence
	 * 		Created incidence
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode createSuccess(IncidenceTO incidence) {
		LinkedHashMap resJson = new LinkedHashMap();
		LinkedHashMap incidenceJson = new LinkedHashMap();

        incidenceJson.put("id", incidence.getId());
        incidenceJson.put("value", incidence.getValue());
        incidenceJson.put("disease", incidence.getDisease().getPopularName());
        incidenceJson.put("location", incidence.getLocation().getName());

        resJson.put("result", "success");
		resJson.put("incidence", incidenceJson);

		return Json.toJson(resJson);
	}

	/**
	 * Response for /incidence/disable
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode disableSuccess() {
		LinkedHashMap resJson = new LinkedHashMap();

		resJson.put("result", "success");
		resJson.put("message", "Incidence disabled successfully!");

		return Json.toJson(resJson);
	}

	/**
	 * Response for /incidence/remove
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode removeSuccess() {
		LinkedHashMap resJson = new LinkedHashMap();

		resJson.put("result", "success");
		resJson.put("message", "Incidence removed successfully!");

		return Json.toJson(resJson);
	}
}
