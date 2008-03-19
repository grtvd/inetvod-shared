--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
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

ALTER TABLE dbo.Provider ADD
	IsAdult bit NOT NULL CONSTRAINT DF_Provider_IsAdult DEFAULT 0
GO

ALTER TABLE dbo.Category ADD
	IsAdult bit NOT NULL CONSTRAINT DF_Category_IsAdult DEFAULT 0
GO

ALTER TABLE dbo.MemberSession ADD
	IncludeRatingIDList varchar(128) NULL
GO

COMMIT

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
