var xdModule=angular.module("xdModule",['ui.router']);
xdModule.config(function($stateProvider,$urlRouterProvider){
	$urlRouterProvider.when("","/list");
	$stateProvider.state("list",{
		url:"/list",
		templateUrl: "book/list.html",
		controller:function($scope){
			$scope.message="list";
		}
	}).state("detail",{
		url:"/detail",
		templateUrl: "book/detail.html",
		controller:function($scope){
			$scope.message="Details";
		}
	}).state("userList",{
		url:"/userList",
		templateUrl:"user/userList.html",
		controller:"userListController"
	});
});


xdModule.controller("BookListController",function($scope){
	$scope.pageName="首页第一例子";
});
xdModule.controller("BookDetailController",function($scope){
	$scope.pageName="段元鑫";
});
xdModule.controller("userListController",function($scope){
	$.ajax({
		url:"user.it?action=getUserList",
		type:"get"
	}).done(function(data){
		$scope.userList=data;
		$scope.$apply();
	}).fail(function(){
		alert("服务器连接失败");
	});
});























