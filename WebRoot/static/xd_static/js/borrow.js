var mainModule=angular.module("mainModule",[]);
mainModule.controller("borrowController",function($scope,pageList,ajaxFail){
	$scope.borrow={
			userName:"",
			bookName:"",
			operatorName:"",
			pageSize:15,
			currentPage:1
	}
	$scope.page={};
	$scope.page.pageSize=$scope.borrow.pageSize;
	function getBorrowList(){
		$.ajax({
			url:"../../borrow.it?action=getBorrowList",
			data:$scope.borrow
		}).done(function(data){
			$scope.page.totalCount=data.totalCount;
			$scope.page.currentPage=data.currentPage;
			$scope.page=pageList.page($scope.page);//调用服务,重置page页码属性
			$scope.borrowList=data.list;
			$scope.$apply();
		}).fail(ajaxFail.fail);
	}
	getBorrowList();
	$scope.serachList=function(){
		getBorrowList();
	}
});