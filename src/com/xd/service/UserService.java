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
	 * 判断登录名是否可用
	 * @param user
	 * @return
	 */
	private boolean checkLoginName(User user) {
		User u=new User();
		boolean result=false;
		u.setLoginName(user.getLoginName());
		int i=userDao.getUserCount(user);
		if(i>=1){
			result=false;//该登录名不可用
		}else{
			result=true;//登录名可用
		}
		return result;
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
private String insertUser(User user){
	String result="";
	String md5Password=getMD5String("123456");
	user.setPassword(md5Password);
	if(user.getEntry().equals("")){
		user.setEntry(null);
	}
	if(user.getBirth().equals("")){
		user.setBirth(null);
	}
	int i = userDao.insertUser(user);
	if(i>0){
		result="注册成功";
	}else {
		result="注册失败";
	}
	return result;
}
	private String updateUser(User user){
		String result="";
		int i=0;
		i = userDao.updateUser(user);
		if(i==0){
			result="修改失败";
		}else {
			result="修改成功";
		}
		return result;
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
		boolean b=checkLoginName(user);
		if(b){//登陆名可用
			if (user.getId() == 0) {// 插入
				result=insertUser(user);
			}
			else {// 更新
				result=updateUser(user);
			}
		}else {//登录名被占用
			result="该登陆名被占用!";
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
