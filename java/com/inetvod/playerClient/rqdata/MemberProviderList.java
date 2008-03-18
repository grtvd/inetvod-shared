/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.rqdata;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import com.inetvod.common.core.CtorUtil;

public class MemberProviderList extends ArrayList<MemberProvider>
{
	/* Constants */
	public static final Constructor<MemberProviderList> Ctor = CtorUtil.getCtorDefault(MemberProviderList.class);
}
