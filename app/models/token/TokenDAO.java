package models.token;

import models.base.BaseDAO;

/**
 * Created by joaochencci on 16/07/14.
 */
public interface TokenDAO extends BaseDAO<TokenTO> {

	public TokenTO getByValue(String value);

}
