var mainModule=angular.module("mainModule",[]);
mainModule.controller("myBorrowController",function($scope,pageList,ajaxFail){
	$scope.borrow={
			pageSize:15,
			currentPage:1
	}
	$scope.page={};

	$scope.page.pageSize=$scope.borrow.pageSize;
	function getBorrowList(){
		$.ajax({
			url:"../../borrow.it?action=getMyBorrowList",
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
	
	$scope.clickPageNum=function(currentPage){
		$scope.borrow.currentPage=currentPage;
		getBorrowList();
	}
	$scope.deleteData=function(id){
		$.ajax({
			url:"../../borrow.it?action=deleteBorrow&id="+id
		}).done(function(data){
			alert(data.msg)
			if(data.status=="success"){
				getBorrowList();
			}			
		}).fail(ajaxFail.fail);
	}
	
});