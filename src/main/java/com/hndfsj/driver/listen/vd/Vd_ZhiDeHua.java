package com.hndfsj.driver.listen.vd;
import java.util.Date;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hndfsj.app.device.domain.DsStruct;
import com.hndfsj.app.device.domain.VdStruct;
import com.hndfsj.app.device.service.IDsStructService;
import com.hndfsj.app.device.service.IVdStructService;
import com.hndfsj.driver.HDBaseDriver;
import com.hndfsj.driver.serailport.SerialPortReader;
import com.hndfsj.framework.utils.SpringContextHolder;
import com.hndfsj.framework.utils.driver.CusDateTool;

/**
 * @author yuanxin
 * Tractech T-11双雷达微波交通检测器
 * 微波车检器串口驱动
 * 驱动仅作收、发操作与数据解析，不涉及轮询频率等控制逻辑
 */
public class Vd_ZhiDeHua extends HDBaseDriver {
	
	private VdStruct vdStruct;
	private SerialPortReader sr;
	private String dataStr;
	private String[] data;
	
	private int flag = 1;

	private DsStruct dsStruct;
	private IVdStructService vdStructService=SpringContextHolder.getBean("vdStructService");
	private IDsStructService dsStructService=SpringContextHolder.getBean("dsStructService");
	static Logger log=LoggerFactory.getLogger(Vd_ZhiDeHua.class);

	/**
	 * 初始化驱动并打开串口
	 * @param dvcId
	 */
	public Vd_ZhiDeHua(String dvcId) {
		super.driverInit(dvcId);
	}
	
	@Override
	public void send(String msg) {
		// 发送数据
	}
		
	@Override
	public void update(Observable o, Object arg) {
		// 将收到的数据解析出来，执行相应的处理
		dataStr = arg.toString();
		sr = (SerialPortReader) o;
		log.info("接收数据来自：志德华通车检器-" + this.dvcId + "-" + sr.spc.getPortName()
		 				+ "-" + CusDateTool.getSysTime().substring(0, 19) + "\r\n" + dataStr);
		data = dataStr.split(",");
		vdStruct=new VdStruct();
		vdStruct.setCarType(99);// carType车长类型,
		vdStruct.setDvcId(this.dvcId);
		vdStruct.setPeriod(Integer.parseInt(data[1]));// period采集周期（单位：秒）,
		vdStruct.setLane1Flux(Integer.parseInt(data[4]) + Integer.parseInt(data[6]) + Integer.parseInt(data[8]) + Integer.parseInt(data[10])); // lane1Flux车道1流量,
		vdStruct.setLane2Flux(Integer.parseInt(data[12]) + Integer.parseInt(data[14]) + Integer.parseInt(data[16]) + Integer.parseInt(data[18])); 
		vdStruct.setLane3Flux(Integer.parseInt(data[20]) + Integer.parseInt(data[22]) + Integer.parseInt(data[24]) + Integer.parseInt(data[26])); 
		vdStruct.setLane4Flux(Integer.parseInt(data[28]) + Integer.parseInt(data[30]) + Integer.parseInt(data[32]) + Integer.parseInt(data[34])); 
		vdStruct.setLane1Speed(Integer.parseInt(data[5]) + Integer.parseInt(data[7]) + Integer.parseInt(data[9]) + Integer.parseInt(data[11]) / 4); // lane1Speed车道1速度,
		vdStruct.setLane2Speed(Integer.parseInt(data[13]) + Integer.parseInt(data[15]) + Integer.parseInt(data[17]) + Integer.parseInt(data[19]) / 4); 
		vdStruct.setLane3Speed(Integer.parseInt(data[21]) + Integer.parseInt(data[23]) + Integer.parseInt(data[25]) + Integer.parseInt(data[27]) / 4);
		vdStruct.setLane4Speed(Integer.parseInt(data[29]) + Integer.parseInt(data[31]) + Integer.parseInt(data[33]) + Integer.parseInt(data[35]) / 4); 
		vdStruct.setLane1Occ(Double.valueOf(data[35]));// lane1Occ车道1占有率,
		vdStruct.setLane2Occ(Double.valueOf(data[36]));
		vdStruct.setLane3Occ(Double.valueOf(data[37]));
		vdStruct.setLane4Occ(Double.valueOf(data[38]));
		vdStruct.setLane5Flux(0);
		vdStruct.setLane6Flux(0);
		vdStruct.setLane7Flux(0);
		vdStruct.setLane8Flux(0);
		vdStruct.setLane5Speed(0);
		vdStruct.setLane6Speed(0);
		vdStruct.setLane7Speed(0);
		vdStruct.setLane8Speed(0);
		vdStruct.setLane5Occ(Double.valueOf(0));
		vdStruct.setLane6Occ(Double.valueOf(0));
		vdStruct.setLane7Occ(Double.valueOf(0));
		vdStruct.setLane8Occ(Double.valueOf(0));
		vdStruct.setOccUp(vdStruct.getLane1Occ() + vdStruct.getLane2Occ()); // occUp上行占有率,
		vdStruct.setOccDown(vdStruct.getLane3Occ() + vdStruct.getLane4Occ()); // occDown下行占有率,
		vdStruct.setFluxUp(vdStruct.getLane1Flux() + vdStruct.getLane2Flux());// fluxUp上行流量,
		vdStruct.setFluxDown(vdStruct.getLane3Flux() + vdStruct.getLane4Flux());  // fluxDown下行流量,
		vdStruct.setSpeedUp((vdStruct.getLane1Speed() + vdStruct.getLane2Speed()) / 2); // speedUp上行速度,
		vdStruct.setSpeedDown((vdStruct.getLane3Speed() + vdStruct.getLane4Speed()) / 2); // speedDown下行速度,
		vdStruct.setTransFlag(0); // transFlag上传标识,
		vdStruct.setLaneNum(Integer.parseInt("4")); // laneNum总车道数（双向车道数和）,
		vdStructService.save(vdStruct);
		// 将车检数据存储完成后，同时更新设备通讯状态为正常
		dsStruct = new DsStruct(this.dvcId,1,"通讯正常");
		dsStructService.save(dsStruct);
	}

	@Override
	public void send(Object obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void getData() {
		// 此车检器设备无需发送命令，设备每5min向上位机回传一次数据
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
