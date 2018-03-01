package com.hndfsj.framework.utils.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CusDbTool {

	/**
	 * 月分表数据查询，语句拼接
	 * 
	 * @param tblNamePrefix
	 *            例如：hd_vd_
	 * @param timeCol
	 *            数据时间列，例如： tTime
	 * @param startTime
	 *            格式：2016-04-16 09:50:21
	 * @param endTime
	 * @return String 拼接完成的SQL语句（可能为""）
	 */
	public static List<String> getSqlForTable(String tblNamePrefix, String startTime, String endTime) {
		List<String> mTbls = new ArrayList<String>();
		try {
			String tblName = "";
			SimpleDateFormat sdfz = new SimpleDateFormat("yyyyMM");
			SimpleDateFormat sdfx = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar sc = Calendar.getInstance(), ec = Calendar.getInstance();
			sc.setTime(sdfx.parse(startTime));
			ec.setTime(sdfx.parse(endTime));
			if (sc.compareTo(ec) <= 0) {
				// 开始时间等于或小于结束时间，正确
				do {
					tblName = tblNamePrefix + sdfz.format(sc.getTime());
					// if (isTableExists(tblName)) {
					mTbls.add(tblName); // 如果月分表存在,记录
					// }
					sc.add(Calendar.MONTH, 1); // 月份 +1
				} while (sc.compareTo(ec) <= 0);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mTbls;
	}

	/**
	 * 月分表数据查询，语句拼接
	 * 
	 * @param tblNamePrefix
	 *            例如：hd_vd_
	 * @param timeCol
	 *            数据时间列，例如： tTime
	 * @param startTime
	 *            格式：2016-04-16 09:50:21
	 * @param endTime
	 * @return String 拼接完成的SQL语句（可能为""）
	 */
	public static List<Map<String, String>> getSqlForMMData(List<String> mTbls, String timeCol, String startTime, String endTime) {
		List<Map<String, String>> maps = new ArrayList<>();
		try {
			for (String str : mTbls) {
				Map<String, String> map = new HashMap<>();
				map.put("tableName", str);
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				maps.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maps;
	}

	/**
	 * 月分表数据查询，语句拼接
	 * 
	 * @param tblNamePrefix
	 *            例如：hd_vd_
	 * @param timeCol
	 *            数据时间列，例如： tTime
	 * @param startTime
	 *            格式：2016-04-16 09:50:21
	 * @param endTime
	 * @return String 拼接完成的SQL语句（可能为""）
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Map<String, String>> getSqlForMMData(List<String> mTbls) {
		List<Map<String, String>> maps = new ArrayList<>();
		try {
			for (String str : mTbls) {
				Map map = new HashMap<>();
				map.put("tableName", str);
				maps.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maps;
	}

	/**
	 * mysql 数据库测试连接
	 * 
	 * @param url
	 * @param name
	 * @param pwd
	 * @return boolean
	 */
	public static boolean testConnection(String url, String name, String pwd) {
		boolean flag = false;
		String driver = "com.mysql.jdbc.Driver"; // 获取mysql数据库的驱动类
		// String url="jdbc:mysql://localhost:3306/test"; //连接数据库（kucun是数据库名）
		// String name="root"; //连接mysql的用户名
		// String pwd="123456"; //连接mysql的密码
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, name, pwd);// 获取连接对象
			if (conn != null) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
		} catch (SQLException e) {
			// e.printStackTrace();
		}
		return flag;
	}

}
