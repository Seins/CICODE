<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>首页</title>


<link rel="stylesheet" href="<%=basePath%>resources/css/dtree.css"	type="text/css" />
<link rel="stylesheet" href="<%=basePath%>resources/css/nav.css"	type="text/css" />
<link rel="stylesheet" href="<%=basePath%>resources/css/common.css"	type="text/css" />

<link rel="stylesheet"	href="<%=basePath%>resources/js/mouseMenu/mouseMenu.css"	type="text/css" />
<script type="text/javascript" src="<%=basePath%>resources/js/dtree.js"></script>
<script type="text/javascript"	src="<%=basePath%>resources/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript"	src="<%=basePath%>resources/js/mouseMenu/mouseMenu.js"></script>
<script type="text/javascript">
	function ShowDiv(show_div,bg_div){
		document.getElementById(show_div).style.display='block';
		document.getElementById(bg_div).style.display='block' ;
		var bgdiv = document.getElementById(bg_div);
		bgdiv.style.width = document.body.scrollWidth;
		$("#"+bg_div).height($(document).height());
	};
	//关闭弹出层
	function CloseDiv(show_div,bg_div)
	{
		document.getElementById(show_div).style.display='none';
		document.getElementById(bg_div).style.display='none';
	};
</script>
<style type="text/css">
	.span12{width:700px;}
    label{padding-top:6px;font-size:13px;line-height:18px;float:left;width:130px;text-align:right;color:#404040;}
    input.span6,textarea.span6{display:inline-block;float:none;width:330px;margin-left:0;}
    .submitBox>input[type="button"]{
		width:120px;
		height:28px;
	}
	.submitBox>input.ok{
		background:#81D4A8;
		color:#fff;
		border-color:#fff;
	}
	.submitBox>input.ok:hover{
		color:#8190D4;
	}
    .notice{
    	font-weight:500;
		font:bold;
		font-size:16px;
		color:#888;
		width:100%;
		padding:3px 0;
		border-bottom:1px solid #ccc;
		margin-bottom:30px;
    }
    .title_lb{
    	font-family:'微软雅黑';
		font-size:22px;
		font-style:normal;
		color:#41A6DB;
		text-align:center;
		margin:0;
		background:#efefef;
    }
    .inputBox{
	min-width:550px;
	padding:3px 15px;
	}
	.inputBox>label{
		font-size:14px;
		color:#555;
	}
	.inputBox>input{
		margin:0 10px;
	}
	.submitBox{
		width:100%;
		padding:10px 0;
		min-height:50px;
		text-align:center;
		position:relative;
	}
    .black_overlay{
		display: none;
		position: absolute;
		top: 0%;
		left: 0%;
		width: 100%;
		height: 100%;
		background-color:#666666;
		z-index:1001;
		-moz-opacity: 0.8;
		opacity:.80;
		filter: alpha(opacity=80);
		}
		.white_content {
		display: none;
		position: absolute;
		top: 10%;
		left: 10%;
		width: 60%;
		height: 60%;
		border: 1px solid white;
		background-color: white;
		z-index:1002;
		overflow: auto;
		}
		.white_content_small {
		display: none;
		position: absolute;
		top: 20%;
		left: 30%;
		width: 40%;
		height: 50%;
		border: 16px solid lightblue;
		background-color: white;
		z-index:1002;
		overflow: auto;
		}
</style>
</head>

<body onload="init()" >
	<div class="layout">
		<table class="layout-table">
			<tbody>
				<tr>
					<td colspan="1">
						<div class="navMain">
							<div>
								<script type="text/javascript">
									d = new dTree('d');
									d.add(0,-1,"root","javascript:void(0)","","",false);
									$.ajax({  
										type:"post", //数据发送方式    
									    url:"ShowProjectProgram",   								
									    dataType:"xml", //接受数据格式   
									    async: false ,//同步方式  
									    error:function(xml){  
									             alert( "数据请求失败:+"+xml+"!");  
									       },  
									    success: function(xml){  
									         	$(xml).find("node").each(function(){ 
									        		var nodeId=$(this).attr("nodeId");
									        		var parentId= $(this).attr("parentId");
											        var nodeName=$(this).text();  
											        if(parentId==0){
											        	d.add(nodeId,parentId,nodeName,"javascript:void(0)","","frameRight","","",false); 
											        }else{
											        	d.add(nodeId,parentId,nodeName,"ToPage?programid="+nodeId,"","frameRight","","",false); 
											        }
								                });  
								           }
									     }); 
									document.write(d);
								</script>
							</div>
						</div>
						<span id="close" class="close"></span>
					</td>
					 <td colspan="5" style="border-left:2px solid #AAAAAA">
    						<iframe src=""  style="HEIGHT: 100%; VISIBILITY: inherit; WIDTH: 100%; Z-INDEX: 2;padding:0%;" frameborder=0  name="frameRight" ></iframe>
    				</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<script>
		var _h=$(window).height();
		var _w=$(window).width();
		
		function init(){
			$('.navMain').parent().css("width",(_w*0.15)+"px");
			$('.navMain').parent().css("height",_h*0.94+"px");
			$('.contentMain').parent().css("width",(_w*0.94)+"px");
			$('.contentMain').parent().css("height",_h*0.94+"px");
		}
		
		
		$(function(){
			$('#close').click(function(){
				if($(this).hasClass('close')){
					closeNav();
				}else{
					openNav();
				}
			});
			
			$('.navMain').rightMenu({
				items:{
					"添加元素":doAdd,
					"删除元素":doDelete
				}
			});
		});
		
		
		function closeNav(){
			$('.navMain>div').fadeOut("slow",function(){
				$('.navMain').parent().animate({
					"width":(_w*0.001)+"px"
				});
				$('#close').toggleClass("close");
			});
		}
		
		function openNav(){
			$('.navMain').parent().animate({
				"width":(_w*0.15)+"px"
			},"normal",function(){
				$('.navMain>div').fadeIn("fast");
				$('#close').toggleClass("close");
			});
		}
		
		
		
		//响应事件定义
		var doAdd=function(){
			var parentProgramid=d.getSelected();
			ShowDiv('MyDiv','fade');
			var addobj=$("#add");
        	addobj.click(function(){
	        	var programname=$("#programname").val();
	        	$.ajax({
			    	type:"post",
			    	url:"AddProgram?programname="+programname+"&projectid="+parentProgramid,
			    	data:{},
			    	dataType:"html",
			        success: function (data,textStatus) {
			        	alert("添加成功");
			        	window.location.reload();
			          }
			    });
	        });
			
		};
		
		var doDelete=function(){
			var parentProgramid=d.getSelected();
			var projectid=d.getNodeById(parentProgramid).pid;
			var t = window.confirm("确认删除此节点?");
			var programid=d.getSelected();
			if(t==true){
				$.ajax({
			    	type:"post",
			    	url:"DelProgram?programid="+programid+"&projectid="+projectid,
			    	data:{},
			    	dataType:"html",
			        success: function (data,textStatus) {
			        	alert("删除成功");
			        	window.location.reload();
			          }
			    });
			}
		};
		
		var doModify=function(){
			alert("删除节点方法");
		};
	</script>
<input name="programid" id="cn" type="hidden"><br>
<div id="fade" class="black_overlay">
</div>
<div id="MyDiv" class="white_content">
<div style="text-align: right; cursor: default; height: 40px;">
<span style="font-size: 16px;" onclick="CloseDiv('MyDiv','fade')"><img src="<%=basePath %>resources/images/del.gif"></img></span>
</div>
   <div id="addElementDetail" >
	  		<h1 class="title_lb">新增模块信息</h1>
				<div class="span12" id="add_nodo">
					<p class="notice span3">输入模块信息</p>
					<div class="inputBox">
						<label for="programname">模块名称:</label>
						<input type="text"	name="programname" id="programname" class="span6" />
					</div>
					<div class="submitBox">
						<input type="button" id="add" value="确定" class="ok"></input>
					</div>
				</div>
				
			</div>
</div>
</body>
</html>
