/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.rqdata;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.data.CategoryID;

public class Category
{
	/* Constants */
	public static Constructor<Category> CtorDataReader = DataReader.getCtor(Category.class);
	private static final int NameMaxLength = 64;

	/* Fields */
	private CategoryID fCategoryID;
	private String fName;

	/* Getters and Setters */
	public CategoryID getCategoryID() { return fCategoryID; }
	public String getName() { return fName; }

	/* Construction */
	public Category(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fCategoryID = reader.readDataID("CategoryID", CategoryID.MaxLength, CategoryID.CtorString);
		fName = reader.readString("Name", NameMaxLength);
	}
}
