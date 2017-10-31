package com.lsys.lmail.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsys.common.function.CommonFunction;
import com.lsys.common.token.TokenOperate;

public class LoginFilter implements Filter
{

	public void init(FilterConfig filterConfig) throws ServletException
	{
		System.out.println("loginfilter start!");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		Cookie[] cookies = hRequest.getCookies();
		String id = CommonFunction.getCookieValueByName(cookies, "id");
		String token = CommonFunction.getCookieValueByName(cookies, "token");
		if (id == null || token == null)
		{
			hResponse.sendRedirect("/login/index.html");
			return;
		}
		if (!TokenOperate.validateToken(id, token))
		{
			hResponse.sendRedirect("/login/index.html");
			return;
		}
		chain.doFilter(hRequest, response);
	}

	public void destroy()
	{
		System.out.println("loginfilter destroy!");
	}

}
