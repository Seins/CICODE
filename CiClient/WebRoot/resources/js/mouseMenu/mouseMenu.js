(function($) {  	
	$.fn.rightMenu = function(options) {
		var defaultOpts={
			items:{
				'添加节点':doAdd
			}//菜单节点及其响应事件
		};  

		$('*').click(function(){
			clearMenu();
		});
		var obj=$(this);
		var opts=$.extend(defaultOpts, options);

		var init=function(){
			
			if(!obj){
				alert("右键菜单资源初始化错误：未找到对应的"+opts.element+"元素")
			}else{
				$(document).bind("contextmenu",function(e){   
					e = e || window.event;  
					clearMenu();
					var point = {x:0,y:0};  
				    if(e.pageX || e.pageY){  
				        point.x = e.pageX;  
				        point.y = e.pageY;  
				    } else {//兼容ie  
				        point.x = e.clientX + document.body.scrollLeft - document.body.clientLeft;  
				        point.y = e.clientY + document.documentElement.scrollTop;  
				    }  
					if(isInActiveArea(point,e) == true){
					    
						getMenu(point);
				        return false;   
					}else{
						//不再区域内
					}
					
			    });
			}
		}


		var getMenu=function(point){
			var _ls=$("<ul class='menu-list'></ul>");
			var _lh;
			_ls.append(_lh);
			for(var i in opts.items){
				if(typeof(opts.items[i]) == "undefinded"){
					alert('获取菜单失败，未找到对应的响应事件:'+opts.items[i]);
				}else{
					if( typeof opts.items[i] == "function"){
						var _item=$("<li><a class='menu-item' >"+i+"</a></li>");
						_item.find('a').click(opts.items[i]);
						_ls.append(_item);
					}else{
						alert("菜单项："+i+" 响应事件类型错误！菜单生成失败！");
						return;
					}
				}
			}
			_ls.css("top",point.y+"px");
			_ls.css("left",point.x+"px");
			$('body').append(_ls);
		}

		var clearMenu=function(){
			$('.menu-list').remove();
		}
		init();

		var isInActiveArea=function(point,event){
			var x=event.clientX;  
            var y=event.clientY;  
            var divx1 = obj.offset().left;  
            var divy1 = obj.offset().top;  
            var divx2 = obj.offset().left + obj.outerWidth();  
            var divy2 = obj.offset().top + obj.outerHeight();
            if( x < divx1 || x > divx2 || y < divy1 || y > divy2){

            	//不在操作区域内
              	return false; 
            } else{

            	//在操作区域内
        		return true;
            }
		}

	};  
})(jQuery); 