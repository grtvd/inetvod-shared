/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperties
{
	/* Fields */
	private static AppProperties fAppProperties = new AppProperties();

	private Properties fProperties;

	/* Getters and Setters */
	public static AppProperties getThe() { return fAppProperties; }

	public String getProperty(String key) { return fProperties.getProperty(key); }

	/* Construction */
	public static void initialize(String propertiesFilePath) throws IOException
	{
		FileInputStream propertiesFile = new FileInputStream(new File(propertiesFilePath));
		try
		{
			fAppProperties.fProperties = new Properties();
			fAppProperties.fProperties.loadFromXML(propertiesFile);
		}
		finally
		{
			propertiesFile.close();
		}
	}

	private AppProperties()
	{
		// nothing to do
	}
}
