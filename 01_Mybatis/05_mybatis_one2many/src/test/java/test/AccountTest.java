package test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.AccountDao;
import domain.Account;

public class AccountTest {

	private InputStream in;
	private SqlSession sqlSession;
	private AccountDao accountDao;

	@Before // 用于在测试方法执行前执行
	public void init() throws Exception {
		// 1.读取配置文件
		in = Resources.getResourceAsStream("SqlMapConfig.xml");
		// 2.创建SqlSessionFactory工厂
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(in);
		// 3.使用工厂生产SqlSession对象
		sqlSession = factory.openSession();
		// 4.使用SqlSession创建Dao接口的代理对象
		accountDao = sqlSession.getMapper(AccountDao.class);
	}

	@After // 用于在测试方法执行后执行
	public void destory() throws Exception {
		// 6.释放资源
		sqlSession.close();
		in.close();
	}
	
	/**
	 * 测试查询所有账户,同时包含用户名和地址
	 */
	@Test
	public void testFindAll() {
		// 5.执行查询所有方法
		List<Account> accounts = accountDao.findAll();
		for (Account account : accounts) {
			System.out.println(account);
			System.out.println(account.getUser());
		}
	}
	
}
