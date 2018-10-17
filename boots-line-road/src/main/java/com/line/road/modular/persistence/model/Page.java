package com.line.road.modular.persistence.model;

public class Page {
	// 下面pageNum、pageSize需要和Mybatis-PageHelper插件params属性配置的名字一致
	private Integer pageNum;// 第几页
	private Integer pageSize = 5;// 每页几条,默认每页10条

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
