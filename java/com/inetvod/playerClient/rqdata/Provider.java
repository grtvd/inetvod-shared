/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.rqdata;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.Readable;
import com.inetvod.common.data.ProviderID;

public class Provider implements Readable
{
	/* Constants */
	public static Constructor<Provider> CtorDataReader = DataReader.getCtor(Provider.class);
	private static final int NameMaxLength = 64;

	/* Fields */
	private ProviderID fProviderID;
	private String fName;

	/* Getters and Setters */
	public ProviderID getProviderID() { return fProviderID; }
	public String getName() { return fName; }

	/* Construction */
	public Provider(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fProviderID = reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
		fName = reader.readString("Name", NameMaxLength);
	}
}
