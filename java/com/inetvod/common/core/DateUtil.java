/**
 * Copyright © 2006-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil
{
	/* Fields */
	public static final TimeZone GMT = TimeZone.getTimeZone("GMT");
	private static final String RFC2822DateFormat = "EEE, d MMM yyyy HH:mm:ss Z";
	private static final String RFC2822AltDateFormat = "EEE, ddMMMyyyy HH:mm:ss Z";

	public static final String DateFormat = "MM/dd/yyyy";
	public static final String DayOfWeekShortFormat = "EEE";
	public static final String DayOfWeekHourShortFormat = "EEE ha";
	public static final String HourMinuteShortFormat = "h:mma";
	public static final String MonthDayOnlyFormat = "M/d";
	public static final String YearOnlyFormat = "yyyy";

	public static final int DaysPerWeek = 7;
	public static final int DaysPerYear = 365;
	public static final long MillisPerDay = 86400000;

	/* Implementation */
	public static Date today()
	{
		return dateOnly(new Date());
	}

	public static Date now()
	{
		return new Date();
	}

	public static Date dateOnly(Date date)
	{
		if(date == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);

		cal.clear();
		cal.set(year, month, day);

		return cal.getTime();
	}

	public static Date timeOnly(Date date)
	{
		if(date == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);

		cal.clear();
		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);

		return cal.getTime();
	}

	public static Date combineDateTime(Date date, Date time)
	{
		if((date == null) && (time == null))
			return null;
		if(date == null)
			return time;
		if(time == null)
			return date;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);

		cal.clear();
		cal.setTime(time);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);

		cal.clear();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);

		return cal.getTime();
	}

	public static String formatDate(Date date, String format, boolean replaceAMPM)
	{
		if(date == null)
			return null;
		if(!StrUtil.hasLen(format))
			format = DateFormat;
		String dateStr = (new SimpleDateFormat(format)).format(date);
		if(!replaceAMPM)
			return dateStr;
		return dateStr.replaceFirst("PM", "p").replaceFirst("AM", "a");
	}

	public static String formatDate(Date date, String format)
	{
		return formatDate(date, format, false);
	}

	public static String formatDate(Date date)
	{
		return formatDate(date, null, false);
	}

	public static Date parseDate(String dateStr, String format)
	{
		if(!StrUtil.hasLen(dateStr))
			return null;

		Date date = null;
		try { date = (new SimpleDateFormat(format)).parse(dateStr); } catch(Exception ignore) {}

		return date;
	}

	/**
	 * Drops the time component from date, using default time zone, returning SQL date
	 * @return SQL date w/o time
	 */
	public static java.sql.Date convertToDBDate(Date date)
	{
		if(date == null)
			return null;

		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);

		cal.clear();
		cal.set(year, month, day);
		return new java.sql.Date(cal.getTime().getTime());
	}

	/**
	 * For storing all date/times in DB as if GMT.
	 * @return converted Timestamp as if GMT
	 */
	public static Timestamp convertToDBTimestamp(Date date)
	{
		if(date == null)
			return null;

		TimeZone timeZone = TimeZone.getDefault();
		int offsetGMT = timeZone.getOffset(date.getTime());

		Calendar cal = Calendar.getInstance(timeZone);
		cal.setTime(date);
		cal.add(Calendar.MILLISECOND, -offsetGMT);

		return new Timestamp(cal.getTime().getTime());
	}

	/**
	 * For storing all date/times in DB as if GMT.
	 * @return converted Timestamp as if GMT
	 */
	public static Date convertFromDBTimestamp(Timestamp date)
	{
		if(date == null)
			return null;

		TimeZone timeZone = TimeZone.getDefault();
		int offsetGMT = timeZone.getOffset(date.getTime());

		Calendar cal = Calendar.getInstance(timeZone);
		cal.setTime(date);
		cal.add(Calendar.MILLISECOND, offsetGMT);

		return cal.getTime();
	}

	public static Date convertFromRFC2822(String dateStr)
	{
		if(!StrUtil.hasLen(dateStr))
			return null;

		Date date = null;

		try { date = (new SimpleDateFormat(RFC2822DateFormat)).parse(dateStr); } catch(Exception ignore) {}

		if(date == null)
			try { date = (new SimpleDateFormat(RFC2822AltDateFormat)).parse(dateStr); } catch(Exception ignore) {}

		return date;
	}

	public static String convertToISO8601(Date date)
	{
		if(date == null)
			return null;

		return (new ISO8601DateTimeFormat().format(date));
	}

	public static Date convertFromISO8601(String dateStr) throws ParseException
	{
		if(!StrUtil.hasLen(dateStr))
			return null;

		return (new ISO8601DateTimeFormat().parse(dateStr));
	}

	public static double daysDiff(Date from, Date to)
	{
		return (to.getTime() - from.getTime()) / (double)MillisPerDay;
	}
}
