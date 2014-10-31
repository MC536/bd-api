package models.token;

import models.base.BaseDAOImpl;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by joaochencci on 16/07/14.
 */
public class TokenDAOImpl extends BaseDAOImpl<TokenTO> implements TokenDAO {

	private static TokenDAOImpl instance;

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	static TokenDAOImpl getInstance() {
		if (instance == null) {
			instance = new TokenDAOImpl();
		}
		return instance;
	}

	public TokenTO getByValue(String value) {
		TokenTO res;
		Session sess = prepareSession();

		Criteria c = sess.createCriteria(TokenTO.class);
		c.add(Restrictions.eq("value", value));
		List results = c.list();

		if (!results.isEmpty()) {
			res = (TokenTO) results.get(0);
		}
		else {
			res = null;
		}

		return res;
	}
}
