/**
 * Copyright © 2006-2009 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.request;


import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;

public class PlayerRqst implements Writeable
{
	/* Constants */
	private static final int VersionMaxLength = 16;
	private static final int SessionDataMaxLength = Short.MAX_VALUE;

	/* Fields */
	private String fVersion;
	private String fSessionData;
	private RequestData fRequestData;

	/* Getters and Setters */
//	public void setVersion(String version) { fVersion = version; }
	public void setRequestData(RequestData requestData) { fRequestData = requestData; }

	/* Construction */
	private PlayerRqst(String version, String sessionData)
	{
		fVersion = version;
		fSessionData = sessionData;
	}

	public static PlayerRqst newInstance(String version, String sessionData)
	{
		return new PlayerRqst(version, sessionData);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("Version", fVersion, VersionMaxLength);
		writer.writeString("SessionData", fSessionData, SessionDataMaxLength);

		writer.writeObject("RequestData", fRequestData);
	}
}
