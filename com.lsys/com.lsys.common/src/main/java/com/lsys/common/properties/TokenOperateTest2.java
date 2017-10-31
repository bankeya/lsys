package com.lsys.common.properties;

import com.lsys.common.memcache.MemcachedUtil;

public class TokenOperateTest2
{

	public static void insertToken(String id, String token)
	{
		MemcachedUtil.put(id, token);
	}

	public static boolean validateToken(String id, String token)
	{

		if (token.equals(MemcachedUtil.get(id)))
		{
			return true;
		}
		return false;
	}

	public static void updateToken(String id, String token)
	{
		MemcachedUtil.put(id, token);
	}

	public static void deleteToken(String id)
	{
		MemcachedUtil.delete(id);
	}
}
