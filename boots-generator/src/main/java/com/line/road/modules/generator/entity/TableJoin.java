package com.line.road.modules.generator.entity;

import com.line.road.framework.persistence.entity.BaseEntity;

/**
 * 连接表信息
 * 
 * @author Administrator
 *
 */
public class TableJoin extends BaseEntity<TableJoin> {
	private static final long serialVersionUID = 1L;
	private String tableName;
	private String tablePk;
	private String joinType;
	private String joinTableName;
	private String joinTablePk;
	private String returnField;

	public TableJoin() {
	}

	public TableJoin(TableSchema genTable) {
		this.tableName = genTable.getTableName();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTablePk() {
		return tablePk;
	}

	public void setTablePk(String tablePk) {
		this.tablePk = tablePk;
	}

	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	public String getJoinTableName() {
		return joinTableName;
	}

	public void setJoinTableName(String joinTableName) {
		this.joinTableName = joinTableName;
	}

	public String getJoinTablePk() {
		return joinTablePk;
	}

	public void setJoinTablePk(String joinTablePk) {
		this.joinTablePk = joinTablePk;
	}

	public String getReturnField() {
		return returnField;
	}

	public void setReturnField(String returnField) {
		this.returnField = returnField;
	}
}
