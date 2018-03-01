package com.hndfsj.driver.listen.wd;

import java.util.Date;
import java.util.Observable;

import com.hndfsj.app.common.enums.ZHGKEnum.PAVEMENT_STATE;
import com.hndfsj.app.device.domain.DsStruct;
import com.hndfsj.app.device.domain.WsStruct;
import com.hndfsj.app.device.service.IDsStructService;
import com.hndfsj.driver.HDBaseDriver;
import com.hndfsj.driver.serailport.SerialPortReader;
import com.hndfsj.framework.utils.SpringContextHolder;
import com.hndfsj.framework.utils.driver.CusDateTool;

public class Wd_XunFei extends HDBaseDriver {
	
	private WsStruct wsStruct;
	private SerialPortReader sr;
	private String dataStr;
	private DsStruct dsStruct;
	private String[] data;
	
	private int flag = 1;

	private IDsStructService dsStructService=SpringContextHolder.getBean("dsStructService");
	
	/**
	 * 初始化驱动并打开串口
	 * @param dvcId
	 */
	public Wd_XunFei(String dvcId) {
		super.driverInit(dvcId);
		// 此设备返回数据分段间隔较大,设定delayTime
		this.spr.delayRead = 200;
	}
	
	@Override
	public void send(Object obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// 如果有数据回复，则判定为通讯正常
		sr = (SerialPortReader) o;
		dataStr = arg.toString();
		dataStr = dataStr.replaceAll("\\s{2,}", " ");
		System.out.println("接收数据来自：勋飞气象设备-" + this.dvcId + "-" 
						+ sr.spc.getPortName() + "-" + CusDateTool.getSysTime().substring(0, 19) 
						+ "\r\n" + dataStr);
		data = dataStr.split(" ");
		wsStruct = new WsStruct();
		wsStruct.setDvcId(this.dvcId);
		wsStruct.setVisAvg(Double.parseDouble(data[4]) * 1000);
		wsStruct.setRtempAvg((Double.parseDouble(data[6]) - 500) / 10);
		wsStruct.setRsurFace(PAVEMENT_STATE.contains(data[12]));
		wsStruct.setRainVol(Double.parseDouble(data[15]));
		wsStruct.setWspeedAvg(Double.parseDouble(data[17]));
		wsStruct.setWdir(Integer.parseInt(data[18]));
		wsStruct.setAtempAvg((Double.parseDouble(data[20]) - 400) / 10);
		wsStruct.setHumiAvg(Double.parseDouble(data[21]) / 10);
		// update by YuanX.X. 20160416 无数据字段填充0，避免报表导出空指针问题
		
		// 数据解析存储完毕，保存一条通讯状态为正常的数据
		dsStruct = new DsStruct(this.dvcId,1,"通讯正常");
		dsStructService.save(dsStruct);
	}

	@Override
	public void getData() {
		// @@ 00 01 GETALLDATA\r\n 发送命令，远端设备返回数据
		super.send("@@ 00 01 GETALLDATA\r\n");
	}

	@Override
	public void checkLink() {
		// 如果此方法被触发，则判断上次接收到数据的时间，如果时间间隔大于5分钟，则认定发生了通讯故障
		long ss = (new Date().getTime() - this.spr.lastRcvDataTime.getTime()) / 1000;
		if (((int)ss / 60) > 5) {
			// 五分钟内未收到过数据，则状态认定为故障
			dsStruct.setDstatus(0);
			if (flag == 1) {
				dsStruct = new DsStruct(this.dvcId,0,"通讯故障");
				flag = 0;
			}
		} else {
			if (flag == 0) {
				dsStruct = new DsStruct(this.dvcId,1,"通讯正常");
				flag = 1;
			}
		}
		dsStructService.save(dsStruct);
	}
	
}
