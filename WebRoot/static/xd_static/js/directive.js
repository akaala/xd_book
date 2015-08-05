mainModule.directive("pagelistDirective",function(){
				return{
					restrict:"E",
					replace:true,
					transclude:true,
					templateUrl:"../temp/pageList.html",
					controller:function($scope,$element,$attrs,$transclude){
					
					},
					link:function(scope,ele,attrs){
						/*scope.page={};
						var totalCount=scope.page.totalCount; //attrs.totalcount; 
						
						var pageSize=scope.page.pageSize;//attrs.pagesize;
						var totalPage=Math.ceil(totalCount/pageSize);
						var currentPage=scope.page.currentPage;
						var lastPage=totalPage;
						
						scope.page.totalPage=totalPage;
						scope.page.lastPage=lastPage;
						 scope.changePage=function(currentPage){
						 	
							var nextPage=currentPage+1;
							if(nextPage>lastPage){
								nextPage=lastPage;
							}
							var upPage=currentPage-1;
							if(upPage<1){
								upPage=1;
							}						
							if(currentPage<1){
								currentPage=1;
							}
							scope.page.currentPage=currentPage;
							scope.page.firstPage=1;
							scope.page.nextPage=nextPage;
							scope.page.upPage=upPage;	
							
						}
						
						scope.changePage(currentPage);*/
												
					}
				}
			});