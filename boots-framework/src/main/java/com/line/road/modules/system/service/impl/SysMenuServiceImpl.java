package com.line.road.modules.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.line.road.modules.system.dao.SysMenuDao;
import com.line.road.modules.system.entity.SysMenu;
import com.line.road.modules.system.service.ISysMenuService;

@Service
@Transactional
public class SysMenuServiceImpl implements ISysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao;

	@Override
	public List<SysMenu> getLeftMenuList() {
		List<SysMenu> menus = sysMenuDao.getLeftMenuList();

		if (null != menus && !menus.isEmpty()) {

			for (int i = 0; i < menus.size(); i++) {
				SysMenu menu = menus.get(i);
				// 子操作父集
				if (0 != menu.getMenuPrantId()) {
					continue;
				}
				// 循环父集菜单
				recursive(menus, menu);
			}
			for (int j = menus.size() - 1; j >= 0; j--) {
				SysMenu menu = menus.get(j);
				if (0 != menu.getMenuPrantId()) {
					menus.remove(j);
				}
			}
		}

		return menus;
	}

	/**
	 * 递归所有子菜单
	 * 
	 * @param menus
	 *            所有的菜单
	 * @param prantMenu
	 *            二级菜单
	 */
	private void recursive(List<SysMenu> menus, SysMenu prantMenu) {
		// 判断是否有子规则
		List<SysMenu> prant = prantMenu.getSubMenu();
		if (null != prant && !prant.isEmpty()) {

			for (int i = 0; i < prant.size(); i++) {
				// 二级菜单的子集
				List<SysMenu> subList = new ArrayList<SysMenu>();
				// 二级菜单对象
				SysMenu pMenu = prant.get(i);

				for (int z = 0; z < menus.size(); z++) {
					SysMenu subMenu = menus.get(z);
					if (subMenu.getMenuPrantId() == pMenu.getMenuId()) {
						subList.add(subMenu);
						pMenu.setSubMenu(subList);
						recursive(menus, subMenu);
					} else {
						continue;
					}
				}

			}
		}
	}

	@Override
	public int insertSysMenu(SysMenu sysMenu) {
		sysMenu.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:ss:dd").format(new Date()));
		return sysMenuDao.insertSysMenu(sysMenu);
	}

	@Override
	public List<SysMenu> allMenus(SysMenu sysMenu) {
		return sysMenuDao.getAllMenus(sysMenu);
	}

	@Override
	public int updateSysMenu(SysMenu sysMenu) {
		sysMenu.setModifyTime(new SimpleDateFormat("yyyy-MM-dd HH:ss:dd").format(new Date()));
		return sysMenuDao.updateSysMenu(sysMenu);
	}

	@Override
	public boolean delectSysMenu(int menuId) {
		return sysMenuDao.delectSysMenu(menuId);
	}

	@Override
	public SysMenu getSysMenuById(int menuId) {
		return sysMenuDao.getSysMenuById(menuId);
	}

}
