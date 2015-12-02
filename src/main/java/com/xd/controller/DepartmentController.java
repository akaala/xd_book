package com.xd.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xd.model.Department;
import com.xd.service.DepartmentService;

@Controller
@RequestMapping("view/dept/dept.it")
public class DepartmentController {

	private DepartmentService deptService;
	public DepartmentController(){
		this.deptService=new DepartmentService();
	}
	
	/**
	 * 获取部门信息
	 * @param department
	 * @return
	 */
	@RequestMapping(params="action=getDeptList",method=RequestMethod.GET)
	@ResponseBody
	public List<Department> querydept(Department department){
		return deptService.querydept(department);
	}
	
	/**
	 * 删除部门
	 * @param department
	 * @return
	 */
	@RequestMapping(params="action=deleteDept",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,String> deleteDept(Department department){
		return deptService.deleteDept(department);
	}
	
	/**
	 * 更新部门信息
	 * @param department
	 * @return
	 */
	@RequestMapping(params="action=updateDept",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,String> updateDept(Department department){
		return deptService.updateDept(department);
	}
	/**
	 * 插入部门
	 * @return
	 */
	@RequestMapping(params="action=saveDept",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> saveDept(Department department){
		return deptService.savdDept(department);
	}
	
}
