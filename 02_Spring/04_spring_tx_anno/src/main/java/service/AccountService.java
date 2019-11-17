package service;

import java.util.List;

import domain.Account;

/**
 * 账户的业务层接口
 */
public interface AccountService {
	
	/**
	 * 根据id查询账户信息
	 */
	Account findAccountById(Integer accountId);
	
	/**
	 * 转账
	 */
	void transfer(String sourceName,String targetName,Float money);
	
}
