/**
 * Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.dbdata;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;

import com.inetvod.common.core.ArrayListString;
import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.StrUtil;
import com.inetvod.common.data.ManufacturerID;
import com.inetvod.common.data.PlayerID;

/**
 * TODO: Place holder object until Player DB table can be created
 */
public class Player implements com.inetvod.common.core.Readable
{
	/* Constants */
	public static Constructor<Player> CtorDataReader = DataReader.getCtor(Player.class);
	private static final int NameMaxLength = 64;
	private static final int ModelNoMaxLength = 32;	//TODO: Share with rqdata.Player
	private static final int MimeTypeMaxLength = 64;

	/* Fields */
	private PlayerID fPlayerID;
	private String fName;
	protected ManufacturerID fManufacturerID;
	protected String fModelNo;
	private HashSet<String> fMimeTypeSet;

	/* Getters & Setters */
	public PlayerID getPlayerID() { return fPlayerID; }
	public ManufacturerID getManufacturerID() { return fManufacturerID; }
	public String getModelNo() { return fModelNo; }

	/* Construction */
	public Player(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementations */
	public void readFrom(DataReader reader) throws Exception
	{
		fPlayerID = reader.readDataID("PlayerID", PlayerID.MaxLength, PlayerID.CtorString);
		fName = reader.readString("Name", NameMaxLength);
		fManufacturerID = reader.readDataID("ManufacturerID", ManufacturerID.MaxLength, ManufacturerID.CtorString);
		fModelNo = reader.readString("ModelNo", ModelNoMaxLength);

		ArrayList<String> mimeTypeList = reader.readStringList("MimeType", MimeTypeMaxLength,
			ArrayListString.Ctor, StrUtil.CtorString);

		fMimeTypeSet = new HashSet<String>();
		for(String mimeType : mimeTypeList)
			fMimeTypeSet.add(mimeType);
	}

	public boolean supportsMimeType(String mimeType)
	{
		return fMimeTypeSet.contains(mimeType);
	}
}