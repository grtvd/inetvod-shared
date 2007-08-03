/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.contmgr.data;

import java.util.HashMap;

import com.inetvod.common.core.FileExtension;

public enum AudioCodec
{
	/* Constants */
	MP3("mpga", FileExtension.mp3),
	M4A("mp4a", FileExtension.m4a);

	/* Fields */
	private final String fValue;
	private final FileExtension fDefaultFileExtension;

	private static HashMap<String, AudioCodec> fAllValues = new HashMap<String, AudioCodec>();

	/* Getters and Setters */
	public String toString() { return fValue; }
	public FileExtension getDefaultFileExtension() { return fDefaultFileExtension; }

	/* Construction */
	static
	{
		for(AudioCodec value : values())
			fAllValues.put(value.toString(), value);
	}

	private AudioCodec(String value, FileExtension defaultFileExtension)
	{
		fValue = value;
		fDefaultFileExtension = defaultFileExtension;
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
