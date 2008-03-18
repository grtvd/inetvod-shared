/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.rqdata;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Readable;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.ProviderID;

public class MemberProvider implements Readable, Writeable
{
	/* Constants */
	public static Constructor<MemberProvider> CtorDataReader = DataReader.getCtor(MemberProvider.class);

	/* Fields */
	protected ProviderID fProviderID;

	/* Getters and Setters */
	public ProviderID getProviderID() { return fProviderID; }

	/* Construction */
	public MemberProvider(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fProviderID = reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
	}
}
