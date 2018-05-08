package com.line.road.modular.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.line.road.framework.web.controller.BaseController;
import com.line.road.modular.persistence.model.User;
import com.line.road.modular.system.service.IUserService;

@Controller
public class IndexController extends BaseController {

	@Autowired
	private IUserService iUserService;

	@RequestMapping("/")
	@ResponseBody
	public String index() {
		User u = iUserService.selectUserById(1l);
		System.out.println(u.toString());
		return "hello world!";
	}

	@RequestMapping("/test")
	@ResponseBody
	public PageInfo<User> test(User user) {
		setPageInfo(user);
		List<User> userInfoList = iUserService.selectUserAll(user);
		return getPage(userInfoList);
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert() {
		User user =new User();
		int u = iUserService.insertUser(user);
		return u+"   hello world!";
	}
}
