package com.hndfsj.app.device.web;

import java.util.*;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.framework.utils.driver.CusDbTool;
import com.hndfsj.framework.base.controller.BaseRestJSONController;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hndfsj.app.device.domain.VdStruct;
import com.hndfsj.app.device.service.IVdStructService;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-09-05 14:37:21
 * @see com.hndfsj.app.device.web.VdStruct
 */
@Controller
@RequestMapping(value = "/vdStruct")
public class VdStructController extends BaseRestJSONController<VdStruct, java.lang.Integer> {

	static Logger log = LoggerFactory.getLogger(VdStructController.class);

	@Resource
	private IVdStructService vdStructService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat(DateUtils.DATETIME_SECOND_FORMAT), true));
	}

	/**
	 * 进入vdStruct 列表
	 *
	 * @param request
	 * @param model
	 * @return
	 * @author zxj
	 * @version 2017-09-05 14:37:21
	 */
	public @ResponseBody MReturnObject list(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		List<String> mTbls = CusDbTool.getSqlForTable("hd_vd_", request.getParameter("sTime"),
				request.getParameter("eTime"));
		List<String> mTblsSqlForMMData = new ArrayList<String>();
		for (String tableName : mTbls) {
			VdStruct vdStruct=new VdStruct();
			vdStruct.setTable(tableName);
			if (vdStructService.isTableExists(vdStruct) > 0) {
				mTblsSqlForMMData.add(tableName);
			}
		}
		List<Map<String, String>> map = CusDbTool.getSqlForMMData(mTblsSqlForMMData, "tTime", request.getParameter("sTime"),
				request.getParameter("eTime"));
		pageRequest.addAndCondition(VdStruct.DVC_ID, SearchCondition.EQUAL, request.getParameter("dvcId"));
		pageRequest.putMap("tableSql", map);
		return new MReturnObject(MReturnObject.SUCCESS, vdStructService.getAllPage(pageRequest));
	}

	/**
	 * 进入vdStruct 列表
	 *
	 * @param request
	 * @param model
	 * @return
	 * @author zxj
	 * @version 2017-09-05 14:37:21
	 */
	@RequestMapping(value = "/listAll")
	public @ResponseBody MReturnObject listAll(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		pageRequest.setColumns(" hd_vd_struct.*,hd_device.name ");
		pageRequest.setLeftJoinSql(" left join hd_device on hd_device.id=hd_vd_struct.dvcId ");
		pageRequest.addAndCondition(VdStruct.CAR_TYPE, SearchCondition.NOT_EQUAL, "0");
		pageRequest.putMap("tableSql", null);
		return new MReturnObject(MReturnObject.SUCCESS, vdStructService.findColumnsAll(pageRequest));
	}

}
