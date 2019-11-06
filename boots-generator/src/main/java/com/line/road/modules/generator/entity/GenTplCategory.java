package com.line.road.modules.generator.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.line.road.modules.system.entity.SysDict;

public class GenTplCategory extends SysDict {
	private static final long serialVersionUID = 1L;

	public static String CATEGORY_REF = "category-ref:";

	private List<String> template;
	private List<String> childTableTemplate;

	@XmlElement(name = "template")
	public List<String> getTemplate() {
		return template;
	}

	public void setTemplate(List<String> template) {
		this.template = template;
	}

	@XmlElementWrapper(name = "childTable")
	@XmlElement(name = "template")
	public List<String> getChildTableTemplate() {
		return childTableTemplate;
	}

	public void setChildTableTemplate(List<String> childTableTemplate) {
		this.childTableTemplate = childTableTemplate;
	}
}
