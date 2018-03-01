package  com.hndfsj.app.road.web;

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
import com.hndfsj.app.road.domain.RoadFacilities;
import com.hndfsj.app.road.service.IRoadFacilitiesService;
import com.hndfsj.framework.base.controller.BaseRestJSONController;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.utils.UUIDGenerator;
import com.hndfsj.framework.utils.properties.MessageUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2018-02-26 16:31:04
 * @see com.hndfsj.road.web.RoadFacilities
 */
@Controller
@RequestMapping(value="/roadFacilities")
public class RoadFacilitiesController extends BaseRestJSONController<RoadFacilities, java.lang.String> {

	static Logger log=LoggerFactory.getLogger(RoadFacilitiesController.class);
	
	@Resource
	private IRoadFacilitiesService roadFacilitiesService;
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(DateUtils.DATETIME_SECOND_FORMAT), true));  
	}

	/**
	* 进入roadFacilities 列表
	*
	* @param request
	* @param model
	* @return
	* @author zxj
	* @version  2018-02-26 16:31:04
	*/
	public @ResponseBody MReturnObject list(HttpServletRequest request) throws Exception{
		PageRequest pageRequest = newPageRequest(request);
		return roadFacilitiesService.findPageAll(pageRequest).toMReturnObject();

	}
	
	public @ResponseBody MReturnObject show(@PathVariable("id") java.lang.String id) {
		return new MReturnObject(MReturnObject.SUCCESS, roadFacilitiesService.getById(id));
	}
	
	/**
	* 新增/修改save操作
	*
	* @param model
	* @param roadFacilities
	* @param request
	* @param response
	* @return
	* @throws Exception
	* @author zxj
	* @version  2018-02-26 16:31:04
	*/
	public @ResponseBody MReturnObject create(Model model,RoadFacilities roadFacilities,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(StringUtils.isBlank(
				roadFacilities.getId())
		){// 新增
				roadFacilities.setId(UUIDGenerator.UUIDValue());
			try {
				roadFacilitiesService.validateEntity(roadFacilities);
				roadFacilitiesService.save(roadFacilities);
				return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.ADD_SUCCESS);
			} catch (ValidateParamException e) {
				return new MReturnObject(MReturnObject.ERROR, e.getMessage());
			} catch (Exception e) {
				log.error("",e);
			}
			return new MReturnObject(MReturnObject.ERROR, MessageUtils.ADD_FAIL);
		} else {// 修改
			try {
				RoadFacilities orig_roadFacilities=roadFacilitiesService.getById(roadFacilities.getId());
				if(orig_roadFacilities!=null){
					roadFacilitiesService.validateEntity(roadFacilities);
						roadFacilities.setId(orig_roadFacilities.getId())
					 	                .setRoadId(orig_roadFacilities.getRoadId())
					 	                .setName(orig_roadFacilities.getName())
					 	                .setSign(orig_roadFacilities.getSign());
					roadFacilitiesService.update(roadFacilities);
					return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.EDIT_SUCCESS);
				}
			} catch (ValidateParamException e) {
				return new MReturnObject(MReturnObject.ERROR, e.getMessage());
			} catch (Exception e) {
				log.error("",e);
			}
			return new MReturnObject(MReturnObject.WARNING, MessageUtils.EDIT_WARING);
		}
		
	}
	
	
	public @ResponseBody MReturnObject edit(@PathVariable("id") java.lang.String id) {
		return new MReturnObject(MReturnObject.SUCCESS, roadFacilitiesService.getById(id));
	}
	
	public @ResponseBody MReturnObject destroy(@PathVariable java.lang.String id) {
		// 执行删除
		try {
			roadFacilitiesService.deleteById(id);
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
	* @author zxj
	* @version  2018-02-26 16:31:04
	*/
	public @ResponseBody
	MReturnObject delMultiRecords(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		System.out.println(records);
		String[] rs = records.split(",");
		int i = 0;
		for (String str : rs) {
			try {
				roadFacilitiesService.deleteById(str);
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
