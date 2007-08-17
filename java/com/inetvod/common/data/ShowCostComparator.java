/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.data;

import java.util.Comparator;

import com.inetvod.common.core.CompUtil;

public class ShowCostComparator implements Comparator<ShowCost>
{
	/* Implementation */
	public int compare(ShowCost o1, ShowCost o2)
	{
		return CompUtil.compare(o1, o2);
	}
}
