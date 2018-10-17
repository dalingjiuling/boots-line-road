package com.line.road.modular.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.line.road.modular.persistence.model.SysMenu;
import com.line.road.modular.system.service.ISysMenuService;

@Controller
@RequestMapping("menu")
public class SysMenuController {

	@Autowired
	private ISysMenuService iSysMenuService;

	/**
	 * 左边导航菜单
	 */
	@CrossOrigin
	@RequestMapping(value = "left", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<SysMenu> getLeftMenuList() {
		return iSysMenuService.getLeftMenuList();
	}

	/**
	 * 获取全部菜单
	 * 
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "meunList")
	@ResponseBody
	public PageInfo<SysMenu> allMenus(SysMenu sysMenu) {
		if (null != sysMenu.getPageNum()) {
			// 分页
			PageHelper.startPage(sysMenu);
		}
		List<SysMenu> menus = iSysMenuService.allMenus(sysMenu);
		PageInfo<SysMenu> page = new PageInfo<SysMenu>(menus);
		return page;
	}

	/**
	 * 添加菜单
	 * 
	 * @param sysMenu
	 */
	@CrossOrigin
	@RequestMapping("addMenu")
	@ResponseBody
	public int addMenu(SysMenu sysMenu) {
		return iSysMenuService.insertSysMenu(sysMenu);
	}

	/**
	 * 修改菜单
	 * 
	 * @param sysMenu
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "update")
	@ResponseBody
	public int updateMenu(SysMenu sysMenu) {
		return iSysMenuService.updateSysMenu(sysMenu);
	}

	/**
	 * 删除菜单
	 * 
	 * @param menuId
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "delete")
	@ResponseBody
	public boolean delectSysMenu(int menuId) {
		return iSysMenuService.delectSysMenu(menuId);
	}

	/**
	 * 根据ID获取菜单
	 * 
	 * @param menuId
	 *            主键
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "get")
	@ResponseBody
	public SysMenu getSysMenuById(int menuId) {
		return iSysMenuService.getSysMenuById(menuId);
	}
}
