package com.nagarro.pos.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Creating hibernate utils
 * 
 * @author nishantgarg
 *
 */
public class HibernateUtils {
	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			
			
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");

			System.out.println("Creating session factory");
			return configuration.buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSession() {
		return sessionFactory;
	}

}
