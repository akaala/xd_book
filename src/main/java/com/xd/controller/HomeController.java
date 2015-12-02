package com.xd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home.it")
public class HomeController {
	
	
	@RequestMapping(params="xd=index")
	public String homePage(){
		
		return "index";
	}
	@RequestMapping(params="xd=list")
	public String list(){
		return "book/list";
	}
	@RequestMapping(params="xd=detail")
	public String detail(){
		return "book/detail";
	}
	
}
