/**
 * Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import java.util.ArrayList;
import java.lang.reflect.Constructor;

public class NameValueList extends ArrayList<NameValue>
{
	/* Constants */
	public static final Constructor<NameValueList> Ctor = CtorUtil.getCtorDefault(NameValueList.class);

	/* Implementation */
	public NameValueHashMap getHashMap()
	{
		NameValueHashMap hashMap = new NameValueHashMap();

		for(NameValue nameValue : this)
			hashMap.put(nameValue.getName(), nameValue.getValue());

		return hashMap;
	}
}
