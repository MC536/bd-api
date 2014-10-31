package controllers;

import play.*;
import play.mvc.*;

import utils.cors.CorsComposition;
import utils.response.DefaultResponseHelper;
import utils.response.UserResponseHelper;
import views.html.*;

@CorsComposition.Cors
public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

	public static Result preflight(String path) {

		return ok(DefaultResponseHelper.defaultSuccess());
		//return movedPermanently("http://192.168.1.133" + path);
	}

}
