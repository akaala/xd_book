package com.xd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.scripting.xmltags.VarDeclSqlNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xd.model.Department;
import com.xd.model.User;
import com.xd.service.UserService;

@Controller
@RequestMapping("/login.it")
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
	@RequestMapping(params="it=loginpage")
	public String loginPage() {
		return "login/login";
	}

	@RequestMapping(params="it=doLogin",method=RequestMethod.POST)
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
	@RequestMapping(params="it=register",method=RequestMethod.GET)
	public String getRegisterPage(Model model){
		//attr.addFlashAttribute("depart","获取部门");//userService.getDepart()
		//attr.addAttribute("depart","获取部门");
		//request.setAttribute("depart", userService.getDepart());
		model.addAttribute("depart",userService.getDepart());
		return "login/register";
	}
/*	@RequestMapping(value="/doRegister")
	public @ResponseBody String doRegister(@RequestBody User user,RedirectAttributes attr,HttpServletRequest request){
		//attr.addFlashAttribute("user",user);
		String name=request.getParameter("name");
		
	//	return "fdfdfd";
		System.out.println("name:>>>>>>>>>>>>>>>"+name);
		String result=userService.saveUser(user);
		return result;
	}*/
/*	@RequestMapping(value="/doRegister")
	public @ResponseBody String doRegister(@RequestParam(value="name") @RequestBody String name,RedirectAttributes attr,HttpServletRequest request){
		//attr.addFlashAttribute("user",user);
		//String name=request.getParameter("name");
		
	//	return "fdfdfd";
		System.out.println("name:>>>>>>>>>>>>>>>"+name);
		//String result=userService.saveUser(user);
		return "1234";
	}*/
	//public @ResponseBody String doRegister(@RequestParam(value="name") @RequestBody String name){
	@RequestMapping(params="it=doRegister",method=RequestMethod.POST)
	@ResponseBody
  public String doRegister(User user,HttpServletResponse response){
		System.out.println("name:>>>>>>>>>>>>>>>"+user.getName());
		String result=userService.saveUser(user);
		//Map<String, String> map=new HashMap<String, String>();
		//map.put("status", "success");
		//map.put("msg", "成功");
		
		return result;
		//return "{'name':'guoyansi','age':25}";
	}
	
	@RequestMapping(params="it=registerInit",method=RequestMethod.GET)
	public @ResponseBody List<Department> getDepart(){
		return userService.getDepart();
	}

	

}
