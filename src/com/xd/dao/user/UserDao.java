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
	
	/*private SqlSession session=null;
	public UserDao(){
		session=(new SqlSessionDao()).session;
	}*/
	
	/*public static void main(String[] args) {
		UserDao loginDao=new UserDao();
		System.out.println("获取的人数:"+loginDao.getUserCount(null));
	}*/
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
}
