package com.line.road;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

@SpringBootApplication
// 开启dubbo功能
@EnableDubboConfiguration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class BootsServiceApplication {
	private static final Logger LOG = LoggerFactory.getLogger(BootsServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BootsServiceApplication.class, args);
		LOG.info("spring dubbo service start end.............................");
	}
}
