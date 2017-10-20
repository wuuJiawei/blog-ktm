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
	 * ��stringת��Ϊdate
	 * Ĭ�ϸ�ʽ yyyy-MM-dd
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
	 * ��stringת��Ϊdate
	 * û��Ĭ�ϸ�ʽ
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
	 * ��dateת��Ϊstring
	 * û��Ĭ�ϸ�ʽ
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
	 * @return true:ϵͳʱ���
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
	 * ʱ�����
	 * @param time1
	 * @param time2
	 * @return long ����
	 */
	public static long timeMinus(Date time1,Date time2) {
		long t1 = time1.getTime();
		long t2 = time2.getTime();
		
		return t2- t1;
	}
	
	/**
	 * �����������������
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
	 * �Ӻ���ת����Date
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
	 * ��ȡ��date���i�������
	 * @param date
	 * @param i ����/����
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
	 * ��ȡ��ǰ�·ݵ�����
	 * @return
	 */
	public static int getDayOfMonth(){
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int day=aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}
	
	/**
	 * ��������
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
                throw new IllegalArgumentException("���䲻�ܳ�����ǰ����");
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
