package models.user;

import models.base.BaseBO;
import models.token.TokenBO;
import models.token.TokenTO;
import org.mindrot.jbcrypt.BCrypt;
import play.api.libs.Crypto;

import java.util.Date;

/**
 * Created by joaochencci on 25/06/14.
 */

public class UserBO extends BaseBO<UserTO, UserDAO> {

	private static UserBO instance;
	private UserDAO userDAO;

    private TokenBO mgrToken = TokenBO.getInstance();

	private UserTO userTO = new UserTO();

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	public static UserBO getInstance() {
		if (instance == null) {
			instance = new UserBO();
			instance.userDAO = UserDAOImpl.getInstance();
			instance.userTO = new UserTO();
		}
		return instance;
	}

	/**
	 * Method createUser receives some informations about the new user and return an instance of RolePermissionTO.
	 *
	 * @param email
	 * 		User's username string.
	 *
	 * @return An instance of RolePermissionTO with username, password, creation date, modify date and active status.
	 */
	public UserTO createUser(String name, String email, String password) {
		UserTO res = new UserTO();

		res.setName(name);
        res.setEmail(email);
        res.setPassword(password);
		res.setCreationDate(new Date());
		res.setModifyDate(new Date());
		res.setActive(true);

		return res;
	}

	/**
	 * Method check if the entered password is just like the encrypted database password.
	 *
	 * @param user
	 * 		Password's owner.
	 * @param password
	 * 		Input password to compare with the database one.
	 */
	public Boolean validatePassword(UserTO user, String password) {
		Boolean res;

		if (BCrypt.checkpw(password, user.getPassword())) {
			res = true;
		}
		else {
			res = false;
		}

		return res;
	}

	/**
	 * Method getDao is the abstract method implementation written on BaseBO that returns what type of DAO this class
	 * want to deal with.
	 *
	 * @return A private UserDAO variable.
	 */
	@Override
	protected UserDAO getDao() {
		return userDAO;
	}

	/**
	 * Method getTo is the abstract method implementation written on BaseBO that returns what type of TO this class want
	 * to deal with.
	 *
	 * @return A private UserTO variable.
	 */
	@Override
	protected UserTO getTo() {
		return userTO;
	}

    /**
     * Method generates a new Token to be set on the User.
     *
     * @param user
     * 		User that needs a new Token.
     */
    public TokenTO generateToken(UserTO user) {
        TokenTO res;

        res = mgrToken.createToken(Crypto.generateToken() + Crypto.generateToken());
        mgrToken.save(res);
        user.setToken(res);
        getDao().update(user);

        return res;
    }

	/**
	 * Method check if the input email is already registered.
	 *
	 * @param email
	 * 		Input email to compare.
	 */
	public Boolean checkEmailAvailability(String email) {
		Boolean res;

		UserTO user = getByEmail(email);
		if (user != null) {
			res = false;
		}
		else {
			res = true;
		}

		return res;
	}

	/**
	 * Method get from database an User with the input email.
	 *
	 * @param email
	 * 		Input email that will be used in the User's query.
	 */
	public UserTO getByEmail(String email) {
		return getDao().getByEmail(email);
	}

    /**
     * Method fetch an User by its Token's code.
     *
     * @param token
     * 		Token's value.
     *
     * @return Fetched User with the input token.
     */
    public UserTO getByToken(String token) {
        UserTO res;

        res = getDao().getByToken(token);

        return res;
    }

}
