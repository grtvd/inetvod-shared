/**
 * Copyright © 2006-2007 iNetVOD, Inc. All Rights Reserved.
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
import com.inetvod.common.data.MediaEncoding;
import com.inetvod.common.data.PlayerID;
import com.inetvod.common.data.ShowFormat;
import com.inetvod.common.data.ShowFormatList;

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
	private ManufacturerID fManufacturerID;
	private String fModelNo;
	private ShowFormatList fShowFormatList;
	private ArrayList<String> fMimeTypeList;	//ordered list

	private HashSet<MediaEncoding> fMediaEncodingSet;	//TODO for now, only comparing MedidEncoding
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
		fShowFormatList = reader.readList("ShowFormat", ShowFormatList.Ctor, ShowFormat.CtorDataReader);
		fMimeTypeList = reader.readStringList("MimeType", MimeTypeMaxLength, ArrayListString.Ctor, StrUtil.CtorString);

		fMediaEncodingSet = new HashSet<MediaEncoding>(fShowFormatList.size());
		for(ShowFormat showFormat : fShowFormatList)
			fMediaEncodingSet.add(showFormat.getMediaEncoding());

		fMimeTypeSet = new HashSet<String>(fMimeTypeList.size());
		for(String mimeType : fMimeTypeList)
			fMimeTypeSet.add(mimeType);
	}

	public boolean supportsFormat(ShowFormat showFormat, String mimeType)
	{
		//TODO For now, just checking MediaEncoding, later need to check all parameters
		if((showFormat != null) && fMediaEncodingSet.contains(showFormat.getMediaEncoding()))
			return true;

		return StrUtil.hasLen(mimeType) && fMimeTypeSet.contains(mimeType);
	}

	/**
	 * Return 'first' item from the showFormatList parameter items based on the order of Player's ShowFormatList.
	 */
	public ShowFormat supportsFormatFirst(ShowFormatList showFormatList)
	{
		//TODO For now, just checking MediaEncoding, later need to check all parameters
		for(ShowFormat showFormat : fShowFormatList)
		{
			ShowFormatList supported = showFormatList.findByMediaEncoding(showFormat.getMediaEncoding());
			if(supported.size() > 0)
				return supported.get(0);
		}

		return null;
	}

	/**
	 * Return 'first' item from the mimeTypeList parameter items based on the order of Player's MimeTypeList.
	 */
	public String supportsMimeFirst(ArrayList<String> mimeTypeList)
	{
		//TODO For now, just checking MediaEncoding, later need to check all parameters
		for(String mimeType : fMimeTypeList)
		{
			if(mimeTypeList.contains(mimeType))
				return mimeType;
		}

		return null;
	}
}