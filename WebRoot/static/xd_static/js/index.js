/*var homeModule=angular.module("xdModule",[]);
homeModule.controller("indexController",function($scope){
	$scope.clickMenuItem=function(){
		$scope.boolean=true;
	}
});*/

$(function(){
	$(".xd-menu-box li").click(function(){
		var src=$("a.xd-menu-text",this).attr("rel");
		if(!src)
			return;
		$(this).siblings().removeClass("xdMenuItemHover");
		$(this).addClass("xdMenuItemHover");		
		$("#mainContentIframe").attr("src",src);
	})
});