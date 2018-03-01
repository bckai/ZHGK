
package com.hndfsj.framework.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;

/**
 * 请在此加入你的功能说明
 * 
 * @author 王富强
 * @date 2009-8-24 上午09:10:35
 */
public class PrivateKey {
	/**
	 *固定的密钥
	 */
	private static final byte[] KEY_SPEC = {'c','f','_','c','h','a','n','g','e','_','m','o','b','i','l','e'};
	/**
	 * cf_change_mobile
	 */
	private static final SecretKeySpec SECRET_KEY_SPEC = new javax.crypto.spec.SecretKeySpec(
			KEY_SPEC, "AES");

	
	/**
	 * 加密文件infilename,输出加密后的文件outfilename 返回AES加密密钥
	 * 
	 * @param infilename
	 * @param outfilename
	 * @return
	 * @throws Exception
	 */
	public static byte[] AesEnCrypt(String infilename, String outfilename)
			throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);

		Cipher cipher = Cipher.getInstance("AES");

		InputStream in = new FileInputStream(infilename);
		DataOutputStream out = new DataOutputStream(new FileOutputStream(
				outfilename));

		cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY_SPEC);
		crypt(in, out, cipher);

		in.close();
		out.close();
		return SECRET_KEY_SPEC.getEncoded();
	}

	/**
	 * 解密文件infilename,输出明文文件outfilename
	 * 
	 * @param infilename
	 * @param outfilename
	 */
	public static void AesDeCrypt(String infilename, String outfilename) {

		try {

			Cipher cipher = Cipher.getInstance("AES");

			File file = new File(outfilename);
			if(!file.exists()){
				file.createNewFile();
			}
			OutputStream out = new FileOutputStream(file);
			DataInputStream in = new DataInputStream(new FileInputStream(
					infilename));

			cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY_SPEC);
			crypt(in, out, cipher);

			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);

		} catch (GeneralSecurityException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}
	public static void AesDeCrypt(InputStream in,OutputStream out){
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY_SPEC);
			crypt(in, out, cipher);

			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	/**
	 * 用Cipher处理流(加解密)
	 * 
	 * @param in
	 * @param out
	 * @param cipher
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	private static void crypt(InputStream in, OutputStream out, Cipher cipher)
			throws IOException, GeneralSecurityException {
		int blockSize = cipher.getBlockSize();
		int outputSize = cipher.getOutputSize(blockSize);
		byte[] inBytes = new byte[blockSize];
		byte[] outBytes = new byte[outputSize];
		int inLength = 0;
		boolean more = true;
		while (more) {
			inLength = in.read(inBytes);
			if (inLength == blockSize) {
				int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
				out.write(outBytes, 0, outLength);
			} else {
				more = false;
			}
		}
		if (inLength > 0)
			outBytes = cipher.doFinal(inBytes, 0, inLength);
		else
			outBytes = cipher.doFinal();
		out.write(outBytes);
	}

	/**
	 * 加解密
	 * @param in 输入字符
	 * @param key 密钥
	 * @param opmod 加密 解密
	 * @return
	 * @throws Exception
	 */
	public static String crypt(byte[] in ,String key, int opmod) throws Exception{
		byte[] keyByte = key.getBytes();
		SecretKeySpec keySpec = new javax.crypto.spec.SecretKeySpec(keyByte, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(opmod, keySpec);
		byte[] cipherText = cipher.doFinal(in);
		return new String(cipherText,"UTF-8");
		
	}
	public static String crypt(String in, Cipher cipher) throws IOException,
			GeneralSecurityException {
		byte[] outBytes = cipher.doFinal(in.getBytes());
		
		return new String(outBytes);
	}

	public static void main(String[] args) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY_SPEC);
		byte[] cipherText = cipher.doFinal("wangfuqiang".getBytes());
		String after1 = new String(cipherText);
		System.out.println("用私钥加密完成：" + after1);
		
		
		Cipher cipher2 = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher2.init(Cipher.DECRYPT_MODE, SECRET_KEY_SPEC);
		byte[] cipherText2 = cipher2.doFinal(cipherText);
		String after2 = new String(cipherText2);
		System.out.println("用私钥jie密完成：" + after2);
	}

//	public void testPrivatekey() throws UnsupportedEncodingException,
//			NoSuchAlgorithmException, NoSuchPaddingException,
//			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//		String before = "asdf";
//		byte[] plainText = before.getBytes("UTF8");
//
//		// 1步**********************************************************************
//		System.out.println("Start generate AES key.");
//		// 得到一个使用AES算法的KeyGenerator的实例
//		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//		// 定义密钥长度128位
//		keyGen.init(128);
//		// 通过KeyGenerator产生一个key（密钥算法刚才已定义，为AES）
//		SecretKey key = keyGen.generateKey();
//		System.out.println("Finish generating AES key." + key);
//
//		// 2步**********************************************************************
//		// 获得一个私钥加密类Cipher，定义Cipher的基本信息：ECB是加密方式，PKCS5Padding是填充方法
//		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//		System.out.println("\n" + cipher.getProvider().getInfo());
//
//		// 3步**********************************************************************
//		// 使用私钥加密
//		System.out.println("\n用私钥加密...");
//		// 把刚才生成的key当作参数，初始化使用刚才获得的私钥加密类，Cipher.ENCRYPT_MODE意思是加密
//		cipher.init(Cipher.ENCRYPT_MODE, key);
//
//		// 私钥加密类Cipher进行加密，加密后返回一个字节流byte[]
//		byte[] cipherText = cipher.doFinal(plainText);
//
//		// 以UTF8格式把字节流转化为String
//		String after1 = new String(cipherText, "UTF8");
//		System.out.println("用私钥加密完成：" + after1);
//
//		// 4步**********************************************************************
//		// 使用私钥对刚才加密的信息进行解密，看看是否一致，Cipher.DECRYPT_MODE意思是解密钥
//		System.out.println("\n用私钥解密...");
//		cipher.init(Cipher.DECRYPT_MODE, key);
//
//		// 对刚才私钥加密的字节流进行解密，解密后返回一个字节流byte[]
//		byte[] newPlainText = cipher.doFinal(cipherText);
//
//		String after2 = new String(newPlainText, "UTF8");
//		System.out.println("用私钥解密完成：" + after2);
//	}
}
