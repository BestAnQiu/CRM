package com.anqiu.ssh.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.anqiu.ssh.dao.UserDao;
import com.anqiu.ssh.domain.User;
import com.anqiu.ssh.service.UserService;
import com.anqiu.ssh.utils.MD5Utils;

@Transactional
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void regist(User user) {
		//MD5加密
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		userDao.regist(user);
	}

	@Override
	public User login(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		User existUser = userDao.login(user);
		System.out.println("service1");
		return existUser;
	}

	@Override
	public List<User> findAll() {
		List<User> list = userDao.findAll();
		return list;
	}

}
