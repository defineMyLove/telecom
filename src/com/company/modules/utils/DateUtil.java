package com.company.modules.utils;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
	
	
	public static void main(String[] args) {
//		for (int i = 1; i < 13; i++) {
//			System.out.println(DateUtil.getDateFromMonth(2013, i)[0]);
//		}
		getDateAdd(20121119155341l, 1, 14);
	}
	
	/**
	 * 取得给定月第一个周一的日期
	 * @param date 6位时间
	 * @return
	 * 作者：杨凯
	 */
	public static Long getFirstMondayOfMonth(Long date1){
		Long days [] = DateUtil.getDateFromMonth(DateUtil.getYear(date1),DateUtil.getMonth(date1));
		for (long i = days[0]; i <= days[1]; i++) {
			if(DateUtil.getDayOfWeek(i)==1){
				return i;
			}
		}
		return null;
	}
	
	/**
	 * 取得给定月第一个周一的日期
	 * @return
	 * 作者：杨凯
	 */
	public static Long getFirstMondayOfMonth(){
		return getFirstMondayOfMonth(DateUtil.getCurrentDate6());
	}
	
	
	public static Long getToday14(){
		return ConvertUtil.obj2Long(DateUtil.getCurrentDate8()+""+"235959");
	}

	/**
	 * 取得年度
	 * @param date
	 * @return
	 * 作者：杨凯
	 */
	public static int getYear(Long date){
		return ConvertUtil.obj2Integer(StringUtils.left(date.toString(), 4));
	}
	
	/**
	 * 取得给定时间几周后的周几的日期
	 * @param date 给定8位时间
	 * @param zhouci 给定增加几周
	 * @param week 给定周几
	 * @return
	 * 作者：杨凯
	 */
	public static Long getDayInWeekAdd(Long date,int zhouci,int week){
		int days = zhouci * 7 + week - 1;
		//给定年
		int x = ConvertUtil.obj2Integer(StringUtils.left(date.toString(), 4));
		//给定周次
		int n = DateUtil.getWeekForYear(date);
		return DateUtil.getDateAdd(DateUtil.getDateFormWeek(x, n)[0], days, 8);
 	}
	
	
	public static int getJidu(Long date){
		if (date.toString().length() < 6) {
			throw new RuntimeException("提供的参数date" + date + "必须大于6位数");
		}
		int yuef = ConvertUtil.obj2Integer(date.toString().substring(4, 6));
		if (yuef <= 3) {
			return 1;
		} else if (yuef <= 6) {
			return 2;
		} else if (yuef <= 9) {
			return 3;
		} else {
			return 4;
		}
	}
	
	
	public static int getMonth(Long date){
		if (date.toString().length() < 6) {
			throw new RuntimeException("提供的参数date" + date + "必须大于6位数");
		}
		return ConvertUtil.obj2Integer(date.toString().substring(4, 6));
	}
	
	/**
	 * 取得某年的第几周的周几的日期
	 * @param year
	 * @param zhouci
	 * @param week
	 * @return
	 * 作者：杨凯
	 */
	public static Long getDayInWeek(int year,int zhouci,int week){
		Long[] sjs = DateUtil.getDateFormWeek(year, zhouci);
		return DateUtil.getDateAdd(sjs[0], week-1, 8);
	}
	
	
	
	/**
	 * 根据提供的时间取得本月最后一天的6位时间表示
	 * @param ldate
	 * @return
	 * 作者：杨凯
	 */
	public static Long lastDayOfMonth(Long ldate){
	        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
	        Calendar cal=format.getCalendar();
	        try {
	        	cal.setTime(format.parse(ldate.toString()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			cal.set(Calendar.DAY_OF_MONTH,1); 
			cal.roll(Calendar.DAY_OF_MONTH,-1); 
		return ConvertUtil.obj2Long(DateUtil.format(cal.getTime(), "yyyyMMdd")); 
	} 

	public static int getWeekOfMonthForNew() {
		int year = DateUtil.getYear();
		int yue = DateUtil.getMonth();
		
		long now = DateUtil.getCurrentDate8().longValue();
		
		Long days [] = DateUtil.getDateFromMonth(year,yue);
		int week = 0;
		for (long i = days[0]; i <= days[1]; i++) {
			if(DateUtil.getDayOfWeek(i)==1){
				week ++;
			}
			if ( now  == i) {
				return week == 0 ? 1 : week;
			}
			
		}
		return week;
	}
	
	
	
	/**
	 * 取得当前周是本月的第几周
	 * @return
		@deprecated
	 * 作者：杨凯
	 */
	public static int getWeekOfMonth(){
		String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
		SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);
		pdt.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
		pdt.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
		Calendar calendar = new GregorianCalendar(pdt);
		calendar.setFirstDayOfWeek(2);
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}
	
	/**
	 * 取得当前周是本年的第几周
	 * @return
	 * 作者：杨凯
	 */
	public static int getWeekOfYear(){
		String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
		SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);
		pdt.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
		pdt.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
		Calendar calendar = new GregorianCalendar(pdt);
		calendar.setFirstDayOfWeek(2);
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	/**
	 * 本周 周一8位日期和 周日 8位日期
	 * @return
	 */
	public  static Long[] getStartEndWeek() {
		Long date [] = new Long[2];
		SimpleDateFormat s=new SimpleDateFormat("y年M月d日 E H时m分s秒",Locale.CHINA);
		Calendar c=Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(System.currentTimeMillis());
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		date[0] = DateUtil.Calendar2Long(c, 8);
		
		c.add(Calendar.WEEK_OF_YEAR, 1);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		date[1] = DateUtil.Calendar2Long(c, 8);
		return date;
	}
	
	/**
	 * 取得指定时间是一年中的第几周
	 * @param date
	 * @return
	 * 作者：杨凯
	 */
	public static Integer getWeekForYear(Long date) {
		if (date == null) {
			return 0;
		}
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(2);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            cal.setTime(format.parse(date.toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        
//        if (getDayOfWeek(date) == 7) {
//			return week - 1;
//		}
        
        return week;
    }
	
	/**
	 * 取得指定时间是一月中的第几周
	 * @param date
	 * @return
	 * 作者：杨凯
	 */
	public static int getWeekForMonth(Long date) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(2);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            cal.setTime(format.parse(date.toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int week = cal.get(Calendar.WEEK_OF_MONTH);
        return week;
    }
	
	
	/**
	 * 指定某一天是一周之内的周几
	 * @param date
	 * @return
	 * 作者：杨凯
	 */
	public static int getDayOfWeek(Long date){
		if (date.toString().length() > 8) {
			date = ConvertUtil.obj2Long(date.toString().substring(0,8));
		}
		 Calendar cal = Calendar.getInstance();
	        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	        try {
	            cal.setTime(format.parse(date.toString()));
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        int week = cal.get(Calendar.DAY_OF_WEEK)-1;
	        return week == 0 ? 7 : week;
	}
	
	public static String getDayOfWeek4Chinese(){
		String now = DateUtil.getCurrentDate8().toString();
		 Calendar cal = Calendar.getInstance();
	        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	        try {
	            cal.setTime(format.parse(now));
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        int week = cal.get(Calendar.DAY_OF_WEEK)-1;
	        String dayOfWeek = "";
	        switch (week) {
			case 0:
				dayOfWeek = "日";
				break;
			case 1:
				dayOfWeek = "一";
				break;
			case 2:
				dayOfWeek = "二";
				break;
			case 3:
				dayOfWeek = "三";
				break;
			case 4:
				dayOfWeek = "四";
				break;
			case 5:
				dayOfWeek = "五";
				break;
			case 6:
				dayOfWeek = "六";
				break;
			default:
				break;
			}
	        return  dayOfWeek;
	}
	
	/**
	 * 获取指定年及周数，所对应的开始日期及结束日期，即：周一的时间和周日的时间
	 * @param x
	 * @param n
	 * @return
	 * 作者：杨凯
	 */
	public static Long[] getDateFormWeek(int x,int n){
		Calendar cal = new GregorianCalendar(x, Calendar.JANUARY, 1);

		int ff=cal.get(Calendar.DAY_OF_WEEK);
		int week=cal.get(Calendar.WEEK_OF_YEAR);
		switch(ff){
		case 1:
		cal.set(Calendar.DATE,7*(n-2)+ff+7);
		break;
		case 2:
		cal.set(Calendar.DATE,7*(n-2)+ff+6);
		break;
		case 3:
		cal.set(Calendar.DATE,7*(n-2)+ff+5);
		break;
		case 4:
		cal.set(Calendar.DATE,7*(n-2)+ff+4);
		break;
		case 5:
		cal.set(Calendar.DATE,7*(n-2)+ff+3);
		break;
		case 6:
		cal.set(Calendar.DATE,7*(n-2)+ff+2);
		break;
		case 7:
		cal.set(Calendar.DATE,7*(n-2)+ff+1);
		break;
		}

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		Calendar calFirstDayInThisWeek = (Calendar) cal.clone();
		calFirstDayInThisWeek.add(Calendar.DATE, cal.getActualMinimum(Calendar.DAY_OF_WEEK)-dayOfWeek);
		Calendar calLastDayInThisWeek = (Calendar) cal.clone();
		calLastDayInThisWeek.add(Calendar.DATE,cal.getActualMaximum(Calendar.DAY_OF_WEEK)-dayOfWeek);
		Date startDate=calFirstDayInThisWeek.getTime();
		Date endDate=calLastDayInThisWeek.getTime();
		
		SimpleDateFormat simFormat = new SimpleDateFormat("yyyyMMdd", Locale.CHINA); 
		//开始时间
		String sd = simFormat.format(startDate); 
		//结束时间
		String ed = simFormat.format(endDate);
		return new Long[]{DateUtil.getDateAdd(Long.valueOf(sd), 1, 8),DateUtil.getDateAdd(Long.valueOf(ed), 1, 8)};
	}
	
	
	
	/** 月份转季度
	 * @param month
	 * @return
	 * 作者：杨凯
	 */
	public static int month2Jidu(int month){
		if (month <= 3) {
			return 1;
		} 
		if (month <= 6) {
			return 2;
		}
		if (month <= 9) {
			return 3;
		}
		if (month <= 12) {
			return 4;
		}
		return 0;
	}
	
	public static String long2StrDate(Number date) {
		String fmDate = new String();
		if (date == null) {
			fmDate = "";
		} else {
			fmDate = date.toString();
			String year = "";
			String month = "";
			String day = "";
			String hour = "";
			String minute = "";
			// 日期格式不合法则转化为空串
			if (fmDate.length() < 8) {
				fmDate = "";
			}
			if (fmDate.length() >= 8) {
				year = fmDate.substring(0, 4);
				month = fmDate.substring(4, 6);
				day = fmDate.substring(6, 8);
				fmDate = year + "/" + month + "/" + day;
			}
			if ((date.toString()).length() >= 12) {
				hour = (date.toString()).substring(8, 10);
				minute = (date.toString()).substring(10, 12);
				fmDate = fmDate + " " + hour + ":" + minute;
			}
			if ((date.toString()).length() == 14) {
				fmDate = fmDate + ":" + (date.toString()).substring(12, 14);
			}
		}
		return fmDate;
	}
	public static Calendar long2Calendar(Long time) {
		Calendar calendar = null;
		if (time == null) {
			calendar = Calendar.getInstance();
		} else {
			String strTime = String.valueOf(time);
			int year = 0;
			int month = 0;
			int day = 0;
			int hour = 0;
			int minute = 0;
			int sec = 0;
			if (strTime.length() < 8) {
				throw new RuntimeException("时间格式不合法，不能少与八位数字！");
			}
			if (strTime.length() >= 8) {
				year = Integer.valueOf(strTime.substring(0, 4));
				month = Integer.valueOf(strTime.substring(4, 6)) - 1;
				day = Integer.valueOf(strTime.substring(6, 8));
			}
			if (strTime.length() >= 10) {
				hour = Integer.valueOf(strTime.substring(8, 10));
			}
			if (strTime.length() >= 12) {
				minute = Integer.valueOf(strTime.substring(10, 12));
			}
			if (strTime.length() >= 14) {
				sec = Integer.valueOf(strTime.substring(12, 14));
			}
			calendar = new GregorianCalendar(year, month, day, hour, minute, sec);
		}
		return calendar;
	}
	
	/**
	 * 功能描述：返回传入的时间距离现在的时间的毫秒数<BR>
	 * @param date
	 * @return
	 * @author:杨凯<BR>
	 * 时间：Apr 16, 2009 9:16:32 AM<BR>
	 */
	public static Long getLongDate2Now(Long date){
		Calendar calendar = DateUtil.long2Calendar(date);
		return Calendar.getInstance().getTimeInMillis() - calendar.getTimeInMillis();
	}
	
	/**
	 * 功能描述：判断12位的日期距离现在的时间<BR>
	 * @param date
	 * @return 返回的格式为：XX分钟之前，XX小时之前，XX天之前
	 * @author:杨凯<BR>
	 * 时间：Apr 14, 2009 2:55:25 PM<BR>
	 */
	public static String getFrom2Now12(Long date){
		Calendar dateCalendar = DateUtil.long2Calendar(date);
		Calendar nowCalendar = Calendar.getInstance();
		Long time = nowCalendar.getTimeInMillis() - dateCalendar.getTimeInMillis();
		long intTime = time/(1000*60);
		if (intTime < 60) {
			return intTime+"分钟之前";
		}
		if (intTime > 60 && intTime/60 < 24) {
			return intTime/60 + "小时之前";
		}
		if (intTime/60 > 24 && intTime/(60*24) < 100) {
			return intTime/(60*24)+"天之前";
		}
		return "很多天之前";
	}
	
	/**
	 * 功能描述：判断14位的日期距离现在的时间<BR>
	 * @param date
	 * @return 返回的格式为：XX分钟之前，XX小时之前，XX天之前
	 * @author:杨凯<BR>
	 * 时间：Apr 14, 2009 2:55:25 PM<BR>
	 */
	public static String getFrom2Now14(Long date){
		date = Long.valueOf(StringUtils.substring(String.valueOf(date), 0, 12));
		return getFrom2Now12(date);
	}
	
	/**
	 * 功能描述：得到从from到to的插值时间，即指定的时间间隔多少天<BR>
	 * @param from
	 * @param to
	 * @return XX天
	 * @author:杨凯<BR>
	 * 时间：Apr 17, 2009 6:40:10 PM<BR>
	 */
	public static Long getFromTime2TimeByDay(Long from,Long to){
		if (from == null || to == null) {
			return null;
		}
		Calendar fromCalendar = DateUtil.long2Calendar(from);
		Calendar toCalendar = DateUtil.long2Calendar(to);
		Long time = toCalendar.getTimeInMillis() - fromCalendar.getTimeInMillis();
		long intTime = time/(1000*60);
		return intTime/(60*24);
		
	}
	
	/**
	 * 功能描述：返回给定日期加上多少天之后的时间<BR>
	 * @param from   yyyyMMdd
	 * @param day
	 * @param length
	 * @return
	 * @author:杨凯<BR>
	 * 时间：Apr 18, 2009 11:56:16 AM<BR>
	 */
	public static Long getDateAdd(Long from,int day,int length){
		System.out.println(from);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(from.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		calendar.add(Calendar.DAY_OF_MONTH, day);
		String date = sdf.format(calendar.getTime());
		return ConvertUtil.obj2Long(StringUtils.left(date, length));
	}

	public static int getYear(){
		// 获得当前日期
		Calendar cldCurrent = Calendar.getInstance();
		return cldCurrent.get(Calendar.YEAR);
	}


	
	/**
	 * 一月中的第几天即：12月3日
	 * @return
	 * @version V1.0.0
	 * @author 杨凯
	 * @date Apr 1, 2014 3:59:30 PM
	 */
	public static int getDayOfMonth(){
		// 获得当前日期
		Calendar cldCurrent = Calendar.getInstance();
		return cldCurrent.get(Calendar.DATE);
	}
	
	
	public static int getMonth(){
		// 获得当前日期
		Calendar cldCurrent = Calendar.getInstance();
        return cldCurrent.get(Calendar.MONTH) + 1;
	}
    public static String getPrevMonth(String format){
        String str = "";
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DATE, 1);//把日期设置为当月第一天
        return sdf.format(cal.getTime());
    }

	public static int getNextMonth(){
       String str = "";
       SimpleDateFormat sdf=new SimpleDateFormat("MM");        
   
       Calendar lastDate = Calendar.getInstance();    
      lastDate.add(Calendar.MONTH,1);//减一个月    
      lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天     
       str=sdf.format(lastDate.getTime());    
       return ConvertUtil.obj2Integer(str);      
	}
	
	public static Long getNextMonthDate(){
		  String str = "";    
	       SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");        
	   
	       Calendar lastDate = Calendar.getInstance();    
	      lastDate.add(Calendar.MONTH,1);//减一个月    
	      lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天     
	       str=sdf.format(lastDate.getTime());    
	       return ConvertUtil.obj2Long(str);      
	}
	
	
	
	public static String getCurrentDateTimeView() {
		// 获得当前日期
		Calendar cldCurrent = Calendar.getInstance();
		// 获得年月日
		String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
		String strMonth = String.valueOf(cldCurrent.get(Calendar.MONTH) + 1);
		String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));
		String srtHours = String.valueOf(cldCurrent.get(Calendar.HOUR_OF_DAY));
		String strMinute = String.valueOf(cldCurrent.get(Calendar.MINUTE));
		String second = String.valueOf(cldCurrent.get(Calendar.SECOND));
		// 整理格式
		strMonth = strMonth.length() < 2 ? "0" + strMonth : strMonth;
		strDate = strDate.length() < 2 ? "0" + strDate : strDate;
		srtHours = srtHours.length() < 2 ? "0" + srtHours : srtHours;
		strMinute = strMinute.length() < 2 ? "0" + strMinute : strMinute;
		second = second.length() < 2 ? "0" + second : second;
		// 得出当天日期时间的字符串
		String StrCurrentCalendar = strYear + "-" + strMonth + "-" + strDate + " " + srtHours + ":" + strMinute + ":" + second;
		return StrCurrentCalendar;
	}
	
	public static Long Calendar2Long(Calendar calendar,int length){
		String strYear = String.valueOf(calendar.get(Calendar.YEAR));
		String strMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String strDate = String.valueOf(calendar.get(Calendar.DATE));
		String srtHours = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		String strMinute = String.valueOf(calendar.get(Calendar.MINUTE));
		String second = String.valueOf(calendar.get(Calendar.SECOND));
		// 整理格式
		strMonth = strMonth.length() < 2 ? "0" + strMonth : strMonth;
		strDate = strDate.length() < 2 ? "0" + strDate : strDate;
		srtHours = srtHours.length() < 2 ? "0" + srtHours : srtHours;
		strMinute = strMinute.length() < 2 ? "0" + strMinute : strMinute;
		second = second.length() < 2 ? "0" + second : second;
		
		if (length == 8) {
			return Long.valueOf(strYear + strMonth + strDate);
		}
		if (length == 14) {
			return Long.valueOf(strYear + strMonth + strDate+ srtHours + strMinute + second);
		}
		
		return Long.valueOf(strYear + strMonth + strDate+ srtHours + strMinute);
	}
	
	/**
	 * 功能描述：取得12位当前时间<BR>
	 * @return
	 * @author:杨凯<BR>
	 * 时间：Apr 18, 2009 11:55:28 AM<BR>
	 */
	public static Long getCurrnetDate12(){
		Calendar cldCurrent = Calendar.getInstance();
		return DateUtil.Calendar2Long(cldCurrent, 12);
	}
	
	/**
	 * 功能描述：取得8位当前时间<BR>
	 * @return
	 * @author:杨凯<BR>
	 * 时间：Apr 18, 2009 11:55:16 AM<BR>
	 */
	public static Long getCurrentDate8(){
		Calendar cldCurrent = Calendar.getInstance();
		return DateUtil.Calendar2Long(cldCurrent, 8);
	}
	
	/**
	 * 功能描述：取14位当前时间<BR>
	 * @return
	 * @author:杨凯<BR>
	 * 时间：May 4, 2009 10:43:31 AM<BR>
	 */
	public static Long getCurrentDate14(){
		Calendar cldCurrent = Calendar.getInstance();
		return DateUtil.Calendar2Long(cldCurrent, 14);
	}
	
	public static Long getCurrentDate6(){
		return ConvertUtil.obj2Long(StringUtils.left(getCurrentDate8().toString(), 6));
	}
	
	/**
	 * 功能描述：日期格式化<BR>
	 * @param str yyyy MM dd HH mm ss
	 * @param time
	 * @return
	 * @author:杨凯<BR>
	 * 时间：Apr 10, 2009 12:44:00 PM<BR>
	 */
	public static String format(String str,Object time){
		if (str==null||time == null || time.equals("")) {
			return null;
		}
		String strTime = ConvertUtil.obj2Str(time).replaceAll(" ", "");
		str = str.replaceAll("yyyy", strTime.substring(0, 4));
		str = str.replaceAll("MM", strTime.substring(4,6));
		if (StringUtils.contains(str, "dd")) {
			str = str.replaceAll("dd", strTime.substring(6, 8));
		}
		if (StringUtils.contains(str, "HH")) {
			str = str.replaceAll("HH", strTime.substring(8,10));
		}
		if (StringUtils.contains(str, "mm")) {
			str =  str.replaceAll("mm",strTime.substring(10, 12));
		}
		if (StringUtils.contains(str, "ss")) {
			str =  str.replaceAll("ss",strTime.substring(12, 14));
		}
		
		return str;
	}
	
	/**
	 * 功能描述：把短的时间转为14位的<BR>
	 * @param t
	 * @return
	 * @author 胡晓 huxiao.djwl@qq.com<BR>
	 * 时间：2009-11-23 下午03:28:52<BR>
	 */
	public static long trimTo14(Object t){
		String time = ConvertUtil.obj2Str(t);
		time = time.replaceAll("-", "");
		time = time.trim();
		for (int i = time.length(); i < 14; i++) {
			time += "0";
		}
		return ConvertUtil.obj2Long(time);
	}
	
	/**
	 * 日期格式化
	 * @param date
	 * @param expression yyyy MM dd HH mm ss
	 * @return
	 * 作者：杨凯
	 */
	public static String format(Date date,String expression){
		SimpleDateFormat time=new SimpleDateFormat(expression); 
		
		return time.format(date);
	}
	
	/**
	 *  获取上周的年度周次.原理:获取到上周4
	 * @author <a href="mailto:winhunter@163.com">郝世博</a>
	 * @time Jun 29, 2011 10:20:58 PM
	 */
	public static Integer getPrevWeekForYear(Long date){
		int week = DateUtil.getDayOfWeek(date);
		Long prevWeekDate = DateUtil.getDateAdd(date, week-(week+4), 8);
		return DateUtil.getWeekForYear(prevWeekDate);
	}
	
	/**
	 *  获取下周的年度周次. 原理:获取到下周4
	 * @author <a href="mailto:winhunter@163.com">郝世博</a>
	 * @time Jun 29, 2011 10:20:58 PM
	 */
	public static Integer getNextWeekForYear(Long date){
		int week = DateUtil.getDayOfWeek(date);
		Long prevWeekDate = DateUtil.getDateAdd(date, 7-week+4, 8);
		return DateUtil.getWeekForYear(prevWeekDate);
	}
	
	/**
	 * 取得当前月开始日期及结束日期8位时间
	 * @return
	 * 作者：杨凯
	 */
	public static Long[] getStartAndEndDateFormCurrentMonth(){
		int year = DateUtil.getYear();
		int month = DateUtil.getMonth();
		return getDateFromMonth(year, month);
	}
	
	
	/**
	 * 每月开始结束日期8位时间
	 * @return
	 */
	public static Long []  getDateFromMonth (int y,int m) {
		Calendar cldCurrent = new GregorianCalendar(y, m, 0);
//		cldCurrent.add(Calendar.MONTH, +1);
		
		int month = cldCurrent.get(Calendar.MONTH)+1;
		
		String nextMonth ="";
		String dqMonth ="";
		if(month<=9){
			dqMonth = "0"+(month);
		}else{
			dqMonth = ""+(month);
		}
		if((month+1)<=9){
			nextMonth = "0"+((month+1));
		}else{
			nextMonth = ""+((month+1));
		}
		String nextMonthdate = cldCurrent.get(Calendar.YEAR)+nextMonth+"01";
		Long months [] = new Long[2];
		months[0]=ConvertUtil.obj2Long((cldCurrent.get(Calendar.YEAR)+dqMonth+"01"));
		months[1] = ConvertUtil.obj2Long((DateUtil.getDateAdd(ConvertUtil.obj2Long(nextMonthdate), -1, 8)));
		return months ;
	}
	/**
	 * 加月份
	 * 描述：
	 * @param time
	 * @param addNum
	 * @return
	 * 作者：李坤
	 * 时间：Aug 22, 2011
	 */
	public static Long  getDateAdd6FromMonth (Long time,int addNum) {
		Calendar calendar = null;
		if (time == null) {
			calendar = Calendar.getInstance();
		} else {
			String strTime = String.valueOf(time);
			int year = 0;
			int month = 0;
			int day = 0;
			int hour = 0;
			int minute = 0;
			int sec = 0;
			year = Integer.valueOf(strTime.substring(0, 4));
			month = Integer.valueOf(strTime.substring(4, 6)) ;
			calendar = new GregorianCalendar(year, month, 0, 0, 0, 0);
		}
		calendar.add(Calendar.MONTH, addNum);
		String strMonth = (calendar.get(Calendar.MONTH) + 1)+"";
		strMonth  = strMonth.length() < 2 ? "0" + strMonth : strMonth;
		return ConvertUtil.obj2Long(calendar.get(Calendar.YEAR)+strMonth);
	}
	
	public static Long  getDateAdd6FromMonth2 (int year, int month,int addNum) {
		Calendar calendar = null;
		int day = 0;
		int hour = 0;
		int minute = 0;
		int sec = 0;
		calendar = new GregorianCalendar(year, month, 0, 0, 0, 0);
		calendar.add(Calendar.MONTH, addNum);
		String strMonth = (calendar.get(Calendar.MONTH) + 1)+"";
		strMonth  = strMonth.length() < 2 ? "0" + strMonth : strMonth;
		return ConvertUtil.obj2Long(calendar.get(Calendar.YEAR)+strMonth);
	}
	public static String format2Message(String time){
		if (time == null || time.equals("")) {
			return null;
		}
		String result = time.substring(0, 4) + "-";
		result += time.substring(4,6).startsWith("0") ? time.substring(5,6) : time.substring(4,6);
		result += "-" + (time.substring(6,8).startsWith("0") ? time.substring(7,8) : time.substring(6,8));
		
		result += " " + DateUtil.format("hh:MM:ss", time);
		
		return result;
	}
	
	/**
	 * 取得两个时间相减所得到的毫秒数
	 * @param kssj
	 * @param jssj
	 * @return
	 * 作者：杨凯
	 */
	public static Long getMillisFormDate2Date(Long kssj,Long jssj){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar ksCalendar = Calendar.getInstance();
		Calendar jsCalendar = Calendar.getInstance();
		try {
			ksCalendar.setTime(sdf.parse(kssj.toString()));
			jsCalendar.setTime(sdf.parse(jssj.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		long millis = jsCalendar.getTime().getTime() - ksCalendar.getTime().getTime();
		return millis;
	}
	
	/**
	 * 取得两个时间相减所得到的秒数
	 * @param kssj
	 * @param jssj
	 * @return
	 * 作者：杨凯
	 */
	public static Long getSecondsFormDate2Date(Long kssj,Long jssj){
		Long millis = getMillisFormDate2Date(kssj, jssj);
		return millis/1000;
	}
	/**
	 * 功能描述：返回给定日期加上多少分钟之后的时间<BR>
	 * @param from   yyyyMMddhhmmss
	 * @param hour
	 * @param length
	 * @return
	 * @author:杨凯<BR>
	 * 时间：Apr 18, 2009 11:56:16 AM<BR>
	 */
	public static Long getMillisAdd(Long from,int Millis){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(from.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		calendar.add(Calendar.MILLISECOND, Millis);
		String date = sdf.format(calendar.getTime());
		return ConvertUtil.obj2Long(date);
	}
	public static String getDay() {
		return StringUtils.right("" + getCurrentDate8(), 2);
	}

    public static String getDate2Now2(Long date){
		if (date.toString().length() < 14) {
			date = ConvertUtil.obj2Long(date + "235959");
		}
		Calendar dateCalendar = DateUtil.long2Calendar(date);
		Calendar nowCalendar = Calendar.getInstance();
		Long time = nowCalendar.getTimeInMillis() - dateCalendar.getTimeInMillis() ;
		long intTime = time/(1000*60);
		if (intTime < 60 && intTime >= 0) {
			return intTime+"分钟以前";
		}
		if (intTime > 60 && intTime/60 < 24) {
			return intTime/60 + "小时以前";
		}
		if (intTime/60 > 24 && intTime/(60*24) < 100) {
			return intTime/(60*24)+"天以前";
		}
		return "很久以前";
	}


    /**
     *  把日期字符串转换成日期类型
     * @param aMask
     *            the date pattern the string is in
     * @param strDate
     *            a string representation of a date
     * @return a converted Date object
     * @see java.text.SimpleDateFormat
     * @throws java.text.ParseException
     * @author by zxl
     */
    public static final Date convertStringToDate(String aMask, String strDate) {
        if (aMask == null || strDate == null || strDate.equals("")
                || "".equals(aMask)) {
            return null;
        }
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            return date;
        }

        return (date);
    }

    /**
     * This method converts a String to a date using the datePattern
     *
     * @param strDate
     *            the date to convert (in format MM/dd/yyyy)
     * @return a date object
     *
     * by zxl
     */
    public static Date convertStringToDate(String strDate) {
        Date aDate = null;
        aDate = convertStringToDate(getDatePattern(), strDate);
        return aDate;
    }
    private static String defaultDatePattern = "yyyy-MM-dd";;

    /**
     *    by zxl
     */
    public static synchronized String getDatePattern() {
        return defaultDatePattern;
    }

    public static String convertMillisToString(Long date){
        DateFormat formatter = new SimpleDateFormat(getDatePattern());
        long now = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);
        System.out.println(now + " = " + formatter.format(date));
        return   formatter.format(date);
    }

    public static String convertMillisToString(Long date,String pattern){
        DateFormat formatter = new SimpleDateFormat(StringUtil.isBlank(pattern)?getDatePattern():pattern);
        long now = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);
        System.out.println(now + " = " + formatter.format(date));
        return   formatter.format(date);
    }
}
