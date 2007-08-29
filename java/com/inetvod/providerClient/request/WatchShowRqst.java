/**
 * Copyright © 2005-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.providerClient.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.ProviderShowID;
import com.inetvod.common.data.ShowFormat;

public class WatchShowRqst implements Writeable
{
	/* Constants */
	private static final int PlayerIPAddressMaxLength = 16;

	/* Fields */
	private ProviderShowID fShowID;
	private String fPlayerIPAddress;
	private ShowFormat fShowFormat;

	/* Construction */
	private WatchShowRqst(ProviderShowID showID, String playerIPAddress, ShowFormat showFormat)
	{
		fShowID = showID;
		fPlayerIPAddress = playerIPAddress;
		fShowFormat = showFormat;
	}

	public static WatchShowRqst newInstance(ProviderShowID showID, String playerIPAddress, ShowFormat showFormat)
	{
		return new WatchShowRqst(showID, playerIPAddress, showFormat);
	}

	/* Implementation */
	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowID", fShowID, ProviderShowID.MaxLength);
		writer.writeString("PlayerIPAddress", fPlayerIPAddress, PlayerIPAddressMaxLength);
		writer.writeObject("ShowFormat", fShowFormat);
	}
}
