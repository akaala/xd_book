var homeModule=angular.module("xdModule",[]);
homeModule.controller("indexController",function($scope){
	$scope.clickMenuItem=function(i){
		$(".xd-menu-box").find("li").removeClass("xd-menu-item-hover");
		$(".xd-menu-box").find("li").eq(i).addClass("xd-menu-item-hover");
		var rel=$(".xd-menu-box .xd-menu-item-hover .xd-menu-text").attr("rel");
		$scope.src=rel;
		
	}
});