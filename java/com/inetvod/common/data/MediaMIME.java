/**
 * Copyright © 2006-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.data;

import java.util.HashMap;

import com.inetvod.common.core.Logger;

public enum MediaMIME
{
	video_x_ms_wmv("video/x-ms-wmv"),
	video_x_msvideo("video/x-msvideo"),
	video_mp4("video/mp4"),
	video_x_m4v("video/x-m4v"),
	video_mov("video/mov"),
	video_quicktime("video/quicktime"),
	video_mpeg("video/mpeg"),
	video_x_flv("video/x-flv"),

	audio_mpeg("audio/mpeg"),
	audio_mp4("audio/mp4"),

	application_octet_stream("application/octet-stream"),
	application_x_shockwave_flash("application/x-shockwave-flash");

	/* Constants */
	public static final int MaxLength = 32;

	/* Fields */
	private static HashMap<String, MediaMIME> fAllValues = new HashMap<String, MediaMIME>();

	private final String fValue;

	static
	{
		for(MediaMIME value : values())
			fAllValues.put(value.toString(), value);
	}

	/* Getters and Setters */
	public String toString() { return fValue; }

	/* Construction */
	private MediaMIME(String name)
	{
		fValue = name;
	}

	public static MediaMIME convertFromString(String value)
	{
		if((value == null) || (value.length() == 0))
			return null;

		MediaMIME mediaMIME = fAllValues.get(value);
		if(mediaMIME != null)
			return mediaMIME;

		Logger.logWarn(MediaMIME.class, "convertFromString", String.format("bad value(%s)", value));
		return null;
	}

	public static String convertToString(MediaMIME value)
	{
		if(value == null)
			return null;
		return value.toString();
	}
}
