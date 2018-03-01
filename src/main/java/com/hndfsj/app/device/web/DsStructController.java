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
import com.hndfsj.app.device.domain.DsStruct;
import com.hndfsj.app.device.service.IDsStructService;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-09-05 14:27:31
 * @see com.hndfsj.app.device.web.DsStruct
 */
@Controller
@RequestMapping(value = "/dsStruct")
public class DsStructController extends BaseRestJSONController<DsStruct, String> {

	static Logger log = LoggerFactory.getLogger(DsStructController.class);

	@Resource
	private IDsStructService dsStructService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat(DateUtils.DATETIME_SECOND_FORMAT), true));
	}

	/**
	 * 进入dsStruct 列表
	 *
	 * @param request
	 * @param model
	 * @return
	 * @author zxj
	 * @version 2017-09-05 14:27:31
	 */
	public @ResponseBody MReturnObject list(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		List<String> mTbls = CusDbTool.getSqlForTable("hd_ws_", request.getParameter("sTime"),
				request.getParameter("eTime"));
		List<String> mTblsSqlForMMData = new ArrayList<String>();
		for (String tableName : mTbls) {
			DsStruct dsStruct=new DsStruct();
			dsStruct.setTable(tableName);
			if (dsStructService.isTableExists(dsStruct) > 0) {
				mTblsSqlForMMData.add(tableName);
			}
		}
		List<Map<String, String>> map = CusDbTool.getSqlForMMData(mTblsSqlForMMData, "tTime", request.getParameter("sTime"),
				request.getParameter("eTime"));
		pageRequest.addAndCondition(DsStruct.DVC_ID, SearchCondition.EQUAL, request.getParameter("dvcId"));
		pageRequest.putMap("tableSql", map);
		return new MReturnObject(MReturnObject.SUCCESS, dsStructService.getAllPage(pageRequest));
	}
}
