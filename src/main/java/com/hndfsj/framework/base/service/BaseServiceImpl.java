package com.hndfsj.framework.base.service;

import java.io.Serializable;
import java.util.List;

import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.pager.PageModel;
import com.hndfsj.framework.pager.PageRequest;

@SuppressWarnings("unchecked")
public abstract class BaseServiceImpl<E, PK extends Serializable> implements IBaseService<E, PK> {

	protected abstract IBaseDao getBaseDao();

	public E getById(PK id) {
		return (E) getBaseDao().getById(id);
	}

	public void deleteById(PK id) {
		getBaseDao().deleteById(id);
	}

	public void save(E entity) {
		getBaseDao().save(entity);
	}

	public void update(E entity) {
		getBaseDao().update(entity);
	}

	public List<E> findAll() {
		return getBaseDao().findAll();
	}

	public List<E> findAll(PageRequest pageRequest) {
		return getBaseDao().findAll(pageRequest);
	}

	public PageModel findPageAll(PageRequest pageRequest) {
		return getBaseDao().findPageAll(pageRequest);
	}

	public int getCount() {
		return getBaseDao().getCount();
	}

	public int getCount(PageRequest search) {
		return getBaseDao().getCount(search);
	}

	public boolean isExist(String propertyName, Object propertyValue) {
		return getBaseDao().isExist(propertyName, propertyValue);
	}

	public Object queryCustomObject(String statementName, Object paraObject, Class clas) {
		return getBaseDao().queryCustomObject(statementName, paraObject, clas);
	}

	public Object queryCustomList(String statementName, Object paraObject, Class clas) {
		return getBaseDao().queryCustomList(statementName, paraObject, clas);
	}

	/**
	 * 根据查询条件返回记录集(分页)
	 * 
	 * @param pageRequest
	 * @return
	 */
	public PageModel findColumnsPageAll(PageRequest pageRequest) {
		return getBaseDao().findColumnsPageAll(pageRequest);
	}

	/**
	 * 根据查询条件返回记录集
	 * 
	 * @param pageRequest
	 * @return
	 */
	public List<E> findColumnsAll(PageRequest pageRequest) {
		return getBaseDao().findColumnsAll(pageRequest);
	}

}
