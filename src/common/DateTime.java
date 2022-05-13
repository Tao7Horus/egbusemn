// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.util.Calendar;
import java.text.ParsePosition;
import java.util.GregorianCalendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public final class DateTime
{
    public static void check(final String s) throws Exception {
        check(s, "yyyy-MM-dd");
    }
    
    public static void check(final String s, final String format) throws ParseException {
        if (s == null) {
            throw new NullPointerException("date string to check is null");
        }
        if (format == null) {
            throw new NullPointerException("format string to check date is null");
        }
        final SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
        Date date = null;
        try {
            date = formatter.parse(s);
        }
        catch (ParseException e) {
            throw new ParseException(String.valueOf(e.getMessage()) + " with format \"" + format + "\"", e.getErrorOffset());
        }
        if (!formatter.format(date).equals(s)) {
            throw new ParseException("Out of bound date:\"" + s + "\" with format \"" + format + "\"", 0);
        }
    }
    
    public static String getDateString() {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        return formatter.format(new Date());
    }
    
    public static int getDay() {
        return getNumberByPattern("dd");
    }
    
    public static String getFormatString(final String pattern) {
        final SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.KOREA);
        final String dateString = formatter.format(new Date());
        return dateString;
    }
    
    public static int getMonth() {
        return getNumberByPattern("MM");
    }
    
    public static int getNumberByPattern(final String pattern) {
        final SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.KOREA);
        final String dateString = formatter.format(new Date());
        return Integer.parseInt(dateString);
    }
    
    public static String getShortDateString() {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        return formatter.format(new Date());
    }
    
    public static String getShortTimeString() {
        final SimpleDateFormat formatter = new SimpleDateFormat("HHmmss", Locale.KOREA);
        return formatter.format(new Date());
    }
    
    public static String getTimeStampString() {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss:SSS", Locale.KOREA);
        return formatter.format(new Date());
    }
    
    public static String getTimeString() {
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
        return formatter.format(new Date());
    }
    
    public static int getYear() {
        return getNumberByPattern("yyyy");
    }
    
    public static String getDateString(final String format) {
        final SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
        return formatter.format(new Date());
    }
    
    public static long getDiffDays(final String curr, final String next, final String format) throws ParseException {
        final SimpleDateFormat df = new SimpleDateFormat(format, Locale.KOREA);
        return (df.parse(next).getTime() - df.parse(curr).getTime()) / 86400L / 1000L;
    }
    
    public static String formatDate(final Date date, final String dateFormat) {
        final SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.KOREA);
        return formatter.format(date);
    }
    
    public static Date getIncrementDays(final String strDate, final String dateFormat, final int days) throws Exception {
        SimpleDateFormat formatter = null;
        final Calendar calendar = new GregorianCalendar();
        final Date date = null;
        int year = 0;
        int month = 0;
        int day = 0;
        String tempStr = null;
        formatter = new SimpleDateFormat(dateFormat, Locale.KOREA);
        try {
            final ParsePosition pos = new ParsePosition(0);
            final Date tmpDate = formatter.parse(strDate, pos);
            tempStr = formatDate(tmpDate, "yyyy-MM-dd");
        }
        catch (Exception e1) {
            throw e1;
        }
        try {
            year = Integer.parseInt(tempStr.substring(0, 4));
            month = Integer.parseInt(tempStr.substring(5, 7));
            day = Integer.parseInt(tempStr.substring(8, 10));
        }
        catch (Exception e2) {
            throw e2;
        }
        calendar.set(year, month - 1, day + days);
        return calendar.getTime();
    }
    
    public static String getAddDay(final String strDate, final String fromFormat, final String toFormat, final int days) throws Exception {
        return formatDate(getIncrementDays(strDate, fromFormat, days), toFormat);
    }
    
    public static String getAddDay(final String strDate, final String fromFormat, final int days) throws Exception {
        return formatDate(getIncrementDays(strDate, fromFormat, days), fromFormat);
    }
    
    public static String changeDateTimeFormat(final String strDate, final String expectedPattern) {
        if (strDate == null || strDate.equals("") || expectedPattern == null || expectedPattern.equals("")) {
            return "";
        }
        Log.debug("strDate: " + strDate);
        final Date date = new Date(Date.parse(strDate));
        Log.debug("Date: " + date);
        final SimpleDateFormat formater = new SimpleDateFormat(expectedPattern);
        return formater.format(date);
    }
    
    public static String toVnDateTimeFormat(final String str) {
        return changeDateTimeFormat(str, "dd/MM/yyyy HH:mm");
    }
    
    public static String toVnDateFormat(final String str) {
        return changeDateTimeFormat(str, "dd/MM/yyyy");
    }
}
