package com.line.road.modules.generator.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.line.road.modules.system.entity.SysDict;

public class GenConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 生成文件 */
	private List<GenTplCategory> tplCategoryList;
	/** java类型 */
	private List<SysDict> javaTypeList;
	/** 查询类型 */
	private List<SysDict> queryTypeList;
	/** 显示类型 */
	private List<SysDict> showTypeList;

	@XmlElementWrapper(name = "tplCategory")
	@XmlElement(name = "category")
	public List<GenTplCategory> getTplCategoryList() {
		return tplCategoryList;
	}

	public void setTplCategoryList(List<GenTplCategory> tplCategoryList) {
		this.tplCategoryList = tplCategoryList;
	}

	@XmlElementWrapper(name = "javaType")
	@XmlElement(name = "dict")
	public List<SysDict> getJavaTypeList() {
		return javaTypeList;
	}

	public void setJavaTypeList(List<SysDict> javaTypeList) {
		this.javaTypeList = javaTypeList;
	}

	@XmlElementWrapper(name = "queryType")
	@XmlElement(name = "dict")
	public List<SysDict> getQueryTypeList() {
		return queryTypeList;
	}

	public void setQueryTypeList(List<SysDict> queryTypeList) {
		this.queryTypeList = queryTypeList;
	}

	@XmlElementWrapper(name = "showType")
	@XmlElement(name = "dict")
	public List<SysDict> getShowTypeList() {
		return showTypeList;
	}

	public void setShowTypeList(List<SysDict> showTypeList) {
		this.showTypeList = showTypeList;
	}
}
