/**
 * Copyright © 2005-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

public class CompUtil
{
	public static <T> int compare(Comparable<T> lhs, T rhs)
	{
		if((lhs == null) && (rhs == null))
			return 0;
		if((lhs == null) || (rhs == null))
			return (lhs == null) ? -1 : 1;

		return lhs.compareTo(rhs);
	}

	public static <T> boolean areEqual(T lhs, T rhs)
	{
		if((lhs == null) && (rhs == null))
			return true;
		if((lhs == null) || (rhs == null))
			return false;
		return lhs.equals(rhs);
	}
}
