/**
 * Copyright � 2006-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.dbdata;

import java.sql.Types;
import java.util.ArrayList;

import com.inetvod.common.data.ProviderConnectionType;
import com.inetvod.common.data.ProviderID;

public class ProviderConnectionList extends ArrayList<ProviderConnection>
{
	/* Construction */
	public static ProviderConnectionList findByProviderID(ProviderID providerID) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[1];

		params[0] = new DatabaseProcParam(Types.VARCHAR, providerID.toString());

		return ProviderConnection.getDatabaseAdaptor().selectManyByProc("ProviderConnection_GetByProviderID", params);
	}

	public static ProviderConnectionList findByProviderIDConnectionType(ProviderID providerID,
		ProviderConnectionType providerConnectionType) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[2];

		params[0] = new DatabaseProcParam(Types.VARCHAR, providerID.toString());
		params[1] = new DatabaseProcParam(Types.VARCHAR, providerConnectionType.toString());

		return ProviderConnection.getDatabaseAdaptor().selectManyByProc(
			"ProviderConnection_GetByProviderIDConnectionType", params);
	}

	public static ProviderConnectionList findByConnectionURL(String connectionURL) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[1];

		params[0] = new DatabaseProcParam(Types.VARCHAR, connectionURL);

		return ProviderConnection.getDatabaseAdaptor().selectManyByProc("ProviderConnection_GetByConnectionURL",
			params);
	}
}
