package models.disease;

import models.base.BaseBO;

/**
 * Created by joaochencci on 16/07/14.
 */

public class DiseaseBO extends BaseBO<DiseaseTO, DiseaseDAO> {

	private static DiseaseBO instance;
	private DiseaseDAO diseaseDAO;
	private DiseaseTO diseaseTO;

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	public static DiseaseBO getInstance() {
		if (instance == null) {
			instance = new DiseaseBO();
			instance.diseaseDAO = DiseaseDAOImpl.getInstance();
			instance.diseaseTO = new DiseaseTO();
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
	protected DiseaseDAO getDao() {
		return diseaseDAO;
	}

	/**
	 * Method getTo is the abstract method implementation written on BaseBO that returns what type of TO this class
	 * want to deal with.
	 *
	 * @return A private RolePermissionTO variable.
	 */
	@Override
	protected DiseaseTO getTo() {
		return diseaseTO;
	}

	//Methods
	public DiseaseTO createDisease(String popularName, String scientificName){
		DiseaseTO res = new DiseaseTO();
		res.setActive(true);
        res.setPopularName(popularName);
        res.setScientificName(scientificName);
		return res;
	}
}
