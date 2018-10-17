package com.line.road.modular.system.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.line.road.modular.persistence.model.Result;
import com.line.road.modular.persistence.model.ResultFactory;
import com.line.road.modular.persistence.model.VueLoginInfoVo;
import com.line.road.service.IHelloService;

@Controller
public class LoginController {

	@Reference(check = false)
	private IHelloService iHelloService;

	// @GetMapping("/login")
	// public String login() {
	// return "login";
	// }
	//
	// @RequestMapping("/dubbo")
	// @ResponseBody
	// public String dubbo() {
	// return iHelloService.test();
	// }

	/**
	 * 登录控制器，前后端分离用的不同协议和端口，所以需要加入@CrossOrigin支持跨域。
	 * 给VueLoginInfoVo对象加入@Valid注解，并在参数中加入BindingResult来获取错误信息。
	 * 在逻辑处理中我们判断BindingResult知否含有错误信息，如果有错误信息，则直接返回错误信息。
	 */
	@CrossOrigin
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result login(@Valid @RequestBody VueLoginInfoVo loginInfoVo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String message = String.format("登陆失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
			return ResultFactory.buildFailResult(message);
		}
		if (!Objects.equals("admin", loginInfoVo.getUsername())
				|| !Objects.equals("123456", loginInfoVo.getPassword())) {
			String message = String.format("登陆失败，详细信息[用户名、密码信息不正确]。");
			return ResultFactory.buildFailResult(message);
		}
		return ResultFactory.buildSuccessResult("登录成功");
	}
}
