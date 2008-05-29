/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

public class EncodeUtil
{
	/* Implementation */

	public static String encodeJSLiteral(String data)
	{
		if (data == null)
			return null;

		String newVal = data;

		if((data.indexOf("'") >= 0) || (data.indexOf("\"") >= 0))
		{
			newVal = newVal.replaceAll("\\'", "\\\\'");
			newVal = newVal.replaceAll("\\\"", "\\\\\"");
		}

		return newVal;

	}
}
