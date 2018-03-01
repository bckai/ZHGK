package com.hndfsj.admin.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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

import com.hndfsj.admin.domain.User;
import com.hndfsj.admin.service.IRoleService;
import com.hndfsj.admin.service.IUserService;
import com.hndfsj.framework.base.controller.BaseRestJSONController;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageModel;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.framework.utils.UUIDGenerator;
import com.hndfsj.framework.utils.properties.MessageUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version 2016-10-21 15:14:57
 * @see com.hndfsj.admin.web.User
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseRestJSONController<User, java.lang.String> {

	static Logger log = LoggerFactory.getLogger(UserController.class);

	@Resource
	private IUserService userService;
	@Resource
	private IRoleService roleService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat(DateUtils.DATETIME_SECOND_FORMAT), true));
	}

	/**
	 * 进入user Web页面后直接展现第一页数据
	 *
	 * @param request
	 * @param model
	 * @return
	 * @author Mr.Hao<Auto generate>
	 * @version 2016-10-21 15:14:57
	 */
	public @ResponseBody MReturnObject list(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		return userService.findPageAll(pageRequest).toMReturnObject();

	}

	// @GetMapping("/list")
	@RequestMapping("/page/list")
	public String list(HttpServletRequest request, Model model) throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		PageModel pageModel = userService.findPageAll(pageRequest);
		model.addAttribute(pageModel);
		model.addAttribute("page", pageModel);
		return "admin/user/userList";
	}

	@RequestMapping(value = "/details/{id}")
	public String list(@PathVariable("id") java.lang.String id, Model model) throws Exception {
		if (!"new".equals(id)) {
			model.addAttribute("data", userService.getById(id));
		}
		return "admin/user/frag";
	}

	public @ResponseBody MReturnObject show(@PathVariable("id") java.lang.String id) {
		return new MReturnObject(MReturnObject.SUCCESS, userService.getById(id));
	}

	/**
	 * 新增/修改save操作
	 *
	 * @param model
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author Mr.Hao<Auto generate>
	 * @version 2016-10-21 15:14:57
	 */
	public @ResponseBody MReturnObject create(Model model, User user, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (StringUtils.isBlank(user.getId())) {// 新增
			user.setId(UUIDGenerator.UUIDValue());
			try {
				userService.validateEntity(user);
				// user.setUsername(orig_user.getUsername());
				// user.setPassword(orig_user.getPassword());
				// user.setRealname(orig_user.getRealname());
				// user.setUserType(orig_user.getUserType());
				// user.setSignPath(orig_user.getSignPath());
				userService.save(user);
				return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.ADD_SUCCESS);
			} catch (ValidateParamException e) {
				return new MReturnObject(MReturnObject.ERROR, e.getMessage());
			} catch (Exception e) {
			}
			return new MReturnObject(MReturnObject.ERROR, MessageUtils.ADD_FAIL);
		} else {// 修改
			try {
				User orig_user = userService.getById(user.getId());
				if (orig_user != null) {
					userService.validateEntity(user);
					user.setUsername(orig_user.getUsername());
					user.setRealname(orig_user.getRealname());
					user.setSex(orig_user.getSex());
					user.setPost(orig_user.getPost());
					user.setMobile(orig_user.getMobile());
					user.setPhone(orig_user.getPhone());
					user.setEmail(orig_user.getEmail());
					user.setAddress(orig_user.getAddress());
					user.setUserType(orig_user.getUserType());
					user.setIsValid(orig_user.getIsValid());
					user.setDeptId(orig_user.getDeptId());
					user.setLoginTime(orig_user.getLoginTime());
					user.setSignPath(orig_user.getSignPath());
					userService.update(user);
					return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.EDIT_SUCCESS);
				}
			} catch (Exception e) {
			}
			return new MReturnObject(MReturnObject.WARNING, MessageUtils.EDIT_WARING);
		}

	}

	public @ResponseBody MReturnObject edit(@PathVariable("id") java.lang.String id) {
		return new MReturnObject(MReturnObject.SUCCESS, userService.getById(id));
	}

	public @ResponseBody MReturnObject destroy(@PathVariable java.lang.String id) {
		// 执行删除
		try {
			userService.deleteById(id);
			return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.DELETE_SUCCESS);
		} catch (Exception e) {
			// TODO 检查关联状态
			System.err.println(e);
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
	 * @version 2016-10-21 15:14:57
	 */
	public @ResponseBody MReturnObject delMultiRecords(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		System.out.println(records);
		String[] rs = records.split(",");
		int i = 0;
		for (String str : rs) {
			try {
//				userService.deleteById(str);
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
