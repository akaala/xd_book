;(function($){            
       var defaults={
            type:0,//0淡入淡出
            width:800,//box宽度
            height:500,//box高度
            fadeTime:2000,//淡入淡出时间
            changeTime:4000//图片变化的时间
        }
        var showStr="show";
        var hideStr="hide";            
        function changeImg(liArray,time){
            liArray.each(function(){
                if($(this).is("."+showStr)){//有show这个类
                    $(this).fadeOut(time,function(){
                        $(this).removeClass(showStr).addClass(hideStr);
                    });
                var thisNext=$(this).next();
                if(thisNext.length>0){//有下级元素
                    thisNext.fadeIn(time,function(){
                        $(this).removeClass(hideStr).addClass(showStr);
                    });
                }
                else{//没有下级元素
                    liArray.eq(0).fadeIn(time,function(){
                        $(this).removeClass(hideStr).addClass(showStr);
                    });
                    }
                return false;//找到了show这个类,立马跳循环
                }

            });
        }
        $.fn.gysPPT=function(option){
            option=$.extend({},defaults,option);
            $(this).addClass("gysPPtBox");
            if(option.width=="100%"){
            	$(this).css({width:option.width});
            }else{
            	$(this).width(option.width)
            }
            if(option.height=="100%"){
            	$(this).css({height:option.height});
            }else{
            	$(this).height(option.height);
            }
            $("li img",$(this)).width(option.width).height(option.height);
            if(option.type==0){
                var liArray=$("li",$(this))
                var liCount=liArray.length;                    
                var randow=Math.ceil(Math.random()*liCount)-1;                    
                liArray.each(function(index){
                    if(randow==index){$(this).show().addClass(showStr)}
                    else if(randow!=index){$(this).hide().addClass(hideStr)}                        
                });
                setInterval(function(){changeImg(liArray,option.fadeTime)},option.changeTime);
            }
            else {
            alert("不存在的type值")                    
            }
            return $(this);
        }
	})(jQuery);