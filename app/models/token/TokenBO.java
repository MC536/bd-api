package models.token;

import models.base.BaseBO;

/**
 * Created by joaochencci on 16/07/14.
 */

public class TokenBO extends BaseBO<TokenTO, TokenDAO> {

	private static TokenBO instance;
	private TokenDAO tokenDAO;
	private TokenTO tokenTO;

	/**
	 * Method getInstance is the singleton constructor and returns an unique instance of this class.
	 *
	 * @return An instance of this class to be used anywhere as a singleton.
	 */
	public static TokenBO getInstance() {
		if (instance == null) {
			instance = new TokenBO();
			instance.tokenDAO = TokenDAOImpl.getInstance();
			instance.tokenTO = new TokenTO();
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
	protected TokenDAO getDao() {
		return tokenDAO;
	}

	/**
	 * Method getTo is the abstract method implementation written on BaseBO that returns what type of TO this class
	 * want to deal with.
	 *
	 * @return A private RolePermissionTO variable.
	 */
	@Override
	protected TokenTO getTo() {
		return tokenTO;
	}

	//Methods
	public TokenTO createToken(String value){
		TokenTO res = new TokenTO();
		res.setValue(value);
		res.setActive(true);
		return res;
	}

	public TokenTO getByValue(String value) {
		return getDao().getByValue(value);
	}

}
