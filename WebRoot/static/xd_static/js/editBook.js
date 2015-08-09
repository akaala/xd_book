var mainModule=angular.module("mainModule",[]);
mainModule.controller("editController",function($scope){
	$scope.book={
			id:0,
			number:"",
			name:"",
			price:"",
			author:"",
			publisher:""
	};
    function getUrlParam() {
        var url = document.location.toString();
        var arrObj = url.split("?");
        var result = {};
        if (arrObj.length > 1) {
            var arrPara = arrObj[1].split("&");
            var arr;
            for (var i = 0; i < arrPara.length; i++) {
                arr = arrPara[i].split("=");
                result[arr[0]] = arr[1];
            }
        }
        return result;
    }
    var bookId=getUrlParam().id;
    if(bookId=="-1"){
    	$scope.title="添加图书";
    	$scope.book.id=bookId;
    }else{
    	$scope.title="修改图书";
    	$.ajax({
    		url:"book.it?action=getBookList&id="+bookId
    	}).done(function(data){
    		$scope.book={
    				number:data.list[0].number,
    				name:data.list[0].name,
    				price:data.list[0].price,
    				author:data.list[0].author,
    				publisher:data.list[0].publisher
    		}
    		$scope.$apply();
    	}).fail(function(){
    		alert("连接服务器失败!");
    	});
    	
    	
    }    
	$scope.goBack=function(){
		location.href="bookManager.html";
	}
	$scope.saveBook=function(){
		if(!$scope.book.number){
			alert("请填写图书编码");
			return;
		}
		if(!$scope.book.name){
			alert("请填写图书名称");
			return;
		}
		
		if(isNaN($scope.book.price)){
			alert("价格必须是数字");
			return;
		}
		
		$.ajax({
			url:"book.it?action=saveBook",
			data:$scope.book
		}).done(function(data){
			alert(data.msg);
			if(data.status=="success"){
				location.href="bookManager.html";
			}
		}).fail(function(){
			alert("服务器连接失败!");
		});
	}
	
})