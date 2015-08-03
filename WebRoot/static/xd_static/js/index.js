var homeModule=angular.module("xdModule",[]);
homeModule.controller("indexController",function($scope){
	$scope.user={};	
	$.ajax({
		url:"login.it?action=getSession",
		type:"get"
	}).done(function(data){
		if(data.status=="success"){
			$scope.user=data.user;
			$scope.$apply();
			var currentObj=angular.element(".xd-menu-box li")
			currentObj.eq(0).addClass("xdMenuItemHover");
			src=$("a.xd-menu-text",currentObj).attr("rel");
			if(!src)
				return;		
			angular.element("#mainContentIframe").attr("src",src);
			
			
			angular.element(".xd-menu-box li").click(function(){
				var src=angular.element("a.xd-menu-text",this).attr("rel");
				if(!src)
					return;		
				angular.element(this).siblings().removeClass("xdMenuItemHover");
				angular.element(this).addClass("xdMenuItemHover");		
				angular.element("#mainContentIframe").attr("src",src);
			})
			
			
		}else{
			alert("获取登录信息失败,将跳转到登录页");
			location.href="login.html";
		}
		
	}).fail(function(){
		alert("获取登录信息失败,将跳转到登录页");
		location.href="login.html";
	});
	$scope.logOut=function(){
		$.ajax({
			url:"login.it?action=logOut",
			type:"get"
		}).done(function(data){
			if(data.status=="success"){
				location.href="login.html";
			}else{
				alert("服务器执行失败,请稍后操作!");
			}
		}).fail(function(){
			alert("服务器连接失败,请重新操作.")
		});
	}	
});

