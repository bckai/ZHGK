package com.hndfsj.framework.utils;

import java.security.MessageDigest;

import javax.crypto.Cipher;

import com.hndfsj.framework.config.RWAdminConfiguration;

/**
 * 常用工具类
 * 
 * @author ibm
 */
public class CryptUtil {

	/**
	 * MD5加密(32位)
	 * 
	 * @param message
	 *            要加密的字符串
	 * @return 返回加密后的字符串
	 */
	public final static String encoderByMd5For32Bit(String message) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			//if (message != null && !"".equals(message)) {
				byte[] strTemp = message.getBytes();
				// MD5计算方法
				MessageDigest mdTemp = MessageDigest.getInstance("MD5");
				mdTemp.update(strTemp);
				byte[] md = mdTemp.digest();
				int j = md.length;
				char str[] = new char[j * 2];
				int k = 0;
				for (int i = 0; i < j; i++) {
					byte byte0 = md[i];
					str[k++] = hexDigits[byte0 >>> 4 & 0xf];
					str[k++] = hexDigits[byte0 & 0xf];
				}
				return new String(str);
			/*} else {
				return null;*/
			//}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * MD5加密(16位)
	 * 
	 * @param instr
	 *            要加密的字符串
	 * @return 返回加密后的字符串
	 */
	public final static String encoderByMd5For16Bit(String instr) {
		return encoderByMd5For32Bit(instr).substring(8, 24);
	}

	/**
	 * 根据配置文件的配置加密 可配置AES 和 MD5方式
	 * @param in
	 * @param opmod
	 * @return
	 * @throws Exception 
	 */
	public final static String cryptByConfig(String in) throws Exception{
		String cryptType = RWAdminConfiguration.getInstance().getAppPropsValue("admin.password.crypttype", "MD5");
		if(cryptType.equals("MD5")){
			return encoderByMd5For32Bit(in);
		}else if(cryptType.equals("AES")) {
			String key = RWAdminConfiguration.getInstance().getAppPropsValue("admin.password.aes.key","hndfsj");
			return PrivateKey.crypt(in.getBytes(), key, Cipher.ENCRYPT_MODE);
		}else {
			//不加密
			return in;
		}
		
	}
	/**
	 * 根据配置文件的配置解密 只能在配置文件是AES加密方式下有用
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public final static String decryptByConfig(String in) throws Exception{
		String cryptType = RWAdminConfiguration.getInstance().getAppPropsValue("admin.password.crypttype", "MD5");
		if(cryptType.equals("AES")) {
			String key = RWAdminConfiguration.getInstance().getAppPropsValue("admin.password.aes.key","hndfsj");
			return PrivateKey.crypt(in.getBytes(), key, Cipher.DECRYPT_MODE);
		}else {
			//不解密
			return in;
		}
		}
	
	public static void main(String[] args) throws Exception {
		String cryptByConfig = cryptByConfig("1a_bc");
		System.out.println(cryptByConfig);
		System.out.println(encoderByMd5For32Bit("111111"));
		
	}
}
