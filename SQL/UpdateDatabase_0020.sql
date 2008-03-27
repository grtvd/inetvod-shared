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

ALTER TABLE dbo.Rating ADD
	SortOrder smallint NOT NULL CONSTRAINT DF_Rating_SortOrder DEFAULT 0
GO

insert into Rating (RatingID, Name) values ('clean', 'Clean')
insert into Rating (RatingID, Name) values ('explicit', 'Explicit')
GO

update Rating set SortOrder = 1 where RatingID = 'clean'
update Rating set SortOrder = 2 where RatingID = 'g'
update Rating set SortOrder = 3 where RatingID = 'tvy'
update Rating set SortOrder = 4 where RatingID = 'tvy7'
update Rating set SortOrder = 5 where RatingID = 'tvy7fv'
update Rating set SortOrder = 6 where RatingID = 'tvg'
update Rating set SortOrder = 7 where RatingID = 'pg'
update Rating set SortOrder = 8 where RatingID = 'tvpg'
update Rating set SortOrder = 9 where RatingID = 'pg13'
update Rating set SortOrder = 10 where RatingID = 'tv14'
update Rating set SortOrder = 11 where RatingID = 'r'
update Rating set SortOrder = 12 where RatingID = 'tvma'
update Rating set SortOrder = 13 where RatingID = 'explicit'
update Rating set SortOrder = 14 where RatingID = 'nc17'
GO

update MemberPrefs set IncludeRatingIDList = 'notrated,g,r,tvy,tvpg,pg,tvy7,tv14,pg13,tvy7fv,tvma,tvg,clean,explicit'
where MemberID = '3d5ef800-ac2c-4271-813e-6fc1cbd18857'
GO

COMMIT

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
