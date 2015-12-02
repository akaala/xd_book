package com.xd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	public Map<String, Object> getUserList(User user,HttpServletRequest request){
		List<User> list=userService.getUserList(user);
		int totalCount=userService.getUserCount(user);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("users", list);
		map.put("totalCount", totalCount);
		map.put("currentPage", user.getcurrentPage());
		String getDept=request.getParameter("getDept");
		if(getDept.equals("yes")){
			List<Department> dept=userService.getDepart();
			map.put("dept", dept);
		}
		
		return map;
	}	
	@RequestMapping(params="action=deleteUser",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,String> deleteUser(User user){
		 Map<String,String> map=userService.deleteUser(user);
		 return map;
	}
	
	@RequestMapping(params="action=changeUserStatus",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,String> changeUserStatus(HttpServletRequest request){
		int id=Integer.parseInt(request.getParameter("id"));
		String value=request.getParameter("value");
		if(value.equals("yes")){
			return userService.yesUserStatus(id);
		}else {
			return userService.noUserStatus(id);
		}
	}
	
	@RequestMapping(params="action=changeMangager",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,String> changeMangager(HttpServletRequest request){
		int id=Integer.parseInt(request.getParameter("id"));
		String value=request.getParameter("value");
		if(value.equals("yes")){
			return userService.yesManager(id);
		}else {
			return userService.noManager(id);
		}
	}
	

	
}
