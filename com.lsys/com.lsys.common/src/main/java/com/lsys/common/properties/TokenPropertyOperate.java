package com.lsys.common.properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

public class TokenPropertyOperate
{
	private final static String path = "/token.properties";

	private static Properties tokenProperty = new Properties();

	static
	{
		try
		{
			InputStream input = TokenPropertyOperate.class.getResourceAsStream(path);
			tokenProperty.load(input);
			input.close();
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}

	// private static Properties getTokenProperties()
	// {
	// Properties properties = new Properties();
	// InputStream input = TokenPropertyOperate.class.getResourceAsStream(path);
	// try
	// {
	// properties.load(input);
	// input.close();
	// }
	// catch (Exception e1)
	// {
	// e1.printStackTrace();
	// }
	// System.out.println(properties.getProperty("a"));
	// return properties;
	// }

	/**
	 * 插入一个token
	 * 
	 * @param id
	 * @param token
	 * @author 罗彬
	 */
	public static void insertToken(String id, String token)
	{
		tokenProperty.setProperty(id, token);
		save("insert");
	}

	/**
	 * 验证token
	 * 
	 * @param id
	 * @param token
	 * @return boolean 验证成功则返回true，失败则返回false
	 * @author 罗彬
	 */
	public static boolean validateToken(String id, String token)
	{
		System.out.println(tokenProperty.getProperty(id));

		if (token.equals(tokenProperty.getProperty(id)))
		{
			return true;
		}
		return false;
	}

	/**
	 * 更新token
	 * 
	 * @param id
	 * @param token
	 * @author 罗彬
	 */
	public static void updateToken(String id, String token)
	{
		tokenProperty.setProperty(id, token);
		save("update");
	}

	private synchronized static void save(String comments)
	{
		URL url = /* TokenPropertyOperate.class.getResource(path); */
		ClassLoader.getSystemResource(path);
		OutputStream out = null;
		try
		{
			File file = new File(url.toURI());
			out = new FileOutputStream(file);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{

			tokenProperty.store(out, comments);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 删除一个token
	 * 
	 * @param id
	 * @author 罗彬
	 */
	public static void deleteToken(String id)
	{
		tokenProperty.remove(id);
		save("delete");
	}
}
