--//////////////////////////////////////////////////////////////////////////////
-- Copyright � 2005-2006 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

use [iNetVOD]
GO

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION

--//////////////////////////////////////////////////////////////////////////////

if not exists (select * from Rating where RatingID = 'tvma')
insert into Rating (RatingID, Name) values ('tvma', 'TV-MA')
GO

update Show set RatingID = 'tvma' where RatingID = 'tv-ma'
GO

delete from Rating where RatingID = 'tv-ma'
GO

--//////////////////////////////////////////////////////////////////////////////

if not exists (select * from Category where CategoryID = 'sports')
insert into Category (CategoryID, Name) values ('sports', 'Sports')
GO

--//////////////////////////////////////////////////////////////////////////////

if not exists (select * from Provider where ProviderID = 'mlb')
insert into Provider (ProviderID, Name, RequestURL, AdminUserID, AdminPassword) values ('mlb', 'MLB.com', 'http://api.inetvod.com/provider_mlb/providerapi', 'super', 'superpassword')
GO

--//////////////////////////////////////////////////////////////////////////////

COMMIT

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
