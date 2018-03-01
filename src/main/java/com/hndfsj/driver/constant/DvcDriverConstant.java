package com.hndfsj.driver.constant;

import java.util.HashMap;
import java.util.Map;

import com.hndfsj.driver.HDBaseDriver;
import com.hndfsj.driver.service.NioUdpServer;
import com.hndfsj.driver.service.UdpClientServer;
import com.hndfsj.driver.tcp.TcpClientServer;

public class DvcDriverConstant {
	
	public static Map<String, HDBaseDriver> hbds = new HashMap<>();
	
	public static Map<String, NioUdpServer> nuss = new HashMap<>();
	
	public static Map<String, UdpClientServer> udps = new HashMap<>();
	
	public static Map<String, TcpClientServer> tcp = new HashMap<>();
	
	/**
	 * 根据设备编号获取设备驱动
	 * @param dvcId
	 * @return HDBaseDriver
	 */
	public static HDBaseDriver getDvcDriver(String dvcId) {
		return hbds.get(dvcId);
	}
	
	/**
	 * 根据设备编号获取设备驱动
	 * @param dvcId
	 * @return udp
	 */
	public static UdpClientServer getUdpClient(String dvcId) {
		return udps.get(dvcId);
	}
	
	/**
	 * 根据设备编号获取设备驱动
	 * @param dvcId
	 * @return udp
	 */
	public static TcpClientServer getTcpClient(String dvcId) {
		return tcp.get(dvcId);
	}
}
