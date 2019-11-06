package com.line.road.framework.web.page;

import javax.persistence.Transient;

/**
 * 分页数据
 * 
 * @author zhaoliangliang
 * @since 2018-04-25 10:48
 */
public class PageDomain {

	/** 当前记录起始页索引 */
	// Transient 注解来告诉通用 Mapper这不是表中的字段
	@Transient
	private Integer pageNum;
	/** 每页显示记录数 */
	@Transient
	private Integer pageSize;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
