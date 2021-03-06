/**
 * Copyright � 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.dbdata;

import java.sql.Types;
import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.inetvod.common.core.CompUtil;
import com.inetvod.common.core.DataExists;
import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.StrUtil;
import com.inetvod.common.crypto.CryptoCipher;
import com.inetvod.common.crypto.CryptoKeyStore;
import com.inetvod.common.data.MemberID;

public class MemberLogon extends DatabaseObject
{
	/* Constants */
	public static final String KeyPassword = "com.inetvod.common.dbdata.MemberLogon";

	private static final int EmailMaxLength = 64;
	private static final int PasswordMaxLength = 32;
	private static final int PINMaxLength = 32;

	private static final int SecretQuestionMaxLength = 128;		// max encrypted length
	private static final int SecretAnswerMaxLength = 64;		// max encrypted length
	private static final int TermsAcceptedVersionMaxLength = 16;

	/* Fields */
	private CryptoCipher fCryptoCipher;		// don't access directory, use getCryptoCipher()

	private MemberID fMemberID;
	private String fEmail;
	private String fPassword;
	private int fLogonID;
	private String fPIN;

	private String fSecretQuestionEncrypted;
	private String fSecretQuestionPlainText;
	private String fSecretAnswerEncrypted;
	private String fSecretAnswerPlainText;

	private Date fTermsAcceptedOn;
	private String fTermsAcceptedVersion;
	private Date fLogonFailedAt;
	private int fLogonFailedCount;
	private boolean fLogonDisabled;

	private static DatabaseAdaptor<MemberLogon, MemberLogonList> fDatabaseAdaptor =
		new DatabaseAdaptor<MemberLogon, MemberLogonList>(MemberLogon.class, MemberLogonList.class);
	public static DatabaseAdaptor<MemberLogon, MemberLogonList> getDatabaseAdaptor() { return fDatabaseAdaptor; }

	/* Getters and Setters */
	private CryptoCipher getCryptoCipher() throws Exception
	{
		if(fCryptoCipher == null)
			fCryptoCipher = CryptoCipher.newInstance(CryptoKeyStore.getThe().getKeyPassword(KeyPassword));
		return fCryptoCipher;
	}

	public MemberID getMemberID() { return fMemberID; }

	public String getEmail() { return fEmail; }
	public void setEmail(String email) throws AddressException
	{
		InternetAddress internetAddress = new InternetAddress(email);
		internetAddress.validate();
		fEmail = internetAddress.toString();
	}

	public String getPassword() { return fPassword; }
	public void setPassword(String password) { fPassword = password; }

	public int getLogonID() { return fLogonID; }

	public String getPIN() { return fPIN; }
	public void setPIN(String PIN) { fPIN = PIN; }

	public String getSecretQuestion() throws Exception
	{
		if(StrUtil.hasLen(fSecretQuestionEncrypted) && !StrUtil.hasLen(fSecretQuestionPlainText))
			fSecretQuestionPlainText = getCryptoCipher().decrypt(fSecretQuestionEncrypted);
		return fSecretQuestionPlainText;
	}

	public void setSecretQuestion(String secretQuestion) throws Exception
	{
		fSecretQuestionPlainText = secretQuestion;
		fSecretQuestionEncrypted = getCryptoCipher().encrypt(fSecretQuestionPlainText);
	}

	public String getSecretAnswer() throws Exception
	{
		if(StrUtil.hasLen(fSecretAnswerEncrypted) && !StrUtil.hasLen(fSecretAnswerPlainText))
			fSecretAnswerPlainText = getCryptoCipher().decrypt(fSecretAnswerEncrypted);
		return fSecretAnswerPlainText;
	}

	public void setSecretAnswer(String secretAnswer) throws Exception
	{
		fSecretAnswerPlainText = secretAnswer;
		fSecretAnswerEncrypted = getCryptoCipher().encrypt(fSecretAnswerPlainText);
	}

	public Date getTermsAcceptedOn() { return fTermsAcceptedOn; }
	public void setTermsAcceptedOn(Date termsAcceptedOn) { fTermsAcceptedOn = termsAcceptedOn; }

	public String getTermsAcceptedVersion() { return fTermsAcceptedVersion; }
	public void setTermsAcceptedVersion(String termsAcceptedVersion) { fTermsAcceptedVersion = termsAcceptedVersion; }

	public Date getLogonFailedAt() { return fLogonFailedAt; }
	public void setLogonFailedAt(Date logonFailedAt) { fLogonFailedAt = logonFailedAt; }

	public int getLogonFailedCount() { return fLogonFailedCount; }
	public void setLogonFailedCount(int logonFailedCount) { fLogonFailedCount = logonFailedCount; }

	public boolean getLogonDisabled() { return fLogonDisabled; }
	public void setLogonDisabled(boolean logonDisabled) { fLogonDisabled = logonDisabled; }

	/* Construction */
	private MemberLogon(MemberID memberID)
	{
		super(true);
		fMemberID = memberID;
	}

	public MemberLogon(DataReader reader) throws Exception
	{
		super(reader);
		readFrom(reader);
	}

	public static MemberLogon newInstance(MemberID memberID)
	{
		return new MemberLogon(memberID);
	}

	private static MemberLogon load(MemberID memberID, DataExists exists) throws Exception
	{
		return fDatabaseAdaptor.selectByKey(memberID, exists);
	}

	public static MemberLogon get(MemberID memberID) throws Exception
	{
		return load(memberID, DataExists.MayNotExist);
	}

	public static MemberLogon getCreate(MemberID memberID) throws Exception
	{
		MemberLogon memberLogon = load(memberID, DataExists.MayNotExist);

		if(memberLogon == null)
			memberLogon = newInstance(memberID);

		return memberLogon;
	}

	public static MemberLogon findByEmail(String email) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[1];

		InternetAddress internetAddress = new InternetAddress(email);
		internetAddress.validate();
		params[0] = new DatabaseProcParam(Types.VARCHAR, internetAddress.toString());

		return fDatabaseAdaptor.selectByProc("MemberLogon_GetByEmail", params, DataExists.MayNotExist);
	}

	public static MemberLogon findByEmailPassword(String email, String password) throws Exception
	{
		MemberLogon memberLogon = findByEmail(email);
		if(memberLogon == null)
			return null;

		if(!CompUtil.areEqual(memberLogon.getPassword(), password))
			return null;

		return memberLogon;
	}

	public static MemberLogon findByLogonIDPIN(int logonID, String pin) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[2];

		params[0] = new DatabaseProcParam(Types.INTEGER, logonID);
		params[1] = new DatabaseProcParam(Types.VARCHAR, pin);

		return fDatabaseAdaptor.selectByProc("MemberLogon_GetByLogonIDPIN", params, DataExists.MayNotExist);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fMemberID = reader.readDataID("MemberID", MemberID.MaxLength, MemberID.CtorString);
		fEmail = reader.readString("Email", EmailMaxLength);
		fPassword = reader.readString("Password", PasswordMaxLength);
		fLogonID = reader.readIntValue("LogonID");
		fPIN = reader.readString("PIN", PINMaxLength);

		fSecretQuestionEncrypted = reader.readString("SecretQuestion", SecretQuestionMaxLength);
		fSecretAnswerEncrypted = reader.readString("SecretAnswer", SecretAnswerMaxLength);
		fTermsAcceptedOn = reader.readDateTime("TermsAcceptedOn");
		fTermsAcceptedVersion = reader.readString("TermsAcceptedVersion", TermsAcceptedVersionMaxLength);
		fLogonFailedAt = reader.readDateTime("LogonFailedAt");
		fLogonFailedCount = reader.readIntValue("LogonFailedCount");
		fLogonDisabled = reader.readBooleanValue("LogonDisabled");
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("MemberID", fMemberID, MemberID.MaxLength);
		writer.writeString("Email", fEmail, EmailMaxLength);
		writer.writeString("Password", fPassword, PasswordMaxLength);
		writer.writeIntValue("LogonID", fLogonID);
		writer.writeString("PIN", fPIN, PINMaxLength);

		writer.writeString("SecretQuestion", fSecretQuestionEncrypted, SecretQuestionMaxLength);
		writer.writeString("SecretAnswer", fSecretAnswerEncrypted, SecretAnswerMaxLength);
		writer.writeDateTime("TermsAcceptedOn", fTermsAcceptedOn);
		writer.writeString("TermsAcceptedVersion", fTermsAcceptedVersion, TermsAcceptedVersionMaxLength);
		writer.writeDateTime("LogonFailedAt", fLogonFailedAt);
		writer.writeInt("LogonFailedCount", fLogonFailedCount);
		writer.writeBooleanValue("LogonDisabled", fLogonDisabled);
	}

	public void update() throws Exception
	{
		fDatabaseAdaptor.update(this);
	}

	public void delete() throws Exception
	{
		fDatabaseAdaptor.delete(fMemberID);
	}

	static public void delete(MemberID memberID) throws Exception
	{
		fDatabaseAdaptor.delete(memberID);
	}
}
