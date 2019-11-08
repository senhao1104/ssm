package dao;

import java.util.List;

import domain.Account;

public interface AccountDao {
	
	/**
	 * 查询所有账户 同时包含用户名和地址
	 */
	List<Account> findAll();
}
