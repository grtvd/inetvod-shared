/**
 * Copyright � 2006-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.data;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.CtorUtil;
import com.inetvod.common.core.StringID;

public class ProviderConnectionType extends StringID
{
	/* Constants */
	public static final Constructor<ProviderConnectionType> CtorString = CtorUtil.getCtorString(ProviderConnectionType.class);
	public static final int MaxLength = 16;

	public static final ProviderConnectionType ProviderAPI = new ProviderConnectionType("ProviderAPI");
	public static final ProviderConnectionType Rss2 = new ProviderConnectionType("Rss2");

	/* Construction */
	public ProviderConnectionType(String value)
	{
		super(value);
	}
}
