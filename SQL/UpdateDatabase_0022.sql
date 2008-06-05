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

CREATE TABLE [dbo].[RentedShowHistory] (
	[RentedShowHistoryID] uniqueidentifier NOT NULL ROWGUIDCOL ,
	[MemberID] uniqueidentifier NOT NULL ,
	[ShowID] uniqueidentifier NOT NULL ,
	[ProviderID] [varchar] (64) NOT NULL ,
	[ShowProviderID] uniqueidentifier NOT NULL ,
	[RentedShowID] uniqueidentifier NOT NULL ,

-- Show
	[Name] [varchar] (64) NOT NULL ,
	[EpisodeName] [varchar] (64) NULL ,
	[EpisodeNumber] [varchar] (32) NULL ,
	[ReleasedOn] [datetime] NULL ,
	[ReleasedYear] [smallint] NULL ,
--	[Description] [text] NULL ,
	[RunningMins] [smallint] NULL ,
--	[PictureURL] [varchar] (4096) NULL ,
	[RatingID] [varchar] (32) NULL ,
	[IsAdult] [bit] NOT NULL ,

-- ShowProvider
	[ProviderConnectionID] uniqueidentifier NOT NULL ,
	[ProviderShowID] [varchar] (256) NOT NULL ,
	[ShowURL] [varchar] (4096) NULL ,
	[ShowFormatMime] [varchar] (32) NULL ,
	[ShowFormat_ShowFormatID] [varchar] (256) NULL ,
	[ShowFormat_MediaEncoding] [varchar] (32) NULL ,
	[ShowFormat_MediaContainer] [varchar] (32) NULL ,
	[ShowFormat_HorzResolution] [smallint] NULL ,
	[ShowFormat_VertResolution] [smallint] NULL ,
	[ShowFormat_FramesPerSecond] [smallint] NULL ,
	[ShowFormat_BitRate] [smallint] NULL ,
--	[ShowCostList] [varchar] (2048) NULL ,
--	[ShowAvail] [varchar] (32) NOT NULL ,

-- RentedShow
	[ShowCost_ShowCostType] [varchar] (32) NOT NULL ,
	[ShowCost_Cost_CurrencyID] [varchar] (3) NULL ,
	[ShowCost_Cost_Amount] [decimal] (17,2) NULL ,
	[ShowCost_CostDisplay] [varchar] (32) NOT NULL ,
	[ShowCost_RentalWindowDays] [smallint] NULL ,
	[ShowCost_RentalPeriodHours] [smallint] NULL ,
--	[ShowURL] [varchar] (4096) NOT NULL ,
	[RentedOn] [datetime] NOT NULL ,
	[AvailableUntil] [datetime] NULL ,

-- Player
	[ManufacturerID] [varchar] (32) NOT NULL ,
	[ModelNo] [varchar] (32) NOT NULL ,
	[SerialNo] [varchar] (64) NOT NULL ,
	[Version] [varchar] (16) NOT NULL ,
	[PlayerIPAddress] [varchar] (32) NOT NULL
) ON [PRIMARY]

GO

COMMIT

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////

DELETE FROM MemberSession
GO

BEGIN TRANSACTION
GO
ALTER TABLE dbo.MemberSession
	DROP CONSTRAINT FK_MemberSession_Member
GO
COMMIT
BEGIN TRANSACTION
GO
CREATE TABLE dbo.Tmp_MemberSession
	(
	MemberSessionID uniqueidentifier NOT NULL ROWGUIDCOL,
	MemberID uniqueidentifier NOT NULL,
	PlayerID uniqueidentifier NOT NULL,
	PlayerSerialNo varchar(64) NOT NULL,
	PlayerVersion varchar(16) NOT NULL,
	StartedOn datetime NOT NULL,
	ExpiresAt datetime NOT NULL,
	ShowAdult bit NOT NULL,
	IncludeRatingIDList varchar(128) NULL
	)  ON [PRIMARY]
GO
IF EXISTS(SELECT * FROM dbo.MemberSession)
	 EXEC('INSERT INTO dbo.Tmp_MemberSession (MemberSessionID, MemberID, PlayerID, StartedOn, ExpiresAt, ShowAdult, IncludeRatingIDList)
		SELECT MemberSessionID, MemberID, PlayerID, StartedOn, ExpiresAt, ShowAdult, IncludeRatingIDList FROM dbo.MemberSession WITH (HOLDLOCK TABLOCKX)')
GO
DROP TABLE dbo.MemberSession
GO
EXECUTE sp_rename N'dbo.Tmp_MemberSession', N'MemberSession', 'OBJECT'
GO
ALTER TABLE dbo.MemberSession ADD CONSTRAINT
	PK_MemberSession PRIMARY KEY CLUSTERED
	(
	MemberSessionID
	) ON [PRIMARY]

GO
CREATE NONCLUSTERED INDEX IX_MemberSession_MemberID ON dbo.MemberSession
	(
	MemberID
	) ON [PRIMARY]
GO
ALTER TABLE dbo.MemberSession WITH NOCHECK ADD CONSTRAINT
	FK_MemberSession_Member FOREIGN KEY
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
--//////////////////////////////////////////////////////////////////////////////
