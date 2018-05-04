package com.line.road.framework.druid;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * Druid数据源生产者
 * 
 * @author zhaoliangliang
 *
 */
public class DruidDataSourceBuilder {

	public static DruidDataSourceBuilder create() {
		return new DruidDataSourceBuilder();
	}

	public DruidDataSource build() {
		return new DruidDataSourceWrapper();
	}
}
