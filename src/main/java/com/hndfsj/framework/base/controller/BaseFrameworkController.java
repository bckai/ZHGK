package com.hndfsj.framework.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hndfsj.framework.config.RWAdminConfiguration;
import com.hndfsj.framework.pager.PageRequest;

/**
 * 针对框架（定制框架）写该父类<br/>
 * 定义标准的crud rest方法规范，以实现rest统一标准，并可以避免子类重复编写@RequestMapping
 * 子类要实现某功能只需覆盖下面的方法即可, 注意: 覆盖时请使用@Override,以确保不会发生错误
 * 
 * @copyright {@link www.hndfsjsoft.com}
 * @author haoyingshuai@hndfsj.com,haoluziqi@126|gmail.com
 * @version 2013-1-15 上午10:19:26
 * @see com.hndfsj.framework.base.controller.BaseFrameworkController
 * 
 * @param <Entity>
 * @param <PK>
 */
public class BaseFrameworkController<Entity, PK> {

	/**
	 * 进入查看页
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}")
	public @ResponseBody
	Entity show(@PathVariable("id") PK id) {
		throw new UnsupportedOperationException("not implement");
	}

	/**
	 * 进入新增页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/post/pre")
	public String _new(Model model,HttpServletRequest request) {
		throw new UnsupportedOperationException("not implement");
	}

	/**
	 * 进入修改页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/put/pre")
	public String edit(@PathVariable("id") PK id, Model model) {
		throw new UnsupportedOperationException("not implement");
	}

	/**
	 * 构造分页排序请求(查询尚未实现)
	 * 
	 * @param request
	 * @return
	 */
	protected PageRequest newPageRequest(HttpServletRequest request) {
		int pageIndex = NumberUtils.toInt(request.getParameter("pageIndex"), 1);
		int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), Integer
				.parseInt(RWAdminConfiguration.getInstance().getAppPropsValue("pagesize", "15")));
		String sortCol = request.getParameter("sort");
		String sortOrder = request.getParameter("order");
		return new PageRequest(pageIndex, pageSize, sortCol, sortOrder);
	}
}
