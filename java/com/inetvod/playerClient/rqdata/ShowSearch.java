/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.rqdata;

import java.lang.reflect.Constructor;
import java.util.Date;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DateUtil;
import com.inetvod.common.core.Readable;
import com.inetvod.common.core.StrUtil;
import com.inetvod.common.data.ShowID;

public class ShowSearch implements Readable
{
	/* Constants */
	public static Constructor<ShowSearch> CtorDataReader = DataReader.getCtor(ShowSearch.class);

	/* Fields */
	protected ShowID fShowID;
	protected String fName;
	protected String fEpisodeName;
	protected Date fReleasedOn;
	protected Short fReleasedYear;
	protected ShowProviderList fShowProviderList = new ShowProviderList();

	/* Getters and Setters */
	public ShowID getShowID() { return fShowID; }
	public String getName() { return fName; }

	public String getNameWithEpisode()
	{
		if(StrUtil.hasLen(fEpisodeName))
			return fName + " - \"" + fEpisodeName + "\"";
		else
			return fName;
	}

	public String getEpisodeName() { return fEpisodeName; }
	public Short getReleasedYear() { return fReleasedYear; }
	public ShowProviderList getShowProviderList() { return fShowProviderList; }

	/* Constuction */
	public ShowSearch(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fShowID = reader.readDataID("ShowID", ShowID.MaxLength, ShowID.CtorString);
		fName = reader.readString("Name", ShowDetail.NameMaxLength);
		fEpisodeName = reader.readString("EpisodeName", ShowDetail.EpisodeNameMaxLength);
		fReleasedOn = reader.readDate("ReleasedOn");
		fReleasedYear = reader.readShort("ReleasedYear");
		fShowProviderList = reader.readList("ShowProvider", ShowProviderList.Ctor, ShowProvider.CtorDataReader);
	}

	public String buildReleasedStr()
	{
		String date = "";

		if(fReleasedOn != null)
		{
			double totalDays = DateUtil.daysDiff(fReleasedOn, DateUtil.today());

			if(totalDays < 1.0)
				date = "Today";
			else if(totalDays <= DateUtil.DaysPerWeek)
				date = DateUtil.formatDate(fReleasedOn, DateUtil.DayOfWeekShortFormat);
			else if(totalDays <= DateUtil.DaysPerYear)
				date = DateUtil.formatDate(fReleasedOn, DateUtil.MonthDayOnlyFormat);
			else
				date = DateUtil.formatDate(fReleasedOn, DateUtil.YearOnlyFormat);
		}
		else if(fReleasedYear != null)
			date = fReleasedYear.toString();

		return date;
	}

	public String buildCostStr()
	{
		ShowProvider showProvider = fShowProviderList.get(0);
		String cost = showProvider.getShowCostList().get(0).getCostDisplay();
		if((fShowProviderList.size() > 1) || (showProvider.getShowCostList().size() > 1))
			return "*" + cost;
		return cost;
	}
}