package com.hndfsj.framework.utils.driver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hndfsj.framework.utils.DateUtils;

public class CusDateTool {
	
	// 获取系统当前时间
	public static String getSysTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
		return sdf.format(new java.util.Date());
	}
	
	// 获取当前年月(多用于数据记录按月分表拼接表名)
	public static String getyyyyMM() {
		return getSysTime().substring(0, 7).replaceAll("-", "");
	}
	
	// 日期字符串转成日期
	public static Date getDateFromStr(String dateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
		Date date = null;
		try {
			date = (Date) sdf.parse(dateTime + "00:00:00:000");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 日期格式化成日期字符串
	 * @param date
	 * @return String
	 */
	public static String getStrFromDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
		return sdf.format(date);
	}
	
	/**
	 * 根据时间返回格式如： x天前、x小时前、x分钟前、x秒前
	 * @param date
	 * @return String
	 */
	public static String getTimeDesc(Date date) {
		long timeDiff = new Date().getTime() - date.getTime();
		if (timeDiff > 1000 * 60 * 60 * 24) {
			return timeDiff / (1000 * 60 * 60 * 24) + "天前";
		} else if (timeDiff > 1000 * 60 * 60) {
			return timeDiff / (1000 * 60 * 60) + "小时前";
		} else if (timeDiff > 1000 * 60) {
			return timeDiff / (1000 * 60) + "分钟前";
		} else if (timeDiff > 1000) {
			return timeDiff / 1000 + "秒前";
		} else {
			return "刚刚";
		}
	}
	
	/**
	 * 根据时间是否是半小時鐘前
	 * @param date
	 * @return String
	 */
	public static boolean getTimeAgo(Date date) {
		long timeDiff = new Date().getTime() - date.getTime();
			return timeDiff / (1000 * 60)>30;
	}
	public static void main(String[] args) {
		System.out.println(getTimeAgo(new Date()));
	}
}
