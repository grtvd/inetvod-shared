/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.rqdata;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import com.inetvod.common.core.CtorUtil;

public class CategoryList extends ArrayList<Category>
{
	/* Constants */
	public static final Constructor<CategoryList> Ctor = CtorUtil.getCtorDefault(CategoryList.class);
}
