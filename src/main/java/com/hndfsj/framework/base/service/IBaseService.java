package com.hndfsj.framework.base.service;

import java.io.Serializable;
import java.util.List;

import com.hndfsj.framework.pager.PageModel;
import com.hndfsj.framework.pager.PageRequest;

public interface IBaseService<E, PK extends Serializable> {

	/**
	 * 根据ID查找对象
	 * 
	 * @param id
	 * @return
	 */
	public E getById(PK id);

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 */
	public void deleteById(PK id);

	/**
	 * 保存
	 * 
	 * @param entity
	 */
	public void save(E entity);

	/**
	 * 更新
	 * 
	 * @param entity
	 */
	public void update(E entity);

	/**
	 * 所有记录集
	 * 
	 * @return
	 */
	public List<E> findAll();

	/**
	 * 根据查询条件返回记录集
	 * 
	 * @param pageRequest
	 * @return
	 */
	public List<E> findAll(PageRequest pageRequest);

	/**
	 * 根据查询条件返回记录集(分页)
	 * 
	 * @param pageRequest
	 * @return
	 */
	public PageModel findPageAll(PageRequest pageRequest);

	/**
	 * 返回所有记录数
	 * 
	 * @return
	 */
	public int getCount();

	/**
	 * 返回符合条件的记录数
	 * 
	 * @param search
	 * @return
	 */
	public int getCount(PageRequest search);

	/**
	 * 某字段值是否已经存在
	 * 
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	public boolean isExist(String propertyName, Object propertyValue);

	public Object queryCustomObject(String statementName, Object paraObject, Class clas);

	Object queryCustomList(String statementName, Object paraObject, Class clas);

	/**
	 * 根据查询条件返回记录集(分页)
	 * 
	 * @param pageRequest
	 * @return
	 */
	public PageModel findColumnsPageAll(PageRequest pageRequest);


	/**
	 * 根据查询条件返回记录集
	 * 
	 * @param pageRequest
	 * @return
	 */
	public List<E> findColumnsAll(PageRequest pageRequest);
}
