package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.Account;
import service.AccountService;

public class AccountServiceTest {
	
	@Test
	public void testFindAll() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
		AccountService as = ac.getBean("accountService",AccountService.class);
		List<Account> accounts = as.findAllAccount();
		for(Account account : accounts) {
			System.out.println(account);
		}
	}
	
	@Test
	public void testFindOne() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
		AccountService as = ac.getBean("accountService",AccountService.class);
		Account account = as.findAccountById(1);
		System.out.println(account);
		
	}
	
	@Test
	public void testSave() {
		Account account = new Account();
		account.setName("test");
		account.setMoney(3000.0f);
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
		AccountService as = ac.getBean("accountService",AccountService.class);
		as.saveAccount(account);
	}
	
	@Test
	public void testUpdate() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
		AccountService as = ac.getBean("accountService",AccountService.class);
		Account account = as.findAccountById(4);
		account.setMoney(1234.0f);
		as.updateAccount(account);
		
	}
	
	@Test
	public void testDelete() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
		AccountService as = ac.getBean("accountService",AccountService.class);
		as.deleteAccount(4);
	}
}
