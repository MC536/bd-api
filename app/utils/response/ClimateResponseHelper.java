package utils.response;

import com.fasterxml.jackson.databind.JsonNode;
import models.climate.ClimateTO;
import models.incidence.IncidenceTO;
import play.libs.Json;

import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 29/07/14.
 */
public class ClimateResponseHelper {

	/**
	 * Response for /climate/get
	 *
	 * @param climate
	 * 		Get climate
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode getSuccess(ClimateTO climate) {
		LinkedHashMap resJson = new LinkedHashMap();
		LinkedHashMap climateJson = new LinkedHashMap();

        climateJson.put("id", climate.getId());
        climateJson.put("name", climate.getName());
        climateJson.put("minTemperature", climate.getMinTemperature());
        climateJson.put("maxTemperature", climate.getMaxTemperature());
        climateJson.put("rangeTemperature", climate.getRangeTemperature());

		resJson.put("result", "success");
		resJson.put("climate", climateJson);

		return Json.toJson(resJson);
	}

	/**
	 * Response for /climate/create
	 *
	 * @param climate
	 * 		Created climate
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode createSuccess(ClimateTO climate) {
		LinkedHashMap resJson = new LinkedHashMap();
		LinkedHashMap climateJson = new LinkedHashMap();

        climateJson.put("id", climate.getId());
        climateJson.put("name", climate.getId());
        climateJson.put("minTemperature", climate.getId());
        climateJson.put("maxTemperature", climate.getId());
        climateJson.put("rangeTemperature", climate.getId());

        resJson.put("result", "success");
		resJson.put("climate", climateJson);

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
