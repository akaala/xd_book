package com.xd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xd.model.Department;
import com.xd.model.User;
import com.xd.service.UserService;

@Controller
@RequestMapping("view/user/user.it")
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
	@RequestMapping(params="action=userManagerInit",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> userManagerInit(User user){
		List<User> users=this.getUserList(user);
		List<Department> dept=userService.getDepart();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("users", users);
		map.put("dept", dept);
		return map;
	}
	
}
