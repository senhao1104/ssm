package dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

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
	@Select("select * from user")
	List<User> findAll();
}
