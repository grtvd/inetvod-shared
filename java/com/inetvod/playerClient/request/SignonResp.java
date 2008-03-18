/**
 * Copyright © 2006-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.request;

import java.util.Date;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.Readable;
import com.inetvod.playerClient.rqdata.MemberState;

public class SignonResp implements Readable
{
	/* Constants */
	public static final int SessionDataMaxLength = Short.MAX_VALUE;

	/* Fields */
	private String fSessionData;
	private Date fSessionExpires;
	private MemberState fMemberState;

	/* Getters and Setters */
	public String getSessionData() { return fSessionData; }
	public Date getSessionExpires() { return fSessionExpires; }
	public MemberState getMemberState() { return fMemberState; }

	/* Contruction */
	public SignonResp(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fSessionData = reader.readString("SessionData", SessionDataMaxLength);
		fSessionExpires = reader.readDateTime("SessionExpires");
		fMemberState = reader.readObject("MemberState", MemberState.CtorDataReader);
	}
}
