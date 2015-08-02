package com.xd.dao.user;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.xd.dao.SqlSessionDao;
import com.xd.model.Department;
import com.xd.model.User;

@Repository
public class UserDao{
	
	private SqlSession getSession(){
		SqlSessionDao sqlSessionDao=new SqlSessionDao();
		return sqlSessionDao.session;
	}
	/**
	 * 获取人员数量
	 * @param user
	 * @return 数量
	 */
	public int getUserCount(User user){
		int count=0;
		SqlSession session=getSession();
		try {
			count=session.selectOne("user.getUserCount",user);
		} catch (Exception e) {
			System.out.println("Dao层getUserCount方法异常......");
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return count;
	}
	/**
	 * 插入用户
	 * @return 用户id
	 */
	public int insertUser(User user){
		int id=0;
		SqlSession session=getSession();
		try {
			id=session.insert("user.insertUser",user);
			session.commit();
		} catch (Exception e) {
			System.out.println("Dao层insertUser方法异常......");
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return id;
	}
	
	/**
	 * 更新数据
	 */
	public int updateUser(User user){
		int id=0;
		SqlSession session=getSession();
		try {
			id=session.update("user.updateUser",user);
			session.commit();
		} catch (Exception e) {
			System.out.println("Dao层updateUser方法异常......");
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return id;
	}
	/**
	 * 获取部门
	 */
	public List<Department> getDepart(){
		List<Department> list=null;
		SqlSession session=getSession();
		try {
			list=session.selectList("user.getDepart",null);
		} catch (Exception e) {
			System.out.println("userDao层getDepart发生异常......");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	/**
	 * 获取用户list
	 * @param user
	 * @return
	 */
	public List<User> getUserList(User user){
		List<User> list=null;
		SqlSession session=getSession();
		try {
			list=session.selectList("user.queryUser",user);
		} catch (Exception e) {
			System.out.println("userDao层getUserList发生异常.....");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	/**
	 * 检查用户是否有未接的书
	 * @param user
	 * @return 未接图书数量
	 */
	public int checkUserForDelete(User user){
		int i=-1;
		SqlSession session=getSession();
		try {
			i=session.selectOne("user.checkUserForDelete",user);
		} catch (Exception e) {
			System.out.println("userDao层checkUserForDelete发生异常.....");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return i;
	}
	
	
/**
 * 删除用户
 * @param user
 * @return 删除id
 */
	public int deleteUser(User user){
		int i=0;
		SqlSession session=getSession();
		try {
			i=session.delete("user.deleteUser", user);
			session.commit();
		} catch (Exception e) {
			System.out.println("userDao层deleteUser发生异常.....");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return i;
	}
	
}
