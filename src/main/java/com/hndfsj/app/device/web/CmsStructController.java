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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.framework.utils.driver.CusDbTool;
import com.hndfsj.framework.utils.properties.MessageUtils;
import com.hndfsj.framework.base.controller.BaseRestJSONController;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hndfsj.app.device.domain.CmsShowlist;
import com.hndfsj.app.device.domain.CmsStruct;
import com.hndfsj.app.device.domain.Device;
import com.hndfsj.app.device.service.ICmsShowlistService;
import com.hndfsj.app.device.service.ICmsStructService;
import com.hndfsj.app.device.service.IDeviceService;
import com.hndfsj.driver.constant.DvcTypeConstant;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2017-09-12 14:44:18
 * @see com.hndfsj.app.device.web.CmsStruct
 */
@Controller
@RequestMapping(value="/cmsStruct")
public class CmsStructController extends BaseRestJSONController<CmsStruct, java.lang.String> {

	static Logger log=LoggerFactory.getLogger(CmsStructController.class);
	
	@Resource
	private ICmsStructService cmsStructService;
	
	@Resource
	private ICmsShowlistService cmsShowlistService;
	
	@Resource
	private IDeviceService deviceService;
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(DateUtils.DATETIME_SECOND_FORMAT), true));  
	}

	/**
	* 进入cmsStruct 列表
	*
	* @param request
	* @param model
	* @return
	* @author zxj
	* @version  2017-09-12 14:44:18
	*/
	public @ResponseBody MReturnObject list(HttpServletRequest request) throws Exception{
		PageRequest pageRequest = newPageRequest(request);
		List<String> mTbls=CusDbTool.getSqlForTable("hd_cms_",request.getParameter("sTime"),request.getParameter("eTime"));
		List<String> mTbls2=new ArrayList<String>();
		for (String tableName : mTbls) {
			CmsStruct cmsStruct=new CmsStruct();
			cmsStruct.setTable(tableName);
			 if(cmsStructService.isTableExists(cmsStruct)>0){
				 mTbls2.add(tableName);
			 }
		}
		List<Map<String, String>> map = CusDbTool.getSqlForMMData(mTbls2, "tTime", request.getParameter("sTime"), request.getParameter("eTime"));
		pageRequest.addAndCondition(CmsStruct.DVC_ID, SearchCondition.EQUAL, request.getParameter("dvcId"));
		pageRequest.putMap("tableSql", map);
		return new MReturnObject(MReturnObject.SUCCESS, cmsStructService.getAllPage(pageRequest));
	}
	
	/**
	 * 新增/修改save操作
	 * 
	 * @param model
	 * @param gencode
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author Mr.Hao
	 * @version 2013-01-08 16:36:37
	 */
	public @ResponseBody MReturnObject create(Model model, CmsStruct cmsStruct, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PageRequest pageRequestInfo=newPageRequest(request);
		pageRequestInfo.addAndCondition("substring("+Device.ID+","+4+","+2+")='"+DvcTypeConstant.getDvcType("cms")+"'", SearchCondition.SUB_STRING, null);
		pageRequestInfo.addAndCondition(Device.IS_DELETED, SearchCondition.EQUAL, 0);
		pageRequestInfo.addAndCondition(Device.ID, SearchCondition.EQUAL, request.getParameter("dvcId"));
		
		if(request.getParameter("dvcId").isEmpty()&&deviceService.findAll(pageRequestInfo).isEmpty()){
			return new MReturnObject(MReturnObject.ERROR, "设备编号有误！");
		}
		
		PageRequest pageRequest=newPageRequest(request);
		for (String id : request.getParameter("cmsShowlistId").split(",")) {
			pageRequest.addOrCondition(CmsShowlist.ID, SearchCondition.EQUAL, id);
		}
		List<CmsShowlist> cmsShowlists=cmsShowlistService.findAll(pageRequest);
		
		if(cmsShowlists.isEmpty()){
			return new MReturnObject(MReturnObject.ERROR, "节目必须保留一个！");
		}
		cmsStructService.deleteById(cmsStruct.getDvcId());
		for (CmsShowlist cmsShowlist : cmsShowlists) {
			cmsStruct.setId(cmsShowlist.getId());
			cmsStruct.setCdntLeft(cmsShowlist.getCdntLeft());
			cmsStruct.setCdntUp(cmsShowlist.getCdntUp());
			cmsStruct.setFontColor(cmsShowlist.getFontColor());
			cmsStruct.setFontSize(cmsShowlist.getFontSize());
			cmsStruct.setFontStyle(cmsShowlist.getFontStyle());
			cmsStruct.setKeepTime(cmsShowlist.getKeepTime());
			cmsStruct.setMessage(cmsShowlist.getMessage());
			cmsStruct.setPlayMode(cmsShowlist.getPlayMode());
			cmsStruct.setPubFlag(0);
			cmsStruct.setPubMan(getCurrentMember(request).getRealName());
			cmsStruct.setCreateTime(new Date());
			cmsStruct.setTransFlag(0);
			cmsStructService.save(cmsStruct);
		}
		deviceService.addCountOne(cmsStruct.getDvcId());
		return new MReturnObject(MReturnObject.SUCCESS, MessageUtils.ADD_SUCCESS);
	}
	
}
