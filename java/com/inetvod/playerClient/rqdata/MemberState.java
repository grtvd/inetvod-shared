/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.rqdata;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.Readable;

public class MemberState implements Readable
{
	/* Constants */
	public static final Constructor<MemberState> CtorDataReader = DataReader.getCtor(MemberState.class);

	/* Fields */
	protected MemberPrefs fMemberPrefs;
	protected MemberProviderList fMemberProviderList;

	/* Getters and Setters */
	public MemberPrefs getMemberPrefs() { return fMemberPrefs; }
	public MemberProviderList getMemberProviderList() { return fMemberProviderList; }

	/* Construction */
	public MemberState(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fMemberPrefs = reader.readObject("MemberPrefs", MemberPrefs.CtorDataReader);
		fMemberProviderList = reader.readList("MemberProvider", MemberProviderList.Ctor,  MemberProvider.CtorDataReader);
	}
}
