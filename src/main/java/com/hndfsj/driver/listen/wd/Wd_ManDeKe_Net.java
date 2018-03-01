package com.hndfsj.driver.listen.wd;

import com.hndfsj.app.common.enums.ZHGKEnum.PAVEMENT_STATE;
import com.hndfsj.app.device.domain.DeviceConfig;
import com.hndfsj.app.device.domain.DsStruct;
import com.hndfsj.app.device.domain.WsStruct;
import com.hndfsj.app.device.service.IDeviceConfigService;
import com.hndfsj.app.device.service.IDsStructService;
import com.hndfsj.app.device.service.IWsStructService;
import com.hndfsj.driver.serailport.SerialPortReader;
import com.hndfsj.driver.tcp.TcpClientServer;
import com.hndfsj.framework.utils.SpringContextHolder;
import com.hndfsj.framework.utils.driver.CusDateTool;

public class Wd_ManDeKe_Net extends TcpClientServer {

	private WsStruct wsStruct;
	private SerialPortReader sr;
	private DsStruct dsStruct;
	private String[] data;

	private IDsStructService dsStructService = SpringContextHolder.getBean("dsStructService");
	private IWsStructService wsStructService = SpringContextHolder.getBean("wsStructService");
	private IDeviceConfigService deviceConfigService = SpringContextHolder.getBean("deviceConfigService");

	/**
	 * 初始化驱动并打开串口
	 * 
	 * @param dvcId
	 */
	public Wd_ManDeKe_Net(String dvcId) {
		this.dvcId = dvcId;
	}
	
	@Override
	public void send(Object obj) {
		DeviceConfig deviceConfig = deviceConfigService.getById(this.dvcId);
		String dataStr = super.send(deviceConfig.getComPort(), "4001", "<00AD\\r");
		System.out.println("接收数据来自：曼德克气象设备-" + this.dvcId + "-" + sr.spc.getPortName() + "-"
				+ CusDateTool.getSysTime().substring(0, 19) + "\r\n" + dataStr);
		data = dataStr.split("   ");
		wsStruct = new WsStruct();
		wsStruct.setDvcId(this.dvcId);
		wsStruct.setWspeedAvg(Double.parseDouble(data[1].split(" ")[0]) / 10);// wspeedAvg风速平均值,单位米/秒
		wsStruct.setWdir(Integer.parseInt(data[1].split(" ")[1]));// wdir风向,01指正北，32个方位01~32对应顺时针旋转一周的方位
		wsStruct.setAtempAvg((Double.parseDouble(data[2].split(" ")[0]) - 500) / 10);// atempAvg气温平均值,单位°C
		wsStruct.setHumiAvg(Double.parseDouble(data[2].split(" ")[0]) / 100);// humiAvg湿度平均值,相对湿度，单位%
		wsStruct.setRainVol(Double.parseDouble(data[4].split(" ")[0]) / 100);// rainVol降雨量,当前降水量数据，单位mm
		wsStruct.setVisAvg(Double.parseDouble(data[5]));// visAvg能见度平均值,单位米
		wsStruct.setRtempAvg((Double.parseDouble(data[6].split(" ")[0]) - 500) / 10); // rtempAvg路面温度平均值,单位°C
		wsStruct.setRsurFace(PAVEMENT_STATE.contains(data[6].split(" ")[4]));// rsurFace路面状况 00干燥 01潮湿 02多雨 03冰冻 04霜/雪
																				// 05残盐 06冰雨07 --危险；99—未知,
		wsStructService.save(wsStruct);
		// 数据解析存储完毕，保存一条通讯状态为正常的数据
		dsStruct = new DsStruct(this.dvcId, 1, "通讯正常");
		dsStructService.save(dsStruct);
	}

	@Override
	public void sendLink(String dvcId, String host, int serverPort) {
		
	}

}
