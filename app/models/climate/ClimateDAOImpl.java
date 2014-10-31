package models.climate;

import models.base.BaseDAOImpl;
import models.user.UserTO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by joaochencci on 16/07/14.
 */
public class ClimateDAOImpl extends BaseDAOImpl<ClimateTO> implements ClimateDAO {

	private static ClimateDAOImpl instance;

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	static ClimateDAOImpl getInstance() {
		if (instance == null) {
			instance = new ClimateDAOImpl();
		}
		return instance;
	}

    /**
     * Method get from database an Climate with the input name.
     *
     * @param name
     * 		Input name that will be used in the Climate's query.
     */
    public ClimateTO getByName(String name) {
        ClimateTO res;
        Session sess = prepareSession();

        Criteria c = sess.createCriteria(ClimateTO.class);
        c.add(Restrictions.eq("name", name));
        List results = c.list();

        if (!results.isEmpty()) {
            res = (ClimateTO) results.get(0);
        }
        else {
            res = null;
        }

        return res;
    }
}
