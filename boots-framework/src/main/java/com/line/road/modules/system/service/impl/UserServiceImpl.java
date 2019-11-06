package com.line.road.modules.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.line.road.external.test.dao.ISecUserDao;
import com.line.road.external.test.entity.Person;
import com.line.road.modules.system.dao.IUserDao;
import com.line.road.modules.system.entity.User;
import com.line.road.modules.system.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao iUserDao;

	@Autowired
	private ISecUserDao iSecUserDao;

	@Override
	public List<User> selectUserAll(User user) {
		return iUserDao.selectUserAll(user);
	}

	@Override
	public User selectUserById(Long userId) {
		return iUserDao.selectUserById(userId);
	}

	@Override
	public int insertUser(User user) {
		user.setEmail("yzz_ivy@163.com");
		user.setPhonenumber("15088888888");
		user.setLoginName("zhaoxiao");
		user.setUpdateTime(new SimpleDateFormat("yyyyy-MM-dd HH:dd:ss").format(new Date()));
		user.setCreateTime(new SimpleDateFormat("yyyyy-MM-dd HH:dd:ss").format(new Date()));
		int count = iUserDao.insertUser(user);
		// int a = 0 / 0;
		return count;
	}

	@Override
	public List<Person> selectPersonAll(Person user) {
		return iSecUserDao.selectUserAll(user);
	}
}
