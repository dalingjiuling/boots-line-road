package com.line.road.modular.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.line.road.modular.persistence.dao.IUserDao;
import com.line.road.modular.persistence.model.User;

@Controller
public class IndexController {

	@Autowired
	private IUserDao iUserDao;
	
	
	@RequestMapping("/")
	@ResponseBody
	public String index() {
		User u = iUserDao.selectUserById(1l);
		System.out.println(u.toString());
		return "hello world!";
	}
}
