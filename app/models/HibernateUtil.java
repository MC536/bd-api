package models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by joaochencci on 26/06/14.
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * Method buildSessionFactory create a SessionFactory using the properties and mappings in this configuration. The
	 * SessionFactory will be immutable, so changes made to this Configuration after building the SessionFactory will
	 * not affect it.
	 *
	 * @return The build SessionFactory.
	 *
	 * @throws org.hibernate.HibernateException
	 * 		- usually indicates an invalid configuration or invalid mapping information
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
					configuration.getProperties()).build();


			return configuration.buildSessionFactory(serviceRegistry);
		}
		catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Method getSessionFactory returns the static SessionFactory variable of this class.
	 *
	 * @return Static SessionFactory atribute to generate Session
	 */
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Method getSession returns the current Session available on the static SessionFactory.
	 *
	 * @return Current Session available.
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}




