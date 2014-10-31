package models.climate;

import models.base.BaseBO;

/**
 * Created by joaochencci on 16/07/14.
 */

public class ClimateBO extends BaseBO<ClimateTO, ClimateDAO> {

	private static ClimateBO instance;
	private ClimateDAO climateDAO;
	private ClimateTO climateTO;

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	public static ClimateBO getInstance() {
		if (instance == null) {
			instance = new ClimateBO();
			instance.climateDAO = ClimateDAOImpl.getInstance();
			instance.climateTO = new ClimateTO();
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
	protected ClimateDAO getDao() {
		return climateDAO;
	}

	/**
	 * Method getTo is the abstract method implementation written on BaseBO that returns what type of TO this class
	 * want to deal with.
	 *
	 * @return A private RolePermissionTO variable.
	 */
	@Override
	protected ClimateTO getTo() {
		return climateTO;
	}

	//Methods
	public ClimateTO createClimate(String name, Integer min, Integer max, Integer range){
		ClimateTO res = new ClimateTO();
		res.setActive(true);
        res.setName(name);
        res.setMinTemperature(min);
        res.setMaxTemperature(max);
        res.setRangeTemperature(range);
		return res;
	}

    /**
     * Method get from database an User with the input email.
     *
     * @param email
     * 		Input email that will be used in the User's query.
     */
    public ClimateTO getByName(String name) {
        return getDao().getByName(name);
    }
}
