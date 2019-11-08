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

import dao.RoleDao;
import dao.UserDao;
import domain.Role;
import domain.User;

public class RoleTest {

	private InputStream in;
	private SqlSession sqlSession;
	private RoleDao roleDao;

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
		roleDao = sqlSession.getMapper(RoleDao.class);
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
		List<Role> roles = roleDao.findAll();
		for (Role role : roles) {
			System.out.println(role);
			System.out.println(role.getUsers());
		}
	}
	
}
