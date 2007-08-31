--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
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
ALTER TABLE dbo.ShowProvider
	DROP CONSTRAINT FK_ShowProvider_ProviderConnection
GO
COMMIT

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION
ALTER TABLE dbo.ShowProvider
	DROP CONSTRAINT FK_ShowProvider_Provider
GO
COMMIT

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION
ALTER TABLE dbo.ShowProvider
	DROP CONSTRAINT FK_ShowProvider_Show
GO
COMMIT

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION
CREATE TABLE dbo.Tmp_ShowProvider
	(
	ShowProviderID uniqueidentifier NOT NULL ROWGUIDCOL,
	ShowID uniqueidentifier NOT NULL,
	ProviderID varchar(64) NOT NULL,
	ProviderConnectionID uniqueidentifier NOT NULL,
	ProviderShowID varchar(256) NOT NULL,
	ShowURL varchar(4096) NULL,
	ShowFormatMime varchar(32) NULL,
	ShowCostList varchar(2048) NULL,
	ShowAvail varchar(32) NOT NULL
	)  ON [PRIMARY]
GO
IF EXISTS(SELECT * FROM dbo.ShowProvider)
	 EXEC('INSERT INTO dbo.Tmp_ShowProvider (ShowProviderID, ShowID, ProviderID, ProviderConnectionID, ProviderShowID, ShowURL, ShowFormatMime, ShowAvail)
		SELECT ShowProviderID, ShowID, ProviderID, ProviderConnectionID, ProviderShowID, ShowURL, ShowFormatMime, ShowAvail FROM dbo.ShowProvider TABLOCKX')
GO
DROP TABLE dbo.ShowProvider
GO
EXECUTE sp_rename N'dbo.Tmp_ShowProvider', N'ShowProvider', 'OBJECT'
GO
ALTER TABLE dbo.ShowProvider ADD CONSTRAINT
	PK_ShowProvider PRIMARY KEY CLUSTERED
	(
	ShowProviderID
	) ON [PRIMARY]

GO
CREATE NONCLUSTERED INDEX IX_ShowProvider_ShowID ON dbo.ShowProvider
	(
	ShowID
	) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX IX_ShowProvider_ProviderID ON dbo.ShowProvider
	(
	ProviderID
	) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX IX_ShowProvider_ProviderConnectionID ON dbo.ShowProvider
	(
	ProviderConnectionID
	) ON [PRIMARY]
GO
ALTER TABLE dbo.ShowProvider WITH NOCHECK ADD CONSTRAINT
	FK_ShowProvider_Show FOREIGN KEY
	(
	ShowID
	) REFERENCES dbo.Show
	(
	ShowID
	)
GO
ALTER TABLE dbo.ShowProvider WITH NOCHECK ADD CONSTRAINT
	FK_ShowProvider_Provider FOREIGN KEY
	(
	ProviderID
	) REFERENCES dbo.Provider
	(
	ProviderID
	)
GO
ALTER TABLE dbo.ShowProvider WITH NOCHECK ADD CONSTRAINT
	FK_ShowProvider_ProviderConnection FOREIGN KEY
	(
	ProviderConnectionID
	) REFERENCES dbo.ProviderConnection
	(
	ProviderConnectionID
	)
GO
COMMIT

--//////////////////////////////////////////////////////////////////////////////

update showprovider set showcostlist = '<?xml version="1.0" encoding="UTF-8"?><data><ShowCost><ShowCostType>Free</ShowCostType><CostDisplay>Free</CostDisplay></ShowCost></data>'
where showcostlist is null

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION
ALTER TABLE dbo.ShowProvider
	DROP CONSTRAINT FK_ShowProvider_ProviderConnection
GO
COMMIT
BEGIN TRANSACTION
ALTER TABLE dbo.ShowProvider
	DROP CONSTRAINT FK_ShowProvider_Provider
GO
COMMIT
BEGIN TRANSACTION
ALTER TABLE dbo.ShowProvider
	DROP CONSTRAINT FK_ShowProvider_Show
GO
COMMIT
BEGIN TRANSACTION
CREATE TABLE dbo.Tmp_ShowProvider
	(
	ShowProviderID uniqueidentifier NOT NULL ROWGUIDCOL,
	ShowID uniqueidentifier NOT NULL,
	ProviderID varchar(64) NOT NULL,
	ProviderConnectionID uniqueidentifier NOT NULL,
	ProviderShowID varchar(256) NOT NULL,
	ShowURL varchar(4096) NULL,
	ShowFormatMime varchar(32) NULL,
	ShowFormat_ShowFormatID varchar(256) NULL,
	ShowFormat_MediaEncoding varchar(32) NULL,
	ShowFormat_MediaContainer varchar(32) NULL,
	ShowFormat_HorzResolution smallint NULL,
	ShowFormat_VertResolution smallint NULL,
	ShowFormat_FramesPerSecond smallint NULL,
	ShowFormat_BitRate smallint NULL,
	ShowCostList varchar(2048) NULL,
	ShowAvail varchar(32) NOT NULL
	)  ON [PRIMARY]
GO
IF EXISTS(SELECT * FROM dbo.ShowProvider)
	 EXEC('INSERT INTO dbo.Tmp_ShowProvider (ShowProviderID, ShowID, ProviderID, ProviderConnectionID, ProviderShowID, ShowURL, ShowFormatMime, ShowCostList, ShowAvail)
		SELECT ShowProviderID, ShowID, ProviderID, ProviderConnectionID, ProviderShowID, ShowURL, ShowFormatMime, ShowCostList, ShowAvail FROM dbo.ShowProvider TABLOCKX')
GO
DROP TABLE dbo.ShowProvider
GO
EXECUTE sp_rename N'dbo.Tmp_ShowProvider', N'ShowProvider', 'OBJECT'
GO
ALTER TABLE dbo.ShowProvider ADD CONSTRAINT
	PK_ShowProvider PRIMARY KEY CLUSTERED
	(
	ShowProviderID
	) ON [PRIMARY]

GO
CREATE NONCLUSTERED INDEX IX_ShowProvider_ShowID ON dbo.ShowProvider
	(
	ShowID
	) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX IX_ShowProvider_ProviderID ON dbo.ShowProvider
	(
	ProviderID
	) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX IX_ShowProvider_ProviderConnectionID ON dbo.ShowProvider
	(
	ProviderConnectionID
	) ON [PRIMARY]
GO
ALTER TABLE dbo.ShowProvider WITH NOCHECK ADD CONSTRAINT
	FK_ShowProvider_Show FOREIGN KEY
	(
	ShowID
	) REFERENCES dbo.Show
	(
	ShowID
	)
GO
ALTER TABLE dbo.ShowProvider WITH NOCHECK ADD CONSTRAINT
	FK_ShowProvider_Provider FOREIGN KEY
	(
	ProviderID
	) REFERENCES dbo.Provider
	(
	ProviderID
	)
GO
ALTER TABLE dbo.ShowProvider WITH NOCHECK ADD CONSTRAINT
	FK_ShowProvider_ProviderConnection FOREIGN KEY
	(
	ProviderConnectionID
	) REFERENCES dbo.ProviderConnection
	(
	ProviderConnectionID
	)
GO
COMMIT

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
