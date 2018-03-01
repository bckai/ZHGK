/**
 * hndfsj ccls project
 */

package com.hndfsj.framework.config;

/**
 * 项目枚举类型类
 * 
 * @date 2010-9-9 上午10:50:49
 */
public class EnumType {

	/**
	 * 用户类型
	 * 
	 * @copyright {@link www.hndfsjsoft.com}
	 * @author haoyingshuai@hndfsj.com,haoluziqi@126|gmail.com
	 * @version 2013-1-6 上午10:44:45
	 * @see com.hndfsj.config.UserType
	 * 
	 */
	public enum UserType {

		ADMIN("系统管理员", 0);

		private int userType;
		private String value;

		public int getUserType() {
			return userType;
		}

		public String getValue() {
			return value;
		}

		private UserType(String value, int dicType) {
			this.userType = dicType;
			this.value = value;
		}

		public static UserType getUserType(String value) {
			if ("0".equals(value)) {
				return ADMIN;
			}
			return null;
		}
	}

	/**
	 * TODO 帐号与设备绑定类型
	 * 
	 * @copyright {@link www.hndfsjsoft.com}
	 * @author
	 * @version 2015年7月16日 下午1:27:09
	 * @see com.hndfsj.app.common.objects.DeviceType
	 * 
	 */
	public enum DeviceType {
		mobile, ios, pad;// 手机、平板

		/**
		 * TODO DeviceType.name()
		 *
		 * @param userAgent
		 * @return
		 * @author Mr.Zheng
		 * @version 2015年7月16日 下午1:38:55
		 */
		public static boolean containName(String userAgent) {
			for (DeviceType deviceType : values()) {
				if (deviceType.name().equals(userAgent)) {
					return true;
				}
			}
			return false;
		}
	}

}