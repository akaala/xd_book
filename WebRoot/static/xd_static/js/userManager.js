var uMgM=angular.module("userManagerModule",[]);
uMgM.service("getUser",function(){
	this.getList=function(url,data){
		return $.ajax({
			url:url,
			data:data,
			type:"get"
		});
	}
});
uMgM.service("getListDone",function(){
	this.done=function(users,depts){
		var dlen=depts.length;
		var ulen=users.length;
		function getDeptName(id){
			for(var i=0;i<dlen;i++){
				if(depts[i].id==id&&id!=0){
					return depts[i].name;
				}
			}
		}
		
		for(var i=0;i<ulen;i++){
			var name=getDeptName(users[i].departId);
			if(!name)
				name="空";
			users[i].deptName=name;
		}		
		return users;
	}
});

uMgM.service("ajaxFail",function(){
	this.fail=function(){
		alert("连接服务器失败")
	}
});
uMgM.service("pageList",function(){
	this.page=function(page){
		var totalCount=page.totalCount;
		var pageSize=page.pageSize;
		var currentPage=page.currentPage;
		var totalPage=Math.ceil((totalCount/pageSize));
		var upPage=currentPage-1;
		if(upPage<1){
			upPage=1;
		}
		var nextPage=currentPage+1;
		if(nextPage>totalPage){
			nextPage=totalPage;
		}
		
		return{
			totalCount:totalCount,
			pageSize:pageSize,
			currentPage:currentPage,
			totalPage:totalPage,
			upPage:upPage,
			firstPage:1,
			nextPage:nextPage,
			lastPage:totalPage
		}
		
	}
})

uMgM.controller("userListController",["$scope","getUser","getListDone","ajaxFail","pageList",function($scope,getUser,getListDone,ajaxFail,pageList){
	$scope.search={
		name:"",
		departId:0,
		pageSize:10,
		currentPage:1		
	};
	$scope.depts=[];
	$scope.userList=[];
	
	$scope.page={
		totalCount:0,//总条数
		nextPage:2,//下页
		firstPage:1,//首页
		upPage:1,//上页
		lastPage:0,//最后页
		pageSize:$scope.search.pageSize,//每页条数
		currentPage:$scope.search.currentPage,//当前页
		totalPage:0//总条数
	};
	
	
	getUser.getList("user.it?action=userManagerInit",$scope.search).done(function(data){
		$scope.depts=data.dept;
		$scope.depts.splice(0,0,{"id":0,name:"==请选择=="});
		$scope.search.departId=$scope.depts[0].id;
		
		$scope.page.totalCount=data.totalCount;
		$scope.page.currentPage=data.currentPage;
		$scope.page=pageList.page($scope.page);
		
		$scope.userList=getListDone.done(data.users,data.dept);
		
		$scope.$apply();
	}).fail(ajaxFail.fail);
	
	$scope.serachList=function(){
		getUser.getList("user.it?action=getUserList",$scope.search).done(function(data){
			$scope.page.totalCount=data.totalCount;
			$scope.page.currentPage=data.currentPage;
			$scope.page=pageList.page($scope.page);
			$scope.userList=getListDone.done(data.users,$scope.depts);
			$scope.$apply();
		}).fail(ajaxFail.fail);
	}
	$scope.clickPageNum=function(goPage){
		$scope.search.currentPage=goPage;
		$scope.serachList();		
	}
	
	
	$scope.deleteUser=function(id){
		$.ajax({
			url:"user.it?action=deleteUser&id="+id,
			type:"get"
		}).done(function(data){
			if(data.status=="success"){
				alert(data.msg);
				window.location.reload(true);
			}else if(data.status=="error"){
				alert(data.msg);
			}else{
				alert("返回参数异常,删除失败!");
			}
		}).fail(ajaxFail.fail);
	}
}]);
