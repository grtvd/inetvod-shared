/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.data;

import java.lang.reflect.Constructor;
import java.util.UUID;

import com.inetvod.common.core.CtorUtil;
import com.inetvod.common.core.UUStringID;

public class RentedShowHistoryID extends UUStringID
{
	public static final Constructor<RentedShowHistoryID> CtorString = CtorUtil.getCtorString(RentedShowHistoryID.class);
	public static final int MaxLength = 64;

	public RentedShowHistoryID(String value)
	{
		super(value);
	}

	public static RentedShowHistoryID newInstance()
	{
		return new RentedShowHistoryID(UUID.randomUUID().toString());
	}
}
