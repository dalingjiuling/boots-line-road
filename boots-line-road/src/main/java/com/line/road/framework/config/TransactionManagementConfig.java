package com.line.road.framework.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * 声明式事物配置</br>
 * 单个数据源配置
 * 
 * @author zhaolianglinag
 * @since 2018-04-26 10:26
 */
// @Configuration
// @EnableTransactionManagement
public class TransactionManagementConfig implements TransactionManagementConfigurer {

	@Autowired
	private DataSource dataSource;

	@Bean(name = "txManager")
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource);
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return txManager();
	}
}
