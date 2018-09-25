package com.anqiu.ssh.dao;

import java.util.List;

import com.anqiu.ssh.domain.Customer;
import com.anqiu.ssh.domain.User;

public interface UserDao extends BaseDao<User>{

	void regist(User user);

	User login(User user);

}
