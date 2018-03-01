package com.hndfsj.framework.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 统计处理日期工具类
 * @copyright {@link www.hndfsj.com}
 * @version Mar 19, 2014 10:52:59 AM
 */
public class StatisticsDateUtils {
	/**
	 * 获得当前时间段
	 * 
	 * @param day
	 *            当前日期的前几天
	 * @param end
	 *            结束的日期，根据这个日期和天数，计算出日期段的日期数据
	 * @param formate 日期格式化字符串
	 * @return
	 * @author john
	 * @version Mar 11, 2014 3:39:58 PM
	 */
	public static List<String> getDatesByDay(int day, Date end,String formate) {
		List<String> list = new ArrayList<String>();// 存放日期数据,索引0为开始日期，最一位为结束日期
		List<Date> dates = new ArrayList<Date>();// 存放日期数据,索引0为开始日期，最一位为结束日期
//		Date dBefore = null;// 之前的日期
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(end);// 把当前时间赋给日历
		dates.add(end);// 最后日期
		for (int i = 0; i < day - 1; i++) {
			calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前1天
			end = calendar.getTime();
			dates.add(end);
			calendar.setTime(end);// 把当前时间赋给日历
		}
		Collections.sort(dates);// 排序
		for (Date d : dates) {
			list.add(handleDate(d,formate));// 处理格式化日期为字符串
		}
		return list;
	}
	
	/**
	* 获得当前日期前几天
	* @param day 前几天不能小于0，0为今天,小于0返回今天
	* @param today 当前日期
	* @return
	* @author john
	* @version Mar 27, 2014 10:24:06 AM
	*/
	public static Date getBeforeDay(int day,Date today){
		if(day<=0){
			return new Date();
		}
		Calendar calendar = Calendar.getInstance(); // 得到日历
//		calendar.setTime(today);// 把当前时间赋给日历
		calendar.set(Calendar.YEAR,getYear(today));
		calendar.set(Calendar.MONTH, getMonth(today));
		calendar.set(Calendar.DAY_OF_MONTH, getDay(today));
		calendar.set(Calendar.HOUR, 1);
		calendar.set(Calendar.MINUTE, 1);
		calendar.set(Calendar.SECOND, 1);
		calendar.add(Calendar.DATE, -day); // 设置为前几天
//		calendar.set(Calendar.MONTH, Calendar.MONTH); // 设置为前几天
////		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)); // 设置为前几天
//		System.err.println(calendar.get(Calendar.DAY_OF_MONTH));
//		System.err.println(Calendar.MONTH);
//		return setyyyyMMddHHmmSSDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		return calendar.getTime();
	}
	
	/**
	* 获得当前日期后几天
	* @param day 前几天不能小于0，0为今天,小于0返回今天
	* @param today 当前日期
	* @return
	* @version Mar 27, 2014 10:24:06 AM
	*/
	public static Date getAfterDay(int day,Date today){
		if(day<=0){
			return new Date();
		}
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(today);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, day-1); // 设置为前几天
		return calendar.getTime();
	}
	
	public static Date parseDate(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(date);
	}

	/**
	 * 格式化日期
	 * 
	 * @param start
	 * @param formate 格式字符串
	 * @return
	 * @version Mar 11, 2014 3:41:50 PM
	 */
	public static String handleDate(Date date,String formate) {
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		return sdf.format(date);
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days)) + 1;
	}

	/**
	* 计算两个日期之间月份差
	* @param start
	* @param end
	* @return
	* @throws ParseException
	* @version Mar 17, 2014 1:29:12 PM
	*/
	public static int getMonthSpace(Date start, Date end) throws ParseException {
		int result = getMonthDifference(start,end);
		return result>12?12:result;
	}
	/**
	* getMonthDifference方法说明
	* 获取两个日期相差的月数
	* @param start
	* @param end
	* @return
	* @throws ParseException
	* @version 2014年7月10日 上午10:56:33
	*/
	public static int getMonthDifference(Date start, Date end) throws ParseException {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(sdf.parse(sdf.format(start)));
		c2.setTime(sdf.parse(sdf.format(end)));
		int y1=c1.get(Calendar.YEAR);
		int y2=c2.get(Calendar.YEAR);
		if(y1==y2){//同一年，只判断月份
			result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH)+1;
		}else{//不同一年
			//首先Y1小于Y2
			if(y2-y1>2){//超过一年以上
				return 12;
			}else{
				result=(12-c1.get(Calendar.MONTH))+c2.get(Calendar.MONTH)+1;
			}
		}
		return result;
	}
	
	
	/**
	* 设定日期的时间
	* @param date
	* @param hour 小时
	* @param minute 分钟
	* @param second 秒钟
	* @return
	* @version Mar 18, 2014 4:05:36 PM
	*/
	public static  Date setDateTime(Date date, int hour, int minute, int second) {
	    // 获取Calendar  
	    Calendar calendar = Calendar.getInstance();  
	    // 设置时间,当前时间不用设置  
	    calendar.setTime(date);  
		calendar.set(Calendar.HOUR_OF_DAY, hour);//设定小时
	    calendar.set(Calendar.MINUTE, minute);//设定分钟
	    calendar.set(Calendar.SECOND, second);//设定秒
		return calendar.getTime();
	}
	/**
	* @param year
	* @param month[1-12]
	* @param day
	* @return
	* @author Mr.Zheng
	* @version 2014年10月29日 下午9:04:41
	*/
	public static Date setDate( int year, int month, int day){
		month--;
		if(month<0||month>11)month=1;
	    // 获取Calendar  
	    Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.YEAR, year);//设定年
	    calendar.set(Calendar.MONTH, month);//设定月
	    calendar.set(Calendar.DAY_OF_MONTH, day);//设定天
	    return calendar.getTime();
	}
	/**
	* 设定年月日时分秒日期
	* @param year
	* @param month[1-12]
	* @param day
	* @param hour
	* @param minute
	* @param second
	* @return
	* @version Apr 4, 2014 3:21:00 PM
	*/
	public static Date setyyyyMMddHHmmSSDate(int year, int month, int day,int hour, int minute, int second){
		return setDateTime( setDate(year,month,day),hour,minute,second);
	}
	
	/**
	* 获取每个月的第一天或最后一天
	* @param isFirstDay 是否是第一天 false是最后一天
	* @param date
	* @return
	* @version Mar 14, 2014 5:19:48 PM
	*/
	public static Date getDateByMonth(Date date,boolean isFirstDay){
	    // 获取Calendar  
	    Calendar calendar = Calendar.getInstance();  
	    // 设置时间,当前时间不用设置  
	     calendar.setTime(date);  
	     if(isFirstDay){
	 	    calendar.set(Calendar.DATE,1); //每个月的第一天
		    calendar.set(Calendar.HOUR_OF_DAY, 0);
		    calendar.set(Calendar.MINUTE, 0);
		    calendar.set(Calendar.SECOND, 0);
	     }else{
		 	    // 设置日期为本月最大日期  
		 	 calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); //每个月的最后一天 
			    calendar.set(Calendar.HOUR_OF_DAY, 23);
			    calendar.set(Calendar.MINUTE, 59);
			    calendar.set(Calendar.SECOND, 59);
	     }
	    return calendar.getTime();
	}
	
	/**
	* 获取每个月的第一天或最后一天
	* @param isFirstDay 是否是第一天 false是最后一天
	* @param date
	* @return
	* @version Mar 14, 2014 5:19:48 PM
	*/
	public static String getDateByMonth(Date date,boolean isFirstDay,DateFormat format ){
	    return format.format(getDateByMonth(date, isFirstDay));
	}
	
	/**
	* 日期字符串是否匹配yyyy-MM-dd HH:mm:ss格式
	* @param date
	* @return
	* @version Mar 28, 2014 11:04:23 AM
	*/
	public static boolean dateStrMatchFormat(String date){
//		String regex="\\d{4}-\\d{1,2}-\\d{1,2}|\\d{4}-\\d{1,2}-\\d{1,2}\\s*\\d{2}:\\d{2}:\\d{2}";
		String regex="\\d{4}-\\d{2}-\\d{2}\\s*\\d{2}:\\d{2}:\\d{2}";
		Pattern pat = Pattern.compile(regex);  
		Matcher mat = pat.matcher(date);
		return mat.matches();
	}

	public static List<Date> getMonthDay(int year,int month){
		Date date=setDate(year, month, 1);
		return getMonthDay(date);
	}
	
	/**
	* 获取日期月份的天数日期如 2014-04-25 刚返回4月份从1号到30号的日期数据
	* @param date
	* @return
	* @author Mr.Zheng
	* @version 2014年10月29日 下午8:39:52
	*/
	public static List<Date> getMonthDay(Date date){
		Date start=StatisticsDateUtils.getDateByMonth(date,true);
		Date end=StatisticsDateUtils.getDateByMonth(date,false);
		List<Date> dates=new ArrayList<Date>();
		int year=getYear(date);
		int month=getMonth(date);
		for(int i=getDay(start);i<=getDay(end);i++){
			dates.add(setDate(year, month, i));
		}
		return dates;
	}
	
	public  static List<String> getMonthDay(Date date,String format){
		DateFormat dateFormat = new SimpleDateFormat(format); 
		List<String> list=new ArrayList<String>();
		
		for(Date day:getMonthDay(date)){
			list.add(dateFormat.format(day));
		}
		return list;

	}
	
	public static  List<String> getMonthDay(int year,int month,String format){
		return getMonthDay(setDate(year, month, 1),format);
	}
	
	
	/**
	* 格式化为yyyy-MM-dd HH:mm:ss格式
	* @param now
	* @return
	* @version Apr 1, 2014 3:41:57 PM
	*/
	public static String formate(Date now) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return format.format(now);
	}
	/**
	* 今年的年份
	* @return
	* @author john
	* @version Apr 4, 2014 3:29:25 PM
	*/
	public static int  getYear(){
		return getYear(new Date());
	}
	/**
	* 获取日期的年
	* @param date
	* @return
	* @version Apr 4, 2014 3:29:04 PM
	*/
	public static int getYear(Date date){
	    // 获取Calendar  
	    Calendar calendar = Calendar.getInstance();  
	    // 设置时间,当前时间不用设置  
	     calendar.setTime(date); 
	     return calendar.get(Calendar.YEAR);
	}
	
	/**
	* 获取日期的月
	* @param date
	* @return
	* @version Apr 4, 2014 3:29:04 PM
	*/
	public static int getMonth(Date date){
	    // 获取Calendar  
	    Calendar calendar = Calendar.getInstance();  
	    // 设置时间,当前时间不用设置  
	     calendar.setTime(date); 
	     return calendar.get(Calendar.MONTH)+1;//java有月份【0-11】
	}
	
	/**
	* 获取日期的号
	* @param date
	* @return
	* @version Apr 4, 2014 3:29:04 PM
	*/
	public static int getDay(Date date){
	    // 获取Calendar  
	    Calendar calendar = Calendar.getInstance();  
	    // 设置时间,当前时间不用设置  
	     calendar.setTime(date); 
	     return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	* 获取一个日期未来N分钟后的日期
	* @param date 初始日期
	* @param minute 增加分钟数
	* @return
	* @author Mr.Zheng
	* @version 2014年10月10日 上午10:47:13
	*/
	public static Date getDate(Date date,int minute){
	    // 获取Calendar  
	    Calendar calendar = Calendar.getInstance();  
	    // 设置时间,当前时间不用设置  
	    calendar.setTime(date);  
	    if(minute<0){
	    	minute=0;//
	    }else{
		    int hour=calendar.get(Calendar.HOUR_OF_DAY);
		    hour+=minute/60;
		    minute=minute%60;
			calendar.set(Calendar.HOUR_OF_DAY, hour);//设定小时
	    }
	    minute+=calendar.get(Calendar.MINUTE);
	    calendar.set(Calendar.MINUTE, minute);//设定分钟
		return calendar.getTime();
	}
	
	/**
	* 获取一个日期之前N分钟的日期
	* @param date
	* @param minute[0~无限] 要减去的分钟为正数
	* @return
	* @author Mr.Zheng
	* @version 2015年3月2日 下午2:17:51
	*/
	public static Date getBeforeTime(Date date,int minute){
		// 获取Calendar  
		Calendar calendar = Calendar.getInstance();  
		// 设置时间,当前时间不用设置  
		calendar.setTime(date);  
		if(minute<0){
			minute=0;//
			minute+=calendar.get(Calendar.MINUTE);
		}else{
			int hour=calendar.get(Calendar.HOUR_OF_DAY);
			int _minute=calendar.get(Calendar.MINUTE);
			hour-=minute/60;
			int temp=minute%60;
			if(_minute>temp){
				minute-=temp;
			}else{
				hour--;
				minute=_minute+60-minute;
			}
			calendar.set(Calendar.HOUR_OF_DAY, hour);//设定小时
		}
		calendar.set(Calendar.MINUTE, minute);//设定分钟
		return calendar.getTime();
	}
	
	/**
	* 获取12个月的MM格式数据如6月，06
	* @return
	* @author Mr.Zheng
	* @version 2014年10月29日 下午3:06:52
	*/
	public static List<String> getYearMonths(){
		List<String> list=new ArrayList<String>();
		list.add("01");
		list.add("02");
		list.add("03");
		list.add("04");
		list.add("05");
		list.add("06");
		list.add("07");
		list.add("08");
		list.add("09");
		list.add("10");
		list.add("11");
		list.add("12");
		return list;
	}

	/**
	* 获取日期前几个月的日期
	* @param date 日期
	* @param num 前几个月(小于12)
	* @author Mr.Zheng
	* @version 2014年11月12日 下午8:22:42
	*/
	public static Date getBeforeMonth(Date date, int num) {
		if(num>12)return null;
		int year=getYear(date);
		int month=getMonth(date);
		if(month>num){
			return setyyyyMMddHHmmSSDate(year, month-num, 1, 0, 0, 0);
		}
		return setyyyyMMddHHmmSSDate(year-1, 12+month-num, 1, 0, 0, 0);
	}
	public static void main(String[] args) {
		System.out.println(formate(getBeforeMonth(new Date(), 11)));
	}

	public static int getHours(Date date) {
	    // 获取Calendar  
	    Calendar calendar = Calendar.getInstance();  
	    // 设置时间,当前时间不用设置  
	     calendar.setTime(date); 
	     return calendar.get(Calendar.HOUR_OF_DAY);//java有月份【0-11】
	}
}
