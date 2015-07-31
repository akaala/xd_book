<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登陆界面</title>
  </head>
  
  <body>
  <form action="login/doLogin" method="post">
  	 <table>
   		<tr><td>登陆名:</td><td><input type="text" name="loginName" value="${loginName }"/></td></tr>
   		<tr><td>密码:</td><td><input type="password" name="password" value="${password }" /></td></tr>
   		<tr><td></td><td style="color:red;">${msg }</td></tr>
   		<tr><td></td><td><input type="submit" value="登陆" /></td></tr>
   	</table>
  </form>
  </body>
</html>
