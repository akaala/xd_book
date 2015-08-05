var mainModule=angular.module("mainModule",[]);
mainModule.controller("bookListController",function($scope){
	$scope.search={
			name:"",
			number:"",
			pageSize:15,
			currentPage:1
	}
	
	$scope.page={};
	$scope.page.pageSize=$scope.search.pageSize;
	
});