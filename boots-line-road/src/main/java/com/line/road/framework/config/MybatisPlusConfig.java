package com.line.road.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis配置
 * 
 * @author zhaoliangliang
 * @since 2018-04-24 14:07
 */
@Configuration
// 无配置文件注解版
@MapperScan(basePackages = { "com.line.road.modular.persistence.dao" })
public class MybatisPlusConfig {

	// @Primary
	// @Bean
	// @ConfigurationProperties("spring.datasource.druid.one")
	// public DataSource dataSourceOne(){
	// return DruidDataSourceBuilder.create().build();
	// }
	// @Bean
	// @ConfigurationProperties("spring.datasource.druid.two")
	// public DataSource dataSourceTwo(){
	// return DruidDataSourceBuilder.create().build();
	// }
}
