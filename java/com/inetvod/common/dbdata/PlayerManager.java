/**
 * Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.dbdata;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.Readable;
import com.inetvod.common.core.XmlDataReader;
import com.inetvod.common.data.PlayerID;

public class PlayerManager implements Readable
{
	/* Constants */
	public static final Constructor<PlayerManager> CtorDataReader = DataReader.getCtor(PlayerManager.class);
//	private static final PlayerID MediaCenterEditionPlayerID = new PlayerID("1dbdacca-dce6-4ae5-b122-5fb4113aafc3");
//	private static final PlayerID MCEDownloadServicePlayerID = new PlayerID("989e736b-f59e-4092-8f4a-a1e8164f1a97");
//	private static final PlayerID MediaPlayerDemoPlayerID = new PlayerID("5e668926-070f-4097-8865-4949dc57e08b");
//	private static final PlayerID WindowsWidgetPlayerID = new PlayerID("2e1400e4-34cb-4e8a-baae-04b510c1ce5a");

//	private static final String[] MediaCenterEditionValidMimeTypeList = new String[] { MediaMIME.video_x_ms_wmv,
//		MediaMIME.audio_mpeg };
//	private static final String[] MCEDownloadServiceValidMimeTypeList = new String[] { MediaMIME.video_x_ms_wmv,
//		MediaMIME.video_mp4, MediaMIME.video_mov, MediaMIME.video_quicktime, MediaMIME.audio_mpeg };
//	private static final String[] MediaPlayerDemoValidMimeTypeList = new String[] { MediaMIME.video_x_ms_wmv,
//		MediaMIME.video_mp4, MediaMIME.video_mov, MediaMIME.video_quicktime, MediaMIME.audio_mpeg };
//	private static final String[] WindowsWidgetValidMimeTypeList = new String[] { MediaMIME.video_x_ms_wmv,
//		MediaMIME.video_mp4, MediaMIME.video_mov, MediaMIME.video_quicktime, MediaMIME.audio_mpeg };

	/* Fields */
	private static PlayerManager fThePlayerManager;
	private HashMap<String, Player> fPlayerHashMap;

	/* Getters and Setters */
	public static PlayerManager getThe() { return fThePlayerManager; }

	/* Construction */
	public PlayerManager(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public static void load(String playerDataXml) throws Exception
	{
		FileInputStream inputStream = new FileInputStream(new File(playerDataXml));
		XmlDataReader dataReader = new XmlDataReader(inputStream);

		fThePlayerManager = dataReader.readObject("PlayerData", CtorDataReader);
	}

	public void readFrom(DataReader reader) throws Exception
	{
		PlayerList playerList = reader.readList("Player", PlayerList.Ctor, Player.CtorDataReader);

		fPlayerHashMap = new HashMap<String, Player>();
		for(Player player : playerList)
			fPlayerHashMap.put(player.getPlayerID().toString(), player);
	}

	public PlayerID findPlayerIDFromPlayer(com.inetvod.player.rqdata.Player rqPlayer)
	{
		if(rqPlayer == null)
			return null;

		for(Player player : fPlayerHashMap.values())
			if(player.getManufacturerID().equals(rqPlayer.getManufacturerID())
					&& player.getModelNo().equals(rqPlayer.getModelNo()))
				return player.getPlayerID();

//		if("mce".equals(modelNo))
//			return MediaCenterEditionPlayerID;
//		if("mce-dls".equals(modelNo))
//			return MCEDownloadServicePlayerID;
//		if("mpdemo".equals(modelNo))
//			return MediaPlayerDemoPlayerID;
//		if("winwid".equals(modelNo))
//			return WindowsWidgetPlayerID;

		return null;
	}

	public Player getPlayer(PlayerID playerID)
	{
		if(playerID != null)
		{
			Player player = fPlayerHashMap.get(playerID.toString());
			if(player != null)
				return player;
		}

		throw new IllegalArgumentException(String.format("Unknown PlayerID(%s)", (playerID != null) ? playerID.toString() : ""));
	}

	/*
	public static String[] getValidMimeTypeListForPlayer(PlayerID playerID) throws Exception
	{
		if(MediaCenterEditionPlayerID.equals(playerID))
		{
			return MediaCenterEditionValidMimeTypeList;
		}
		else if(MCEDownloadServicePlayerID.equals(playerID))
		{
			return MCEDownloadServiceValidMimeTypeList;
		}
		else if(MediaPlayerDemoPlayerID.equals(playerID))
		{
			return MediaPlayerDemoValidMimeTypeList;
		}
		else if(WindowsWidgetPlayerID.equals(playerID))
		{
			return WindowsWidgetValidMimeTypeList;
		}
		else
			throw new Exception(String.format("Unknown PlayerID(%s)", (playerID != null) ? playerID.toString() : ""));
	}
	*/
}