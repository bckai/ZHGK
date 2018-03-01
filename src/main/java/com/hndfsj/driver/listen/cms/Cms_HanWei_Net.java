package com.hndfsj.driver.listen.cms;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hndfsj.app.device.domain.CmsStruct;
import com.hndfsj.app.device.domain.DeviceConfig;
import com.hndfsj.app.device.domain.DsStruct;
import com.hndfsj.app.device.service.ICmsStructService;
import com.hndfsj.app.device.service.IDeviceConfigService;
import com.hndfsj.app.device.service.IDsStructService;
import com.hndfsj.driver.service.UdpClientServer;
import com.hndfsj.framework.utils.SpringContextHolder;

public class Cms_HanWei_Net extends UdpClientServer {

	static Logger log = LoggerFactory.getLogger(Cms_HanWei_Net.class);

	private String cmdCheckLink = "023031393727C503";
	
	private IDsStructService dsStructService = SpringContextHolder.getBean("dsStructService");
	private ICmsStructService cmsStructService = SpringContextHolder.getBean("cmsStructService");
	private IDeviceConfigService deviceConfigService = SpringContextHolder.getBean("deviceConfigService");

	/**
	 * 初始化驱动并打开串口
	 * 
	 * @param dvcId
	 */
	public Cms_HanWei_Net(String dvcId) {
		this.dvcId = dvcId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void send(Object obj) {
		// 情报板发送接收到的参数为： List<ShowCurr>
		if (obj instanceof List<?>) {
			List<CmsStruct> msgs = (List<CmsStruct>) obj;
			DeviceConfig deviceConfig = deviceConfigService.getById(this.dvcId);
			String data = super.send(deviceConfig.getComPort(), 8001, CmsFactory.HanWeiSendMsgs(msgs));
			System.out.println(data.substring(2));
			try {
				if (data.substring(2).startsWith("303130")) {
					cmsStructService.saveLog(msgs);
					dsStructService.save(new DsStruct(this.dvcId, 1, "通讯正常"));
				} else {
					dsStructService.save(new DsStruct(this.dvcId, 0, "通讯故障"));
				}
			} catch (Exception e) {
				dsStructService.save(new DsStruct(this.dvcId, 0, "通讯故障"));
			}
		}
	}

	@Override
	public void sendLink(String dvcId, String host, int serverPort) {
		String data = super.send(host, serverPort, "0230313939C60B03");
		try {
			if (data != null) {
				if (data.substring(2).startsWith("303130343030")) {
					dsStructService.save(new DsStruct(dvcId, 1, "通讯正常"));
				}
			}
		} catch (Exception e) {
			dsStructService.save(new DsStruct(dvcId, 0, "通讯故障"));
		}
	}
}
