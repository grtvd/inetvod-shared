/**
 * Copyright © 2004-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.data;

import com.inetvod.common.core.CompUtil;

public class ShowCostType implements Comparable<ShowCostType>
{
	public static final int MaxLength = 32;

	public static final ShowCostType Free = new ShowCostType("Free", 0);
	public static final ShowCostType Subscription = new ShowCostType("Subscription", 1);
	public static final ShowCostType PayPerView = new ShowCostType("PayPerView", 2);

	private final String fValue;
	private final int fOrder;

	private ShowCostType(String value, int order)
	{
		fValue = value;
		fOrder = order;
	}

	public String toString()
	{
		return fValue;
	}

	public static ShowCostType convertFromString(String value)
	{
		if((value == null) || (value.length() == 0))
			return null;

		if(Free.fValue.equals(value))
			return Free;
		if(Subscription.fValue.equals(value))
			return Subscription;
		if(PayPerView.fValue.equals(value))
			return PayPerView;

		throw new IllegalArgumentException("bad value(" + value + ")");
	}

	public static String convertToString(ShowCostType value)
	{
		if(value == null)
			return null;
		return value.toString();
	}

	public int compareTo(ShowCostType o)
	{
		if(o == null)
			return 1;

		return CompUtil.compare(fOrder, o.fOrder);
	}
}
