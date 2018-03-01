/**
* hndfsj ccls project
*/

package com.hndfsj.framework.config;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * ccls项目properties配置文件读取类
 *
 * @author wfq
 * @date 2010-9-9 上午10:34:27
 */
public class MessageResource {

	private static PropertyResourceBundle properTyResourceBundle;

	private MessageResource() {
	}

	static {
		properTyResourceBundle = (PropertyResourceBundle) ResourceBundle.getBundle("message/messageResources", Locale.getDefault());
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

	public static void main(String[] args) {
		System.out.println(getString("resource_assign_success"));
	}
}

