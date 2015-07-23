package com.xd.controller;

import org.apache.ibatis.scripting.xmltags.VarDeclSqlNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xd.model.User;
import com.xd.service.UserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	/*@Autowired
	@Qualifier("UserService")
	private UserService userService;*/
	
	private UserService service(){
		return new UserService();
	}
	
	@RequestMapping(value = "/loginpage")
	public String loginPage() {
		return "login/login";
	}

	@RequestMapping(value = "doLogin",method=RequestMethod.POST)
	public String doLogin(User user) {
		UserService service=new UserService();
		int i=service.getUserCount(user);
		String view="";
		if(i==1){
			view="login/success";
		}else {
			view="redirect:loginpage";
		}
		return view;
		
	}

}
