/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.contmgr.data;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;

public class Info implements Writeable
{
	/* Fields */
	private long fFileSize;

	/* Getters and Setters */

	public void setFileSize(long fileSize)
	{
		fFileSize = fileSize;
	}

	/* Construction */

	/* Implementation */
	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeLong("file-size", fFileSize);
	}
}
