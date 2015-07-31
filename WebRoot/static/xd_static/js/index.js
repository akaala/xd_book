/*var homeModule=angular.module("xdModule",[]);
homeModule.controller("indexController",function($scope){
	$scope.clickMenuItem=function(){
		$scope.boolean=true;
	}
});*/

$(function(){
	$(".xd-menu-box li").click(function(){
		$(this).siblings().removeClass("xdMenuItemHover");
		$(this).addClass("xdMenuItemHover");
		var src=$("a.xd-menu-text",this).attr("rel");
		$("#mainContentIframe").attr("src",src);
	})
});