package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.climate.ClimateBO;
import models.climate.ClimateTO;
import models.user.UserBO;
import models.user.UserTO;
import play.mvc.Controller;
import play.mvc.Result;
import utils.request.ClimateRequestHelper;
import utils.request.DiseaseRequestHelper;
import utils.response.ClimateResponseHelper;
import utils.response.ErrorResponseHelper;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 25/06/14.
 */
public class Climate extends Controller {

    private static ClimateBO mgr = ClimateBO.getInstance();
    private static UserBO mgrUser = UserBO.getInstance();

    /**
     * Endpoint: /climate/get Get Climate by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result getClimate() {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = ClimateRequestHelper.get(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                ClimateTO climate = mgr.get(id);
                if (climate != null) {
                    res = ok(ClimateResponseHelper.getSuccess(climate));
                } else {
                    res = ok(ErrorResponseHelper.climateNotFoundError());
                }
            } else {
                res = ok(ErrorResponseHelper.userTokenInvalidError());
            }
        } else {
            res = ok(ErrorResponseHelper.badTokenError());
        }

        return res;
    }

    /**
     * Endpoint: /climate/create Create Climate by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result create() {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = ClimateRequestHelper.create(data);

        String token = parser.get("token").toString();
        String name = parser.get("name").toString();
        String minTemperature = parser.get("minTemperature").toString();
        String maxTemperature = parser.get("maxTemperature").toString();
        String rangeTemperature = parser.get("rangeTemperature").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                ClimateTO climate = mgr.createClimate(name, Integer.parseInt(minTemperature), Integer.parseInt(maxTemperature), Integer.parseInt(rangeTemperature));
                mgr.save(climate);
                res = ok(ClimateResponseHelper.createSuccess(climate));
            } else {
                res = ok(ErrorResponseHelper.userTokenInvalidError());
            }
        } else {
            res = ok(ErrorResponseHelper.badTokenError());
        }

        return res;
    }

    /**
     * Endpoint: /climate/remove Remove Climate by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result remove() {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = ClimateRequestHelper.disableOrRemove(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                ClimateTO target = mgr.get(id);
                if (target != null) {
                    mgr.remove(id);
                    res = ok(ClimateResponseHelper.removeSuccess());
                } else {
                    res = ok(ErrorResponseHelper.vectorNotFoundError());
                }
            } else {
                res = ok(ErrorResponseHelper.userTokenInvalidError());
            }
        } else {
            res = ok(ErrorResponseHelper.badTokenError());
        }

        return res;
    }

    /**
     * Endpoint: /climate/disable Disable Climate by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result disable() {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = ClimateRequestHelper.disableOrRemove(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                ClimateTO target = mgr.get(id);
                if (target != null) {
                    target.setActive(false);
                    target.setModifyDate(new Date());
                    mgr.update(target);
                    res = ok(ClimateResponseHelper.disableSuccess());
                } else {
                    res = ok(ErrorResponseHelper.vectorNotFoundError());
                }
            } else {
                res = ok(ErrorResponseHelper.userTokenInvalidError());
            }
        } else {
            res = ok(ErrorResponseHelper.badTokenError());
        }

        return res;
    }
}
