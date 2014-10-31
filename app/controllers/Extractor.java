package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.climate.ClimateBO;
import models.climate.ClimateTO;
import models.incidence.IncidenceBO;
import models.location.LocationBO;
import models.location.LocationTO;
import models.user.UserBO;
import models.user.UserTO;
import play.mvc.Controller;
import play.mvc.Result;
import utils.extract.LocationExtractorHelper;
import utils.request.ExtractorRequestHelper;
import utils.response.ErrorResponseHelper;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by joaochencci on 25/06/14.
 */
public class Extractor extends Controller {

    public static UserBO mgrUser = UserBO.getInstance();
    public static LocationBO mgrLocation = LocationBO.getInstance();
    public static ClimateBO mgrClimate= ClimateBO.getInstance();

    private enum Entities {
        Location;
    }

    public static boolean contains(String test) {

        for (Entities c : Entities.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Endpoint: /incidence/get Get Incidence by POST method receiving a JSON.
     *
     * @return JSON containing the request result.
     */
    public static Result extractData() throws IOException {
        Result res;

        JsonNode data = request().body()
                .asJson();

        LinkedHashMap parser = ExtractorRequestHelper.standardInput(data);

        String token = parser.get("token").toString();
        String entity = parser.get("entity").toString();

        UserTO user = mgrUser.getByToken(token);
        if (user != null) {
            if (user.getToken()
                    .getActive()) {
                if(contains(entity)) {
                    Entities selectedEntity = Entities.valueOf(entity);

                    switch(selectedEntity) {
                        case Location:
                            LinkedHashMap extractedData = LocationExtractorHelper.getLocationData();

                            Iterator<LinkedHashMap> it = extractedData.values().iterator();
                            for(int i=0; i<extractedData.size(); i++)
                            {
                                LinkedHashMap singleState = it.next();
                                String name = (String)singleState.get("name");
                                String population = (String)singleState.get("population");
                                String climateName = (String)singleState.get("climate");

                                ClimateTO climate = mgrClimate.getByName(climateName);

                                LocationTO location = mgrLocation.createLocation(name, Integer.valueOf(population), climate);
                                mgrLocation.save(location);
                            }

                            break;
                    }
                    res = ok("ok");
                }
                else {
                    res = ok("na");
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
