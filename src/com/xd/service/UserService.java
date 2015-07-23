package com.xd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.dao.login.UserDao;
import com.xd.model.User;

@Service
public class UserService {
	/*private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}*/
private UserDao dao(){
	return new UserDao();
}
	
	public int getUserCount(User user){
		UserDao dao=new UserDao();
		return dao.getUserCount(user);
	}
	
}
