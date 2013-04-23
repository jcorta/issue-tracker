package edu.unlp.db.loader;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;

import edu.unlp.db.domain.Tracker;

public class TrackerLoader implements InitializingBean {
	
	//Guarda la instancia para que la puedan recuperar los converter de dozer que no usan spring
	private static TrackerLoader instance; 
	
	private SessionFactory sessionFactory;

	public static TrackerLoader getInstance(){
		return instance;
	}
	public Tracker getTracker() {
		return (Tracker) sessionFactory.getCurrentSession().get(Tracker.class,
				1l);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void refreshOids(){
		sessionFactory.getCurrentSession().flush();
	}

	public void afterPropertiesSet() throws Exception {
		instance = this;
	}

}