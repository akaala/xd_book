var xdModule=angular.module("xdModule",['ui.router']);
/*xdModule.config(['$routeProvider',function($routeProvider){
	$routeProvider.when("/list",{
		templateUrl:"book/list.html",
		controller:"BookListController"
	}).when("/detail",{
		templateUrl:"login/list.html",
		controller:"BookDetailController"
	}) .otherwise({
		redirectTo: '/list'
	});
}]);*/
xdModule.config(function($stateProvider,$urlRouterProvider){
	$urlRouterProvider.when("","/list");
	$stateProvider.state("list",{
		url:"/list",
		templateUrl: "book/list.html"
	}).state("detail",{
		url:"/detail",
		templateUrl: "book/detail.html"
	});
});


xdModule.controller("BookListController",function($scope){
	$scope.pageName="首页第一例子";
});
xdModule.controller("BookDetailController",function($scope){
	$scope.pageName="段元鑫";
});