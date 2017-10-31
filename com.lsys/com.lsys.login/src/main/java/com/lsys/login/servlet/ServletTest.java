package com.lsys.login.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ServletTest extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException
	{
		System.out.println("------------servlet inti!!!!!-------------");
	}

	public void destroy()
	{
		System.out.println("------------servlet destroy!!!!!-------------");
	}
}
