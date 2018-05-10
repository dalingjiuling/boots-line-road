package com.line.road.modular.persistence.primary.dao;

import java.util.List;

import com.line.road.modular.persistence.primary.model.User;



public interface IUserDao {

	/**
	 * 通过用户ID查询用户
	 * 
	 * @param userId
	 *            用户ID
	 * @return 用户对象信息
	 */
	public User selectUserById(Long userId);

	/**
	 * 获取全部用户
	 * 
	 * @param user
	 * @return
	 */
	public List<User> selectUserAll(User user);

	public int insertUser(User user);

}
