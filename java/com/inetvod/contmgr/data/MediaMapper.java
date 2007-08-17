/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.contmgr.data;

import java.util.HashMap;

import com.inetvod.common.core.FileExtension;
import com.inetvod.common.core.Logger;
import com.inetvod.common.data.MediaMIME;

public class MediaMapper
{
	/* Fields */
	private static HashMap<VideoCodec, FileExtension> fVideoCodecFileExtensionMap;
	private static HashMap<AudioCodec, FileExtension> fAudioCodecFileExtensionMap;

	private static HashMap<VideoCodec, MediaMIME> fVideoCodecMediaMIMEMap;
	private static HashMap<AudioCodec, MediaMIME> fAudioCodecMediaMIMEMap;

	/* Construction */
	static
	{
		fVideoCodecFileExtensionMap = new HashMap<VideoCodec, FileExtension>();
		fVideoCodecFileExtensionMap.put(VideoCodec.WMV1, FileExtension.wmv);
		fVideoCodecFileExtensionMap.put(VideoCodec.WMV2, FileExtension.wmv);
		fVideoCodecFileExtensionMap.put(VideoCodec.WMV3, FileExtension.wmv);
		fVideoCodecFileExtensionMap.put(VideoCodec.AVC1, FileExtension.mp4);
		fVideoCodecFileExtensionMap.put(VideoCodec.MP4V, FileExtension.mp4);
		fVideoCodecFileExtensionMap.put(VideoCodec.SVQ3, FileExtension.mov);

		fAudioCodecFileExtensionMap = new HashMap<AudioCodec, FileExtension>();
		fAudioCodecFileExtensionMap.put(AudioCodec.MP3, FileExtension.mp3);
		fAudioCodecFileExtensionMap.put(AudioCodec.M4A, FileExtension.m4a);
		fAudioCodecFileExtensionMap.put(AudioCodec.WMA2, FileExtension.wma);

		fVideoCodecMediaMIMEMap = new HashMap<VideoCodec, MediaMIME>();
		fVideoCodecMediaMIMEMap.put(VideoCodec.WMV1, MediaMIME.video_x_ms_wmv);
		fVideoCodecMediaMIMEMap.put(VideoCodec.WMV2, MediaMIME.video_x_ms_wmv);
		fVideoCodecMediaMIMEMap.put(VideoCodec.WMV3, MediaMIME.video_x_ms_wmv);

		fAudioCodecMediaMIMEMap = new HashMap<AudioCodec, MediaMIME>();
		fAudioCodecMediaMIMEMap.put(AudioCodec.MP3, MediaMIME.audio_mpeg);
	}

	/* Implementation */
	public static FileExtension getDefaultFileExtension(VideoCodec videoCodec)
	{
		FileExtension fileExtension = fVideoCodecFileExtensionMap.get(videoCodec);

		if(fileExtension == null)
			Logger.logWarn(MediaMapper.class, "getDefaultFileExtension", String.format("No map for VideoCodec(%s)",
				VideoCodec.convertToString(videoCodec)));

		return fileExtension;
	}

	public static FileExtension getDefaultFileExtension(AudioCodec audioCodec)
	{
		FileExtension fileExtension = fAudioCodecFileExtensionMap.get(audioCodec);

		if(fileExtension == null)
			Logger.logWarn(MediaMapper.class, "getDefaultFileExtension", String.format("No map for AudioCodec(%s)",
				AudioCodec.convertToString(audioCodec)));

		return fileExtension;
	}

	public static MediaMIME getDefaultMediaMIME(VideoCodec videoCodec)
	{
		MediaMIME mediaMIME = fVideoCodecMediaMIMEMap.get(videoCodec);

		if(mediaMIME == null)
			Logger.logWarn(MediaMapper.class, "getDefaultMediaMIME", String.format("No map for VideoCodec(%s)",
				VideoCodec.convertToString(videoCodec)));

		return mediaMIME;
	}

	public static MediaMIME getDefaultMediaMIME(AudioCodec audioCodec)
	{
		MediaMIME mediaMIME = fAudioCodecMediaMIMEMap.get(audioCodec);

		if(mediaMIME == null)
			Logger.logWarn(MediaMapper.class, "getDefaultMediaMIME", String.format("No map for AudioCodec(%s)",
				AudioCodec.convertToString(audioCodec)));

		return mediaMIME;
	}
}
