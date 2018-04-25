package com.line.road.framework.web.support;

import javax.servlet.http.HttpServletRequest;

import com.line.road.common.utils.MapDataUtil;
import com.line.road.common.utils.ServletUtils;
import com.line.road.framework.web.page.PageUtilEntity;

public class TableSupport {

	/**
	 * 封装分页对象
	 */
	public static PageUtilEntity getPageUtilEntity() {
		HttpServletRequest request = ServletUtils.getHttpServletRequest();
		PageUtilEntity pageUtilEntity = new PageUtilEntity();
		pageUtilEntity.setPage(Integer.valueOf(request.getParameter("offset")));
		pageUtilEntity.setSize(Integer.valueOf(request.getParameter("limit")));
		pageUtilEntity.setOrderByColumn(request.getParameter("sort"));
		pageUtilEntity.setIsAsc(request.getParameter("order"));
		pageUtilEntity.setSearchValue(request.getParameter("search"));
		pageUtilEntity.setReqMap(MapDataUtil.convertDataMap(request));
		return pageUtilEntity;
	}

	public static PageUtilEntity buildPageRequest() {
		return getPageUtilEntity();
	}
}
