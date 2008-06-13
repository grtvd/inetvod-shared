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
import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.data.RentedShowID;
import com.inetvod.common.data.ShowID;

public class RentedShowSearch implements Readable, Writeable
{
	/* Constants */
	public static Constructor<RentedShowSearch> CtorDataReader = DataReader.getCtor(RentedShowSearch.class);

	/* Fields */
	private RentedShowID fRentedShowID;

	private ShowID fShowID;
	private ProviderID fProviderID;
	private String fName;
	private String fEpisodeName;

	private Date fReleasedOn;
	private Short fReleasedYear;
	private String fPictureURL;

	private Date fRentedOn;
	private Date fAvailableUntil;

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

		fReleasedOn = reader.readDate("ReleasedOn");
		fReleasedYear = reader.readShort("ReleasedYear");
		fPictureURL = reader.readString("PictureURL", ShowDetail.PictureURLMaxLength);

		fRentedOn = reader.readDateTime("RentedOn");
		fAvailableUntil = reader.readDateTime("AvailableUntil");
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("RentedShowID", fRentedShowID, RentedShowID.MaxLength);

		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
		writer.writeString("Name", fName, ShowDetail.NameMaxLength);
		writer.writeString("EpisodeName", fEpisodeName, ShowDetail.EpisodeNameMaxLength);

		writer.writeDate("ReleasedOn", fReleasedOn);
		writer.writeShort("ReleasedYear", fReleasedYear);
		writer.writeString("PictureURL", fPictureURL, ShowDetail.PictureURLMaxLength);

		writer.writeDateTime("RentedOn", fRentedOn);
		writer.writeDateTime("AvailableUntil", fAvailableUntil);
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
