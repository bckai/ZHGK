package com.hndfsj.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hndfsj.app.device.domain.VdStruct;
import com.hndfsj.app.device.service.IVdStructService;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;
import com.hndfsj.framework.utils.DateUtils;
import com.hndfsj.framework.utils.driver.CusDbTool;

public class Statistics {

	@Resource
	private IVdStructService vdStructService;
	static Logger log = LoggerFactory.getLogger(Statistics.class);

	/**
	 * 统计车检器历史数据
	 */
	public void wsYears() {
		VdStruct vdStruct = new VdStruct();
		vdStruct.setTable(
				"hd_vd_" + DateUtils.formatDate(DateUtils.getLastMaxMonthDate(), DateUtils.DATETIME_YM_FORMAT));
		PageRequest pageRequest = new PageRequest();
		pageRequest.addAndCondition(VdStruct.DVC_ID, SearchCondition.EQUAL, vdStruct.getTable().substring(6));
		if (vdStructService.findAll(pageRequest).size() == 0) {
			if (vdStructService.isTableExists(vdStruct) > 0) {
				PageRequest pageRequestYears = new PageRequest();
				pageRequestYears
						.setColumns(" dvcId id,sum(fluxUp) fluxUp, sum(fluxDown) fluxDown, sum(speedUp) speedUp, "
								+ "sum(speedDown) speedDown, sum(occUp) occUp, sum(occDown) occDown,laneNum ");

				List<String> mTblsSqlForMMData = new ArrayList<String>();
				mTblsSqlForMMData.add(vdStruct.getTable());
				List<Map<String, String>> map = CusDbTool.getSqlForMMData(mTblsSqlForMMData);
				pageRequestYears.putMap("tableSql", map);

				List<VdStruct> vdStructs = vdStructService.findColumnsAll(pageRequestYears);
				for (VdStruct vdStructinfo : vdStructs) {
					log.info("================需统计的设备      " + vdStructinfo.getId());
					vdStructinfo.setDvcId(vdStruct.getTable().substring(6));
					vdStructService.saveWsYears(vdStructinfo);
				}
			}
		}
	}
}
