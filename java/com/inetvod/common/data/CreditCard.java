/**
 * Copyright © 2005-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.data;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Readable;
import com.inetvod.common.core.StrUtil;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.crypto.CryptoCipher;
import com.inetvod.common.crypto.CryptoKeyStore;

public class CreditCard implements Readable, Writeable
{
	/* Constants */
	public static Constructor<CreditCard> CtorDataReader = DataReader.getCtor(CreditCard.class);
	public static final String KeyPassword = "com.inetvod.common.data.CreditCard";

	private static final int NameOnCCMaxLength = 64;	// max encrypted length
	private static final int CCNumberMaxLength = 32;	// max encrypted length
	private static final int CCSICMaxLength = 16;		// max encrypted length
	private static final int ExpireDateMaxLength = 32;	// max encrypted length

	private static final int Century = 100;
	private static final int DefaultMillennia = 2000;

	/* Fields */
	private CryptoCipher fCryptoCipher;		// don't access directory, use getCryptoCipher()

	private String fNameOnCCEncrypted;
	private String fNameOnCCPlainText;
	private CreditCardType fCCType;
	private String fCCNumberEncrypted;
	private String fCCNumberPlainText;
	private String fCCSICEncrypted;
	private String fCCSICPlainText;
	private String fExpireDateEncrypted;
	private String fExpireDatePlainText;
	private Address fBillingAddress;

	/* Getters and Setters */
	private CryptoCipher getCryptoCipher() throws Exception
	{
		if(fCryptoCipher == null)
			fCryptoCipher = CryptoCipher.newInstance(CryptoKeyStore.getThe().getKeyPassword(KeyPassword));
		return fCryptoCipher;
	}

	public String getNameOnCC() throws Exception
	{
		if(StrUtil.hasLen(fNameOnCCEncrypted) && !StrUtil.hasLen(fNameOnCCPlainText))
			fNameOnCCPlainText = getCryptoCipher().decrypt(fNameOnCCEncrypted);
		return fNameOnCCPlainText;
	}

	public void setNameOnCC(String nameOnCC) throws Exception
	{
		fNameOnCCPlainText = nameOnCC;
		fNameOnCCEncrypted = getCryptoCipher().encrypt(fNameOnCCPlainText);
	}

	public CreditCardType getCCType() { return fCCType; }
	public void setCCType(CreditCardType CCType) { fCCType = CCType; }

	public String getCCNumber() throws Exception
	{
		if(StrUtil.hasLen(fCCNumberEncrypted) && !StrUtil.hasLen(fCCNumberPlainText))
			fCCNumberPlainText = getCryptoCipher().decrypt(fCCNumberEncrypted);
		return fCCNumberPlainText;
	}

	public void setCCNumber(String ccNumber) throws Exception
	{
		fCCNumberPlainText = ccNumber;
		fCCNumberEncrypted = getCryptoCipher().encrypt(fCCNumberPlainText);
	}

	public String getCCSIC() throws Exception
	{
		if(StrUtil.hasLen(fCCSICEncrypted) && !StrUtil.hasLen(fCCSICPlainText))
			fCCSICPlainText = getCryptoCipher().decrypt(fCCSICEncrypted);
		return fCCSICPlainText;
	}

	public void setCCSIC(String ccSIC) throws Exception
	{
		fCCSICPlainText = ccSIC;
		fCCSICEncrypted = getCryptoCipher().encrypt(fCCSICPlainText);
	}

	private String getExpireDate() throws Exception
	{
		if(StrUtil.hasLen(fExpireDateEncrypted) && !StrUtil.hasLen(fExpireDatePlainText))
			fExpireDatePlainText = getCryptoCipher().decrypt(fExpireDateEncrypted);
		return fExpireDatePlainText;
	}

	private void setExpireDate(String expireDate) throws Exception
	{
		fExpireDatePlainText = expireDate;
		fExpireDateEncrypted = getCryptoCipher().encrypt(fExpireDatePlainText);
	}

	public int getExpireDateMonth() throws Exception
	{
		String expireDate = getExpireDate();
		if((expireDate == null) || (expireDate.length() != 6))
			return 0;

		return Integer.parseInt(expireDate.substring(0, 2));
	}

	public int getExpireDateYear() throws Exception
	{
		String expireDate = getExpireDate();
		if((expireDate == null) || (expireDate.length() != 6))
			return 0;

		return Integer.parseInt(expireDate.substring(2, 6));
	}

	public void setExpireDate(int expireMonth, int expireYear) throws Exception
	{
		if(expireYear < Century)
			expireYear += DefaultMillennia;
		setExpireDate(String.format("%02d%04d", expireMonth, expireYear));
	}

	public Address getBillingAddress() { return fBillingAddress; }
	public void setBillingAddress(Address billingAddress) { fBillingAddress = billingAddress; }

	/* Constuction */
	public CreditCard()
	{
	}

	public CreditCard(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fNameOnCCEncrypted = reader.readString("NameOnCC", NameOnCCMaxLength);
		fCCType = CreditCardType.convertFromString(reader.readString("CCType", CreditCardType.MaxLength));
		fCCNumberEncrypted = reader.readString("CCNumber", CCNumberMaxLength);
		fCCSICEncrypted = reader.readString("CCSIC", CCSICMaxLength);
		fExpireDateEncrypted = reader.readString("ExpireDate", ExpireDateMaxLength);
		fBillingAddress = reader.readObject("BillingAddress", Address.CtorDataReader);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("NameOnCC", fNameOnCCEncrypted, NameOnCCMaxLength);
		writer.writeString("CCType", CreditCardType.convertToString(fCCType), CreditCardType.MaxLength);
		writer.writeString("CCNumber", fCCNumberEncrypted, CCNumberMaxLength);
		writer.writeString("CCSIC", fCCSICEncrypted, CCSICMaxLength);
		writer.writeString("ExpireDate", fExpireDateEncrypted, ExpireDateMaxLength);
		writer.writeObject("BillingAddress", fBillingAddress);
	}
}
