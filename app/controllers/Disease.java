package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.disease.DiseaseBO;
import models.disease.DiseaseTO;
import models.user.UserBO;
import models.user.UserTO;
import models.vector.VectorTO;
import play.mvc.Controller;
import play.mvc.Result;
import utils.request.DiseaseRequestHelper;
import utils.request.VectorRequestHelper;
import utils.response.DiseaseResponseHelper;
import utils.response.ErrorResponseHelper;
import utils.response.VectorResponseHelper;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 25/06/14.
 */
public class Disease extends Controller {

	private static DiseaseBO mgr = DiseaseBO.getInstance();
    private static UserBO mgrUser = UserBO.getInstance();

	/**
	 * Endpoint: /disease/get Get Disease by POST method receiving a JSON.
	 *
	 * @return JSON containing the request result.
	 */
	public static Result getDisease() {
		Result res;

		JsonNode data = request().body()
								 .asJson();

        LinkedHashMap parser = DiseaseRequestHelper.get(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

		UserTO user = mgrUser.getByToken(token);
		if (user != null) {
			if (user.getToken()
					.getActive()) {
				DiseaseTO disease = mgr.get(id);
				if (disease != null) {
					res = ok(DiseaseResponseHelper.getSuccess(disease));
				}
				else {
					res = ok(ErrorResponseHelper.diseaseNotFoundError());
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
	 * Endpoint: /product/create Create Store by POST method receiving a JSON.
	 *
	 * @return JSON containing the request result.
	 */
	public static Result create() {
		Result res;

		JsonNode data = request().body()
								 .asJson();

        LinkedHashMap parser = DiseaseRequestHelper.create(data);

        String token = parser.get("token")
						   .toString();
		String popularName = parser.get("popularName").toString();
        String scientificName = parser.get("scientificName").toString();

		UserTO user = mgrUser.getByToken(token);
		if (user != null) {
			if (user.getToken()
					.getActive()) {
				DiseaseTO disease = mgr.createDisease(popularName, scientificName);
				mgr.save(disease);
				res = ok(DiseaseResponseHelper.createSuccess(disease));
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
     * Endpoint: /disease/remove Remove Disease by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result remove() {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = DiseaseRequestHelper.disableOrRemove(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                DiseaseTO target = mgr.get(id);
                if (target != null) {
                    mgr.remove(id);
                    res = ok(DiseaseResponseHelper.removeSuccess());
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
     * Endpoint: /disease/disable Disable Disease by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result disable() {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = DiseaseRequestHelper.disableOrRemove(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                DiseaseTO target = mgr.get(id);
                if (target != null) {
                    target.setActive(false);
                    target.setModifyDate(new Date());
                    mgr.update(target);
                    res = ok(DiseaseResponseHelper.disableSuccess());
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
