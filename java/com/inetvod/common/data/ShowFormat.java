/**
 * Copyright © 2005-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.data;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Readable;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.CompUtil;

public class ShowFormat implements Readable, Writeable
{
	/* Constants */
	public static Constructor<ShowFormat> CtorDataReader = DataReader.getCtor(ShowFormat.class);

	/* Fields */
	private ShowFormatID fShowFormatID;		//optional
	private MediaEncoding fMediaEncoding;
	private MediaContainer fMediaContainer;
	private Short fHorzResolution;
	private Short fVertResolution;
	private Short fFramesPerSecond;
	private Short fBitRate;

	private Integer fHashCode;

	/* Getters and Setters */
	public ShowFormatID getShowFormatID() { return fShowFormatID; }
	public MediaEncoding getMediaEncoding() { return fMediaEncoding; }
	public MediaContainer getMediaContainer() { return fMediaContainer; }
	public Short getHorzResolution() { return fHorzResolution; }
	public Short getVertResolution() { return fVertResolution; }
	public Short getFramesPerSecond() { return fFramesPerSecond; }
	public Short getBitRate() { return fBitRate; }

	/* Constuction */
	public ShowFormat(ShowFormatID showFormatID, MediaEncoding mediaEncoding, MediaContainer mediaContainer,
		Short horzResolution, Short vertResolution, Short framesPerSecond, Short bitRate)
	{
		fShowFormatID = showFormatID;
		fMediaEncoding = mediaEncoding;
		fMediaContainer = mediaContainer;
		fHorzResolution = horzResolution;
		fVertResolution = vertResolution;
		fFramesPerSecond = framesPerSecond;
		fBitRate = bitRate;
	}

	public ShowFormat(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	@SuppressWarnings({"NonFinalFieldReferenceInEquals"})
	@Override public boolean equals(Object obj)
	{
		if(!(obj instanceof ShowFormat))
			return false;
		ShowFormat showFormat = (ShowFormat)obj;

		return CompUtil.areEqual(fShowFormatID, showFormat.fShowFormatID)
			&& CompUtil.areEqual(fMediaEncoding, showFormat.fMediaEncoding)
			&& CompUtil.areEqual(fMediaContainer, showFormat.fMediaContainer)
			&& CompUtil.areEqual(fHorzResolution, showFormat.fHorzResolution)
			&& CompUtil.areEqual(fVertResolution, showFormat.fVertResolution)
			&& CompUtil.areEqual(fFramesPerSecond, showFormat.fFramesPerSecond)
			&& CompUtil.areEqual(fBitRate, showFormat.fBitRate);
	}

	@SuppressWarnings({ "NonFinalFieldReferencedInHashCode" })
	@Override
	public int hashCode()
	{
		if(fHashCode != null)
			return fHashCode;

		StringBuilder sb = new StringBuilder();

		if(fShowFormatID != null)
			sb.append(fShowFormatID);
		sb.append("|");
		sb.append(MediaEncoding.convertToString(fMediaEncoding));
		sb.append("|");
		sb.append(MediaContainer.convertToString(fMediaContainer));
		sb.append("|");
		if(fHorzResolution != null)
			sb.append(fHorzResolution.toString());
		sb.append("|");
		if(fVertResolution != null)
			sb.append(fVertResolution.toString());
		sb.append("|");
		if(fFramesPerSecond != null)
			sb.append(fFramesPerSecond.toString());
		sb.append("|");
		if(fBitRate != null)
			sb.append(fBitRate.toString());

		fHashCode = sb.toString().hashCode();
		return fHashCode;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fShowFormatID = reader.readDataID("ShowFormatID", ShowFormatID.MaxLength, ShowFormatID.CtorString);
		fMediaEncoding = MediaEncoding.convertFromString(reader.readString("MediaEncoding", MediaEncoding.MaxLength));
		fMediaContainer = MediaContainer.convertFromString(reader.readString("MediaContainer", MediaContainer.MaxLength));
		fHorzResolution = reader.readShort("HorzResolution");
		fVertResolution = reader.readShort("VertResolution");
		fFramesPerSecond = reader.readShort("FramesPerSecond");
		fBitRate = reader.readShort("BitRate");
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowFormatID", fShowFormatID, ShowFormatID.MaxLength);
		writer.writeString("MediaEncoding", MediaEncoding.convertToString(fMediaEncoding), MediaEncoding.MaxLength);
		writer.writeString("MediaContainer", MediaContainer.convertToString(fMediaContainer), MediaContainer.MaxLength);
		writer.writeShort("HorzResolution", fHorzResolution);
		writer.writeShort("VertResolution", fVertResolution);
		writer.writeShort("FramesPerSecond", fFramesPerSecond);
		writer.writeShort("BitRate", fBitRate);
	}
}
