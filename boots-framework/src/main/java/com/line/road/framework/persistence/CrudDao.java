package com.line.road.framework.persistence;

import java.util.List;

public interface CrudDao<E> extends BaseDao {

	/**
	 * 查询全部信息
	 * 
	 * @param param
	 * @return
	 */
	public List<E> findAllList(E param);

	/**
	 * 根据条件查询全部信息
	 * 
	 * @param param
	 * @return
	 */
	public List<E> findList(E param);

	/**
	 * 根据相关条件查询对象的信息
	 * 
	 * @param param
	 * @return
	 */
	public E get(E param);

	/**
	 * 根据某个条件查询对象的信息,一般为主键
	 * 
	 * @param paramString
	 * @return
	 */
	public E get(String paramString);

	/**
	 * 插入数据
	 * 
	 * @param param
	 * @return
	 */
	public int insert(E param);

	/**
	 * 删除数据
	 * 
	 * @param param
	 * @return
	 */
	public int delete(E param);

	/**
	 * 更新数据
	 * 
	 * @param param
	 * @return
	 */
	public int update(E param);
}
