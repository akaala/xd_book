package com.xd.dao.login;

import org.springframework.stereotype.Repository;

import com.xd.dao.SqlSessionDao;
import com.xd.model.User;

@Repository
public class UserDao {
	/*public static void main(String[] args) {
		UserDao loginDao=new UserDao();
		System.out.println("获取的人数:"+loginDao.getUserCount(null));
	}*/
	public int getUserCount(User user){
		return SqlSessionDao.session.selectOne("user.getUserCount",user);
	}
}
