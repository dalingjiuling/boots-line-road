package com.line.road.modular.persistence.model;

import java.util.List;

/**
 * 菜单表
 * 
 * @author 赵亮亮
 *
 */
public class SysMenu extends Page {

	/** 菜单主键 */
	private Integer menuId;
	/** 链接路径 */
	private String dataUrl;
	/** 菜单样式 */
	private String menuClass;
	/** 菜单名称 */
	private String menuName;
	/** 上级菜单主键 */
	private Integer menuPrantId;
	/** 菜单排序 */
	private Integer sequence;
	/** 菜单类型(1是左导航菜单 2是按钮权限) */
	private String menuType;
	/** 状态（0：禁用；1：启用） */
	private String menuState;
	/** 菜单排序 */
	private String menuIcon;
	/** 创建时间 */
	private String createTime;
	/** 修改时间 */
	private String modifyTime;
	/** 创建者 */
	private Integer createUser;
	/** 修改者 */
	private Integer modifyUser;
	/** 子菜单 */
	private List<SysMenu> subMenu;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getDataUrl() {
		return dataUrl;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	public String getMenuClass() {
		return menuClass;
	}

	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getMenuPrantId() {
		return menuPrantId;
	}

	public void setMenuPrantId(Integer menuPrantId) {
		this.menuPrantId = menuPrantId;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(Integer modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getMenuState() {
		return menuState;
	}

	public void setMenuState(String menuState) {
		this.menuState = menuState;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public List<SysMenu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<SysMenu> subMenu) {
		this.subMenu = subMenu;
	}

}
