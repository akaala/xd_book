package com.xd.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xd.model.User;
import com.xd.service.UserService;

@Controller
@RequestMapping("view/user.it")
public class UserController {

	private UserService userService;
	public UserController (){
		userService=new UserService();
	}
	
	@RequestMapping(params="action=getUserList",method=RequestMethod.GET)
	@ResponseBody
	public List<User> getUserList(User user){
		List<User> list=userService.getUserList(user);
				
		return list;
	}
	
	
}
