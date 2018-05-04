package com.line.road.framework.druid;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.line.road.framework.druid.properties.DruidStatProperties;
import com.line.road.framework.druid.stat.DruidFilterConfiguration;
import com.line.road.framework.druid.stat.DruidSpringAopConfiguration;
import com.line.road.framework.druid.stat.DruidStatViewServletConfiguration;
import com.line.road.framework.druid.stat.DruidWebStatFilterConfiguration;

/**
 * @Import注解支持导入普通的java类,并将其声明成一个bean
 * @ConditionalOnClass（某个class位于类路径上，才会实例化一个Bean）
 * @AutoConfigureBefore： 在指定的配置类初始化前加载
 * @EnableConfigurationProperties注解是用来开启对@ConfigurationProperties注解配置Bean的支持。 也就是@EnableConfigurationProperties注解告诉Spring
 *                                                                            Boot
 *                                                                            使能支持@ConfigurationProperties
 * @author zhaoliangliang
 *
 */
@Configuration
@ConditionalOnClass(DruidDataSource.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties({ DruidStatProperties.class, DataSourceProperties.class })
@Import({ DruidSpringAopConfiguration.class, DruidStatViewServletConfiguration.class,
		DruidWebStatFilterConfiguration.class, DruidFilterConfiguration.class })

@EnableTransactionManagement
@MapperScan(basePackages = { "com.line.road.modular.persistence.dao" })
public class DruidDataSourceAutoConfigure {

	private static final Logger LOGGER = LoggerFactory.getLogger(DruidDataSourceAutoConfigure.class);

	@Bean(initMethod = "init")
	@ConditionalOnMissingBean
	public DataSource dataSource() {
		LOGGER.info("Init DruidDataSource");
		return new DruidDataSourceWrapper();
	}

	// 多数据源处理
	// @ConfigurationProperties("spring.datasource.druid.one")
	public DataSource singleDatasource() {
		return DruidDataSourceBuilder.create().build();
	}
}
