package com.hndfsj.app.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 本项目所有枚举值定义
 * 
 * @author zxj
 *
 */
public class ZHGKEnum {
	public enum DICTIONARY_TYPE {
		CMS_TYPE, COUNT, DEVICE_TYPE, INCIDENT_TYPE, VD_TYPE, WD_TYPE, INCIDENT_COUNT
	}

	public enum DICTIONARY_COUNT {
		CMS("COUNT"), VD("COUNT"), WD("COUNT"), CAM("COUNT");
		String id;

		private DICTIONARY_COUNT(String id) {
			this.id = id;
		}

		public Object getId() {
			return this.id;
		}
	}

	/**
	 * 平台基础角色
	 * 
	 * @copyright {@link www.hndfsj.com}
	 * @author zxj
	 * @version 2015年3月30日 下午4:12:43
	 * 
	 */
	public enum ZHGKEnum_Role {
		系统管理员("ZHGKE_SYSTEMER"), 普通用户("ZHGKE_ORDINARY");

		String id;

		private ZHGKEnum_Role(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public static boolean contains(String name) {
			for (ZHGKEnum_Role type : values()) {
				if (type.name().equals(name)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 路面状态
	 * 
	 * @author Administrator
	 *
	 */
	public enum PAVEMENT_STATE {
		干燥("00"), 潮湿("01"), 多雨("02"), 冰冻("03"), 霜雪("04"), 残盐("05"), 冰雨("06"), 危险("07"), 未知("99");
		String id;

		private PAVEMENT_STATE(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public String getId(String id) {
			return this.name();
		}

		public void setId(String id) {
			this.id = id;
		}

		public static String contains(String roleId) {
			for (PAVEMENT_STATE type : values()) {
				if (type.id.equals(roleId)) {
					return type.name();
				}
			}
			return contains("99");
		}
	}

	/**
	 * 串口参数
	 * 
	 * @author Administrator
	 *
	 */
	public enum PORT_CONFIG {
		defaults(new int[] { 9600, 8, 1, 0 }), MTD(new int[] { 115200, 8, 1, 0 }), DianMing(
				new int[] { 9600, 8, 1, 0 }), HanWei(new int[] { 9600, 8, 1, 0 }), SanSi(
						new int[] { 9600, 8, 1, 0 }), YinTe(new int[] { 9600, 8, 1, 0 }), LingXin(
								new int[] { 9600, 8, 1, 0 }), ZhiDeHua(new int[] { 115200, 8, 1, 0 }), ShiDai(
										new int[] { 9600, 8, 1, 0 }), SmartSensor(new int[] { 9600, 8, 1, 0 });

		int[] id;

		private PORT_CONFIG(int[] id) {
			this.id = id;
		}

		public static int[] contains(String name) {
			for (PORT_CONFIG type : values()) {
				if (type.name().equals(name)) {
					return type.id;
				}
			}
			return contains("defaults");
		}
	}

	public enum DVC_FAC_NAME {
		cms, vd, wd, cam;
		public static String getDvcFacName(String dvcType, String dvcBrand) {
			if ("cms".equals(dvcType)) {
				return CMS_FAC.getName(dvcBrand);
			} else if ("vd".equals(dvcType)) {
				return VD_FAC.getName(dvcBrand);
			} else if ("wd".equals(dvcType)) {
				return WD_FAC.getName(dvcBrand);
			} else if ("cam".equals(dvcType)) {
				return CAM_FAC.getName(dvcBrand);
			}
			return "未知";
		}

		public static Map<String, String> getDvcFacNameMap(String dvcType) {
			if ("cms".equals(dvcType)) {
				return CMS_FAC.getFac();
			} else if ("vd".equals(dvcType)) {
				return VD_FAC.getFac();
			} else if ("wd".equals(dvcType)) {
				return WD_FAC.getFac();
			} else if ("cam".equals(dvcType)) {
				return CAM_FAC.getFac();
			}
			return null;
		}
	}

	public enum WD_FAC {
		勋飞("XunFei");
		// 曼德克("ManDeKe");
		String id;

		private WD_FAC(String id) {
			this.id = id;
		}

		public static String getName(String dvcBrand) {
			for (WD_FAC type : values()) {
				if (type.id.equals(dvcBrand)) {
					return type.name();
				}
			}
			return dvcBrand;
		}

		public static Map<String, String> getFac() {
			Map<String, String> map = new HashMap<>();
			for (WD_FAC type : values()) {
				map.put(type.id, type.name());
			}
			return map;
		}
	}

	public enum VD_FAC {
		SmartSensor("SmartSensor");
		// 动视元("DongShiYuan"),
		// 华通致远("MTD"),
		// 志德华("ZhiDeHua");
		String id;

		private VD_FAC(String id) {
			this.id = id;
		}

		public static String getName(String dvcBrand) {
			for (VD_FAC type : values()) {
				if (type.id.equals(dvcBrand)) {
					return type.name();
				}
			}
			return dvcBrand;
		}

		public static Map<String, String> getFac() {
			Map<String, String> map = new HashMap<>();
			for (VD_FAC type : values()) {
				map.put(type.id, type.name());
			}
			return map;
		}
	}

	public enum CMS_FAC {
		// 电明("DianMing")
		汉威("HanWei"), 时代("ShiDai"), 三思("SanSi"), 银特("YinTe")
		// , 灵信("LingXin")
		;
		String id;

		private CMS_FAC(String id) {
			this.id = id;
		}

		public static String getName(String dvcBrand) {
			for (CMS_FAC type : values()) {
				if (type.id.equals(dvcBrand)) {
					return type.name();
				}
			}
			return dvcBrand;
		}

		public static Map<String, String> getFac() {
			Map<String, String> map = new HashMap<>();
			for (CMS_FAC type : values()) {
				map.put(type.id, type.name());
			}
			return map;
		}
	}

	public enum CAM_FAC {
		派尔高("PaiErGao")
		// 金三立("JinSanLi")
		// ,海康("HaiKang")
		;
		String id;

		private CAM_FAC(String id) {
			this.id = id;
		}

		public static String getName(String dvcBrand) {
			for (CAM_FAC type : values()) {
				if (type.id.equals(dvcBrand)) {
					return type.name();
				}
			}
			return dvcBrand;
		}

		public static Map<String, String> getFac() {
			Map<String, String> map = new HashMap<>();
			for (CAM_FAC type : values()) {
				map.put(type.id, type.name());
			}
			return map;
		}
	}

	public static void main(String[] args) {
		System.out.println(CAM_FAC.getName("JinSanLi"));
	}

}
