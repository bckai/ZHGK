package com.hndfsj.framework.pager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 页面请求类(包括分页、排序、查询等)
 * 
 * @author ibm
 * @date May 22, 2010
 */
public class PageRequest {

	private int currentPage;// 请求页
	private int pageSize;// 每页行数
	private String sortOrder;// 排序方式(asc desc)
	private String sortCol;// 排序字段
	private List<SearchCondition> andConditions;// and条件
	private List<SearchCondition> orConditions;// or条件
	private List<SearchCondition> sortConditions;// 多重排序
	private Map<String, Object> map;// 存放其他非string类型参数
	private String escape ="escape";
	private String substring ="substring";
	private final static String LEFTSQL ="leftSql";
	public static final String MAP_COLUMNS="columns";
	public static final String MAP_GROUPBY="groupBy";
	public static final String MAP_LEFTSQL="leftSql";
	public static final String ORDER_ASC="ASC";
	public static final String ORDER_DESC="DESC";
	/**
	 * 默认构造函数
	 */
	public PageRequest() {
	}

	/**
	 * 构造函数
	 * 
	 * @param currentPage
	 *            分页索引
	 * @param pageSize
	 *            页的行数
	 * @param pageCount
	 *            总页数
	 */
	public PageRequest(int currentPage, int pageSize) {
		this(currentPage, pageSize, null, null);
	}

	/**
	 * 构造函数
	 * 
	 * @param currentPage
	 *            分页索引
	 * @param pageSize
	 *            页的行数
	 * @param pageCount
	 *            总页数
	 * @param sortCol
	 *            排序字段
	 * @param sortOrder
	 *            排序方式
	 */
	public PageRequest(int currentPage, int pageSize, String sortCol, String sortOrder) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.sortCol = sortCol;
		this.sortOrder = sortOrder;
		if(sortCol!=null&&sortCol!=""){
			sortOrder=sortOrder==null?"asc":sortOrder;
			addSortConditions(sortCol, sortOrder);
		}
		this.sortCol=null;
	}
	public PageRequest( String sortCol, String sortOrder) {
		this.sortCol = sortCol;
		this.sortOrder = sortOrder;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortcol() {
		return sortCol;
	}

	public void setSortcol(String sortcol) {
		this.sortCol = sortcol;
	}
	public void setColumns(String columns) {
		putMap(MAP_COLUMNS, columns);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getEscape() {
		return escape;
	}

	public String getSubstring() {
		return substring;
	}

	public int getOffset() {
		return (currentPage - 1) * pageSize;
	}

	public List<SearchCondition> getAndConditions() {
		return andConditions;
	}

	public List<SearchCondition> getOrConditions() {
		return orConditions;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void putMap(String key, Object value) {
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put(key, value);
	}

	public void addAndCondition(String key, String operator, Object value) {
		if (this.andConditions == null)
			this.andConditions = new ArrayList<SearchCondition>();
		this.andConditions.add(new SearchCondition(key, operator, value));
	}
	public void addSortConditions(String sortCol, String sortOrder) {
		if (this.sortConditions == null)
			this.sortConditions = new ArrayList<SearchCondition>();
		this.sortConditions.add(new SearchCondition(sortCol, null, sortOrder));
	}
	public void setLeftJoinSql(String leftJoinSql) {
		if(StringUtils.isNotBlank(leftJoinSql)){
			putMap(LEFTSQL, leftJoinSql);
		}
	}

	public void addOrCondition(String key, String operator, Object value) {
		if (this.orConditions == null)
			this.orConditions = new ArrayList<SearchCondition>();
		this.orConditions.add(new SearchCondition(key, operator, value));
	}

	public int getStart() {
		return (currentPage - 1) * pageSize + 1;
	}

	public int getEnd() {
		return currentPage * pageSize;
	}

	
}
