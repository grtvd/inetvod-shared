/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
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

	/* Fields */
	private long fFileSize;

	/* Getters and Setters */
	public long getFileSize() { return fFileSize; }
	public void setFileSize(long fileSize) { fFileSize = fileSize; }

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
	}
}
