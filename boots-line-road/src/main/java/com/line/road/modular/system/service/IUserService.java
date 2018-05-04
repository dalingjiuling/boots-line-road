package com.line.road.modular.system.service;

import java.util.List;

import com.line.road.modular.persistence.model.User;

public interface IUserService {

	public List<User> selectUserAll(User user);
	
	public User selectUserById(Long userId);
}
