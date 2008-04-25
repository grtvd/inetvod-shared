/**
 * Copyright © 2006-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.dbdata;

import com.inetvod.common.core.DataExists;
import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.StrUtil;
import com.inetvod.common.crypto.CryptoCipher;
import com.inetvod.common.crypto.CryptoKeyStore;
import com.inetvod.common.data.ProviderConnectionID;
import com.inetvod.common.data.ProviderConnectionType;
import com.inetvod.common.data.ProviderID;

public class ProviderConnection extends DatabaseObject
{
	/* Constants */
	public static final String KeyPassword = "com.inetvod.common.dbdata.ProviderConnection";

	private static final int ConnectionURLMaxLength = 4096;
	private static final int AdminUserIDMaxLength = 128;	//64 if not encrypted
	private static final int AdminPasswordMaxLength = 32;	//16 if not encrypted
	private static final int UseFieldForNameMaxLength = 32;
	private static final int UseFieldForEpisodeNameMaxLength = 32;

	/* Fields */
	private CryptoCipher fCryptoCipher;		// don't access directory, use getCryptoCipher()

	private ProviderConnectionID fProviderConnectionID;
	private ProviderID fProviderID;
	private ProviderConnectionType fProviderConnectionType;
	private boolean fDisabled;

	private String fConnectionURL;

	private String fAdminUserIDEncrypted;
	private String fAdminUserIDPlainText;
	private String fAdminPasswordEncrypted;
	private String fAdminPasswordPlainText;

	private String fUseFieldForName;
	private String fUseFieldForEpisodeName;

	private static DatabaseAdaptor<ProviderConnection, ProviderConnectionList> fDatabaseAdaptor =
		new DatabaseAdaptor<ProviderConnection, ProviderConnectionList>(ProviderConnection.class, ProviderConnectionList.class);
	public static DatabaseAdaptor<ProviderConnection, ProviderConnectionList> getDatabaseAdaptor() { return fDatabaseAdaptor; }

	/* Getters and Setters */
	private CryptoCipher getCryptoCipher() throws Exception
	{
		if(fCryptoCipher == null)
			fCryptoCipher = CryptoCipher.newInstance(CryptoKeyStore.getThe().getKeyPassword(KeyPassword));
		return fCryptoCipher;
	}

	public ProviderConnectionID getProviderConnectionID() { return fProviderConnectionID; }
	public ProviderID getProviderID() { return fProviderID; }
	public ProviderConnectionType getProviderConnectionType() { return fProviderConnectionType; }
	public boolean isEnabled() { return !fDisabled; }

	public String getConnectionURL() { return fConnectionURL; }
	public void setConnectionURL(String connectionURL) { fConnectionURL = connectionURL; }

	public String getAdminUserID() throws Exception
	{
		if(StrUtil.hasLen(fAdminUserIDEncrypted) && !StrUtil.hasLen(fAdminUserIDPlainText))
			fAdminUserIDPlainText = getCryptoCipher().decrypt(fAdminUserIDEncrypted);
		return fAdminUserIDPlainText;
	}

	public String getAdminPassword() throws Exception
	{
		if(StrUtil.hasLen(fAdminPasswordEncrypted) && !StrUtil.hasLen(fAdminPasswordPlainText))
			fAdminPasswordPlainText = getCryptoCipher().decrypt(fAdminPasswordEncrypted);
		return fAdminPasswordPlainText;
	}

	public String getUseFieldForName() { return fUseFieldForName; }
	public String getUseFieldForEpisodeName() { return fUseFieldForEpisodeName; }

	/* Construction */
	public ProviderConnection(ProviderID providerID, ProviderConnectionType providerConnectionType)
	{
		super(true);
		fProviderConnectionID = ProviderConnectionID.newInstance();
		fProviderID = providerID;
		fProviderConnectionType = providerConnectionType;
	}

	public ProviderConnection(DataReader reader) throws Exception
	{
		super(reader);
		readFrom(reader);
	}

	public static ProviderConnection newInstance(ProviderID providerID, ProviderConnectionType providerConnectionType)
	{
		return new ProviderConnection(providerID, providerConnectionType);
	}

	private static ProviderConnection load(ProviderConnectionID providerConnectionID, DataExists exists) throws Exception
	{
		return fDatabaseAdaptor.selectByKey(providerConnectionID, exists);
	}

	public static ProviderConnection get(ProviderConnectionID providerConnectionID) throws Exception
	{
		return load(providerConnectionID, DataExists.MustExist);
	}

	public static ProviderConnection findByProviderIDConnectionType(ProviderID providerID,
		ProviderConnectionType providerConnectionType) throws Exception
	{
		ProviderConnectionList providerConnectionList = ProviderConnectionList.findByProviderIDConnectionType(
			providerID, providerConnectionType);

		if(providerConnectionList.size() == 1)
			return providerConnectionList.get(0);

		if(providerConnectionList.size() == 0)
			return null;

		throw new SearchException("Too Many Records Found");
	}

	public static ProviderConnection findByConnectionURL(String connectionURL) throws Exception
	{
		ProviderConnectionList providerConnectionList = ProviderConnectionList.findByConnectionURL(connectionURL);

		if(providerConnectionList.size() == 1)
			return providerConnectionList.get(0);

		if(providerConnectionList.size() == 0)
			return null;

		throw new SearchException("Too Many Records Found");
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fProviderConnectionID = reader.readDataID("ProviderConnectionID", ProviderConnectionID.MaxLength,
			ProviderConnectionID.CtorString);
		fProviderID = reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
		fProviderConnectionType = reader.readDataID("ProviderConnectionType", ProviderConnectionType.MaxLength, ProviderConnectionType.CtorString);
		fDisabled = reader.readBooleanValue("Disabled");

		fConnectionURL = reader.readString("ConnectionURL", ConnectionURLMaxLength);
		fAdminUserIDEncrypted = reader.readString("AdminUserID", AdminUserIDMaxLength);
		fAdminPasswordEncrypted = reader.readString("AdminPassword", AdminPasswordMaxLength);
		fUseFieldForName = reader.readString("UseFieldForName", UseFieldForNameMaxLength);
		fUseFieldForEpisodeName = reader.readString("UseFieldForEpisodeName", UseFieldForEpisodeNameMaxLength);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ProviderConnectionID", fProviderConnectionID, ProviderConnectionID.MaxLength);
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
		writer.writeDataID("ProviderConnectionType", fProviderConnectionType, ProviderConnectionType.MaxLength);
		writer.writeBooleanValue("Disabled", fDisabled);

		writer.writeString("ConnectionURL", fConnectionURL, ConnectionURLMaxLength);
		writer.writeString("AdminUserID", fAdminUserIDEncrypted, AdminUserIDMaxLength);
		writer.writeString("AdminPassword", fAdminPasswordEncrypted, AdminPasswordMaxLength);
		writer.writeString("UseFieldForName", fUseFieldForName, UseFieldForNameMaxLength);
		writer.writeString("UseFieldForEpisodeName", fUseFieldForEpisodeName, UseFieldForEpisodeNameMaxLength);
	}

	public void update() throws Exception
	{
		fDatabaseAdaptor.update(this);
	}

	public void delete() throws Exception
	{
		fDatabaseAdaptor.delete(fProviderConnectionID);
	}
}
