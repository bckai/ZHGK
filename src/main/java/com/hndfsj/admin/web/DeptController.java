package com.hndfsj.admin.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hndfsj.admin.domain.Dept;
import com.hndfsj.admin.domain.User;
import com.hndfsj.admin.service.IDeptService;
import com.hndfsj.admin.service.IUserService;
import com.hndfsj.framework.base.controller.BaseFrameworkController;
import com.hndfsj.framework.objects.ComboTreeObject;
import com.hndfsj.framework.objects.EasyUI;
import com.hndfsj.framework.objects.RWReturnObject;
import com.hndfsj.framework.objects.TreeObject;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.security.context.RWAdminContextHolder;

/**
 * 部门控制器类
 * 
 * @author ibm
 * @date May 18, 2010
 */
@Controller
@RequestMapping(value = "/dept")
public class DeptController extends BaseFrameworkController<Dept, String>{

	@Autowired
	private IDeptService deptService;
	@Autowired
	private IUserService userService;

	// ==测试提醒加载
	@RequestMapping(value="/remind/index")
	public String testLoading() {
		return "common/testloading";
	}
	
	@RequestMapping(value = "/get/pre")
	public String index(HttpServletRequest request,Model model){
		PageRequest pageRequest = new PageRequest();
		pageRequest.setSortcol("code");
		pageRequest.setSortOrder("asc");
		List<Dept> deptList = deptService.findAll(pageRequest);
		List<TreeObject> listTree = EasyUI.genTableTree(deptList, "id", "superDep");
		model.addAttribute("listTree", listTree);
		
		return "admin/dept/deptList";
	}
		
	@Override
	public @ResponseBody Dept show(@PathVariable("id") String id) {
		Dept dept = deptService.getById(id);
		return dept;
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody RWReturnObject destroy(@PathVariable("id") String id) {
		//关联判断
		List<Dept> depts = deptService.getDeptBySuperId(id);
		List<User> users = userService.getUsersByDeptId(id);
		if (depts.size() > 0 || users.size() > 0) {
			return new RWReturnObject(RWReturnObject.WARNING,"删除失败");
		}
		
		// 执行删除
		deptService.deleteById(id);
		return new RWReturnObject(RWReturnObject.SUCCESS,"删除成功");
	}
	
	@Override
	public String _new(Model model,HttpServletRequest request){
		model.addAttribute(new Dept());
		model.addAttribute("submitType", "new");
		return "admin/dept/deptCru";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@ModelAttribute("dept") Dept dept,HttpServletRequest request, Model model) {
		if (dept.getId() != null && !dept.getId().equals("")) {
			if (StringUtils.isBlank(dept.getSuperDep())) {
				dept.setSuperDep(null);
			}
			deptService.update(dept);
		} else {
			dept.setId(dept.getCode());
			if (StringUtils.isBlank(dept.getSuperDep())) {
				dept.setSuperDep(null);
			}
			deptService.save(dept);
		}
		return index(request,model);
	}
	
	@Override
	public String edit(@PathVariable("id") String id,Model model){
		List<User> users = userService.getUsersByDeptId(id);
		model.addAttribute("submitType", "edit");
		model.addAttribute("dept",this.show(id));
		model.addAttribute("users", users);
		return "admin/dept/deptCru";
	}

	/**
	 * 返回JSON格式的组织结构下拉树
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/combotree/public", method = RequestMethod.GET)
	public @ResponseBody List<ComboTreeObject> combotree(Model model) {
				PageRequest pageRequest = new PageRequest();
		pageRequest.setSortOrder("asc");
		List<Dept> deptList = deptService.findAll(pageRequest);
		List<ComboTreeObject> listTree = EasyUI.genComboTree(deptList, "id", "name","superDep");
		listTree.add(0, new ComboTreeObject("","选择部门"));
		return listTree;
	}
	
	/**
	 * 返回String格式的组织结构
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jsonStr/public", method = RequestMethod.GET)
	public @ResponseBody
	String jsonStr(Model model, HttpServletRequest request) {
		User user = userService.getById(RWAdminContextHolder.getCurrentUser()
				.getUserId());
		List<Dept> deptList = new ArrayList<Dept>();
		if("0".equals(user.getUserType())){
			deptList=deptService.findAll();
		}else{
			deptList = deptService.getDeptsBySuperId(user.getDeptId(),0);
			deptList.add(0, deptService.getById(user.getDeptId()));
		}
		StringBuffer jsonStr = new StringBuffer("");
		for (Dept dept : deptList) {
			jsonStr.append(";" + dept.getId() + "," + dept.getName());
		}
		return jsonStr.toString().substring(1);
	}
	

	/**
	 * 
	 * 
	 * 分配负责人预处理
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @author lijunwei
	 */
	@RequestMapping(value = "/{id}/deptManager/post/pre")
	public String editManager(@PathVariable("id") String id, Model model) {
		List<User> users = userService.getUsersByDeptId(id);
		model.addAttribute("dept", this.show(id));
		model.addAttribute("users", users);
		return "admin/dept/deptManager";
	}
	/**
	 * 
	 * 
	 * 执行分配负责人
	 * 
	 * @param id
	 * @param dept
	 * @return
	 * @author lijunwei
	 */
	@RequestMapping(value = "/{id}/deptManager", method = RequestMethod.POST)
	public String updateDeptManager(@PathVariable("id") String id,HttpServletRequest request, Dept dept,Model model) {
		dept.setId(id);
		deptService.updateDeptManager(dept);
		return index(request,model);
	}
	
	
	/**
	 * 验证组织结构名称是否存在
	 * 
	 * @param name
	 * @param roleName
	 * @return
	 */
	@RequestMapping(value = "/{deptName}/validator", method = RequestMethod.PUT)
	public @ResponseBody
	RWReturnObject checkUserName(@RequestParam("oldName") String deptName,@RequestParam("name") String name) {

		// 是否改动
		RWReturnObject object = new RWReturnObject(RWReturnObject.SUCCESS);
		if (StringUtils.isNotBlank(deptName) && deptName.equals(name)) {
			return object;
		}
		// 是否存在
		if (deptService.isExist("name", name)) {
			return new RWReturnObject(RWReturnObject.ERROR);
		}
		return object;
	}
	
}
