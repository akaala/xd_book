package com.xd.dao.dept;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.xd.dao.SqlSessionDao;
import com.xd.model.Department;

@Repository
public class DepartmentDao {

	private SqlSession getSession(){
		SqlSessionDao sqlSessionDao=new SqlSessionDao();
		return sqlSessionDao.session;
	}
	
	/**
	 * 获取部门信息
	 * @param department
	 * @return
	 */
	public List<Department> querydept(Department department){
		List<Department> list=null;
		SqlSession session=getSession();
		try {
			list=session.selectList("dept.querydept",department);
		} catch (Exception e) {
			System.out.println("获取部门信息异常......");
e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	/**
	 * 更新部门信息
	 * @param department
	 * @return 返回更新部门id
	 */
	public int updateDept(Department department){
		int i=0;
		SqlSession session=getSession();
		try {
			i=session.update("dept.updateDept",department);
			session.commit();
		} catch (Exception e) {
			System.out.println("更新部门信息出现异常.....");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return i;
	}
	
	/**
	 * 插入部门
	 * @param department
	 * @return 插入部门id
	 */
	public int insertDept(Department department){
		int i=0;
		SqlSession session=getSession();
		try {
			i=session.insert("dept.insertDept", department);
			session.commit();
		} catch (Exception e) {
			System.out.println("插入部门信息失败.....");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return i;
	}
	/**
	 * 检查该部门是否还有人
	 * @param department
	 * @return 人数
	 */
	public int checkUserInDept(Department department){
		int i=-1;
		SqlSession session=getSession();
		try {
			i=session.selectOne("dept.checkUserInDept", department);
		} catch (Exception e) {
			System.out.println("检查部门人员是异常....");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return i;
	}
	
	/**
	 * 删除部门
	 * @param department
	 * @return
	 */
	public int deleteDept(Department department){
		int i=-1;
		SqlSession session=getSession();
		try {
			i=session.delete("dept.deleteDept",department);
			session.commit();
		} catch (Exception e) {
			System.out.println("删除部门异常.....");
		}finally{
			session.close();
		}
		return i;
	}
	
}
