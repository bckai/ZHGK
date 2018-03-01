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
import com.hndfsj.app.device.domain.WsStruct;
import com.hndfsj.app.device.service.IWsStructService;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-09-05 14:27:53
 * @see com.hndfsj.app.device.web.WsStruct
 */
@Controller
@RequestMapping(value = "/wsStruct")
public class WsStructController extends BaseRestJSONController<WsStruct, String> {

	static Logger log = LoggerFactory.getLogger(WsStructController.class);

	@Resource
	private IWsStructService wsStructService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat(DateUtils.DATETIME_SECOND_FORMAT), true));
	}

	/**
	 * 进入wsStruct 列表
	 *
	 * @param request
	 * @param model
	 * @return
	 * @author zxj
	 * @version 2017-09-05 14:27:53
	 */
	public @ResponseBody MReturnObject list(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		List<String> mTbls = CusDbTool.getSqlForTable("hd_ws_", request.getParameter("sTime"),
				request.getParameter("eTime"));
		List<String> mTblsSqlForMMData = new ArrayList<String>();
		for (String tableName : mTbls) {
			WsStruct wsStruct=new WsStruct();
			wsStruct.setTable(tableName);
			if (wsStructService.isTableExists(wsStruct) > 0) {
				mTblsSqlForMMData.add(tableName);
			}
		}
		List<Map<String, String>> map = CusDbTool.getSqlForMMData(mTblsSqlForMMData, "tTime", request.getParameter("sTime"),
				request.getParameter("eTime"));
		pageRequest.addAndCondition(WsStruct.DVC_ID, SearchCondition.EQUAL, request.getParameter("dvcId"));
		pageRequest.putMap("tableSql", map);
		return new MReturnObject(MReturnObject.SUCCESS, wsStructService.getAllPage(pageRequest));
	}

	@RequestMapping(value = "/listAll")
	public @ResponseBody MReturnObject one(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = newPageRequest(request);
		pageRequest.setColumns(" hd_ws_struct.*,hd_device.name ");
		pageRequest.setLeftJoinSql(" left join hd_device on hd_device.id=hd_ws_struct.dvcId ");
		return new MReturnObject(MReturnObject.SUCCESS, wsStructService.findColumnsAll(pageRequest));
	}
}
