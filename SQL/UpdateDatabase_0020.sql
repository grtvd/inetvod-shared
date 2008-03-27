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

BEGIN TRANSACTION
GO
ALTER TABLE dbo.Category
	DROP CONSTRAINT DF_Category_IsAdult
GO
CREATE TABLE dbo.Tmp_Category
	(
	CategoryID varchar(32) NOT NULL,
	Name varchar(64) NOT NULL,
	IsAdult bit NOT NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.Tmp_Category ADD CONSTRAINT
	DF_Category_IsAdult DEFAULT (0) FOR IsAdult
GO
IF EXISTS(SELECT * FROM dbo.Category)
	 EXEC('INSERT INTO dbo.Tmp_Category (CategoryID, Name, IsAdult)
		SELECT CategoryID, Name, IsAdult FROM dbo.Category WITH (HOLDLOCK TABLOCKX)')
GO
ALTER TABLE dbo.ShowCategory
	DROP CONSTRAINT FK_ShowCategory_Category
GO
DROP TABLE dbo.Category
GO
EXECUTE sp_rename N'dbo.Tmp_Category', N'Category', 'OBJECT'
GO
ALTER TABLE dbo.Category ADD CONSTRAINT
	PK_Category PRIMARY KEY CLUSTERED
	(
	CategoryID
	) ON [PRIMARY]

GO
COMMIT
BEGIN TRANSACTION
GO
ALTER TABLE dbo.ShowCategory WITH NOCHECK ADD CONSTRAINT
	FK_ShowCategory_Category FOREIGN KEY
	(
	CategoryID
	) REFERENCES dbo.Category
	(
	CategoryID
	) ON UPDATE CASCADE

GO
COMMIT

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION

delete from Category where not CategoryID in (
'action',
'daily',
'featured',
'girlgirl',
'masterbate',
'scifi',
'sports',
'technology',
'thriller'
)
GO

--//////////////////////////////////////////////////////////////////////////////

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


update ShowCategory set CategoryID = 'tvfilm' where CategoryID = 'action' 
update ShowCategory set CategoryID = 'tvfilm' where CategoryID = 'scifi' 
update ShowCategory set CategoryID = 'tvfilm' where CategoryID = 'thriller' 

update ShowCategory set CategoryID = 'dai' where CategoryID = 'daily' 

update ShowCategory set CategoryID = 'sporec' where CategoryID = 'sports' 
update ShowCategory set CategoryID = 'tech' where CategoryID = 'technology' 

update ShowCategory set CategoryID = 'adu-gg' where CategoryID = 'girlgirl' 
update ShowCategory set CategoryID = 'adu-mas' where CategoryID = 'masterbate' 

delete from Category where CategoryID in (
'action',
'daily',
'girlgirl',
'masterbate',
'scifi',
'sports',
'technology',
'thriller'
)

COMMIT

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
