/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.contmgr.data;

import java.util.HashMap;

import com.inetvod.common.core.FileExtension;
import com.inetvod.common.core.Logger;

public class FileExtensionMapper
{
	/* Fields */
	private static HashMap<VideoCodec, FileExtension> fMapVideoCodec;
	private static HashMap<AudioCodec, FileExtension> fMapAudioCodec;

	/* Construction */
	static
	{
		fMapVideoCodec = new HashMap<VideoCodec, FileExtension>();
		fMapVideoCodec.put(VideoCodec.WMV1, FileExtension.wmv);
		fMapVideoCodec.put(VideoCodec.WMV2, FileExtension.wmv);
		fMapVideoCodec.put(VideoCodec.WMV3, FileExtension.wmv);
		fMapVideoCodec.put(VideoCodec.AVC1, FileExtension.mp4);
		fMapVideoCodec.put(VideoCodec.MP4V, FileExtension.mp4);
		fMapVideoCodec.put(VideoCodec.SVQ3, FileExtension.mov);

		fMapAudioCodec = new HashMap<AudioCodec, FileExtension>();
		fMapAudioCodec.put(AudioCodec.MP3, FileExtension.mp3);
		fMapAudioCodec.put(AudioCodec.M4A, FileExtension.m4a);
		fMapAudioCodec.put(AudioCodec.WMA2, FileExtension.wma);
	}

	/* Implementation */
	public static FileExtension getDefaultForVideoCodec(VideoCodec videoCodec)
	{
		FileExtension fileExtension = fMapVideoCodec.get(videoCodec);

		if(fileExtension == null)
			Logger.logWarn(FileExtensionMapper.class, "getDefaultForVideoCodec", String.format("No map for VideoCodec(%s)",
				VideoCodec.convertToString(videoCodec)));

		return fileExtension;
	}

	public static FileExtension getDefaultForAudioCodec(AudioCodec audioCodec)
	{
		FileExtension fileExtension = fMapAudioCodec.get(audioCodec);

		if(fileExtension == null)
			Logger.logWarn(FileExtensionMapper.class, "getDefaultForAudioCodec", String.format("No map for AudioCodec(%s)",
				AudioCodec.convertToString(audioCodec)));

		return fileExtension;
	}
}
