package com.xd.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.scripting.xmltags.VarDeclSqlNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xd.model.Department;
import com.xd.model.User;
import com.xd.service.UserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	/*@Autowired
	@Qualifier("UserService")*/
	private UserService userService;
	
	/*private UserService service(){
		return new UserService();
	}*/
	public LoginController (){
		userService=new UserService();
	}
	@RequestMapping(value = "/loginpage")
	public String loginPage() {
		return "login/login";
	}

	@RequestMapping(value = "doLogin",method=RequestMethod.POST)
	public String doLogin(User user,RedirectAttributes attr) {
		UserService service=new UserService();
		int i=service.getUserCount(user);
		String view="";
		if(i==1){
			view="login/success";
		}else {
			view="redirect:loginpage";
			//attr.addAttribute("msg", "登录失败!");
			attr.addFlashAttribute("loginName",user.getLoginName());
			attr.addFlashAttribute("password",user.getPassword());
			attr.addFlashAttribute("msg", "登录失败!");
		}
		return view;		
	}
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String getRegisterPage(Model model){
		//attr.addFlashAttribute("depart","获取部门");//userService.getDepart()
		//attr.addAttribute("depart","获取部门");
		//request.setAttribute("depart", userService.getDepart());
		model.addAttribute("depart",userService.getDepart());
		return "login/register";
	}
	@RequestMapping(value="/doRegister")
	public @ResponseBody String doRegister(User user,RedirectAttributes attr,HttpServletRequest request){
		//attr.addFlashAttribute("user",user);
		String name=request.getParameter("name");
		
		return "fdfdfd";
		
		//String result=userService.saveUser(user);
		//return result;
	}
	@RequestMapping(value="/registerInit",method=RequestMethod.GET)
	public @ResponseBody List<Department> getDepart(){
		return userService.getDepart();
	}
	

}
