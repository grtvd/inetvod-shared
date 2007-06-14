/**
 * Copyright © 2005-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.data;

import java.util.HashMap;

public enum MediaEncoding
{
	WMV9("WMV9"),
	RV9("RV9"),
	SVQ3("SVQ3"),
	DivX5("DivX5"),
	Xvid("Xvid"),
	MP3("MP3");

	/* Constants */
	public static final int MaxLength = 32;

	/* Fields */
	private static HashMap<String, MediaEncoding> fAllValues = new HashMap<String, MediaEncoding>();

	private final String fValue;

	static
	{
		for(MediaEncoding value : values())
			fAllValues.put(value.toString(), value);
	}

	/* Getters and Setters */
	public String toString() { return fValue; }

	/* Construction */
	private MediaEncoding(String name)
	{
		fValue = name;
	}

	public static MediaEncoding convertFromString(String value)
	{
		if((value == null) || (value.length() == 0))
			return null;

		MediaEncoding mediaEncoding = fAllValues.get(value);
		if(mediaEncoding != null)
			return mediaEncoding;

		throw new IllegalArgumentException("bad value(" + value + ")");
	}

	public static String convertToString(MediaEncoding value)
	{
		if(value == null)
			return null;
		return value.toString();
	}
}
