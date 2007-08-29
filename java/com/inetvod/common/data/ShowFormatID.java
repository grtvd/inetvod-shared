/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.data;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.StringID;
import com.inetvod.common.core.CtorUtil;

public class ShowFormatID extends StringID
{
	public static final Constructor<ShowFormatID> CtorString = CtorUtil.getCtorString(ShowFormatID.class);
	public static final int MaxLength = 256;

	public ShowFormatID(String value)
	{
		super(value);
	}
}
