package models.vector;

import models.base.BaseBO;

/**
 * Created by joaochencci on 16/07/14.
 */

public class VectorBO extends BaseBO<VectorTO, VectorDAO> {

	private static VectorBO instance;
	private VectorDAO vectorDAO;
	private VectorTO vectorTO;

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	public static VectorBO getInstance() {
		if (instance == null) {
			instance = new VectorBO();
			instance.vectorDAO = VectorDAOImpl.getInstance();
			instance.vectorTO = new VectorTO();
		}
		return instance;
	}

	/**
	 * Method getDao is the abstract method implementation written on BaseBO that returns what type of DAO this class
	 * want to deal with.
	 *
	 * @return A private RolePermissionDAO variable.
	 */
	@Override
	protected VectorDAO getDao() {
		return vectorDAO;
	}

	/**
	 * Method getTo is the abstract method implementation written on BaseBO that returns what type of TO this class
	 * want to deal with.
	 *
	 * @return A private RolePermissionTO variable.
	 */
	@Override
	protected VectorTO getTo() {
		return vectorTO;
	}

	//Methods
	public VectorTO createVector(String family, String popularName, String scientificName, Integer bestTemp){
		VectorTO res = new VectorTO();
		res.setActive(true);
        res.setFamily(family);
        res.setPopularName(popularName);
        res.setScientificName(scientificName);
        res.setBestTemperature(bestTemp);
		return res;
	}
}
