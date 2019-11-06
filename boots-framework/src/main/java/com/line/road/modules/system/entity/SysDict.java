package com.line.road.modules.system.entity;

import com.line.road.framework.persistence.entity.BaseEntity;

public class SysDict extends BaseEntity<SysDict> {
	private static final long serialVersionUID = 1L;

	/** 字典编码 */
	private String dictCode;
	/** 字典名 */
	private String name;
	/** 字典值 */
	private String value;
	/** 字典类型 */
	private String type;
	/** 字典描述 */
	private String description;
	/** 是否系统内置 */
	private String isSys;
	/** CSS样式 */
	private String cssStyle;
	/** CSS类 */
	private String cssClass;

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsSys() {
		return isSys;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}

	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
}