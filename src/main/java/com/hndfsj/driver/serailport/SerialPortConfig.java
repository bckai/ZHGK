package com.hndfsj.driver.serailport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hndfsj.app.common.enums.ZHGKEnum.PORT_CONFIG;
import com.hndfsj.app.device.domain.DeviceConfig;
import com.hndfsj.app.device.service.IDeviceConfigService;
import com.hndfsj.framework.utils.SpringContextHolder;

/**
 * @author yuanxin
 * 串口参数配置
 */
public class SerialPortConfig {
	
	private IDeviceConfigService deviceConfigService=SpringContextHolder.getBean("deviceConfigService");
	static Logger log=LoggerFactory.getLogger(SerialPortConfig.class);
	
	private String portName;
	private int baudRate;
	private int dataBit;
	private int stopBit;
	private int parityBit;
	private int timeOut;
	private int delay;
	
	/**
	 * 使用设备编号初始化串口参数配置
	 * 注：设备超时时间和数据准备时间默认为100ms
	 * @param dvcId
	 */
	public SerialPortConfig(String dvcId) {
		initSpcByDvcId(dvcId);
		this.timeOut = 100;
		this.delay = 100;
	}
	
	/**
	 * 根据设备编号初始化串口通讯配置
	 * @param dvcId
	 */
	private void initSpcByDvcId(String dvcId) {
		DeviceConfig deviceConfig = deviceConfigService.getById(dvcId);
		if (deviceConfig != null) {
			int PortConfig[]=PORT_CONFIG.contains(deviceConfig.getDvcBrand());
			this.portName = deviceConfig.getComPort();
			this.baudRate = PortConfig[0];
			this.dataBit = PortConfig[1];
			this.stopBit = PortConfig[2];
			this.parityBit = PortConfig[3];
		}
	}

	public String getPortName() {
		return portName;
	}

	public int getBaudRate() {
		return baudRate;
	}

	public int getDataBit() {
		return dataBit;
	}

	public int getStopBit() {
		return stopBit;
	}

	public int getParityBit() {
		return parityBit;
	}

	public int getTimeOut() {
		return timeOut;
	}
	
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public int getDelay() {
		return delay;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
}
