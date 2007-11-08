/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;

public class SystemDataRqst implements Writeable
{
	/* Construction */
	public static SystemDataRqst newInstance()
	{
		return new SystemDataRqst();
	}

	/* Implementation */
	public void writeTo(DataWriter writer)
	{
		// No Fields
	}
}
