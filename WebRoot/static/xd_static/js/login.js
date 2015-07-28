$(function(){
	 $(".xd_login_ppt").gysPPT({width:"100%",height:"100%"});
});
var loginModule=angular.module("loginModule",[]);
loginModule.controller("loginController",function($scope){
	$scope.user={loginName:"",password:""};
	
	//登录
$scope.login=function(){
		$.ajax({
			url:"login.it?action=doLogin",
			type:"post",
			data:$scope.user
			}).done(function(data){
				alert(data);
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


loginModule.controller("registerController",function($scope){
	$scope.gologinForm=function(){
		$(".register-form-box").animate({left:"-1000px"});
		$(".login-form-box").animate({left:"50%"});

	}
});
