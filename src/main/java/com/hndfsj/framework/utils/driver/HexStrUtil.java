package com.hndfsj.framework.utils.driver;

import java.io.UnsupportedEncodingException;

public class HexStrUtil {
	
	private static String hexString = "0123456789ABCDEF";

	public static String getHexResult(String targetStr) {
		StringBuilder hexStr = new StringBuilder();
		int len = targetStr.length();
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				char tempStr = targetStr.charAt(i);
				String data = String.valueOf(tempStr);
				if (isCN(data)) {
					hexStr.append(encodeCN(data));
				} else {
					hexStr.append(encodeStr(data));
				}
			}
		}
		return hexStr.toString().toUpperCase();
	}
	
	private static String encodeCN(String data) {
		byte[] bytes;
		try {
			bytes = data.getBytes("gbk");
			StringBuilder sb = new StringBuilder(bytes.length * 2);

			for (int i = 0; i < bytes.length; i++) {
				sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
				sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String encodeStr(String data) {
		String result = "";
		byte[] bytes;
		try {
			bytes = data.getBytes("gbk");
			for (int i = 0; i < bytes.length; i++) {
				result += Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1);
			}
			return result;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 判定是否为中文汉字
	 * @param data
	 * @return
	 */
	private static boolean isCN(String data) {
		boolean flag = false;
		String regex = "^[\u4e00-\u9fa5]*$";
		if (data.matches(regex)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 将  String 字符串转换成格式为  {(byte)0x30,(byte)0x3b} 类型的 16 进制字节数组
	 * 不同于  str.getBytes()
	 * @param inputStr
	 * @return  byte[]
	 */
	public static byte[] getHexBytes(String str) {
		byte[] result = new byte[str.length() / 2];
		for (int i = 0; i < str.length() / 2; ++i)
			result[i] = (byte) (str.charAt(i) & 0xff);
		return result;
	}
	
	/**
	 * 将 16 进制串转为 10 进制数字
	 * @param hexStr
	 * @return 10进制数字
	 */
	public static int hex2Decimal(String hexStr) {
		int i = 0;
		int sum = 0;
		while (i < hexStr.length()) {
			char c = hexStr.charAt(i);
			int n = c - '0'; //9以内
			if (c >= 'a' && c <= 'f') {
				n = c - 'a' + 10;//a-f之间
			}
			sum = (sum << 4) + n;
			i++;
		}
		return sum;
	}
	
}
