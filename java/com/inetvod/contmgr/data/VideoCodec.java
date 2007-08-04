/**
 * Copyright � 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.contmgr.data;

import java.util.HashMap;

import com.inetvod.common.core.FileExtension;

public enum VideoCodec
{
	/* Constants */
	WMV1("WMV1", FileExtension.wmv),
	WMV2("WMV2", FileExtension.wmv),
	WMV3("WMV3", FileExtension.wmv),
	AVC1("avc1", FileExtension.mp4),
	MP4V("mp4v", FileExtension.mp4),
	SVQ3("SVQ3", FileExtension.mov);

	/* Fields */
	private final String fValue;
	private final FileExtension fDefaultFileExtension;

	private static HashMap<String, VideoCodec> fAllValues = new HashMap<String, VideoCodec>();

	/* Getters and Setters */
	public String toString() { return fValue; }
	public FileExtension getDefaultFileExtension() { return fDefaultFileExtension; }

	/* Construction */
	static
	{
		for(VideoCodec value : values())
			fAllValues.put(value.toString(), value);
	}

	private VideoCodec(String value, FileExtension defaultFileExtension)
	{
		fValue = value;
		fDefaultFileExtension = defaultFileExtension;
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
