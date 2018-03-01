package com.hndfsj.driver.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.TooManyListenersException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hndfsj.framework.utils.driver.HexStrUtil;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

@SuppressWarnings("unused")
public class TcpReader extends Observable implements Runnable {

	static Logger log = LoggerFactory.getLogger(TcpReader.class);

	public int delayRead = 100; // 端口数据准备时间(部分设备回传数据分段时间间隔较大，需对应增大此数值)
	private int numBytes; // buffer中的实际数据字节数
	private byte[] readBuffer = new byte[1024]; // 4k的buffer空间,缓存串口读入的数据
	private InputStream inputStream;// 读
	private OutputStream outputStream;// 写
	private Thread readThread; // 原为static类型
	private Socket socket;
	private boolean isOpen = false; // 端口是否打开了
	public TcpConfig tcpConfig;

	public Date lastRcvDataTime = new Date(); // 最近一次收取数据的时间（有的设备需要使用时间标记区分接收到的数据）

	/**
	 * 初始化端口操作的参数.
	 * 
	 * @throws SerialPortException
	 */
	public TcpReader(TcpConfig tcpConfig, Observer observer) {
		this.isOpen = false;
		this.tcpConfig = tcpConfig;
		this.open(tcpConfig);
		this.addObserver(observer);
	}

	/**
	 * 端口是否处于开启状态
	 * 
	 * @return boolean
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * 根据参数打开串口
	 * 
	 * @param spc
	 *            串口参数配置实例
	 */
	public void open(TcpConfig spc) {
		// 如果当前是开启状态，则先关闭串口
		if (isOpen) {
			close();
		}
		if (spc != null) {
			socket = new Socket(); // 客户机
			try {
				socket.setTcpNoDelay(true);
				socket.setReuseAddress(true);
				socket.setSoTimeout(30000);
				socket.setSoLinger(true, 5);
				socket.setSendBufferSize(1024);
				socket.setReceiveBufferSize(1024);
				socket.setKeepAlive(true);
				//socket.connect(new InetSocketAddress(spc.getIp(), spc.getPort()), 2000);
			} catch (Exception e) {
				log.info("========初始化失败====" + spc.getIp() + ":" + spc.getPort() + "========");
			}
			isOpen = true;
			log.info("========初始化串口失败====" + spc.getIp() + ":" + spc.getPort() + "========");
			Thread readThread = new Thread(this);
			readThread.start();
		}
	}

	public void run() {
		try {
			Thread.sleep(50);
			start();
		} catch (InterruptedException e) {
		}
	}

	/**
	 * 启动读取数据的线程
	 */
	private void start() {
		try {
			outputStream = socket.getOutputStream();
			readThread = new Thread(this);
			readThread.start();
		} catch (Exception e) {
		}
	}

	/**
	 * 向串口发送数据(字符串)
	 * 
	 * @param message
	 */
	public void send(String message) {
		try {
			Thread.sleep(4);
			if (socket != null && message != null && message.length() != 0) {
				outputStream.write(message.getBytes()); // 往串口发送数据，是双向通讯的。
				System.out.println("向TCP发送数据:" + message);
			}
		} catch (IOException e) {
			e.getStackTrace();
		} catch (InterruptedException e) {
			e.getStackTrace();
		}
	}

	/**
	 * 向串口发送数据(hex)
	 * 
	 * @param hexMessage
	 */
	public void sendHex(String hexMessage) {
		try {
			Thread.sleep(4);
			if (socket != null && hexMessage != null && hexMessage.length() != 0) {
				outputStream.write(HexStrUtil.getHexBytes(hexMessage)); // 往串口发送数据，是双向通讯的。
				System.out.println("向TCP发送数据:" + hexMessage);
			}
		} catch (IOException e) {
			e.getStackTrace();
		} catch (InterruptedException e) {
			e.getStackTrace();
		}
	}

	/**
	 * 关闭串口
	 */
	public void close() {
		if (isOpen) {
			try {
				inputStream.close();
				socket.close();
				isOpen = false;
			} catch (IOException ex) {
				System.err.println("关闭串口失败！");
			}
		}
	}

	/*
	 * 事件监听处理
	 */
	public void serialEvent(SerialPortEvent event) {
		try {
			Thread.sleep(delayRead);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		switch (event.getEventType()) {
		case SerialPortEvent.BI: // 10
		case SerialPortEvent.OE: // 7
		case SerialPortEvent.FE: // 9
		case SerialPortEvent.PE: // 8
		case SerialPortEvent.CD: // 6
		case SerialPortEvent.CTS: // 3
		case SerialPortEvent.DSR: // 4
		case SerialPortEvent.RI: // 5
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2
			break;
		case SerialPortEvent.DATA_AVAILABLE: // 1
			try {
				// 多次读取,将所有数据读入
				while (inputStream.available() > 0) {
					numBytes = inputStream.read(readBuffer);
				}
				// 打印接收到的字节数据的ASCII码
				// for (int i = 0; i < numBytes; i++) {
				// System.out.println("msg[" + numBytes + "]: [" + readBuffer[i] +
				// "]:"+(char)readBuffer[i]);
				// }
				numBytes = inputStream.read(readBuffer);
				changeMessage(readBuffer, numBytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	/**
	 * observer pattern 将buffer中的空字节删除后再发送更新消息,通知观察者进行消息处理
	 * 
	 * @param message
	 * @param length
	 */
	private void changeMessage(byte[] message, int length) {
		// byte[] temp = new byte[length];
		// System.arraycopy(message, 0, temp, 0, length);
		// setChanged();
		// notifyObservers(temp);
		this.lastRcvDataTime = new Date();
		setChanged();
		// 转为String再通知观察者
		notifyObservers(new String(message));
	}
}