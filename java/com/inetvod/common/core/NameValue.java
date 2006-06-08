/**
 * Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import java.lang.reflect.Constructor;

public class NameValue implements Readable
{
	/* Constants */
	public static final Constructor<NameValue> CtorDataReader = DataReader.getCtor(NameValue.class);
	private static final int NameMaxLength = 64;

	/* Fields */
	private String fName;
	private String fValue;

	/* Getters and Setters */
	public String getName() { return fName; }
	public String getValue() { return fValue; }

	/* Construction */
	private NameValue(String name, String value)
	{
		fName = name;
		fValue = value;
	}

	public NameValue(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public static NameValue newInstance(String name, String value)
	{
		if(!StrUtil.hasLen(name))
			throw new IllegalArgumentException("Name must be specified");

		return new NameValue(name, value);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fName = reader.readString("name", NameMaxLength);
		fValue = reader.readString("value", Integer.MAX_VALUE);
	}
}
