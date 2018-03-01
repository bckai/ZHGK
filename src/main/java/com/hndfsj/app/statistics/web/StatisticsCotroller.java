package com.hndfsj.app.statistics.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hndfsj.admin.domain.Dictionary;
import com.hndfsj.admin.service.IDictionaryService;
import com.hndfsj.app.common.enums.ZHGKEnum;
import com.hndfsj.app.device.domain.Device;
import com.hndfsj.app.device.domain.VdStruct;
import com.hndfsj.app.device.service.IDeviceService;
import com.hndfsj.app.device.service.IDsStructService;
import com.hndfsj.app.device.service.IVdStructService;
import com.hndfsj.driver.constant.DvcTypeConstant;
import com.hndfsj.framework.common.MReturnObject;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;
import com.hndfsj.framework.utils.driver.CusDbTool;

@Controller
@RequestMapping(value = "/statistics")
public class StatisticsCotroller {

	@Resource
	private IDictionaryService dictionaryService;

	@Resource
	private IDeviceService deviceService;

	@Resource
	private IDsStructService dsStructService;

	@Resource
	private IVdStructService vdStructService;

	/**
	 * 各类型事件数量
	 * 
	 * @return
	 */
	@RequestMapping("/event")
	public @ResponseBody MReturnObject event() {
		PageRequest pageRequest = new PageRequest();

		pageRequest.addAndCondition(Dictionary.DICTYPE, SearchCondition.EQUAL, ZHGKEnum.DICTIONARY_TYPE.INCIDENT_TYPE);
		pageRequest.setLeftJoinSql("left join sys_dictionary sys_d on sys_d.code=sys_dictionary.code ");
		pageRequest.putMap(PageRequest.MAP_GROUPBY, Dictionary.VALUE);
		pageRequest.setColumns(" sys_dictionary.*,sys_d.value as count ");

		return new MReturnObject(MReturnObject.SUCCESS, dictionaryService.findColumnsAll(pageRequest));
	}

	/**
	 * 各个情报板的发布次数
	 * 
	 * @return
	 */
	@RequestMapping("/cms/release")
	public @ResponseBody MReturnObject cmsRelease() {
		PageRequest pageRequest = new PageRequest();
		pageRequest.addAndCondition(Device.IS_DELETED, SearchCondition.EQUAL, 0);
		pageRequest.addAndCondition(
				"substring(" + Device.ID + "," + 4 + "," + 2 + ")='" + DvcTypeConstant.getDvcType("cms") + "'",
				SearchCondition.SUB_STRING, null);
		return new MReturnObject(MReturnObject.SUCCESS, deviceService.findAll(pageRequest));
	}

	/**
	 * 车检器历史数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/vd/record")
	public @ResponseBody MReturnObject vdRecord(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = new PageRequest();
		List<String> mTbls = CusDbTool.getSqlForTable("hd_vd_", request.getParameter("sTime"),
				request.getParameter("eTime"));
		List<String> mTblsSqlForMMData = new ArrayList<String>();
		for (String table : mTbls) {
			VdStruct vdStruct = new VdStruct();
			vdStruct.setTable(table);
			if (vdStructService.isTableExists(vdStruct) > 0) {
				mTblsSqlForMMData.add(table);
			}
		}
		List<Map<String, String>> map = CusDbTool.getSqlForMMData(mTblsSqlForMMData, "tTime",
				request.getParameter("sTime"), request.getParameter("eTime"));
		pageRequest.addAndCondition(VdStruct.DVC_ID, SearchCondition.EQUAL, request.getParameter("dvcId"));
		pageRequest.putMap("tableSql", map);
		return new MReturnObject(MReturnObject.SUCCESS, vdStructService.getAllPage(pageRequest));
	}

	/**
	 * 车检器历史数据 按年分
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/vd/years")
	public @ResponseBody MReturnObject vdYears(HttpServletRequest request) throws Exception {
		PageRequest pageRequest = new PageRequest();
		pageRequest.addAndCondition(Device.IS_DELETED, SearchCondition.EQUAL, 0);
		pageRequest.addAndCondition(
				"substring(" + Device.ID + "," + 4 + "," + 2 + ")='" + DvcTypeConstant.getDvcType("vd") + "'",
				SearchCondition.SUB_STRING, null);
		return new MReturnObject(MReturnObject.SUCCESS, deviceService.findVdYearsRecordAll(pageRequest));
	}

	/**
	 * 实时设备故障完好率
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/device/rate")
	public @ResponseBody MReturnObject deviceRate(HttpServletRequest request) {
		return new MReturnObject(MReturnObject.SUCCESS, dsStructService.deviceRate());
	}

	/**
	 * 设备采集数
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/device/collect")
	public @ResponseBody MReturnObject deviceCollect(HttpServletRequest request) {
		return new MReturnObject(MReturnObject.SUCCESS, deviceService.deviceCollect());
	}

}
