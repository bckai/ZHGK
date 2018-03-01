package com.hndfsj.framework.pager;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.hndfsj.framework.common.MReturnObject;

/**
 * 类描述：分页模型
 * 
 * 创建人：ibm 创建时间：Sep 29, 2009 9:03:18 AM
 * 
 */

@SuppressWarnings("unchecked")
public class PageModel {

	private int pageIndex; // 当前页（第一页=1）
	private int recCount; // 总记录数
	private int pageSize; // 每页行数
	private String sortCol; // 排序字段
	private String sortOrder; // 排序方式
	private List data;// 当前页的记录集

	// 2013年1月7日 Mr.Hao 针对框架页设计返回数据属性
	private int pages;// 总页数
	private boolean firstPage;// 第一页？
	private boolean lastPage;// 最后一页？
	private boolean hasNext;// 是否有下一页？
	private boolean hasPrev;// 是否有上一页？

	public PageModel() {
	}

	public PageModel(int pageIndex, int pageSize, int pageCount, List data) {
		this(pageIndex, pageSize, pageCount, null, null, data);
	}

	public PageModel(int pageIndex, int pageSize, int recCount, String sortCol, String sortOrder, List data) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.recCount = recCount;
		this.sortCol = sortCol;
		this.sortOrder = sortOrder;
		this.data = data;
		// 2013年1月7日 Mr.Hao
		try {
			pageIndex = pageIndex == 0 ? 1 : pageIndex;
			if (pageIndex == 1)
				this.firstPage = true;
			pages = (pages = recCount % pageSize == 0 ? recCount / pageSize : recCount / pageSize + 1) == 0 ? 1 : pages;
			if (pages - pageIndex > 0)
				hasNext = true;
			if (pageIndex > 1)
				hasPrev = true;
			if (pages == pageIndex)
				lastPage = true;
		} catch (Exception e) {
		}
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getRecCount() {
		return recCount;
	}

	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public String getSortCol() {
		return sortCol;
	}

	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasPrev() {
		return hasPrev;
	}

	public void setHasPrev(boolean hasPrev) {
		this.hasPrev = hasPrev;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("pageIndex", getPageIndex())
				.append("recCount", getRecCount()).append("pageSize", getPageSize()).append("pages", getPages())
				.append("sortCol", getSortCol()).append("sortOrder", getSortOrder()).append("data", getData())
				.toString();
	}

	public MReturnObject toMReturnObject() {
		MReturnObject returnObj = new MReturnObject(MReturnObject.SUCCESS, "请求成功");
		returnObj.setRespList(data);
		returnObj.setPageIndex(pageIndex);
		returnObj.setItemCount(data.size());
		returnObj.setPages(pages);
		returnObj.setPageSize(pageSize);
		returnObj.setServerDate(new Date());
		returnObj.setSortCol(sortCol);
		returnObj.setSortOrder(sortOrder);
		returnObj.setRecCount(recCount);
		returnObj.setLastPage(lastPage);
		returnObj.setFirstPage(firstPage);
		returnObj.setHasNext(hasNext);
		returnObj.setHasPrev(hasPrev);
		return returnObj;
	}

}
