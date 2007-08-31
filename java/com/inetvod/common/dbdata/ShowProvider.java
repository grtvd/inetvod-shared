/**
 * Copyright © 2004-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.dbdata;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.data.ProviderConnectionID;
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.data.ProviderShowID;
import com.inetvod.common.data.ShowAvail;
import com.inetvod.common.data.ShowCostList;
import com.inetvod.common.data.ShowFormat;
import com.inetvod.common.data.ShowID;
import com.inetvod.common.data.ShowProviderID;

public class ShowProvider extends DatabaseObject
{
	/* Constants */
	public static final int ShowFormatMimeMaxLength = 32;
	private static final int ShowCostListMaxLength = 2048;

	/* Fields */
	private ShowProviderID fShowProviderID;
	private ShowID fShowID;
	private ProviderID fProviderID;

	private ProviderConnectionID fProviderConnectionID;
	private ProviderShowID fProviderShowID;
	private String fShowURL;			// only available for 'Connection' type providers
	private String fShowFormatMime;		//TODO to become MediaFormatID
	private ShowFormat fShowFormat;
	private ShowCostList fShowCostList;

	private ShowAvail fShowAvail;

	private static DatabaseAdaptor<ShowProvider, ShowProviderList> fDatabaseAdaptor =
		new DatabaseAdaptor<ShowProvider, ShowProviderList>(ShowProvider.class, ShowProviderList.class);
	public static DatabaseAdaptor<ShowProvider, ShowProviderList> getDatabaseAdaptor() { return fDatabaseAdaptor; }

	/* Getters and Setters */
	public ShowProviderID getShowProviderID() { return fShowProviderID; }
	public ShowID getShowID() { return fShowID; }
	public ProviderID getProviderID() { return fProviderID; }

	public ProviderConnectionID getProviderConnectionID() { return fProviderConnectionID; }
	public ProviderShowID getProviderShowID() { return fProviderShowID; }

	public String getShowURL() { return fShowURL; }
	public void setShowURL(String showURL) { fShowURL = showURL; }

	public String getShowFormatMime() { return fShowFormatMime; }
	public void setShowFormatMime(String showFormatMime) { fShowFormatMime = showFormatMime; }

	public ShowFormat getShowFormat() { return fShowFormat; }
	public ShowCostList getShowCostList() { return fShowCostList; }

	public ShowAvail getShowAvail() { return fShowAvail; }
	public void setShowAvail(ShowAvail showAvail) { fShowAvail = showAvail; }

	/* Construction */
	private ShowProvider(ShowID showID, ProviderID providerID, ProviderConnectionID providerConnectionID,
		ProviderShowID providerShowID, String showFormatMime, ShowFormat showFormat)
	{
		super(true);
		fShowProviderID = ShowProviderID.newInstance();
		fShowID = showID;
		fProviderID = providerID;
		fProviderConnectionID = providerConnectionID;
		fProviderShowID = providerShowID;
		fShowFormatMime = showFormatMime;
		fShowFormat = showFormat;
		fShowCostList = new ShowCostList();
	}

	public ShowProvider(DataReader reader) throws Exception
	{
		super(reader);
		readFrom(reader);
	}

	public static ShowProvider newInstance(ShowID showID, ProviderID providerID,
		ProviderConnectionID providerConnectionID, ProviderShowID providerShowID, String showFormatMime,
		ShowFormat showFormat)
	{
		return new ShowProvider(showID, providerID, providerConnectionID, providerShowID, showFormatMime, showFormat);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fShowProviderID = reader.readDataID("ShowProviderID", ShowProviderID.MaxLength, ShowProviderID.CtorString);
		fShowID = reader.readDataID("ShowID", ShowID.MaxLength, ShowID.CtorString);
		fProviderID = reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);

		fProviderConnectionID = reader.readDataID("ProviderConnectionID", ProviderConnectionID.MaxLength,
			ProviderConnectionID.CtorString);
		fProviderShowID = reader.readDataID("ProviderShowID", ProviderShowID.MaxLength, ProviderShowID.CtorString);
		fShowURL = reader.readString("ShowURL", Show.ShowURLMaxLength);
		fShowFormatMime = reader.readString("ShowFormatMime", ShowFormatMimeMaxLength);
		fShowFormat = reader.readObject("ShowFormat", ShowFormat.CtorDataReader);
		fShowCostList = ShowCostList.newInstanceFromXmlString(reader.readString("ShowCostList", ShowCostListMaxLength));

		fShowAvail = ShowAvail.convertFromString(reader.readString("ShowAvail", ShowAvail.MaxLength));
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowProviderID", fShowProviderID, ShowProviderID.MaxLength);
		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);

		writer.writeDataID("ProviderConnectionID", fProviderConnectionID, ProviderConnectionID.MaxLength);
		writer.writeDataID("ProviderShowID", fProviderShowID, ProviderShowID.MaxLength);
		writer.writeString("ShowURL", fShowURL, Show.ShowURLMaxLength);
		writer.writeString("ShowFormatMime", fShowFormatMime, ShowFormatMimeMaxLength);
		writer.writeObject("ShowFormat", fShowFormat);
		writer.writeString("ShowCostList", ShowCostList.toXmlString(fShowCostList), ShowCostListMaxLength);

		writer.writeString("ShowAvail", ShowAvail.convertToString(fShowAvail), ShowAvail.MaxLength);
	}

	public void update() throws Exception
	{
		fDatabaseAdaptor.update(this);
	}

	public void delete() throws Exception
	{
		fDatabaseAdaptor.delete(fShowProviderID);
	}
}
