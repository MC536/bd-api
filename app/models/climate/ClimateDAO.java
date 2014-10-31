package models.climate;

import models.base.BaseDAO;

/**
 * Created by joaochencci on 16/07/14.
 */
public interface ClimateDAO extends BaseDAO<ClimateTO> {
    public ClimateTO getByName(String name);
}
