/**
 * Copyright © 2005-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.data;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;

import com.inetvod.common.core.CtorUtil;
import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Readable;
import com.inetvod.common.core.StrUtil;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.XmlDataReader;
import com.inetvod.common.core.XmlDataWriter;

public class ShowCostList extends ArrayList<ShowCost> implements Readable, Writeable
{
	/* Constants */
	public static final Constructor<ShowCostList> Ctor = CtorUtil.getCtorDefault(ShowCostList.class);
	public static final Constructor<ShowCostList> CtorDataReader = DataReader.getCtor(ShowCostList.class);
	private static final String ContainerFieldName = "data";

	/* Construction */
	public ShowCostList()
	{
	}

	public ShowCostList(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public static ShowCostList newInstanceFromXmlString(String xmlString) throws Exception
	{
		if(!StrUtil.hasLen(xmlString))
			return new ShowCostList();

		byte[] xmlBytes = xmlString.getBytes("UTF-8");
		ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlBytes);
		XmlDataReader reader = new XmlDataReader(inputStream);
		return reader.readObject(ContainerFieldName, CtorDataReader);
	}

	public static String toXmlString(ShowCostList showCostList) throws Exception
	{
		if((showCostList == null) || showCostList.isEmpty())
			return null;

		StringWriter stringWriter = new StringWriter();
		new XmlDataWriter(new PrintWriter(stringWriter), "UTF-8").writeObject(ContainerFieldName, showCostList);
		return stringWriter.toString();
	}

	public void readFrom(DataReader reader) throws Exception
	{
		copy(reader.readList("ShowCost", Ctor, ShowCost.CtorDataReader));
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeList("ShowCost", this);
	}

	/* Item Methods */

	@SuppressWarnings({ "MethodOverloadsMethodOfSuperclass" })
	public int indexOf(ShowCost showCost)
	{
		for(int i = 0; i < size(); i++)
			if(get(i).equals(showCost))
				return i;

		return -1;
	}

	@SuppressWarnings({"MethodOverloadsMethodOfSuperclass"})
	public ShowCost get(ShowCost showCost) throws Exception
	{
		int pos = indexOf(showCost);
		if(pos >= 0)
			return get(pos);

		throw new Exception("ShowCost not found");
	}

	public ShowCost find(ShowCost showCost)
	{
		int pos = indexOf(showCost);
		if(pos >= 0)
			return get(pos);

		return null;
	}

	public void copy(ShowCostList showCostList)
	{
		clear();
		if(showCostList != null)
			addAll(showCostList);
	}

	public void merge(ShowCostList showCostList)
	{
		for(ShowCost showCost : showCostList)
		{
			if(find(showCost) == null)
				add(showCost);
		}
	}

	public void sort()
	{
		Collections.sort(this, new ShowCostComparator());
	}
}
