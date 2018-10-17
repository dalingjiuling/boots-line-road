package com.line.road.modular.system.service;

import java.util.List;

import com.line.road.modular.persistence.model.SysMenu;

public interface ISysMenuService {

	/**
	 * 获取左侧菜单
	 * 
	 * @return
	 */
	public List<SysMenu> getLeftMenuList();

	/**
	 * 插入菜单，同时返回主键ID
	 * 
	 * @param sysMenu
	 * @return
	 */
	public int insertSysMenu(SysMenu sysMenu);

	/**
	 * 获取全部菜单
	 * 
	 * @param sysMenu
	 * @return
	 */
	public List<SysMenu> allMenus(SysMenu sysMenu);

	/**
	 * 修改菜单
	 * 
	 * @param sysMenu
	 * @return
	 */
	public int updateSysMenu(SysMenu sysMenu);

	/**
	 * 删除菜单
	 * 
	 * @param menuId
	 * @return
	 */
	public boolean delectSysMenu(int menuId);

	/**
	 * 根据ID获取菜单
	 * 
	 * @param menuId
	 *            主键
	 * @return
	 */
	public SysMenu getSysMenuById(int menuId);
}
