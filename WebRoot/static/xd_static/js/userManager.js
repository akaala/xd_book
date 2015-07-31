var uMgM=angular.module("userManagerModule",[]);
uMgM.controller("userListController",function($scope){
	$.ajax({
		url:"user.it?action=userManagerInit&pageSize=10&pageNumber=1",
		type:"get"
	}).done(function(data){
		var depts=data.dept;
		var users=data.users;
		var dlen=depts.length;
		var ulen=users.length;
		function getDeptName(id){
			for(var i=0;i<dlen;i++){
				if(depts[i].id==id){
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
		
		
		
	
		$scope.userList=users;
		$scope.$apply();
	}).fail(function(){
		alert("连接服务器失败!");
	})
});
