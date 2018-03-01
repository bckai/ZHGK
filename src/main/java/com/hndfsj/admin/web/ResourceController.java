package com.hndfsj.admin.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hndfsj.admin.domain.Module;
import com.hndfsj.admin.domain.Resource;
import com.hndfsj.admin.domain.Role;
import com.hndfsj.admin.domain.User;
import com.hndfsj.admin.service.IModuleService;
import com.hndfsj.admin.service.IResourceService;
import com.hndfsj.admin.service.IRoleService;
import com.hndfsj.admin.service.IUserService;
import com.hndfsj.framework.base.controller.BaseFrameworkController;
import com.hndfsj.framework.objects.RWReturnObject;
import com.hndfsj.framework.utils.UUIDGenerator;
import com.hndfsj.framework.utils.properties.MessageUtils;

/**
 * 资源控制器类
 *
 * @author ibm
 * @date   May 18, 2010
 */
@Controller
@RequestMapping(value = "/resource")
public class ResourceController extends BaseFrameworkController<Resource, String>{
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IModuleService moduleService;
	

	@Override
	public Resource show(@PathVariable("id") String id) {
		Resource resource = resourceService.getById(id);
		return resource;
	}
	
	public String index(String id, Model model){
		model.addAttribute("module",moduleService.getById(id));
		List<Resource> listResource = resourceService.getResourceByModuleId(id);
		model.addAttribute("listResource",listResource);
		return "admin/resource/resourceList";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody RWReturnObject destroy(@PathVariable("id") String id) {
		// 检查资源关联的角色和用户
		List<Role> roles = roleService.getRolesByResId(id);
		List<User> users = userService.getUsersByResId(id);
		if(roles.size() > 0 || users.size() > 0){
			return new RWReturnObject(RWReturnObject.WARNING,MessageUtils.DELETE_WARNING);
		}
		resourceService.deleteById(id);
		return new RWReturnObject(RWReturnObject.SUCCESS,MessageUtils.DELETE_SUCCESS);
	}
	
	
	
	/**
	 * 展示资源新增页面
	 */
	@RequestMapping(value = "{id}/post/pre")
	public String _new(@PathVariable("id") String id,Model model){
		Resource res = new Resource();
		Module module=moduleService.getById(id);
		res.setMethod("get");
		res.setModId(id);
		model.addAttribute("submitType", "new");
		model.addAttribute("module", module);
		model.addAttribute("resource",res);
		return "admin/resource/resourceCru";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String create(Resource resource,Model model) {
		//判断是新增和修改
		if (resource.getId() != null && !resource.getId().equals("")) {
		
			resourceService.update(resource);
		} else {
			resource.setId(UUIDGenerator.UUIDValue());
		
			resourceService.save(resource);
		}
		return index(resource.getModId(),model);
	}
	
	@Override
	public String edit(@PathVariable("id") String id,Model model){
		Resource resource=this.show(id);
		model.addAttribute("resource",resource);
		Module module=moduleService.getById(resource.getModId());
		model.addAttribute("module", module);
		model.addAttribute("submitType", "new");
		return "admin/resource/resourceCru";
	}

	/**
	 * 验证资源信息name是否已经存在
	 * 
	 * @param name
	 * @param resName
	 * @return
	 */
	@RequestMapping(value="/{name}/validator",method= RequestMethod.POST)
 	public @ResponseBody RWReturnObject checkResourceName(@PathVariable("name") String name,@RequestParam("resName") 
 			String resName){
		//是否改动
		RWReturnObject object = new RWReturnObject(RWReturnObject.SUCCESS);
		if(StringUtils.isNotBlank(resName) && resName.equals(name)){
			return object;
		}
		//==是否存在
		if (resourceService.isExist("name",resName)){
			return new RWReturnObject(RWReturnObject.ERROR);
		}
		return object;
	}
	
	
}
