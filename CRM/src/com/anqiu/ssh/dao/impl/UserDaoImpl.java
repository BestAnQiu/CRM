package com.anqiu.ssh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.anqiu.ssh.dao.UserDao;
import com.anqiu.ssh.domain.Customer;
import com.anqiu.ssh.domain.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public void regist(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public User login(User user) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_name=? and user_password=?",user.getUser_name(),user.getUser_password());	
		if (list.size()>0) {
			System.out.println("dao1");
			return list.get(0);
		}
		return null;	
	}

}
