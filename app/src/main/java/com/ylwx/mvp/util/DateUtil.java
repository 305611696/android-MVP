package com.ylwx.mvp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 * @author Administrator
 *
 */
public class DateUtil {
	/**
	 * 日期格式 yyyy-MM-ddformatformat
	 */
	public static final String yyyy_MM_dd = "yyyy-MM-dd";
	/**
	 * 日期格式 yyyy-MM-dd HH:mm
	 */
	public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
	/**
	 * 日期格式 HH:mm
	 */
	public static final String HH_mm = "HH:mm";
	/**
	 * 日期格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	
	public static final String yyyyMM = "yyyyMM";
	
	public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	
	/**
	 * 比较时间，精确到毫秒，yyyy-MM-dd HH:mm:ss SSS
	 */
	public static final int compareTo_type_millisecond = 1;
	/**
	 * 比较时间，精确到秒，yyyy-MM-dd HH:mm:ss
	 */
	public static final int compareTo_type_second = 2;
	/**
	 * 比较时间，精确到分钟，yyyy-MM-dd HH:mm
	 */
	public static final int compareTo_type_minute = 3;
	/**
	 * 比较时间，精确到小时，yyyy-MM-dd HH
	 */
	public static final int compareTo_type_hour = 4;
	/**
	 * 比较时间，精确到天，yyyy-MM-dd
	 */
	public static final int compareTo_type_day = 5;
	/**
	 * 比较时间，精确到月，yyyy-MM
	 */
	public static final int compareTo_type_month = 6;
	
	/**
	 * 格式化时间
	 * @param 	pattern	格式，例如：yyyy-MM-dd
	 * @param 	object	时间
	 * @return	pattern时间格式
	 */
	public static String format(String pattern,Object object){
		return new SimpleDateFormat(pattern).format(object);
	}
	public static long[] getMonthStartAndEnd(String month){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM");
		Date date=new Date();
		try {
			date = simpleDateFormat.parse(month);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		long[] ls=new long[2];
		ls[0]=date.getTime();
		ls[1]=calendar.getTimeInMillis();
		return ls;
	}
	public static int compareTo(long millis1,long millis2,int compareTo_type){
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTimeInMillis(millis1);
		calendar2.setTimeInMillis(millis2);
		return compareTo(calendar1, calendar2, compareTo_type);
	}
	
	public static int compareTo(Calendar calendar1,Calendar calendar2,int compareTo_type){
		switch (compareTo_type) {
		case compareTo_type_second:

			calendar1.set(Calendar.MILLISECOND, 0);
			calendar2.set(Calendar.MILLISECOND, 0);
			break;
		case compareTo_type_minute:
			calendar1.set(Calendar.SECOND, 0);
			calendar2.set(Calendar.SECOND, 0);
			calendar1.set(Calendar.MILLISECOND, 0);
			calendar2.set(Calendar.MILLISECOND, 0);
			break;
		case compareTo_type_hour:
			calendar1.set(Calendar.MINUTE, 0);
			calendar2.set(Calendar.MINUTE, 0);
			calendar1.set(Calendar.SECOND, 0);
			calendar2.set(Calendar.SECOND, 0);
			calendar1.set(Calendar.MILLISECOND, 0);
			calendar2.set(Calendar.MILLISECOND, 0);
			break;
		case compareTo_type_day:
			calendar1.set(Calendar.HOUR, 0);
			calendar2.set(Calendar.HOUR, 0);
			calendar1.set(Calendar.MINUTE, 0);
			calendar2.set(Calendar.MINUTE, 0);
			calendar1.set(Calendar.SECOND, 0);
			calendar2.set(Calendar.SECOND, 0);
			calendar1.set(Calendar.MILLISECOND, 0);
			calendar2.set(Calendar.MILLISECOND, 0);
			break;
		case compareTo_type_month:
			calendar1.set(Calendar.DAY_OF_MONTH, 0);
			calendar2.set(Calendar.DAY_OF_MONTH, 0);
			calendar1.set(Calendar.HOUR, 0);
			calendar2.set(Calendar.HOUR, 0);
			calendar1.set(Calendar.MINUTE, 0);
			calendar2.set(Calendar.MINUTE, 0);
			calendar1.set(Calendar.SECOND, 0);
			calendar2.set(Calendar.SECOND, 0);
			calendar1.set(Calendar.MILLISECOND, 0);
			calendar2.set(Calendar.MILLISECOND, 0);
			break;
		default:
			break;
		}
		
		int c = calendar1.compareTo(calendar2);
		
		return c;
	} 
	
	
	public static void main(String[] args) throws Exception {
//		String start = "2014-01-03";
//		String end = "2014-01-04";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date dBegin = sdf.parse(start);
//		Date dEnd = sdf.parse(end);
//		List<Date> listDate = getDatesBetweenTwoDate(dBegin, dEnd);
//		for(int i=0;i<listDate.size();i++){
//			System.out.println(sdf.format(listDate.get(i)));
//		}
		String startTime = "2014-01-03";
		String endTime = "2014-01-04";
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.yyyy_MM_dd);
		Date dBegin = null;
		try {
			dBegin = sdf.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date dEnd = null;
		if ( endTime == null || endTime.equals("")) {
			dEnd = new Date();
		}else {
			try {
				dEnd =sdf.parse(endTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (dBegin != null && dEnd != null) {
			System.out.println(DateUtil.compareTo(dBegin.getTime(), dEnd.getTime(), DateUtil.compareTo_type_day));
			if (DateUtil.compareTo(dBegin.getTime(), dEnd.getTime(), DateUtil.compareTo_type_day)==0) {
				
			}
		}
	}

	/**
	 * 根据开始时间和结束时间返回时间段内的时间集合
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return List
	 */
	public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(beginDate);// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(new Date(cal.getTimeInMillis()+3600*24*1000l))) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		lDate.add(endDate);// 把结束时间加入集合
		return lDate;
	}
	public static Date pseDate(String dateStr,String pattern){
		Date date=null;
		try {
			date= new SimpleDateFormat(pattern).parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	 /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    public static long[] getTodayStatAndEnd(){
		long[] ls=new long[2];
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String dateStr= simpleDateFormat.format(date);
		try {
			ls[0]=simpleDateFormat.parse(dateStr).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ls[1]=ls[0]+3600l*24*1000;
		return ls;
	}
	public static long[] getOneDayStartAndEnd(String dateStr){
		long[] ls=new long[2];
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			ls[0]=simpleDateFormat.parse(dateStr).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ls[1]=ls[0]+3600l*24*1000;
		return ls;
	}
	
	/**
	 * 获取一天开始时间
	 * @param dateStr	yyyy-MM-dd
	 * @return	long
	 */
	public static long getOneDayStart(String dateStr){
		long startTime = 0;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			startTime = simpleDateFormat.parse(dateStr).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return startTime;
	}
	
	/**
	 * 获取一天结束时间
	 * @param dateStr	yyyy-MM-dd
	 * @return	long
	 */
	public static long getOneDayEnd(String dateStr){
		long endTime = 0;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			endTime = simpleDateFormat.parse(dateStr).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		endTime = endTime+3600l*24*1000;
		return endTime;
	}
	public static int getStartTime(int year,int month){
		int time=0;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyy-MM");
		try {
			time=(int)(simpleDateFormat.parse(year+"-"+month).getTime()/1000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}
	public static int getEndTime(int year,int month){
		int time=getStartTime(year,month);
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(time*1000l);
		calendar.add(Calendar.MONTH, 1);
		time=(int)(calendar.getTimeInMillis()/1000);
		return time;
	}
	/**
	 * 获取开始时间
	 * @param startTime
	 * @return
	 */
	public static int getStartTime(String startTime){
		int time=0;
		if (startTime!=null&&!startTime.equals("")) {
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyy-MM-dd");
			try {
				time=(int)(simpleDateFormat.parse(startTime).getTime()/1000);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return time;
	}
	/**
	 * 获取结束时间
	 * @param endTime
	 * @return
	 */
	public static int getEndTime(String endTime){
		int time=Integer.MAX_VALUE;
		if (endTime!=null&&!endTime.equals("")) {
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyy-MM-dd");
			try {
				time=(int)(simpleDateFormat.parse(endTime).getTime()/1000);
				time=time+24*60*60;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return time;
	}
	
	public static int getCurrentYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	public static int getCurrentMonth(){
		return Calendar.getInstance().get(Calendar.MONTH);
	}
}
