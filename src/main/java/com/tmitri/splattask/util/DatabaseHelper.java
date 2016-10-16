package com.tmitri.splattask.util;

import com.tmitri.splattask.dao.DaoFactory;
import com.tmitri.splattask.models.Account;
import com.tmitri.splattask.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


import java.util.List;
import java.util.Random;

/**
 * Created by Дмитрий on 15.10.2016.
 */
//класс для дополнительных действий над БД

public class DatabaseHelper {
    private static SessionFactory sessionFactory = null;


    //создадим n новых записей c разными value
    public void fillDatabase(Integer n) {

        hqlTruncate("Account");
        for (int i = 0; i < n; ++i) {
            Account account = new Account();
            Long x = (i + 1L) *10L;
            account.setValue(x);
            DaoFactory.getInstance().getAccountDao().addAccount(account);
        }
        List<Account> accounts = DaoFactory.getInstance().getAccountDao().getAllAccounts();

        for (int i = 0; i < accounts.size(); ++i) {
            //System.out.println(accounts.get(i).getId() + " " + accounts.get(i).getValue());
        }
    }

    public void hqlTruncate(String myTable){
        String hql = String.format("delete from %s",myTable);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();
        //session.close();
    }



}
