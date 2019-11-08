package dao;

import java.util.List;

import domain.Role;

public interface RoleDao {

	/**
	 * 查询所有角色
	 */
	List<Role> findAll();
}
