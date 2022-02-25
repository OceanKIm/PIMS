package com.pims.api.utils;

import lombok.extern.log4j.Log4j2;
import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * DateUtils
 * : 종합 시간, 날짜 관련 유틸 클래스
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-02-25
**/
@Log4j2
public class DateUtils {

    /**
     * Default data format variable
     */
    private static String defaultDatePattern = null;
    private static String BUNDLE_KEY = null;

    /**
     * Date format
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_PATTERN_FORMAT = "yyyyMMdd";
    /**
     * Time format
     */
    public static final String TIME_PATTERN = "HH:mm";

    /**
     * Time format SEC
     */
    public static final String TIME_PATTERN_SEC = "HH:mm:ss";
    /**
     * Date Time format
     */
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    /**
     * Date HMS format
     */
    public static final String DATE_HMS_FORMAT = "yyyyMMddHHmmss";
    /**
     * Time stamp format
     */
    public static final String TIMESTAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * Time stamp format
     */
    public static final String TIMESTAMP_FORMAT2 = "yyyyMMddHMS";


    /**
     * @author hskim
     * @return boolean 날짜 스트링 유효성 검사(yyyyMMdd)
     */
    public static boolean checkInputDate(String checkDate) {
        try {
            SimpleDateFormat dateFormatParser = new SimpleDateFormat(DATE_PATTERN_FORMAT);
            dateFormatParser.setLenient(false);
            dateFormatParser.parse(checkDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 현재 날짜를 가져온다.
     *
     * @return String 현재 날짜(yyyy-MM-dd)
     */
    public static String getCurrentDay() {
        return getCurrentTime(DATE_PATTERN);
    }

    /**
     * 주어진 날짜 패턴의 현재 날짜를 가져온다.
     *
     * @param pattern 패턴
     * @return String pattern에 의한 현재 날짜
     */
    public static String getCurrentDay(String pattern) {
        return getCurrentTime(pattern);
    }

    /**
     * HH:mm:ss 패턴의 현재 시간을 가져온다.
     *
     * @return String 현재 시간( HH:mm:ss)
     */
    public static String getCurrentTime() {
        return getCurrentTime(TIME_PATTERN_SEC);
    }

    /**
     * 주어진 패턴의 현재시간을 가져온다.
     *
     * @param pattern 패턴
     * @return String pattern에 의한 현재 시간
     */
    public static String getCurrentTime(String pattern) {
        DateTime dt = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.print(dt);
    }

    /**
     * 년도를 포함한 현재월을 가져온다.
     *
     * @return String 현재월(yyyy-MM)
     */
    public static String getThisMonth() {
        return getCurrentTime("yyyy-MM");
    }

    /**
     * 현재 년도를 가져온다.
     *
     * @return String 현재 년도 (yyyy)
     */
    public static String getThisYear() {
        return getCurrentTime("yyyy");
    }

    /**
     * 현재시간을 가져온다.
     *
     * @return String 현재시간 (HH:mm)
     */
    public static String getCurrentHour() {
        return getCurrentTime(TIME_PATTERN);
    }

    /**
     * 입력된 날짜의 지역에 해당하는 요일을 가져온다.
     * 2014-07-10 = 목
     *
     * @param str date(yyyy-MM-dd)
     * @return String 요일 정보 (Default Locale)
     */
    public static String getDayOfWeek(String str) {
        return getDayOfWeek(str, true, LocaleContextHolder.getLocale());
    }

    /**
     * 입력된 날짜의 지역에 해당하는 요일을 가져온다.
     * 축약여부에 따라 요일정보 반환.
     *
     * @param str          date(yyyy-MM-dd)
     * @param abbreviation 축약 여부
     * @param locale       locale
     * @return String day of week
     */
    public static String getDayOfWeek(String str, Boolean abbreviation, Locale locale) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
        DateTime dt = fmt.parseDateTime(str);
        DateTime.Property dayOfWeek = dt.dayOfWeek();

        if (abbreviation)
            return dayOfWeek.getAsShortText(locale);
        else
            return dayOfWeek.getAsText(locale);
    }


    /**
     * 특정날짜의  요일의 숫자를 리턴
     * 0:일요일 ~ 6:토요일
     *
     * @param sYear  년
     * @param sMonth 월
     * @param sDay   일
     * @return int dayOfWeek
     */
    @SuppressWarnings("static-access")
    public static synchronized int dayOfWeek(String sYear, String sMonth, String sDay) {
        int iYear = Integer.parseInt(sYear);
        int iMonth = Integer.parseInt(sMonth) - 1;
        int isDay = Integer.parseInt(sDay);
        GregorianCalendar gc = new GregorianCalendar(iYear, iMonth, isDay);
        return gc.get(gc.DAY_OF_WEEK) - 1;
    }

    /**
     * 두날짜의 차이 일수를 구한다.
     *
     * @param startDate start date(yyyy-MM-dd)
     * @param endDate   end date(yyyy-MM-dd)
     * @return integer of days
     */
    public static int getDays(String startDate, String endDate) {
        return getDays(startDate, endDate, DATE_HMS_FORMAT);
    }

    /**
     * 두 날짜의 차이 일수를 구한다.
     *
     * @param startDate start date
     * @param endDate   end date
     * @param pattern   date format
     * @return integer of days
     */
    public static int getDays(String startDate, String endDate, String pattern) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        DateTime startDateTime = fmt.parseDateTime(startDate);
        DateTime endDateTime = fmt.parseDateTime(endDate);
        long startMillis = startDateTime.getMillis();
        long endMillis = endDateTime.getMillis();
        int result = (int) (startMillis / (60 * 60 * 1000 * 24));
        int result1 = (int) (endMillis / (60 * 60 * 1000 * 24));
        return result1 - result;
    }

    /**
     * 두 날짜가 같은지 비교한다.
     *
     * @param date1 첫번째 날짜
     * @param date2 두번째 날짜
     * @return 같으면 true, 다르면 false
     * otherwise.
     */
    public static boolean equals(Date date1, String date2) {
        return equals(date1, date2, DATE_PATTERN);
    }

    /**
     * 입력된 날짜 패턴에 의한 두 날짜가 같은지 비교
     *
     * @param date1       첫번째 날짜
     * @param date2       두번째 날짜
     * @param date2format 날짜 패턴
     * @return 같으면 true, 다르면 false
     * otherwise.
     */
    public static boolean equals(Date date1, String date2, String date2format) {
        Date date = string2Date(date2, date2format);
        return equals(date1, date);
    }

    /**
     * 두 날짜가 같은지 비교
     *
     * @param date1 첫번째 날짜
     * @param date2 두번째 날짜
     * @return 같으면 true, 다르면 false
     */
    public static boolean equals(Date date1, Date date2) {
        if (date1.getTime() == date2.getTime()) {
            return true;
        }
        return false;
    }

    /**
     * 두 날짜의 순서 비교
     *
     * @param date1 첫번째 날짜 (yyyy-MM-dd)
     * @param date2 두번째 날짜 (yyyy-MM-dd)
     * @return 첫번째 날짜가 두번째 날짜와 같거나 그 이후의 날짜이면 true, 첫번째 날짜가 두번째 날짜의 이전 날짜이면 false
     */
    public static boolean greaterThan(Date date1, String date2) {
        return greaterThan(date1, date2, DATE_PATTERN);
    }

    /**
     * 입력된 패턴의 두 날짜 순서 비교
     *
     * @param date1       첫번째 날짜 (yyyy-MM-dd)
     * @param date2       두번째 날짜 (yyyy-MM-dd)
     * @param date2format 날짜패턴
     * @return 첫번째 날짜가 두번째 날짜와 같거나 그 이후의 날짜이면 true, 첫번째 날짜가 두번째 날짜의 이전 날짜이면 false
     */
    public static boolean greaterThan(Date date1, String date2, String date2format) {
        Date date = string2Date(date2, date2format);
        return greaterThan(date1, date);
    }

    /**
     * 두 날짜의 순서 비교
     *
     * @param date1 첫번째 날짜 (yyyy-MM-dd)
     * @param date2 두번째 날짜 (yyyy-MM-dd)
     * @return 첫번째 날짜가 두번째 날짜와 같거나 그 이후의 날짜이면 true, 첫번째 날짜가 두번째 날짜의 이전 날짜이면 false
     */
    public static boolean greaterThan(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            return true;
        }
        return false;
    }

    /**
     * 주어진 날짜에서 주어진 기간에 해당하는 일자를 구한다.
     * 단, 해단 기간은 40일보다 작은 기간
     *
     * @param startDay     기준일자 (yyyy-MM-dd)
     * @param intervalDays 기간
     * @return 기준일자로부터 기간이후의 날짜 반환 (yyyy-MM-dd)
     */
    public static String getEndDate(String startDay, int intervalDays) {
        StringTokenizer st = new StringTokenizer(startDay, "-");
        int year = 0;
        int mon = 0;
        int day = 0;
        for (int i = 0; st.hasMoreTokens(); i++) {
            if (i == 0) {
                year = Integer.parseInt(st.nextToken());
            }
            if (i == 1) {
                String sMon = st.nextToken();
                if (sMon.startsWith("0")) {
                    sMon = sMon.substring(1);
                }

                mon = Integer.parseInt(sMon);
            }
            if (i == 2) {
                String sDay = st.nextToken();
                if (sDay.startsWith("0")) {
                    sDay = sDay.substring(1);
                }
                day = Integer.parseInt(sDay);
            }
        }
        DateTime start = new DateTime(year, mon, day, 0, 0, 0, 0);

        Period p1 = new Period(20 * 86400000);
        Period p2 = new Period((intervalDays - 20) * 86400000);

        DateTime end = start.plus(p1);
        end = end.plus(p2);
        year = end.getYear();
        mon = end.getMonthOfYear();
        day = end.getDayOfMonth();
        String xMon = "";
        String xDay = "";
        if (mon < 10) {
            xMon = "0" + (new Integer(mon)).toString();
        } else {
            xMon = (new Integer(mon)).toString();
        }
        if (day < 10) {
            xDay = "0" + (new Integer(day)).toString();
        } else {
            xDay = (new Integer(day)).toString();
        }
        String endDay = (new Integer(year)).toString() + "-" + xMon + "-" + xDay;
        return endDay;
    }

    /**
     * 주어진 날짜에서 특정일자를 더한다.
     *
     * @param str  기준이자
     * @param days 일수
     * @return String 기준일자 + 일수 yyyy-MM-dd
     */
    public static String addDays(String str, int days) {
        if (days == 0) {
            return str;
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
        DateTime dt = fmt.parseDateTime(str);
        DateTime subtracted = dt.withFieldAdded(DurationFieldType.days(), days);
        return fmt.print(subtracted);
    }

    /**
     * return the date adding months to the input date. negative month will be
     * returned by calculating the previous days of the input date.
     *
     * @param str    string of the date
     * @param months the amount of months to add, may be negative
     * @return String calculated date
     */
    public static String addMonths(String str, int months) {
        if (months == 0) {
            return str;
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
        DateTime dt = fmt.parseDateTime(str);
        DateTime subtracted = dt.withFieldAdded(DurationFieldType.months(), months);
        return fmt.print(subtracted);
    }

    /**
     * return the date adding years to the input date. negative year will be
     * returned by calculating the previous days of the input date.
     *
     * @param str   string of the date
     * @param years the amount of years to add, may be negative
     * @return String calculated date
     */
    public static String addYears(String str, int years) {
        if (years == 0) {
            return str;
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
        DateTime dt = fmt.parseDateTime(str);
        DateTime subtracted = dt.withFieldAdded(DurationFieldType.years(), years);
        return fmt.print(subtracted);
    }

    /**
     * return date calculating years, months, days to the input date
     *
     * @param str    string of the date
     * @param years  the amount of years to add, may be negative
     * @param months the amount of months to add, may be negative
     * @param days   the amount of days to add, may be negative
     * @return String calculated date
     */
    public static String addYearMonthDay(String str, int years, int months, int days) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
        DateTime dt = fmt.parseDateTime(str);

        if (years != 0)
            dt = dt.withFieldAdded(DurationFieldType.years(), years);
        if (months != 0)
            dt = dt.withFieldAdded(DurationFieldType.months(), months);
        if (days != 0)
            dt = dt.withFieldAdded(DurationFieldType.days(), days);

        return fmt.print(dt);
    }

    /**
     * get the first date of the month based on the input date.
     *
     * @param str string of the date
     * @return the new date of the first date of the month
     */
    public static String getFirstDateOfMonth(String str) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
        DateTime dt = fmt.parseDateTime(str);
        DateTime dtRet = new DateTime(dt.getYear(), dt.getMonthOfYear(), 1, 0, 0, 0, 0);
        return fmt.print(dtRet);
    }

    /**
     * get the last date of the month based on the input date.
     *
     * @param str string of the date
     * @return the new date of the last date of the month
     */
    public static String getLastDateOfMonth(String str) {
        String firstDateOfMonth = getFirstDateOfMonth(str);

        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
        DateTime dt = fmt.parseDateTime(firstDateOfMonth);
        dt = dt.plusMonths(1).minusDays(1);
        return fmt.print(dt);
    }

    /**
     * get the first day of the previous month based on the input date.
     *
     * @param str string of the date
     * @return the new date of the first date of the previous month
     */
    public static String getFirstDateOfPrevMonth(String str) {
        String firstDateOfMonth = getFirstDateOfMonth(str);

        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
        DateTime dt = fmt.parseDateTime(firstDateOfMonth);
        dt = dt.minusMonths(1);
        return fmt.print(dt);
    }

    /**
     * get the last day of the previous month based on the input date.
     *
     * @param str string of the date
     * @return the new date of the last date of the previous month
     */
    public static String getLastDateOfPrevMonth(String str) {
        String firstDateOfMonth = getFirstDateOfMonth(str);

        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
        DateTime dt = fmt.parseDateTime(firstDateOfMonth);
        dt = dt.minusDays(1);
        return fmt.print(dt);
    }

    /**
     * check whether the input date is valid.
     *
     * @param str string of the date (yyyy-MM-dd)
     * @return if valid date, return <code>true</code>.
     */
    public static boolean isDate(String str) {
        return isDate(str, DATE_PATTERN);
    }

    /**
     * check whether the input date is valid date.
     *
     * @param str     string of the date
     * @param pattern date format
     * @return return <code>true</code>if valid date and <code>false</code> if
     * not.
     */
    public static boolean isDate(String str, String pattern) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        DateTime dt = new DateTime();
        dt = fmt.parseDateTime(str);

        if (!fmt.print(dt).equals(str)) {
            return false;
        }
        return true;
    }

    /**
     * check whether the input time is valid date.
     *
     * @param str string of the time (HH:mm)
     * @param str time format
     * @return return <code>true</code>if valid time and <code>false</code> if
     * not.
     */
    public static boolean isTime(String str) {
        return isTime(str, TIME_PATTERN);
    }

    /**
     * check whether the input hour is valid time.
     *
     * @param str string of the time
     * @return return <code>true</code>if valid time and <code>false</code> if
     * not.
     */

    /**
     * check whether the input hour is valid time.
     *
     * @param str     string of the time
     * @param pattern pattern
     * @return return if valid time and
     */
    public static boolean isTime(String str, String pattern) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        DateTime dt = new DateTime();
        dt = fmt.parseDateTime(str);

        if (!fmt.print(dt).equals(str)) {
            return false;
        }
        return true;
    }

    /**
     * convert String to java.util.Date
     *
     * @param str the String Date to be converted (yyyy-MM-dd)
     * @return <code>java.util.Date</code>
     */
    public static Date string2Date(String str) {
        return string2Date(str, DATE_PATTERN);
    }

    /**
     * convert String to <code>java.util.Date</code>
     *
     * @param str     the String Date to be converted (The format equals format that input argument)
     * @param pattern format converted date format
     * @return <code>java.util.Date</code>
     */
    public static Date string2Date(String str, String pattern) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseDateTime(str).toDate();
    }

    /**
     * convert <code>Date</code> to <code>String</code>
     *
     * @param date date
     * @return result String (yyyy-MM-dd)
     */
    public static String date2String(Date date) {
        return date2String(date, DATE_PATTERN);
    }

    /**
     * convert <code>Date</code> to <code>String</code>
     *
     * @param date    date
     * @param pattern date format
     * @return result String
     */
    public static String date2String(Date date, String pattern) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.print(date.getTime());
    }


    /**
     * get the current timestamp
     *
     * @return String of current timestamp;
     */
    public static String getTimeStamp() {
        DateTime dt = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern(TIMESTAMP_FORMAT);
        return fmt.print(dt);
    }

    /**
     * Return default datePattern (yyyy-MM-dd)
     *
     * @return a string representing the date pattern on the UI
     */
    public static synchronized String getDefaultDatePattern() {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            defaultDatePattern = ResourceBundle.getBundle(BUNDLE_KEY, locale).getString("date.format");
        } catch (Exception mse) {
            defaultDatePattern = DATE_PATTERN;
        }
        return defaultDatePattern;
    }


    /**
     * convert String to <code>java.sql.Date</code> type
     *
     * @param millis  millis
     * @param pattern date format to be converted.
     * @return <code>java.sql.Date</code>
     */
    public static java.sql.Date string2SQLDate(long millis, String pattern) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return new java.sql.Date(millis);
    }

    /**
     * convert String to <code>java.sql.Date</code> type
     *
     * @return <code>java.sql.Date</code>
     */

    public static Timestamp getInsertDate() {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        log.info("====================");
        log.info(timestamp);
        log.info("====================");
        return timestamp;
    }

    /**
     * convert String to <code>java.sq.Timestamp</code>
     *
     * @param str the String Date to be converted (yyyy-MM-dd)
     * @return <code>java.sql.Timestamp</code>
     */
    public static Timestamp string2Timestamp(String str) {
        return string2Timestamp(str, DATE_PATTERN);
    }

    /**
     * convert String to <code>java.sq.Timestamp</code>
     *
     * @param str     the String Date to be converted (The format equals format that input argument)
     * @param pattern format converted date format
     * @return <code>java.sql.Timestamp</code>
     */
    public static Timestamp string2Timestamp(String str, String pattern) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return new Timestamp(fmt.parseDateTime(str).getMillis());
    }

    /**
     * convert <code>java.sq.Timestamp</code> to <code>String</code> type
     *
     * @param date the Date to be converted
     * @return a string representing the date (yyyy-MM-dd)
     */
    public static String timestamp2String(Timestamp date) {
        return timestamp2String(date, DATE_PATTERN);
    }

    /**
     * convert <code>java.sq.Timestamp</code> to <code>String</code> type
     *
     * @param date   the Date to be converted (The format equals format that input argument)
     * @param format Date format
     * @return a string representing the date
     */
    public static String timestamp2String(Timestamp date, String format) {
        if (date == null) {
            return "";
        }
        return date2String(date, format);
    }


    /**
     * get yesterday
     *
     * @return String representing yesterday (yyyy-MM-dd)
     */
    public static String getYesterday() {
        return getYesterday(DATE_PATTERN);
    }

    /**
     * get yesterday with format
     *
     * @param format Date format
     * @return String representing yesterday
     */
    public static String getYesterday(String format) {
        Calendar cal = getCalendar();
        cal.roll(Calendar.DATE, -1);
        Date date = cal.getTime();
        return date2String(date, format);
    }

    /**
     * get current calendar of korea time zone
     *
     * @return <code>java.util.Calendar</code>
     */
    private static Calendar getCalendar() {
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"), Locale.KOREA);
        calendar.setTime(new Date());
        return calendar;
    }

    /**
     * find dates between startDay and endDay
     *
     * @param startDay start day (yyyy-MM-dd)
     * @param endDay   end day (yyyy-MM-dd)
     * @return String array of dates between startDay and endDay
     */
    public static String[] getDates(String startDay, String endDay) {
        return getDates(startDay, endDay, DATE_PATTERN);
    }

    /**
     * find dates between startDay and endDay
     *
     * @param startDay start day
     * @param endDay   end day
     * @param format   date format
     * @return String array of dates between startDay and endDay
     */
    public static String[] getDates(String startDay, String endDay, String format) {
        List<String> result = new ArrayList<String>();
        result.add(startDay);

        Calendar cal = getCalendar();
        cal.setTime(string2Date(startDay, format));
        String nextDay = date2String(cal.getTime(), format);

        while (!nextDay.equals(endDay)) {
            cal.add(Calendar.DATE, 1);
            nextDay = date2String(cal.getTime(), format);
            result.add(nextDay);
        }
        return result.toArray(new String[0]);
    }

    /**
     * {@link SimpleDateFormat}의 instance를 반환한다.
     *
     * @param format 형식화 패턴
     * @return java.text.SimpleDateFormat
     */
    public static DateFormat getSimpleDateFormat(String format) {
        return new SimpleDateFormat(format);
    }

    /**
     * 특정 날짜 형식화 패턴을 기준으로 문자열로 구성된 날짜를 받아 {@link Date}객체를 생성하여 반환한다. 날짜문자열은 반드시
     * Date Formatter가 보유한 형식화 패턴과 일치하는 패턴이어야 한다.
     *
     * @param date      날짜문자열
     * @param formatter 날짜문자열을 Parsing할 Date Formatter
     * @return {@link Date}
     * @throws ParseException java.util.Date로 변환할 날짜 문자열이 Date Formatter의 형식화 패턴과 일지하지 않을 때
     *                        발생한다.
     */
    public static Date parseDate(String date, DateFormat formatter) throws ParseException {
        return formatter.parse(date);
    }

    /**
     * 지금 날짜를 return 한다. 형식은 yyyymmdd
     *
     * @return String date
     */
    public static synchronized String toDate() {
        Calendar cal = Calendar.getInstance();
        String month = "";
        String day = "";
        if ((cal.get(Calendar.MONTH) + 1) < 10) {
            month = "0" + (cal.get(Calendar.MONTH) + 1);
        } else {
            month = (cal.get(Calendar.MONTH) + 1) + "";
        }

        if (cal.get(Calendar.DATE) < 10) {
            day = "0" + cal.get(Calendar.DATE);

        } else {
            day = cal.get(Calendar.DATE) + "";
        }
        return cal.get(Calendar.YEAR) + month + day;
    }

    /**
     * 지금 날짜를 return 한다. 형식은 yyyymmddhhmmss
     *
     * @return String date
     */
    public static synchronized String toDateTime() {
        return format(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 날짜를 지정된 포맷으로 변환한다.
     * 날짜형식 정의
     * - "yyyyMM";
     * - "yyMMdd";
     * - "yyyyMMdd";
     * - "yyyyMMddHHmmss";
     * - "yyyyMMddHHmmssSSS";
     * - "HHmmss";
     * - "EE";
     * - "F";
     *
     * @param inDate date
     * @param format date format
     * @return string date format
     */
    public static String format(Date inDate, String format) {
        if (inDate == null)
            return "";

        if (format == null || format.length() <= 0) {
            format = "yyyyMMddHHmmss";
        }

        SimpleDateFormat SDF = new SimpleDateFormat(format);

        return SDF.format(inDate);
    }

    /**
     * @param format date format
     * @return String today
     */
    public static String getToday(String format) {
        String today = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Calendar currentDate = Calendar.getInstance();
        // 현재일자
        today = dateFormat.format(currentDate.getTime());

        return today;
    }

    /**
     * 넘어온 날짜 형식을 구분자를 두어서 변환 후 return 한다.
     *
     * @param strDate yyyyMMdd 형식
     * @param section 구분자
     * @return string
     */
    public static String converterDate(String strDate, String section) {
        //자리수가 8자리 이하일경우
        if (strDate.length() < 8) {
            return strDate;
        }

        //내용물에 숫자 이외의 데이터가 있을경우
        try {
            Integer.parseInt(strDate);
        } catch (Exception e) {
            return strDate;
            // TODO: handle exception
        }
        return strDate.substring(0, 4) + section + strDate.substring(4, 6) + section + strDate.substring(6);

    }

    /**
     * 현재 날짜부터 몇일 지난 날짜를 FORMAT 형식으로 return
     * 전 날짜를 구하려면 -입력
     * Kind 에는 날짜면 day, 달이면 month ,년이면 year
     *
     * @param passDate 날짜 int
     * @param kind     년 월 일
     * @param format   포멧 방식
     * @return String 날짜 포멧
     * @throws Exception throws
     */
    public static String passDate(int passDate, String kind, String format) throws Exception {
        //현재 날짜를 만들어냄
        Calendar calendar = Calendar.getInstance();

        //넘어온 날짜 만큼 이동
        if ("year".equals(kind)) {
            calendar.add(Calendar.YEAR, passDate);
        } else if ("month".equals(kind)) {
            calendar.add(Calendar.MONTH, passDate);
        } else {
            calendar.add(Calendar.DAY_OF_MONTH, passDate);
        }

        return format(calendar.getTime(), format);
    }

}