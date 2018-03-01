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
import com.hndfsj.app.road.domain.Road;
import com.hndfsj.app.road.service.IRoadService;
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
 * @version  2018-02-26 16:31:12
 * @see com.hndfsj.road.web.Road
 */
@Controller
@RequestMapping(value="/road")
public class RoadController extends BaseRestJSONController<Road, java.lang.String> {

	static Logger log=LoggerFactory.getLogger(RoadController.class);
	
	@Resource
	private IRoadService roadService;
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(DateUtils.DATETIME_SECOND_FORMAT), true));  
	}

	/**
	* 进入road 列表
	*
	* @param request
	* @param model
	* @return
	* @author zxj
	* @version  2018-02-26 16:31:12
	*/
	public @ResponseBody MReturnObject list(HttpServletRequest request) throws Exception{
		PageRequest pageRequest = newPageRequest(request);
		return roadService.findPageAll(pageRequest).toMReturnObject();

	}
	
	public @ResponseBody MReturnObject show(@PathVariable("id") java.lang.String id) {
		return new MReturnObject(MReturnObject.SUCCESS, roadService.getById(id));
	}
	
	/**
	* 新增/修改save操作
	*
	* @param model
	* @param road
	* @param request
	* @param response
	* @return
	* @throws Exception
	* @author zxj
	* @version  2018-02-26 16:31:12
	*/
	public @ResponseBody MReturnObject create(Model model,Road road,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(StringUtils.isBlank(
				road.getId())
		){// 新增
				road.setId(UUIDGenerator.UUIDValue());
			try {
				roadService.validateEntity(road);
				roadService.save(road);
				return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.ADD_SUCCESS);
			} catch (ValidateParamException e) {
				return new MReturnObject(MReturnObject.ERROR, e.getMessage());
			} catch (Exception e) {
				log.error("",e);
			}
			return new MReturnObject(MReturnObject.ERROR, MessageUtils.ADD_FAIL);
		} else {// 修改
			try {
				Road orig_road=roadService.getById(road.getId());
				if(orig_road!=null){
					roadService.validateEntity(road);
						road.setId(orig_road.getId())
					 	                .setName(orig_road.getName())
					 	                .setStart(orig_road.getStart())
					 	                .setEnd(orig_road.getEnd());
					roadService.update(road);
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
		return new MReturnObject(MReturnObject.SUCCESS, roadService.getById(id));
	}
	
	public @ResponseBody MReturnObject destroy(@PathVariable java.lang.String id) {
		// 执行删除
		try {
			roadService.deleteById(id);
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
	* @version  2018-02-26 16:31:12
	*/
	public @ResponseBody
	MReturnObject delMultiRecords(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		System.out.println(records);
		String[] rs = records.split(",");
		int i = 0;
		for (String str : rs) {
			try {
				roadService.deleteById(str);
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
