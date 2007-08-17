/**
 * Copyright © 2004-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

public class StringID extends DataID implements Comparable<StringID>
{
	protected final String fValue;

	public StringID(String value)
	{
		if ((value == null) || (value.length() == 0))
			throw new IllegalArgumentException("value is undefined");

		fValue = value;
	}

	public String toString()
	{
		return fValue;
	}

	@Override
	public boolean equals(Object o)
	{
		if ((o == null) || !(o instanceof StringID))
			return false;

		return fValue.equals(((StringID)o).fValue);
	}

	@Override
	public int hashCode()
	{
		return fValue.hashCode();
	}

	public int compareTo(StringID o)
	{
		if(o == null)
			return 1;

		return fValue.compareTo(o.fValue);
	}
}
