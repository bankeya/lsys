package com.lsys.login.dao;

import com.lsys.login.bean.User;

public interface UserDao
{

	int getUserCountById(String id);

	void insertUser(User user);

	int getCountByIDAndPwd(String id, String pwd);

}
