/**
 * Copyright © 2004-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.List;

public abstract class DataReader
{
	public static <T> Constructor<T> getCtor(Class<T> cl)
	{
		try
		{
			return cl.getConstructor(DataReader.class);
		}
		catch(Exception ignore)
		{
		}

		return null;
	}

	/**
	 * Read a Byte.
	 * @return may return null
	 */
	public abstract Byte readByte(String fieldName) throws Exception;

	/**
	 * Read a Short.
	 * @return may return null
	 */
	public abstract Short readShort(String fieldName) throws Exception;

	/**
	 * Read a short value.
	 * @return will throw exception on null value
	 */
	public short readShortValue(String fieldName) throws Exception
	{
		Short value = readShort(fieldName);

		if(value == null)
			throw new Exception("value is null");

		return value;
	}

	/**
	 * Read a Integer.
	 * @return may return null
	 */
	public abstract Integer readInt(String fieldName) throws Exception;

	/**
	 * Read an int value.
	 * @return will throw exception on null value
	 */
	public int readIntValue(String fieldName) throws Exception
	{
		Integer value = readInt(fieldName);

		if(value == null)
			throw new Exception("value is null");

		return value;
	}

	/**
	 * Read a Long.
	 * @return may return null
	 */
	public abstract Long readLong(String fieldName) throws Exception;

	/**
	 * Read a Long.
	 * @return will throw exception on null value
	 */
	public long readLongValue(String fieldName) throws Exception
	{
		Long value = readLong(fieldName);

		if(value == null)
			throw new Exception("value is null");

		return value;
	}

	/**
	 * Read a Float.
	 * @return may return null
	 */
	public abstract Float readFloat(String fieldName) throws Exception;

	/**
	 * Read a Double.
	 * @return may return null
	 */
	public abstract Double readDouble(String fieldName) throws Exception;

	/**
	 * Read a String.
	 * @return may return null
	 */
	public abstract String readString(String fieldName, int maxLength) throws Exception;

	/**
	 * Read a Date, no Time component.
	 * @return may return null
	 */
	public abstract Date readDate(String fieldName) throws Exception;

	/**
	 * Read a Date with a Time compnent.
	 * @return may return null
	 */
	public abstract Date readDateTime(String fieldName) throws Exception;

	/**
	 * Read a Boolean.
	 * @return may return null
	 */
	public abstract Boolean readBoolean(String fieldName) throws Exception;

	/**
	 * Read a boolean value.
	 * @return will throw exception on null value
	 */
	public boolean readBooleanValue(String fieldName) throws Exception
	{
		Boolean value = readBoolean(fieldName);

		if(value == null)
			throw new Exception("value is null");

		return value;
	}

	/**
	 * Read a boolean value.
	 * @return will return defaultValue on on null value
	 */
	public boolean readBooleanValue(String fieldName, boolean defaultValue) throws Exception
	{
		Boolean value = readBoolean(fieldName);

		if(value == null)
			return defaultValue;

		return value;
	}

	/**
	 * Read an Object.
	 * @return may return null
	 */
	//public abstract Readable readObject(String fieldName, Constructor ctorDataReader) throws Exception;
	public abstract <T extends Readable> T readObject(String fieldName, Constructor<T> ctorDataReader) throws Exception;

	/**
	 * Read a list of complex Objects.
	 * @return will never return null, may return an empty list
	 */
	//public abstract List readList(String fieldName, Constructor listCtor, Constructor itemCtorDataReader) throws Exception;
	public abstract <T, L extends List<T>> L readList(String fieldName, Constructor<L> listCtor, Constructor<T> itemCtorDataReader) throws Exception;

	/**
	 * Read a list of Strings (or non-complex items than can be constructed from a sting).
	 * @return will never return null, may return an empty list
	 */
	//public abstract List readStringList(String fieldName, int maxLength, Constructor listCtor, Constructor itemCtorString) throws Exception;
	public abstract <T, L extends List<T>> L readStringList(String fieldName, int maxLength, Constructor<L> listCtor,
		Constructor<T> itemCtorString) throws Exception;

	/**
	 * Read a DataID object.
	 * @return may return null
	 */
	public abstract <T extends DataID> T readDataID(String fieldName, int maxLength, Constructor<T> ctorString) throws Exception;
}
