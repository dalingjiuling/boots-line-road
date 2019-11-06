package com.line.road.modules.system.service;

import java.util.List;

import com.line.road.external.test.entity.Person;
import com.line.road.modules.system.entity.User;

public interface IUserService {

	public List<User> selectUserAll(User user);

	public User selectUserById(Long userId);

	public int insertUser(User user);

	public List<Person> selectPersonAll(Person user);
}
