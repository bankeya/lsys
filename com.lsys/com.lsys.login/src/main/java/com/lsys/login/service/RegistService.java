package com.lsys.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.lsys.common.constant.UserStateConstant;
import com.lsys.common.util.MD5;
import com.lsys.login.bean.User;
import com.lsys.login.business.UserBusiness;
import com.lsys.login.businessImpl.UserBusinessImpl;

@Path("regist")
public class RegistService
{
	@Context
	HttpServletRequest request;

	@Context
	HttpServletResponse response;

	UserBusiness service = new UserBusinessImpl();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String regist(@QueryParam("id") String id, @QueryParam("name") String name,
			@QueryParam("password") String password)
	{
		if (!service.UserUnique(id))
		{
			return UserStateConstant.REGIST_USEREXIST;
		}
		service.regist(new User(id, MD5.encrypt(password), name));
		return UserStateConstant.REGIST_SUCCESS;
	}
}
