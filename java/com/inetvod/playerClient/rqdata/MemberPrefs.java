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
import com.inetvod.common.data.IncludeAdult;

public class MemberPrefs implements Readable, Writeable
{
	/* Constants */
	public static Constructor<MemberPrefs> CtorDataReader = DataReader.getCtor(MemberPrefs.class);

	/* Fields */
	protected IncludeAdult fIncludeAdult;

	/* Getters and Setters */
	public IncludeAdult getIncludeAdult() { return fIncludeAdult; }

	/* Construction */
	public MemberPrefs(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fIncludeAdult = IncludeAdult.convertFromString(reader.readString("IncludeAdult", IncludeAdult.MaxLength));
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("IncludeAdult", IncludeAdult.convertToString(fIncludeAdult), IncludeAdult.MaxLength);
	}
}
