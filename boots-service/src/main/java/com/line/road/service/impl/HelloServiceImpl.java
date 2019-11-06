package com.line.road.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.line.road.service.IHelloService;
import com.alibaba.dubbo.config.annotation.Service;

@Service(interfaceClass = IHelloService.class)
@Component
public class HelloServiceImpl implements IHelloService {

	private static final Logger LOG = LoggerFactory.getLogger(HelloServiceImpl.class);

	@Override
	public String test() {
		LOG.info("测试dubbo----------成功------。");
		return "测试dubbo";
	}

}
