package models.vector_disease;

import models.base.BaseBO;

/**
 * Created by joaochencci on 16/07/14.
 */

public class VectorDiseaseBO extends BaseBO<VectorDiseaseTO, VectorDiseaseDAO> {

	private static VectorDiseaseBO instance;
	private VectorDiseaseDAO vectorDiseaseDAO;
	private VectorDiseaseTO vectorDiseaseTO;

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	public static VectorDiseaseBO getInstance() {
		if (instance == null) {
			instance = new VectorDiseaseBO();
			instance.vectorDiseaseDAO = VectorDiseaseDAOImpl.getInstance();
			instance.vectorDiseaseTO = new VectorDiseaseTO();
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
	protected VectorDiseaseDAO getDao() {
		return vectorDiseaseDAO;
	}

	/**
	 * Method getTo is the abstract method implementation written on BaseBO that returns what type of TO this class
	 * want to deal with.
	 *
	 * @return A private RolePermissionTO variable.
	 */
	@Override
	protected VectorDiseaseTO getTo() {
		return vectorDiseaseTO;
	}

	//Methods
	public VectorDiseaseTO createVector(String family, String popularName, String scientificName, Integer bestTemp){
		VectorDiseaseTO res = new VectorDiseaseTO();
		res.setActive(true);
		return res;
	}
}
