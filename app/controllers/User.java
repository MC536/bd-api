package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.token.TokenBO;
import models.user.UserBO;
import models.user.UserTO;
import play.mvc.Controller;
import play.mvc.Result;
import utils.cors.CorsComposition;
import utils.request.UserRequestHelper;
import utils.response.ErrorResponseHelper;
import utils.response.UserResponseHelper;
import utils.validation.ValidationHelper;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 25/06/14.
 */
@CorsComposition.Cors
public class User extends Controller {

	private static UserBO mgr = UserBO.getInstance();
	private static TokenBO mgrToken = TokenBO.getInstance();

	/**
	 * Endpoint: /user/me Only returns the target User's basic info.
	 *
	 * @return JSON containing the request result and some User' basic info.
	 */
	public static Result me() {
		Result res;

		JsonNode data = request().body()
								 .asJson();

        LinkedHashMap parser = UserRequestHelper.me(data);
		String token = parser.get("token").toString();

        UserTO user = mgr.getByToken(token);
		if (user != null) {
			if(user.getToken().getActive()){
				res = ok(UserResponseHelper.getSuccess(user));
			}
			else {
				res = ok(ErrorResponseHelper.userTokenInvalidError());
			}
		}
		else {
			res = ok(ErrorResponseHelper.userNotFoundError());
		}

		return res;
	}

	/**
	 * Endpoint: /user/me Only returns the target User's basic info.
	 *
	 * @return JSON containing the request result and some User' basic info.
	 */
	public static Result getUser() {
		Result res;

		JsonNode data = request().body()
								 .asJson();

        LinkedHashMap parser = UserRequestHelper.get(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

		UserTO user = mgr.getByToken(token);
		if (user != null) {
			if (user.getToken()
					.getActive()) {
				UserTO newUser = mgr.get(id);
				if (newUser != null) {
					res = ok(UserResponseHelper.getSuccess(newUser));
				}
				else {
					res = ok(ErrorResponseHelper.userNotFoundError());
				}
			}
			else {
				res = ok(ErrorResponseHelper.userTokenInvalidError());
			}
		}
		else {
			res = ok(ErrorResponseHelper.badTokenError());
		}

		return res;
	}

	/**
	 * Endpoint: /user/new Create a new User by POST method receiving a JSON.
	 *
	 * @return JSON containing the request result and some User' basic info.
	 */
	public static Result create() {
		Result res;

		JsonNode data = request().body()
								 .asJson();

        LinkedHashMap parser = UserRequestHelper.create(data);

        String name = parser.get("name").toString();
        String email = parser.get("email").toString();
        String password = parser.get("password").toString();

        if (!ValidationHelper.isValidEmail(email)) {
            res = ok(ErrorResponseHelper.invalidEmailError());
        }
        if (!ValidationHelper.isValidPassword(password)) {
            res = ok(ErrorResponseHelper.invalidPasswordlError());
        }
        else {
            if (mgr.checkEmailAvailability(email)) {
                UserTO newUser = mgr.createUser(name, email, password);
                mgr.save(newUser);
                mgr.generateToken(newUser);

                res = ok(UserResponseHelper.createSuccess(newUser));
            }
            else {
                res = ok(ErrorResponseHelper.userAlreadyRegisteredError());
            }
        }

		return res;
	}

	/**
	 * Endpoint: /user/login Auth the User by POST method receiving a JSON. Also generate a code that provide access to
	 * Token.
	 *
	 * @return JSON containing the request result, some User' basic info and the access Token.
	 */
	public static Result login() {
		Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = UserRequestHelper.login(data);

        String email = parser.get("email").toString();
        String password = parser.get("password").toString();

		if (!ValidationHelper.isValidEmail(email)) {
			res = ok(ErrorResponseHelper.invalidEmailError());
		}
		else if (!ValidationHelper.isValidPassword(password)) {
			res = ok(ErrorResponseHelper.invalidPasswordlError());
		}
		else {
			UserTO user = mgr.getByEmail(email);

			if (user != null) {
				if (mgr.validatePassword(user, password)) {
					if (user.getToken() != null) {
						user.getToken()
							.setActive(false);
						mgrToken.update(user.getToken());
					}
                    mgr.generateToken(user);
					user.setModifyDate(new Date());
					res = ok(UserResponseHelper.loginSuccess(user));
				}
				else {
					//Wrong password
					res = ok(ErrorResponseHelper.badCredentialsError());
				}
			}
			else {
				//Wrong email
				res = ok(ErrorResponseHelper.badCredentialsError());
			}
		}

		return res;
	}

	/**
	 * Endpoint: /user/disable Disable User by POST method receiving a JSON.
	 *
	 * @return JSON containing the request result.
	 */
	public static Result disable() {
		Result res;

		JsonNode data = request().body()
								 .asJson();

        LinkedHashMap parser = UserRequestHelper.disableOrRemove(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

		UserTO user = mgr.getByToken(token);
		if (user != null) {
			if (user.getToken()
					.getActive()) {
				UserTO target = mgr.get(id);
				if (target != null) {
					target.setActive(false);
					target.setModifyDate(new Date());
					mgr.update(target);
					res = ok(UserResponseHelper.disableUserSuccess());
				}
				else {
					res = ok(ErrorResponseHelper.userNotFoundError());
				}
			}
			else {
				res = ok(ErrorResponseHelper.userTokenInvalidError());
			}
		}
		else {
			res = ok(ErrorResponseHelper.badTokenError());
		}

		return res;
	}

	/**
	 * Endpoint: /user/remove Remove User by POST method receiving a JSON.
	 *
	 * @return JSON containing the request result.
	 */
	public static Result remove() {
		Result res;

		JsonNode data = request().body()
								 .asJson();

        LinkedHashMap parser = UserRequestHelper.disableOrRemove(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

		UserTO user = mgr.getByToken(token);
		if (user != null) {
			if (user.getToken()
					.getActive()) {
				UserTO target = mgr.get(id);
				if (target != null) {
					mgr.remove(id);
					res = ok(UserResponseHelper.removeUserSuccess());
				}
				else {
					res = ok(ErrorResponseHelper.userNotFoundError());
				}
			}
			else {
				res = ok(ErrorResponseHelper.userTokenInvalidError());
			}
		}
		else {
			res = ok(ErrorResponseHelper.badTokenError());
		}

		return res;
	}

	/**
	 * Endpoint: /user/logout Logout User by POST method receiving a JSON. input Token.
	 *
	 * @return JSON containing the request result.
	 */
	public static Result logout() {
		Result res;

		JsonNode data = request().body()
								 .asJson();

        LinkedHashMap parser = UserRequestHelper.logout(data);

        String token = parser.get("token").toString();

		UserTO user = mgr.getByToken(token);
		if (user == null) {
			res = ok(ErrorResponseHelper.badTokenError());
		}
		else if (!user.getToken()
					  .getActive()) {
			res = ok(ErrorResponseHelper.userTokenInvalidError());
		}
		else {
			user.getToken()
				.setActive(false);
			user.setModifyDate(new Date());
			mgrToken.update(user.getToken());
			res = ok(UserResponseHelper.logoutSuccess());
		}

		return res;
	}

	/**
	 * Endpoint: /user/changePassword Change password by POST method receiving a JSON.
	 *
	 * @return JSON containing the request result.
	 */
	public static Result changePassword() {
		Result res;

		JsonNode data = request().body()
								 .asJson();

        LinkedHashMap parser = UserRequestHelper.changePassword(data);

        String token = parser.get("token").toString();
		String password = parser.get("password").toString();
		String newPassword = parser.get("newPassword").toString();

		UserTO user = mgr.getByToken(token);
		if (user != null) {
			if (user.getToken()
					.getActive()) {
				if (ValidationHelper.isValidPassword(password) && ValidationHelper.isValidPassword(newPassword)) {
					if (mgr.validatePassword(user, password)) {
						user.setPassword(newPassword);
						user.setModifyDate(new Date());
						mgr.update(user);
						res = ok(UserResponseHelper.changePasswordSuccess());
					}
					else {
						res = ok(ErrorResponseHelper.badCredentialsError());
					}
				}
				else {
					res = ok(ErrorResponseHelper.invalidPasswordlError());
				}
			}
			else {
				res = ok(ErrorResponseHelper.userTokenInvalidError());
			}
		}
		else {
			res = ok(ErrorResponseHelper.badTokenError());
		}

		return res;
	}
}
