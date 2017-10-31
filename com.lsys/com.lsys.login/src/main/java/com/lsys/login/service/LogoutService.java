package com.lsys.login.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.lsys.common.constant.UserStateConstant;
import com.lsys.common.function.CommonFunction;
import com.lsys.common.token.TokenOperate;

@Path("logout")
public class LogoutService
{
	@Context
	HttpServletRequest request;

	@Context
	HttpServletResponse response;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String logout()
	{
		HttpServletRequest hRequest = (HttpServletRequest) request;
		Cookie[] cookies = hRequest.getCookies();
		String id = CommonFunction.getCookieValueByName(cookies, "id");
		String token = CommonFunction.getCookieValueByName(cookies, "token");
		if (id == null || token == null)
		{
			return UserStateConstant.LOGOUT_COOKIEERROR;
		}
		if (TokenOperate.validateToken(id, token))
		{
			TokenOperate.deleteToken(id);
			Cookie userCookie = new Cookie("id", id);
			userCookie.setMaxAge(0);
			userCookie.setPath("/");
			response.addCookie(userCookie);

			Cookie tokenCookie = new Cookie("token", token);
			tokenCookie.setMaxAge(0);
			tokenCookie.setPath("/");
			response.addCookie(tokenCookie);

			return UserStateConstant.LOGOUT_SUCCESS;
		}
		return UserStateConstant.LOGOUT_FAIL;
	}
}
