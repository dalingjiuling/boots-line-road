package com.line.road.framework.persistence.config;

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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.line.road.framework.persistence.annotion.MyBatisDao;
import com.line.road.framework.persistence.entity.BaseEntity;

@Configuration
@MapperScan(basePackages = "com.line.road.modules", sqlSessionTemplateRef = "primSqlSessionTemplate", annotationClass = MyBatisDao.class)
@EnableTransactionManagement
public class PrimaryDataSourceConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrimaryDataSourceConfig.class);

	@Value("${mybatis.one.type-aliases-package}")
	private String typeAliasesPackage;

	@Value("${mybatis.one.mapper-locations}")
	private String mapperLocations;

	@Bean(name = "primDataSource")
	@ConfigurationProperties("spring.datasource.druid.one")
	@Primary
	public DataSource primDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean(name = "primSqlSessionFactory")
	@Primary
	public SqlSessionFactory sentinelSqlSessionFactory(@Qualifier("primDataSource") DataSource dataSource)
			throws Exception {
		LOGGER.info("****primSqlSessionFactory Init****");
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		bean.setTypeAliasesPackage(typeAliasesPackage);
		bean.setTypeAliasesSuperType(BaseEntity.class);
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	@Bean(name = "primSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate sentinelSqlSessionTemplate(
			@Qualifier("primSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean(name = "primTxManager")
	@Primary
	public PlatformTransactionManager txManager(@Qualifier("primDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
