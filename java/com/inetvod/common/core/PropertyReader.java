/**
 * Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//TODO extend DataReader?
public class PropertyReader
{
	/* Fields */
	private Properties fProperties;

	/* Construction */
	private PropertyReader(String propertyFilePath) throws IOException
	{
		fProperties = new Properties();
		FileInputStream propertiesFile = new FileInputStream(new File(propertyFilePath));
		try
		{
			fProperties.loadFromXML(propertiesFile);
		}
		finally
		{
			propertiesFile.close();
		}
	}

	public static PropertyReader newInstance(String propertyFilePath) throws IOException
	{
		return new PropertyReader(propertyFilePath);
	}

	/* Implementation */

	/**
	 * Read a Boolean.
	 * @param fieldName
	 * @return may return null
	 */
	public Boolean readBoolean(String fieldName) throws Exception
	{
		String data = readString(fieldName);

		if(data == null)
			return null;

		return Boolean.parseBoolean(data);
	}

	/**
	 * Read a Integer.
	 * @param fieldName
	 * @return may return null
	 */
	public Integer readInt(String fieldName) throws Exception
	{
		String data = readString(fieldName);

		if(data == null)
			return null;

		return Integer.decode(data);
	}

	/**
	 * Internal read a String.
	 * @param fieldName
	 * @return may return null
	 */
	public String readString(String fieldName) throws Exception
	{
		String data = fProperties.getProperty(fieldName);

		if(StrUtil.hasLen(data))
			return data;

		return null;
	}
}
