package com.hndfsj.driver.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Observable;
import java.util.Set;

public class NioUdpServer extends Observable implements Runnable {

	private int listenPort = 0; // 需要监听的端口

	/** 发送过来数据的客户端信息：IP和端口 */
	private String clientIp = "";
	private int clientPort = 0;
	private String recvData = "";
	private String[] ObPara = new String[3];

	public NioUdpServer(int listenPort) {
		this.listenPort = listenPort;
	}

	@Override
	public void run() {
		System.out.println("开始监听端口:" + this.listenPort);
		try {
			Selector selector = Selector.open();
			DatagramChannel channel = DatagramChannel.open();
			channel.configureBlocking(false);
			DatagramSocket socket = channel.socket();
			socket.bind(new InetSocketAddress("10.141.151.150", 4001));
			channel.register(selector, SelectionKey.OP_READ);

			ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
			while (true) {
				int n = selector.select();
				if (n == 0) {
					continue;
				}
				Set<SelectionKey> readyKeys = selector.selectedKeys();
				for (SelectionKey key : readyKeys) {
					readyKeys.remove(key);
					if (key.isReadable()) {
						DatagramChannel dc = (DatagramChannel) key.channel();
						// 接收来自任意一个Client的数据报
						InetSocketAddress client = (InetSocketAddress) dc.receive(receiveBuffer); 
						key.interestOps(SelectionKey.OP_READ);
						this.clientIp = client.getAddress().getHostAddress();
						this.clientPort = client.getPort();
						receiveBuffer.flip();
						DataInputStream dis = new DataInputStream(new ByteArrayInputStream(receiveBuffer.array()));
						BufferedReader d = new BufferedReader(new InputStreamReader(dis));
						this.recvData = d.readLine();
						receiveBuffer.clear();
						
						System.out.println("UDP端口" + this.clientPort + "收到数据：" + recvData);
						
						// 通知观察者们（各个网络通信的设备驱动实例）
						setChanged();
						ObPara[0] = this.clientIp;
						ObPara[1] = Integer.toString(this.clientPort);
						ObPara[2] = this.recvData;
						notifyObservers(ObPara);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}