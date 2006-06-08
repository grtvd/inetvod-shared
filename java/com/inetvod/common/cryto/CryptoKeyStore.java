/**
 * Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.cryto;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.NameValue;
import com.inetvod.common.core.NameValueHashMap;
import com.inetvod.common.core.NameValueList;
import com.inetvod.common.core.Readable;
import com.inetvod.common.core.XmlDataReader;

public class CryptoKeyStore implements Readable
{
	/* Constants */
	public static final Constructor<CryptoKeyStore> CtorDataReader = DataReader.getCtor(CryptoKeyStore.class);

	/* Fields */
	private static CryptoKeyStore fTheCryptoKeyStore;

	private NameValueHashMap fKeyPasswordHashMap;

	/* Fields */

	/* Getters and Setters */
	public static CryptoKeyStore getThe() { return fTheCryptoKeyStore; }

	public String getKeyPassword(String keyPasswordName)
	{
		return fKeyPasswordHashMap.get(keyPasswordName);
	}

	/* Construction */
	public CryptoKeyStore(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public static void load(String filePathXml) throws Exception
	{
		FileInputStream inputStream = new FileInputStream(new File(filePathXml));
		XmlDataReader dataReader = new XmlDataReader(inputStream);

		fTheCryptoKeyStore = dataReader.readObject("cryptokeystore", CtorDataReader);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		NameValueList keyPasswordList = reader.readList("keypassord", NameValueList.Ctor, NameValue.CtorDataReader);
		fKeyPasswordHashMap = keyPasswordList.getHashMap();
	}
}
