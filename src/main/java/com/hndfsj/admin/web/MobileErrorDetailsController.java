package com.hndfsj.admin.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hndfsj.admin.domain.MobileErrorDetails;
import com.hndfsj.admin.service.IMobileErrorDetailsService;
import com.hndfsj.framework.base.controller.BaseRestJSONController;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.framework.utils.UUIDGenerator;
import com.hndfsj.framework.utils.properties.MessageUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version 2015-11-30 09:59:40
 * @see com.hndfsj.common.web.MobileErrorDetails
 */
@Controller
@RequestMapping(value = "/common/mobile/error")
public class MobileErrorDetailsController extends
		BaseRestJSONController<MobileErrorDetails, java.lang.String> {

	static Logger log = LoggerFactory.getLogger(MobileErrorDetailsController.class);

	@Resource
	private IMobileErrorDetailsService mobileErrorDetailsService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(
				DateUtils.DATETIME_SECOND_FORMAT), true));
	}

	/**
	 * 进入mobileErrorDetails Web页面后直接展现第一页数据
	 *
	 * @param request
	 * @param model
	 * @return
	 * @author Mr.Hao<Auto generate>
	 * @version 2015-11-30 09:59:40
	 */
	public @ResponseBody MReturnObject list(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		return mobileErrorDetailsService.findPageAll(pageRequest).toMReturnObject();

	}

	public @ResponseBody MReturnObject show(@PathVariable("id") java.lang.String id) {
		return new MReturnObject(MReturnObject.SUCCESS, mobileErrorDetailsService.getById(id));
	}

	/**
	 * 新增/修改save操作
	 *
	 * @param model
	 * @param mobileErrorDetails
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author Mr.Hao<Auto generate>
	 * @version 2015-11-30 09:59:40
	 */
	public @ResponseBody MReturnObject create(Model model, MobileErrorDetails mobileErrorDetails,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			mobileErrorDetails.setId(UUIDGenerator.UUIDValue());
			mobileErrorDetails.setCreateDate(new Date());
			mobileErrorDetailsService.save(mobileErrorDetails);
			return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.ADD_SUCCESS);
		} catch (ValidateParamException e) {
			return new MReturnObject(MReturnObject.ERROR, e.getMessage());
		} catch (Exception e) {
		}
		return new MReturnObject(MReturnObject.ERROR, MessageUtils.ADD_FAIL);

	}

	public @ResponseBody MReturnObject edit(@PathVariable("id") java.lang.String id) {
		return new MReturnObject(MReturnObject.SUCCESS, mobileErrorDetailsService.getById(id));
	}

	public @ResponseBody MReturnObject destroy(@PathVariable java.lang.String id) {
		// 执行删除
		try {
			mobileErrorDetailsService.deleteById(id);
			return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.DELETE_SUCCESS);
		} catch (Exception e) {
			// TODO 检查关联状态
		}
		return new MReturnObject(MReturnObject.WARNING, MessageUtils.DELETE_WARNING);
	}

	/**
	 * 删除多条记录
	 *
	 * @param request
	 * @param model
	 * @return
	 * @author Mr.Hao<Auto generate>
	 * @version 2015-11-30 09:59:40
	 */
	public @ResponseBody MReturnObject delMultiRecords(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		System.out.println(records);
		String[] rs = records.split(",");
		int i = 0;
		for (String str : rs) {
			try {
				mobileErrorDetailsService.deleteById(str);
			} catch (Exception e) {
				if (i > 0) {
					return new MReturnObject(MReturnObject.ERROR, MessageUtils.DELETE_ALL_WARNING);
				}
				return new MReturnObject(MReturnObject.ERROR, MessageUtils.DELETE_ALL_FAIL);
			}
			i++;
		}
		return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);
	}

}
