/**
 * Copyright © 2007-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.rqdata;

import java.lang.reflect.Constructor;
import java.util.Date;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.StrUtil;
import com.inetvod.common.data.CategoryID;
import com.inetvod.common.data.CategoryIDList;
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.data.RatingID;
import com.inetvod.common.data.RentedShowID;
import com.inetvod.common.data.ShowCost;
import com.inetvod.common.data.ShowID;


public class RentedShow implements com.inetvod.common.core.Readable
{
	/* Constants */
	public static Constructor<RentedShow> CtorDataReader = DataReader.getCtor(RentedShow.class);

	/* Fields */
	private RentedShowID fRentedShowID;

	private ShowID fShowID;
	private ProviderID fProviderID;
	private String fName;
	private String fEpisodeName;
	private String fEpisodeNumber;
	private Date fReleasedOn;
	private Short fReleasedYear;
	private String fDescription;
	private Short fRunningMins;
	private String fPictureURL;

	private CategoryIDList fCategoryIDList;
	private RatingID fRatingID;
	private Boolean fIsAdult;

	private ShowCost fShowCost;
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
	public String getEpisodeNumber() { return fEpisodeNumber; }
	public Date getReleasedOn() { return fReleasedOn; }
	public Short getReleasedYear() { return fReleasedYear; }
	public String getDescription() { return fDescription; }
	public Short getRunningMins() { return fRunningMins; }
	public String getPictureURL() { return fPictureURL; }

	public CategoryIDList getCategoryIDList() { return fCategoryIDList; }
	public RatingID getRatingID() { return fRatingID; }
	public Boolean getIsAdult() { return fIsAdult; }

	public ShowCost getShowCost() { return fShowCost; }
	public Date getRentedOn() { return fRentedOn; }
	public Date getAvailableUntil() { return fAvailableUntil; }

	public RentedShow(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fRentedShowID = reader.readDataID("RentedShowID", RentedShowID.MaxLength, RentedShowID.CtorString);

		fShowID = reader.readDataID("ShowID", ShowID.MaxLength, ShowID.CtorString);
		fProviderID = reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
		fName = reader.readString("Name", ShowDetail.NameMaxLength);
		fEpisodeName = reader.readString("EpisodeName", ShowDetail.EpisodeNameMaxLength);
		fEpisodeNumber = reader.readString("EpisodeNumber", ShowDetail.EpisodeNumberMaxLength);

		fReleasedOn = reader.readDateTime("ReleasedOn");
		fReleasedYear = reader.readShort("ReleasedYear");
		fDescription = reader.readString("Description", ShowDetail.DescriptionMaxLength);
		fRunningMins = reader.readShort("RunningMins");
		fPictureURL = reader.readString("PictureURL", ShowDetail.PictureURLMaxLength);

		fCategoryIDList = reader.readStringList("CategoryID", CategoryID.MaxLength, CategoryIDList.Ctor, CategoryID.CtorString);
		fRatingID = reader.readDataID("RatingID", RatingID.MaxLength, RatingID.CtorString);
		fIsAdult = reader.readBoolean("IsAdult");

		fShowCost = reader.readObject("ShowCost", ShowCost.CtorDataReader);
		fRentedOn = reader.readDateTime("RentedOn");
		fAvailableUntil = reader.readDateTime("AvailableUntil");
	}
}
