package com.hndfsj.app.manage.web;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.hndfsj.admin.domain.Role;
import com.hndfsj.admin.domain.User;
import com.hndfsj.admin.service.IDeptService;
import com.hndfsj.admin.service.IRoleService;
import com.hndfsj.admin.service.IUserService;
import com.hndfsj.admin.web.DefaultController;
import com.hndfsj.app.common.enums.ZHGKEnum.ZHGKEnum_Role;
import com.hndfsj.framework.base.controller.BaseRestJSONController;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.config.Configuration;
import com.hndfsj.framework.config.Constants;
import com.hndfsj.framework.config.RWAdminConfiguration;
import com.hndfsj.framework.config.RWAdminConstant;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;
import com.hndfsj.framework.security.bean.RWUserDetails;
import com.hndfsj.framework.security.context.RWAdminContext;
import com.hndfsj.framework.security.context.RWAdminContextHolder;
import com.hndfsj.framework.utils.CryptUtil;
import com.hndfsj.framework.utils.UUIDGenerator;
import com.hndfsj.framework.utils.properties.MessageUtils;

@Controller
public class ManageCotroller extends BaseRestJSONController<User, String> {
	static Logger log = LoggerFactory.getLogger(ManageCotroller.class);

	@Resource
	private IDeptService deptService;
	@Resource
	private IUserService userService;
	@Resource
	private IRoleService roleService;

	@RequestMapping(value = "/user/{deptId}")
	public @ResponseBody MReturnObject userListByDeptId(@PathVariable("deptId") String deptId,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		return new MReturnObject(MReturnObject.SUCCESS, userService.getUsersByDeptId(deptId));
	}

	@RequestMapping(value = "/manage/user/list")
	public @ResponseBody MReturnObject userSelList(HttpServletRequest request, HttpServletResponse response) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.setColumns("id ,realname ,sex ,post ,mobile ,phone,signPath,sort ");
		pageRequest.addAndCondition("username", SearchCondition.NOTEQUAL, "admin");
		pageRequest.addAndCondition("deleted", "=", false);
		pageRequest.addSortConditions("sort", "asc");
		List<User> users = userService.findColumnsAll(pageRequest);
		return new MReturnObject(MReturnObject.SUCCESS, users);
	}
	@RequestMapping(value = "/manage/role/list")
	public @ResponseBody MReturnObject roleList(HttpServletRequest request, HttpServletResponse response) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.addSortConditions("sort", pageRequest.ORDER_ASC);
		pageRequest.addAndCondition("isValid", SearchCondition.EQUAL, true);
		return new MReturnObject(MReturnObject.SUCCESS, roleService.findAll(pageRequest));
	}
	@RequestMapping(value = "/manage/userList")
	public @ResponseBody MReturnObject userList(String name,HttpServletRequest request, HttpServletResponse response) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.addSortConditions("sys_user.id", pageRequest.ORDER_ASC);
		pageRequest.addAndCondition("sys_user.id", SearchCondition.NOTEQUAL, getCurrentMember(request).getUserId());
		pageRequest.addAndCondition("username", SearchCondition.NOTEQUAL, "admin");
		pageRequest.addAndCondition("deleted", SearchCondition.EQUAL, false);
		if(StringUtils.isNotBlank(name)){
			pageRequest.addAndCondition("realname", SearchCondition.LIKE, "%"+name+"%");
		}
		List<User> users = userService.getManagedUser(pageRequest);
		return new MReturnObject(MReturnObject.SUCCESS, users);
	}

	/**
	 * 保存用户/修改用户
	 */
	@RequestMapping(value = "/manage/saveUser", method = RequestMethod.POST)
	public @ResponseBody MReturnObject saveUser(User user, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		try {
			user.setLoginTime(new Date());
			user.setUserType("1");
			if(StringUtils.isBlank(user.getRealname()))throw new ValidateParamException("realname is null");
			if (StringUtils.isNotBlank(user.getId())) {
				userService.updateRoles(user, ZHGKEnum_Role.系统管理员.getId(),"0");
			} else {
				user.setCreateTime(new Date());
				user.setId(UUIDGenerator.UUIDValue());
				String defaultPass = RWAdminConfiguration.getInstance()
						.getAppPropsValue(RWAdminConstant.DEFAULT_PASSWORD);
				user.setPassword(CryptUtil.cryptByConfig(defaultPass));
				userService.saveRole(user, ZHGKEnum_Role.普通用户.getId(),"0");
			}
			return new MReturnObject(MReturnObject.SUCCESS,user);
		} catch (ValidateParamException ex) {
			log.error("", ex);
			return new MReturnObject(MReturnObject.ERROR, ex.getMessage());
		} catch (Exception exception) {
			log.error("", exception);
		}

		return new MReturnObject(MReturnObject.ERROR, MessageUtils.EDIT_WARING);
	}

	@RequestMapping(value = "/manage/deleteUser/{id}")
	public @ResponseBody MReturnObject deleteUser(HttpServletRequest request, @PathVariable java.lang.String id) {
		// 执行删除
		try {
			// RWUserDetails currentUser = getCurrentMember(request);
			//if (canDeleteMember(null, id)) {
				// TODO 查询是否是有角色帐号
				User user=userService.getById(id);
				if(user!=null&&!"admin".equals(user.getUsername())){
					user.setIsValid("0");
					user.setDeleted(true);
					userService.update(user);
					return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.DELETE_SUCCESS);
				}
			//}
			//return new MReturnObject(MReturnObject.ERROR, "该用户拥有角色，严禁删除！");
		} catch (Exception e) {
			log.error("", e);
			// TODO 检查关联状态
		}
		return new MReturnObject(MReturnObject.WARNING, MessageUtils.DELETE_WARNING);
	}

	private boolean canDeleteMember(List<Role> list, String id) {
		List<Role> deleted_user = roleService.getRoleAssignByUserId(id);
		if (deleted_user.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 重置密码
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/manage/{id}/password/reset")
	public @ResponseBody MReturnObject resetPassword(@PathVariable("id") String id, HttpServletRequest request,
			Model model) {
		String defaultPass = RWAdminConfiguration.getInstance().getAppPropsValue(RWAdminConstant.DEFAULT_PASSWORD);
		userService.updateUserPassword(id, defaultPass);
		return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.PASSWORD_EDIT_SUCCESS);
	}

	/**
	 * 保存密码修改
	 * 
	 * @param oldpassword
	 * @param newpassword
	 * @return
	 */
	@RequestMapping(value = "/user/msg/password/update", method = RequestMethod.POST)
	public @ResponseBody MReturnObject updatePassword(@RequestParam("oldpassword") String oldpassword,
			@RequestParam("newpassword") String newpassword,HttpServletRequest request) {
		User user = userService.getById(RWAdminContextHolder.getCurrentUser().getUserId());
		if (!user.getPassword().equals(CryptUtil.encoderByMd5For32Bit(oldpassword))) {
			return new MReturnObject(MReturnObject.ERROR, MessageUtils.PASSWORD_ERROR);
		}
		userService.updateUserPassword(user.getId(), newpassword);
		RWAdminContextHolder.cleanContext();
		request.getSession().removeAttribute(RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION);
		request.getSession().invalidate();
		request.getSession();
		return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.PASSWORD_EDIT_SUCCESS);
	}
	/**
	 * 启用
	 * 
	 * @param oldpassword
	 * @param newpassword
	 * @param confirmpassword
	 * @return
	 */
	@RequestMapping(value = "/manage/enable/{id}", method = RequestMethod.GET)
	public @ResponseBody MReturnObject enable(@PathVariable String id,HttpServletRequest request) {
		User user = userService.getById(id);
		if (user!=null) {
			user.setIsValid("1");
			userService.update(user);
			return new MReturnObject(MReturnObject.SUCCESS, "启用用户成功！");
		}
		return new MReturnObject(MReturnObject.ERROR, "启用用户失败！");
	}
	
	/**
	 * 禁用
	 * 
	 * @param oldpassword
	 * @param newpassword
	 * @param confirmpassword
	 * @return
	 */
	@RequestMapping(value = "/manage/disable/{id}", method = RequestMethod.GET)
	public @ResponseBody MReturnObject disable(@PathVariable String id,HttpServletRequest request) {
		User user = userService.getById(id);
		if (user!=null) {
			user.setIsValid("0");
			userService.update(user);
			return new MReturnObject(MReturnObject.SUCCESS, "禁用用户成功！");
		}
		return new MReturnObject(MReturnObject.ERROR, "禁用用户失败！");
	}
	
	/**
	 * 保存用户/修改用户
	 */
	@RequestMapping(value = "/user/msg/save", method = RequestMethod.POST)
	public @ResponseBody MReturnObject edit(User user, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		try {
			RWUserDetails memberDetails = getCurrentMember(request);
			User ori_user = userService.getById(memberDetails.getUserId());
			ori_user.setAddress(user.getAddress());
			ori_user.setEmail(user.getEmail());
			ori_user.setMobile(user.getMobile());
			ori_user.setPhone(user.getPhone());
			ori_user.setRealname(user.getRealname());
			ori_user.setSex(user.getSex());
			ori_user.setPost(user.getPost());
			userService.update(user);
			return new MReturnObject(MReturnObject.SUCCESS);
		} catch (ValidateParamException ex) {
			log.error("", ex);
			return new MReturnObject(MReturnObject.ERROR, ex.getMessage());
		} catch (Exception exception) {
			log.error("", exception);
		}

		return new MReturnObject(MReturnObject.ERROR, MessageUtils.EDIT_WARING);
	}

	@RequestMapping(value = "/user/msg/upload/headimg", method = RequestMethod.POST)
	public @ResponseBody MReturnObject uploadHeadimg(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RWUserDetails memberDetails = getCurrentMember(request);
		if (memberDetails != null && request instanceof DefaultMultipartHttpServletRequest) {// 新增
			try {
				uploadMul((DefaultMultipartHttpServletRequest) request, memberDetails.getUserId());
				User user=userService.getRoleByUserId(memberDetails.getUserId());
				DefaultController.loadUser(user);
				return new MReturnObject(MReturnObject.SUCCESS,user );
			} catch (IllegalStateException | IOException e) {
			}
		}
		return new MReturnObject(MReturnObject.ERROR, MessageUtils.ADD_FAIL);
	}
	@RequestMapping(value = "/manage/user/{id}/upload/headimg", method = RequestMethod.POST)
	public @ResponseBody MReturnObject uploadUserHeadimg(@PathVariable("id")String id,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			User user=userService.getById(id);
		if (user != null && request instanceof DefaultMultipartHttpServletRequest) {// 新增
			try {
				uploadMul((DefaultMultipartHttpServletRequest) request, user.getId());
				return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.ADD_SUCCESS);
			} catch (IllegalStateException | IOException e) {
			}
		}
		return new MReturnObject(MReturnObject.ERROR, MessageUtils.ADD_FAIL);
	}

	private void uploadMul(DefaultMultipartHttpServletRequest request, String memberId)
			throws IllegalStateException, IOException {
		String headImg = "";
		MultiValueMap<String, MultipartFile> mFiles = request.getMultiFileMap();
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp,tif,tiff,jpe");
		for (String key : mFiles.keySet()) {
			List<MultipartFile> list = mFiles.get(key);
			for (int i = 0; i < list.size();) {
				MultipartFile mf = list.get(i);
				String fileName = mf.getOriginalFilename();
				// 检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase().replace("blob", "jpg");
				if (Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt)) {
					fileName = new Date().getTime() + "." + fileExt;// rename
					headImg += Constants.LOGINDOWNLOAD + memberId + "/" + fileName;
				}
				String tempPath = Configuration.getInstance().getAppPropsValue("file.attach.dir") + "/" + memberId;
				File tempFile = new File(tempPath);
				if (!tempFile.exists()) {
					tempFile.mkdirs();
				}
				mf.transferTo(new File(tempPath, fileName));
				return;// 取一张
			}
		}
	}

	protected RWUserDetails getCurrentMember(HttpServletRequest request) {
		// 获取当前用户信息
		RWAdminContext context = (RWAdminContext) request.getSession()
				.getAttribute(RWAdminConstant.RW_ADMIN_CONTEXT_IN_SESSION);
		RWUserDetails memberDetails = context.getCurrentUser();
		return memberDetails;
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
		int pageSize = NumberUtils.toInt(request.getParameter("pageSize"),
				Integer.parseInt(RWAdminConfiguration.getInstance().getAppPropsValue("pagesize", "15")));

		String sortCol = request.getParameter("sortCol");
		String sortOrder = request.getParameter("sortOrder");
		return new PageRequest(pageIndex, pageSize, sortCol, sortOrder);
	}

}
