var mainModule=angular.module("mainModule",[]);
mainModule.service("getBookList",function(){
	this.ajax=function(url,data){
		return $.ajax({
			url:url,
			type:"get"
		});
	}
	/*this.done=function(books){
		
	}*/
})



mainModule.controller("bookListController",function($scope,getBookList){
	$scope.search={
			name:"",
			number:"",
			pageSize:15,
			currentPage:1
	}
	
	$scope.page={};
	$scope.page.pageSize=$scope.search.pageSize;
	
	getBookList.ajax("book.it?action=getBookList",$scope.search).done(function(data){
		//console.log(data);
		$scope.bookList=data.list;
		$scope.$apply();
	});
	$scope.serachList=function(){
		
	}
	
});