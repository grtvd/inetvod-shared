--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
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

exec Member_Insert '3d5ef800-ac2c-4271-813e-6fc1cbd18857', 'Guest', 'Account'
GO

SET IDENTITY_INSERT MemberLogon ON

insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion, LogonFailedAt, LogonFailedCount, LogonDisabled)
values('3d5ef800-ac2c-4271-813e-6fc1cbd18857', 'GUEST', 'guest', 'aNVXb4tZEDEi+QzHM8qO5cBSLrk=', 1, 'Fz13RHCV1W3YudkDX1CnTcUThQY=', 'MiTcDA4MBwfFfWyCuSzc2Kx0cV2yS1XwJFo7myghL2zJrGv4SOSE2Q==', 'Ko1rL/anv8A=', '11/1/2007', '1.0.0', null, 0, 0)
GO

SET IDENTITY_INSERT MemberLogon OFF

exec MemberPrefs_Insert '3d5ef800-ac2c-4271-813e-6fc1cbd18857', 'Never', null, 'notrated,g,r,tvy,tvpg,pg,tvy7,tv14,pg13,tvy7fv,tvma,tvg', 1, 1, '1500K'
GO

COMMIT

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
