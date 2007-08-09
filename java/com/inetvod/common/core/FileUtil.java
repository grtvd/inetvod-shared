/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

public class FileUtil
{
	/* Fields */

	/* Getters and Setters */

	/* Construction */

	/* Implementation */
	public static String buildFileName(String fileNameBase, FileExtension fileExtension)
	{
		if(!StrUtil.hasLen(fileNameBase))
			throw new IllegalArgumentException(String.format("fileNameBase is null"));

		if(fileExtension == null)
			return fileNameBase;

		return fileNameBase.concat(fileExtension.toString());
	}

	public static FileExtension determineFileExtFromURL(String url)
	{
		if(!StrUtil.hasLen(url))
			return null;

		int dotPos = url.lastIndexOf(".");
		if(dotPos < 0)
			return null;

		if(url.lastIndexOf("/") >= dotPos)
			return null;

		int paramPos = url.lastIndexOf("?");
		if(paramPos >= dotPos)
			return FileExtension.convertFromString(url.substring(dotPos, paramPos));

		return FileExtension.convertFromString(url.substring(dotPos).trim());
	}
}
