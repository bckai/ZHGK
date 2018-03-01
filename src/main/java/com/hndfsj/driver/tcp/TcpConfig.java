package com.hndfsj.driver.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hndfsj.app.device.domain.DeviceConfig;
import com.hndfsj.app.device.service.IDeviceConfigService;
import com.hndfsj.framework.utils.SpringContextHolder;

public class TcpConfig {
	private IDeviceConfigService deviceConfigService = SpringContextHolder.getBean("deviceConfigService");
	static Logger log = LoggerFactory.getLogger(TcpConfig.class);

	private String port;
	private String ip;

	/**
	 * 使用设备编号初始化串口参数配置 注：设备超时时间和数据准备时间默认为100ms
	 * 
	 * @param dvcId
	 */
	public TcpConfig(String dvcId) {
		DeviceConfig deviceConfig = deviceConfigService.getById(dvcId);
		if (deviceConfig != null) {
			this.port = "4001";
			this.ip = deviceConfig.getComPort();
		}
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
