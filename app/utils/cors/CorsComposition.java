package utils.cors;

import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.With;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by joaochencci on 30/07/14.
 */


public class CorsComposition {

	/**
	 * Wraps the annotated action in an <code>CorsAction</code>.
	 */
	@With(CorsAction.class)
	@Target({ ElementType.TYPE, ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Cors {
		String value() default "*";
	}

	public static class CorsAction extends Action<Cors> {

		@Override
		public F.Promise<play.mvc.Result> call(Http.Context context) throws Throwable {
			Http.Response response = context.response();
			response.setHeader("Access-Control-Allow-Origin", "*");

			//Handle preflight requests
			//if(context.request().method().equals("POST")) {
			//	response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
			//	response.setHeader("Access-Control-Max-Age", "3600");
			//	response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, X-Auth-Token");
			//	response.setHeader("Access-Control-Allow-Credentials", "true");
			//
			//	return delegate.call(context);
			//}

			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, X-Auth-Token");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			return delegate.call(context);
		}
	}
}