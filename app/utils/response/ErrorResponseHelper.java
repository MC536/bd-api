package utils.response;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.LinkedHashMap;
import play.libs.Json;

/**
 * Created by joaochencci on 28/07/14.
 */
public class ErrorResponseHelper {

	public enum SystemErrors {
		INVALID_EMAIL(101, "Invalid email."),
		INVALID_PASSWORD(102, "Invalid password."),
		USER_NOT_FOUND(103, "User not found."),
		WRONG_PASSWORD(104, "Wrong password."),
		USER_ALREADY_REGISTERED(105, "User already registered."),
		USER_TOKEN_INVALID(106, "User token invalid."),
		CANNOT_ASSIGN_ROLE(107, "Cannot assign role."),
		INVALID_APP_KEY(108, "Invalid app key."),
		BAD_CREDENTIALS(109, "Bad credentials."),
		BAD_TOKEN(110, "Bad token."),
		INVALID_CPF(111, "Invalid CPF."),
		VECTOR_NOT_FOUND(201, "Vector not found."),
        DISEASE_NOT_FOUND(301, "Disease not found."),
        CLIMATE_NOT_FOUND(401, "Climate not found.");

		private Integer code;
		private String message;

		private SystemErrors(Integer code, String message) {
			this.code = code;
			this.message = message;
		}

		public Integer getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}
	}

	;

	/**
	 * Step method to support all other Error responses to create responses.
	 *
	 * @param code
	 * 		Error code
	 * @param msg
	 * 		Error message
	 *
	 * @return Error API's response JSON with all needed information
	 */
	private static LinkedHashMap generateError(Integer code, String msg) {
		LinkedHashMap resJson = new LinkedHashMap();
		LinkedHashMap execptionJson = new LinkedHashMap();

		execptionJson.put("code", code);
		execptionJson.put("error", msg);

		resJson.put("result", "error");
		resJson.put("message", msg);
		resJson.put("exception", execptionJson);

		return resJson;
	}

	/**
	 * Error 101 - Invalid email
	 *
	 * @return Generated response JSON.
	 */
	public static JsonNode invalidEmailError() {
		return Json.toJson(
				generateError(SystemErrors.INVALID_EMAIL.getCode(), SystemErrors.INVALID_EMAIL.getMessage()));
	}

	/**
	 * Error 102 - Invalid password
	 *
	 * @return Generated response JSON.
	 */
	public static JsonNode invalidPasswordlError() {
		return Json.toJson(
				generateError(SystemErrors.INVALID_PASSWORD.getCode(), SystemErrors.INVALID_PASSWORD.getMessage()));
	}

	/**
	 * Error 103 - User not found
	 *
	 * @return Generated response JSON.
	 */
	public static JsonNode userNotFoundError() {
		return Json.toJson(
				generateError(SystemErrors.USER_NOT_FOUND.getCode(), SystemErrors.USER_NOT_FOUND.getMessage()));
	}

	/**
	 * Error 104 - Wrong password
	 *
	 * @return Generated response JSON.
	 */
	public static JsonNode wrongPasswordError() {
		return Json.toJson(
				generateError(SystemErrors.WRONG_PASSWORD.getCode(), SystemErrors.WRONG_PASSWORD.getMessage()));
	}

	/**
	 * Error 105 - User already registered
	 *
	 * @return Generated response JSON.
	 */
	public static JsonNode userAlreadyRegisteredError() {
		return Json.toJson(generateError(SystemErrors.USER_ALREADY_REGISTERED.getCode(),
				SystemErrors.USER_ALREADY_REGISTERED.getMessage()));
	}

	/**
	 * Error 106 - Invalid token
	 *
	 * @return Generated response JSON.
	 */
	public static JsonNode userTokenInvalidError() {
		return Json.toJson(
				generateError(SystemErrors.USER_TOKEN_INVALID.getCode(), SystemErrors.USER_TOKEN_INVALID.getMessage()));
	}

	/**
	 * Error 107 - Cannot assign role
	 *
	 * @return Generated response JSON.
	 */
	public static JsonNode cannotAssignRoleError() {
		return Json.toJson(
				generateError(SystemErrors.CANNOT_ASSIGN_ROLE.getCode(), SystemErrors.CANNOT_ASSIGN_ROLE.getMessage()));
	}

	/**
	 * Error 108 - Invalid app key
	 *
	 * @return Generated response JSON.
	 */
	public static JsonNode invalidAppKeyError() {
		return Json.toJson(
				generateError(SystemErrors.INVALID_APP_KEY.getCode(), SystemErrors.INVALID_APP_KEY.getMessage()));
	}

	/**
	 * Error 109 - Bad credentials
	 *
	 * @return Generated response JSON.
	 */
	public static JsonNode badCredentialsError() {
		return Json.toJson(
				generateError(SystemErrors.BAD_CREDENTIALS.getCode(), SystemErrors.BAD_CREDENTIALS.getMessage()));
	}

	/**
	 * Error 110 - Bad token
	 *
	 * @return Generated response JSON.
	 */
	public static JsonNode badTokenError() {
		return Json.toJson(generateError(SystemErrors.BAD_TOKEN.getCode(), SystemErrors.BAD_TOKEN.getMessage()));
	}

	/**
	 * Error 111 - Invalid CPF
	 *
	 * @return Generated response JSON.
	 */
	public static JsonNode invalidCPFError() {
		return Json.toJson(generateError(SystemErrors.INVALID_CPF.getCode(), SystemErrors.INVALID_CPF.getMessage()));
	}

	/**
	 * Error 201 - Vector not found
	 *
	 * @return Generated response JSON.
	 */
	public static JsonNode vectorNotFoundError() {
		return Json.toJson(
				generateError(SystemErrors.VECTOR_NOT_FOUND.getCode(), SystemErrors.VECTOR_NOT_FOUND.getMessage()));
	}

    /**
     * Error 301 - Disease not found
     *
     * @return Generated response JSON.
     */
    public static JsonNode diseaseNotFoundError() {
        return Json.toJson(
                generateError(SystemErrors.DISEASE_NOT_FOUND.getCode(), SystemErrors.DISEASE_NOT_FOUND.getMessage()));
    }

    /**
     * Error 401 - Climate not found
     *
     * @return Generated response JSON.
     */
    public static JsonNode climateNotFoundError() {
        return Json.toJson(
                generateError(SystemErrors.CLIMATE_NOT_FOUND.getCode(), SystemErrors.CLIMATE_NOT_FOUND.getMessage()));
    }
}
