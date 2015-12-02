$(function(){
	 $(".xd_login_ppt").gysPPT({width:"100%",height:"100%"});
});
var loginModule=angular.module("loginModule",[]);
//注册一个获取部门的服务
loginModule.factory("getDepart",["$http",function($http){
	return $http.get("login.it?action=getDepart");
}]);


//登陆控制器
loginModule.controller("loginController",function($scope){
	$scope.user={loginName:"",password:""};
	$.ajax({
		url:"login.it?action=getSession",
		type:"get"
	}).done(function(data){
		if(data.status=="success"){
			location.href="index.html";
		}
	});	
	
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
				if(data.status=="success"){
					window.location.href="index.html";
				}
				else if(data.status=="error"){
					alert(data.msg);
				}else{
					alert("返回参数异常,请联系管理员.登录失败");
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
loginModule.controller("registerController",function($scope,getDepart){
	$scope.user={
			name:"",
			departId:0,
			job:"",
			loginName:"",
			birth:"",
			entry:""
	};
	
	getDepart.success(function(data){
		$scope.departs=data;
		$scope.user.departId=$scope.departs[0].id;
		//$scope.$apply();
	}).error(function(){
		alert("连接服务器失败!");
	});
	$scope.gologinForm=function(){
		$(".register-form-box").animate({left:"-1000px"});
		$(".login-form-box").animate({left:"50%"});
	}
	
	$scope.registerSubmit=function(){
		if(!$scope.user.name){		
	            $scope.message="真实姓名是必填项";	    
			return false;
		}else if(!$scope.user.loginName){			
	            $scope.message="登录名是必填项";
				return false;
		}
		$scope.message="";
		$.ajax({
			url:"login.it?action=doRegister",
			data:$scope.user,
			type:"post"
		}).done(function(data){           
            	 $scope.message=data;
            	 $scope.$apply();         
		}).fail(function(){
			alert("服务器连接失败!")
		});
	}
});
