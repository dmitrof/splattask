package com.tmitri.splattask.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

//хелпер для работы с ORM
public class HibernateUtil {
	 private static SessionFactory sessionFactory = null;
	 
	 static {
		 try{
			 Configuration configuration = new Configuration();
			 configuration.configure();
			 ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
			            configuration.getProperties()).build();
			 sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			 //sessionFactory.openSession();
			 //System.out.println("session is opened");
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

}
