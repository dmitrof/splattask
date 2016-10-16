package com.tmitri.splattask.dao;

import java.util.List;
import com.tmitri.splattask.models.Account;
import com.tmitri.splattask.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 * Created by Дмитрий on 15.10.2016.
 */
//реализация DAO для Account
class AccountDaoImpl implements AccountDao {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();




	public void addAccount(Account account)  {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(account);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		
	}

	public void updateAccount(Account account)  {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(account);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
                session.close();
            }
		}
		
	}

	public Account getAccountById(Integer id)  {
		Session session = null;
		Account account = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			//используем get вместо Load, иначе будет загружен proxy и поймаем lazyloadingexception или типа того
			account = (Account) session.get(Account.class, id);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
                session.close();
            }
		}

		return account;
	}

	public List<Account> getAllAccounts()  {
		Session session = null;
		List<Account> accounts = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			accounts = session.createCriteria(Account.class).list();
			session.getTransaction().commit();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
                session.close();
            }
		}
		return accounts;
	}

	public void deleteAccount(Account account)  {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(account);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
                session.close();
            }
		}
		
	}

}
