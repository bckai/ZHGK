package com.hndfsj.app.road.web;

import java.util.*;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.app.road.domain.Center;
import com.hndfsj.app.road.service.ICenterService;
import com.hndfsj.framework.base.controller.BaseRestJSONController;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.pager.PageRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version 2018-02-26 16:30:32
 * @see com.hndfsj.road.web.Center
 */
@Controller
@RequestMapping(value = "/center")
public class CenterController extends BaseRestJSONController<Center, java.lang.String> {

	static Logger log = LoggerFactory.getLogger(CenterController.class);

	@Resource
	private ICenterService centerService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat(DateUtils.DATETIME_SECOND_FORMAT), true));
	}

	/**
	 * 进入center 列表
	 *
	 * @param request
	 * @param model
	 * @return
	 * @author zxj
	 * @version 2018-02-26 16:30:32
	 */
	public @ResponseBody MReturnObject list(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		return centerService.findPageAll(pageRequest).toMReturnObject();

	}

	public @ResponseBody MReturnObject show(@PathVariable("id") java.lang.String id) {
		return new MReturnObject(MReturnObject.SUCCESS, centerService.getById(id));
	}

	public @ResponseBody MReturnObject edit(@PathVariable("id") java.lang.String id) {
		return new MReturnObject(MReturnObject.SUCCESS, centerService.getById(id));
	}

}
