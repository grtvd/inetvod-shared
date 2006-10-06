/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.dbdata;

import java.sql.Types;

import com.inetvod.common.core.DataExists;
import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.StrUtil;
import com.inetvod.common.crypto.CryptoCipher;
import com.inetvod.common.crypto.CryptoKeyStore;
import com.inetvod.common.data.MemberID;
import com.inetvod.common.data.MemberProviderID;
import com.inetvod.common.data.ProviderID;

public class MemberProvider extends DatabaseObject
{
	/* Constants */
	public static final String KeyPassword = "com.inetvod.common.dbdata.MemberProvider";

	public static final int UserIDMaxLength = 128;		// max encrypted length
	public static final int PasswordMaxLength = 32;	// max encrypted length

	/* Fields */
	private CryptoCipher fCryptoCipher;		// don't access directory, use getCryptoCipher()

	private MemberProviderID fMemberProviderID;
	private MemberID fMemberID;
	private ProviderID fProviderID;

	private String fUserIDEncrypted;
	private String fUserIDPlainText;
	private String fPasswordEncrypted;
	private String fPasswordPlainText;

	private static DatabaseAdaptor<MemberProvider, MemberProviderList> fDatabaseAdaptor =
		new DatabaseAdaptor<MemberProvider, MemberProviderList>(MemberProvider.class, MemberProviderList.class);
	public static DatabaseAdaptor<MemberProvider, MemberProviderList> getDatabaseAdaptor() { return fDatabaseAdaptor; }

	/* Getters and Setters */
	private CryptoCipher getCryptoCipher() throws Exception
	{
		if(fCryptoCipher == null)
			fCryptoCipher = CryptoCipher.newInstance(CryptoKeyStore.getThe().getKeyPassword(KeyPassword));
		return fCryptoCipher;
	}

	public MemberProviderID getMemberProviderID() { return fMemberProviderID; }

	public MemberID getMemberID() { return fMemberID; }
	public ProviderID getProviderID() { return fProviderID; }

	public String getUserID() throws Exception
	{
		if(StrUtil.hasLen(fUserIDEncrypted) && !StrUtil.hasLen(fUserIDPlainText))
			fUserIDPlainText = getCryptoCipher().decrypt(fUserIDEncrypted);
		return fUserIDPlainText;
	}

	public void setUserID(String userID) throws Exception
	{
		fUserIDPlainText = userID;
		fUserIDEncrypted = getCryptoCipher().encrypt(fUserIDPlainText);
	}

	public String getPassword() throws Exception
	{
		if(StrUtil.hasLen(fPasswordEncrypted) && !StrUtil.hasLen(fPasswordPlainText))
			fPasswordPlainText = getCryptoCipher().decrypt(fPasswordEncrypted);
		return fPasswordPlainText;
	}

	public void setPassword(String password) throws Exception
	{
		fPasswordPlainText = password;
		fPasswordEncrypted = getCryptoCipher().encrypt(fPasswordPlainText);
	}

	/* Construction */
	protected MemberProvider(MemberID memberID, ProviderID providerID)
	{
		super(true);
		fMemberProviderID = MemberProviderID.newInstance();
		fMemberID = memberID;
		fProviderID = providerID;
	}

	public MemberProvider(DataReader reader) throws Exception
	{
		super(reader);
		readFrom(reader);
	}

	public static MemberProvider newInstance(MemberID memberID, ProviderID providerID)
	{
		return new MemberProvider(memberID, providerID);
	}

	protected static MemberProvider load(MemberProviderID memberProviderID, DataExists exists) throws Exception
	{
		return fDatabaseAdaptor.selectByKey(memberProviderID, exists);
	}

	public static MemberProvider get(MemberProviderID memberProviderID) throws Exception
	{
		return load(memberProviderID, DataExists.MustExist);
	}

	protected static MemberProvider loadByMemberIDProviderID(MemberID memberID, ProviderID providerID, DataExists dataExists)
		throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[2];

		params[0] = new DatabaseProcParam(Types.VARCHAR, memberID.toString());
		params[1] = new DatabaseProcParam(Types.VARCHAR, providerID.toString());

		return fDatabaseAdaptor.selectByProc("MemberProvider_GetByMemberIDProviderID", params, dataExists);
	}

	public static MemberProvider findByMemberIDProviderID(MemberID memberID, ProviderID providerID) throws Exception
	{
		return loadByMemberIDProviderID(memberID, providerID, DataExists.MayNotExist);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fMemberProviderID = reader.readDataID("MemberProviderID", MemberProviderID.MaxLength,
			MemberProviderID.CtorString);
		fMemberID = reader.readDataID("MemberID", MemberID.MaxLength, MemberID.CtorString);
		fProviderID = reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
		fUserIDEncrypted = reader.readString("UserID", UserIDMaxLength);
		fPasswordEncrypted = reader.readString("Password", PasswordMaxLength);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("MemberProviderID", fMemberProviderID, MemberProviderID.MaxLength);
		writer.writeDataID("MemberID", fMemberID, MemberID.MaxLength);
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
		writer.writeString("UserID", fUserIDEncrypted, UserIDMaxLength);
		writer.writeString("Password", fPasswordEncrypted, PasswordMaxLength);
	}

	public void update() throws Exception
	{
		fDatabaseAdaptor.update(this);
	}

	public void delete() throws Exception
	{
		fDatabaseAdaptor.delete(fMemberProviderID);
	}
}
