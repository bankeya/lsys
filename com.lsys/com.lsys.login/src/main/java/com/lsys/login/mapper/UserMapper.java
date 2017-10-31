package com.lsys.login.mapper;

import org.apache.ibatis.annotations.Param;

import com.lsys.login.bean.User;

public interface UserMapper
{

	int getUserCountById(String id);

	void insertUser(User user);

	int getCountByIDAndPwd(@Param("id") String id, @Param("password") String password);

}
