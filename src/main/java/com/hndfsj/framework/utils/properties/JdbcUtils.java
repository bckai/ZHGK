package com.hndfsj.framework.utils.properties;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * 配置文件内容读取类
 * 
 */
public class JdbcUtils {
	private static PropertyResourceBundle properTyResourceBundle;

	public JdbcUtils() {
	}

	static {
		properTyResourceBundle = (PropertyResourceBundle) ResourceBundle
				.getBundle("jdbc", Locale.getDefault());
	}

	/**
	 * 根据key获得对应的value
	 * 
	 * @param strPropertyName
	 *            key
	 * @return String
	 */
	public static String getString(String strPropertyName) {
		try {
			return properTyResourceBundle.getString(strPropertyName);
		} catch (Exception e) {
			return strPropertyName;
		}
	}

	public static String getString(String strPropertyName, Object... obj) {
		String str = properTyResourceBundle.getString(strPropertyName);
		if (str == null) {
			return strPropertyName;
		}
		return MessageFormat.format(str, obj);
	}

	public static PropertyResourceBundle getBundle() {
		return properTyResourceBundle;
	}

	/*public static void main(String[] args) {
		System.out.print(Resource.getString("hndfsj"));
	}*/
}
