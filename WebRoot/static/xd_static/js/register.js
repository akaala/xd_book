var registerModule = angular.module("registerModule", []);
registerModule.controller(
				"registerController",
				function($scope, $http) {
					$scope.user = {
						name : "",
						loginName : "",
						job : "",
						entry : "",
						birth : "",
						departId : 1,
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

						var postCfg = {
							headers : {
								'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
							},
							transformRequest : function(data) {
								return $.param(data);
							}
						};
						$.ajax({
							url : "login/doRegister",
							data : $scope.user,
							type : "post"
						}).done(function(data) {
							alert(data);
						}).fail(function() {
							alert("连接服务器失败!");
						});
					}
				});/**
 * 
 */