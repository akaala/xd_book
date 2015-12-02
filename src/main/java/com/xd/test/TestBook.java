package com.xd.test;

import java.util.List;

import com.xd.controller.LoginController;
import com.xd.model.Book;
import com.xd.model.User;
import com.xd.service.BookService;
import com.xd.service.UserService;

public class TestBook {
	public static void main(String[] args) {
		LoginController controller=new LoginController();
		//UserService service=new UserService();
		/*User user=new User();
		user.setName("郭");
		user.setBirth("1990-09-07");
		user.setEntry("2013-06-23");
		user.setDepartId(2);
		user.setJob("前端工程师!");
		user.setLoginName("guoyansii");
		user.setPassword("123");*/
		System.out.println("获取部门:>>>>>>"+controller.getDepart());
	}
}
