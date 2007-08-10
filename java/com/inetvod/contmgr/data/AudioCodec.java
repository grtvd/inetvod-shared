/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.contmgr.data;

import java.util.HashMap;

public enum AudioCodec
{
	/* Constants */
	MP3("mpga"),
	M4A("mp4a"),
	WMA2("WMA2");

	public static final int MaxLength = 8;

	/* Fields */
	private final String fValue;

	private static HashMap<String, AudioCodec> fAllValues = new HashMap<String, AudioCodec>();

	/* Getters and Setters */
	public String toString() { return fValue; }

	/* Construction */
	static
	{
		for(AudioCodec value : values())
			fAllValues.put(value.toString(), value);
	}

	private AudioCodec(String value)
	{
		fValue = value;
	}

	/* Implementation */
	public static AudioCodec convertFromString(String value)
	{
		if((value == null) || (value.length() == 0))
			return null;

		AudioCodec audioCodec = fAllValues.get(value);
		if(audioCodec != null)
			return audioCodec;

		throw new IllegalArgumentException("bad value(" + value + ")");
	}

	public static String convertToString(AudioCodec value)
	{
		if(value == null)
			return null;
		return value.toString();
	}
}
