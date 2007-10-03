/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.contmgr.mgr;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;

import com.inetvod.common.core.Logger;
import com.inetvod.common.core.StrUtil;
import com.inetvod.common.core.StreamUtil;
import com.inetvod.contmgr.data.Info;
import com.inetvod.contmgr.data.VideoCodec;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.HeadMethod;

public class ContentManager
{
	/* Constants*/
	private static final String GET_CONTENT_FUNC = "gc";
	private static final String GET_CONTENT_STATS_FUNC = "gcs";

	/* Fields */
	private static String fBaseServiceURL;

	/* Getters and Setters */

	/* Construction */
	public static void initialize(String baseServiceURL) throws Exception
	{
		if(!StrUtil.hasLen(baseServiceURL))
			throw new Exception(String.format("baseServiceURL !hasLen"));

		fBaseServiceURL = baseServiceURL;
		if(!fBaseServiceURL.endsWith("/"))
			fBaseServiceURL += "/";
	}

	/* Implementation */
	private static String buildServiceURL(String func, String sourceURL, VideoCodec needVideoCodec) throws Exception
	{
		if(!StrUtil.hasLen(sourceURL))
			throw new Exception("sourceURL !hasLen");

		StringBuilder url = new StringBuilder(String.format("%s%s?u=%s", fBaseServiceURL, func,
			URLEncoder.encode(sourceURL, "UTF-8")));
		if(needVideoCodec != null)
			url.append(String.format("&v=%s", needVideoCodec.toString()));

		return url.toString();
	}

	public static Info getStats(String sourceURL, VideoCodec needVideoCodec)
	{
		try
		{
			String url = buildServiceURL(GET_CONTENT_STATS_FUNC, sourceURL, needVideoCodec);

			// Send HTTP request to server
			HttpClient httpClient = new HttpClient();
			//TODO httpClient.getParams().setParameter("http.socket.timeout", TimeoutMillis);
			GetMethod getMethod = new GetMethod(url);
			getMethod.setFollowRedirects(true);

			try
			{
				int rc = httpClient.executeMethod(getMethod);
				if(rc != HttpStatus.SC_OK)
				{
					if(rc != HttpStatus.SC_NOT_FOUND)
						Logger.logWarn(ContentManager.class, "getStats", String.format("Bad result(%d) from url(%s)",
							rc, url));
					return null;
				}

				return Info.readFromStream(getMethod.getResponseBodyAsStream());

				//File file = File.createTempFile("video", FileExtension.xml.toString(), fContentDir);
				//InputStream responseStream = getMethod.getResponseBodyAsStream();
				//StreamUtil.streamToFile(responseStream, file.getAbsolutePath());
			}
			finally
			{
				getMethod.releaseConnection();
			}
		}
		catch(Exception e)
		{
			Logger.logErr(ContentManager.class, "getStats", e);
		}

		return null;
	}

	public static boolean checkContent(String sourceURL)
	{
		try
		{
			// Send HTTP request to server
			HttpClient httpClient = new HttpClient();
			//TODO httpClient.getParams().setParameter("http.socket.timeout", TimeoutMillis);
			HeadMethod headMethod = new HeadMethod(sourceURL);
			headMethod.setFollowRedirects(true);

			try
			{
				int rc = httpClient.executeMethod(headMethod);
				if(rc != HttpStatus.SC_OK)
				{
					if(rc != HttpStatus.SC_NOT_FOUND)
						Logger.logInfo(ContentManager.class, "checkContent", String.format(
							"Bad result(%d) from url(%s)", rc, sourceURL));
					return false;
				}

				return true;
			}
			finally
			{
				headMethod.releaseConnection();
			}
		}
		catch(Exception e)
		{
			Logger.logInfo(ContentManager.class, "checkContent", e);
		}

		return false;
	}

	/**
	 * Currently for testing only
	 */
	public static boolean getContent(String sourceURL, VideoCodec needVideoCodec, File file) throws Exception
	{
		return downloadFile(buildServiceURL(GET_CONTENT_FUNC, sourceURL, needVideoCodec), file);
	}

	private static boolean downloadFile(String sourceURL, File file) throws Exception
	{
		try
		{
			file.getParentFile().mkdirs();
			if(file.exists())
				file.delete();

			Logger.logInfo(ContentManager.class, "downloadFile", String.format("Downloading '%s' to '%s'", sourceURL,
				file.getAbsolutePath()));

			// Send HTTP request to server
			HttpClient httpClient = new HttpClient();
			//TODO httpClient.getParams().setParameter("http.socket.timeout", TimeoutMillis);
			GetMethod getMethod = new GetMethod(sourceURL);
			getMethod.setFollowRedirects(true);

			try
			{
				int rc = httpClient.executeMethod(getMethod);
				if(rc != HttpStatus.SC_OK)
				{
					if(rc != HttpStatus.SC_NOT_FOUND)
						Logger.logWarn(ContentManager.class, "downloadFile", String.format(
							"Bad result(%d) from url(%s)", rc, sourceURL));
					return false;
				}

				InputStream responseStream = getMethod.getResponseBodyAsStream();
				StreamUtil.streamToFile(responseStream, file.getAbsolutePath());
				return true;
			}
			finally
			{
				getMethod.releaseConnection();
			}
		}
		catch(Exception e)
		{
			Logger.logErr(ContentManager.class, "downloadFile", e);
			throw e;
		}
	}
}
