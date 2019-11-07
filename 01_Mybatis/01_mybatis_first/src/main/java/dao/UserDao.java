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
	 * 查询所有操作
	 */
	List<User> findAll();
}
