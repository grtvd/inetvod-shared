/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.dbdata;

import java.util.Date;

import com.inetvod.common.core.DataExists;
import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.data.ManufacturerID;
import com.inetvod.common.data.MemberID;
import com.inetvod.common.data.ProviderConnectionID;
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.data.ProviderShowID;
import com.inetvod.common.data.RatingID;
import com.inetvod.common.data.RentedShowHistoryID;
import com.inetvod.common.data.RentedShowID;
import com.inetvod.common.data.ShowCost;
import com.inetvod.common.data.ShowFormat;
import com.inetvod.common.data.ShowID;
import com.inetvod.common.data.ShowProviderID;

public class RentedShowHistory extends DatabaseObject
{
	/* Constants */
	private static final int PlayerIPAddressMaxLength = 16;

	/* Fields */
	private RentedShowHistoryID fRentedShowHistoryID;
	private MemberID fMemberID;
	private ShowID fShowID;
	private ProviderID fProviderID;
	private ShowProviderID fShowProviderID;
	private RentedShowID fRentedShowID;

	private String fName;
	private String fEpisodeName;
	private String fEpisodeNumber;
	private Date fReleasedOn;
	private Short fReleasedYear;
	private Short fRunningMins;
	private RatingID fRatingID;
	private Boolean fIsAdult;

	private ProviderConnectionID fProviderConnectionID;
	private ProviderShowID fProviderShowID;
	private String fShowURL;
	private String fShowFormatMime;
	private ShowFormat fShowFormat;

	private ShowCost fShowCost;
	private Date fRentedOn;
	private Date fAvailableUntil;

	private ManufacturerID fManufacturerID;
	private String fModelNo;
	private String fSerialNo;
	private String fVersion;
	private String fPlayerIPAddress;

	private static DatabaseAdaptor<RentedShowHistory, RentedShowHistoryList> fDatabaseAdaptor =
		new DatabaseAdaptor<RentedShowHistory, RentedShowHistoryList>(RentedShowHistory.class, RentedShowHistoryList.class);
	public static DatabaseAdaptor<RentedShowHistory, RentedShowHistoryList> getDatabaseAdaptor() { return fDatabaseAdaptor; }

	/* Getters and Setters */
//	public RentedShowHistoryID getRentedShowHistoryID() { return fRentedShowHistoryID; }

//	public MemberID getMemberID() { return fMemberID; }
//	public ShowID getShowID() { return fShowID; }
//	public ProviderID getProviderID() { return fProviderID; }
//	public ShowProviderID getShowProviderID() { return fShowProviderID; }
//	public RentedShowID getRentedShowID() { return fRentedShowID; }

//	public String getName() { return fName; }
//	public String getEpisodeName() { return fEpisodeName; }
//	public String getEpisodeNumber() { return fEpisodeNumber; }
//	public Date getReleasedOn() { return fReleasedOn; }
//	public Short getReleasedYear() { return fReleasedYear; }
//	public Short getRunningMins() { return fRunningMins; }
//	public RatingID getRatingID() { return fRatingID; }
//	public Boolean getIsAdult() { return fIsAdult; }

//	public ProviderConnectionID getProviderConnectionID() { return fProviderConnectionID; }
//	public ProviderShowID getProviderShowID() { return fProviderShowID; }
//	public String getShowURL() { return fShowURL; }
//	public String getShowFormatMime() { return fShowFormatMime; }
//	public ShowFormat getShowFormat() { return fShowFormat; }

//	public ShowCost getShowCost() { return fShowCost; }
//	public Date getRentedOn() { return fRentedOn; }
//	public Date getAvailableUntil() { return fAvailableUntil; }

//	public ManufacturerID getManufacturerID() { return fManufacturerID; }
//	public String getModelNo() { return fModelNo; }
//	public String getSerialNo() { return fSerialNo; }
//	public String getVersion() { return fVersion; }
//	public String fPlayerIPAddress;

	/* Construction */
	public RentedShowHistory(Member member, Show show, ShowProvider showProvider, RentedShow rentedShow, Player player,
		String playerSerialNo, String playerVersion, String playerIPAddress)
	{
		super(true);
		fRentedShowHistoryID = RentedShowHistoryID.newInstance();
		fMemberID = member.getMemberID();
		fShowID = show.getShowID();
		fProviderID = showProvider.getProviderID();
		fShowProviderID = showProvider.getShowProviderID();
		fRentedShowID = rentedShow.getRentedShowID();

		fName = show.getName();
		fEpisodeName = show.getEpisodeName();
		fEpisodeNumber = show.getEpisodeNumber();
		fReleasedOn = show.getReleasedOn();
		fReleasedYear = show.getReleasedYear();
		fRunningMins = show.getRunningMins();
		fRatingID = show.getRatingID();
		fIsAdult = show.getIsAdult();

		fProviderConnectionID = showProvider.getProviderConnectionID();
		fProviderShowID = showProvider.getProviderShowID();
		fShowURL = showProvider.getShowURL();
		fShowFormatMime = showProvider.getShowFormatMime();
		fShowFormat = showProvider.getShowFormat();

		fShowCost = rentedShow.getShowCost();
		fRentedOn = rentedShow.getRentedOn();
		fAvailableUntil = rentedShow.getAvailableUntil();

		fManufacturerID = player.getManufacturerID();
		fModelNo = player.getModelNo();
		fSerialNo = playerSerialNo;
		fVersion = playerVersion;
		fPlayerIPAddress = playerIPAddress;
	}

	public RentedShowHistory(DataReader reader) throws Exception
	{
		super(reader);
		readFrom(reader);
	}

	public static RentedShowHistory newInstance(Member member, Show show, ShowProvider showProvider,
		RentedShow rentedShow, Player player, String playerSerialNo, String playerVersion, String playerIPAddress)
	{
		return new RentedShowHistory(member, show, showProvider, rentedShow, player, playerSerialNo, playerVersion,
			playerIPAddress);
	}

	protected static RentedShowHistory load(RentedShowHistoryID rentedShowHistoryID, DataExists exists) throws Exception
	{
		return fDatabaseAdaptor.selectByKey(rentedShowHistoryID, exists);
	}

	public static RentedShowHistory get(RentedShowHistoryID rentedShowHistoryID) throws Exception
	{
		return load(rentedShowHistoryID, DataExists.MustExist);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fRentedShowHistoryID = reader.readDataID("RentedShowHistoryID", RentedShowHistoryID.MaxLength, RentedShowHistoryID.CtorString);
		fMemberID = reader.readDataID("MemberID", MemberID.MaxLength, MemberID.CtorString);
		fShowID = reader.readDataID("ShowID", ShowID.MaxLength, ShowID.CtorString);
		fProviderID = reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
		fShowProviderID = reader.readDataID("ShowProviderID", ShowProviderID.MaxLength, ShowProviderID.CtorString);
		fRentedShowID = reader.readDataID("RentedShowID", RentedShowID.MaxLength, RentedShowID.CtorString);

		fName = reader.readString("Name", Show.NameMaxLength);
		fEpisodeName = reader.readString("EpisodeName", Show.EpisodeNameMaxLength);
		fEpisodeNumber = reader.readString("EpisodeNumber", Show.EpisodeNumberMaxLength);
		fReleasedOn = reader.readDate("ReleasedOn");
		fReleasedYear = reader.readShort("ReleasedYear");
		fRunningMins = reader.readShort("RunningMins");
		fRatingID = reader.readDataID("RatingID", RatingID.MaxLength, RatingID.CtorString);
		fIsAdult = reader.readBoolean("IsAdult");

		fProviderConnectionID = reader.readDataID("ProviderConnectionID", ProviderConnectionID.MaxLength,
			ProviderConnectionID.CtorString);
		fProviderShowID = reader.readDataID("ProviderShowID", ProviderShowID.MaxLength, ProviderShowID.CtorString);
		fShowURL = reader.readString("ShowURL", Show.ShowURLMaxLength);
		fShowFormatMime = reader.readString("ShowFormatMime", ShowProvider.ShowFormatMimeMaxLength);
		fShowFormat = reader.readObject("ShowFormat", ShowFormat.CtorDataReader);

		fShowCost = reader.readObject("ShowCost", ShowCost.CtorDataReader);
		fRentedOn = reader.readDateTime("RentedOn");
		fAvailableUntil = reader.readDateTime("AvailableUntil");

		fManufacturerID = reader.readDataID("ManufacturerID", ManufacturerID.MaxLength, ManufacturerID.CtorString);
		fModelNo = reader.readString("ModelNo", Player.ModelNoMaxLength);
		fSerialNo = reader.readString("SerialNo",  Player.SerialNoMaxLength);
		fVersion = reader.readString("Version", Player.VersionMaxLength);
		fPlayerIPAddress = reader.readString("PlayerIPAddress", PlayerIPAddressMaxLength);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("RentedShowHistoryID", fRentedShowHistoryID, RentedShowHistoryID.MaxLength);
		writer.writeDataID("MemberID", fMemberID, MemberID.MaxLength);
		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
		writer.writeDataID("ShowProviderID", fShowProviderID, ShowProviderID.MaxLength);
		writer.writeDataID("RentedShowID", fRentedShowID, RentedShowID.MaxLength);

		writer.writeString("Name", fName, Show.NameMaxLength);
		writer.writeString("EpisodeName", fEpisodeName, Show.EpisodeNameMaxLength);
		writer.writeString("EpisodeNumber", fEpisodeNumber, Show.EpisodeNumberMaxLength);
		writer.writeDate("ReleasedOn", fReleasedOn);
		writer.writeShort("ReleasedYear", fReleasedYear);
		writer.writeShort("RunningMins", fRunningMins);
		writer.writeDataID("RatingID", fRatingID, RatingID.MaxLength);
		writer.writeBoolean("IsAdult", fIsAdult);

		writer.writeDataID("ProviderConnectionID", fProviderConnectionID, ProviderConnectionID.MaxLength);
		writer.writeDataID("ProviderShowID", fProviderShowID, ProviderShowID.MaxLength);
		writer.writeString("ShowURL", fShowURL, Show.ShowURLMaxLength);
		writer.writeString("ShowFormatMime", fShowFormatMime, ShowProvider.ShowFormatMimeMaxLength);
		writer.writeObject("ShowFormat", fShowFormat);

		writer.writeObject("ShowCost", fShowCost);
		writer.writeDateTime("RentedOn", fRentedOn);
		writer.writeDateTime("AvailableUntil", fAvailableUntil);

		writer.writeDataID("ManufacturerID", fManufacturerID, ManufacturerID.MaxLength);
		writer.writeString("ModelNo", fModelNo, Player.ModelNoMaxLength);
		writer.writeString("SerialNo", fSerialNo, Player.SerialNoMaxLength);
		writer.writeString("Version", fVersion, Player.VersionMaxLength);
		writer.writeString("PlayerIPAddress", fPlayerIPAddress, PlayerIPAddressMaxLength);
	}

	public void update() throws Exception
	{
		fDatabaseAdaptor.update(this);
	}

	public void delete() throws Exception
	{
		fDatabaseAdaptor.delete(fRentedShowHistoryID);
	}

	static public void delete(RentedShowHistoryID rentedShowHistoryID) throws Exception
	{
		fDatabaseAdaptor.delete(rentedShowHistoryID);
	}
}
