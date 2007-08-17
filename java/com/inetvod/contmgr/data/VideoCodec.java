/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.contmgr.data;

import java.util.HashMap;

public enum VideoCodec
{
	/* Constants */
	WMV1("WMV1"),
	WMV2("WMV2"),
	WMV3("WMV3"),
	AVC1("avc1"),
	MP4V("mp4v"),
	SVQ3("SVQ3"),
	DIVX("divx");

	public static final int MaxLength = 8;

	/* Fields */
	private final String fValue;

	private static HashMap<String, VideoCodec> fAllValues = new HashMap<String, VideoCodec>();

	/* Getters and Setters */
	public String toString() { return fValue; }

	/* Construction */
	static
	{
		for(VideoCodec value : values())
			fAllValues.put(value.toString(), value);
	}

	private VideoCodec(String value)
	{
		fValue = value;
	}

	/* Implementation */
	public static VideoCodec convertFromString(String value)
	{
		if((value == null) || (value.length() == 0))
			return null;

		VideoCodec videoCodec = fAllValues.get(value);
		if(videoCodec != null)
			return videoCodec;

		throw new IllegalArgumentException("bad value(" + value + ")");
	}

	public static String convertToString(VideoCodec value)
	{
		if(value == null)
			return null;
		return value.toString();
	}
}
