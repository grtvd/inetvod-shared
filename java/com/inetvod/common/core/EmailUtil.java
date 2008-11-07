/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailUtil
{
	public static boolean isEmail(String str)
	{
		try
		{
			InternetAddress internetAddress = new InternetAddress(str);
			internetAddress.validate();
			return true;
		}
		catch(AddressException ignore)
		{
		}
		return false;
	}
}
