package com.anqiu.ssh.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.anqiu.ssh.domain.User;
import com.anqiu.ssh.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user=new User();
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String regist(){
		userService.regist(user);
		return LOGIN;
	}
	
	public String login(){
		User existUser = userService.login(user);
		if (existUser!=null) {
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			System.out.println("login1");
			return SUCCESS;
		}else {
			System.out.println("错误方法");
			this.addActionError("用户名或密码输入错误！");
			return LOGIN;
		}
	}
	public String findAllUser() throws IOException{
		List<User> list = userService.findAll();
		//转换json字符串
		JSONArray jsonArray = JSONArray.fromObject(list);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
