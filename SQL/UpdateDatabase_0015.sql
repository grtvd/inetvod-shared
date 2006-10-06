--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
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
EXECUTE sp_rename N'dbo.MemberProvider.EncryptedUserName', N'Tmp_UserID', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.MemberProvider.EncryptedPassword', N'Tmp_Password_1', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.MemberProvider.Tmp_UserID', N'UserID', 'COLUMN'
GO
EXECUTE sp_rename N'dbo.MemberProvider.Tmp_Password_1', N'Password', 'COLUMN'
GO
COMMIT

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
