package com.lsys.login.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User
{
	private String id;
	private String password;
	private String name;

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public User(String id, String password, String name)
	{
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}

	public User()
	{
		super();
	}
}
