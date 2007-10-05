/**
 * Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import java.sql.Timestamp;
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
	private static final long MillisPerDay = 86400000;

	/* Implementation */
	public static Date today()
	{
		return dateOnly(new Date());
	}

	public static Date now()
	{
		return new Date();
	}

	public static Date dateOnly(Date pDate)
	{
		if(pDate == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(pDate);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);

		cal.clear();
		cal.set(year, month, day);

		return cal.getTime();
	}

	public static Date timeOnly(Date pDate)
	{
		if(pDate == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(pDate);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);

		cal.clear();
		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);

		return cal.getTime();
	}

	public static Date combineDateTime(Date pDate, Date pTime)
	{
		if((pDate == null) && (pTime == null))
			return null;
		if(pDate == null)
			return pTime;
		if(pTime == null)
			return pDate;

		Calendar cal = Calendar.getInstance();
		cal.setTime(pDate);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);

		cal.clear();
		cal.setTime(pTime);
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

	public static String formatDate(Date pDate, String pFormat, boolean replaceAMPM)
	{
		if(pDate == null)
			return null;
		if(!StrUtil.hasLen(pFormat))
			pFormat = DateFormat;
		String date = (new SimpleDateFormat(pFormat)).format(pDate);
		if(!replaceAMPM)
			return date;
		return date.replaceFirst("PM", "p").replaceFirst("AM", "a");
	}

	public static String formatDate(Date pDate, String pFormat)
	{
		return formatDate(pDate, pFormat, false);
	}

	public static String formatDate(Date pDate)
	{
		return formatDate(pDate, null, false);
	}

	/**
	 * Drops the time component from date, using default time zone, returning SQL date
	 * @param date
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
	 * @param date
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
	 * @param date
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

		try { date = (new SimpleDateFormat(RFC2822DateFormat)).parse(dateStr); } catch(Exception e) {}

		if(date == null)
			try { date = (new SimpleDateFormat(RFC2822AltDateFormat)).parse(dateStr); } catch(Exception e) {}

		return date;
	}

	public static double daysDiff(Date pFrom, Date pTo)
	{
		return (pTo.getTime() - pFrom.getTime()) / (double)MillisPerDay;
	}
}
