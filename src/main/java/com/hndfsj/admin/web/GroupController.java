package  com.hndfsj.admin.web;

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

import com.hndfsj.admin.domain.Group;
import com.hndfsj.admin.service.IGroupService;
import com.hndfsj.framework.base.controller.BaseRestJSONController;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.framework.utils.UUIDGenerator;
import com.hndfsj.framework.utils.properties.MessageUtils;

/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:55:58
 * @see com.hndfsj.admin.web.Group
 */
@Controller
@RequestMapping(value="/group")
public class GroupController extends BaseRestJSONController<Group, java.lang.String> {

	static Logger log=LoggerFactory.getLogger(GroupController.class);
	
	@Resource
	private IGroupService groupService;
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(DateUtils.DATETIME_SECOND_FORMAT), true));  
	}

	/**
	* 进入group Web页面后直接展现第一页数据
	*
	* @param request
	* @param model
	* @return
	* @author Mr.Hao<Auto generate>
	* @version  2016-10-21 13:55:58
	*/
	public @ResponseBody MReturnObject list(HttpServletRequest request) throws Exception{
		PageRequest pageRequest = newPageRequest(request);
		return groupService.findPageAll(pageRequest).toMReturnObject();

	}
	
	public @ResponseBody MReturnObject show(@PathVariable("id") java.lang.String id) {
		return new MReturnObject(MReturnObject.SUCCESS, groupService.getById(id));
	}
	
	/**
	* 新增/修改save操作
	*
	* @param model
	* @param group
	* @param request
	* @param response
	* @return
	* @throws Exception
	* @author Mr.Hao<Auto generate>
	* @version  2016-10-21 13:55:58
	*/
	public @ResponseBody MReturnObject create(Model model,Group group,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(StringUtils.isBlank(
				group.getId())
		){// 新增
				group.setId(UUIDGenerator.UUIDValue());
			try {
				groupService.validateEntity(group);
			// group.setId(orig_group.getId());
			// group.setName(orig_group.getName());
			// group.setDescription(orig_group.getDescription());
			// group.setIsValid(orig_group.getIsValid());
				groupService.save(group);
				return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.ADD_SUCCESS);
			} catch (ValidateParamException e) {
				return new MReturnObject(MReturnObject.ERROR, e.getMessage());
			} catch (Exception e) {
			}
			return new MReturnObject(MReturnObject.ERROR, MessageUtils.ADD_FAIL);
		} else {// 修改
			try {
				Group orig_group=groupService.getById(group.getId());
				if(orig_group!=null){
					groupService.validateEntity(group);
					group.setId(orig_group.getId());
					group.setName(orig_group.getName());
					group.setDescription(orig_group.getDescription());
					group.setIsValid(orig_group.getIsValid());
					groupService.update(group);
					return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.EDIT_SUCCESS);
				}
			} catch (Exception e) {
			}
			return new MReturnObject(MReturnObject.WARNING, MessageUtils.EDIT_WARING);
		}
		
	}
	
	
	public @ResponseBody MReturnObject edit(@PathVariable("id") java.lang.String id) {
		return new MReturnObject(MReturnObject.SUCCESS, groupService.getById(id));
	}
	
	public @ResponseBody MReturnObject destroy(@PathVariable java.lang.String id) {
		// 执行删除
		try {
			groupService.deleteById(id);
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
	* @version  2016-10-21 13:55:58
	*/
	public @ResponseBody
	MReturnObject delMultiRecords(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		System.out.println(records);
		String[] rs = records.split(",");
		int i = 0;
		for (String str : rs) {
			try {
				groupService.deleteById(str);
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
