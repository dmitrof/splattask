package com.tmitri.splattask.dao;


//Простая фабрика для компонентов ORM-архитектуры
public class DaoFactory {
	private static AccountDao accountDao;
	private static DaoFactory instance = null;
	
	
	
	public static synchronized DaoFactory getInstance() {
		if (instance == null) {
			instance = new DaoFactory();
		}
		return instance;
	}
	
	public AccountDao getAccountDao() {
		if (accountDao == null){
            accountDao = new AccountDaoImpl();
          }
          return accountDao;
	}
}
