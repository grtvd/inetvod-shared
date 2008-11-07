/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

public class LockableRandomAccessFile
{
	/* Constants */
	private static int OpenTries = 5;
	private static int RetryWaitMillis = 1000;

	/* Fields */
	private File fFile;
	private RandomAccessFile fRandomAccessFile;
	private FileLock fFileLock;
	private int fOpenTries;
	private int fRetryWaitMillis;

	/* Getters and Setters */

	/* Construction */
	private LockableRandomAccessFile(File file, Integer openTries, Integer retryWaitMillis)
	{
		fFile = file;
		fOpenTries = (openTries != null) ? openTries : OpenTries;
		fRetryWaitMillis = (retryWaitMillis != null) ? retryWaitMillis : RetryWaitMillis;
	}

	public static LockableRandomAccessFile newInstance(File file, Integer openTries, Integer retryWaitMillis)
		throws Exception
	{
		LockableRandomAccessFile lockableRandomAccessFile = new LockableRandomAccessFile(file, openTries, retryWaitMillis);
		if(lockableRandomAccessFile.open())
			return lockableRandomAccessFile;
		return null;
	}

	public static LockableRandomAccessFile newInstance(File file) throws Exception
	{
		return newInstance(file, null, null);
	}

	/* Implementation */
	private boolean open() throws IOException, InterruptedException
	{
		if(openFile())
			if(lockFile())
				return true;

		close();
		return false;
	}

	public void close()
	{
		releaseLock();

		if(fRandomAccessFile != null)
		{
			try { fRandomAccessFile.close(); } catch(IOException ignore) {}
			fRandomAccessFile = null;
		}
	}

	private boolean openFile() throws InterruptedException
	{
		close();

		for(int i = 0; i < fOpenTries; i++)
		{
			try
			{
				fRandomAccessFile = new RandomAccessFile(fFile, "rw");
				return true;
			}
			catch(FileNotFoundException ignore)
			{
				// a locked file will raise this same exception
			}

			Thread.sleep(fRetryWaitMillis);
		}

		return false;
	}

	private boolean lockFile() throws IOException, InterruptedException
	{
		releaseLock();

		if(fRandomAccessFile == null)
			return false;

		for(int i = 0; i < fOpenTries; i++)
		{
			fFileLock = fRandomAccessFile.getChannel().tryLock(0, Long.MAX_VALUE, false);
			if (fFileLock != null)
				return true;

			//file locked by another process, do nothing

			Thread.sleep(fRetryWaitMillis);
		}

		return false;
	}

	private void releaseLock()
	{
		if(fFileLock != null)
		{
			try { fFileLock.release(); } catch(IOException ignore) {}
			fFileLock = null;
		}
	}

	public InputStream readFile() throws Exception
	{
		fRandomAccessFile.seek(0);
		return StreamUtil.streamCopyToMemory(fRandomAccessFile);
	}

	public void writeFile(InputStream inputStream) throws Exception
	{
		fRandomAccessFile.seek(0);
		fRandomAccessFile.setLength(0);
		StreamUtil.streamCopy(inputStream, fRandomAccessFile, false);
	}

	public void appendFile(InputStream inputStream) throws Exception
	{
		fRandomAccessFile.seek(fRandomAccessFile.length());
		StreamUtil.streamCopy(inputStream, fRandomAccessFile, false);
	}
}
