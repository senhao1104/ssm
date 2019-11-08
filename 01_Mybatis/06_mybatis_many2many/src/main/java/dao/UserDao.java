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
	 * 查询所有用户，同时获取到用户下所有账户的信息
	 */
	List<User> findAll();
	
	/**
	 * 根据id查询用户信息
	 */
	User findById(Integer id);

}