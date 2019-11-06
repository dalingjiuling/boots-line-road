package com.line.road.framework.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.line.road.modular.persistence.dao.MyBatisDao;

@Configuration
@MapperScan(value = "com.line.road.modular.persistence.secondary.dao", sqlSessionTemplateRef = "secSqlSessionTemplate", annotationClass = MyBatisDao.class)
@EnableTransactionManagement
public class SecondaryDataSourceConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecondaryDataSourceConfig.class);

	@Value("${mybatis.two.type-aliases-package}")
	private String twoTypeAliasesPackage;

	@Value("${mybatis.two.mapper-locations}")
	private String twoMapperLocations;

	@Bean(name = "secDataSource")
	@ConfigurationProperties("spring.datasource.druid.two")
	public DataSource dataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean(name = "secSqlSessionFactory")
	public SqlSessionFactory sentinelSqlSessionFactory(@Qualifier("secDataSource") DataSource dataSource)
			throws Exception {
		LOGGER.info("****secSqlSessionFactory Init****");
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(twoMapperLocations));
		bean.setTypeAliasesPackage(twoTypeAliasesPackage);
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	@Bean(name = "secSqlSessionTemplate")
	public SqlSessionTemplate sentinelSqlSessionTemplate(
			@Qualifier("secSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean(name = "secTxManager")
	public PlatformTransactionManager txManager(@Qualifier("secDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
