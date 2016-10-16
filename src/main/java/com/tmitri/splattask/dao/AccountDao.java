package com.tmitri.splattask.dao;

import java.sql.SQLException;
import java.util.List;

import com.tmitri.splattask.models.Account;

//Data Access Object для Account

public interface AccountDao {
	public void addAccount(Account account);
	public void updateAccount(Account account);
	public Account getAccountById(Integer id);
	public List getAllAccounts();
	public void deleteAccount(Account account);


}
