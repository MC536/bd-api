package models.disease;

import models.base.BaseDAOImpl;

/**
 * Created by joaochencci on 16/07/14.
 */
public class DiseaseDAOImpl extends BaseDAOImpl<DiseaseTO> implements DiseaseDAO {

	private static DiseaseDAOImpl instance;

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	static DiseaseDAOImpl getInstance() {
		if (instance == null) {
			instance = new DiseaseDAOImpl();
		}
		return instance;
	}
}
