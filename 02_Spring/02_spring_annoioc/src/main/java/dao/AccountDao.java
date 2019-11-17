package dao;

import java.util.List;

import domain.Account;

/**
 * 账户的持久层接口
 */
public interface AccountDao {
	
	/**
	 * 查询所有
	 */
	List<Account> findAllAccount();
	
	/**
	 * 查询一个
	 */
	Account findAccountById(Integer accountId);
	
	/**
	 * 保存
	 */
	void saveAccount(Account account);
	
	/**
	 * 更新
	 */
	void updateAccount(Account account);
	
	/**
	 * 删除
	 */
	void deleteAccount(Integer accountId);
}
