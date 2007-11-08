/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.Readable;
import com.inetvod.playerClient.rqdata.Category;
import com.inetvod.playerClient.rqdata.CategoryList;
import com.inetvod.playerClient.rqdata.Provider;
import com.inetvod.playerClient.rqdata.ProviderList;
import com.inetvod.playerClient.rqdata.Rating;
import com.inetvod.playerClient.rqdata.RatingList;

public class SystemDataResp implements Readable
{
	/* Fields */
	private ProviderList fProviderList;
	private CategoryList fCategoryList;
	private RatingList fRatingList;

	/* Getters and Setters */
	public ProviderList getProviderList() { return fProviderList; }
	public CategoryList getCategoryList() { return fCategoryList; }
	public RatingList getRatingList() { return fRatingList; }

	/* Construction */
	public SystemDataResp(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fProviderList = reader.readList("Provider", ProviderList.Ctor, Provider.CtorDataReader);
		fCategoryList = reader.readList("Category", CategoryList.Ctor, Category.CtorDataReader);
		fRatingList = reader.readList("Rating", RatingList.Ctor, Rating.CtorDataReader);
	}
}
