package models.user;

import models.base.BaseDAO;

/**
 * Created by joaochencci on 25/06/14.
 */
public interface UserDAO extends BaseDAO<UserTO> {

	public UserTO getByEmail(String email);
    public UserTO getByToken(String token);
}
