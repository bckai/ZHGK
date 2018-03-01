package com.hndfsj.driver.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hndfsj.app.device.domain.DeviceConfig;
import com.hndfsj.app.device.service.IDeviceConfigService;
import com.hndfsj.driver.HDBaseDriver;
import com.hndfsj.driver.constant.DvcDriverConstant;
import com.hndfsj.driver.constant.DvcTypeConstant;
import com.hndfsj.driver.tcp.TcpClientServer;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;
import com.hndfsj.framework.utils.SpringContextHolder;

/**
 * @author yuanxin
 * 设备服务管理器
 *
 */
public class HDServiceManager implements Runnable {
	
	
	private IDeviceConfigService deviceConfigService=SpringContextHolder.getBean("deviceConfigService");
	static Logger log=LoggerFactory.getLogger(HDServiceManager.class);
	
	@Override
	public void run() {
		// 初始化驱动
		initDrivers();
		log.info("=============初始化驱动完成==========");
		// 4. 启动udp端口监听服务
		/*PortListenService portListenService = new PortListenService();
		ConstantPool.THREAD_POOL.add(portListenService);
		portListenService.start();*/
	}

	/**
	 * 初始化设备驱动并打开占用相应的串口
	 */
	private void initDrivers() {
		new Thread(new Runnable() {
			public void run() {
				// 根据参数配置，打开系统需要用到的所有串口
				DvcDriverConstant.hbds = new HashMap<>(); 
				// step.1 获取所有需要使用硬件服务的设备
				PageRequest pageRequest=new PageRequest();
				pageRequest.addOrCondition("substring("+DeviceConfig.DVC_ID+","+4+","+2+")='"+DvcTypeConstant.getDvcType("vd")+"'", SearchCondition.SUB_STRING, null);
				pageRequest.addOrCondition("substring("+DeviceConfig.DVC_ID+","+4+","+2+")='"+DvcTypeConstant.getDvcType("cms")+"'", SearchCondition.SUB_STRING, null);
				pageRequest.addOrCondition("substring("+DeviceConfig.DVC_ID+","+4+","+2+")='"+DvcTypeConstant.getDvcType("wd")+"'", SearchCondition.SUB_STRING, null);
				pageRequest.addSortConditions(DeviceConfig.DVC_BRAND,PageRequest.ORDER_DESC );
				pageRequest.addAndCondition(DeviceConfig.IS_DELETED, SearchCondition.EQUAL, 0);
				List<DeviceConfig> deviceConfigs = deviceConfigService.findAll(pageRequest);
				// step.2 实例化设备对应的驱动
				String driverClassName = "";
				for (DeviceConfig deviceConfig : deviceConfigs) {
					driverClassName = "com.hndfsj.driver.listen.";
					if (deviceConfig.getDvcId().substring(3, 5).equals("11")) {
						driverClassName += "vd.Vd_";
					} else if (deviceConfig.getDvcId().substring(3, 5).equals("12")) {
						driverClassName += "wd.Wd_";
					} else if (deviceConfig.getDvcId().substring(3, 5).equals("15")) {
						driverClassName += "cms.Cms_";
					}
					driverClassName += deviceConfig.getDvcBrand().trim();
					// 如果驱动不为空则先关闭，避免重复加载导致rxtx崩溃
					if (DvcDriverConstant.hbds.get(deviceConfig.getDvcId()) != null) {
						DvcDriverConstant.hbds.get(deviceConfig.getDvcId()).close();
					}
					// 添加网络通信的设备
					if (deviceConfig.getComPort().startsWith("COM")) {
						try {
							DvcDriverConstant.hbds.put(deviceConfig.getDvcId(), getDriverInstance(deviceConfig, driverClassName));
						} catch (Exception e) {
							log.error("==================system：设备驱动加载失败====="+deviceConfig.getDvcId()+"    "+deviceConfig.getComPort());
						}
					} else {
						// 将网络通信的汉威情报板加入驱动管理
						if(deviceConfig.getDvcBrand().equals("ManDeKe")) {
							//DvcDriverConstant.tcp.put(deviceConfig.getDvcId(), getDriverTcpInstance(deviceConfig, driverClassName + "_Net"));
						}else {
							DvcDriverConstant.udps.put(deviceConfig.getDvcId(), getDriverUdpsInstance(deviceConfig, driverClassName + "_Net"));
						}
					}
				}
				log.info("==================system：设备驱动加载完成=====================");
			}
		}).start();
	}
	
	@SuppressWarnings("rawtypes")
	protected UdpClientServer getDriverUdpsInstance(DeviceConfig deviceConfig, String driverClassName) {
		UdpClientServer clientServer=null;
		try {
			//根据类名获取Class对象
			Class<?> driver = Class.forName(driverClassName);
			//参数类型数组
			Class[] parameterTypes = {String.class};
			//根据参数类型获取相应的构造函数
			java.lang.reflect.Constructor constructor = driver.getConstructor(parameterTypes);
			// 参数数组
			Object[] parameters = {deviceConfig.getDvcId()};
			//根据获取的构造函数和参数，创建实例			
			clientServer = UdpClientServer.class.cast(constructor.newInstance(parameters));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return clientServer;
	}
	
	@SuppressWarnings("rawtypes")
	protected TcpClientServer getDriverTcpInstance(DeviceConfig deviceConfig, String driverClassName) {
		TcpClientServer clientServer=null;
		try {
			//根据类名获取Class对象
			Class<?> driver = Class.forName(driverClassName);
			//参数类型数组
			Class[] parameterTypes = {String.class};
			//根据参数类型获取相应的构造函数
			java.lang.reflect.Constructor constructor = driver.getConstructor(parameterTypes);
			// 参数数组
			Object[] parameters = {deviceConfig.getDvcId()};
			//根据获取的构造函数和参数，创建实例			
			clientServer = TcpClientServer.class.cast(constructor.newInstance(parameters));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return clientServer;
	}

	/**
	 * 获取设备驱动实例
	 * @param device
	 * @param driverClassName
	 * @return HDBaseDriver
	 */
	@SuppressWarnings({ "rawtypes"})
	private HDBaseDriver getDriverInstance(DeviceConfig deviceConfig, String driverClassName) {
		HDBaseDriver hbd = null;
		try {
			//根据类名获取Class对象
			Class<?> driver = Class.forName(driverClassName);
			//参数类型数组
			Class[] parameterTypes = {String.class};
			//根据参数类型获取相应的构造函数
			java.lang.reflect.Constructor constructor = driver.getConstructor(parameterTypes);
			// 参数数组
			Object[] parameters = {deviceConfig.getDvcId()};
			//根据获取的构造函数和参数，创建实例			
			hbd = HDBaseDriver.class.cast(constructor.newInstance(parameters));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return hbd;
	}
	
}
