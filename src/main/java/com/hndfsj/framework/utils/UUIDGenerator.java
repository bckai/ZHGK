package com.hndfsj.framework.utils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author ibm
 * 
 */
public class UUIDGenerator {
	// 可选字符
	private static String codes = "123456789ABCDEFGHJKMNPQRSTUVWXYZ";
	private static Random r = new Random();

	/**
	 * 得到一个随机的32位的UUID值
	 * 
	 * @return
	 */
	public static String UUIDValue() {
		// 随即得到一个uuid的String值
		String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");
		return uuid;
	}

	/**
	 * 编号
	 * 
	 * @return
	 */
	public static String UUIDNumber() {
		return DateUtils.formatDate(new Date(), DateUtils.DATETIME_YMD_FORMAT) + randomChar() + randomChar()
				+ randomChar() + randomChar() + randomChar();
	}

	// 随机生成一个字符
	private static char randomChar() {
		int index = r.nextInt(codes.length());
		return codes.charAt(index);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(UUIDNumber());
		}
	}
}
