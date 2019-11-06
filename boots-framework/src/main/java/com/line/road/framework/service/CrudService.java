package com.line.road.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.line.road.framework.persistence.CrudDao;
import com.line.road.framework.persistence.entity.BaseEntity;

/**
 * 增删改成
 * 
 * @author Administrator
 *
 * @param <E>
 *            实例对象
 * @param <D>
 *            数据库操作DAO
 */
@Transactional(readOnly = true)
public class CrudService<E extends BaseEntity<?>, D extends CrudDao<E>> extends BaseService {

	@Autowired
	protected D dao;
	protected Class<E> entityClass;

	/**
	 * 根据条件查询全部信息
	 * 
	 * @param param
	 * @return
	 */
	public List<E> findAllList(E param) {
		return dao.findAllList(param);
	}

	/**
	 * 根据相关条件查询对象的信息
	 * 
	 * @param param
	 * @return
	 */
	public E get(E param) {
		return dao.get(param);
	}

	/**
	 * 根据某个条件查询对象的信息,一般为主键
	 * 
	 * @param paramString
	 * @return
	 */
	public E get(String paramString) {
		return dao.get(paramString);
	}

	/**
	 * 插入数据
	 * 
	 * @param param
	 * @return
	 */
	@Transactional(readOnly = false)
	public int insert(E param) {
		return dao.insert(param);
	}

	/**
	 * 删除数据
	 * 
	 * @param param
	 * @return
	 */
	@Transactional(readOnly = false)
	public int delete(E param) {
		return dao.delete(param);
	}

	/**
	 * 更新数据
	 * 
	 * @param param
	 * @return
	 */
	@Transactional(readOnly = false)
	public int update(E param) {
		return dao.update(param);
	}
}
