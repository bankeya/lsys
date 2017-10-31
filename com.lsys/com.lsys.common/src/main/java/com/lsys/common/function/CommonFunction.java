package com.lsys.common.function;

import javax.servlet.http.Cookie;

public class CommonFunction
{
	public static String getCookieValueByName(Cookie[] cookies, String name)
	{
		if (cookies == null || cookies.length == 0)
		{
			return null;
		}
		for (Cookie c : cookies)
		{

			if (name.equals(c.getName()))
			{
				return c.getValue();
			}
		}
		return null;
	}
}
