package models.user;

import models.base.BaseDAOImpl;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by joaochencci on 25/06/14.
 */
public class UserDAOImpl extends BaseDAOImpl<UserTO> implements UserDAO {

	private static UserDAOImpl instance;

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	static UserDAOImpl getInstance() {
		if (instance == null) {
			instance = new UserDAOImpl();
		}
		return instance;
	}

	/**
	 * Method get from database an User with the input email.
	 *
	 * @param email
	 * 		Input email that will be used in the User's query.
	 */
	public UserTO getByEmail(String email) {
		UserTO res;
		Session sess = prepareSession();

		Criteria c = sess.createCriteria(UserTO.class);
		c.add(Restrictions.eq("email", email));
		List results = c.list();

		if (!results.isEmpty()) {
			res = (UserTO) results.get(0);
		}
		else {
			res = null;
		}

		return res;
	}

    /**
     * Method get from database an User with the input token code.
     *
     * @param value
     * 		Input token code that will be used in the User's query.
     */
    public UserTO getByToken(String value) {
        UserTO res;
        Session sess = prepareSession();

        Criteria c = sess.createCriteria(UserTO.class, "user");
        c.setFetchMode("user.token", FetchMode.JOIN);
        c.createAlias("user.token", "token");
        c.add(Restrictions.eq("token.value", value));

        List results = c.list();

        if (!results.isEmpty()) {
            res = (UserTO) results.get(0);
        }
        else {
            res = null;
        }

        return res;
    }
}
