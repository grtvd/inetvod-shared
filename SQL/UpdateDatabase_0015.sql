--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

use [iNetVOD]
GO

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION
SET QUOTED_IDENTIFIER ON
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
SET ARITHABORT ON
SET NUMERIC_ROUNDABORT OFF
SET CONCAT_NULL_YIELDS_NULL ON
SET ANSI_NULLS ON
SET ANSI_PADDING ON
SET ANSI_WARNINGS ON
COMMIT

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION
EXECUTE sp_rename N'dbo.MemberProvider.EncryptedUserName', N'Tmp_UserID', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.MemberProvider.EncryptedPassword', N'Tmp_Password_1', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.MemberProvider.Tmp_UserID', N'UserID', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.MemberProvider.Tmp_Password_1', N'Password', 'COLUMN'
GO
COMMIT

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION
ALTER TABLE dbo.MemberLogon
	DROP CONSTRAINT FK_MemberLogon_Member
GO
COMMIT
BEGIN TRANSACTION
ALTER TABLE dbo.MemberLogon
	DROP CONSTRAINT DF__MemberLog__Logon__1E704FB5
GO
ALTER TABLE dbo.MemberLogon
	DROP CONSTRAINT DF__MemberLog__Logon__1F6473EE
GO
CREATE TABLE dbo.Tmp_MemberLogon
	(
	MemberID uniqueidentifier NOT NULL ROWGUIDCOL,
	EmailKey varchar(64) NOT NULL,
	Email varchar(64) NOT NULL,
	Password varchar(32) NOT NULL,
	LogonID int NOT NULL IDENTITY (100000200, 1),
	PIN varchar(32) NULL,
	SecretQuestion varchar(128) NOT NULL,
	SecretAnswer varchar(64) NOT NULL,
	TermsAcceptedOn datetime NOT NULL,
	TermsAcceptedVersion varchar(16) NOT NULL,
	LogonFailedAt datetime NULL,
	LogonFailedCount tinyint NOT NULL,
	LogonDisabled bit NOT NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.Tmp_MemberLogon ADD CONSTRAINT
	DF__MemberLog__Logon__1E704FB5 DEFAULT (0) FOR LogonFailedCount
GO
ALTER TABLE dbo.Tmp_MemberLogon ADD CONSTRAINT
	DF__MemberLog__Logon__1F6473EE DEFAULT (0) FOR LogonDisabled
GO
SET IDENTITY_INSERT dbo.Tmp_MemberLogon ON
GO
IF EXISTS(SELECT * FROM dbo.MemberLogon)
	 EXEC('INSERT INTO dbo.Tmp_MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion, LogonFailedAt, LogonFailedCount, LogonDisabled)
		SELECT MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion, LogonFailedAt, LogonFailedCount, LogonDisabled FROM dbo.MemberLogon TABLOCKX')
GO
SET IDENTITY_INSERT dbo.Tmp_MemberLogon OFF
GO
DROP TABLE dbo.MemberLogon
GO
EXECUTE sp_rename N'dbo.Tmp_MemberLogon', N'MemberLogon', 'OBJECT'
GO
ALTER TABLE dbo.MemberLogon ADD CONSTRAINT
	PK_MemberLogon PRIMARY KEY CLUSTERED 
	(
	MemberID
	) ON [PRIMARY]

GO
CREATE UNIQUE NONCLUSTERED INDEX IX_MemberLogon_Email ON dbo.MemberLogon
	(
	EmailKey
	) ON [PRIMARY]
GO
CREATE UNIQUE NONCLUSTERED INDEX IX_MemberLogon_LogonID ON dbo.MemberLogon
	(
	LogonID
	) ON [PRIMARY]
GO
ALTER TABLE dbo.MemberLogon WITH NOCHECK ADD CONSTRAINT
	FK_MemberLogon_Member FOREIGN KEY
	(
	MemberID
	) REFERENCES dbo.Member
	(
	MemberID
	) ON UPDATE CASCADE
	 ON DELETE CASCADE
	
GO
COMMIT

--//////////////////////////////////////////////////////////////////////////////

update memberlogon
set secretquestion = 'MiTcDA4MBwfFfWyCuSzc2Kx0cV2yS1XwJFo7myghL2zJrGv4SOSE2Q==',
secretanswer = 'Ko1rL/anv8A='
GO
COMMIT


--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
