package com.lsys.login.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.lsys.common.constant.UserStateConstant;
import com.lsys.common.token.TokenOperate;
import com.lsys.common.util.MD5;
import com.lsys.login.business.UserBusiness;
import com.lsys.login.businessImpl.UserBusinessImpl;

@Path("login")
public class LoginService
{
	@Context
	HttpServletRequest request;

	@Context
	HttpServletResponse response;

	UserBusiness service = new UserBusinessImpl();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@QueryParam("id") String id, @QueryParam("password") String password)
	{
		String pwd = MD5.encrypt(password);

		if (!service.validate(id, MD5.encrypt(password)))
		{
			return UserStateConstant.LOGIN_FAIL;
		}

		String token = TokenOperate.generateToken(id + pwd);

		Cookie userCookie = new Cookie("id", id);
		userCookie.setMaxAge(60 * 60 * 2);
		userCookie.setPath("/");
		response.addCookie(userCookie);

		Cookie tokenCookie = new Cookie("token", token);
		tokenCookie.setMaxAge(60 * 60 * 2);
		tokenCookie.setPath("/");
		response.addCookie(tokenCookie);

		TokenOperate.insertToken(id, token);

		return UserStateConstant.LOGIN_OK;
	}
}
