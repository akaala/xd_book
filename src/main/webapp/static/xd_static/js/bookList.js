var mainModule=angular.module("mainModule",[]);
mainModule.service("getBookList",function(){
	this.ajax=function(url,data){
		return $.ajax({
			url:url,
			data:data,
			type:"get"
		});
	}
})



mainModule.controller("bookListController",function($scope,getBookList,pageList){
	$scope.search={
			name:"",
			number:"",
			pageSize:15,
			currentPage:1
	};
	
	$scope.page={};
	$scope.page.pageSize=$scope.search.pageSize;

	function getList(){
		getBookList.ajax("book.it?action=getBookList",$scope.search).done(function(data){
			$scope.bookList=data.list;
			$scope.page.currentPage=data.currentPage;
			$scope.page.totalCount=data.totalCount;
			$scope.page=pageList.page($scope.page);//调用服务,重置page页码属性
			$scope.$apply();
		});
	}
	getList();
	$scope.serachList=function(){
		getList();
	}
	$scope.clickPageNum=function(topage){
		$scope.search.currentPage=topage;
		getList();
	}
	$scope.borrowBook=function(bookId){
		$.ajax({
			url:"../../borrow.it?action=insertBorrow&bookId="+bookId
		}).done(function(data){
			alert(data.msg);
			getList();
		}).fail(function(){
			alert("服务器连接失败!");
		})
	}	
});