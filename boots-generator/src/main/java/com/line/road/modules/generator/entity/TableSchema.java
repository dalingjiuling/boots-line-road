package com.line.road.modules.generator.entity;

import java.util.ArrayList;
import java.util.List;

import com.line.road.framework.persistence.entity.BaseEntity;

/**
 * 表信息
 * 
 * @author Administrator
 *
 */
public class TableSchema extends BaseEntity<TableSchema> {

	private static final long serialVersionUID = 1L;
	/** 表明 */
	private String tableName;
	/** 表备注 */
	private String tableComment;
	/** 表类型 */
	private String tableType;
	/** 模板类型 */
	private String tplCategory;
	/** 父表名称 */
	private String parentTableName;
	/** 父表对象 */
	private TableSchema parent;
	/** 所在包名 */
	private String packageName;
	/** 所在模块名 */
	private String moduleName;
	/** 所在子模块名 */
	private String subModuleName;
	/** 所在类的函数名 */
	private String functionName;
	/** 函数名简称 */
	private String functionNameSimple;
	/** 函数创建者 */
	private String functionAuthor;
	/** 文件生成的目录 */
	private String GenBaseDir;
	/** 类名 */
	private String className;
	/***/
	private Boolean parentExists;
	/***/
	private String options;
	/***/
	private Boolean replaceFile;
	/***/
	private String flag;
	/** 主键集合 */
	private List<TableColumn> pkList;
	/** 表字段 */
	private List<TableColumn> columnList = new ArrayList<TableColumn>();
	/** 子表 */
	private List<TableSchema> childList = new ArrayList<TableSchema>();
	/** 连接表 */
	private List<TableJoin> tableJoinList = new ArrayList<TableJoin>();

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public List<TableColumn> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<TableColumn> columnList) {
		this.columnList = columnList;
	}

	public String getTplCategory() {
		return tplCategory;
	}

	public void setTplCategory(String tplCategory) {
		this.tplCategory = tplCategory;
	}

	public String getParentTableName() {
		return parentTableName;
	}

	public void setParentTableName(String parentTableName) {
		this.parentTableName = parentTableName;
	}

	public List<TableSchema> getChildList() {
		return childList;
	}

	public void setChildList(List<TableSchema> childList) {
		this.childList = childList;
	}

	public TableSchema getParent() {
		return parent;
	}

	public void setParent(TableSchema parent) {
		this.parent = parent;
	}

	public List<TableJoin> getTableJoinList() {
		return tableJoinList;
	}

	public void setTableJoinList(List<TableJoin> tableJoinList) {
		this.tableJoinList = tableJoinList;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSubModuleName() {
		return subModuleName;
	}

	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionNameSimple() {
		return functionNameSimple;
	}

	public void setFunctionNameSimple(String functionNameSimple) {
		this.functionNameSimple = functionNameSimple;
	}

	public String getFunctionAuthor() {
		return functionAuthor;
	}

	public void setFunctionAuthor(String functionAuthor) {
		this.functionAuthor = functionAuthor;
	}

	public String getGenBaseDir() {
		return GenBaseDir;
	}

	public void setGenBaseDir(String genBaseDir) {
		GenBaseDir = genBaseDir;
	}

	public Boolean getParentExists() {
		return parentExists;
	}

	public void setParentExists(Boolean parentExists) {
		this.parentExists = parentExists;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Boolean getReplaceFile() {
		return replaceFile;
	}

	public void setReplaceFile(Boolean replaceFile) {
		this.replaceFile = replaceFile;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<TableColumn> getPkList() {
		return pkList;
	}

	public void setPkList(List<TableColumn> pkList) {
		this.pkList = pkList;
	}
}
