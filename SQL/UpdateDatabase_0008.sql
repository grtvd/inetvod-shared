--//////////////////////////////////////////////////////////////////////////////
-- Copyright � 2005-2006 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

use [iNetVOD]
GO

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION

--//////////////////////////////////////////////////////////////////////////////

delete from RentedShow
delete from ShowProvider
delete from ShowCategory
delete from Show
GO

--//////////////////////////////////////////////////////////////////////////////

COMMIT

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
