package com.line.road.modules.generator.entity;

import com.line.road.framework.persistence.entity.BaseEntity;

/**
 * 对应模板生成相关文件的信息
 * 
 * @author Administrator
 *
 */
public class GenTemplate extends BaseEntity<GenTemplate> {
	private static final long serialVersionUID = 1L;
	/** 模板名称 */
	private String name;
	/** 列表 */
	private String category;
	/** 文件路径 */
	private String filePath;
	/** 文件名称 */
	private String fileName;
	/** 文件名称 */
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
