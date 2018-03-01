package com.hndfsj.scheduler;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.hndfsj.app.common.enums.ZHGKEnum.CAM_FAC;
import com.hndfsj.app.device.domain.CmsStruct;
import com.hndfsj.app.device.domain.Device;
import com.hndfsj.app.device.domain.DeviceConfig;
import com.hndfsj.app.device.domain.DsStruct;
import com.hndfsj.app.device.domain.VdStruct;
import com.hndfsj.app.device.domain.WsStruct;
import com.hndfsj.app.device.service.IDeviceConfigService;
import com.hndfsj.app.device.service.IDsStructService;
import com.hndfsj.app.device.service.IVdStructService;
import com.hndfsj.app.device.service.IWsStructService;
import com.hndfsj.driver.HDBaseDriver;
import com.hndfsj.driver.constant.DvcDriverConstant;
import com.hndfsj.driver.constant.DvcTypeConstant;
import com.hndfsj.driver.service.UdpClientServer;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;
import com.hndfsj.framework.utils.HttpUtils;
import com.hndfsj.framework.utils.driver.CusDateTool;
import com.hndfsj.framework.utils.driver.HexStrUtil;
import com.hndfsj.framework.utils.properties.DeviceUtils;

/**
 * @author 设备状态检测服务
 */
public class HDState {

	private List<DeviceConfig> deviceConfigs;
	private HDBaseDriver hbd;
	private UdpClientServer udpClientServer;

	@Resource
	private IDeviceConfigService deviceConfigService;
	
	@Resource
	private IVdStructService vdStructService;
	@Resource
	private IWsStructService wsStructService;

	@Resource
	private IDsStructService dsStructService;

	static Logger log = LoggerFactory.getLogger(HDState.class);

	public void send() {
		log.info("================================================      " + new Date());
		camCheckoutStatus();
		cmsCheckoutStatus();
		vdCheckoutStatus();
		wdCheckoutStatus();
	}

	public void camCheckoutStatus(){
		deviceConfigs=deviceConfigs("cam");
		for (DeviceConfig deviceConfig : deviceConfigs) {
			dsStructService.save(new DsStruct(deviceConfig.getDvcId(), 1, "通讯正常"));
			// {"status":"error","message":"摄像机连接失败！"}
			JSONObject camStatus = JSONObject.parseObject(HttpUtils
					.doGet(DeviceUtils.getString("CMS_TEST_URL") + "?vedio=" + deviceConfig.getComPort(), "UTF-8"));
			try {
				if (camStatus.get("status").equals("success")) {
					dsStructService.save(new DsStruct(deviceConfig.getDvcId(), 1, "通讯正常"));
				} else {
					//dsStructService.save(new DsStruct(deviceConfig.getDvcId(), 0, "通讯故障"));
				}
			} catch (Exception e) {
				//dsStructService.save(new DsStruct(deviceConfig.getDvcId(), 0, "通讯故障"));
			}
		}
	}
	
	public void cmsCheckoutStatus(){
		deviceConfigs=deviceConfigs("cms");
		for (DeviceConfig deviceConfig : deviceConfigs) {
			udpClientServer = DvcDriverConstant.getUdpClient(deviceConfig.getDvcId());
			if (udpClientServer != null) {
				DsStruct dsStruct = dsStructService.getById(deviceConfig.getDvcId());
				if (dsStruct != null) {
					if (CusDateTool.getTimeAgo(dsStruct.getCreateTime())) {
						dsStructService.save(new DsStruct(deviceConfig.getDvcId(), 0, "通讯故障"));
					}
				} else {
					dsStructService.save(new DsStruct(deviceConfig.getDvcId(), 0, "通讯故障"));
				}
				log.info("-------------------" + deviceConfig.getComPort());
				udpClientServer.sendLink(deviceConfig.getDvcId(), deviceConfig.getComPort(), 8001);
			}
		}
	}
	
	public void vdCheckoutStatus(){
		deviceConfigs=deviceConfigs("vd");
		for (DeviceConfig deviceConfig : deviceConfigs) {
			 VdStruct  vdStruct = vdStructService.status(deviceConfig.getDvcId());
			 if(vdStruct==null) {
				 dsStructService.save(new DsStruct(deviceConfig.getDvcId(), 0, "通讯故障"));
			 }else {
				 dsStructService.save(new DsStruct(deviceConfig.getDvcId(), 1, "通讯正常"));
			 }
		}
	}
	
	public void wdCheckoutStatus(){
		deviceConfigs=deviceConfigs("wd");
		for (DeviceConfig deviceConfig : deviceConfigs) {
			 WsStruct  wsStruct = wsStructService.status(deviceConfig.getDvcId());
			 if(wsStruct==null) {
				 dsStructService.save(new DsStruct(deviceConfig.getDvcId(), 0, "通讯故障"));
			 }else {
				 dsStructService.save(new DsStruct(deviceConfig.getDvcId(), 1, "通讯正常"));
			 }
		}
	}
	
	public List<DeviceConfig> deviceConfigs(String type) {
		log.info("================================================      " + new Date());
		// 需要检测通讯状态的设备
		PageRequest pageRequest = new PageRequest();
		pageRequest.addAndCondition(DeviceConfig.IS_DELETED, SearchCondition.EQUAL, 0);
		pageRequest.addAndCondition(
				"substring(" + DeviceConfig.DVC_ID + "," + 4 + "," + 2 + ")='" + DvcTypeConstant.getDvcType(type) + "'",
				SearchCondition.SUB_STRING, null);
		return deviceConfigService.findAll(pageRequest);
	}

	public static void main(String[] args) {
		System.out.println(HttpUtils.doGet(DeviceUtils.getString("CMS_TEST_URL"), "UTF-8"));
		// 1、使用JSONObject
		String objectStr = "{\"status\":\"error\",\"message\":\"摄像机连接失败！\"}";
		JSONObject j = JSONObject.parseObject(objectStr);
		if (j.get("status").equals("error")) {
			System.out.println(j.get("status"));
		}
	}
}
