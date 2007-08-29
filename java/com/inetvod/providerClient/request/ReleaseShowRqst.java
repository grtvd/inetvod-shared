/**
 * Copyright © 2005-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.providerClient.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.ProviderShowID;
import com.inetvod.common.data.ShowFormat;

public class ReleaseShowRqst implements Writeable
{
	/* Fields */
	private ProviderShowID fShowID;
	private ShowFormat fShowFormat;

	/* Construction */
	private ReleaseShowRqst(ProviderShowID showID, ShowFormat showFormat)
	{
		fShowID = showID;
		fShowFormat = showFormat;
	}

	public static ReleaseShowRqst newInstance(ProviderShowID showID, ShowFormat showFormat)
	{
		return new ReleaseShowRqst(showID, showFormat);
	}

	/* Implementation */
	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowID", fShowID, ProviderShowID.MaxLength);
		writer.writeObject("ShowFormat", fShowFormat);
	}
}
