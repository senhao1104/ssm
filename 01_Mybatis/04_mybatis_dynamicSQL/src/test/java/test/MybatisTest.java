package test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.UserDao;
import domain.QueryVo;
import domain.User;

public class MybatisTest {

	private InputStream in;
	private SqlSession sqlSession;
	private UserDao userDao;

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
		userDao = sqlSession.getMapper(UserDao.class);
	}

	@After // 用于在测试方法执行后执行
	public void destory() throws Exception {
		// 6.释放资源
		sqlSession.close();
		in.close();
	}

	/**
	 * 测试查询所有用户
	 */
	@Test
	public void testFindAll() {
		// 5.执行查询所有方法
		List<User> users = userDao.findAll();
		for (User user : users) {
			System.out.println(user);
		}
	}

	/**
	 * 测试查询用户
	 */
	@Test
	public void testFindById() {
		// 执行查询一个方法
		User user = userDao.findById(41);
		System.out.println(user);
	}
	
	/**
	 * 测试模糊查询用户
	 */
	@Test
	public void testFindByName() {
		// 执行模糊查询
		List<User> users = userDao.findByName("%a%");
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	/**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%a%");
        vo.setUser(user);
        //5.执行查询一个方法
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }
    
    /**
	 * 测试根据条件查询用户
	 */
	@Test
	public void testfindUserByCondition() {
		User user = new User();
		user.setUsername("aaa");
		user.setSex("男");
		// 5.执行查询方法
		List<User> users = userDao.findUserByCondition(user);
		for (User u : users) {
			System.out.println(u);
		}
	}
	
	/**
	 * 测试根据queryvo中的id集合 查询用户
	 */
	@Test
	public void testfindUserInIds() {
		QueryVo vo = new QueryVo();
		List<Integer> list = new ArrayList<Integer>();
		list.add(41);
		list.add(42);
		list.add(43);
		vo.setIds(list);
		// 5.执行查询方法
		List<User> users = userDao.findUserInIds(vo);
		for (User u : users) {
			System.out.println(u);
		}
	}
}
