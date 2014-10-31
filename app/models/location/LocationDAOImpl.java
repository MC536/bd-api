package models.location;

import models.base.BaseDAOImpl;

/**
 * Created by joaochencci on 16/07/14.
 */
public class LocationDAOImpl extends BaseDAOImpl<LocationTO> implements LocationDAO {

	private static LocationDAOImpl instance;

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	static LocationDAOImpl getInstance() {
		if (instance == null) {
			instance = new LocationDAOImpl();
		}
		return instance;
	}
}
