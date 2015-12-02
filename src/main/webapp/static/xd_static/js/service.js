mainModule.service("pageList",function(){
	this.page=function(page){
		var totalCount=page.totalCount;
		var pageSize=page.pageSize;
		var currentPage=page.currentPage;
		var totalPage=Math.ceil((totalCount/pageSize));
		if(totalPage<1){
			totalPage=1;
		}
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
});

mainModule.service("ajaxFail",function(){
	this.fail=function(){
		alert("连接服务器失败");
	}
});