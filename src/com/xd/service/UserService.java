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
	 * 获取md5加密后的字符串
	 */
	private String getMD5String(String source){
		String md5string="";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			md5string = base64en.encode(md5.digest(source.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("MD5加密异常1......");
			e.printStackTrace();
		}
		 catch (UnsupportedEncodingException e) {
			 System.out.println("MD5加密异常2......");
			 e.printStackTrace();
		}
		return md5string;
	}
	
	
	/**
	 * 获取人员数量
	 * 
	 * @param user
	 * @return 数量
	 */
	public int getUserCount(User user) {
		String MD5Password="";
		if(!user.getPassword().equals("")){
			MD5Password=getMD5String(user.getPassword());
			user.setPassword(MD5Password);
		}
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
		if (user.getId() == 0) {// 插入
			String md5Password=getMD5String("123456");
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
		return result;
	}

	/**
	 * 获取部门
	 */
	public List<Department> getDepart(){
		return userDao.getDepart();
	}
	
}
