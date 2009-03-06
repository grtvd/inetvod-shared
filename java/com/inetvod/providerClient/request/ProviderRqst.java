/**
 * Copyright © 2005-2009 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.providerClient.request;


import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.providerClient.rqdata.Authenticate;

public class ProviderRqst implements Writeable
{
	/* Constants */
	private static final int VersionMaxLength = 16;

	/* Fields */
	private String fVersion;
	private Authenticate fAuthenticate;
	private RequestData fRequestData;

	/* Getters and Setters */
	public void setVersion(String version) { fVersion = version; }
	public void setAuthenticate(Authenticate authenticate) { fAuthenticate = authenticate; }
	public void setRequestData(RequestData requestData) { fRequestData = requestData; }

	/* Construction */
	private ProviderRqst(String version)
	{
		fVersion = version;
	}

	public static ProviderRqst newInstance(String version)
	{
		return new ProviderRqst(version);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("Version", fVersion, VersionMaxLength);
		writer.writeObject("Authenticate", fAuthenticate);
		writer.writeObject("RequestData", fRequestData);
	}
}
