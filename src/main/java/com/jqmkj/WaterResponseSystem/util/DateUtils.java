package com.jqmkj.WaterResponseSystem.util;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	public static SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//	public static final String yyyyMMdd = "yyyyMMdd";
//    public static final String yyyyMMddHHmm = "yyyyMMddHHmm";
//    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
//    public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

    public static final String _yyyyMMdd = "yyyy-MM-dd";
    //public static final String _yyyyMMddHH = "yyyy-MM-dd HH";
    public static final String _yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
    public static final String _yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String _yyyyMMddHHmmssSSS = "yyyy-MM-dd HH:mm:ss.SSS";

//    public static final String __yyyyMMdd = "yyyy/MM/dd";
//    public static final String __yyyyMMddHHmm = "yyyy/MM/dd HH:mm";
//    public static final String __yyyyMMddHHmmss = "yyyy/MM/dd HH:mm:ss";

//
//    /**
//     * 获取当前月的第一天
//     */
//    public static Date getFastDateFromMonth(){
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.MONTH, -1);
//		calendar.set(Calendar.DAY_OF_MONTH, 1);
//		Date date = calendar.getTime();
//		return date;
//    }
//    /**
//     * 获取上个月的最后一天
//     */
//    public static Date getLastDateFromLastMonth(){
//    	Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.DAY_OF_MONTH, 1);
//		calendar.add(Calendar.DATE, -1);
//		Date date = calendar.getTime();
//		return date;
//    }
//    /**
//     * 获取这个月最后一天
//     * @return
//     */
//    public static Date getLastDateFromMonth(){
//    	Calendar calendar = Calendar.getInstance();
//    	calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//        Date date = calendar.getTime();
//		return date;
//    }

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public static String getPatternByDateType(String dateType){
        String pattern = "";
        String year  = Constants.DateTypeEnum.year.value();
        String month = Constants.DateTypeEnum.month.value();
        String day   = Constants.DateTypeEnum.day.value();
        String hour  = Constants.DateTypeEnum.hour.value();
        String minute  = Constants.DateTypeEnum.minute.value();
        if(month.equals(dateType)){
            pattern = "yyyy-MM";
        }else if(day.equals(dateType)){
            pattern = "yyyy-MM-dd";
        }else if(hour.equals(dateType)){
            pattern = "yyyy-MM-dd HH:mm";
        }else if(minute.equals(dateType)){
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        return  pattern;
    }
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

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null){
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
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(24*60*60*1000);
    }

    /**
     * 获取过去的小时
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(60*60*1000);
    }

    /**
     * 获取过去的分钟
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(60*1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis){
        long day = timeMillis/(24*60*60*1000);
        long hour = (timeMillis/(60*60*1000)-day*24);
        long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
        long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
        long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
        return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }
    /**
     * 随机时期"yyyy-MM-dd"
     */
    public static Date randomDate(String beginDate, String endDate,String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            Date start = format.parse(beginDate);// 构造开始日期
            Date end = format.parse(endDate);// 构造结束日期
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());

            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //
    //判断闰年
    public static boolean isLeap(int year)
    {
        if (((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0))
            return true;
        else
            return false;
    }
    //返回当月天数
    public static int getDays(int year, int month)
    {
        int days;
        int FebDay = 28;
        if (isLeap(year))
            FebDay = 29;
        switch (month)
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                days = FebDay;
                break;
            default:
                days = 0;
                break;
        }
        return days;
    }
    //
    // 获取当前时间所在年的周数
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }
    // 获取当前时间所在年的最大周数
    public static int getWeeks(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

        return getWeekOfYear(c.getTime());
    }
    //
    public static int getHours(){
        return 24;
    }
    public static int getMonths(){
        return 12;
    }


    public static int getYears(String beginDate,String endDate){
        int years = 0;
        if(Strings.isNullOrEmpty(beginDate) || Strings.isNullOrEmpty(endDate)) return 0;
        String pattern = "yyyy-MM";
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            Date start = format.parse(beginDate);// 构造开始日期
            Date end = format.parse(endDate);// 构造结束日期
            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            if (start.getTime() >= end.getTime()) {
                return 0;
            }
            startCal.setTime(start);
            endCal.setTime(end);
            return (endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return years;
    }

    public static int getMonths(String beginDate,String endDate){
        int months = 0;
        if(Strings.isNullOrEmpty(beginDate) || Strings.isNullOrEmpty(endDate)) return 0;
        String pattern = "yyyy-MM";
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            Date start = format.parse(beginDate);// 构造开始日期
            Date end = format.parse(endDate);// 构造结束日期
            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            if (start.getTime() >= end.getTime()) {
                return 0;
            }
            //long date = random(start.getTime(), end.getTime());
            startCal.setTime(start);
            endCal.setTime(end);
            int years = (endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR));
            int result = years * 12 + endCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH);
            return result == 0 ? 1 : Math.abs(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return months;
    }
    public static List<String> getMonthBetween(String beginDate, String endDate,Integer limit) {
        Integer offset = 1;
        return getBetweenBase(beginDate,endDate,Constants.DateTypeEnum.month.value(),Calendar.MONTH,offset,limit);
    }
    public static List<String> getDayBetween(String beginDate, String endDate,Integer limit) {
        Integer offset = 1;
        return getBetweenBase(beginDate,endDate,Constants.DateTypeEnum.day.value(),Calendar.DATE,offset,limit);
    }
    public static List<String> getHourBetween(String beginDate, String endDate,Integer limit) {
        Integer offset = 1;
        return getBetweenBase(beginDate,endDate,Constants.DateTypeEnum.hour.value(),Calendar.HOUR,offset,limit);
    }
    public static List<String> getMinuteBetween(String beginDate, String endDate,Integer limit) {
        Integer offset = 10;
        return getBetweenBase(beginDate,endDate,Constants.DateTypeEnum.minute.value(),Calendar.MINUTE,offset,limit);
    }
    private static List<String> getBetweenBase(String beginDate, String endDate,String dateType,Integer type,Integer offset,Integer limit){
        ArrayList<String> result = Lists.newArrayList();
        if(Strings.isNullOrEmpty(beginDate) || Strings.isNullOrEmpty(endDate)) return result;
        String pattern = getPatternByDateType(dateType);
        SimpleDateFormat format = new SimpleDateFormat(pattern);//格式化为年月
        try {
            Date start = format.parse(beginDate);// 构造开始日期
            Date end = format.parse(endDate);// 构造结束日期
            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            if (start.getTime() >= end.getTime()) {
                return result;
            }
            startCal.setTime(start);
            endCal.setTime(end);
            Calendar curr = startCal;
            boolean a = (limit!=null&&limit>0);
            while ( ( curr.before(endCal) || curr.equals(endCal) ) ) {
                boolean flag =  (a && result.size()>=limit);
                if(flag) break;
                //System.out.println(limit+","+flag+","+result.size());
                result.add(format.format(curr.getTime()));
                curr.add(type, offset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**     * Date转换为LocalDateTime     * @param date     */
    public static LocalDateTime date2LocalDateTime(Date date){
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        //System.out.println(localDateTime.toString());//2018-03-27T14:07:32.668
        //System.out.println(localDateTime.toLocalDate() + " " +localDateTime.toLocalTime());//2018-03-27 14:48:57.453
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(_yyyyMMddHHmmss);//This class is immutable and thread-safe.@since 1.8
        //System.out.println(dateTimeFormatter.format(localDateTime));//2018-03-27 14:52:57
        return localDateTime;
    }

    public static void main(String[] args) {
        String beginDate = "2017-01-01 00:00:00";
        String endDate   = "2017-01-01 10:00:00";
        List<String> lists = getMinuteBetween(beginDate,endDate,0);
        for (String s:lists){
            System.out.println(s);
        }
        System.out.println(lists.size());
//        List<String> mlists = getHourBetween(beginDate,endDate);
//        for (String s:mlists){
//            System.out.println(s);
//        }
    }



}
