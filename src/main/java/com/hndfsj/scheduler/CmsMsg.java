package com.hndfsj.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hndfsj.app.device.domain.CmsStruct;
import com.hndfsj.app.device.domain.DeviceConfig;
import com.hndfsj.app.device.service.ICmsStructService;
import com.hndfsj.driver.HDBaseDriver;
import com.hndfsj.driver.constant.DvcDriverConstant;
import com.hndfsj.driver.service.UdpClientServer;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;

/**
 * @author 情报板节目发布服务
 *
 */
public class CmsMsg {

	private List<CmsStruct> msgs;
	private List<CmsStruct> sendMsgs;
	private List<CmsStruct> dvcIds;
	private String dvcId;
	// private HDBaseDriver hbd;
	private UdpClientServer udpClientServer;

	@Resource
	private ICmsStructService cmsStructService;
	static Logger log = LoggerFactory.getLogger(CmsMsg.class);

	public void send() {
		log.info("================================================      " + new Date());
		// 等待驱动加载
		// DvcDriverConstant.waitDriverLoading();
		// 循环检查有无需要发布的节目，如果存在节目未发出，则将节目发送出去并添加标记已发送
		sendMsgs = new ArrayList<CmsStruct>();
		PageRequest pageRequest = new PageRequest();
		pageRequest.setLeftJoinSql(" left join hd_device_config on hd_device_config.dvcId=hd_cms_struct.dvcId ");
		pageRequest.addAndCondition(DeviceConfig.IS_DELETED, SearchCondition.EQUAL, 0);
		pageRequest.addAndCondition(CmsStruct.PUB_FLAG, SearchCondition.EQUAL, 0);
		pageRequest.setColumns(" hd_cms_struct.* ");
		msgs = cmsStructService.findColumnsAll(pageRequest);
		pageRequest.putMap(PageRequest.MAP_GROUPBY, CmsStruct.DVC_ID);
		dvcIds = cmsStructService.findColumnsAll(pageRequest);

		for (CmsStruct dsc : dvcIds) {
			dvcId = dsc.getDvcId();
			sendMsgs.clear();
			for (CmsStruct msc : msgs) {
				if (dvcId.equals(msc.getDvcId())) {
					sendMsgs.add(msc);
				}
			}
			log.info("================需要发送的设备      " + dvcId);
			udpClientServer = DvcDriverConstant.getUdpClient(dvcId);
			if (udpClientServer != null) {
				udpClientServer.send(sendMsgs);
			}
		}
	}

}
