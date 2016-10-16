package com.tmitri.splattask.webservice;

import com.tmitri.splattask.dao.AccountDao;
import com.tmitri.splattask.dao.DaoFactory;
import com.tmitri.splattask.models.Account;
import javax.jws.WebService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * Created by Дмитрий on 15.10.2016.
 */
//Реализация сервиса
@WebService(endpointInterface = "com.tmitri.splattask.webservice.AccountService")
public class AccountServiceImpl implements AccountService {
    private ConcurrentMap<Integer, Object> locks = new ConcurrentHashMap<Integer, Object>();
    private AccountDao accountDao = DaoFactory.getInstance().getAccountDao();

    public Long getAmount(Integer id)
    {
        Object lock = locks.get(id);
        if(lock == null) {
            Object lockObj = locks.putIfAbsent(id, lock = new Object());
            if (lockObj != null) {
                lock = lockObj;
            }
        }
        synchronized (lock) {
            return accountDao.getAccountById(id).getValue();
        }
    }

    public void addAmount(Integer id, Long value) {
        Object lock = locks.get(id);
        if(lock == null) {
            Object lockObj = locks.putIfAbsent(id, lock = new Object());
            if (lockObj != null) {
                lock = lockObj;
            }
        }
        synchronized (lock) {
            Account account = accountDao.getAccountById(id);
            account.setValue(account.getValue() + value);
            accountDao.updateAccount(account);
        }

    }
}
