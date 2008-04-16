/**
 * Copyright © 2004-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.dbdata;

import java.util.ArrayList;

import com.inetvod.common.data.RatingIDList;

public class RatingList extends ArrayList<Rating>
{
	/* Construction */
	public static RatingList find() throws Exception
	{
		return Rating.getDatabaseAdaptor().selectManyByProc("Rating_GetAll", null);
	}

	/* Implementation */
	public RatingIDList getIDList()
	{
		RatingIDList ratingIDList = new RatingIDList();

		for(Rating rating : this)
			ratingIDList.add(rating.getRatingID());

		return ratingIDList;
	}

}
