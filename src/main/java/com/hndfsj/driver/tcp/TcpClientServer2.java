package com.hndfsj.driver.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract  class TcpClientServer2 {
	public TcpClientServer2(){}
	static Logger log = LoggerFactory.getLogger(TcpClientServer2.class);
	protected String dvcId;
	
	private static Socket socket;
	
	static {
		try {
			socket= new Socket();
			socket.setReuseAddress(true);
			socket.setSoTimeout(30000);
			socket.setSoLinger(true, 5);
			socket.setSendBufferSize(1024);
			socket.setReceiveBufferSize(1024);
			socket.setKeepAlive(true);
			socket.setTcpNoDelay(true);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据设备初始化串口
	 * 
	 * @param dvcId
	 */
	public void driverInit(String dvcId) {
		this.dvcId = dvcId;
	}

	public static void main(String[] args) {  
	    String reqData = "<00AD\r";  
	    String IP = "10.141.151.150";  
	    String port = "4001";  
	    OutputStream out = null; // 写
		InputStream in = null; // 读
		String respData = null; // 响应报文
		Socket socket = new Socket(); // 客户机
		try {
			socket.setTcpNoDelay(true);
			socket.setReuseAddress(true);
			socket.setSoTimeout(30000);
			socket.setSoLinger(true, 5);
			socket.setSendBufferSize(1024);
			socket.setReceiveBufferSize(1024);
			socket.setKeepAlive(true);
			socket.connect(new InetSocketAddress(IP, Integer.parseInt(port)), 3000);
			/**
			 * 发送TCP请求
			 */
			out = socket.getOutputStream();
			out.write(reqData.getBytes());
			Thread.sleep(4000);// 睡眠1000毫秒
			/**
			 * 接收TCP响应
			 */
			in = socket.getInputStream();

			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			String info = null;
			while ((info = br.readLine()) != null) {
				System.out.println("我是服务器，客户端说：" + info);
				respData = info;
			}
			socket.shutdownInput();// 关闭输入流
		} catch (Exception e) {
			System.out.println("与[" + IP + ":" + port + "]通信遇到异常,堆栈信息如下");
			e.printStackTrace();
		} finally {
			if (null != socket && socket.isConnected() && !socket.isClosed()) {
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("关闭客户机Socket时发生异常,堆栈信息如下");
					e.printStackTrace();
				}
			}
		}
	    System.out.println("=============================================================================");  
	    System.out.println(respData);  
	    System.out.println("=============================================================================");  
	} 
	
	
	public String send(String IP, String port, String reqData) {
		return TCPUtil.sendTCPRequest(IP, port, reqData);
		/*OutputStream out = null; // 写
		InputStream in = null; // 读
		String respData = null; // 响应报文
		try {
			socket.connect(new InetSocketAddress(IP, Integer.parseInt(port)), 3000);
			*//**
			 * 发送TCP请求
			 *//*
			out = socket.getOutputStream();
			out.write(reqData.getBytes());
			Thread.sleep(10000);// 睡眠1000毫秒
			*//**
			 * 接收TCP响应
			 *//*
			in = socket.getInputStream();

			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			String info = null;
			System.out.println("我是服务器，客户端说：" + info);
			while ((info = br.readLine()) != null) {
				System.out.println("我是服务器，客户端说：" + info);
				respData = info;
			}
			socket.shutdownInput();// 关闭输入流
		} catch (Exception e) {
			System.out.println("与[" + IP + ":" + port + "]通信遇到异常,堆栈信息如下");
			e.printStackTrace();
		} finally {
			if (null != socket && socket.isConnected() && !socket.isClosed()) {
				try {
					//socket.close();
				} catch (IOException e) {
					System.out.println("关闭客户机Socket时发生异常,堆栈信息如下");
					e.printStackTrace();
				}
			}
		}
		return respData;*/
	}

	/**
	 * 通过ASCII码将十进制的字节数组格式化为十六进制字符串
	 * 
	 * @see 该方法会将字节数组中的所有字节均格式化为字符串
	 * @see 使用说明详见<code>formatToHexStringWithASCII(byte[], int, int)</code>方法
	 */
	private static String formatToHexStringWithASCII(byte[] data) {
		return formatToHexStringWithASCII(data, 0, data.length);
	}

	private static String formatToHexStringWithASCII(byte[] data, int offset, int length) {
		int end = offset + length;
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		sb.append("\r\n------------------------------------------------------------------------");
		boolean chineseCutFlag = false;
		for (int i = offset; i < end; i += 16) {
			sb.append(String.format("\r\n%04X: ", i - offset)); // X或x表示将结果格式化为十六进制整数
			sb2.setLength(0);
			for (int j = i; j < i + 16; j++) {
				if (j < end) {
					byte b = data[j];
					if (b >= 0) { // ENG ASCII
						sb.append(String.format("%02X ", b));
						if (b < 32 || b > 126) { // 不可见字符
							sb2.append(" ");
						} else {
							sb2.append((char) b);
						}
					} else { // CHA ASCII
						if (j == i + 15) { // 汉字前半个字节
							sb.append(String.format("%02X ", data[j]));
							chineseCutFlag = true;
							String s = new String(data, j, 2);
							sb2.append(s);
						} else if (j == i && chineseCutFlag) { // 后半个字节
							sb.append(String.format("%02X ", data[j]));
							chineseCutFlag = false;
							String s = new String(data, j, 1);
							sb2.append(s);
						} else {
							sb.append(String.format("%02X %02X ", data[j], data[j + 1]));
							String s = new String(data, j, 2);
							sb2.append(s);
							j++;
						}
					}
				} else {
					sb.append("   ");
				}
			}
			sb.append("| ");
			sb.append(sb2.toString());
		}
		sb.append("\r\n------------------------------------------------------------------------");
		return sb.toString();
	}

	/**
	 * 设备驱动实现本方法，主要用于向串口发送依据通讯协议编码后的数据 区别：可以传入各种类型的参数
	 * 
	 * @param obj
	 */
	public abstract void send(Object obj);

	public abstract void sendLink(String dvcId, String host, int serverPort);

}
