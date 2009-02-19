/**
 * Copyright © 2004-2009 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class StreamUtil
{
	private static final int BufferSize = 4096;

	public static InputStream streamCopyToMemory(InputStream input) throws Exception
	{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		streamCopy(input, output, false);
		return new ByteArrayInputStream(output.toByteArray());
	}

	public static InputStream streamCopyToMemory(RandomAccessFile input) throws Exception
	{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		streamCopy(input, output, false);
		return new ByteArrayInputStream(output.toByteArray());
	}

	public static void streamCopy(InputStream input, OutputStream output, boolean keepPosition) throws Exception
	{
		byte[] bytes = new byte[BufferSize];
		int numBytes;

		if (keepPosition)
			input.mark(Integer.MAX_VALUE);

		while((numBytes = input.read(bytes, 0, BufferSize)) > 0)
			output.write(bytes, 0, numBytes);

		if (keepPosition)
			input.reset();
	}

	public static void streamCopy(RandomAccessFile input, OutputStream output, boolean keepPosition) throws Exception
	{
		byte[] bytes = new byte[BufferSize];
		int numBytes;
		long oldPosition = input.getFilePointer();

		while((numBytes = input.read(bytes, 0, BufferSize)) > 0)
			output.write(bytes, 0, numBytes);

		if (keepPosition)
			input.seek(oldPosition);
	}

	public static void streamCopy(InputStream input, RandomAccessFile output, boolean keepPosition) throws Exception
	{
		byte[] bytes = new byte[BufferSize];
		int numBytes;

		if (keepPosition)
			input.mark(Integer.MAX_VALUE);

		while((numBytes = input.read(bytes, 0, BufferSize)) > 0)
			output.write(bytes, 0, numBytes);

		if (keepPosition)
			input.reset();
	}

	public static void streamToFile(InputStream stream, File file) throws Exception
	{
		FileOutputStream fileStream = new FileOutputStream(file);
		try
		{
			streamCopy (stream, fileStream, stream.markSupported());
		}
		finally
		{
			fileStream.close();
		}
	}

	public static void streamToFile(InputStream stream, String file) throws Exception
	{
		streamToFile(stream, new File(file));
	}

	public static String streamToString(InputStream stream) throws Exception
	{
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		streamCopy(stream, byteArrayOutputStream, true);
		return byteArrayOutputStream.toString("UTF-8");
	}

	public static void stringToFile(String string, File file) throws Exception
	{
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(string.getBytes("UTF-8"));
		streamToFile(byteArrayInputStream, file);
	}

	public static void stringToFile(String string, String file) throws Exception
	{
		stringToFile(string, new File(file));
	}

	public static void streamFile(File file, OutputStream stream) throws Exception
	{
		FileInputStream fileStream = new FileInputStream(file);
		try
		{
			streamCopy (fileStream, stream, false);
		}
		finally
		{
			fileStream.close();
		}
	}
}
