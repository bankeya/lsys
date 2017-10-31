package com.lsys.common.properties;

import com.lsys.common.util.PropertiesUtil;

public class TokenOperateTest
{
	private final static String path = "/token.properties";

	private static PropertiesUtil propertiesUtil = new PropertiesUtil(path);

	public static void insertToken(String id, String token)
	{
		propertiesUtil.writeProperty(id, token);
	}

	public static boolean validateToken(String id, String token)
	{
		System.out.println(propertiesUtil.readProperty(id));

		if (token.equals(propertiesUtil.readProperty(id)))
		{
			return true;
		}
		return false;
	}

	public static void updateToken(String id, String token)
	{
		propertiesUtil.writeProperty(id, token);
	}

	public static void deleteToken(String id)
	{
		propertiesUtil.writeProperty(id, null);
	}
}
