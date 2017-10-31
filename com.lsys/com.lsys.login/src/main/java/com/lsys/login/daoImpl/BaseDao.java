package com.lsys.login.daoImpl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BaseDao
{
	protected SqlSession getSession()
	{
		// mybatis配置文件是以流的方式读取的Resource是mybatis包下的类
		InputStream config = null;
		try
		{
			config = Resources.getResourceAsStream("mybatis/SqlMapConfig.xml");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		// 创建mybatis的会话工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(config);
		// 通过会话工厂得到SqlSession 
		//参数true则为自动commit
		SqlSession session = sessionFactory.openSession(true);
		return session;

	}
}
