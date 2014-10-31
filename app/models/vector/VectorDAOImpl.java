package models.vector;

import models.base.BaseDAOImpl;

/**
 * Created by joaochencci on 16/07/14.
 */
public class VectorDAOImpl extends BaseDAOImpl<VectorTO> implements VectorDAO {

	private static VectorDAOImpl instance;

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	static VectorDAOImpl getInstance() {
		if (instance == null) {
			instance = new VectorDAOImpl();
		}
		return instance;
	}
}
