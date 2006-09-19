/**
 * Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class ArrayListString extends ArrayList<String>
{
	public static final Constructor<ArrayListString> Ctor = CtorUtil.getCtorDefault(ArrayListString.class);
}
