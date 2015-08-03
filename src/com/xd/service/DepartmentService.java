package com.xd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xd.dao.dept.DepartmentDao;
import com.xd.model.Department;

@Service
public class DepartmentService {
	private DepartmentDao deptDao;

	public DepartmentService() {
		this.deptDao = new DepartmentDao();
	}
	
	/**
	 * 获取部门信息
	 * @param department
	 * @return
	 */
	public List<Department> querydept(Department department){
		List<Department> list=deptDao.querydept(department);
		return list;
	}
	/**
	 * 更新部门信息
	 * @param department
	 * @return 返回更新部门id
	 */
	public Map<String,String> updateDept(Department department){
		int i=0;
		Map<String, String> map=null;
		i=deptDao.updateDept(department);
		if(i>0){
			map.put("status", "success");
			map.put("msg", "部门更新成功.");
		}else {
			map.put("status", "error");
			map.put("msg", "部门更新失败.");
		}
		return map;
	}
	
	/**
	 * 插入部门
	 * @param department
	 * @return 插入部门id
	 */
	public  Map<String,String> insertDept(Department department){
		int i=0;
		 Map<String,String> map=new HashMap<String, String>();
		i=deptDao.insertDept(department);
		if(i>0){
			map.put("status", "success");
			map.put("msg", "部门添加成功.");
		}else {
			map.put("status", "error");
			map.put("msg", "部门添加失败.");
		}
		return map;
	}
	
}
