package com.hndfsj.driver.serailport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.TooManyListenersException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hndfsj.driver.HDBaseDriver;
import com.hndfsj.framework.utils.driver.HexStrUtil;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

@SuppressWarnings({ "rawtypes", "unused" })
public class SerialPortReader extends Observable implements Runnable, SerialPortEventListener {
	
	static Logger log=LoggerFactory.getLogger(SerialPortReader.class);
	
	private CommPortIdentifier portId;
	public int delayRead = 100;		// 端口数据准备时间(部分设备回传数据分段时间间隔较大，需对应增大此数值)
	private int numBytes; 			// buffer中的实际数据字节数
	private byte[] readBuffer = new byte[1024]; // 4k的buffer空间,缓存串口读入的数据
	private Enumeration portList;
	private InputStream inputStream;
	private OutputStream outputStream;
	private SerialPort serialPort;
	private Thread readThread;		// 原为static类型
	private boolean isOpen = false;	// 端口是否打开了
	public SerialPortConfig spc;
	
	public Date lastRcvDataTime = new Date();	// 最近一次收取数据的时间（有的设备需要使用时间标记区分接收到的数据）

	/**
	 * 初始化端口操作的参数.
	 * @throws SerialPortException
	 */
	public SerialPortReader(SerialPortConfig spc, Observer observer) {
		this.isOpen = false;
		this.spc = spc;
		this.open(spc);
		this.addObserver(observer);
	}

	/**
	 * 端口是否处于开启状态
	 * @return boolean
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * 根据参数打开串口
	 * @param spc 串口参数配置实例
	 */
	public void open(SerialPortConfig spc) {
		// 如果当前是开启状态，则先关闭串口
		if (isOpen) {
			close();
		}
		try {
			if (spc != null) {
				// 参数初始化
				delayRead = spc.getDelay();
				// 打开端口
				log.info("========初始化串口===="+spc.getPortName()+"========");
				portId = CommPortIdentifier.getPortIdentifier(spc.getPortName());
				serialPort = (SerialPort) portId.open("SerialReader", spc.getTimeOut());
				inputStream = serialPort.getInputStream();
				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
				serialPort.setSerialPortParams(spc.getBaudRate(), spc.getDataBit(), spc.getStopBit(), spc.getParityBit());
				isOpen = true;
				log.info("========初始化串口成功===="+spc.getPortName()+"========");
			}
		} catch (PortInUseException e) {
			// "端口"+serialParams.get( PARAMS_PORT ).toString()+"已经被占用";
		} catch (TooManyListenersException e) {
			// "端口"+serialParams.get( PARAMS_PORT ).toString()+"监听者过多";
		} catch (UnsupportedCommOperationException e) {
			// "端口操作命令不支持";
		} catch (NoSuchPortException e) {
			// "端口"+serialParams.get( PARAMS_PORT ).toString()+"不存在";
		} catch (IOException e) {
			// "打开端口"+serialParams.get( PARAMS_PORT ).toString()+"失败";
		}
		Thread readThread = new Thread(this);
		readThread.start();
	}

	public void run() {
		try {
			Thread.sleep(50);
			start();
		} catch (InterruptedException e) {
		}
	}

	/**
	 * 启动读取串口数据的线程
	 */
	private void start() {
		try {
			outputStream = serialPort.getOutputStream();
			readThread = new Thread(this);
			readThread.start();
		} catch (Exception e) {
		}
	} 

	/**
	 * 向串口发送数据(字符串)
	 * @param message
	 */
	public void send(String message) {
		try {
			Thread.sleep(4);
			if (serialPort != null && message != null && message.length() != 0) {
				outputStream.write(message.getBytes()); // 往串口发送数据，是双向通讯的。
				System.out.println("向串口发送数据:" + message);
			}
		} catch (IOException e) {
			e.getStackTrace();
		} catch (InterruptedException e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * 向串口发送数据(hex)
	 * @param hexMessage
	 */
	public void sendHex(String hexMessage) {
		try {
			Thread.sleep(4);
			if (serialPort != null && hexMessage != null && hexMessage.length() != 0) {
				outputStream.write(HexStrUtil.getHexBytes(hexMessage)); // 往串口发送数据，是双向通讯的。
				System.out.println("向串口发送数据:" + hexMessage);
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
				serialPort.notifyOnDataAvailable(false);
				serialPort.removeEventListener();
				inputStream.close();
				serialPort.close();
				isOpen = false;
			} catch (IOException ex) {
				System.err.println("关闭串口失败！");
			}
		}
	}

	/* 
	 * 串口事件监听处理
	 * @see gnu.io.SerialPortEventListener#serialEvent(gnu.io.SerialPortEvent)
	 */
	public void serialEvent(SerialPortEvent event) {
		try {
			Thread.sleep(delayRead);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		switch (event.getEventType()) {
		case SerialPortEvent.BI: 	// 10
		case SerialPortEvent.OE: 	// 7
		case SerialPortEvent.FE: 	// 9
		case SerialPortEvent.PE: 	// 8
		case SerialPortEvent.CD: 	// 6
		case SerialPortEvent.CTS: 	// 3
		case SerialPortEvent.DSR: 	// 4
		case SerialPortEvent.RI: 	// 5
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY: 	// 2
			break;
		case SerialPortEvent.DATA_AVAILABLE: 		// 1
			try {
				// 多次读取,将所有数据读入
				while (inputStream.available() > 0) {
					numBytes = inputStream.read(readBuffer);
				}
				// 打印接收到的字节数据的ASCII码
//				for (int i = 0; i < numBytes; i++) {
//					System.out.println("msg[" + numBytes + "]: [" + readBuffer[i] + "]:"+(char)readBuffer[i]);
//				}
				numBytes = inputStream.read(readBuffer);
				changeMessage(readBuffer, numBytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
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
	/**
	 * observer pattern
	 * 将buffer中的空字节删除后再发送更新消息,通知观察者进行消息处理
	 * @param message
	 * @param length
	 */
	private void changeMessage(byte[] message, int length) {
//		byte[] temp = new byte[length];
//		System.arraycopy(message, 0, temp, 0, length);
//		setChanged();
//		notifyObservers(temp);

		this.lastRcvDataTime = new Date();
		setChanged();
		// 转为String再通知观察者
		notifyObservers(byteArrayToHexString(readBuffer));
	}

	
	//--------------------暂未使用的功能--------------------
	/**
	 * 列出所有串口
	 */
	private static void listPorts() {
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier portIdentifier = (CommPortIdentifier) portEnum.nextElement();
		}
	}

	/**
	 * 根据端口类型号获取端口类型名称
	 * @param portType
	 * @return String
	 */ 
	private static String getPortTypeName(int portType) {
		switch (portType) {
			case CommPortIdentifier.PORT_I2C:
				return "I2C";
			case CommPortIdentifier.PORT_PARALLEL:
				return "Parallel";
			case CommPortIdentifier.PORT_RAW:
				return "Raw";
			case CommPortIdentifier.PORT_RS485:
				return "RS485";
			case CommPortIdentifier.PORT_SERIAL:
				return "Serial";
			default:
				return "unknown type";
		}
	}

	private HashSet<CommPortIdentifier> getAvailableSerialPorts() {
		HashSet<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
		Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
		while (thePorts.hasMoreElements()) {
			CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
			switch (com.getPortType()) {
			case CommPortIdentifier.PORT_SERIAL:
				try {
					CommPort thePort = com.open("CommUtil", 50);
					thePort.close();
					h.add(com);
				} catch (PortInUseException e) {
					System.err.println("串口 " + com.getName() + " 被占用！");
				} catch (Exception e) {
					System.err.println("打开串口失败：" + com.getName() + e);
				}
			}
		}
		return h;
	}
}