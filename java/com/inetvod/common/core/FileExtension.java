/**
 * Copyright � 2006-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import java.util.HashMap;

public class FileExtension
{
	/* Constants */
	public static final int MaxLength = 32;

	public static final FileExtension log = new FileExtension(".log");
	public static final FileExtension txt = new FileExtension(".txt");
	public static final FileExtension xml = new FileExtension(".xml");
	public static final FileExtension raw = new FileExtension(".raw");
	public static final FileExtension avi = new FileExtension(".avi");
	public static final FileExtension wmv = new FileExtension(".wmv");
	public static final FileExtension rm = new FileExtension(".rm");
	public static final FileExtension mov = new FileExtension(".mov");
	public static final FileExtension mp4 = new FileExtension(".mp4");

	/* Fields */
	private static HashMap<String, FileExtension> fAllValues;

	private final String fValue;

	/* Getters and Setters */
	public String toString() { return fValue; }

	/* Construction */
	private FileExtension(String name)
	{
		if(fAllValues == null)
			fAllValues = new HashMap<String, FileExtension>();
		fValue = name;
		fAllValues.put(name, this);
	}

	public static FileExtension convertFromString(String value)
	{
		if((value == null) || (value.length() == 0))
			return null;

		FileExtension item = fAllValues.get(value);
		if(item != null)
			return item;

		//throw new IllegalArgumentException("bad value(" + value + ")");
		Logger.logWarn(FileExtension.class, "convertFromString", String.format("unknown value(%s)", value));
		return new FileExtension(value);
	}

	public static String convertToString(FileExtension value)
	{
		if(value == null)
			return null;
		return value.toString();
	}
}
