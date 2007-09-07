/**
 * Copyright © 2005-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.data;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import com.inetvod.common.core.CompUtil;
import com.inetvod.common.core.CtorUtil;

public class ShowFormatList extends ArrayList<ShowFormat>
{
	public static final Constructor<ShowFormatList> Ctor = CtorUtil.getCtorDefault(ShowFormatList.class);

	public void copy(ShowFormatList showFormatList)
	{
		clear();
		addAll(showFormatList);
	}

	public ShowFormatList findByMediaEncoding(MediaEncoding mediaEncoding)
	{
		ShowFormatList showFormatList = new ShowFormatList();

		for(ShowFormat showFormat : this)
			if(CompUtil.areEqual(mediaEncoding, showFormat.getMediaEncoding()))
				showFormatList.add(showFormat);

		return showFormatList;
	}
}
