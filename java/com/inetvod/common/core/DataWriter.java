/**
 * Copyright © 2004-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import java.util.Date;
import java.util.List;

public abstract class DataWriter
{
	/**
	 * Write a Byte
	 */
	public abstract void writeByte(String fieldName, Byte data) throws Exception;

	/**
	 * Write a byte value
	 */
	public void writeByteValue(String fieldName, byte data) throws Exception
	{
		writeByte(fieldName, data);
	}

	/**
	 * Write a Short
	 */
	public abstract void writeShort(String fieldName, Short data) throws Exception;

	/**
	 * Write a short value
	 */
	public void writeShortValue(String fieldName, short data) throws Exception
	{
		writeShort(fieldName, data);
	}

	/**
	 * Write an Integer
	 */
	public abstract void writeInt(String fieldName, Integer data) throws Exception;

	/**
	 * Write an int value
	 */
	public void writeIntValue(String fieldName, int data) throws Exception
	{
		writeInt(fieldName, data);
	}

	/**
	 * Write a Long
	 */
	public abstract void writeLong(String fieldName, Long data) throws Exception;

	/**
	 * Write a Float
	 */
	public abstract void writeFloat(String fieldName, Float data) throws Exception;

	/**
	 * Write a Double
	 */
	public abstract void writeDouble(String fieldName, Double data) throws Exception;

	/**
	 * Write a String
	 */
	public abstract void writeString(String fieldName, String data, int maxLength) throws Exception;

	/**
	 * Write a date, no Time component
	 */
	public abstract void writeDate(String fieldName, Date data) throws Exception;

	/**
	 * Write a Date with a Time component
	 */
	public abstract void writeDateTime(String fieldName, Date data) throws Exception;

	/**
	 * Write a Boolean
	 */
	public abstract void writeBoolean(String fieldName, Boolean data) throws Exception;

	/**
	 * Write a boolean value
	 */
	public void writeBooleanValue(String fieldName, boolean data) throws Exception
	{
		writeBoolean(fieldName, data);
	}

	/**
	 * Write a complex Object
	 */
	public abstract void writeObject(String fieldName, Writeable data) throws Exception;

	/**
	 * Write a list of complex Objects
	 */
	public abstract void writeList(String fieldName, List data) throws Exception;

	/**
	 * Write a list of Strings (or non-complex items than can be converted to a sting)
	 */
	public abstract void writeStringList(String fieldName, List data, int maxLength) throws Exception;

	/**
	 * Write a DataID Object
	 */
	public abstract void writeDataID(String fieldName, DataID data, int maxLength) throws Exception;
}
