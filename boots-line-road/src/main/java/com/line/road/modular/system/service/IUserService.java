package com.line.road.modular.system.service;

import java.util.List;

import com.line.road.modular.persistence.primary.model.User;
import com.line.road.modular.persistence.secondary.model.Person;


public interface IUserService {

	public List<User> selectUserAll(User user);
	
	public User selectUserById(Long userId);
	
	public int insertUser(User user);
	
	public List<Person> selectPersonAll(Person user);
}
