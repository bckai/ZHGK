package  com.hndfsj.app.device.web;

import java.util.*;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hndfsj.framework.exceptions.ValidateParamException;
import org.apache.commons.lang.StringUtils;

import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.framework.base.controller.BaseRestJSONController;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;
import com.hndfsj.framework.utils.UUIDGenerator;
import com.hndfsj.framework.utils.properties.MessageUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hndfsj.app.device.domain.CmsShowlist;
import com.hndfsj.app.device.service.ICmsShowlistService;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-11 15:15:20
 * @see com.hndfsj.app.device.web.CmsShowlist
 */
@Controller
@RequestMapping(value="/cmsShowlist")
public class CmsShowlistController extends BaseRestJSONController<CmsShowlist, java.lang.String> {

	static Logger log=LoggerFactory.getLogger(CmsShowlistController.class);
	
	@Resource
	private ICmsShowlistService cmsShowlistService;
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(DateUtils.DATETIME_SECOND_FORMAT), true));  
	}

	/**
	* 进入cmsShowlist 列表
	*
	* @param request
	* @param model
	* @return
	* @author zxj
	* @version  2017-09-11 15:15:20
	*/
	@RequestMapping("/{dvcType}/list")
	public @ResponseBody MReturnObject list(HttpServletRequest request,@PathVariable String dvcType) throws Exception{
		PageRequest pageRequest = newPageRequest(request);
		pageRequest.addAndCondition(CmsShowlist.DVC_TYPE, SearchCondition.EQUAL, dvcType);
		return cmsShowlistService.findPageAll(pageRequest).toMReturnObject();
	}
	
	/**
	* 新增/修改save操作
	*
	* @param model
	* @param cmsShowlist
	* @param request
	* @param response
	* @return
	* @throws Exception
	* @author zxj
	* @version  2017-09-11 15:15:20
	*/
	public @ResponseBody MReturnObject create(Model model,CmsShowlist cmsShowlist,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(StringUtils.isBlank(
				cmsShowlist.getId())
		){// 新增
				cmsShowlist.setId(UUIDGenerator.UUIDValue());
			try {
				cmsShowlistService.validateEntity(cmsShowlist);
				cmsShowlist.setCreateTime(new Date());
				cmsShowlistService.save(cmsShowlist);
				return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.ADD_SUCCESS);
			} catch (ValidateParamException e) {
				return new MReturnObject(MReturnObject.ERROR, e.getMessage());
			} catch (Exception e) {
				log.error("",e);
			}
			return new MReturnObject(MReturnObject.ERROR, MessageUtils.ADD_FAIL);
		} else {// 修改
			try {
				CmsShowlist orig_cmsShowlist=cmsShowlistService.getById(cmsShowlist.getId());
				if(orig_cmsShowlist!=null){
					cmsShowlistService.validateEntity(cmsShowlist);
					cmsShowlist.setDvcType(orig_cmsShowlist.getDvcType());
					cmsShowlist.setCreateTime(orig_cmsShowlist.getCreateTime());
					cmsShowlist.setModifyTime(new Date());
					cmsShowlistService.update(cmsShowlist);
					return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.EDIT_SUCCESS);
				}
			} catch (Exception e) {
				log.error("",e);
			}
			return new MReturnObject(MReturnObject.WARNING, MessageUtils.EDIT_WARING);
		}
		
	}
	
	public @ResponseBody MReturnObject destroy(@PathVariable java.lang.String id) {
		// 执行删除
		try {
			cmsShowlistService.deleteById(id);
			return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.DELETE_SUCCESS);
		} catch (Exception e) {
			// TODO 检查关联状态
		}
		return new MReturnObject(MReturnObject.WARNING, MessageUtils.DELETE_WARNING);
	}
}
