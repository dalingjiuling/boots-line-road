package com.line.road.common.utils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH",
			"yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH", "yyyy/MM",
			"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM.dd HH", "yyyy.MM", "yyyy年MM月dd日",
			"yyyy年MM月dd日 HH时mm分ss秒", "yyyy年MM月dd日 HH时mm分", "yyyy年MM月dd日 HH时", "yyyy年MM月", "yyyy" };

	public DateUtils() {
	}

	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	public static String formatDate(long dateTime, String pattern) {
		return formatDate(new Date(dateTime), pattern);
	}

	public static String formatDate(Date date, String pattern) {
		String formatDate = null;
		if (date != null) {
			if (StringUtils.isNotBlank(pattern)) {
				formatDate = DateFormatUtils.format(date, pattern);
			} else {
				formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
			}
		}
		return formatDate;
	}

	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / 86400000L;
		long hour = timeMillis / 3600000L - day * 24L;
		long min = timeMillis / 60000L - day * 24L * 60L - hour * 60L;
		long s = timeMillis / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
		long ss = timeMillis - day * 24L * 60L * 60L * 1000L - hour * 60L * 60L * 1000L - min * 60L * 1000L - s * 1000L;
		StringBuilder sb = new StringBuilder();
		sb.append(day > 0L ? day + "天" : "");
		sb.append((day > 0L) || (hour > 0L) ? hour + "时" : "");
		sb.append((day > 0L) || (hour > 0L) || (min > 0L) ? min + "分" : "");
		sb.append(s > 0L ? s + "秒" : "");
		sb.append(ss > 0L ? ss + "毫秒" : "");
		return sb.toString();
	}

	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	public static String getDate(String pattern, int amont, int type) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(type, amont);
		return DateFormatUtils.format(calendar.getTime(), pattern);
	}

	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / 86400000L;
	}

	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / 3600000L;
	}

	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / 60000L;
	}

	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / 86400000L;
	}

	public static int getMonthHasDays(Date date) {
		return getMonthHasDays(new SimpleDateFormat("yyyyMM").format(date));
	}

	public static int getMonthHasDays(String yyyyMM) {
		String year = yyyyMM.substring(0, 4);
		String month = yyyyMM.substring(4, 6);

		int day = 0;
		String day31 = ",01,03,05,07,08,10,12,";
		String day30 = "04,06,09,11";
		if (day31.contains(month)) {
			day = 31;
		} else if (day30.contains(month)) {
			day = 30;
		} else {
			int y = Integer.parseInt(year);
			if (((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0)) {
				day = 29;
			} else {
				day = 28;
			}
		}
		return day;
	}

	public static int getWeekOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(3);
	}

	public static Date getOfDayFirst(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(10, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);
		return calendar.getTime();
	}

	public static Date getOfDayLast(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(10, 23);
		calendar.set(12, 59);
		calendar.set(13, 59);
		calendar.set(14, 999);
		return calendar.getTime();
	}

	public static Date getServerStartDate() {
		long time = ManagementFactory.getRuntimeMXBean().getStartTime();
		return new Date(time);
	}
}
