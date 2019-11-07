package dao;

import java.util.List;

import domain.QueryVo;
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
	 * 根据id查询用户信息
	 */
	User findById(Integer id);
	
	/**
	 * 根据名称模糊查询用户信息
	 */
	List<User> findByName(String username);
	
	/**
	 * 根据queryVo中的条件查询用户
	 */
	List<User> findUserByVo(QueryVo vo);
	
	/**
	 * 根据传入参数条件查询用户
	 */
	List<User> findUserByCondition(User user);
	/**
	 * 根据queryvo中的id集合，查询用户信息
	 */
	List<User> findUserInIds(QueryVo vo);
}