package utils.response;

import com.fasterxml.jackson.databind.JsonNode;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.TimeZone;
import models.user.UserTO;
import play.libs.Json;

/**
 * Created by joaochencci on 29/07/14.
 */
public class UserResponseHelper {

	/**
	 * Response for /user/get
	 *
	 * @param user
	 * 		Get user
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode getSuccess(UserTO user) {
		LinkedHashMap resJson = new LinkedHashMap();
		LinkedHashMap userJson = new LinkedHashMap();

		userJson.put("id", user.getId());
		userJson.put("email", user.getEmail());
		userJson.put("name", user.getName());

		if(user.getToken() != null) {
			LinkedHashMap tokenJson = new LinkedHashMap();
			tokenJson.put("value", user.getToken()
									   .getValue());
			userJson.put("token", tokenJson);
		}

		resJson.put("result", "success");
		resJson.put("user", userJson);

		return Json.toJson(resJson);
	}

	/**
	 * Response for /user/create
	 *
	 * @param user
	 * 		Created user
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode createSuccess(UserTO user) {
		LinkedHashMap resJson = new LinkedHashMap();
		LinkedHashMap userJson = new LinkedHashMap();

		userJson.put("id", user.getId());
		userJson.put("email", user.getEmail());
        userJson.put("token", user.getToken().getValue());
		userJson.put("active", user.getActive());

		resJson.put("result", "success");
		resJson.put("user", userJson);

		return Json.toJson(resJson);
	}

	/**
	 * Response for /user/login
	 *
	 * @param user
	 * 		Login user
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode loginSuccess(UserTO user) {
		LinkedHashMap resJson = new LinkedHashMap();
		LinkedHashMap userJson = new LinkedHashMap();
		LinkedHashMap tokenJson = new LinkedHashMap();

		tokenJson.put("value", user.getToken()
								  .getValue());
		userJson.put("id", user.getId());
		userJson.put("email", user.getEmail());
		userJson.put("active", user.getActive());
		userJson.put("token", tokenJson);

		resJson.put("result", "success");
		resJson.put("user", userJson);

		return Json.toJson(resJson);
	}

	/**
	 * Response for /user/logout
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode logoutSuccess() {
		LinkedHashMap resJson = new LinkedHashMap();

		resJson.put("result", "success");
		resJson.put("message", "Lougout ok!");

		return Json.toJson(resJson);
	}

	/**
	 * Response for /user/changePassword
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode changePasswordSuccess() {
		LinkedHashMap resJson = new LinkedHashMap();

		resJson.put("result", "success");
		resJson.put("message", "Change password ok!");

		return Json.toJson(resJson);
	}

	/**
	 * Response for /user/disable
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode disableUserSuccess() {
		LinkedHashMap resJson = new LinkedHashMap();

		resJson.put("result", "success");
		resJson.put("message", "User disabled successfully!");

		return Json.toJson(resJson);
	}

	/**
	 * Response for /user/remove
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode removeUserSuccess() {
		LinkedHashMap resJson = new LinkedHashMap();

		resJson.put("result", "success");
		resJson.put("message", "User removed successfully!");

		return Json.toJson(resJson);
	}

	/**
	 * Response for /user/remove
	 *
	 * @return API's response JSON with all needed information.
	 */
	public static JsonNode recoverPasswordSuccess() {
		LinkedHashMap resJson = new LinkedHashMap();

		resJson.put("result", "success");
		resJson.put("message", "Recover email sent successfully!");

		return Json.toJson(resJson);
	}

}
