package com.line.road.modular.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.line.road.framework.web.controller.BaseController;
import com.line.road.modular.persistence.primary.model.User;
import com.line.road.modular.persistence.secondary.model.Person;
import com.line.road.modular.system.service.IUserService;

@Controller
public class IndexController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

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
	public List<User> test(User user) {
		// setPageInfo(user);
		List<User> userInfoList = iUserService.selectUserAll(user);
		LOGGER.info("userInfoList[{}]", userInfoList == null ? 0 : userInfoList.size());
		Person person = new Person();
		person.setPageNum(user.getPageNum());
		person.setPageSize(user.getPageSize());
		List<Person> perList = iUserService.selectPersonAll(person);
		LOGGER.info("perList[{}]", perList == null ? 0 : perList.size());
		return userInfoList;
	}

	@RequestMapping("/test2")
	@ResponseBody
	public List<User> test2(User user) {
		List<User> userInfoList = iUserService.selectUserAll(user);
		return userInfoList;
	}

	@RequestMapping("/test1")
	@ResponseBody
	public PageInfo<Person> test1(Person person) {
		setPageInfo(person);
		List<Person> userInfoList = iUserService.selectPersonAll(person);
		return getPage(userInfoList);
	}

	@RequestMapping("/insert")
	@ResponseBody
	public String insert() {
		User user = new User();
		int u = iUserService.insertUser(user);
		return u + "   hello world!";
	}
}
