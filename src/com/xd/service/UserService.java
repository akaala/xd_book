package com.xd.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		int i=userDao.getUserCount(u);
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
		if(!"".equals(user.getPassword())&&user.getPassword()!=null){
			MD5Password=getMD5String(user.getPassword());
			user.setPassword(MD5Password);
		}
		return userDao.getUserCount(user);
	}
	
	/**
	 * 登录
	 * 
	 * @param user
	 * @return 
	 */
	public Map<String, String> doLogin(User user,HttpServletRequest request) {
		String MD5Password="";
		Map<String, String> map=new HashMap<String, String>();
		
		if("".equals(user.getPassword())||user.getPassword()==null){
			map.put("status", "error");
			map.put("msg", "请填写密码!");
		}else if ("".equals(user.getLoginName())||user.getLoginName()==null) {
			map.put("status", "error");
			map.put("msg", "请填写登录名!");
		}else{
			MD5Password=getMD5String(user.getPassword());
			user.setPassword(MD5Password);
			List<User> list=userDao.getUserList(user);
			int count=list.size();
			if(count>1){
				map.put("status", "error");
				map.put("msg", "改登录名有多人使用,无法登录,请与管理员联系!");
			}else if(count==1){
				int status=list.get(0).getStatus();
				if(status==1){
					map.put("status", "success");
					map.put("msg", "登录成功....");
					HttpSession session=request.getSession();
					session.setAttribute("user",list.get(0));
				}else if (status==0) {
					map.put("status", "error");
					map.put("msg", "该账号未通过审核,请与管理员联系");
				}else {
					map.put("status", "error");
					map.put("msg", "该账号状态异常,禁止登录,请速与管理员联系!");
				}
			}else if (count==0) {
				map.put("status", "error");
				map.put("msg", "登录名或密码错误请重新登录!");
			}else {
				map.put("status", "error");
				map.put("msg", "该账号状态存在异常,请速与管理员联系!");
			}
		}
		return map;
	}
	
	/**
	 * 插入人员
	 * @param user
	 * @return
	 */
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
/**
 * 更新人员信息
 * @param user
 * @return
 */
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
		if(user.getName().equals("")||user.getName()==null){
			result="请填写真实姓名";
		}else if(user.getLoginName().equals("")||user.getLoginName()==null) {
			result="请填写登录名";
		}
		else {
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
		}
		
		return result;
	}

	/**
	 * 获取部门
	 */
	public List<Department> getDepart(){
		return userDao.getDepart();
	}
	
	public List<User> getUserList(User user){
		user.setPageStart(user.getcurrentPage());
		
		return userDao.getUserList(user);
	}
	
	/**
	 * 删除用户
	 * @return 删除id
	 */
	public Map<String,String> deleteUser(User user){
		int i=userDao.checkUserForDelete(user);
		Map<String,String> map=new HashMap<String, String>();
		if(i==0){//未发现未还书籍,可以删除
			i=userDao.deleteUser(user);
			if(i>0){
				map.put("status", "success");
				map.put("msg", "删除成功");
			}else {
				map.put("status", "error");
				map.put("msg", "删除失败");
			}
			
		}else if(i>0){//有未还书籍,无法删除
			map.put("status", "error");
			map.put("msg", "该成员还有"+i+"书没有还.");
		}else {
			System.out.println("查阅是否有未还书籍时异常.....");
			map.put("status", "error");
			map.put("msg", "程序出现异常,请联系工作人员.");
		}
		return map;
	}
	
	/**
	 * 审核通过
	 * @param id
	 * @return
	 */
	public Map<String, String> yesUserStatus(int id){
		int i=userDao.yesUserStatus(id);
		Map<String, String> map=new HashMap<String, String>();
		if(i>0){
			map.put("status", "success");
			map.put("msg", "修改成功!");
		}else {
			map.put("status", "error");
			map.put("msg", "修改失败!");
		}
		return map;
	}
	
	/**
	 * 审核不通过
	 * @param id
	 * @return
	 */
	public Map<String, String> noUserStatus(int id){
		int i=userDao.noUserStatus(id);
		Map<String, String> map=new HashMap<String, String>();
		if(i>0){
			map.put("status", "success");
			map.put("msg", "修改成功!");
		}else {
			map.put("status", "error");
			map.put("msg", "修改失败!");
		}
		return map;
	}
	
	/**
	 * 设置管理员
	 * @param id
	 * @return
	 */
	public Map<String, String> yesManager(int id){
		int i=userDao.yesManager(id);
		Map<String, String> map=new HashMap<String, String>();
		if(i>0){
			map.put("status", "success");
			map.put("msg", "修改成功!");
		}else {
			map.put("status", "error");
			map.put("msg", "修改失败!");
		}
		return map;
	}
	/**
	 * 取消管理员
	 * @param id
	 * @return
	 */
	public Map<String, String> noManager(int id){
		int i=userDao.noManager(id);
		Map<String, String> map=new HashMap<String, String>();
		if(i>0){
			map.put("status", "success");
			map.put("msg", "修改成功!");
		}else {
			map.put("status", "error");
			map.put("msg", "修改失败!");
		}
		return map;
	}
	
	
}
