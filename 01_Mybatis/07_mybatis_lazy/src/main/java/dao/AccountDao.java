package dao;

import java.util.List;

import domain.Account;

public interface AccountDao {
	
	/**
	 * 查询所有账户 同时包含用户名和地址
	 */
	List<Account> findAll();
	
	/**
	 * 根据用户id查询账户信息
	 */
	List<Account> findAccountByUid(Integer uid);
}
