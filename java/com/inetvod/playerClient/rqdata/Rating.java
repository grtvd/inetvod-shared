/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerClient.rqdata;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.Readable;
import com.inetvod.common.data.RatingID;

public class Rating implements Readable
{
	/* Constants */
	public static Constructor<Rating> CtorDataReader = DataReader.getCtor(Rating.class);
	private static final int NameMaxLength = 64;

	/* Fields */
	private RatingID fRatingID;
	private String fName;

	/* Getters and Setters */
	public RatingID getRatingID() { return fRatingID; }
	public String getName() { return fName; }

	/* Construction */
	public Rating(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fRatingID = reader.readDataID("RatingID", RatingID.MaxLength, RatingID.CtorString);
		fName = reader.readString("Name", NameMaxLength);
	}
}
