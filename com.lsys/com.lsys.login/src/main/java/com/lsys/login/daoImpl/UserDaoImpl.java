package com.lsys.login.daoImpl;

import org.apache.ibatis.session.SqlSession;

import com.lsys.login.bean.User;
import com.lsys.login.dao.UserDao;
import com.lsys.login.mapper.UserMapper;

public class UserDaoImpl extends BaseDao implements UserDao
{

	public int getUserCountById(String id)
	{
		SqlSession session = getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		return mapper.getUserCountById(id);
	}

	public void insertUser(User user)
	{
		SqlSession session = getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.insertUser(user);
	}

	public int getCountByIDAndPwd(String id, String password)
	{
		SqlSession session = getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		return mapper.getCountByIDAndPwd(id, password);
	}
}
