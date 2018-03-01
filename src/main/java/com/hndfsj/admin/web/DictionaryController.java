package com.hndfsj.admin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hndfsj.admin.domain.Dictionary;
import com.hndfsj.admin.service.IDeptService;
import com.hndfsj.admin.service.IDictionaryService;
import com.hndfsj.admin.service.IUserService;
import com.hndfsj.app.trafficevent.domain.TrafficEvent;
import com.hndfsj.framework.base.controller.BaseRestController;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.objects.RWReturnObject;
import com.hndfsj.framework.pager.PageModel;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;
import com.hndfsj.framework.utils.properties.MessageUtils;
@Controller
@RequestMapping("/dictionary")
public class DictionaryController extends BaseRestController<Dictionary, java.lang.String>{
	
	@Autowired
	private IDictionaryService dictionaryService;
	@Autowired
	IDeptService deptService;

	@Autowired
	IUserService userService;
	/**
	*
	* @param request
	* @param model
	* @return
	* @author liyua
	*/
	@RequestMapping(value = "/get/pre")
	public String index(HttpServletRequest request, Model model) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.addAndCondition("dicType", SearchCondition.EQUAL, "3");
		PageModel pageModel = dictionaryService.findPageAll(pageRequest);
		model.addAttribute("page", pageModel);
		return "admin/dictionary/dicList";
	}
	@RequestMapping(value = "/messagelist")
	public String messagelist(HttpServletRequest request, Model model) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.addSortConditions(Dictionary.DICTYPE , PageRequest.ORDER_DESC);
		PageModel pageModel = dictionaryService.findPageAll(pageRequest);
		model.addAttribute("page", pageModel);
		return "admin/dictionary/dicMessageList";
	}
	
	//获得交通事件类型列表
	@RequestMapping(value = "/eventList")
	public @ResponseBody MReturnObject getEventList(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		pageRequest.addAndCondition(Dictionary.DICTYPE, SearchCondition.EQUAL, "INCIDENT_TYPE");
		return dictionaryService.findPageAll(pageRequest).toMReturnObject();

	}
	//获得交通事件类型列表
	@RequestMapping(value = "/event")
	public @ResponseBody MReturnObject getEvent(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		pageRequest.addAndCondition(Dictionary.DICTYPE, SearchCondition.EQUAL, "INCIDENT");
		return dictionaryService.findPageAll(pageRequest).toMReturnObject();

	}
		
	@Override
	public Dictionary show(@PathVariable("id") String id) {
		Dictionary dictionart = dictionaryService.getById(id);
		return dictionart;
	}
	
	@Override
	public String _new(Model model,HttpServletRequest request) {
		model.addAttribute("dictionary", new Dictionary());
		return "admin/dictionary/dicCru";
	}
	
	@Override
	public String edit(@PathVariable("id") String id, Model model) {
		model.addAttribute("dictionary", dictionaryService.getById(id));
		return "admin/dictionary/dicCru";
	}
	
	
	/**
	 * 
	 * 保存用户信息
	 * 
	 * @param user
	 * @param request
	 * @param model
	 * @return
	 */
	@Override
	public String create(Dictionary dictionary, HttpServletRequest request,
			Model model) {
		if(dictionary.getCode() != null && !dictionary.getCode().equals("")){
			//数据字典已经存在，进行修改
			Dictionary dictionaryOld=dictionaryService.getById(dictionary.getCode());
			dictionaryOld.setValue(dictionary.getValue());
			dictionaryService.update(dictionaryOld);
			if("11".equals(dictionaryOld.getDicType())){
				return messagelist(request, model);
			}
		}else{
			//新增数据字典数据
			dictionaryService.save(dictionary);
		}
		return index(request, model);
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public @ResponseBody RWReturnObject editDic(Dictionary dictionary, HttpServletRequest request,HttpServletResponse response) {
		if(dictionary.getCode() != null && !dictionary.getCode().equals("")){
			//数据字典已经存在，进行修改
			Dictionary dictionaryOld=dictionaryService.getById(dictionary.getCode());
			dictionaryOld.setValue(dictionary.getValue());
			dictionaryService.update(dictionaryOld);
			
		}else{
			//新增数据字典数据
			dictionaryService.save(dictionary);
		}
		return new RWReturnObject(RWReturnObject.SUCCESS,"操作成功");
	}
	

	
	@RequestMapping(value = "/delete/{id}")
	public @ResponseBody RWReturnObject destroy(@PathVariable java.lang.String id) {
		// 执行删除
		try {
			dictionaryService.deleteById(id);
			return new RWReturnObject(RWReturnObject.SUCCESS, MessageUtils.DELETE_SUCCESS);
		} catch (Exception e) {
			// TODO 检查关联状态
		}
		return new RWReturnObject(RWReturnObject.WARNING, MessageUtils.DELETE_WARNING);
	}
	
	/**
	* 删除多条记录
	*
	* @param request
	* @param model
	* @return
	*/
	@RequestMapping("/delMulti")
	public @ResponseBody
	RWReturnObject delMultiRecords(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		System.out.println(records);
		String[] rs = records.split(",");
		int i = 0;
		for (String str : rs) {
			try {
				dictionaryService.deleteById(str);
			} catch (Exception e) {
				if (i > 0) {
					return new RWReturnObject(RWReturnObject.ERROR, MessageUtils.DELETE_ALL_WARNING);
				}
				return new RWReturnObject(RWReturnObject.ERROR, MessageUtils.DELETE_ALL_FAIL);
			}
			i++;
		}
		return new RWReturnObject(RWReturnObject.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);
	}
}
