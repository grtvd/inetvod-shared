/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.playerClient.rqdata.RentedShow;

public class RentedShowResp implements com.inetvod.common.core.Readable
{
	/* Fields */
	private RentedShow fRentedShow;

	/* Getters and Setters */
	public RentedShow getRentedShow() { return fRentedShow; }

	/* Construction */
	public RentedShowResp(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fRentedShow = reader.readObject("RentedShow", RentedShow.CtorDataReader);
	}
}
