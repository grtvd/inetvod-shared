/**
 * Copyright © 2006-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.rqdata;

import java.lang.reflect.Constructor;
import java.util.Date;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DateUtil;
import com.inetvod.common.core.Readable;
import com.inetvod.common.core.StrUtil;
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.data.RentedShowID;
import com.inetvod.common.data.ShowID;

public class RentedShowSearch implements Readable
{
	/* Constants */
	public static Constructor<RentedShowSearch> CtorDataReader = DataReader.getCtor(RentedShowSearch.class);

	/* Fields */
	protected RentedShowID fRentedShowID;

	protected ShowID fShowID;
	protected ProviderID fProviderID;
	protected String fName;
	protected String fEpisodeName;
	private String fPictureURL;

	public Date fAvailableUntil;

	/* Getters and Setters */
	public RentedShowID getRentedShowID() { return fRentedShowID; }
	public ShowID getShowID() { return fShowID; }
	public ProviderID getProviderID() { return fProviderID; }

	public String getName() { return fName; }

	public String getNameWithEpisode()
	{
		if(StrUtil.hasLen(fEpisodeName))
			return fName + " - \"" + fEpisodeName + "\"";
		else
			return fName;
	}

	public String getEpisodeName() { return fEpisodeName; }
	public String getPictureURL() { return fPictureURL; }
	public Date getAvailableUntil() { return fAvailableUntil; }

	/* Constuction */
	public RentedShowSearch(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fRentedShowID = reader.readDataID("RentedShowID", RentedShowID.MaxLength, RentedShowID.CtorString);

		fShowID = reader.readDataID("ShowID", ShowID.MaxLength, ShowID.CtorString);
		fProviderID = reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
		fName = reader.readString("Name", ShowDetail.NameMaxLength);
		fEpisodeName = reader.readString("EpisodeName", ShowDetail.EpisodeNameMaxLength);
		fPictureURL = reader.readString("PictureURL", ShowDetail.PictureURLMaxLength);

		fAvailableUntil = reader.readDateTime("AvailableUntil");
	}

	public String buildAvailableUtilStr()
	{
		String expires = "n/a";

		if(fAvailableUntil != null)
		{
			double totalDays = DateUtil.daysDiff(DateUtil.today(), fAvailableUntil);

			if(totalDays < 0.0)
				expires = "Expired";
			else if(totalDays <= 1.0)
				expires = DateUtil.formatDate(fAvailableUntil, DateUtil.HourMinuteShortFormat, true);
			else if(totalDays <= 7)
			{
				expires = DateUtil.formatDate(fAvailableUntil, DateUtil.DayOfWeekHourShortFormat, true);
			}
			else
				expires = DateUtil.formatDate(fAvailableUntil, DateUtil.MonthDayOnlyFormat);
		}

		return expires;
	}
}
