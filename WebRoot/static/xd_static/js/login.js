$(function(){
	 $(".xd_login_ppt").gysPPT({width:"100%",height:"100%"});
});
var loginModule=angular.module("loginModule",[]);
//登陆控制器
loginModule.controller("loginController",function($scope){
	$scope.user={loginName:"",password:""};
	
//登录
$scope.login=function(){
	if($scope.user.loginName==""){
		return false;
	}else if($scope.user.password==""){
		return false;
	}
		$.ajax({
			url:"login.it?action=doLogin",
			type:"post",
			data:$scope.user
			}).done(function(data){
				//alert(data);
				if(data="success"){
					window.location.href="index.html"
				}else{
					alert("用户名或密码错误!");
				}
			}).fail(function(){
				alert("服务器连接失败!");
			});
		}
	//注册
$scope.goRegisterForm=function(){
	$(".login-form-box").animate({left:"10000px"});
	$(".register-form-box").animate({left:"50%"});
}
});

//注册控制器
loginModule.controller("registerController",function($scope){
	$scope.user={
			name:"",
			deptId:0,
			job:"",
			loginName:"",
			birth:"",
			entry:""
	};
	
	
	$scope.gologinForm=function(){
		$(".register-form-box").animate({left:"-1000px"});
		$(".login-form-box").animate({left:"50%"});
	}
	$scope.selectDeptChange=function(){
		
	}
	$scope.registerSubmit=function(){
		if($scope.user.name==""){
			return false;
		}else if($scope.user.loginName==""){
			return false;
		}
		
		$.ajax({
			url:"login.it?action=doRegister",
			data:$scope.user,
			type:"post"
		}).done(function(data){
			alert(data);
		}).fail(function(){
			alert("服务器连接失败!")
		});
	}
	
	
	
});
