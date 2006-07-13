/**
 * Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.crypto;


import java.security.MessageDigest;

import com.inetvod.common.core.Logger;
import sun.misc.BASE64Encoder;

public final class CryptoDigest
{
	/* Constants */
	private static final String ALGORITHM = "SHA";
	private static final String CHAR_ENCODING = "UTF-8";

	/* Implementation */
	public static String encrypt(String plaintext) throws Exception
	{
		synchronized(CryptoDigest.class)
		{
			MessageDigest md;
			try
			{
				md = MessageDigest.getInstance(ALGORITHM);
				md.update(plaintext.getBytes(CHAR_ENCODING));

				byte raw[] = md.digest();
				return (new BASE64Encoder()).encode(raw);
			}
			catch(Exception e)
			{
				Logger.logErr(new CryptoDigest(), "encrypt", e);
				throw e;
			}
		}
	}
}
