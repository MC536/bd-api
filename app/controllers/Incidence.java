package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.disease.DiseaseBO;
import models.disease.DiseaseTO;
import models.incidence.IncidenceBO;
import models.incidence.IncidenceTO;
import models.location.LocationBO;
import models.location.LocationTO;
import models.user.UserBO;
import models.user.UserTO;
import play.mvc.Controller;
import play.mvc.Result;
import utils.request.DiseaseRequestHelper;
import utils.request.IncidenceRequestHelper;
import utils.response.ErrorResponseHelper;
import utils.response.IncidenceResponseHelper;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 25/06/14.
 */
public class Incidence extends Controller {

    private static IncidenceBO mgr = IncidenceBO.getInstance();
    private static UserBO mgrUser = UserBO.getInstance();
    private static LocationBO mgrLocation = LocationBO.getInstance();
    private static DiseaseBO mgrDisease = DiseaseBO.getInstance();

    /**
     * Endpoint: /incidence/get Get Incidence by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result getIncidence() {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = IncidenceRequestHelper.get(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                IncidenceTO incidence = mgr.get(id);
                if (incidence != null) {
                    res = ok(IncidenceResponseHelper.getSuccess(incidence));
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
     * Endpoint: /incidence/create Create Incidence by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result create() {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = IncidenceRequestHelper.create(data);

        String token = parser.get("token")
                .toString();
        String locationId = parser.get("locationId").toString();
        String diseaseId = parser.get("diseaseId").toString();
        String value = parser.get("value").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                LocationTO location = mgrLocation.get(locationId);
                DiseaseTO disease = mgrDisease.get(diseaseId);
                IncidenceTO incidence = mgr.createIncidence(Integer.parseInt(value), location, disease);
                mgr.save(incidence);

                res = ok(IncidenceResponseHelper.createSuccess(incidence));
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
     * Endpoint: /incidence/remove Remove Incidence by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result remove() {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = IncidenceRequestHelper.disableOrRemove(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                IncidenceTO target = mgr.get(id);
                if (target != null) {
                    mgr.remove(id);
                    res = ok(IncidenceResponseHelper.removeSuccess());
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
     * Endpoint: /incidence/disable Disable Incidence by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result disable() {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = IncidenceRequestHelper.disableOrRemove(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                IncidenceTO target = mgr.get(id);
                if (target != null) {
                    target.setActive(false);
                    target.setModifyDate(new Date());
                    mgr.update(target);
                    res = ok(IncidenceResponseHelper.disableSuccess());
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
