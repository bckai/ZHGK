package com.hndfsj.driver.service;

import java.util.Map.Entry;

import com.hndfsj.driver.constant.DvcDriverConstant;
import com.hndfsj.framework.utils.driver.ConstantPool;

/**
 * @author Administrator
 * udp端口监听服务
 */
public class PortListenService extends Thread {

	public void run() {
		// 驱动加载完毕，开始启动串口监听服务
		for (Entry<String, NioUdpServer> entry : DvcDriverConstant.nuss.entrySet()) {
			Thread nusThread = new Thread(entry.getValue());
			ConstantPool.THREAD_POOL.add(nusThread);
			nusThread.start();
		}
	}
	
}
