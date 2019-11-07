package dao;

import java.util.List;

import domain.User;

/**
 * 
 * @author senhao
 * 用户持久层接口
 */
public interface UserDao {

	/**
	 * 查询所有用户
	 */
	List<User> findAll();
	
	/**
	 * 保存用户
	 */
	void saveUser(User user);
	
	/**
	 * 更新用户
	 */
	void updateUser(User user);
	
	/**
	 * 根据id删除用户
	 */
	void deleteUser(Integer id);
	
	/**
	 * 根据id查询用户信息
	 */
	User findById(Integer id);
	
	/**
	 * 根据名称模糊查询用户信息
	 */
	List<User> findByName(String username);
	
	/**
	 * 查询总用户数
	 */
	int findTotal();
}