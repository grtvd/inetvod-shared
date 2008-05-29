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
values (newid(), 'internetvideos', 'ProviderAPI', 'http://api.stormmediaplayer.com/provider_internetvideos/providerapi', 'eY36JT/GCwo=', 'Y9bRQu3ioSxiSri4gVZbtQ==')


insert into Provider (ProviderID, Name)
values ('moviesmovies', 'Movies, Movies')

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL, AdminUserID, AdminPassword)
values (newid(), 'moviesmovies', 'ProviderAPI', 'http://api.stormmediaplayer.com/provider_moviesmovies/providerapi', 'eY36JT/GCwo=', 'Y9bRQu3ioSxiSri4gVZbtQ==')


insert into Provider (ProviderID, Name, IsAdult)
values ('abbywinters', 'Abby Winters', 1)

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL, AdminUserID, AdminPassword)
values (newid(), 'abbywinters', 'ProviderAPI', 'http://api.stormmediaplayer.com/provider_abbywinters/providerapi', 'eY36JT/GCwo=', 'Y9bRQu3ioSxiSri4gVZbtQ==')

go

--//////////////////////////////////////////////////////////////////////////////

--insert into Category (CategoryID, Name) values ('action', 'Action')
--insert into Category (CategoryID, Name) values ('classics', 'Classics')
--insert into Category (CategoryID, Name) values ('comedy', 'Comedy')
--insert into Category (CategoryID, Name) values ('daily', 'Daily')
--insert into Category (CategoryID, Name) values ('drama', 'Drama')
--insert into Category (CategoryID, Name) values ('family', 'Family')
--insert into Category (CategoryID, Name) values ('featured', 'Featured')
--insert into Category (CategoryID, Name) values ('romance', 'Romance')
--insert into Category (CategoryID, Name) values ('scifi', 'Sci-Fi')
--insert into Category (CategoryID, Name) values ('sports', 'Sports')
--insert into Category (CategoryID, Name) values ('tv', 'TV')
--insert into Category (CategoryID, Name) values ('thriller', 'Thriller')
--insert into Category (CategoryID, Name) values ('trailers', 'Trailers')
--insert into Category (CategoryID, Name) values ('western', 'Western')
--insert into Category (CategoryID, Name) values ('technology', 'Technology'go


insert into Category (CategoryID, Name) values ('featured', 'Featured')

insert into Category (CategoryID, Name, IsAdult) values ('art', 'Arts', 0)
insert into Category (CategoryID, Name, IsAdult) values ('art-des', 'Arts: Design', 0)
insert into Category (CategoryID, Name, IsAdult) values ('art-fas', 'Arts: Fashion & Beauty', 0)
insert into Category (CategoryID, Name, IsAdult) values ('art-foo', 'Arts: Food', 0)
insert into Category (CategoryID, Name, IsAdult) values ('art-lit', 'Arts: Literature', 0)
insert into Category (CategoryID, Name, IsAdult) values ('art-pa', 'Arts: Performing Arts', 0)
insert into Category (CategoryID, Name, IsAdult) values ('art-va', 'Arts: Visual Arts', 0)

insert into Category (CategoryID, Name, IsAdult) values ('bus', 'Business', 0)
insert into Category (CategoryID, Name, IsAdult) values ('bus-news', 'Business: News', 0)
insert into Category (CategoryID, Name, IsAdult) values ('bus-car', 'Business: Careers', 0)
insert into Category (CategoryID, Name, IsAdult) values ('bus-inv', 'Business: Investing', 0)
insert into Category (CategoryID, Name, IsAdult) values ('bus-mgt', 'Business: Management & Marketing', 0)
insert into Category (CategoryID, Name, IsAdult) values ('bus-sho', 'Business: Shopping', 0)

insert into Category (CategoryID, Name, IsAdult) values ('com', 'Comedy', 0)

insert into Category (CategoryID, Name, IsAdult) values ('dai', 'Daily', 0)

insert into Category (CategoryID, Name, IsAdult) values ('edu', 'Education', 0)
insert into Category (CategoryID, Name, IsAdult) values ('edu-tec', 'Education: Education Technology', 0)
insert into Category (CategoryID, Name, IsAdult) values ('edu-hig', 'Education: Higher Education', 0)
insert into Category (CategoryID, Name, IsAdult) values ('edu-k12', 'Education: K-12', 0)
insert into Category (CategoryID, Name, IsAdult) values ('edu-lan', 'Education: Language Courses', 0)
insert into Category (CategoryID, Name, IsAdult) values ('edu-tra', 'Education: Training', 0)

insert into Category (CategoryID, Name, IsAdult) values ('gam', 'Games & Hobbies', 0)
insert into Category (CategoryID, Name, IsAdult) values ('gam-aut', 'Games & Hobbies: Automotive', 0)
insert into Category (CategoryID, Name, IsAdult) values ('gam-avi', 'Games & Hobbies: Aviation', 0)
insert into Category (CategoryID, Name, IsAdult) values ('gam-hob', 'Games & Hobbies: Hobbies', 0)
insert into Category (CategoryID, Name, IsAdult) values ('gam-oth', 'Games & Hobbies: Other Games', 0)
insert into Category (CategoryID, Name, IsAdult) values ('gam-vid', 'Games & Hobbies: Video Games', 0)

insert into Category (CategoryID, Name, IsAdult) values ('govorg', 'Government & Organizations', 0)
insert into Category (CategoryID, Name, IsAdult) values ('govorg-loc', 'Government & Organizations: Local', 0)
insert into Category (CategoryID, Name, IsAdult) values ('govorg-nat', 'Government & Organizations: National', 0)
insert into Category (CategoryID, Name, IsAdult) values ('govorg-non', 'Government & Organizations: Non-Profit', 0)
insert into Category (CategoryID, Name, IsAdult) values ('govorg-reg', 'Government & Organizations: Regional', 0)

insert into Category (CategoryID, Name, IsAdult) values ('hea', 'Health', 0)
insert into Category (CategoryID, Name, IsAdult) values ('hea-alt', 'Health: Alternative Health', 0)
insert into Category (CategoryID, Name, IsAdult) values ('hea-fit', 'Health: Fitness & Nutrition', 0)
insert into Category (CategoryID, Name, IsAdult) values ('hea-sel', 'Health: Self-Help', 0)
insert into Category (CategoryID, Name, IsAdult) values ('hea-sex', 'Health: Sexuality', 0)

insert into Category (CategoryID, Name, IsAdult) values ('kids', 'Kids & Family', 0)

insert into Category (CategoryID, Name, IsAdult) values ('mus', 'Music', 0)

insert into Category (CategoryID, Name, IsAdult) values ('news', 'News & Politics', 0)

insert into Category (CategoryID, Name, IsAdult) values ('rel', 'Religion & Spirituality', 0)
insert into Category (CategoryID, Name, IsAdult) values ('rel-bud', 'Religion & Spirituality: Buddhism', 0)
insert into Category (CategoryID, Name, IsAdult) values ('rel-chr', 'Religion & Spirituality: Christianity', 0)
insert into Category (CategoryID, Name, IsAdult) values ('rel-hin', 'Religion & Spirituality: Hinduism', 0)
insert into Category (CategoryID, Name, IsAdult) values ('rel-isl', 'Religion & Spirituality: Islam', 0)
insert into Category (CategoryID, Name, IsAdult) values ('rel-jud', 'Religion & Spirituality: Judaism', 0)
insert into Category (CategoryID, Name, IsAdult) values ('rel-oth', 'Religion & Spirituality: Other', 0)
insert into Category (CategoryID, Name, IsAdult) values ('rel-spi', 'Religion & Spirituality: Spirituality', 0)

insert into Category (CategoryID, Name, IsAdult) values ('sci', 'Science & Medicine', 0)
insert into Category (CategoryID, Name, IsAdult) values ('sci-med', 'Science & Medicine: Medicine', 0)
insert into Category (CategoryID, Name, IsAdult) values ('sci-nat', 'Science & Medicine: Natural Sciences', 0)
insert into Category (CategoryID, Name, IsAdult) values ('sci-soc', 'Science & Medicine: Social Sciences', 0)

insert into Category (CategoryID, Name, IsAdult) values ('soccul-', 'Society & Culture', 0)
insert into Category (CategoryID, Name, IsAdult) values ('soccul-his', 'Society & Culture: History', 0)
insert into Category (CategoryID, Name, IsAdult) values ('soccul-per', 'Society & Culture: Personal Journals', 0)
insert into Category (CategoryID, Name, IsAdult) values ('soccul-phi', 'Society & Culture: Philosophy', 0)
insert into Category (CategoryID, Name, IsAdult) values ('soccul-tra', 'Society & Culture: Places & Travel', 0)

insert into Category (CategoryID, Name, IsAdult) values ('sporec', 'Sports & Recreation', 0)
insert into Category (CategoryID, Name, IsAdult) values ('sporec-ama', 'Sports & Recreation: Amateur', 0)
insert into Category (CategoryID, Name, IsAdult) values ('sporec-cohs', 'Sports & Recreation: College & High School', 0)
insert into Category (CategoryID, Name, IsAdult) values ('sporec-out', 'Sports & Recreation: Outdoor', 0)
insert into Category (CategoryID, Name, IsAdult) values ('sporec-pro', 'Sports & Recreation: Professional', 0)

insert into Category (CategoryID, Name, IsAdult) values ('tech', 'Technology', 0)
insert into Category (CategoryID, Name, IsAdult) values ('tech-gad', 'Technology: Gadgets', 0)
insert into Category (CategoryID, Name, IsAdult) values ('tech-news', 'Technology: Tech News', 0)
insert into Category (CategoryID, Name, IsAdult) values ('tech-pod', 'Technology: Podcasting', 0)
insert into Category (CategoryID, Name, IsAdult) values ('tech-how', 'Technology: Software How-To', 0)

insert into Category (CategoryID, Name, IsAdult) values ('tvfilm', 'TV & Film', 0)

insert into Category (CategoryID, Name, IsAdult) values ('adu', 'Adult', 1)
insert into Category (CategoryID, Name, IsAdult) values ('adu-gg', 'Adult: Girl - Girl', 1)
insert into Category (CategoryID, Name, IsAdult) values ('adu-mas', 'Adult: Masterbate', 1)

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
