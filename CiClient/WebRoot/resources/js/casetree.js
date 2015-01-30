$(function() { 
            $('.navMain').contextMenu('menu', 
         { 
             bindings: 
          { 
              'add': function(t, target) { 
            	var addobj=$("#add");
            	addobj.click(function(){
    	        	var programname=$("#programname").val();
    	        	$.ajax({
    			    	type:"post",
    			    	url:"AddProgram?programname="+programname,
    			    	data:{},
    			    	dataType:"html",
    			        success: function (data,textStatus) {
    			        	alert("添加成功");
    			        	window.location.reload();
    			          }
    			    });
    	        });
              }, 
              'delete': function(t, target) { 
            	var t = window.confirm("确认删除此节点?");
      			var programid=d.getSelected();
      			if(t==true){
      				$.ajax({
      			    	type:"post",
      			    	url:"DelProgram?programid="+programid,
      			    	data:{},
      			    	dataType:"html",
      			        success: function (data,textStatus) {
      			        	alert("删除成功");
      			        	window.location.reload();
      			          }
      			    });
      			}
              }, 
              'upload': function(t, target) { 
            	  alert(3);
              } 
          }, 
             onShowMenu: function(e, menu) { 
                 $(e.currentTarget).siblings().removeClass("SelectedRow").end().addClass("SelectedRow"); 
                 return menu; 
             } 

         }); 

        })