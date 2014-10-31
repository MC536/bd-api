package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.user.UserBO;
import models.user.UserTO;
import models.vector.VectorBO;
import models.vector.VectorTO;
import play.mvc.Controller;
import play.mvc.Result;
import utils.request.VectorRequestHelper;
import utils.response.ErrorResponseHelper;
import utils.response.VectorResponseHelper;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 25/06/14.
 */
public class Vector extends Controller {

	private static VectorBO mgr = VectorBO.getInstance();
    private static UserBO mgrUser = UserBO.getInstance();

	/**
	 * Endpoint: /vector/get Get Vector by POST method receiving a JSON.
	 *
	 * @return JSON containing the request result.
	 */
	public static Result getVector() {
		Result res;

		JsonNode data = request().body()
								 .asJson();

        LinkedHashMap parser = VectorRequestHelper.get(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

		UserTO user = mgrUser.getByToken(token);
		if (user != null) {
			if (user.getToken()
					.getActive()) {
				VectorTO vector = mgr.get(id);
				if (vector != null) {
					res = ok(VectorResponseHelper.getSuccess(vector));
				}
				else {
					res = ok(ErrorResponseHelper.vectorNotFoundError());
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
	 * Endpoint: /vector/create Create Vector by POST method receiving a JSON.
	 *
	 * @return JSON containing the request result.
	 */
	public static Result create() {
		Result res;

		JsonNode data = request().body()
								 .asJson();

        LinkedHashMap parser = VectorRequestHelper.create(data);

        String token = parser.get("token").toString();
        String family = parser.get("family").toString();
        String popularName = parser.get("popularName").toString();
        String scientificName = parser.get("scientificName").toString();
        String bestTemperature = parser.get("bestTemperature").toString();

		UserTO user = mgrUser.getByToken(token);
		if (user != null) {
			if (user.getToken()
					.getActive()) {
				VectorTO vector = mgr.createVector(family, popularName, scientificName, Integer.parseInt(bestTemperature));
                mgr.save(vector);
                res = ok(VectorResponseHelper.createSuccess(vector));
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
	 * Endpoint: /vector/remove Remove Vector by POST method receiving a JSON.
	 *
	 * @return JSON containing the request result.
	 */
	public static Result remove() {
		Result res;

		JsonNode data = request().body()
								 .asJson();

        LinkedHashMap parser = VectorRequestHelper.disableOrRemove(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

		UserTO user = mgrUser.getByToken(token);
		if (user != null) {
			if (user.getToken()
					.getActive()) {
				VectorTO target = mgr.get(id);
				if (target != null) {
					mgr.remove(id);
					res = ok(VectorResponseHelper.removeSuccess());
				}
				else {
					res = ok(ErrorResponseHelper.vectorNotFoundError());
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
     * Endpoint: /vector/disable Disable Vector by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result disable() {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = VectorRequestHelper.disableOrRemove(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                VectorTO target = mgr.get(id);
                if (target != null) {
                    target.setActive(false);
                    target.setModifyDate(new Date());
                    mgr.update(target);
                    res = ok(VectorResponseHelper.disableSuccess());
                }
                else {
                    res = ok(ErrorResponseHelper.vectorNotFoundError());
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
