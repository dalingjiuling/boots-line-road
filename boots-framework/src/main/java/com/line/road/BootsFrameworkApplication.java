package com.line.road;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class BootsFrameworkApplication {
	private static final Logger LOG = LoggerFactory.getLogger(BootsFrameworkApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BootsFrameworkApplication.class, args);
		LOG.info("************ 启动成功  ************");
	}
}
