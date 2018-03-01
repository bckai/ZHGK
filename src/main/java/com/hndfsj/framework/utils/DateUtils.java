package com.hndfsj.framework.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

public class DateUtils {
	private static String DATETIME_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
	public static final String DATETIME_SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATETIME_YMD_FORMAT = "yyyyMMdd";
	public static final String DATETIME_YM_FORMAT = "yyyyMM";
	public static final String TIME_FORMAT = "HH:mm:ss";
	public static final long MILLIS_IN_DAY = 86400000L;

	public static Date previous(int days) {
		return new Date(System.currentTimeMillis() - days * 3600000L * 24L);
	}

	public static String formatDate(Date d,String FORMAT) {
		return new SimpleDateFormat(FORMAT).format(d);
	}
	
	public static String formatDateTime(long d) {
		return new SimpleDateFormat(DATETIME_MINUTE_FORMAT).format(Long.valueOf(d));
	}

	public static Date parseDate(String d) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(d);
		} catch (Exception e) {
		}
		return null;
	}

	public static Date parseDateTimeWithMinute(String dt) {
		try {
			return new SimpleDateFormat(DATETIME_MINUTE_FORMAT).parse(dt);
		} catch (Exception e) {
		}
		return null;
	}

	public static Date parseDateTimeWithSecond(String dt) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dt);
		} catch (Exception e) {
		}
		return null;
	}

	public static Date convertString2Date(String strDate) {
		Date result = null;
		try {
			result = convertString2Date("yyyy-MM-dd", strDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return result;
	}

	public static final Date convertString2Date(String formatString, String targetDate) throws ParseException {
		if ((StringUtils.isBlank(targetDate)) || (StringUtils.isEmpty(targetDate)))
			return null;
		SimpleDateFormat format = null;
		Date result = null;
		format = new SimpleDateFormat(formatString);
		try {
			result = format.parse(targetDate);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return result;
	}

	public static String convertDate2String(Date targetDate) {
		return convertDate2String("yyyy-MM-dd", targetDate);
	}

	public static String convertDate2String(String formatString, Date targetDate) {
		SimpleDateFormat format = null;
		String result = null;
		if (targetDate != null) {
			format = new SimpleDateFormat(formatString);
			result = format.format(targetDate);
		} else {
			return null;
		}
		return result;
	}

	public static int compare_date(Date src, Date src1) {
		String date1 = convertDate2String("yyyy-MM-dd HH:mm:ss", src);
		String date2 = convertDate2String("yyyy-MM-dd HH:mm:ss", src1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime())
				return 1;
			if (dt1.getTime() < dt2.getTime()) {
				return -1;
			}
			return 0;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static boolean isDateBefore(String date1, String date2) {
		try {
			DateFormat df = DateFormat.getDateTimeInstance();
			return df.parse(date1).before(df.parse(date2));
		} catch (ParseException e) {
		}
		return false;
	}

	public static boolean isDateBefore(String date2) {
		if (date2 == null)
			return false;
		try {
			Date date1 = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			return date1.before(df.parse(date2));
		} catch (ParseException e) {
		}
		return false;
	}

	public static boolean equalDate(String date2) {
		try {
			String date1 = convertDate2String("yyyy-MM-dd HH:mm:ss", new Date());
			date1.equals(date2);
			Date d1 = convertString2Date("yyyy-MM-dd HH:mm:ss", date1);
			Date d2 = convertString2Date("yyyy-MM-dd HH:mm:ss", date2);
			return d1.equals(d2);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public static boolean equalDate(String date1, String date2) {
		try {
			Date d1 = convertString2Date("yyyy-MM-dd HH:mm:ss", date1);
			Date d2 = convertString2Date("yyyy-MM-dd HH:mm:ss", date2);

			return d1.equals(d2);
		} catch (ParseException e) {
		}
		return false;
	}

	public static Date getBoferBeginDate(Calendar currentDate) {
		Calendar result = Calendar.getInstance();
		result.set(currentDate.get(1), currentDate.get(2) - 1, result.getActualMinimum(5));

		return result.getTime();
	}

	public static Date getBoferEndDate(Calendar currentDate) {
		Calendar result = Calendar.getInstance();
		result.set(currentDate.get(1), currentDate.get(2) - 1, result.getActualMaximum(5));

		return result.getTime();
	}

	public static int getDaysBetween(Calendar beginDate, Calendar endDate) {
		if (beginDate.after(endDate)) {
			Calendar swap = beginDate;
			beginDate = endDate;
			endDate = swap;
		}
		int days = endDate.get(6) - beginDate.get(6) + 1;
		int year = endDate.get(1);
		if (beginDate.get(1) != year) {
			beginDate = (Calendar) beginDate.clone();
			do {
				days += beginDate.getActualMaximum(6);
				beginDate.add(1, 1);
			} while (beginDate.get(1) != year);
		}
		return days;
	}

	public static int getWorkingDay(Calendar beginDate, Calendar endDate) {
		int result = -1;
		if (beginDate.after(endDate)) {
			Calendar swap = beginDate;
			beginDate = endDate;
			endDate = swap;
		}
		int charge_start_date = 0;
		int charge_end_date = 0;

		int stmp = 7 - beginDate.get(7);
		int etmp = 7 - endDate.get(7);
		if ((stmp != 0) && (stmp != 6)) {
			charge_start_date = stmp - 1;
		}
		if ((etmp != 0) && (etmp != 6)) {
			charge_end_date = etmp - 1;
		}
		result = getDaysBetween(getNextMonday(beginDate), getNextMonday(endDate)) / 7 * 5 + charge_start_date
				- charge_end_date;

		return result;
	}

	public static String getChineseWeek(Calendar date) {
		String[] dayNames = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		int dayOfWeek = date.get(7);
		return dayNames[(dayOfWeek - 1)];
	}

	public static int getChineseWeekNumber(Date date) {
		int number = 0;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if ("星期一".equals(getChineseWeek(calendar)))
				number = 1;
			else if ("星期二".equals(getChineseWeek(calendar)))
				number = 2;
			else if ("星期三".equals(getChineseWeek(calendar)))
				number = 3;
			else if ("星期四".equals(getChineseWeek(calendar)))
				number = 4;
			else if ("星期五".equals(getChineseWeek(calendar)))
				number = 5;
			else if ("星期六".equals(getChineseWeek(calendar)))
				number = 6;
			else if ("星期日".equals(getChineseWeek(calendar))) {
				number = 7;
			}
		}
		return number;
	}

	public static Calendar getNextMonday(Calendar date) {
		Calendar result = null;
		result = date;
		do {
			result = (Calendar) result.clone();
			result.add(5, 1);
		} while (result.get(7) != 2);
		return result;
	}

	public static int getHolidays(Calendar beginDate, Calendar endDate) {
		return getDaysBetween(beginDate, endDate) - getWorkingDay(beginDate, endDate);
	}

	public static boolean isDateEnable(Date beginDate, Date endDate, Date currentDate) {
		long beginDateLong = beginDate.getTime();

		long endDateLong = endDate.getTime();

		long currentDateLong = currentDate.getTime();
		if ((currentDateLong >= beginDateLong) && (currentDateLong <= endDateLong)) {
			return Boolean.TRUE.booleanValue();
		}
		return Boolean.FALSE.booleanValue();
	}

	public static Date getMinDate(Calendar currentDate) {
		Calendar result = Calendar.getInstance();
		result.set(currentDate.get(1), currentDate.get(2), currentDate.getActualMinimum(5));

		return result.getTime();
	}

	public static Date addDay(int num, Date Date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(Date);
		calendar.add(5, num);
		return calendar.getTime();
	}

	public static String addOrMinus(int num, String targetDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(convertString2Date(targetDate));
		calendar.add(5, num);
		return convertDate2String(calendar.getTime());
	}

	public static Date addOrMinus(int calendarType, int num, Date targetDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(targetDate);
		calendar.add(calendarType, num);
		return calendar.getTime();
	}
	
	 /**
     * 
     * 描述:获取上个月的最后一天.
     * 
     * @return
     */
    public static Date getLastMaxMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

	public static void main(String[] args) {
		System.out.println(convertDate2String(getNextMonday(Calendar.getInstance()).getTime()));

		System.out.println(new Date());
		System.out.println(addOrMinus(13, 1, new Date()));
	}
}