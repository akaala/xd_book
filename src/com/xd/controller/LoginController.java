package com.xd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xd.model.Department;
import com.xd.model.User;
import com.xd.service.UserService;

@Controller
@RequestMapping("view/login.it")
public class LoginController {
	private UserService userService;

	public LoginController() {
		userService = new UserService();
	}

	@RequestMapping(params = "action=doLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> doLogin(User user, HttpServletRequest request) {
		return userService.doLogin(user, request);
	}

	@RequestMapping(params = "action=doRegister", method = RequestMethod.POST)
	@ResponseBody
	public String doRegister(User user, HttpServletResponse response) {
		/* System.out.println("name:>>>>>>>>>>>>>>>"+user.getName()); */
		String result = userService.saveUser(user);
		return result;
	}

	@RequestMapping(params = "action=getDepart", method = RequestMethod.GET)
	public @ResponseBody
	List<Department> getDepart() {
		List<Department> list = userService.getDepart();
		Department d = new Department();
		d.setId(0);
		d.setName("==请选择==");
		list.add(0, d);
		return list;
	}

	@RequestMapping(params = "action=getSession",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getCurrentSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			user = (User) session.getAttribute("user");
			if(user!=null){
				map.put("status", "success");
				map.put("user", user);
			}
			else {
				map.put("status", "error");
				map.put("user", "获取用户信息失败...");
			}
		} catch (Exception e) {
			map.put("status", "error");
			map.put("user", "获取用户信息失败...");
			System.out.println("获取session异常.....");
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping(params="action=logOut",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> logOut(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			map.put("status", "success");
			map.put("user", "session已销毁");
		} catch (Exception e) {
			map.put("status", "error");
			map.put("user", "销毁用户信息失败.");
			System.out.println("销毁session异常.....");
			e.printStackTrace();
		}
		return map;
	}

}
