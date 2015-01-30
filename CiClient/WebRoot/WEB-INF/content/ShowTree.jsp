<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String programid=(String)request.getAttribute("programid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
    <title>首页</title>
    <link rel="stylesheet" href="<%=basePath %>resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath %>resources/css/jquery.jOrgChart.css"/>
    <link rel="stylesheet" href="<%=basePath %>resources/css/custom.css"/>
    <link href="<%=basePath %>resources/css/prettify.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" href="<%=basePath %>resources/fancybox/jquery.fancybox.css" type="text/css" />
    <script type="text/javascript" src="<%=basePath %>resources/js/prettify.js"></script>
    
    <!-- jQuery includes -->
    <script type="text/javascript" src="<%=basePath %>resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>resources/js/jquery-ui.min.js"></script>
     <script type="text/javascript" src="<%=basePath %>resources/js/fastbuild/fastbuild.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/js/jquery.json.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=basePath %>resources/fancybox/jquery.fancybox.js"></script>
    <script src="<%=basePath %>resources/js/jquery.jOrgChart.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/js/taffy.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/js/AddNode.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/js/editnode.js"></script>
	 <link rel="stylesheet" href="<%=basePath%>resources/css/common.css" type="text/css" />
<style type="text/css">
#search{ 
text-align: left; 
position:relative; 
} 
.autocomplete{ 
border: 1px solid #9ACCFB; 
background-color: white; 
text-align: left; 
} 
.autocomplete li{ 
list-style-type: none; 
} 
.clickable { 
cursor: default; 
} 
.highlight { 
background-color: #9ACCFB; 
} 

#searchOper{ 
text-align: left; 
position:relative; 
} 
#searchType{ 
text-align: left; 
position:relative; 
} 
.clickable { 
cursor: default; 
} 
.highlight { 
background-color: #9ACCFB; 
} 
#chart{
    height: 100%;
    width:100%;
	resize:both;
}
html {  }
ul#upload-chart{
float: right;
    list-style: none outside none;}
ul#upload-chart li {
    background: none repeat scroll 0 0 #ECDC20;
    border: 1px solid #808080;
    border-radius: 2px;
    height: 44px;
    margin-top: 2px;
    padding-top: 5px;
    width: 200px;
}
.box{
	display:none;
}
.divshow(
	display:block;
)
</style>
    <script type="text/javascript">
    	$(document).on("ready", function(){
    		var num=0;
    		var programId=<%=programid%>;
    		
        	$.ajax({
		    	type:"post",
		    	url:"ShowTree?programid="+programId,
		    	data:{},
		    	dataType:"json",
		        success: function (data,textStatus) {
		        	var objs=eval(data);
		        	var obj=objs[0];
		        	var programlist=obj.programList;
		        	loadjson(programlist);
		        	$("#programid").val(programlist[0].programid)
		          }
		    });
		     setTimeout('init_tree()',1000)
		 	//init_tree();
			$("#new_node_name").blur(function(){
				$("#span1").html("");
	        	var pagename=$.trim($("#new_node_name").val());
	        	if(pagename==""){
	        		$("#span1").html("");
	        	}else{
	        		$.ajax({
						 type:"post",
						 url:"CheckPageName?pagename="+pagename+"&programid="+programId,
						 data:{},
						 async: false,
						 dataType:"html",
						 success: function (data) {
						 		$("#span1").html("");
						       	if(data=="ok"){
						       		$("#span1").html("<font color=\"red\">页面名称可以使用</font>");
						       		$("#add_node").removeAttr("disabled");
						       	}else if(data=="exist"){
						       		$("#span1").html("<font color=\"red\">本模块存在此页面!");
						       		$("#add_node").attr("disabled", true);
						       	}else{
						       		$("#span1").html("<font color=\"red\">已存在套用原模板？</font><input type=\"radio\" value=\"1\" name=\"myrad\">是<input type=\"radio\" value=\"0\" name=\"myrad\" checked=\"checked\">否");
						       		$("#add_node").attr("disabled", true);
						       	}
						      }
			  });
	        }
			 
        	$("input:radio").click(function(){
        		num=$(this).val();
        		if(num==0){
        			alert("请输入不同的名称");
        			$("#add_node").attr("disabled", true);
        			$("#new_node_name").focus();
        		}else{
        			if($("#new_node_name").val()!=null){
        				$("#add_node").removeAttr("disabled");
        			}
        		}
        	});
        });
            var add_to_node, del_node, classList,parentid,programid,add_to_node_class;
			var regx = /\w*(row)/;
			if ($('#upload-chart').length == 0) {
			   $("body").append("<ul id='upload-chart'></ul>")
			}
            $(".edit").live("click", function(e){
                classList = $(this).parent().parent().attr('class').split(/\s+/);
                var tipo_n;
                $.each(classList, function(index,item) {
                    if(item != "temp" && item != "node" && item != "child" && item != "ui-draggable" && item != "ui-droppable" && !regx.test(item) ){
                        del_node = item;
                        if(item.indexOf('unic')== 0){
                        	parentid=item.substring(4,item.length);
                        }
                    }
                    if(item == "root" || item == "child"){
                        tipo_n = item;
                    }
                });
                node_to_edit = $("li."+del_node+":not('.temp')");
                 var name=node_to_edit.find("> .label_node:eq(0) > a").html();
                $("#edit_node_name").val(name);
            }).fancybox({
                        maxWidth	: 800,
                        maxHeight	: 800,
                        fitToView	: false,
                        width : 'auto',
						height : 'auto',
                        autoSize	: false,
                        closeClick	: false,
                        openEffect	: 'none',
                        closeEffect	: 'none'
                    });
            $("#edit_node").click(function(e){
            
                e.preventDefault();
                //modify li and refresh tree
                var edit_field = $("#edit_node_name");
                var texto = edit_field.val();
                node_to_edit.find("> .label_node:eq(0) > a").text(texto);
                
                edit_field.val("");
                $.fancybox.close();
                init_tree();
                $.ajax({
					    	type:"post",
					    	url:"EditPage?pagename="+texto+"&pageid="+parentid+"&programid="+programId,
					    	data:{},
					    	async: false,
					    	dataType:"html",
					        success: function (data) {
					       		
					          }
			  		  });
            });
            //-- Listo editar :D
            $(".del").live("click", function(e){
                programid=$("#programid").attr("value");
            	var pageid;
            	if(confirm("确定执行删除操作?")){
            		var nodo=$(this);
                if(!nodo.parent().parent().hasClass("temp")){
                    var nodeDiv = nodo.parent().parent();
                    var cu = nodeDiv.find("a").attr("rel");
                    classList = nodeDiv.attr('class').split(/\s+/);
                    $.each(classList, function(index,item) {
                        if(item != "temp" && item != "node" && item != "child" && item != "ui-draggable" && item != "ui-droppable" && !regx.test(item) ){
                            del_node = item;
                            if(item.indexOf('unic')== 0){
                        	  pageid=item.substring(4,item.length);
                        	 }
                        }
                    });
                    $.ajax({
					    	type:"post",
					    	url:"DelPage?pageid="+pageid+"&programid="+programid,
					    	data:{},
					    	async: false,
					    	dataType:"html",
					        success: function (data) {
					          }
			  		  });
                    var element = $("li."+del_node+":not('.temp, #upload-chart li')").removeAttr("class").addClass("node").addClass("child");
                    remChild(element);
                    init_tree();
                }
               }
            });
            $(".add").live("click", function(){
            		//page名称
                    var text_field = $("#new_node_name");
                    var texto = text_field.val();
                    text_field.val("");
                    //text
                    var text=$("#text").val();
                    var classtype=$("#classtype").val();
                    var elementindex=$("#elementindex").val();
                    var content=$("#content").val();
                    $("#text").val("");
                    $("#classtype").val("");
                    $("#elementindex").val("");
                    $("#content").val("");
            	$("#add_node").attr("disabled", true);
            	$("#span1").html("");
                click_flag=false;
                classList = $(this).parent().parent().attr('class').split(/\s+/); 
                add_to_node="";
                add_to_node_class="";
                $.each(classList, function(index,item) {
                    if(item != "temp" && item != "node" && item != "child" && item != "ui-draggable" && item != "ui-droppable"){
                        add_to_node=item;
                        add_to_node_class+="."+item;
                        if(item.indexOf('unic')== 0){
                        	parentid=item.substring(4,item.length);
                        	if(parentid==0){
                        		$(".inputBox").addClass("box");
                        	}else{
                        		$(".inputBox").removeClass("box");
                        	}
                        }
                    }
                });
            }).fancybox({
                        maxWidth	: 800,
                        maxHeight	: 800,
                        fitToView	: false,
                        width : 'auto',
						height : 'auto',
                        autoSize	: false,
                        closeClick	: false,
                        openEffect	: 'none',
                        closeEffect	: 'none',
                        afterClose  : function(){click_flag=true;init_tree()}
                    });

            $("#add_node").click(function(e){
            		programid=$("#programid").attr("value");
                	e.preventDefault();
                    var tipo_nodo = "";
                    //page名称
                    var text_field = $("#new_node_name");
                    var texto = text_field.val();
                    text_field.val("");
                    //text
                    var text=$("#text").val();
                    var classtype=$("#classtype").val();
                    var elementindex=$("#elementindex").val();
                    var content=$("#content").val();
                    $("#text").val("");
                    $("#classtype").val("");
                    $("#elementindex").val("");
                    $("#content").val("");
                    if(num==0){
                    	var pages;
	                   $.ajax({
					    	type:"post",
					    	url:"AddPage?pagename="+texto+"&programid="+programid+"&parentpageid="+parentid+"&text="+text+"&classtype="+classtype+"&elementindex="+elementindex+"&content="+content,
					    	data:{},
					    	async: false,
					    	dataType:"html",
					        success: function (data) {
					       		pages=data;
					          }
			  		  	});
					  	var $node = $("li"+add_to_node_class+":not('.temp')");
		                var childs = $("#org li").size();
						childs += (childs+4)
	                    childs++;
	                    tipo_nodo += "child "+"unic"+pages+" "+childs;
	                    var append_text = "<li class='"+ tipo_nodo +"'>";
	                    append_text += "<span class='label_node'><a href='ShowElementOperByPageid?pageid="+ pages+"'>" + texto + "</a><br></span>";
	                    append_text += "</li>";
	                    if($node.find("ul").size()==0){
	                        append_text = "<ul>" + append_text + "</ul>";
	                        $node.append(append_text);
	                    }else{
	                    
	                        $node.find("ul:eq(0)").append(append_text);
	                    	}
                    }else{
                    	$.ajax({
					    	type:"post",
					    	url:"CopyPage?pagename="+texto+"&programid="+programid+"&parentpageid="+parentid+"&text="+text+"&classtype="+classtype+"&elementindex="+elementindex+"&content="+content,
					    	data:{},
					    	async: false,
					    	dataType:"json",
					        success: function (data) {
					       		pages=data;
					       		window.location.reload();
					          }
			  		  });
                    }
                    $.fancybox.close();
                    init_tree();
					scroll();
            });

			
            //forms behavior
            $("#new_node_name, #edit_node_name").on("keyup", function(evt){
                var id = $(this).attr("id");
                if($(this).val() != ''){
                    if(id == "new_node_name"){
                        $("#add_node").show();
                    }else{
                        $("#edit_node").show();
                    }
                }else{
                    if(id == "new_node_name"){
                        $("#add_node").hide();
                    }else{
                        $("#edit_node").hide();
                    }
                }
            });
            scroll()

        });

        function init_tree(){
            var opts = {
                chartElement : '#chart',
                dragAndDrop  : true
            };
            
            $("#chart").html("");
            $("#org").jOrgChart(opts);
        }
		function scroll(){
		  $(".node").click(function(){
		  $("#chart").scrollTop(0)
		  $("#chart").scrollTop($(this).offset().top-140);
		})}
		
        var click_flag = true;
        var node_to_edit;
         // read json and convert to html formate
		function loadjson(json){
			var items = [];
			var data=TAFFY(json);
			 data({"parentpageid":-1}).each(function (record,recordnumber) {
			     loops(record);
             });
			//start loop the json and form the html
			function loops(root){
				if (root.parentpageid == -1){
				   items.push("<li class='root unic" + root.pageid + " root' id='" + root.pagename + "'><span class='label_node'><a href='ShowElementOperByPageid?pageid="+ root.pageid+"'>" + root.pagename+"</a></br></span>");
				}
				else{
				   items.push("<li class='child unic" + root.pageid + " root' id='" + root.pagename + "'><span class='label_node'><a href='ShowElementOperByPageid?pageid="+ root.pageid+"'>" + root.pagename +"</a></br></span>");
				}
				var c = data({"parentpageid":root.pageid}).count();
				if (c != 0){
				  items.push("<ul>");
				  data({"parentpageid":root.pageid}).each(function (record,recordnumber) {
					loops(record);
				  });
				 items.push("</ul></li>");
				}
				else{
				  items.push("</li>");
				}
			}// End the generate html code
			//push to html code
			$( "<ul/>", {
			"id": "org",
			"style" : "float:right;",
			html: items.join( "" )
			}).appendTo( "body" );
			}
         
        
    </script>
  </head>

  <body >
  <input type="button" value="生成脚本" onclick="generateScript()"/>
  <input type="button" value="执行脚本" onclick="runTestScript()"/>
  <div style="height:100%">
   <input id="programid" type="hidden" value="" />
   <input id="scriptpath" type="hidden" value="" />
    <div class="topbar">
        <div class="topbar-inner">
        </div>
    </div>
    <div id="in" style="display:none">
      
   </div>            
    <ul id="upload-chart">

	</ul>
    <div id="chart" class="orgChart">
    </div>
    <div id="fancy" class="hidden">
			<form action="." method="post" id="add_node_form">
				<h1 class="title_lb">新增页面</h1>
				<div class="span12" id="add_nodo">
					<p class="notice span3">输入页面信息</p>
					<div class="inputPageBox" >
						<label for="new_node_name">page名称:</label>
						<input type="text" name="node_name" id="new_node_name"	class="span6" />
						<span id="span1"></span>
					</div>
					<div class="inputBox" id="search">
						<label for="classtype">元素类型:</label>
						<input type="text"	name="classtype" id="classtype" class="span6" />
					</div>
					<div class="inputBox">
						<label> 元素文本:</label>
						<input	type="text" name="text" id="text" class="span6" />
					</div>
					<div class="inputBox">
						<label for="elementindex">同类元素排序位置:</label>
						<input type="text" name="elementindex" id="elementindex" class="span6" onchange="value=value.replace(/[^\d]/g,'') " onkeyup="value=value.replace(/[^\d]/g,'') "  onafterpaste="value=value.replace(/[^\d]/g,'')"/>
					</div>	
					<div class="inputBox" id="searchOper">
						<label for="content"> 操作:</label>
						<input type="text" name="content" id="content" class="span6" />
						<ul class="contentOper">
						</ul>
					</div>
					<div class="submitBox">
						<input type="submit" id="add_node" value="确定" class="ok" disabled=disabled></input>
					</div>
				</div>
			</form>
			
		</div>

    <div id="fancy_edit" class="hidden">
			<form action="." method="post" id="edit_node_form">
				<h1 class="title_lb">编辑节点</h1>
				<div class="span12" id="edit_nodo">
					<p class="notice span3">输入新页面名称</p>
					<div class="inputPageBox">
						<label for="node_name">页面名称：</label>
						<input type="text" name="node_name" id="edit_node_name"	class="span6" />
					</div>
					<div class="submitBox">
						<input type="submit"  id="edit_node" value="确定" class="ok"></input>
					</div>
				</div>
			</form>
		</div>

<script type="text/javascript">
	function generateScript(){
		var programid=$("#programid").attr("value");
		$.ajax({
				type:"post",
				url:"GenerateScript?programid="+programid,
				data:{},
				async: false,
				dataType:"html",
			    success: function (data) {
			    		  alert("脚本生成完成");
					      pages=data;
					      //window.location.reload();
					      $("#scriptpath").val(data)
					}
			  });
	}
	function remChild(removing){
		$("#upload-chart").append(removing);
		$("#upload-chart ul li").each(function(){
			var Orgli = $(this).removeAttr("class").addClass("node").addClass("child").clone();
			$(this).remove();
			//$("#upload-chart").append(Orgli);
		});
		$("#upload-chart ul").remove();
		var sideLi = $("#upload-chart").html();
		$("#upload-chart").empty();			
		//$("#upload-chart").append(sideLi);
	}
	function runTestScript(){
		var scriptpath=$("#scriptpath").attr("value");
		$.ajax({
				type:"post",
				url:"RunCase?scriptpath="+scriptpath,
				data:{},
				async: false,
				dataType:"html",
			    success: function (data) {
			    		  alert("脚本执行完成");
					}
			  });
    /**var params={};
    $.ajax({
      type: "GET",
      data:params,
      url: "http://localhost:8080/testScript",//http://172.17.148.158:8099/FastBuild/testScript
      beforeSend: function(request) {
        request.setRequestHeader("dataType",DATA_TYPE_JSON);
      },
      success: function(data,status) {
        $('#runResult').text($.evalJSON(data).runFile +"执行成功...");
      },
      error:function(data,status,e){
        alert("请求失败:"+data.errorInfo);
      }
    },"json");*/
  }
  
</script>
</div>
</body>
</html>
