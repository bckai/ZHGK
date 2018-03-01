package com.hndfsj.framework.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hndfsj.framework.config.RWAdminConfiguration;
import com.hndfsj.framework.objects.RWReturnObject;
import com.hndfsj.framework.pager.PageRequest;

/**
 * 定义标准的rest方法规范，以实现rest统一标准，并可以避免子类重复编写@RequestMapping 子类要实现某功能只需覆盖 该方法即可, 注意:
 * 覆盖时请使用@Override,以确保不会发生错误
 * 
 * <font color="red">frameset 框架页项目使用</font>
 * 
 * @copyright {@link www.hndfsjsoft.com}
 * @author haoyingshuai@hndfsj.com,haoluziqi@126|gmail.com
 * @version 2013-1-15 上午09:53:44
 * @see com.hndfsj.framework.base.controller.BaseRestController
 * 
 * @param <Entity>
 * @param <PK>
 */
public class BaseRestController<Entity, PK> {

	/**
	 * 页面列表请求<br/>
	 * 进入Web页面后直接展现第一页数据
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 * @author Mr.Hao
	 * @version 2013-1-15 上午09:56:07
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) throws Exception {
		throw new UnsupportedOperationException("not implement");
	}

	/**
	 * 新增/修改save操作
	 * 
	 * @param model
	 * @param gencode
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author Mr.Hao
	 * @version 2013-01-08 16:36:37
	 */
	@RequestMapping("/save")
	public @ResponseBody
	RWReturnObject create(Model model, Entity entity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		throw new UnsupportedOperationException("not implement");
	}

	/**
	 * 删除单条记录
	 * 
	 * @param id
	 * @return
	 * @author Mr.Hao
	 * @version 2013-1-15 上午10:04:59
	 */
	@RequestMapping(value = "/delete/{id}")
	public @ResponseBody
	RWReturnObject destroy(@PathVariable String id) {
		throw new UnsupportedOperationException("not implement");
	}


	/**
	 * 删除多条记录
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @author Mr.Hao
	 * @version 2013-01-08 16:36:37
	 */
	@RequestMapping("/delete/multiple")
	public @ResponseBody
	RWReturnObject delMultiRecords(HttpServletRequest request, Model model) {
		throw new UnsupportedOperationException("not implement");
	}

	/**
	 * 进入修改页面之前（获取相关对象属性信息）
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @author Mr.Hao
	 * @version 2013-1-15 上午09:57:46
	 */
	@RequestMapping(value = "/{id}/put/pre")
	public String edit(@PathVariable("id") PK id, Model model) {
		throw new UnsupportedOperationException("not implement");
	}

	/**
	 * 依据ID获取该条记录信息（隶属查看操作）
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @author Mr.Hao
	 * @version 2013-1-15 上午09:56:41
	 */
	@RequestMapping(value = "/{id}",method=RequestMethod.POST)
	public String show(@PathVariable("id") PK id, Model model) {
		throw new UnsupportedOperationException("not implement");
	}

	/**
	 * 进入查看页
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}")
	public @ResponseBody Entity show(@PathVariable("id") PK id){
		throw new UnsupportedOperationException("not implement");
	}
	/**
	 * 在方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 * 
	 * @param model
	 * @author Mr.Hao
	 * @version 2013-1-15 上午09:56:20
	 */
	@ModelAttribute("")
	public void init(Model model) {
		model.addAttribute("now", new java.sql.Timestamp(System.currentTimeMillis()));
	}

	/**
	 * 提取前台分页信息
	 * 
	 * @param request
	 * @return
	 * @author Mr.Hao
	 * @version 2013-1-15 上午10:00:01
	 */
	protected PageRequest newPageRequest(HttpServletRequest request) {
		int pageIndex = NumberUtils.toInt(request.getParameter("pageIndex"), 1);
		int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), Integer
				.parseInt(RWAdminConfiguration.getInstance().getAppPropsValue("pagesize", "15")));

		String sortCol = request.getParameter("sortCol");
		String sortOrder = request.getParameter("sortOrder");
		return new PageRequest(pageIndex, pageSize, sortCol, sortOrder);
	}

	// ==========================================================================================================================

	/**
	 * 保存修改</br> 为先前EasyUI框架页兼容过渡，但<font
	 * color="red">涉及frameset框架页的操作请求请不要使用 该方法！</font>
	 * 
	 * @author Mr.Hao
	 * @version 2013-1-15 上午10:07:17
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	RWReturnObject update(@PathVariable("id") PK id, Entity entity) {
		throw new UnsupportedOperationException("not implement");
	}

	/**
	 * 删除</br> 为先前EasyUI框架页兼容过渡，但<font
	 * color="red">涉及frameset框架页的操作请求请不要使用 该方法！</font>
	 * 
	 * @author Mr.Hao
	 * @version 2013-1-15 上午10:07:17
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	RWReturnObject destroyId(@PathVariable("id") PK id) {
		throw new UnsupportedOperationException("not implement");
	}

	/**
	 * 批量删除</br> 为先前EasyUI框架页兼容过渡，但<font
	 * color="red">涉及frameset框架页的操作请求请不要使用 该方法！</font>
	 * 
	 * @author Mr.Hao
	 * @version 2013-1-15 上午10:07:17
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public ModelAndView batchDelete(@RequestParam("items") PK[] items) {
		throw new UnsupportedOperationException("not implement");
	}

	/**
	 * 进入新增页面</br> 为先前EasyUI框架页兼容过渡，但<font
	 * color="red">涉及frameset框架页的操作请求请不要使用 该方法！</font>
	 * 
	 * @author Mr.Hao
	 * @version 2013-1-15 上午10:07:17
	 */
	@RequestMapping(value = "/post/pre")
	public String _new(Model model, HttpServletRequest request) {
		throw new UnsupportedOperationException("not implement");
	}

	/**
	 * 保存新增</br> 为先前EasyUI框架页兼容过渡，但<font
	 * color="red">涉及frameset框架页的操作请求请不要使用 该方法！</font>
	 * 
	 * @author Mr.Hao
	 * @version 2013-1-15 上午10:07:17
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String create(Entity entity, HttpServletRequest request,  Model model) {
		throw new UnsupportedOperationException("not implement");
	}
}
