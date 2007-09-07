/**
 * Copyright © 2004-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.dbdata;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import com.inetvod.common.core.StrUtil;
import com.inetvod.common.data.CategoryID;
import com.inetvod.common.data.CategoryIDList;
import com.inetvod.common.data.MemberID;
import com.inetvod.common.data.ProviderConnectionID;
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.data.ProviderIDList;
import com.inetvod.common.data.ProviderShowID;
import com.inetvod.common.data.ShowAvail;
import com.inetvod.common.data.ShowCost;
import com.inetvod.common.data.ShowCostList;
import com.inetvod.common.data.ShowFormat;
import com.inetvod.common.data.ShowFormatList;
import com.inetvod.common.data.ShowID;
import com.inetvod.common.data.ShowProviderID;

public class ShowProviderList extends ArrayList<ShowProvider>
{
	/* Construction */
	public static ShowProviderList findByShowIDProviderID(ShowID showID, ProviderID providerID) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[2];

		params[0] = new DatabaseProcParam(Types.VARCHAR, showID.toString());
		params[1] = new DatabaseProcParam(Types.VARCHAR, providerID.toString());

		return ShowProvider.getDatabaseAdaptor().selectManyByProc("ShowProvider_GetByShowIDProviderID", params);
	}

	public static ShowProviderList findByShowIDProviderIDAvailable(ShowID showID, ProviderID providerID) throws Exception
	{
		//TODO move check for Available into stored procedure
		return findByShowIDProviderID(showID, providerID).findItemsByAvailable();
	}

	public static ShowProviderList findByShowID(ShowID showID) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[1];

		params[0] = new DatabaseProcParam(Types.VARCHAR, showID.toString());

		return ShowProvider.getDatabaseAdaptor().selectManyByProc("ShowProvider_GetByShowID", params);
	}

	public static ShowProviderList findByShowIDAvailable(ShowID showID) throws Exception
	{
		//TODO move check for Available into stored procedure
		return findByShowID(showID).findItemsByAvailable();
	}

	public static ShowProviderList findByShowName(String partialName) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[1];

		params[0] = new DatabaseProcParam(Types.VARCHAR, partialName);

		return ShowProvider.getDatabaseAdaptor().selectManyByProc("ShowProvider_Search", params);
	}

	public static ShowProviderList findByProviderIDList(ProviderIDList providerIDList) throws Exception
	{
		ShowProviderList showProviderList = new ShowProviderList();
		Iterator iter = providerIDList.iterator();
		ProviderID providerID;
		DatabaseProcParam params[];

		while(iter.hasNext())
		{
			providerID = (ProviderID)iter.next();
			params = new DatabaseProcParam[1];

			params[0] = new DatabaseProcParam(Types.VARCHAR, providerID.toString());

			showProviderList.merge(ShowProvider.getDatabaseAdaptor().selectManyByProc("ShowProvider_GetByProviderID", params));
		}

		return showProviderList;
	}

	public static ShowProviderList findByProviderConnectionIDProviderShowID(ProviderConnectionID providerConnectionID,
		ProviderShowID providerShowID) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[2];

		params[0] = new DatabaseProcParam(Types.VARCHAR, providerConnectionID.toString());
		params[1] = new DatabaseProcParam(Types.VARCHAR, providerShowID.toString());

		return ShowProvider.getDatabaseAdaptor().selectManyByProc("ShowProvider_GetByProviderConnectionIDProviderShowID", params);
	}

	private static ShowProviderList findByProviderConnectionIDShowAvail(ProviderConnectionID providerConnectionID,
		ShowAvail showAvail) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[2];

		params[0] = new DatabaseProcParam(Types.VARCHAR, providerConnectionID.toString());
		params[1] = new DatabaseProcParam(Types.VARCHAR, showAvail.toString());

		return ShowProvider.getDatabaseAdaptor().selectManyByProc("ShowProvider_GetByProviderConnectionIDShowAvail", params);
	}

	public static ShowProviderList findByProviderConnectionIDReconfirm(ProviderConnectionID providerConnectionID)
		throws Exception
	{
		return findByProviderConnectionIDShowAvail(providerConnectionID, ShowAvail.Reconfirming);
	}

	public static ShowProviderList findByProviderConnectionIDUnconfirm(ProviderConnectionID providerConnectionID)
		throws Exception
	{
		return findByProviderConnectionIDShowAvail(providerConnectionID, ShowAvail.Unconfirmed);
	}

	public static ShowProviderList findByCategoryIDList(CategoryIDList categoryIDList) throws Exception
	{
		ShowProviderList showProviderList = new ShowProviderList();
		Iterator iter = categoryIDList.iterator();
		CategoryID categoryID;
		DatabaseProcParam params[];

		while(iter.hasNext())
		{
			categoryID = (CategoryID)iter.next();
			params = new DatabaseProcParam[1];

			params[0] = new DatabaseProcParam(Types.VARCHAR, categoryID.toString());

			showProviderList.merge(ShowProvider.getDatabaseAdaptor().selectManyByProc("ShowProvider_GetByCategoryID", params));
		}

		return showProviderList;
	}

	public static ShowProviderList findByRentedShowMemberID(MemberID memberID) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[1];

		params[0] = new DatabaseProcParam(Types.VARCHAR, memberID.toString());

		return ShowProvider.getDatabaseAdaptor().selectManyByProc("ShowProvider_GetByRentedShowMemberID", params);
	}

	private static void updateShowAvailByProviderConnectionIDShowAvail(ProviderConnectionID providerConnectionID,
		ShowAvail searchShowAvail, ShowAvail setShowAvail) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[3];

		params[0] = new DatabaseProcParam(Types.VARCHAR, providerConnectionID.toString());
		params[1] = new DatabaseProcParam(Types.VARCHAR, ShowAvail.convertToString(searchShowAvail));
		params[2] = new DatabaseProcParam(Types.VARCHAR, setShowAvail.toString());

		ShowProvider.getDatabaseAdaptor().executeProc("ShowProvider_UpdateShowAvailByProviderConnectionIDShowAvail", params);
	}

	public static void markUnavailByProviderConnectionID(ProviderConnectionID providerConnectionID) throws Exception
	{
		updateShowAvailByProviderConnectionIDShowAvail(providerConnectionID, null, ShowAvail.Unavailable);
	}

	public static void markReconfirmByProviderConnectionIDAvail(ProviderConnectionID providerConnectionID)
		throws Exception
	{
		updateShowAvailByProviderConnectionIDShowAvail(providerConnectionID, ShowAvail.Available, ShowAvail.Reconfirming);
	}

	/* Item Methods */
	public ShowProvider getItem(int index)
	{
		return get(index);
	}

	@SuppressWarnings({"MethodOverloadsMethodOfSuperclass"})
	public int indexOf(ShowProviderID showProviderID)
	{
		for(int i = 0; i < size(); i++)
			if(getItem(i).getShowProviderID().equals(showProviderID))
				return i;

		return -1;
	}

	@SuppressWarnings({"MethodOverloadsMethodOfSuperclass"})
	public ShowProvider get(ShowProviderID showProviderID) throws Exception
	{
		int pos = indexOf(showProviderID);
		if(pos >= 0)
			return getItem(pos);

		throw new Exception("ShowProvider not found");
	}

	public ShowProvider find(ShowProviderID showProviderID)
	{
		int pos = indexOf(showProviderID);
		if(pos >= 0)
			return getItem(pos);

		return null;
	}

	public ShowProvider findByShowFormatMime(String showFormatMime)
	{
		if(!StrUtil.hasLen(showFormatMime))
			return null;

		for(ShowProvider showProvider : this)
		{
			if(showFormatMime.equals(showProvider.getShowFormatMime()))
				return showProvider;
		}

		return null;
	}

	public ShowProvider findByShowFormat(ShowFormat showFormat)
	{
		if(showFormat == null)
			return null;

		for(ShowProvider showProvider : this)
		{
			if(showFormat.equals(showProvider.getShowFormat()))
				return showProvider;
		}

		return null;
	}

	public ShowProvider findFirstByPlayer(Player player)
	{
		ShowFormatList showFormatList = combineShowFormatList();
		ShowFormat showFormat = player.supportsFormatFirst(showFormatList);
		if(showFormat != null)
			return findByShowFormat(showFormat);

		ArrayList<String> showFormatMimeList = combineShowFormatMime();
		String showFormatMime = player.supportsMimeFirst(showFormatMimeList);
		if(StrUtil.hasLen(showFormatMime))
			return findByShowFormatMime(showFormatMime);

		return null;
	}

	public ShowProvider findByShowURL(String showURL)
	{
		if(!StrUtil.hasLen(showURL))
			return null;

		for(ShowProvider showProvider : this)
		{
			if(showURL.equals(showProvider.getShowURL()))
				return showProvider;
		}

		return null;
	}

	/* Group Methods */

	public boolean isShowIDSame()
	{
		if(size() <= 1)
			return true;
		ShowID showID = get(0).getShowID();

		for(ShowProvider showProvider : this)
			if(!showID.equals(showProvider.getShowID()))
				return false;

		return true;
	}

	/**
	 * Returns a sub-set of items from this list that have the specified ShowID
	 * @return sub-set of ShowProviderList
	 */
	public ShowProviderList findItemsByShowID(ShowID showID)
	{
		ShowProviderList showProviderList = new ShowProviderList();
		ShowProvider showProvider;
		Iterator iter = iterator();

		while(iter.hasNext())
		{
			showProvider = (ShowProvider)iter.next();
			if(showProvider.getShowID().equals(showID))
				showProviderList.add(showProvider);
		}

		return showProviderList;
	}

	/**
	 * Returns a sub-set of items from this list that have the specified ProviderID
	 * @return sub-set of ShowProviderList
	 */
	public ShowProviderList findItemsByProviderID(ProviderID providerID)
	{
		ShowProviderList showProviderList = new ShowProviderList();

		for(ShowProvider showProvider : this)
		{
			if(showProvider.getProviderID().equals(providerID))
				showProviderList.add(showProvider);
		}

		return showProviderList;
	}

	/**
	 * Returns a sub-set of items from this list that have the specified ShowID and ProviderID
	 * @return sub-set of ShowProviderList
	 */
	public ShowProviderList findItemsByShowIDProviderID(ShowID showID, ProviderID providerID)
	{
		ShowProviderList showProviderList = new ShowProviderList();
		ShowProvider showProvider;
		Iterator iter = iterator();

		while(iter.hasNext())
		{
			showProvider = (ShowProvider)iter.next();
			if(showProvider.getShowID().equals(showID)
					&& showProvider.getProviderID().equals(providerID))
				showProviderList.add(showProvider);
		}

		return showProviderList;
	}

	/**
	 * Returns a sub-set of items from this list that have the specified ProviderID
	 * @return sub-set of ShowProviderList
	 */
	public ShowProviderList findItemsByProviderIDList(ProviderIDList providerIDList)
	{
		ShowProviderList showProviderList = new ShowProviderList();
		ShowProvider showProvider;
		Iterator iter = iterator();

		while(iter.hasNext())
		{
			showProvider = (ShowProvider)iter.next();
			if(providerIDList.contains(showProvider.getProviderID()))
				showProviderList.add(showProvider);
		}

		return showProviderList;
	}

	/**
	 * Returns a sub-set of items from this list whose ShowAvail is Available
	 * @return sub-set of ShowProviderList
	 */
	public ShowProviderList findItemsByAvailable()
	{
		ShowProviderList showProviderList = new ShowProviderList();

		for(ShowProvider showProvider : this)
		{
			if(ShowAvail.Available.equals(showProvider.getShowAvail()))
				showProviderList.add(showProvider);
		}

		return showProviderList;
	}

	/**
	 * Returns a sub-set of items from this list whose ShowFormatMime is in mimeTypeList
	 * @return sub-set of ShowProviderList
	 */
	public ShowProviderList findItemsByShowFormatMimeList(String[] mimeTypeList)
	{
		ShowProviderList showProviderList = new ShowProviderList();

		for(ShowProvider showProvider : this)
		{
			for(String mimeType : mimeTypeList)
			{
				if(mimeType.equals(showProvider.getShowFormatMime()))
					showProviderList.add(showProvider);
			}
		}

		return showProviderList;
	}

	/**
	 * Returns a sub-set of items from this list whose ShowFormatMime is supported by the Player
	 * @return sub-set of ShowProviderList
	 */
	public ShowProviderList findItemsByPlayer(Player player)
	{
		ShowProviderList showProviderList = new ShowProviderList();

		for(ShowProvider showProvider : this)
		{
			if(player.supportsFormat(showProvider.getShowFormat(), showProvider.getShowFormatMime()))
				showProviderList.add(showProvider);
		}

		return showProviderList;
	}

	public ShowProviderList findItemsByShowCost(ShowCost showCost)
	{
		ShowProviderList showProviderList = new ShowProviderList();

		for(ShowProvider showProvider : this)
		{
			if(showProvider.getShowCostList().contains(showCost))
				showProviderList.add(showProvider);
		}

		return showProviderList;
	}

	/**
	 * Returns a list of unqiue ProviderIDs from this list
	 */
	public ProviderIDList findUniqueProviderIDs()
	{
		ProviderIDList providerIDList = new ProviderIDList();
		HashSet<ProviderID> providerIDSet = new HashSet<ProviderID>();		// just used to remove duplicates

		for(ShowProvider showProvider : this)
			if(!providerIDSet.contains(showProvider.getProviderID()))
			{
				providerIDSet.add(showProvider.getProviderID());
				providerIDList.add(showProvider.getProviderID());
			}

		return providerIDList;
	}

	public HashMap<ProviderID, ShowProviderList> splitByProviderID()
	{
		HashMap<ProviderID, ShowProviderList> providerMap = new HashMap<ProviderID, ShowProviderList>();

		for(ShowProvider showProvider : this)
		{
			ShowProviderList providerShowProviderList = providerMap.get(showProvider.getProviderID());
			if(providerShowProviderList == null)
			{
				providerShowProviderList = new ShowProviderList();
				providerMap.put(showProvider.getProviderID(), providerShowProviderList);
			}
			providerShowProviderList.add(showProvider);
		}

		return providerMap;
	}

	public void merge(ShowProviderList showProviderList)
	{
		Iterator iter = showProviderList.iterator();
		ShowProvider showProvider;

		while(iter.hasNext())
		{
			showProvider = (ShowProvider)iter.next();
			if(find(showProvider.getShowProviderID()) == null)
				add(showProvider);
		}
	}

	public ArrayList<String> combineShowFormatMime()
	{
		ArrayList<String> showFormatMimeList = new ArrayList<String>();

		for(ShowProvider showProvider : this)
			if(StrUtil.hasLen(showProvider.getShowFormatMime()))
				showFormatMimeList.add(showProvider.getShowFormatMime());

		return showFormatMimeList;

	}

	public ShowFormatList combineShowFormatList()
	{
		ShowFormatList showFormatList = new ShowFormatList();

		for(ShowProvider showProvider : this)
			if(showProvider.getShowFormat() != null)
				showFormatList.add(showProvider.getShowFormat());

		return showFormatList;
	}

	public ShowCostList combineShowCostList()
	{
		ShowCostList showCostList = new ShowCostList();

		for(ShowProvider showProvider : this)
			showCostList.merge(showProvider.getShowCostList());

		return showCostList;
	}
}
