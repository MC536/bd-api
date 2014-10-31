package models.incidence;

import models.base.BaseBO;
import models.disease.DiseaseTO;
import models.location.LocationTO;

/**
 * Created by joaochencci on 16/07/14.
 */

public class IncidenceBO extends BaseBO<IncidenceTO, IncidenceDAO> {

	private static IncidenceBO instance;
	private IncidenceDAO incidenceDAO;
	private IncidenceTO incidenceTO;

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	public static IncidenceBO getInstance() {
		if (instance == null) {
			instance = new IncidenceBO();
			instance.incidenceDAO = IncidenceDAOImpl.getInstance();
			instance.incidenceTO = new IncidenceTO();
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
	protected IncidenceDAO getDao() {
		return incidenceDAO;
	}

	/**
	 * Method getTo is the abstract method implementation written on BaseBO that returns what type of TO this class
	 * want to deal with.
	 *
	 * @return A private RolePermissionTO variable.
	 */
	@Override
	protected IncidenceTO getTo() {
		return incidenceTO;
	}

	//Methods
	public IncidenceTO createIncidence(Integer value, LocationTO location, DiseaseTO disease){
		IncidenceTO res = new IncidenceTO();
        res.setDisease(disease);
        res.setLocation(location);
        res.setValue(value);
		res.setActive(true);
		return res;
	}
}
