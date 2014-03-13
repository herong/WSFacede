/**
 * 
 */
package com.github.herong.comm.util;

/**
 * TODO 这里用文字描述这个类的主要作用
 * @author herong
 * @createTime 2013-7-12 上午08:55:56
 * @modifier 
 * @modifyDescription 描述本次修改内容
 * @see
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {


    // 获取当前系统日期值
    public enum CurDate {
        YYYY("yyyy"),
        YYYYMM("yyyyMM"),
        YYYYMMDD("yyyyMMdd"),
        YYYYMMDDHH("yyyyMMddHH"),
        YYYYMMDDHHmm("yyyyMMddHHmm"),
        YYYYMMDDHHmmss("yyyyMMddHHmmss"),
        YYYYMMDDHHmmssSSS("yyyyMMddHHmmssSSS"),

        YYYYMMDD_LINE("yyyy-MM-dd"),
        YYYYMMDDHH_LINE("yyyy-MM-dd HH"),
        YYYYMMDDHHmm_LINE("yyyy-MM-dd HH:mm"),
        YYYYMMDDHHmmss_LINE("yyyy-MM-dd HH:mm:ss"),
        YYYYMMDDHHmmssSSS_LINE("yyyy-MM-dd HH:mm:ss.SSS"),

        YYYYMMDD_DO("yyyy.MM.dd"),
        YYYYMMDDHH_DO("yyyy.MM.dd HH"),
        YYYYMMDDHHmm_DO("yyyy.MM.dd HH:mm"),
        YYYYMMDDHHmmss_DO("yyyy.MM.dd HH:mm:ss"),
        YYYYMMDDHHmmssSSS_DO("yyyy.MM.dd HH:mm:ss.SSS"),

        YYYYMMDD_ZH("yyyy年MM月dd日"),
        YYYYMMDDHH_ZH("yyyy年MM月dd日 HH时"),
        YYYYMMDDHHmm_ZH("yyyy年MM月dd日 HH时mm分"),
        YYYYMMDDHHmmss_ZH("yyyy年MM月dd日 HH时mm分ss秒"),
        YYYYMMDDHHmmssSSS_ZH("yyyy年MM月dd日 HH时mm分ss秒SSS毫秒"),
        HH("HH"),
        HHmm("HH:mm"),
        HHmmss("HH:mm:ss"),
        HHmmssSSS("HH:mm:ss:SSS");

        SimpleDateFormat fm = (SimpleDateFormat) DateFormat.getDateTimeInstance();
        private String pattern;

        private CurDate(String pattern) {
            this.pattern = pattern;
        }

        /**
         * 获取数据库服务器格式化之后的日期
         * 
         * @return 格式化之后字符串日期
         */
        public String getDate() {

            fm.applyPattern(this.pattern);
            return fm.format(new Date());

            /*
             * try { fm.applyPattern(this.pattern); String strD =
             * DateUtil.getDbDate(); Date d = str2Date(strD,
             * "yyyyMMdd HH:mm:ss"); return fm.format(d); } catch (Exception e)
             * { throw new Exception("获取指定格式日期出错,详细:" + e.getMessage(), e); }
             */
        }

        /*
         * 获取日期格式
         */
        public String getPattern() {
            return this.pattern;
        }
    }

    // 给出日期转换成指定格式的日期字符串
    public enum FormatDate {
        YYYY("yyyy"),
        YYYYMM("yyyyMM"),
        YYYYMMDD("yyyyMMdd"),
        YYYYMMDDHH("yyyyMMddHH"),
        YYYYMMDDHHmm("yyyyMMddHHmm"),
        YYYYMMDDHHmmss("yyyyMMddHHmmss"),
        YYYYMMDDHHmmssSSS("yyyyMMddHHmmssSSS"),

        YYYYMMDD_LINE("yyyy-MM-dd"),
        YYYYMMDDHH_LINE("yyyy-MM-dd HH"),
        YYYYMMDDHHmm_LINE("yyyy-MM-dd HH:mm"),
        YYYYMMDDHHmmss_LINE("yyyy-MM-dd HH:mm:ss"),
        YYYYMMDDHHmmssSSS_LINE("yyyy-MM-dd HH:mm:ss.SSS"),

        YYYYMMDD_DO("yyyy.MM.dd"),
        YYYYMMDDHH_DO("yyyy.MM.dd HH"),
        YYYYMMDDHHmm_DO("yyyy.MM.dd HH:mm"),
        YYYYMMDDHHmmss_DO("yyyy.MM.dd HH:mm:ss"),
        YYYYMMDDHHmmssSSS_DO("yyyy.MM.dd HH:mm:ss.SSS"),

        YYYYMMDD_ZH("yyyy年MM月dd日"),
        YYYYMMDDHH_ZH("yyyy年MM月dd日 HH时"),
        YYYYMMDDHHmm_ZH("yyyy年MM月dd日 HH时mm分"),
        YYYYMMDDHHmmss_ZH("yyyy年MM月dd日 HH时mm分ss秒"),
        YYYYMMDDHHmmssSSS_ZH("yyyy年MM月dd日 HH时mm分ss秒SSS毫秒"),
        HH("HH"),
        HHmm("HH:mm"),
        HHmmss("HH:mm:ss"),
        HHmmssSSS("HH:mm:ss:SSS");

        SimpleDateFormat fm = (SimpleDateFormat) DateFormat.getDateTimeInstance();
        private String pattern;

        private FormatDate(String pattern) {
            this.pattern = pattern;
        }

        /**
         * 获取格式化之后的日期
         * 
         * @param d
         *            需要格式化的日期
         * @return 格式化之后的日期字符串
         */
        public String getDate(Date d) {
            fm.applyPattern(this.pattern);
            return fm.format(d);
        }

        /**
         * 获取日期格式
         * 
         * @return 日期格式
         */
        public String getPattern() {
            return this.pattern;
        }

        /**
         * 计算指定天数后的日期
         * 
         * @param date
         *            日期
         * @param intBetween
         *            天数
         * @return 返回指定天数后的日期
         */
        public String getDateBetween2String(Date date, int intBetween) {
            Date dateOld = getDateBetween(date, intBetween);
            return getDate(dateOld);
        }
    }

    /**
     * 日期字符串 转换成日期类型 yyyy-MM-dd 格式的日期字符串可以不用给format
     * 
     * Date d = str2Date("2010-12-27 16:49",'yyyy-MM-dd HH:mm');
     * 
     * Date d = str2Date("2010-12-27",'yyyy-MM-dd'); or Date d =
     * str2Date("2010-12-27",'');
     * 
     * 
     * @param date
     *            要转换的日期格式的字符串
     * @param format
     *            格式串
     * @return Date
     * @throws ParseException 
     */
    public static Date str2Date(String date, String format) throws ParseException {

        if (Util.isEmpty(date)) {
            return null;
        }

        if (Util.isEmpty(format)) {
            format = "yyyy-MM-dd";
        }

        Date d = null;
        

            SimpleDateFormat sf = new SimpleDateFormat(format);
            d = sf.parse(date);

        
        return d;
    }

    /**
     * 日期转换成指定格式的字符串
     * 
     * @param date
     *            日期
     * @param format
     *            格式
     * @return 转换日期字符串
     */
    public static String date2Str(Date date, String format) {
        if (date == null) {
            return null;
        }

        if (Util.isEmpty(format)) {
            format = "yyyy-MM-dd";
        }

        String d = null;
        SimpleDateFormat sf = new SimpleDateFormat(format);
        d = sf.format(date);

        return d;
    }

    /**
     * 返回两个年月之间间隔的月数 alterMonth != null alterMonth != null
     * 
     * @param dealMonth
     *            YYYYMM
     * @param alterMonth
     *            YYYYMM
     * @return 间隔的月数
     */
    public static int calBetweenTwoMonth(String dealMonth, String alterMonth) {
        int length = 0;
        if (dealMonth.length() != 6 || alterMonth.length() != 6) {
            //LOGGER.debug("\u6BD4\u8F83\u5E74\u6708\u5B57\u7B26\u4E32\u7684\u957F\u5EA6\u4E0D\u6B63\u786E");
            length = -1;
        } else {
            int dealInt = Integer.parseInt(dealMonth);
            int alterInt = Integer.parseInt(alterMonth);
            if (dealInt < alterInt) {
                //LOGGER.debug("\u7B2C\u4E00\u4E2A\u5E74\u6708\u53D8\u91CF\u5E94\u5927\u4E8E\u6216\u7B49\u4E8E\u7B2C\u4E8C\u4E2A\u5E74\u6708\u53D8\u91CF");
                length = -2;
            } else {
                int dealYearInt = Integer.parseInt(dealMonth.substring(0, 4));
                int dealMonthInt = Integer.parseInt(dealMonth.substring(4, 6));
                int alterYearInt = Integer.parseInt(alterMonth.substring(0, 4));
                int alterMonthInt = Integer
                        .parseInt(alterMonth.substring(4, 6));
                length = (dealYearInt - alterYearInt) * 12
                        + (dealMonthInt - alterMonthInt);
            }
        }
        return length;
    }

    /**
     * 计算日期间隔的天数 beginDate != null
     * 
     * @param beginDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return 间隔的天数
     */
    public static int daysBetweenDates(Date beginDate, Date endDate) {
        int days = 0;
        Calendar calo = Calendar.getInstance();
        Calendar caln = Calendar.getInstance();
        calo.setTime(beginDate);
        caln.setTime(endDate);
        int oday = calo.get(6);
        int nyear = caln.get(1);
        for (int oyear = calo.get(1); nyear > oyear;) {
            calo.set(2, 11);
            calo.set(5, 31);
            days += calo.get(6);
            oyear++;
            calo.set(1, oyear);
        }

        int nday = caln.get(6);
        days = (days + nday) - oday;
        return days;
    }

    /**
     * 计算间隔天数后的日期 date != null
     * 
     * @param date
     *            日期
     * @param intBetween
     *            天数
     * @return 返回隔天数后的日期
     */
    public static Date getDateBetween(Date date, int intBetween) {
        Calendar calo = Calendar.getInstance();
        calo.setTime(date);
        calo.add(Calendar.DATE, intBetween);
        return calo.getTime();
    }

    /**
     * 比较日期1是否大于等于日期2
     * 
     * @param s1
     *            日期1 YYYYMMDD
     * @param s2
     *            日期2 YYYYMMDD
     * @return boolean s1 > s2 返回true
     */
    public static boolean yearMonthGreatEq(String s1, String s2) {

        if (Long.parseLong(s1) > Long.parseLong(s2)) {
            return true;
        }
        return false;
    }

    /**
     * 增加或减少月
     * 
     * @param strDate
     *            初始年月 YYYYMM
     * @param intDiff
     *            增加或减少的数量
     * @return String
     */
    public static String addYearMonth(String strDate, int intDiff) {
        try {
            if (Util.isEmpty(strDate)) {
                return strDate;
            }

            if (intDiff == 0) {
                return strDate;
            }

            if (strDate.length() != 6 && strDate.length() != 8) {
                return "";
            }

            int year = (new Integer(strDate.substring(0, 4))).intValue();
            int month = (new Integer(strDate.substring(4, 6))).intValue();

            String strDay = "";
            if (strDate.length() > 6) {
                strDay = strDate.substring(6, strDate.length());
            }

            if (intDiff > 0 || month > Math.abs(intDiff)) {
                month += intDiff;
                if (month > 12) {
                    year += month / 12;
                    month %= 12;
                    if (month == 0) {
                        month = 12;
                    }
                }
            } else {
                int n = Math.abs((month + intDiff) / 12) + 1;
                month = Math.abs(month + 12 + intDiff % 12) % 12;
                year -= n;
                if (month == 0) {
                    month = 12;
                }
            }

            if (month <= 12 && month >= 10)
                return year + (new Integer(month)).toString() + strDay;

            return year + "0" + (new Integer(month)).toString() + strDay;

        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 计算两个年月之间的相差月数
     * 
     * @param strDateBegin
     *            YYYYMM
     * @param strDateEnd
     *            YYYYMM
     * @return 相差月数 int
     */
    public static int getMonthBetween(String strDateBegin, String strDateEnd) {
        try {
            int strOut;
            if (strDateBegin.equals("") || strDateEnd.equals("")
                    || strDateBegin.length() != 6 || strDateEnd.length() != 6) {
                strOut = 0;
            } else {
                int intMonthBegin = Integer.parseInt(strDateBegin.substring(0,
                        4))
                        * 12
                        + Integer.parseInt(strDateBegin.substring(4, 6));
                int intMonthEnd = Integer.parseInt(strDateEnd.substring(0, 4))
                        * 12 + Integer.parseInt(strDateEnd.substring(4, 6));
                strOut = intMonthBegin - intMonthEnd;
            }
            return strOut;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 计算指定天数后的日期
     * 
     * @param date
     * @param intBetween
     *            天数
     * @param strFromat
     *            日期格式
     * @return 指定天数后的日期
     */
    public static String getDateBetween2String(Date date, int intBetween,
            String strFromat) {
        Date dateOld = getDateBetween(date, intBetween);
        return date2Str(dateOld, strFromat);
    }

    /**
     * 将“yyyymmdd”格式的日期转化成“yyyy-mm-dd”格式
     * 
     * @param strDate
     *            日期
     * @return 转化日期
     */
    public static String getStrHaveAcross(String strDate) {
        try {
            return strDate.substring(0, 4) + "-" + strDate.substring(4, 6)
                    + "-" + strDate.substring(6, 8);
        } catch (Exception e) {
            return strDate;
        }
    }

    /**
     * 计算当前日期的下个月的第一天
     * 
     * @return 返回当前日期的下个月的第一天
     */
    public static String getFirstDayOfNextMonth() {
        String strToday = CurDate.YYYYMM.getDate();
        return addYearMonth(strToday, 1) + "01";
    }

    /**
     * 计算当前日期的第一天
     * 
     * @return 返回当前日期的第一天
     */
    public static Date getFirstDayOfMonth() {
        Calendar cDate = Calendar.getInstance();
        cDate.set(Calendar.DAY_OF_MONTH, 1);
        return cDate.getTime();
    }

    /**
     * 获取指定日期最后一天
     * 
     * @param d
     *            指定日期
     * @return Date 指定日期最后一天
     */
    public static Date getMaxDayOfMonth(Date d) {
        Calendar cDate = Calendar.getInstance();
        cDate.setTime(d);
        cDate.set(Calendar.DAY_OF_MONTH, cDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cDate.getTime();
    }

    /**
     * 获取指定日期最后一天
     * 
     * @param d
     *            指定日期 yyyy-MM-dd
     * @return Date 指定日期最后一天
     * @throws ParseException 
     */
    public static Date getMaxDayOfMonth(String d) throws ParseException {
        Calendar cDate = Calendar.getInstance();
        cDate.setTime(str2Date(d, "yyyy-MM-dd"));
        cDate.set(Calendar.DAY_OF_MONTH, cDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cDate.getTime();
    }

}
