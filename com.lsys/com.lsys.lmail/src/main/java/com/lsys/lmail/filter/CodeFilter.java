package com.lsys.lmail.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.lsys.common.constant.ConfigConstant;

public class CodeFilter implements Filter
{
	private String encoding = ConfigConstant.ENCODING_UTF8;

	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.encoding = filterConfig.getInitParameter(ConfigConstant.ENCODING);
		System.out.println("编码方式：" + this.encoding);
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		request.setCharacterEncoding(this.encoding);
		response.setCharacterEncoding(this.encoding);
		chain.doFilter(request, response);
	}

	public void destroy()
	{
		this.encoding = ConfigConstant.ENCODING_UTF8;
	}
}
