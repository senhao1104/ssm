package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AccountDao;
import domain.Account;
import service.AccountService;

/**
 * 账户的业务层实现类
 */
@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	public Account findAccountById(Integer accountId) {
		return accountDao.findAccountById(accountId);
	}

	public void transfer(String sourceName, String targetName, Float money) {
		System.out.println("transfer....");
		// 2.1根据名称查询转出账户
		Account source = accountDao.findAccountByName(sourceName);
		// 2.2根据名称查询转入账户
		Account target = accountDao.findAccountByName(targetName);
		// 2.3转出账户减钱
		source.setMoney(source.getMoney() - money);
		// 2.4转入账户加钱
		target.setMoney(target.getMoney() + money);
		// 2.5更新转出账户
		accountDao.updateAccount(source);

		int i = 1 / 0;

		// 2.6更新转入账户
		accountDao.updateAccount(target);

	}

}
