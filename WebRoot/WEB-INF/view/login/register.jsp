<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html ng-app="registerModule">
<head>
<base href="<%=basePath%>">
<title>注册</title>
<link type="text/css" rel="stylesheet" />
<link href="<%=path%>/static/uiFrame/css/pure-min.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript"
	src="<%=path%>/static/uiFrame/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/static/uiFrame/js/angular-1.3.14.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/static/xd_static/js/login.js"></script>
<style>
.notice {
	color: red;
}

.pure-button {
	font-size: 85%;
}
</style>
</head>

<body>
	<div style="width:900px;margin: 0 auto;"
		ng-controller="registerController">
		<form class="pure-form pure-form-aligned" id="registerForm"
			action="login/doRegister" method="post">
			<fieldset>
				<div class="pure-control-group">
					<label>部门</label> 
					<select name="departId"	style="font-size:14px;height:auto;">
						<option value="0">==请选择==</option>
						<option ng-repeat="d in departments" value="{{d.id}">{{d.name}}</option>
					</select>
				</div>

				<div class="pure-control-group">
					<label>职位</label> <input type="text" name="job" placeholder="职位"
						ng-model="user.job" />
				</div>

				<div class="pure-control-group">
					<label>出生日期</label> <input type="text" name="birth"
						placeholder="出生日期" ng-model="user.birth" />
				</div>
				<div class="pure-control-group">
					<label>入职时间</label> <input type="text" name="entry"
						placeholder="入职时间" ng-model="user.entry" />
				</div>
				<div class="pure-control-group">
					<label>真实姓名</label> <input name="name" type="text"
						placeholder="真实姓名" ng-model="user.name"> <span
						class="notice">*</span>
				</div>
				<div class="pure-control-group">
					<label>登录名</label> <input type="text" name="loginName"
						placeholder="登录名" ng-model="user.loginName" /> <span
						class="notice">*</span>
				</div>
				<div class="pure-controls">
					<button type="button" class="pure-button pure-button-primary"
						ng-click="saveRegister()">保存</button>
					<input class="pure-button pure-button-primary" type="button"
						value="返回" />
				</div>
			</fieldset>
		</form>
	</div>

</body>
</html>
