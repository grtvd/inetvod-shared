/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.rqdata;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import com.inetvod.common.core.CtorUtil;

public class ProviderList extends ArrayList<Provider>
{
	/* Constants */
	public static final Constructor<ProviderList> Ctor = CtorUtil.getCtorDefault(ProviderList.class);
}
