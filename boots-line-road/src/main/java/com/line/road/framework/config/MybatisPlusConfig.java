package com.line.road.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.line.road.framework.config.properties.DruidConfig;

@Configuration
@EnableTransactionManagement
// 无配置文件注解版
@MapperScan(basePackages = { "com.line.road.modular.persistence.dao" })
public class MybatisPlusConfig {

	@Autowired
	DruidConfig druidConfig;

	/**
	 * 默认数据源
	 * 
	 * @return
	 */
	private DruidDataSource dataSourceDefault() {
		DruidDataSource dataSource = new DruidDataSource();
		druidConfig.config(dataSource);
		return dataSource;
	}

	/**
	 * 单数据源连接池配置
	 */
	@Bean
	public DruidDataSource singleDatasource() {
		return dataSourceDefault();
	}
}
