package com.line.road.modules.generator.dao;

import java.util.List;

import com.line.road.framework.persistence.CrudDao;
import com.line.road.framework.persistence.annotion.MyBatisDao;
import com.line.road.modules.generator.entity.TableColumn;
import com.line.road.modules.generator.entity.TableSchema;

@MyBatisDao
/**
 * 数据库表信息
 * 
 * @author Administrator
 *
 */
public interface TableSchemaDao extends CrudDao<TableSchema> {
	/**
	 * 获取所有的表
	 * 
	 * @param paramTable
	 * @return
	 */
	List<TableSchema> findTableList(TableSchema paramTable);

	/**
	 * 获取对应表的所有的字段
	 * 
	 * @param paramTable
	 * @return
	 */
	List<TableColumn> findTableColumnList(TableSchema paramTable);

	/**
	 * 获取对应表的主键
	 * 
	 * @param paramTable
	 * @return
	 */
	List<TableColumn> findTablePK(TableSchema paramTable);
}
