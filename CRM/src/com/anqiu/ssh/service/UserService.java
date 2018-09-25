package com.anqiu.ssh.service;

import java.util.List;

import com.anqiu.ssh.domain.User;

public interface UserService {

	void regist(User user);

	User login(User user);

	List<User> findAll();


}
