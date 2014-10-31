package models.vector_disease;

import models.base.BaseDAOImpl;

/**
 * Created by joaochencci on 16/07/14.
 */
public class VectorDiseaseDAOImpl extends BaseDAOImpl<VectorDiseaseTO> implements VectorDiseaseDAO {

	private static VectorDiseaseDAOImpl instance;

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	static VectorDiseaseDAOImpl getInstance() {
		if (instance == null) {
			instance = new VectorDiseaseDAOImpl();
		}
		return instance;
	}
}
