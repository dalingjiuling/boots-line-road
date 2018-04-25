package com.line.road.framework.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的Mapper
 *
 * @author zhaoliangliang
 * @since 2018-04-25 17:04
 *        <p>
 *        特别注意，该接口不能被扫描到，否则会出错
 *        </p>
 */
public interface CustomizeMySqlMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
