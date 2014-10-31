package models.incidence;

import models.base.BaseDAOImpl;

/**
 * Created by joaochencci on 16/07/14.
 */
public class IncidenceDAOImpl extends BaseDAOImpl<IncidenceTO> implements IncidenceDAO {

	private static IncidenceDAOImpl instance;

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	static IncidenceDAOImpl getInstance() {
		if (instance == null) {
			instance = new IncidenceDAOImpl();
		}
		return instance;
	}
}
