/**
 * Copyright © 2004-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.data;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;

import com.inetvod.common.core.CtorUtil;

public class RatingIDList extends ArrayList<RatingID>
{
	public static final Constructor<RatingIDList> Ctor = CtorUtil.getCtorDefault(RatingIDList.class);

	public void copy(RatingIDList ratingIDList)
	{
		clear();
		if(ratingIDList != null)
			addAll(ratingIDList);
	}

	public void and(RatingIDList ratingIDList)
	{
		HashSet<RatingID> ratingIDSet = ratingIDList.getHashSet();

		for(int i = size() - 1; i >= 0; i--)
		{
			if(!ratingIDSet.contains(get(i)))
				remove(i);
		}
	}

	public HashSet<RatingID> getHashSet()
	{
		HashSet<RatingID> hashSet = new HashSet<RatingID>();

		for(RatingID ratingID : this)
			hashSet.add(ratingID);

		return hashSet;
	}
}