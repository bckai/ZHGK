package com.hndfsj.framework.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hndfsj.framework.pager.PageModel;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<E,PK extends Serializable> extends SqlSessionDaoSupport implements IBaseDao<E,PK> {
	
	public static Logger log=LoggerFactory.getLogger(BaseDaoImpl.class);
	
	
	@Autowired
	@Qualifier("sqlSessionFactory")
	public void setRWSqlMapClient(SqlSessionFactory  sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	//==操作的对象
	private final Class<E> clazz;

	public BaseDaoImpl(Class<E> clazz){
		this.clazz = clazz;
		log=LoggerFactory.getLogger(clazz);
	}
	
	//==主要方法
	public E getById(PK id) {
    	return queryObject(getFindByIdQuery(),id);
    }
    
	public void deleteById(PK id) {
		delete(getDeleteQuery(), id);
	}
    
    public Object save(E entity) {
    	return insert(getInsertQuery(), entity);
    }
    
	public void update(E entity) {
		update(getUpdateQuery(), entity);
	}
	
	public List<E> findAll(){
		return queryList(getAllQuery());
	}
	
	public List<E> findAll(PageRequest pageRequest){
		return queryList(getAllQuery(),pageRequest);
	}
	
	public PageModel findPageAll(PageRequest pageRequest){
		return queryPageList(getAllQuery(), getCountQuery(), pageRequest);
	}
	
	public int getCount(){
		return getCount(getCountQuery(),null);
	}	
	
	public int getCount(PageRequest search){
		return getCount(getCountQuery(),search);
	}
	
	public boolean isExist(String propertyName, Object propertyValue){
		PageRequest pageRequest = new PageRequest();
		pageRequest.addAndCondition(propertyName, SearchCondition.EQUAL, propertyValue);
		return getCount(pageRequest)>0?true:false;
	}
	
	private E _uniqueResult(List<E> result) {
		if (result != null && result.size() != 0) {
			return (E) result.get(0);
		}
		return null;
	}	
	
    //==xml sql定义规范
	private String getTableName(){
		return clazz.getSimpleName()+".";
	}
    private String getFindByIdQuery() {
        return "getById";
    }

    private String getInsertQuery() {
        return "insert";
    }

    private String getUpdateQuery() {
    	return "update";
    }

    private String getDeleteQuery() {
    	return "deleteById";
    }

    private String getCountQuery() {
		return "count";
	}

    private String getAllQuery() {
		return "getAll";
	}
    private String getColumnsAllQuery() {
    	return "getColumnsAll";
    }
    
	/**
	 * 根据查询指令返回记录集（分页）
	 * 
	 * @param statementName
	 * @param pageRequest
	 * @return
	 */
	protected PageModel queryPageList(String statementName,String countStatementName, PageRequest pageRequest){
		
		//==获取总记录数
		int recCount = getCount(countStatementName,pageRequest);
		
		//==构造PageModel对象
		int pageSize = pageRequest.getPageSize();
		int pageIndex = pageRequest.getCurrentPage();
		String sortCol = pageRequest.getSortcol();
		String sortOrder = pageRequest.getSortOrder();
		List<E> data = queryList(statementName,pageRequest);
		return new PageModel(pageIndex, pageSize, recCount, sortCol, sortOrder, data);
	}    
	
	/**
	 * 根据查询指令返回记录集
	 * 
	 * @param statementName
	 * @return
	 */	
	protected List<E> queryList(String statementName){
		return (List<E>)getSqlSession().selectList(getTableName()+statementName);
	}
	
	/**
	 * 根据查询指令和某个属性值返回符合条件的列表
	 * 
	 * @param statementName,paraObject
	 * @return
	 */	
	protected List<E> queryList(String statementName,Object paraObject){
		return (List<E>)getSqlSession().selectList(getTableName()+statementName,paraObject);
	}
	
	/**
	 * 根据查询指令和某个属性值返回符合条件的列表<br>
	 * 返回对象为原生对象
	 * 
	 * @param statementName,paraObject
	 * @return
	 */
	protected List nativeQueryList(String statementName,Object paraObject){
		return getSqlSession().selectList(getTableName()+statementName,paraObject);
	}
	
	/**
	 * 根据查询条件返回记录集
	 * 
	 * @param statementName
	 * @param pageRequest
	 * @return
	 */	
	protected List<E> queryList(String statementName,PageRequest pageRequest){
		return (List<E>)getSqlSession().selectList(getTableName()+statementName,pageRequest);
	}
	
	/**
	 * 执行删除指令
	 * 
	 * @param statementName
	 * @param paraObject
	 */
	protected void delete(String statementName,Object paraObject){
		getSqlSession().delete(getTableName()+statementName,paraObject);
	}
	
	/**
	 * 执行插入指令
	 * 
	 * @param statementName
	 * @param paraObject
	 */
	protected Object insert(String statementName,Object paraObject){
		return getSqlSession().insert(getTableName()+statementName,paraObject);
	}
	
	/**
	 * 执行更新指令
	 * 
	 * @param statementName
	 * @param paraObject
	 */
	protected void update(String statementName,Object paraObject){
		getSqlSession().update(getTableName()+statementName,paraObject);
	}
	
	/**
	 * 根据查询指令返回对象
	 * 
	 * @param statementName
	 * @return
	 */	
	protected E queryObject(String statementName){
		return _uniqueResult(queryList(statementName));
	}
	
	/**
	 * 根据查询指令和某个属性值返回符合条件的对象
	 * 
	 * @param statementName,propertyValue
	 * @return
	 */	
	protected E queryObject(String statementName,Object paraObject){
		return _uniqueResult(queryList(statementName,paraObject));
	}
	
	/**
	 * 根据查询指令和某个属性值集合对象返回符合条件的对象
	 * 
	 * @param statementName,propertyValue
	 * @return
	 */	
	protected Object nativeQueryObject(String statementName,Object paraObject){
		return getSqlSession().selectOne(getTableName()+statementName,paraObject);
	}
	
	/**
	 * 根据查询条件返回对象
	 * 
	 * @param statementName
	 * @param pageRequest
	 * @return
	 */	
	protected E queryObject(String statementName,PageRequest pageRequest){
		return _uniqueResult(queryList(statementName,pageRequest));
	}
	/**
	 * 依据查询指令返回记录集（分页），适用于特殊查询，如：多张表联合
	 * 
	 * @param statementName
	 * @param countStatementName
	 * @param pageRequest
	 * @return
	 * @author Mr.Hao
	 * @version 2013-1-23 下午05:28:50
	 */
	protected PageModel queryPageListForM(String statementName, String countStatementName,
			Map<? extends Object, ? extends Object> map) {
		int recCount = getCountForM(countStatementName, map);
		Object pages = map.get("pages");
		PageRequest pageRequest = null;
		if (pages != null) {
			pageRequest = (PageRequest) pages;
		}
		int pageIndex = pageRequest.getCurrentPage();
		int pageSize = pageRequest.getPageSize();
		String sortCol = pageRequest.getSortcol();
		String sortOrder = pageRequest.getSortOrder();
		List<E> data = queryList(statementName, map);
		return new PageModel(pageIndex, pageSize, recCount, sortCol, sortOrder, data);
	}

	/**
	 * 返回对应查询的记录个数，专为解决“滴滴”项目方法
	 * 
	 * @param statementName
	 * @return
	 * @author Mr.Hao
	 * @version 2013-1-23 下午05:30:59
	 */
	protected int getCountForM(String statementName, Object object) {
		int count = (Integer) getSqlSession().selectOne(
				getTableName() + statementName, object);
		return count;
	}
	
	/**
	 * 返回对应查询的记录个数
	 * 
	 * @param statementName
	 * @param search
	 * @return
	 */
	protected int getCount(String statementName,PageRequest search){
		Integer countIneger=(Integer)getSqlSession().selectOne(getTableName()+statementName,search);
		int count = countIneger==null?0:countIneger;
		return count;
	}
	
	/**
	 * 返回对应查询的记录个数
	 * 
	 * @param statementName
	 * @param search
	 * @return
	 */
	protected int getCountTable(String statementName,E e){
		Integer countIneger=(Integer)getSqlSession().selectOne(getTableName()+statementName,e);
		int count = countIneger==null?0:countIneger;
		return count;
	}
	
	public Object queryCustomObject(String statementName,Object paraObject,Class clas){
		return _uniqueResult((List<E>) getSqlSession().selectList(clas.getSimpleName()+"."+statementName,paraObject));
	}
	public Object queryCustomList(String statementName,Object paraObject,Class clas){
		return getSqlSession().selectList(clas.getSimpleName()+"."+statementName,paraObject);
	}
	
	@Override
	public List<E> findColumnsAll(PageRequest pageRequest) {
		return queryList(getColumnsAllQuery(),pageRequest);
	}
	@Override
	public PageModel findColumnsPageAll(PageRequest pageRequest) {
		return queryPageList(getColumnsAllQuery(), getCountQuery(), pageRequest);
	}
	@Override
	public int getTableCount(E entity) {
		return (Integer)getSqlSession().selectOne(getTableName()+"getTableCount", entity);
	}
}
