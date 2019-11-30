package com.sky.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang3.time.DateUtils类
 * 
 * @author lcyan
 * @version 2015-02-07
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	/**
	 * 差值计算类别
	 */
	public enum Type {
		/**
		 * 毫秒数
		 */
		MSEC,
		/**
		 * 秒数
		 */
		SECOND,
		/**
		 * 分钟数
		 */
		MINUTE,
		/**
		 * 小时数
		 */
		HOUR,
		/**
		 * 天数
		 */
		DAY,
		/**
		 * 周(星期/礼拜)数
		 */
		WEEK,
		/**
		 * 月数
		 */
		MONTH,
		/**
		 * 年数
		 */
		YEAR
	}

	private static final String[] pattern = new String[] {
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd",
			"yyyy/MM/dd", "yyyy-MM", "yyyy/MM", "HH:mm:ss",
			"yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss",
			"yyyy-MM-dd'T'HH:mm:ssZ", "EEE, dd MMM yyyy HH:mm:ss Z",
			"yyyy-MM-dd'T'HH:mm:ss+HH:mm", "yyyy-MM-dd'T'HH:mm:ss-HH:mm",
			"yyyy-MM-dd'T'HH:mm+HH:mm", "yyyy-MM-dd'T'HH:mm-HH:mm" };

	private static DateFormat format;

	private static String[] parsePatterns = { "yyyy-MM-dd",
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String getDateTimeStr() {
		return formatDate(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	public static String getYear(Date date) {
		return formatDate(date, "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	public static String getMonth(Date date) {
		return formatDate(date, "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	public static String getDay(Date date) {
		return formatDate(date, "d");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	public static String getWeek(Date date) {
		return formatDate(date, "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	public static Date getDateStart(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取当前日期的month月后的最后一日
	 * 
	 * @param date
	 *            当前日期
	 * @param month
	 *            月份差值
	 */
	@SuppressWarnings("deprecation")
	public static Date getDateMonthEnd(Date date, Integer month) {
		try {
			if (date != null) {
				if (month != null) {
					date.setMonth(date.getMonth() + month);
					date.setDate(1);
					date.setDate(date.getDate() - 1);
				} else {
					date.setMonth(date.getMonth() + 1);
					date.setDate(1);
					date.setDate(date.getDate() - 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getDateEnd(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 枚举各种时间格式进行时间转换
	 */
	public static Date parseDate(String date) {
		Date instance = null;
		try {
			SimpleDateFormat sdf = null;
			for (String type : pattern) {
				try {
					sdf = new SimpleDateFormat(type, Locale.ENGLISH);
					instance = sdf.parse(date);
					break;
				} catch (Exception e) {
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}

	public static String format(Object date, String pattern) {
		String value = null;
		try {
			Date temp = null;
			if (date != null) {
				format = new SimpleDateFormat(pattern);
				if (date instanceof String) {
					temp = parseDate((String) date);
				} else if (date instanceof Integer) {
					temp = new Date(((Integer) date) * 1000);
				} else if (date instanceof Long) {
					temp = new Date((Long) date);
				} else if (date instanceof Date) {
					temp = (Date) date;
				} else if (date instanceof Calendar) {
					temp = ((Calendar) date).getTime();
				}
				value = format.format(temp);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return value;
	}

	public static String format(Object date) {
		String value = null;
		try {
			for (String element : pattern) {
				value = format(date, element);
				if (StringUtils.isNotEmpty(value)) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public static Date parseDate(String date, String pattern) {
		Date instance = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
			instance = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}

	/**
	 * 日期加减计算
	 * 
	 * @param value
	 *            当前日期值
	 * @param offset
	 *            加减偏移量(差值)
	 * @param type
	 *            计算类型
	 * @return 计算结果
	 */
	public static Date dateReckon(Date value, Long offset, Type type) {
		Date now = null;
		try {
			if (value != null && offset != null && type != null) {
				Long abs = Math.abs(offset);// 获取偏移量绝对值
				Calendar data = Calendar.getInstance();
				data.setTime(value);
				if (type.equals(Type.MSEC)) {
					if (abs > 3122064000000L) {
						throw new Exception(
								"Offset is out of range [-3122064000000 ~ 3122064000000]!");
					} else {
						Long time = data.getTimeInMillis();
						data.setTimeInMillis(time + offset);
						now = data.getTime();
					}
				} else if (type.equals(Type.SECOND)) {
					if (abs > 3122064000L) {
						throw new Exception(
								"Offset is out of range [-3122064000 ~ 3122064000]!");
					} else {
						Long time = data.getTimeInMillis();
						data.setTimeInMillis(time + (offset * 1000L));
						now = data.getTime();
					}
				} else if (type.equals(Type.MINUTE)) {
					if (abs > 52034400) {
						throw new Exception(
								"Offset is out of range [-52034400 ~ 52034400]!");
					} else {
						data.add(Calendar.MINUTE, offset.intValue());
						now = data.getTime();
					}
				} else if (type.equals(Type.HOUR)) {
					if (abs > 867240) {
						throw new Exception(
								"Offset is out of range [-867240 ~ 867240]!");
					} else {
						data.add(Calendar.HOUR, offset.intValue());
						now = data.getTime();
					}
				} else if (type.equals(Type.DAY)) {
					if (abs > 36135) {
						throw new Exception(
								"Offset is out of range [-36135 ~ 36135]!");
					} else {
						data.add(Calendar.DATE, offset.intValue());
						now = data.getTime();
					}
				} else if (type.equals(Type.WEEK)) {
					if (abs > 5148) {
						throw new Exception(
								"Offset is out of range [-5148 ~ 5148]!");
					} else {
						data.add(Calendar.WEEK_OF_YEAR, offset.intValue());
						now = data.getTime();
					}
				} else if (type.equals(Type.MONTH)) {
					if (abs > 1188) {
						throw new Exception(
								"Offset is out of range [-1188 ~ 1188]!");
					} else {
						data.add(Calendar.MONTH, offset.intValue());
						now = data.getTime();
					}
				} else if (type.equals(Type.YEAR)) {
					if (abs > 999) {
						throw new Exception(
								"Offset is out of range [-999 ~ 999]!");
					} else {
						data.add(Calendar.YEAR, offset.intValue());
						now = data.getTime();
					}
				}
			} else {
				now = value;
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 计算两个时间之间的差值
	 * 
	 * @param args1
	 *            时间1
	 * @param args2
	 *            时间2
	 * @param type
	 *            差值比较的类别
	 * @return 偏移量(差值)结果
	 */
	public static Long dateDeviation(Date args1, Date args2, Type type) {
		Long value = null;
		try {
			if (args1 != null && args2 != null && type != null) {
				Calendar one = Calendar.getInstance();
				one.setTime(args1);
				Calendar two = Calendar.getInstance();
				two.setTime(args2);
				if (type.equals(Type.MSEC)) {
					value = (two.getTimeInMillis() - one.getTimeInMillis());
				} else if (type.equals(Type.SECOND)) {
					value = ((two.getTimeInMillis() - one.getTimeInMillis()) / 1000);
				} else if (type.equals(Type.MINUTE)) {
					value = ((two.getTimeInMillis() - one.getTimeInMillis()) / (1000 * 60));
				} else if (type.equals(Type.HOUR)) {
					value = ((two.getTimeInMillis() - one.getTimeInMillis()) / (1000 * 60 * 60));
				} else if (type.equals(Type.DAY)) {
					// value=((two.getTimeInMillis()-one.getTimeInMillis())/(1000*60*60*24));
					value = ((long) (Math.rint((two.getTimeInMillis() - one
							.getTimeInMillis()) / (1000 * 60 * 60 * 24))));
				} else if (type.equals(Type.WEEK)) {
					value = ((two.getTimeInMillis() - one.getTimeInMillis()) / (1000 * 60 * 60 * 24 * 7));
				} else if (type.equals(Type.MONTH)) {
					Long m1 = Long.valueOf(one.get(Calendar.YEAR) * 12
							+ one.get(Calendar.MONTH));
					Long m2 = Long.valueOf(two.get(Calendar.YEAR) * 12
							+ two.get(Calendar.MONTH));
					value = m2 - m1;
				} else if (type.equals(Type.YEAR)) {
					value = Long.valueOf((two.get(Calendar.YEAR) - one
							.get(Calendar.YEAR)));
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * @param @param second
	 * @param @return
	 * @return String
	 * @throws
	 * @Title: fmtSecond2HMS
	 * @Description: 秒数格式化为xx时xx分xx秒
	 * @author mengqch
	 * @date 2015年9月17日
	 */
	public static String fmtSecond2HMS(Double second) {
		String HMS = "";
		long h = 0;
		long m = 0;
		long s = 0;
		Double temp = second % 3600;
		if (second > 3600) {
			h = (long) (second / 3600);
			if (temp != 0) {
				if (temp > 60) {
					m = (long) (temp / 60);
					if (temp % 60 != 0) {
						s = Math.round(temp % 60);
					}
				} else {
					s = Math.round(temp);
				}
			}
		} else {
			m = (long) (second / 60);
			if (second % 60 != 0) {
				s = (long) (second % 60);
			}
		}

		if (h > 0) {
			HMS += h + "时";
		}

		if (m > 0) {
			HMS += m + "分";
		}

		HMS += s + "秒";

		return HMS;
	}

	/**
	 * 
	 * @method: getBeforeDay
	 * @description:获取前几天的日期 （传入正数是几天前，传入负数是几天后）
	 * @param days
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	public static String getBeforeDay(Long days) {
		String date = DateUtils.getDate();

		Date parseDate = DateUtils.parseDate(date);
		Date dateReckon = DateUtils.dateReckon(parseDate, -(days), Type.DAY);
		String formatDate = DateUtils.formatDate(dateReckon, "yyyy-MM-dd");
		return formatDate;
	}

	/**
	 * 
	 * @method: getOneDayStart
	 * @description: 获取某一天的起始时间
	 * @param days
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	public static String getOneDayStart(Long days) {
		String date = DateUtils.getDate();
		Date parseDate = DateUtils.parseDate(date);
		Date dateReckon = DateUtils.dateReckon(parseDate, -(days), Type.DAY);
		String formatDate = DateUtils.formatDate(dateReckon,
				"yyyy-MM-dd HH:mm:ss");
		return formatDate;
	}

	public static int differentDaysByString(String date1,String date2){
		int days = (int) ((parseDate(date2).getTime() - parseDate(date1).getTime()) / (1000*3600*24));
		return days;
	}

	public static boolean date1CompareDate2(String date1,String date2){
          return (parseDate(date1).getTime() >= parseDate(date2).getTime());
	}


	public static JSONObject getYearMonthWeekDay(String pointDay){
		JSONObject jsonObject = new JSONObject();
		Date pointDate = DateUtils.parseDate(pointDay , "yyyy-MM-dd");
		int year = Integer.parseInt(DateUtils.getYear(pointDate));
		int month = Integer.parseInt(DateUtils.getMonth(pointDate));
		String week = DateUtils.getWeek(pointDate);
		int day = Integer.parseInt(DateUtils.getDay(pointDate));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(pointDate);
		int monthWeek = calendar.get(Calendar.WEEK_OF_MONTH);
		int targetYear = getPointYear(pointDate) ;
		int targetMonth = getPointMonth(pointDate) ;


		jsonObject.put("year" , targetYear);
		jsonObject.put("month" , targetMonth);

		int targetWeek = getPointWeek(pointDay) ;

		if(targetWeek == 0 || targetWeek == -1){
			String lastMonthDay = DateUtils.formatDate( DateUtils.addMonths(DateUtils.parseDate(pointDay , "yyyy-MM-dd") , -1), "yyyy-MM-dd");
			String lastDay = "31";
			int lastMonth = Integer.parseInt(lastMonthDay.substring(6,7));
			if(lastMonth == 4 || lastMonth == 6 || lastMonth == 9 || lastMonth == 11){
				lastDay = "30";
			}
			if(lastMonth == 2){
				if((year%4==0 && year%100!=0) || year%400==0){
					lastDay = "29";
				}else{
					lastDay = "28";
				}
			}
			targetWeek = getPointWeek(lastMonthDay.substring(0 , 8) + lastDay) ;
		}

		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
			if((day == 31 && week.equals("星期一")) ||
				(day == 31 && week.equals("星期二")) ||
				(day == 30 &&  week.equals("星期一"))
			){
				targetWeek = 1 ;
			}
		}

		if(month == 4 || month == 6 || month == 9 || month == 11){
			if((day == 30 && week.equals("星期一")) ||
			   (day == 30 && week.equals("星期二")) ||
			   (day == 29 &&  week.equals("星期一"))
			){
				targetWeek = 1 ;
			}
		}

		if (month == 2){
			if((year%4==0 && year%100!=0) || year%400==0){
				if(
					(day == 29 && week.equals("星期一")) ||
					(day == 29 && week.equals("星期二")) ||
					(day == 28 &&  week.equals("星期一"))
				){
					targetWeek = 1 ;
				}
			}else{
				if(
					(day == 28 && week.equals("星期一")) ||
					(day == 28 && week.equals("星期二")) ||
					(day == 27 &&  week.equals("星期一"))
				){
					targetWeek = 1 ;
				}
			}
		}

		jsonObject.put("week" , targetWeek);

		int targetDay = 0 ;
		if(week.equals("星期一")){
			targetDay = 1 ;
		}
		if(week.equals("星期二")){
			targetDay = 2 ;
		}
		if(week.equals("星期三")){
			targetDay = 3 ;
		}
		if(week.equals("星期四")){
			targetDay = 4 ;
		}
		if(week.equals("星期五")){
			targetDay = 5 ;
		}
		if(week.equals("星期六")){
			targetDay = 6 ;
		}
		if(week.equals("星期日")){
			targetDay = 7 ;
		}
		jsonObject.put("day" , targetDay);
		return jsonObject;
	}


	private static Integer getPointWeek(String pointDay){
		Date pointDate = DateUtils.parseDate(pointDay , "yyyy-MM-dd");
		int month = Integer.parseInt(DateUtils.getMonth(pointDate));
		String week = DateUtils.getWeek(pointDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(pointDate);
		int monthWeek = calendar.get(Calendar.WEEK_OF_MONTH);

		int targetWeek = monthWeek ;
		int localMonth = getPointMonth(DateUtils.parseDate(pointDay.substring(0 , 8) + "01" , "yyyy-MM-dd"));

		Date pointDate3 = DateUtils.parseDate(pointDay.substring(0 , 8) + "01" , "yyyy-MM-dd");
		Calendar calendar3 = Calendar.getInstance();
		calendar3.setTime(pointDate3);
		int monthWeek3 = calendar3.get(Calendar.WEEK_OF_MONTH);
		String week3 = DateUtils.getWeek(pointDate3);
		if(week3.equals("星期日")){
			monthWeek3 = monthWeek3 - 1 ;
		}

		if(month != localMonth){
			if(monthWeek3 > 0){
				targetWeek = monthWeek - 1 ;
			}
		}
		if(week.equals("星期日")){
			targetWeek = targetWeek - 1 ;
		}
		return targetWeek;
	}


	private static Integer getPointYear(Date pointDate){
		int year = Integer.parseInt(DateUtils.getYear(pointDate));
		int month = Integer.parseInt(DateUtils.getMonth(pointDate));
		String week = DateUtils.getWeek(pointDate);
		int day = Integer.parseInt(DateUtils.getDay(pointDate));
		int targetYear = year ;
		if(month == 1 && (
				(day == 1 && week.equals("星期四")) ||
				(day == 1 && week.equals("星期五")) ||
				(day == 1 && week.equals("星期六")) ||
				(day == 1 && week.equals("星期日")) ||
				(day == 2 &&  week.equals("星期五")) ||
				(day == 2 &&  week.equals("星期六")) ||
				(day == 2 &&  week.equals("星期日")) ||
				(day == 3 &&  week.equals("星期六")) ||
				(day == 3 &&  week.equals("星期日")) ||
				(day == 4 &&  week.equals("星期日"))
		)){
			targetYear = year - 1 ;
		}
		if(month == 12 && (
				(day == 31 && week.equals("星期一")) ||
				(day == 31 && week.equals("星期二")) ||
				(day == 30 &&  week.equals("星期一"))
		)){
			targetYear = year + 1 ;
		}
		return targetYear;
	}


	private static Integer getPointMonth(Date pointDate){
		int year = Integer.parseInt(DateUtils.getYear(pointDate));
		int month = Integer.parseInt(DateUtils.getMonth(pointDate));
		String week = DateUtils.getWeek(pointDate);
		int day = Integer.parseInt(DateUtils.getDay(pointDate));
		int targetMonth = month ;
		if(month == 1 && (
				(day == 1 && week.equals("星期四")) ||
				(day == 1 && week.equals("星期五")) ||
				(day == 1 && week.equals("星期六")) ||
				(day == 1 && week.equals("星期日")) ||
				(day == 2 &&  week.equals("星期五")) ||
				(day == 2 &&  week.equals("星期六")) ||
				(day == 2 &&  week.equals("星期日")) ||
				(day == 3 &&  week.equals("星期六")) ||
				(day == 3 &&  week.equals("星期日")) ||
				(day == 4 &&  week.equals("星期日"))
		)){
			targetMonth = 12 ;
		}
		if(month == 12 && (
				(day == 31 && week.equals("星期一")) ||
				(day == 31 && week.equals("星期二")) ||
				(day == 30 &&  week.equals("星期一"))
		)){
			targetMonth  = 1 ;
		}

		if(month != 1 && month != 12){
			if(
					(day == 1 && week.equals("星期四")) ||
					(day == 1 && week.equals("星期五")) ||
					(day == 1 && week.equals("星期六")) ||
					(day == 1 && week.equals("星期日")) ||
					(day == 2 &&  week.equals("星期五")) ||
					(day == 2 &&  week.equals("星期六")) ||
					(day == 2 &&  week.equals("星期日")) ||
					(day == 3 &&  week.equals("星期六")) ||
					(day == 3 &&  week.equals("星期日")) ||
					(day == 4 &&  week.equals("星期日"))
					){
				targetMonth = month - 1 ;
			}

			if(month == 3 || month == 5 || month == 7 || month == 8 || month == 10){
				if(
						(day == 31 && week.equals("星期一")) ||
						(day == 31 && week.equals("星期二")) ||
						(day == 30 &&  week.equals("星期一"))
						){
					targetMonth = month + 1 ;
				}
			}

			if(month == 4 || month == 6 || month == 9 || month == 11){
				if(
						(day == 30 && week.equals("星期一")) ||
						(day == 30 && week.equals("星期二")) ||
						(day == 29 &&  week.equals("星期一"))
						){
					targetMonth = month + 1 ;
				}
			}

			if(month == 2){
				if((year%4==0 && year%100!=0) || year%400==0){
					if(
							(day == 29 && week.equals("星期一")) ||
							(day == 29 && week.equals("星期二")) ||
							(day == 28 &&  week.equals("星期一"))
							){
						targetMonth = month + 1 ;
					}
				}else{
					if(
							(day == 28 && week.equals("星期一")) ||
							(day == 28 && week.equals("星期二")) ||
							(day == 27 &&  week.equals("星期一"))
							){
						targetMonth = month + 1 ;
					}
				}
			}
		}
		return targetMonth;
	}
}
