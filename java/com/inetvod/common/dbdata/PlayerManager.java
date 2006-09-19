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
import com.inetvod.common.core.StrUtil;
import com.inetvod.common.core.XmlDataReader;
import com.inetvod.common.data.ManufacturerID;
import com.inetvod.common.data.PlayerID;

public class PlayerManager implements Readable
{
	/* Constants */
	public static final Constructor<PlayerManager> CtorDataReader = DataReader.getCtor(PlayerManager.class);

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

	public PlayerID findPlayerIDFromManufacturerIDModelNo(ManufacturerID manufacturerID, String modelNo)
	{
		if((manufacturerID == null) || !StrUtil.hasLen(modelNo))
			return null;

		for(Player player : fPlayerHashMap.values())
			if(player.getManufacturerID().equals(manufacturerID)
					&& player.getModelNo().equals(modelNo))
				return player.getPlayerID();

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
}