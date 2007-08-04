/**
 * Copyright � 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.contmgr.data;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.XmlDataReader;
import com.inetvod.common.core.XmlDataWriter;

public class Info implements com.inetvod.common.core.Readable, Writeable
{
	/* Constants */
	private static final Constructor<Info> CtorDataReader = DataReader.getCtor(Info.class);
	private static final String NodeName = "info";
	private static final int VideoCodecMaxLength = 8;
	private static final int AudioCodecMaxLength = 8;

	/* Fields */
	private long fFileSize;
	private String fVideoCodec;
	private String fAudioCodec;
	private Short fHorzResolution;
	private Short fVertResolution;
	private Short fFramesPerSecond;
	private Short fBitRate;
	private Integer fRunningTimeSecs;

	/* Getters and Setters */
	public long getFileSize() { return fFileSize; }
	public void setFileSize(long fileSize) { fFileSize = fileSize; }

	public String getVideoCodec() { return fVideoCodec; }
	public void setVideoCodec(String videoCodec) { fVideoCodec = videoCodec; }

	public String getAudioCodec() { return fAudioCodec; }
	public void setAudioCodec(String audioCodec) { fAudioCodec = audioCodec; }

	public Short getHorzResolution() { return fHorzResolution; }
	public void setHorzResolution(Short horzResolution) { fHorzResolution = horzResolution; }

	public Short getVertResolution() { return fVertResolution; }
	public void setVertResolution(Short vertResolution) { fVertResolution = vertResolution; }

	public Short getFramesPerSecond() { return fFramesPerSecond; }
	public void setFramesPerSecond(Short framesPerSecond) { fFramesPerSecond = framesPerSecond; }

	public Short getBitRate() { return fBitRate; }
	public void setBitRate(Short bitRate) { fBitRate = bitRate; }

	public Integer getRunningTimeSecs() { return fRunningTimeSecs; }
	public void setRunningTimeSecs(Integer runningTimeSecs) { fRunningTimeSecs = runningTimeSecs; }

	/* Construction */
	public Info()
	{
	}

	public Info(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public static Info readFromStream(InputStream inputStream) throws Exception
	{
		XmlDataReader reader = new XmlDataReader(inputStream);
		return reader.readObject(NodeName, CtorDataReader);
	}

	/* Implementation */
	public void writeToStream(OutputStream outputStream) throws Exception
	{
		XmlDataWriter xmlDataWriter = new XmlDataWriter(outputStream);
		xmlDataWriter.writeObject(NodeName, this);
		xmlDataWriter.flush();
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fFileSize = reader.readLong("file-size");
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeLong("file-size", fFileSize);
		writer.writeString("video-codec", fVideoCodec, VideoCodecMaxLength);
		writer.writeString("audio-codec", fAudioCodec, AudioCodecMaxLength);
		writer.writeShort("horz-resolution", fHorzResolution);
		writer.writeShort("vert-resolution", fVertResolution);
		writer.writeShort("frames-per-second", fFramesPerSecond);
		writer.writeShort("bit-rate", fBitRate);
		writer.writeInt("running-time", fRunningTimeSecs);
	}
}
