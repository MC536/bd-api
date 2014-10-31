package models.base;

import models.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by joaochencci on 26/06/14.
 */
@SuppressWarnings("unchecked")
public class BaseDAOImpl <T extends BaseTO> extends HibernateUtil implements BaseDAO<T> {

	private Class<T> entityClass = null;

	/**
	 * Method get returns the persistent instance of the given entity class with the given identifier, or null if there
	 * is no such persistent instance. (If the instance is already associated with the session, return that instance.
	 * This method never returns an uninitialized instance.)
	 *
	 * @param id
	 * 		Unique instance identifier to retrieve.
	 * @param obj
	 * 		Class reference to modify.
	 *
	 * @return The persistent instance or null.
	 */
	@Override
	public T findById(final Long id, Class obj) {
		if (getSession().isOpen()) {
			getSession().close();
		}

		T o;
		Session sess = getSession();
		Transaction tx;
		tx = sess.getTransaction();

		tx.begin();
		o = (T) sess.get(obj, id);
		tx.commit();
		return o;
	}

	/**
	 * Completely clear the session. Evict all loaded instances and cancel all pending saves, updates and deletions. Do
	 * not close open iterators or instances of ScrollableResults.
	 */
	@Override
	public void clearSession() {
		getSession().clear();
	}

	/**
	 * Save(Object) the given instance, depending upon resolution of the unsaved-value checks (see the manual for
	 * discussion of unsaved-value checking).
	 *
	 * @param entity
	 * 		A transient or detached instance containing new or updated state.
	 */
	@Override
	public void saveOrUpdate(final BaseTO entity) {
		if (getSession().isOpen()) {
			getSession().close();
		}
		Session sess = getSession();
		Transaction tx;
		tx = sess.getTransaction();
		tx.begin();
		sess.saveOrUpdate(entity);
		tx.commit();
	}

	/**
	 * Method remove removes a persistent instance from the datastore. The argument may be an instance associated with
	 * the receiving Session or a transient instance with an identifier associated with existing persistent state.
	 *
	 * @param entity
	 * 		Unique instance identifier to remove.
	 */
	@Override
	public void remove(final BaseTO entity) {
		if (getSession().isOpen()) {
			getSession().close();
		}
		Session sess = getSession();
		Transaction tx;
		tx = sess.getTransaction();
		tx.begin();
		sess.delete(entity);
		tx.commit();
	}

	/**
	 * Update(Object) the given instance, depending upon resolution of the unsaved-value checks (see the manual for
	 * discussion of unsaved-value checking).
	 *
	 * @param entity
	 * 		A transient or detached instance containing new or updated state.
	 */
	public void update(BaseTO entity) {
		if (getSession().isOpen()) {
			getSession().close();
		}
		Session sess = getSession();
		Transaction tx;
		tx = sess.getTransaction();
		tx.begin();
		sess.update(entity);
		tx.commit();
	}

	/**
	 * Method prepares Session and Transaction to provide secure access do DB.
	 *
	 * @return An instance of Session to be used on future DB access.
	 */
	protected Session prepareSession() {
		if (getSession().isOpen()) {
			getSession().close();
		}
		Session sess = getSession();
		Transaction tx;
		tx = sess.getTransaction();
		tx.begin();

		return sess;
	}
}
