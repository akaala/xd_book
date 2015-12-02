var mainModule=angular.module("mainModule",[]);
mainModule.service("getUser",function(){
	this.getList=function(url,data){
		return $.ajax({
			url:url,
			data:data,
			type:"get"
		});
	}
});
mainModule.service("getListDone",function(){
	this.done=function(users,depts){
		var dlen=depts.length;
		var ulen=users.length;
		//获取部门名称
		function getDeptName(id){
			for(var i=0;i<dlen;i++){
				if(depts[i].id==id&&id!=0){
					return depts[i].name;
				}
			}
		}
		//循环用户
		for(var i=0;i<ulen;i++){
			var name=getDeptName(users[i].departId);
			if(!name)
				name="空";
			users[i].deptName=name;			
		}		
		return users;
	}
});

/*mainModule.service("ajaxFail",function(){
	this.fail=function(){
		alert("连接服务器失败")
	}
});*/
/*mainModule.service("pageList",function(){
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
})*/

mainModule.controller("userListController",["$scope","getUser","getListDone","ajaxFail","pageList",function($scope,getUser,getListDone,ajaxFail,pageList){
	$scope.search={
		name:"",
		departId:0,
		pageSize:15,
		currentPage:1		
	};
	$scope.depts=[];
	$scope.userList=[];
	
	$scope.page={};
	$scope.page.pageSize=$scope.search.pageSize;
	//封装获取用户并且更新视图的代码
	function getUserList(url){
		getUser.getList(url,$scope.search).done(function(data){
			if(data.dept){
				$scope.depts=data.dept;
				$scope.depts.splice(0,0,{"id":0,name:"==请选择=="});
				$scope.search.departId=$scope.depts[0].id;
			}
			
			
			$scope.page.totalCount=data.totalCount;
			$scope.page.currentPage=data.currentPage;
			$scope.page=pageList.page($scope.page);//调用服务,重置page页码属性
			$scope.userList=getListDone.done(data.users,$scope.depts);
			
			$scope.$apply();
		}).fail(ajaxFail.fail);
	}
	//加载列表页
	getUserList("user.it?action=getUserList&getDept=yes");
	
	//单击搜索
	$scope.serachList=function(){
		getUserList("user.it?action=getUserList&getDept=no");
	}
	//单击页码
	$scope.clickPageNum=function(goPage){
		$scope.search.currentPage=goPage;
		$scope.serachList();		
	}
	//删除用户	
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
	//审核用户
	$scope.changeUserStatus=function(id,action){
		var url="";
		if(action==1){
			url="user.it?action=changeUserStatus&id="+id+"&value=yes";
		}else if(action==0){
			url="user.it?action=changeUserStatus&id="+id+"&value=no";
		}else{
			alert("参数异常...");
		}
		$.ajax({
			url:url,
			type:"get"
		}).done(function(data){
			if(data.status=="success"){
				alert(data.msg);			
				getUserList("user.it?action=getUserList&getDept=no");				
			}else if(data.status=="error"){
				alert(data.msg);
			}else{
				alert("返回参数异常,删除失败!");
			}
		}).fail(ajaxFail.fail);
	}
	
	/**
	 * action 1:设置成管理员,0:取消管理员
	 */
	$scope.changeManager=function(id,action,status){
		var url="";
		if(action==1){
			if(status==0){
				alert("该人员还未通过审核,不能设置成管理员.");
				return;
			}
			url="user.it?action=changeMangager&id="+id+"&value=yes";
		}else if(action==0){
			url="user.it?action=changeMangager&id="+id+"&value=no";
		}else{
			alert("参数异常...");
		}
		$.ajax({
			url:url,
			type:"get"
		}).done(function(data){
			if(data.status=="success"){
				alert(data.msg);			
				getUserList("user.it?action=getUserList&getDept=no");				
			}else if(data.status=="error"){
				alert(data.msg);
			}else{
				alert("返回参数异常,删除失败!");
			}
		}).fail(ajaxFail.fail);
		
		
	}
	

}]);
