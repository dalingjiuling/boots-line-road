package com.line.road.modules.generator.entity;

import org.apache.commons.lang3.StringUtils;

import com.line.road.framework.persistence.entity.BaseEntity;

/**
 * 表字段信息
 * 
 * @author Administrator
 *
 */
public class TableColumn extends BaseEntity<TableColumn> {

	private static final long serialVersionUID = 1L;

	private TableSchema genTable;
	/** 字段名 */
	private String columnName;
	/** 字段类型 */
	private String columnType;
	/** 备注 */
	private String comments;
	/** 是否允许为空 */
	private String isNull;
	/** 顺序 */
	private Integer sorts;
	/** 是否主键 */
	private String isPk;
	/** 实体类字段 */
	private String javaField;
	/** 实体类字段属性 */
	private String javaType;
	/** 是否允许插入 */
	private String isInsert;
	/** 是否允许更新 */
	private String isUpdate;
	/** 是否是查询条件 */
	private String isQuery;
	/** 查询条件 */
	private String queryType;
	/** 是否允许在类表中显示 */
	private String isList;
	/** 是否允许编辑 */
	private String isEdit;
	/** 显示类型 */
	private String showType;
	/** 字典类型 */
	private String dictType;
	/** 操作 */
	private String options;

	public TableColumn() {
		this.isPk = "0";
		this.isNull = "0";
		this.isInsert = "0";
		this.isUpdate = "0";
		this.isList = "0";
		this.isQuery = "0";
		this.isEdit = "0";
	}

	public TableColumn(TableSchema genTable) {
		this.genTable = genTable;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	public Integer getSorts() {
		return sorts;
	}

	public void setSorts(Integer sorts) {
		this.sorts = sorts;
	}

	public String getIsPk() {
		return isPk;
	}

	public void setIsPk(String isPk) {
		this.isPk = isPk;
	}

	public String getJavaField() {
		return javaField;
	}

	public void setJavaField(String javaField) {
		this.javaField = javaField;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getIsInsert() {
		return isInsert;
	}

	public void setIsInsert(String isInsert) {
		this.isInsert = isInsert;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	public String getIsList() {
		return isList;
	}

	public void setIsList(String isList) {
		this.isList = isList;
	}

	public TableSchema getGenTable() {
		return genTable;
	}

	public void setGenTable(TableSchema genTable) {
		this.genTable = genTable;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	public String getSimpleJavaField() {
		return StringUtils.substringBefore(getJavaField(), ".");
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
}
