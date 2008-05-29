--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

--exec sp_renamedb 'iNetVOD', 'iNetVOD_Old_1'

use [iNetVOD]
GO

--//////////////////////////////////////////////////////////////////////////////

update MemberLogon set
	Password = 'W6ph5Mm5Pz8GgiULbPgzG37mj9g=', -- password
	PIN = 'fEqNCco3Yq9h5ZUglD3CZJT4lBs=', -- 123456
	SecretQuestion = '/H0RSCFU/99jY3FEVORunL2m4UKKAZhogOJ/lTsKdP5GzOdiYJPXqg==', -- What is your father's middle name?
	SecretAnswer = '7aGC9Dsx7Qw=' -- Carol

update MemberAccount set
	CreditCard_NameOnCC = '0NTNt8OaPazFCPLWkwRbTA==', -- John Doe
	CreditCard_CCNumber = 'j5SwPoULtp5hP6wIroz99e/QWbaORbco', -- 1234567890123456
	CreditCard_CCSIC = 'doqjv+pTttA=', -- 1234
	CreditCard_ExpireDate = 'x2Mmpwzf8Dk=' -- 01/2020
	where (CreditCard_NameOnCC is not null) or (CreditCard_CCNumber is not null)
	or (CreditCard_CCSIC is not null) or (CreditCard_ExpireDate is not null)

update MemberPrefs set
	AdultPIN = 'fEqNCco3Yq9h5ZUglD3CZJT4lBs=' -- 123456
	where AdultPIN is not null

update ProviderConnection set
	AdminUserID = 'kQF6yJ5+Wtw=',
	AdminPassword = 'v0AMGg2jsJlmVT793y6vlQ=='
	where ProviderConnectionType = 'ProviderAPI' and (ProviderID = 'moviesmovies' 
	or ProviderID = 'internetvideos' or ProviderID = 'abbywinters')


--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
