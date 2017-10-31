package com.lsys.common.token;

import java.util.Date;
import java.util.UUID;

import com.lsys.common.memcache.MemcachedUtil;
import com.lsys.common.util.MD5;

public class TokenOperate
{
	public static String generateToken(String str)
	{
		switch ((int) (Math.random() * 3))
		{
		case 0:
			return generateTokenByUUID(str);
		case 1:
			return generateTokenByData(str);
		case 2:
			return generateTokenByDateANDUUID(str);

		default:
			return generateTokenByDateANDUUID(str);
		}
	}

	private static String generateTokenByUUID(String str)
	{
		return MD5.encrypt(str + UUID.randomUUID());
	}

	private static String generateTokenByData(String str)
	{
		return MD5.encrypt(str + new Date().getTime());
	}

	private static String generateTokenByDateANDUUID(String str)
	{
		return MD5.encrypt(str + new Date().getTime() + UUID.randomUUID());
	}

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
