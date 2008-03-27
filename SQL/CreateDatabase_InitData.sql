--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2005-2008 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

use inetvod

--//////////////////////////////////////////////////////////////////////////////

delete from RentedShow
delete from ShowProvider
delete from ShowCategory
delete from Show
delete from ProviderConnection
delete from Provider
delete from Category
delete from Rating
delete from MemberPrefs
delete from MemberAccount
delete from MemberLogon
delete from Member
go

--//////////////////////////////////////////////////////////////////////////////

insert into Provider (ProviderID, Name)
values ('internetvideos', 'Internet Videos')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL, AdminUserID, AdminPassword)
values (newid(), 'internetvideos', 'ProviderAPI', 'http://localhost/provider_internetvideos/providerapi', 'super', 'superpassword')


insert into Provider (ProviderID, Name)
values ('moviesmovies', 'Movies, Movies')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL, AdminUserID, AdminPassword)
values (newid(), 'moviesmovies', 'ProviderAPI', 'http://localhost/provider_moviesmovies/providerapi', 'super', 'superpassword')


insert into Provider (ProviderID, Name)
values ('vodflicks', 'VOD Flicks')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL, AdminUserID, AdminPassword)
values (newid(), 'vodflicks', 'ProviderAPI', 'http://localhost/provider_vodflicks/providerapi', 'super', 'superpassword')


insert into Provider (ProviderID, Name)
values ('excellentvideos', 'Excellent Videos')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL, AdminUserID, AdminPassword)
values (newid(), 'excellentvideos', 'ProviderAPI', 'http://localhost/provider_excellentvideos/providerapi', 'super', 'superpassword')


insert into Provider (ProviderID, Name)
values ('mlb', 'MLB.com')

--insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL, AdminUserID, AdminPassword)
--values (newid(), 'mlb', 'ProviderAPI', 'http://localhost/provider_mlb/providerapi', 'super', 'superpassword')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL)
values (newid(), 'mlb', 'Rss2', 'http://mlb.mlb.com/feed/podcast/c1266208.xml')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL)
values (newid(), 'mlb', 'Rss2', 'http://mlb.mlb.com/feed/podcast/mlbradio.xml')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL)
values (newid(), 'mlb', 'Rss2', 'http://mlb.mlb.com/feed/podcast/stayinhot.xml')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL)
values (newid(), 'mlb', 'Rss2', 'http://mlb.mlb.com/feed/podcast/fantasy411.xml')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL)
values (newid(), 'mlb', 'Rss2', 'http://mlb.mlb.com/feed/podcast/c1291376.xml')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL)
values (newid(), 'mlb', 'Rss2', 'http://mlb.mlb.com/feed/podcast/c1265860.xml')


insert into Provider (ProviderID, Name)
values ('rocketboom', 'Rocketboom')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL)
values (newid(), 'rocketboom', 'Rss2', 'http://www.rocketboom.com/vlog/win_media_player_daily_enclosures.xml')


insert into Provider (ProviderID, Name)
values ('insidedigitalmedia', 'Inside Digital Media')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL)
values (newid(), 'insidedigitalmedia', 'Rss2', 'http://www.insidedigitalmedia.com/podcasts/podcasts.xml')


insert into Provider (ProviderID, Name)
values ('radioleo', 'Radio Leo')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL)
values (newid(), 'radioleo', 'Rss2', 'http://leoville.tv/podcasts/leo.xml')


insert into Provider (ProviderID, Name)
values ('happyhouseofhentai', 'Happy House of Hentai')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL)
values (newid(), 'happyhouseofhentai', 'Rss2', 'http://happyhouseofhentai.libsyn.com/rss')


insert into Provider (ProviderID, Name)
values ('clerks2', 'Clerks 2')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL)
values (newid(), 'clerks2', 'Rss2', 'http://clerks2.com/clerks2.xml')

--insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL)
--values (newid(), 'clerks2', 'Rss2', 'http://clerks2.com/trainwreck/?feed=rss2')
go

--//////////////////////////////////////////////////////////////////////////////

insert into Category (CategoryID, Name)
values ('action', 'Action')
insert into Category (CategoryID, Name)
values ('classics', 'Classics')
insert into Category (CategoryID, Name)
values ('comedy', 'Comedy')
insert into Category (CategoryID, Name)
values ('daily', 'Daily')
insert into Category (CategoryID, Name)
values ('drama', 'Drama')
insert into Category (CategoryID, Name)
values ('family', 'Family')
insert into Category (CategoryID, Name)
values ('featured', 'Featured')
insert into Category (CategoryID, Name)
values ('romance', 'Romance')
insert into Category (CategoryID, Name)
values ('scifi', 'Sci-Fi')
insert into Category (CategoryID, Name)
values ('sports', 'Sports')
insert into Category (CategoryID, Name)
values ('tv', 'TV')
insert into Category (CategoryID, Name)
values ('thriller', 'Thriller')
insert into Category (CategoryID, Name)
values ('trailers', 'Trailers')
insert into Category (CategoryID, Name)
values ('western', 'Western')

insert into Category (CategoryID, Name)
values ('technology', 'Technology')
go

--//////////////////////////////////////////////////////////////////////////////

insert into Rating (RatingID, Name, SortOrder)
values ('g', 'G', 2)
insert into Rating (RatingID, Name, SortOrder)
values ('pg', 'PG', 7)
insert into Rating (RatingID, Name, SortOrder)
values ('pg13', 'PG-13', 9)
insert into Rating (RatingID, Name, SortOrder)
values ('r', 'R', 11)
insert into Rating (RatingID, Name, SortOrder)
values ('nc17', 'NC-17', 14)
insert into Rating (RatingID, Name, SortOrder)
values ('tvy', 'TV-Y', 3)
insert into Rating (RatingID, Name, SortOrder)
values ('tvy7', 'TV-Y7', 4)
insert into Rating (RatingID, Name, SortOrder)
values ('tvy7fv', 'TV-Y7-FV', 5)
insert into Rating (RatingID, Name, SortOrder)
values ('tvg', 'TV-G', 6)
insert into Rating (RatingID, Name, SortOrder)
values ('tvpg', 'TV-PG', 8)
insert into Rating (RatingID, Name, SortOrder)
values ('tv14', 'TV-14', 10)
insert into Rating (RatingID, Name, SortOrder)
values ('tvma', 'TV-MA', 12)

insert into Rating (RatingID, Name, SortOrder)
values ('clean', 'Clean', 1)
insert into Rating (RatingID, Name, SortOrder)
values ('explicit', 'Explicit', 13)
go

--//////////////////////////////////////////////////////////////////////////////

insert Member (MemberID, FirstName, LastName)
values ('3d5ef800-ac2c-4271-813e-6fc1cbd18857', 'Guest', 'Account')
go

--//////////////////////////////////////////////////////////////////////////////

SET IDENTITY_INSERT MemberLogon ON

insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion)
values ('3d5ef800-ac2c-4271-813e-6fc1cbd18857', 'GUEST', 'guest', 'aNVXb4tZEDEi+QzHM8qO5cBSLrk=', 1, 'Fz13RHCV1W3YudkDX1CnTcUThQY=', 'MiTcDA4MBwfFfWyCuSzc2Kx0cV2yS1XwJFo7myghL2zJrGv4SOSE2Q==', 'Ko1rL/anv8A=', '2007-11-01 00:00:00.000', '1.0.0')
go

SET IDENTITY_INSERT MemberLogon OFF

--//////////////////////////////////////////////////////////////////////////////

insert MemberPrefs (MemberID, IncludeAdult, AdultPIN, IncludeRatingIDList, IncludeDownload, IncludeStreaming, ConnectionSpeed)
values ('3d5ef800-ac2c-4271-813e-6fc1cbd18857', 'Never', null, 'notrated,g,r,tvy,tvpg,pg,tvy7,tv14,pg13,tvy7fv,tvma,tvg,clean,explicit', 1, 1, '1500K')
go

--//////////////////////////////////////////////////////////////////////////////
