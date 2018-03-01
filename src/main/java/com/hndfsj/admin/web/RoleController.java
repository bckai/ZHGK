package com.hndfsj.admin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hndfsj.admin.domain.Module;
import com.hndfsj.admin.domain.Resource;
import com.hndfsj.admin.domain.Role;
import com.hndfsj.admin.domain.User;
import com.hndfsj.admin.service.IModuleService;
import com.hndfsj.admin.service.IResourceService;
import com.hndfsj.admin.service.IRoleService;
import com.hndfsj.admin.service.IUserService;
import com.hndfsj.framework.base.controller.BaseFrameworkController;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.intercepters.RequestIntercepter;
import com.hndfsj.framework.objects.ComboTreeObject;
import com.hndfsj.framework.objects.EasyUI;
import com.hndfsj.framework.objects.RWRequest;
import com.hndfsj.framework.objects.RWReturnObject;
import com.hndfsj.framework.pager.PageModel;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.security.bean.RWUserDetails;
import com.hndfsj.framework.security.context.RWAdminContextHolder;
import com.hndfsj.framework.utils.UUIDGenerator;

/**
 * 角色控制器
 *
 * @author ibm
 * @date   May 18, 2010
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseFrameworkController<Role, String>{
	static Logger log=LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IModuleService moduleService;
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IUserService userService;

	/**
	 * 
	 * 初始化用户列表页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/get/pre")
	public String index(HttpServletRequest request, Model model) {
		PageRequest pageRequest = newPageRequest(request);
		PageModel pageModel = roleService.findPageAll(pageRequest);
		model.addAttribute(pageModel);
		model.addAttribute("page", pageModel);
		return "admin/role/roleList";
	}
	
	
	/**
	 * 进入查看
	 */
	@Override
	public Role show(@PathVariable("id") String id) {
		Role role = roleService.getById(id);
		return role;
	}
	
	
	/**
	 * 进入新增
	 */
	@Override
	public String _new(Model model,HttpServletRequest request){
		model.addAttribute(new Role());
		return "admin/role/roleCru";
	}

	/**
	 * 保存角色信息
	 * @param entity
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String create(Role role, HttpServletRequest request, Model model) {
		if(role.getId() != null && !role.getId().equals("")){
			roleService.update(role);
		}else{
			role.setId(UUIDGenerator.UUIDValue());
			roleService.save(role);
		}
		return index(request, model);
	}
	
	/**
	 * 进入修改
	 */
	@Override
	public String edit(@PathVariable("id") String id,Model model){
		model.addAttribute("role",this.show(id));
		return "admin/role/roleCru";
	}
	
	
	/**
	 * 删除角色
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public  @ResponseBody RWReturnObject destroy(@PathVariable("id") String id, HttpServletRequest request, Model model){
		//查看关联情况
		List<Resource> resources = resourceService.getResourceByRoleId(id);
		List<User> users = userService.getUsersByRoleId(id);
		if(resources.size() > 0 || users.size() > 0){
			return new RWReturnObject(RWReturnObject.WARNING,"失败");
		}
		//执行删除
		roleService.deleteById(id);
		return new RWReturnObject(RWReturnObject.SUCCESS,"成功");
		//return index(request, model);
	}
	
	/**
	 * 进入角色分配资源页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{id}/resource/assign/post/pre")
	public String assignResourceToRolePage(@PathVariable("id") String id, Model model) throws IOException{
		List<Resource> listResourceRole = resourceService.getResourceByRoleId(id);
		List<Module> listModule = moduleService.getAllModuleResource();
		List<ComboTreeObject> listTree = EasyUI.genModuleResourceTree(listModule, listResourceRole);
		model.addAttribute("resTreeData", JSON.toJSONString(listTree));
		model.addAttribute("role", show(id));
		return "admin/role/resAssign";
	}
	
	
	
	
	
	/**
	 * 生成模块资源分配的JSON树
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/resource/tree/public")
	public @ResponseBody List<ComboTreeObject> tree(@PathVariable("id") String id){
		List<Resource> listResourceRole = resourceService.getResourceByRoleId(id);
		List<Module> listModule = moduleService.getAllModuleResource();
		List<ComboTreeObject> listTree = EasyUI.genModuleResourceTree(listModule, listResourceRole);
		return listTree;
	}
	
	
	
	
	/**
	 * 角色分配资源保存
	 * 
	 * @param id
	 * @param resourceIds
	 * @return
	 */
	@RequestMapping(value="/{id}/resource/assign",method=RequestMethod.POST)
	public @ResponseBody RWReturnObject assignResourceToRole(@PathVariable("id") String id,@RequestParam("resourceIds") String resourceIds){
		roleService.insertRoleResource(id,resourceIds);
		return new RWReturnObject(RWReturnObject.SUCCESS,"角色分配成功");
	
	}
	
	/**
	 * 验证角色账号是否存在
	 * 
	 * @param name
	 * @param roleName
	 * @return
	 */
	@RequestMapping(value = "/{roleName}/validator", method = RequestMethod.PUT)
	public @ResponseBody
	RWReturnObject checkUserName(@PathVariable("roleName") String roleName,@RequestParam("name") String name) {

		// 是否改动
		RWReturnObject object = new RWReturnObject(RWReturnObject.SUCCESS);
		if (StringUtils.isNotBlank(roleName) && roleName.equals(name)) {
			return object;
		}
		// 是否存在
		if (roleService.isExist("name", name)) {
			return new RWReturnObject(RWReturnObject.ERROR);
		}
		return object;
	}
	
	/**
	 * url 权限验证
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody MReturnObject validate(
			@RequestParam("urls") String content,HttpServletRequest request) {
		try{
			List<RWRequest> urls=JSON.parseArray(content, RWRequest.class);
			RWUserDetails currentUserDetails = RWAdminContextHolder.getCurrentUser();
			String reuslt="";
			for(RWRequest rwRequest:urls){
				if (RequestIntercepter.commonNonNeedFilter(rwRequest)) {
					reuslt+= true+",";
				} else {
					if (RequestIntercepter.nonNeedFilter(rwRequest, currentUserDetails.getUserType())) {
						reuslt+= true+",";
//					}else if(RequestIntercepter.resourceAuthentication(rwRequest, currentUserDetails)){
//						reuslt+= true+",";
					}else{
						reuslt+= false+",";
					}
				}
			}
			return new MReturnObject(MReturnObject.SUCCESS,reuslt);
			
		}catch (Exception e) {
			log.error(content,e);
		}
		return new MReturnObject(MReturnObject.ERROR,"请求数据非法");
	}
	
}
