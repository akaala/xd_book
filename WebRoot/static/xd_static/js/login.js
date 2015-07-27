/* $.ajax({
			url:"login/registerInit",
			type:"get"
		}).done(function(data){
			var html="<option value='0'>==请选择==</option>";
			for(var i=0;i<data.length;i++){
				html+="<option value="+data[i].id+">"+data[i].name+"</option>";
			}
			$("select[name=departId]").html(html);
		}).fail(function(){
			alert("失败");
		}); */

		var registerModule = angular.module("registerModule", []);
		registerModule.controller("registerController",
				function($scope, $http) {
					$scope.user = {
						name : "",
						loginName : "",
						job : "",
						entry : "",
						birth : "",
						departId:1,
					};

					$http.get('login.it?it=registerInit').success(
							function(data, status, headers, config) {
								$scope.departments = data;
							}).error(function(data, status, headers, config) {
						alert("服务器连接失败");
					});

					$scope.saveRegister = function() {
						if ($scope.user.name == "") {
							alert("真实姓名是必填项");
							return;
						}
						if ($scope.user.loginName == "") {
							alert("登录名是必填项");
							return;
						}
						//alert($scope.user.name);
						//return;
						/* $http.post("login/doRegister",{name:"ggggggggg",loginName:"log"}).success(function(data){
								alert(data);
							}).error(function(){
								alert("服务器连接失败!")
							}); */
							var postCfg = {
					                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
					                transformRequest: function(data) {
						                return $.param(data);
						            }
					            };
							/* $http({
								url:"login/doRegister",
								data:$scope.user,
								method:"post"
							}); */
							
							/*$.post("login/doRegister",$scope.user,function(data){
								alert(data);
							},"text");*/
							
							/*$.ajax({
								url:"login/doRegister",
								data:$scope.user,
								type:"post"
							}).done(function(data){
								alert(data);
							}).fail(function(){
								alert("连接服务器失败!");
							});*/
							
							 $http.post("login.it?it=doRegister",$scope.user,postCfg)
							 .success(function(data,status,headers,config){
								alert(data);
							}).error(function(data,status,headers,config){
								alert("连接服务器失败!");
							}); 
							
							/*$http({
								method:"post",
								url:"login/doRegister",
								data:$scope.user,
								headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
								transformRequest:function(data){
									return $.param(data);
								},
								transformResponse:function(data){
									alert(data);
								},
								cache:false
							}).success(function(datas,status,headers,config){
								alert(status);
								alert(datas);
							}).error(function(){
								alert("失败");
							});
							*/
							
							
							
							
							
							
							
							
						}
					

				});

		
		/*  	function saveRegister(){
		 				
		 		$.ajax({
		 			url:"login/doRegister",
		 			type:"post",
		 			data:$("#registerForm").serialize()
		 		}).done(function(data){
		 			alert(data);
		 		}).fail(function(){
		 			alert("服务器连接失败!");
		 		});
		 	} */