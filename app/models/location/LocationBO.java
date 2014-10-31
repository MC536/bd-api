package models.location;

import models.base.BaseBO;
import models.climate.ClimateTO;

/**
 * Created by joaochencci on 16/07/14.
 */

public class LocationBO extends BaseBO<LocationTO, LocationDAO> {

	private static LocationBO instance;
	private LocationDAO locationDAO;
	private LocationTO locationTO;

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	public static LocationBO getInstance() {
		if (instance == null) {
			instance = new LocationBO();
			instance.locationDAO = LocationDAOImpl.getInstance();
			instance.locationTO = new LocationTO();
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
	protected LocationDAO getDao() {
		return locationDAO;
	}

	/**
	 * Method getTo is the abstract method implementation written on BaseBO that returns what type of TO this class
	 * want to deal with.
	 *
	 * @return A private RolePermissionTO variable.
	 */
	@Override
	protected LocationTO getTo() {
		return locationTO;
	}

	//Methods
	public LocationTO createLocation(String name, Integer population, ClimateTO climate){
		LocationTO res = new LocationTO();
        res.setName(name);
        res.setPopulation(population);
        res.setClimate(climate);
		res.setActive(true);
		return res;
	}
}
