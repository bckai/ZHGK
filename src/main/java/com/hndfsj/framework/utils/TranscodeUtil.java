/**
 * hndfsj xxb project
 */

package com.hndfsj.framework.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 转码工具类
 * 
 * @author xushixuan
 * @date 2010-12-18 上午10:53:48
 */
public class TranscodeUtil {

	/**
	 * 
	 *将ISO8859_1编码的字符串转化为UTF-8编码的字符串，主要用来处理中文显示乱码的问题
	 * 
	 * @param ISO8859_1str
	 *            通过ISO8859_1编码的字符串
	 * @return 通过UTF-8编码的字符串
	 */
	public static String UTF_8FormISO8859_1(String ISO8859_1str) {
		if (StringUtils.isBlank(ISO8859_1str)) {
			return "";
		} else {
			try {
				return new String(ISO8859_1str.getBytes("ISO-8859-1"), "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
	}

	/**
	 * 将UTF-8编码的字符串转化为ISO8859_1编码的字符串，主要用来处理中文显示乱码的问题
	 * 
	 * @param UTF
	 *            通过UTF编码的字符串
	 * @return 通过ISO8859_1编码的字符串
	 */
	public static String ISO8859_1FromUTF_8(String UTF_8) {
		if (StringUtils.isBlank(UTF_8)) {
			return "";
		} else {
			try {
				return new String(UTF_8.getBytes("UTF-8"), "ISO-8859-1");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 将UTF-8编码的字符串转化为GB2312编码的字符串，主要用来处理中文显示乱码的问题
	 * 
	 * @param UTF
	 *            -8 通过UTF-8编码的字符串
	 * @return 通过GB2312编码的字符串
	 */
	public static String GB2312FromUTF_8(String UTF_8) {
		if (StringUtils.isBlank(UTF_8)) {
			return "";
		} else {
			try {
				return new String(UTF_8.getBytes("UTF-8"), "GB2312");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 将GB2312编码的字符串转化为UTF-8编码的字符串，主要用来处理中文显示乱码的问题
	 * 
	 * @param GB2312
	 *            通过GB2312编码的字符串
	 * @return 通过UTF-8编码的字符串
	 */
	public static String UTF_8FromGB2312(String GB2312) {
		if (StringUtils.isBlank(GB2312)) {
			return "";
		} else {
			try {
				return new String(GB2312.getBytes("GB2312"), "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 
	 * 将ISO-8859-1编码的字符串转化为GBK编码的字符串,
	 * 
	 * @param ISO8859_1
	 * @return
	 */
	public static String GBKFromISO8859_1(String ISO8859_1) {
		if (StringUtils.isBlank(ISO8859_1)) {
			return "";
		} else {
			try {
				return new String(ISO8859_1.getBytes("ISO-8859-1"), "GBK");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 
	 * 将GBK编码的字符串转化为ISO-8859-1编码的字符串,
	 * 
	 * @param GBK
	 * @return
	 */
	public static String ISO8859_1FromGBK(String GBK) {
		if (StringUtils.isBlank(GBK)) {
			return "";
		} else {
			try {
				return new String(GBK.getBytes("GBK"), "ISO-8859-1");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 
	 * 将UTF_8编码的字符串转化为GBK编码的字符串,
	 * 
	 * @param UTF_8
	 * @return
	 */
	public static String GBKFromUTF_8(String UTF_8) {
		if (StringUtils.isBlank(UTF_8)) {
			return "";
		} else {
			try {
				return new String(UTF_8.getBytes("UTF-8"), "GBK");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 
	 * 将GBK编码的字符串转化为UTF_8编码的字符串,
	 * 
	 * @param GBK
	 * @return
	 */
	public static String UTF_8FromGBK(String GBK) {
		if (StringUtils.isBlank(GBK)) {
			return "";
		} else {
			try {
				return new String(GBK.getBytes("GBK"), "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 将GB2312编码的字符串转化为ISO8859_1编码的字符串
	 * 
	 * @param GB2312
	 *            GB2312编码的字符串
	 * @return ISO8859_1编码的字符串
	 */
	public static String ISO8859_1FormGB2312(String GB2312) {
		if (StringUtils.isBlank(GB2312)) {
			return "";
		} else {
			try {
				return new String(GB2312.getBytes("GB2312"), "ISO-8859-1");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 将ISO8859_1编码的字符串转化为 GB2312编码的字符串
	 * 
	 * @param ISO8859_1
	 *            ISO8859_1编码的字符串
	 * @return GB2312编码的字符串
	 */
	public static String GB2312FormISO8859_1(String ISO8859_1) {
		if (StringUtils.isBlank(ISO8859_1)) {
			return "";
		} else {
			try {
				return new String(ISO8859_1.getBytes("ISO-8859-1"), "GB2312");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 将GB2312编码的字符串转化为GBK编码的字符串
	 * 
	 * @param GB2312
	 *            GB2312编码的字符串
	 * @return GBK编码的字符串
	 */
	public static String GBKFormGB2312(String GB2312) {
		if (StringUtils.isBlank(GB2312)) {
			return "";
		} else {
			try {
				return new String(GB2312.getBytes("GB2312"), "GBK");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 将GBK编码的字符串转化为 GB2312编码的字符串
	 * 
	 * @param GBK
	 *            GBK编码的字符串
	 * @return GB2312编码的字符串
	 */
	public static String GB2312FormGBK(String GBK) {
		if (StringUtils.isBlank(GBK)) {
			return "";
		} else {
			try {
				return new String(GBK.getBytes("GBK"), "GB2312");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

}
