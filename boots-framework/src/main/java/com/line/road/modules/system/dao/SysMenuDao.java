package com.line.road.modules.system.dao;

import java.util.List;

import com.line.road.framework.persistence.annotion.MyBatisDao;
import com.line.road.modules.system.entity.SysMenu;

@MyBatisDao
public interface SysMenuDao {

	/**
	 * 获取左侧菜单
	 * 
	 * @return
	 */
	public List<SysMenu> getLeftMenuList();

	/**
	 * 获取全部菜单
	 * 
	 * @return
	 */
	public List<SysMenu> getAllMenus(SysMenu sysMenu);

	/**
	 * 插入菜单，同时返回主键ID
	 * 
	 * @param sysMenu
	 * @return
	 */
	public int insertSysMenu(SysMenu sysMenu);

	/**
	 * 更新菜单数据
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
