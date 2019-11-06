package com.line.road.framework.web.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.line.road.framework.web.page.PageDomain;
import com.line.road.framework.web.page.PageUtilEntity;
import com.line.road.framework.web.support.TableSupport;

/**
 * controller 基类
 * 
 * @author zhaoliangliang
 * @since 2018-04-25 18:53
 *
 */
public class BaseController {

	/**
	 * 获取请求分页数据
	 */
	public PageUtilEntity getPageUtilEntity() {
		PageUtilEntity pageUtilEntity = TableSupport.buildPageRequest();
		return pageUtilEntity;
	}

	/**
	 * 设置请求分页数据
	 */
	protected void setPageInfo(Object obj) {
		PageDomain page = (PageDomain) obj;
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
	}

	/**
	 * 获取分页后的数据
	 * 
	 * @param list
	 * @return
	 */
	protected <T> PageInfo<T> getPage(List<T> list) {
		return new PageInfo<T>(list);
	}
}
