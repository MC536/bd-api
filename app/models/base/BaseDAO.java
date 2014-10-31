package models.base;

/**
 * Created by joaochencci on 26/06/14.
 */
public interface BaseDAO <T extends BaseTO> {

	public T findById(Long id, Class obj);

	public void clearSession();

	public void saveOrUpdate(T entity);

	public void remove(T entity);

	public void update(T entity);
}
