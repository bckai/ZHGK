package com.hndfsj.admin.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hndfsj.admin.domain.Module;
import com.hndfsj.admin.domain.Resource;
import com.hndfsj.admin.service.IModuleService;
import com.hndfsj.admin.service.IResourceService;
import com.hndfsj.framework.base.controller.BaseFrameworkController;
import com.hndfsj.framework.objects.ComboTreeObject;
import com.hndfsj.framework.objects.EasyUI;
import com.hndfsj.framework.objects.RWReturnObject;
import com.hndfsj.framework.objects.TreeObject;
import com.hndfsj.framework.pager.PageModel;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.utils.UUIDGenerator;
import com.hndfsj.framework.utils.properties.MessageUtils;

/**
 * 模块控制器
 * 
 * @author ibm
 * @date May 18, 2010
 */
@Controller
@RequestMapping(value = "/module")
public class ModuleController extends BaseFrameworkController<Module, String>{
	
	@Autowired
	private IModuleService moduleService;
	
	@Autowired
	private IResourceService resourceService;

	/**
	 * 
	 * 模块列表展示
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/get/pre")
	public String index(HttpServletRequest request, Model model) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.setSortcol("sortNo");
		pageRequest.setSortOrder("asc");
		PageModel pageModel = moduleService.findPageAll(pageRequest);
//		model.addAttribute(pageModel);
//		model.addAttribute("page", pageModel);
		
//		List<Module> moduleList = moduleService.findAll();
		List<TreeObject> listTree = EasyUI.genTableTree(pageModel.getData(), "id", "superMod");
		model.addAttribute("listTree", listTree);
		
		return "admin/module/moduleList";
	}

	@Override
	public Module show(@PathVariable("id") String id) {
		Module module = moduleService.getById(id);
		return module;
	}

	/**
	 * 
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	RWReturnObject destroy(@PathVariable("id") String id) {
		// 关联判断
		List<Module> modules = moduleService.getModuleByParentId(id);
		List<Resource> resources = resourceService.getResourceByModuleId(id);
		if (modules.size() > 0 || resources.size() > 0) {
			return new RWReturnObject(RWReturnObject.WARNING, MessageUtils.DELETE_WARNING);
		}

		// 执行删除
		moduleService.deleteById(id);
		return new RWReturnObject(RWReturnObject.SUCCESS, MessageUtils.DELETE_SUCCESS);
	}

	/**
	 * 展示模块新增页面
	 */
	@Override
	public String _new(Model model,HttpServletRequest request) {
		model.addAttribute(new Module());
		model.addAttribute("submitType", "new");
		return "admin/module/moduleCru";
	}

	/**
	 * 展示模块修改页面
	 */
	@Override
	public String edit(@PathVariable("id") String id, Model model) {
		model.addAttribute("module", this.show(id));
		model.addAttribute("submitType", "edit");
		return "admin/module/moduleCru";
	}

	/**
	 * 
	 * 保存数据（新增和修改）
	 * 
	 * @param module
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String create(Module module,HttpServletRequest request, Model model) {
		// 判断是新增和修改
		if (module.getId() != null && !module.getId().equals("")) {
			if (StringUtils.isBlank(module.getSuperMod())) {
				module.setSuperMod(null);
			}
			moduleService.update(module);
		} else {
			module.setId(UUIDGenerator.UUIDValue());
			if (StringUtils.isBlank(module.getSuperMod())) {
				module.setSuperMod(null);
			}
			moduleService.save(module);
		}
		return index(request,model);
	}

	/**
	 * 进入模块对应资源列表页
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}/resource/get/pre")
	public String index(@PathVariable("id") String id, Model model) {
		model.addAttribute("module", show(id));
		List<Resource> listResource = resourceService.getResourceByModuleId(id);
		model.addAttribute("listResource", listResource);
		return "admin/resource/resourceList";
	}
	
	/**
	 * 返回JSON格式的模块下拉树
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/combotree/public", method = RequestMethod.GET)
	public @ResponseBody
	List<ComboTreeObject> combotree(Model model) {
		List<Module> moduleList = moduleService.findNotLeaf();
		List<ComboTreeObject> listTree = EasyUI.genComboTree(moduleList, "id", "name", "superMod");
		listTree.add(0, new ComboTreeObject("", "空"));
		return listTree;
	}

}
