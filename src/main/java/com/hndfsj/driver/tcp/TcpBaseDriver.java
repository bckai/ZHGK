package com.hndfsj.driver.tcp;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import com.hndfsj.app.device.domain.DsStruct;
import com.hndfsj.app.device.service.IDsStructService;
import com.hndfsj.framework.utils.SpringContextHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 一定要重写构造器，使用设备编号初始化驱动！！！
 */
public abstract class TcpBaseDriver implements Observer {
	static Logger log = LoggerFactory.getLogger(TcpBaseDriver.class);

	protected String dvcId;
	protected TcpConfig tcpConfig;
	protected TcpReader tcpReader;
	private DsStruct dsStruct;

	private IDsStructService dsStructService = SpringContextHolder.getBean("dsStructService");

	/**
	 * 根据设备初始化串口
	 * 
	 * @param dvcId
	 */
	public void driverInit(String dvcId) {
		this.dvcId = dvcId;
		this.tcpConfig = new TcpConfig(this.dvcId);
		this.tcpReader = new TcpReader(this.tcpConfig, this);
	}

	/**
	 * 设备驱动实现本方法，用于向串口发送字符串数据
	 * 
	 * @param msg
	 */
	public void send(String msg) {
		tcpReader.send(msg);
	};

	/**
	 * 设备驱动实现本方法，用于向串口发送hex串数据
	 * 
	 * @param hexMsg
	 */
	public void sendHex(String hexMsg) {
		tcpReader.sendHex(hexMsg);
	}

	/**
	 * 设备驱动实现本方法，主要用于向串口发送依据通讯协议编码后的数据 区别：可以传入各种类型的参数
	 * 
	 * @param obj
	 */
	public abstract void send(Object obj);

	/**
	 * 获取数据（发送采集数据的命令：部分设备需要上位机发送控制命令后才返回数据） 此方法由具体的设备驱动来实现，因为各种设备采集数据的命令不同
	 */
	public abstract void getData();

	/**
	 * 检查设备通讯链路连接状态 此方法由具体的设备驱动来实现，因为各种设备检查连接状态的命令与判断逻辑不同 update by yuanx 20160421
	 * 默认判定五分钟内有无通信，有则正常，反之故障 其它有特殊判定方式的设备驱动可重写此函数
	 */
	public void checkLink() {
		// 如果此方法被触发，则判断上次接收到数据的时间，如果时间间隔大于5分钟，则认定发生了通讯故障
		long ss = (new Date().getTime() - this.tcpReader.lastRcvDataTime.getTime()) / 1000;
		dsStruct = new DsStruct(this.dvcId, 1, "通讯正常");
		if (((int) ss / 60) > 5) {
			// 五分钟内未收到过数据，则状态认定为故障
			dsStruct.setDstatus(0);
			dsStruct.setErrContent("通讯故障");
			log.info("================设备故障      " + this.dvcId);
		}
		dsStructService.save(dsStruct);
		// 注： 此处会导致产生系统刚启动时所有设备均为通信正常的情况。
	};

	/**
	 * 释放串口资源，关闭驱动
	 */
	public void close() {
		tcpReader.close();
		this.tcpReader = null;
		this.tcpConfig = null;
	}

	/**
	 * 具体设备驱动实现本方法，用于解析接收到的串口数据，并进一步执行其它操作（保存数据、触发其它事件）
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public abstract void update(Observable o, Object arg);

}
