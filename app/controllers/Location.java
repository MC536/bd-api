package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.climate.ClimateBO;
import models.climate.ClimateTO;
import models.location.LocationBO;
import models.location.LocationTO;
import models.user.UserBO;
import models.user.UserTO;
import play.mvc.Controller;
import play.mvc.Result;
import utils.request.ClimateRequestHelper;
import utils.request.DiseaseRequestHelper;
import utils.request.LocationRequestHelper;
import utils.response.ClimateResponseHelper;
import utils.response.ErrorResponseHelper;
import utils.response.LocationResponseHelper;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by joaochencci on 25/06/14.
 */
public class Location extends Controller {

    private static LocationBO mgr = LocationBO.getInstance();
    private static UserBO mgrUser = UserBO.getInstance();
    private static ClimateBO mgrClimate = ClimateBO.getInstance();

    /**
     * Endpoint: /location/get Get Location by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result getLocation() {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = LocationRequestHelper.get(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                LocationTO location = mgr.get(id);
                if (location != null) {
                    res = ok(LocationResponseHelper.getSuccess(location));
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
     * Endpoint: /location/create Create Location by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result create() {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = LocationRequestHelper.create(data);

        String token = parser.get("token").toString();
        String name = parser.get("name").toString();
        String climateId = parser.get("climateId").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                ClimateTO climate = mgrClimate.get(climateId);
                if (climate != null) {
                    LocationTO location = mgr.createLocation(name, 1, climate);
//                    LocationTO location = mgr.createLocation(name, 1);
                    mgr.save(location);
                    res = ok(LocationResponseHelper.createSuccess(location));
                }
                else {
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
     * Endpoint: /location/remove Remove Location by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result remove() {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = LocationRequestHelper.disableOrRemove(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                LocationTO target = mgr.get(id);
                if (target != null) {
                    mgr.remove(id);
                    res = ok(LocationResponseHelper.removeSuccess());
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
     * Endpoint: /location/disable Disable Location by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result disable() {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = LocationRequestHelper.disableOrRemove(data);

        String token = parser.get("token").toString();
        String id = parser.get("id").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                LocationTO target = mgr.get(id);
                if (target != null) {
                    target.setActive(false);
                    target.setModifyDate(new Date());
                    mgr.update(target);
                    res = ok(LocationResponseHelper.disableSuccess());
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
