package com.blog.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.springframework.web.bind.annotation.RequestMapping;

public class TimeUtil {
	
	/**
	 * 从string转换为date
	 * 默认格式 yyyy-MM-dd
	 * @param str
	 * @return
	 * @throws ParseException 
	 */
	public static Date getDateFromStr(String str) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date d=sdf.parse(str);
		return d;
	}
	
	/**
	 * 从string转换为date
	 * 没有默认格式
	 * @param str
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateFromStr(String str,String pattern) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		Date d=sdf.parse(str);
		return d;
	}
	
	/**
	 * 从date转换为string
	 * 没有默认格式
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getStrFromDate(Date date,String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String str = sdf.format(date);
		return str;
	}
	
	/**
	 * 
	 * @param time1
	 * @return true:系统时间大
	 */
	public static boolean compareWithSysDate(String time1) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String temp=sdf.format(new Date());//2017-04-24
			temp=temp+" "+time1;//
			SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date date=sdf2.parse(temp);
				Date date2=new Date();
				long timeLong=	date.getTime();
				long timeLong2=date2.getTime();
				if (timeLong<=timeLong2) {
					return true;
				}
				else {
					return false;
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			return false;
	}
	

	/**
	 * 时间相减
	 * @param time1
	 * @param time2
	 * @return long 毫秒
	 */
	public static long timeMinus(Date time1,Date time2) {
		long t1 = time1.getTime();
		long t2 = time2.getTime();
		
		return t2- t1;
	}
	
	/**
	 * 计算两日期相隔天数
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int getDateIntevel(Date fDate,Date oDate) {
		Calendar aCalendar = Calendar.getInstance();

       aCalendar.setTime(fDate);

       int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

       aCalendar.setTime(oDate);

       int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

       return day2 - day1;
	}
	
	/**
	 * 从毫秒转换到Date
	 * @return
	 * @throws ParseException 
	 */
	public static Date getDateByMillis(long millis) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		String time = formatter.format(calendar.getTime());
		return formatter.parse(time);
	}
	
	/**
	 * 获取与date相隔i天的日期
	 * @param date
	 * @param i 正数/负数
	 * @return
	 */
	public static Date getNextDay(Date date,Integer i) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.DAY_OF_MONTH, i);
	    date = calendar.getTime();
	    return date;
	}
	
	/**
	 * 获取当前月份的天数
	 * @return
	 */
	public static int getDayOfMonth(){
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int day=aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}
	
	/**
	 * 计算年龄
	 * @param dateOfBirth
	 * @return
	 */
	public static int getAge(Date dateOfBirth) {
        int age = 0;
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        if (dateOfBirth != null) {
            now.setTime(new Date());
            born.setTime(dateOfBirth);
            if (born.after(now)) {
                throw new IllegalArgumentException("年龄不能超过当前日期");
            }
            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
            int nowDayOfYear = now.get(Calendar.DAY_OF_YEAR);
            int bornDayOfYear = born.get(Calendar.DAY_OF_YEAR);
            System.out.println("nowDayOfYear:" + nowDayOfYear + " bornDayOfYear:" + bornDayOfYear);
            if (nowDayOfYear < bornDayOfYear) {
                age -= 1;
            }
        }
        return age;
    }

	
}
