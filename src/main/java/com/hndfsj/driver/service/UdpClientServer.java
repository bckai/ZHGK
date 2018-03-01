package com.hndfsj.driver.service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hndfsj.framework.utils.driver.HexStrUtil;

public abstract class UdpClientServer {

	static Logger log = LoggerFactory.getLogger(UdpClientServer.class);
	protected String dvcId;
	private static DatagramSocket ds = null;

	/**
	 * 根据设备初始化串口
	 * 
	 * @param dvcId
	 */
	public void driverInit(String dvcId) {
		this.dvcId = dvcId;
	}

	static {
		try {
			ds = new DatagramSocket(8899);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public String send(String host, int serverPort, String msg) {
		try {
			DatagramPacket dp = new DatagramPacket(toBytes(msg), toBytes(msg).length, InetAddress.getByName(host),
					serverPort);
			if(ds==null) {
				ds = new DatagramSocket(8898);
			}
			ds.send(dp);
			return receive();
		} catch (IOException e) {
			log.info("============发送失败=============");
		}
		return null;
	}

	/**
	 * byte[]数组转换为16进制的字符串
	 *
	 * @param data
	 *            要转换的字节数组
	 * @return 转换后的结果
	 */
	public static final String byteArrayToHexString(byte[] data) {
		StringBuilder sb = new StringBuilder(data.length * 2);
		for (byte b : data) {
			int v = b & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase(Locale.getDefault());
	}

	public static void main(String[] args) throws IOException {
		// 023031393727C503
		/*String str = "0230313130506C61792E6C73742B000000005B4C6973745D0D0A4974656D5F6E6F3D310D0A4974656D303D3430302C312C302C5C433030303031365C6668333233325C63323535303030303030303030BBB6D3ADCABBC8EB20B5CBB6F5B8DFCBD9310D0A919903";
		DatagramPacket dp = new DatagramPacket(toBytes(str), toBytes(str).length,
				InetAddress.getByName("10.141.151.66"), 8001);
		ds.send(dp);
		byte[] buffer = new byte[1024];
		try {
			DatagramPacket dp1 = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp1);
			// 转为String再通知观察者
			// ds.close();
		} catch (IOException e) {
			log.info("============获取信息失败=============");
		}*/
		String str = HexStrUtil.getHexResult("<00AD")+"0D0A";
		DatagramPacket dp = new DatagramPacket(toBytes("3C303041440D0A"), toBytes("3C303041440D0A").length,
				InetAddress.getByName("10.141.151.150"), 4001);
		ds.send(dp);
		byte[] buffer = new byte[1024];
		try {
			DatagramPacket dp1 = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp1);
			// 转为String再通知观察者
			// ds.close();
		} catch (IOException e) {
			log.info("============获取信息失败=============");
		}
	}

	public static byte[] toBytes(String str) {
		if (str == null || str.trim().equals("")) {
			return new byte[0];
		}

		byte[] bytes = new byte[str.length() / 2];
		for (int i = 0; i < str.length() / 2; i++) {
			String subStr = str.substring(i * 2, i * 2 + 2);
			bytes[i] = (byte) Integer.parseInt(subStr, 16);
		}
		return bytes;
	}

	public String receive() {
		byte[] buffer = new byte[1024];
		try {
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp);
			return byteArrayToHexString(dp.getData());
		} catch (IOException e) {
			log.info("============获取信息失败=============");
		}
		return new String(buffer);
	}

	/**
	 * 设备驱动实现本方法，主要用于向串口发送依据通讯协议编码后的数据 区别：可以传入各种类型的参数
	 * 
	 * @param obj
	 */
	public abstract void send(Object obj);

	public abstract void sendLink(String dvcId, String host, int serverPort);

}
