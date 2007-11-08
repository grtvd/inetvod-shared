/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.rqdata;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import com.inetvod.common.core.CtorUtil;

public class RatingList extends ArrayList<Rating>
{
	/* Constants */
	public static final Constructor<RatingList> Ctor = CtorUtil.getCtorDefault(RatingList.class);
}
