--//////////////////////////////////////////////////////////////////////////////
-- Copyright � 2008 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

use [iNetVOD]
GO

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION
SET QUOTED_IDENTIFIER ON
SET ARITHABORT ON
SET NUMERIC_ROUNDABORT OFF
SET CONCAT_NULL_YIELDS_NULL ON
SET ANSI_NULLS ON
SET ANSI_PADDING ON
SET ANSI_WARNINGS ON
COMMIT

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION
GO
ALTER TABLE dbo.ShowProvider ADD
	LastAvailableAt datetime NULL
GO

ALTER TABLE dbo.ProviderConnection ADD
	MaxDaysSinceAvail smallint NOT NULL DEFAULT 365
GO
COMMIT

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
