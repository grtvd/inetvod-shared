/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.contmgr.data;

import java.util.HashMap;

import com.inetvod.common.core.FileExtension;
import com.inetvod.common.core.Logger;
import com.inetvod.common.data.MediaContainer;
import com.inetvod.common.data.MediaEncoding;
import com.inetvod.common.data.MediaMIME;

public class MediaMapper
{
	/* Fields */
	private static HashMap<VideoCodec, FileExtension> fVideoCodecFileExtensionMap;
	private static HashMap<AudioCodec, FileExtension> fAudioCodecFileExtensionMap;

	private static HashMap<VideoCodec, MediaMIME> fVideoCodecMediaMIMEMap;
	private static HashMap<AudioCodec, MediaMIME> fAudioCodecMediaMIMEMap;

	private static HashMap<VideoCodec, MediaContainer> fVideoCodecMediaContainerMap;
	private static HashMap<AudioCodec, MediaContainer> fAudioCodecMediaContainerMap;

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
		fVideoCodecMediaMIMEMap.put(VideoCodec.AVC1, MediaMIME.video_mp4);
		fVideoCodecMediaMIMEMap.put(VideoCodec.MP4V, MediaMIME.video_mp4);
		fVideoCodecMediaMIMEMap.put(VideoCodec.SVQ3, MediaMIME.video_quicktime);

		fAudioCodecMediaMIMEMap = new HashMap<AudioCodec, MediaMIME>();
		fAudioCodecMediaMIMEMap.put(AudioCodec.MP3, MediaMIME.audio_mpeg);
		fAudioCodecMediaMIMEMap.put(AudioCodec.M4A, MediaMIME.audio_mp4);

		fVideoCodecMediaContainerMap = new HashMap<VideoCodec, MediaContainer>();
		fVideoCodecMediaContainerMap.put(VideoCodec.WMV1, MediaContainer.ASF);
		fVideoCodecMediaContainerMap.put(VideoCodec.WMV2, MediaContainer.ASF);
		fVideoCodecMediaContainerMap.put(VideoCodec.WMV3, MediaContainer.ASF);
		fVideoCodecMediaContainerMap.put(VideoCodec.AVC1, MediaContainer.MOV);
		fVideoCodecMediaContainerMap.put(VideoCodec.MP4V, MediaContainer.MOV);
		fVideoCodecMediaContainerMap.put(VideoCodec.SVQ3, MediaContainer.MOV);
		fVideoCodecMediaContainerMap.put(VideoCodec.DIVX, MediaContainer.AVI);

		fAudioCodecMediaContainerMap = new HashMap<AudioCodec, MediaContainer>();
		fAudioCodecMediaContainerMap.put(AudioCodec.MP3, MediaContainer.MP3);
		fAudioCodecMediaContainerMap.put(AudioCodec.M4A, MediaContainer.MOV);
		fAudioCodecMediaContainerMap.put(AudioCodec.WMA2, MediaContainer.ASF);
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

	public static MediaEncoding getMediaEncodingForVideoAudioCodecs(VideoCodec videoCodec, AudioCodec audioCodec)
	{
		MediaEncoding mediaEncoding = null;

		if(videoCodec != null)
			mediaEncoding = MediaEncoding.convertFromString(videoCodec.toString());
		else if(audioCodec != null)
			mediaEncoding = MediaEncoding.convertFromString(audioCodec.toString());
		else
			return null;

		if(mediaEncoding == null)
			Logger.logWarn(MediaMapper.class, "getMediaEncodingForVideoAudioCodecs",
				String.format("No map for VideoCodec(%s)/AudioCodec(%s)", VideoCodec.convertToString(videoCodec),
				AudioCodec.convertToString(audioCodec)));

		return mediaEncoding;
	}

	public static MediaContainer getMediaContainerForVideoAudioCodecs(VideoCodec videoCodec, AudioCodec audioCodec)
	{
		MediaContainer mediaContainer = null;

		if(videoCodec != null)
			mediaContainer = fVideoCodecMediaContainerMap.get(videoCodec);
		else if(audioCodec != null)
			mediaContainer = fAudioCodecMediaContainerMap.get(audioCodec);
		else
			return null;

		if(mediaContainer == null)
			Logger.logWarn(MediaMapper.class, "getMediaContainerForVideoAudioCodecs",
				String.format("No map for VideoCodec(%s)/AudioCodec(%s)", VideoCodec.convertToString(videoCodec),
				AudioCodec.convertToString(audioCodec)));

		return mediaContainer;
	}
}
