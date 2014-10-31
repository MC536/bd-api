package utils.response;

import com.fasterxml.jackson.databind.JsonNode;
import models.climate.ClimateTO;
import models.location.LocationTO;
import play.libs.Json;

import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 29/07/14.
 */
public class LocationResponseHelper {

	/**
	 * Response for /location/get
	 *
	 * @param location
	 * 		Get climate
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode getSuccess(LocationTO location) {
		LinkedHashMap resJson = new LinkedHashMap();
		LinkedHashMap locationJson = new LinkedHashMap();
        LinkedHashMap climateJson = new LinkedHashMap();

        climateJson.put("climate", location.getClimate().getName());

        locationJson.put("id", location.getId());
        locationJson.put("name", location.getName());
        locationJson.put("climate", climateJson);

		resJson.put("result", "success");
		resJson.put("location", locationJson);

		return Json.toJson(resJson);
	}

	/**
	 * Response for /location/create
	 *
	 * @param location
	 * 		Created climate
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode createSuccess(LocationTO location) {
		LinkedHashMap resJson = new LinkedHashMap();
		LinkedHashMap locationJson = new LinkedHashMap();

        locationJson.put("id", location.getId());
        locationJson.put("name", location.getId());

        resJson.put("result", "success");
		resJson.put("location", locationJson);

		return Json.toJson(resJson);
	}

	/**
	 * Response for /location/disable
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode disableSuccess() {
		LinkedHashMap resJson = new LinkedHashMap();

		resJson.put("result", "success");
		resJson.put("message", "Location disabled successfully!");

		return Json.toJson(resJson);
	}

	/**
	 * Response for /location/remove
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode removeSuccess() {
		LinkedHashMap resJson = new LinkedHashMap();

		resJson.put("result", "success");
		resJson.put("message", "Location removed successfully!");

		return Json.toJson(resJson);
	}
}
