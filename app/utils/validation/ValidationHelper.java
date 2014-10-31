package utils.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by joaochencci on 25/07/14.
 */
public class ValidationHelper {

	/**
	 * Support method to check if input email is valid.
	 *
	 * @param email
	 * 		Email to be checked
	 *
	 * @return True if the email is valid, false otherwise.
	 */
	public static Boolean isValidEmail(String email) {
		Boolean res;

		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		res = matcher.find();

		return res;
	}

	/**
	 * Support method to check if input password is valid.
	 *
	 * @param password
	 * 		Password to be checked
	 *
	 * @return True if the password is valid, false otherwise.
	 */
	public static Boolean isValidPassword(String password) {
		Boolean res;

		if (password.length() > 5) {
			res = true;
		}
		else {
			res = false;
		}

		return res;
	}

    /**
     * Support method to check if input climate is valid.
     *
     * @param climate
     * 		Climate to be checked
     *
     * @return True if the climate is valid, false otherwise.
     */
    public static String isClimate(String climate) {
        String res;

        if(climate.equals(">am, aw") || climate.equals(">()")) {
            res = "tropical";
        }
        else if(climate.equals(">cfa/cfb")) {
            res = "subtropical";
        }
        else {
            int start = 1;
            int end = climate.length();

            res = climate.substring(start, end);
        }

        return res.toLowerCase();
    }
}