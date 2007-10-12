/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.RentedShowID;

public class RentedShowRqst implements Writeable
{
	/* Fields */
	private RentedShowID fRentedShowID;

	/* Getters and Setters */
	public void setRentedShowID(RentedShowID rentedShowID) { fRentedShowID = rentedShowID; }

	/* Implementation */
	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("RentedShowID", fRentedShowID, RentedShowID.MaxLength);
	}
}
