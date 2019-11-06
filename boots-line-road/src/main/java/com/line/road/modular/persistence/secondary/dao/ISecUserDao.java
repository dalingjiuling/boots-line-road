package com.line.road.modular.persistence.secondary.dao;

import java.util.List;

import com.line.road.modular.persistence.dao.MyBatisDao;
import com.line.road.modular.persistence.model.Person;
import com.line.road.modular.persistence.model.User;

@MyBatisDao
public interface ISecUserDao {

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
	public List<Person> selectUserAll(Person user);

	public int insertUser(Person user);

}
