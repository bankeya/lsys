package com.lsys.login.business;

import com.lsys.login.bean.User;

public interface UserBusiness
{

	boolean UserUnique(String id);

	void regist(User user);

	boolean validate(String id, String pwd);

}
