package com.lsys.login.businessImpl;

import com.lsys.login.bean.User;
import com.lsys.login.business.UserBusiness;
import com.lsys.login.dao.UserDao;
import com.lsys.login.daoImpl.UserDaoImpl;

public class UserBusinessImpl implements UserBusiness
{
	UserDao userDao = new UserDaoImpl();

	public boolean UserUnique(String id)
	{
		if (userDao.getUserCountById(id) != 0)
		{
			return false;
		}
		return true;
	}

	public void regist(User user)
	{
		userDao.insertUser(user);
	}

	public boolean validate(String id, String pwd)
	{
		if (userDao.getCountByIDAndPwd(id, pwd) != 1)
		{
			return false;
		}
		return true;
	}

}
