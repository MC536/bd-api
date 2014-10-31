package models.base;

import java.util.Date;

/**
 * Created by joaochencci on 26/06/14.
 */
public abstract class BaseBO <T extends BaseTO, Dao extends BaseDAO<T>> {

	/**
	 * Method save persists the given transient instance, first assigning a generated identifier. (Or using the current
	 * value of the identifier property if the assigned generator is used.)
	 *
	 * @param object
	 * 		A transient instance of a persistent class.
	 */
	public void save(T object) {
		if (object.getId() == null) {
			object.setCreationDate(new Date());
		}
		object.setModifyDate(new Date());
		getDao().saveOrUpdate(object);
	}

	/**
	 * Method update persists the given transient instance with the changes. (Or using the current
	 * value of the identifier property if the assigned generator is used.)
	 *
	 * @param object
	 * 		A transient instance of a persistent class.
	 */
	public void update(T object) {
		object.setModifyDate(new Date());
		getDao().update(object);
	}

	/**
	 * Abstract method getDao must be implemented by each BO classes that extend BaseBO.
	 */
	protected abstract Dao getDao();

	/**
	 * Abstract method getTo must be implemented by each BO classes that extend BaseBO.
	 */
	protected abstract T getTo();

	/**
	 * Method remove removes a persistent instance from the datastore. The argument may be an instance associated with
	 * the receiving Session or a transient instance with an identifier associated with existing persistent state.
	 *
	 * @param id
	 * 		Unique instance identifier to remove.
	 */
	public void remove(String id) {
		T to = get(id);
		getDao().remove(to);
	}

	/**
	 * Method get returns the persistent instance of the given entity class with the given identifier, or null if there
	 * is no such persistent instance. (If the instance is already associated with the session, return that instance.
	 * This method never returns an uninitialized instance.)
	 *
	 * @param id
	 * 		Unique instance identifier to retrieve.
	 *
	 * @return The persistent instance or null.
	 */
	public T get(String id) {
		Dao dao = getDao();
		T to = getTo();
		return dao.findById(Long.valueOf(id), to.getClass());
	}

	/**
	 * Completely clear the session. Evict all loaded instances and cancel all pending saves, updates and deletions. Do
	 * not close open iterators or instances of ScrollableResults.
	 */
	public void clearSession() {
		getDao().clearSession();
	}
}
