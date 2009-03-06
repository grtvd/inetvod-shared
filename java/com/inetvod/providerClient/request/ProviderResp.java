/**
 * Copyright © 2005-2009 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.providerClient.request;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.Readable;
import com.inetvod.providerClient.rqdata.ProviderStatusCode;

public class ProviderResp implements Readable
{
	/* Constants */
	public static final Constructor<ProviderResp> CtorDataReader = DataReader.getCtor(ProviderResp.class);

	/* Fields */
	private ProviderStatusCode fStatusCode;
	private ResponseData fResponseData;

	/* Getters and Setters */
	public ProviderStatusCode getStatusCode() { return fStatusCode; }
	public ResponseData getResponseData() { return fResponseData; }

	/* Construction */
	public ProviderResp(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fStatusCode = ProviderStatusCode.convertFromInt(reader.readInt("StatusCode"));
		fResponseData = reader.readObject("ResponseData", ResponseData.CtorDataReader);
	}
}
