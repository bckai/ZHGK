package com.hndfsj.driver.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DvcTypeConstant {

	public static int DVC_ORGNIZATION = 101;
	
	/**
	 * 获取设备类型编码和类型名称对照关系
	 * 
	 * @return Map<Integer, String>
	 */
	public static Map<String, Integer> getDvcTypeCons() {
		Map<String, Integer> dvcTypes = new HashMap<String, Integer>();
		dvcTypes.put("VD",110);//车辆检测器
		dvcTypes.put("MVD", 110);//微波
		dvcTypes.put("WD",120);//气象检测器
		dvcTypes.put("WS",121);//气象站
		dvcTypes.put("VIS",122);//能见度检测器
		dvcTypes.put("CMS",150);//可变信息标志
		dvcTypes.put("M",151);//门式可变信息标志
		dvcTypes.put("F",152);//F式可变信息标志
		dvcTypes.put("S", 153);//单柱式可变信息标志
		dvcTypes.put("T", 154);//T型可变信息标志
		dvcTypes.put("gun", 141);//枪机
		dvcTypes.put("ball", 142);//球机
		dvcTypes.put("half-ball", 143);//半球
		return dvcTypes;
	}
	/**
	 * 获取情报板设备类型编码和类型名称对照关系
	 * 
	 * @return Map<Integer, String>
	 */
	public static Map<String, String> getCmsTypeCons() {
		Map<String, String> dvcTypes = new HashMap<String, String>();
		//dvcTypes.put("CMS", "可变信息标志");
		dvcTypes.put("M", "门式可变信息标志");
		dvcTypes.put("F", "F式可变信息标志");
		//dvcTypes.put("S", "单柱式可变信息标志");
		//dvcTypes.put("T", "T型可变信息标志");
		return dvcTypes;
	}
	/**
	 * 获取车辆检测器设备类型编码和类型名称对照关系
	 * 
	 * @return Map<Integer, String>
	 */
	public static Map<String, String> getWdTypeCons() {
		Map<String, String> dvcTypes = new HashMap<String, String>();
		dvcTypes.put("WD", "气象检测器");
		//dvcTypes.put("WS", "气象站");
		//dvcTypes.put("VIS", "能见度检测器");
		return dvcTypes;
	}
	/**
	 * 获取车辆检测器设备类型编码和类型名称对照关系
	 * 
	 * @return Map<Integer, String>
	 */
	public static Map<String, String> getVdTypeCons() {
		Map<String, String> dvcTypes = new HashMap<String, String>();
		//dvcTypes.put("VD", "车辆检测器");
		dvcTypes.put("MVD", "微波");
		return dvcTypes;
	}
	/**
	 * 根据设备类型获取支持的品牌
	 * @param dvcType
	 * @return Map<String, String>
	 */
	public static Map<String, String> getFactories(String dvcType) {
		Map<String, String> factories = null;
		if ("cms".equals(dvcType)) {
			factories = getCmsTypeCons();
		} else if ("vd".equals(dvcType)) {
			factories = getVdTypeCons();
		} else if ("wd".equals(dvcType)) {
			factories = getWdTypeCons();
		} else if ("cam".equals(dvcType)) {
			factories = getWdCamCons();
		}
		return factories;
	}
	private static Map<String, String> getWdCamCons() {
		Map<String, String> dvcTypes = new HashMap<String, String>();
		dvcTypes.put("gun", "枪机");
		dvcTypes.put("ball", "球机");
		dvcTypes.put("half-ball", "半球");
		return dvcTypes;
	}
	/**
	 * 获取设备类型编码和类型对照关系
	 * 
	 * @return Map<Integer, String>
	 */
	public static String getDvcType(String dvcType) {
		switch (dvcType) {
		case "cms":
			return "15";
		case "cam":
			return "14";
		case "wd":
			return "12";
		case "vd":
			return "11";
		default:
			return "";
		}
	}

	/**
	 * 根据设备类型编码获取设备类型名称
	 * 
	 * @param dvcTypeCode
	 * @return String
	 */
	public static Integer getDvcTypeName(String dvcTypeCode) {
		Integer dvcTypeName = 0;
		for (Entry<String, Integer> entry : getDvcTypeCons().entrySet()) {
			if (entry.getKey().equals(dvcTypeCode)) {
				dvcTypeName = entry.getValue();
				break;
			}
		}
		return dvcTypeName;
	}
}
