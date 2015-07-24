package com.xd.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.stereotype.Service;

import sun.misc.BASE64Encoder;

import com.xd.dao.user.UserDao;
import com.xd.model.Department;
import com.xd.model.User;

@Service
public class UserService {
	private UserDao userDao;

	// 初始化
	public UserService() {
		userDao = new UserDao();
	}

	/**
	 * 获取人员数量
	 * 
	 * @param user
	 * @return 数量
	 */
	public int getUserCount(User user) {
		return userDao.getUserCount(user);
	}

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 * @return
	 */
	public String saveUser(User user) {
		int i = 0;
		String result = "";
		try {
			if (user.getId() == 0) {// 插入
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				BASE64Encoder base64en = new BASE64Encoder();
				String originPassword = "123456";
				String md5Password = base64en.encode(md5.digest(originPassword.getBytes("utf-8")));
				user.setPassword(md5Password);
				i = userDao.insertUser(user);
			} else {// 更新
				i = userDao.updateUser(user);
			}
			if (i == 0) {
				result = "数据保存失败";
			} else {
				result = "数据保存成功";
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("UserService层的saveUser异常1......");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("UserService层的saveUser异常1......");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取部门
	 */
	public List<Department> getDepart(){
		/*List<Department> list=userDao.getDepart();	
		if(list==null){
			return [];
		}
		*/
		return userDao.getDepart();
	}
	
}
