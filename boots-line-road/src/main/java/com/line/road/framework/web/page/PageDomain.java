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
	@Transient // Transient 注解来告诉通用 Mapper这不是表中的字段
	private int pageNum = 1;
	/** 每页显示记录数 */
	@Transient
	private int pageSize = 10;
	/** 排序列 */
	@Transient
	private String orderByColumn;
	/** 排序的方向 "desc" 或者 "asc". */
	@Transient
	private String isAsc;
	/** 搜索值 */
	@Transient
	private String searchValue;

	/**
	 * 排序字段+排序顺序
	 * 
	 * @return
	 */
	public String getOrderBy() {
		return orderByColumn + " " + isAsc;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderByColumn() {
		return orderByColumn;
	}

	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}

	public String getIsAsc() {
		return isAsc;
	}

	public void setIsAsc(String isAsc) {
		this.isAsc = isAsc;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
}
