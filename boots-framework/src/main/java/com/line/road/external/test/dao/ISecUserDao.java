package com.line.road.external.test.dao;

import java.util.List;

import com.line.road.external.test.entity.Person;
import com.line.road.framework.persistence.annotion.MyBatisDao;
import com.line.road.modules.system.entity.User;

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
