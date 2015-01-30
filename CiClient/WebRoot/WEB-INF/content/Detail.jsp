<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*,com.nd.citest.model.*" pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <%@ taglib prefix="s" uri="/struts-tags"%>
    <%
	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" href="<%=basePath %>resources/css/css.css" type="text/css"></link>
    <link rel="stylesheet" href="<%=basePath %>resources/css/addEle.css" type="text/css"></link>
    <script type="text/javascript"  src="<%=basePath %>resources/js/jquery-1.4.2.min.js"></script>
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
	.inputPageBox{
		min-width:550px;
		padding:3px 15px;
	}
	.inputPageBox>label{
		font-size:14px;
		color:#555;
	}
	.inputPageBox>input{
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
		.inputBox>ul.contentTip{
            display: none;
            min-height: 100px;
            border:1px solid #cfcfcf;
            border-top: 0;
            width: 222px;
            position: absolute;
            top: 155px;
            left: 155px;
            background: #fff;
            z-index: 3;
            max-height: 120px;
            overflow-y:auto;

        }
        .inputBox>ul.contentTip>li{
            line-height: 24px;
            text-align: left;
            text-indent: 0.2cm;
            width: 100%;
            display: block;
            overflow: hidden;
            cursor: pointer;
            font-size: 12px;
        }
        .inputBox>ul.contentTip>li:hover{
            background: #cfcfcf;
        }
        .inputBox>ul.operationalTip{
            display: none;
            min-height: 100px;
            border:1px solid #cfcfcf;
            border-top: 0;
            width: 222px;
            position: absolute;
            top: 230px;
            left: 155px;
            background: #fff;
            z-index: 3;
            max-height: 120px;
            overflow-y:auto;

        }
        .inputBox>ul.operationalTip>li{
            line-height: 24px;
            text-align: left;
            text-indent: 0.2cm;
            width: 100%;
            display: block;
            overflow: hidden;
            cursor: pointer;
            font-size: 12px;
        }
        .inputBox>ul.operationalTip>li:hover{
            background: #cfcfcf;
        }
	<!--
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
	}
	.STYLE1 {font-size: 12px}
	.STYLE3 {font-size: 12px; font-weight: bold; }
	.STYLE4 {
		color: #03515d;
		font-size: 12px;
	}
	
	.selectCell{
		background:#6699FF;
	
	-->
</style>
<script type="text/javascript" language="javascript">
	function ShowDiv(show_div,bg_div){
	document.getElementById(show_div).style.display='block';
	document.getElementById(bg_div).style.display='block' ;
	var bgdiv = document.getElementById(bg_div);
	bgdiv.style.width = document.body.scrollWidth;
	// bgdiv.style.height = $(document).height();
	$("#"+bg_div).height($(document).height());
	};
	//关闭弹出层
	function CloseDiv(show_div,bg_div)
	{
		document.getElementById(show_div).style.display='none';
		document.getElementById(bg_div).style.display='none';
	};
	function del(elementid,pageid,operationalid){
		$.ajax({
		    	type:"post",
		    	url:"DelEleOperational?elementid="+elementid+"&pageid="+pageid+"&operationalid="+operationalid,
		    	data:{},
		    	dataType:"html",
		        success: function (data,textStatus) {
		        	alert("删除成功");
		        	window.location.reload();
		          }
		    });
	}
	
    $(function(){
        var addobj=$("#add");
        addobj.click(function(){
        	var elementText=$("#elementText").val();
        	var pageid=$("#pageid").val();
        	var elementindex=$("#elementindex").val();
        	var content=$("#content").val();
        	var classtype=$("#classtype").val();
        	var resultid=$("#resultid option:selected").val();
        	$("#elementText").val("");
        	$("#pageid").val("");
        	$("#elementindex").val("");
        	$("#content").val("");
        	$("#classtype").val("");
        	$.ajax({
		    	type:"post",
		    	url:"AddEleOper?pageid="+pageid+"&elmentText="+elementText+"&content="+content+"&elementindex="+elementindex+"&resultid="+resultid+"&classtype="+classtype,
		    	data:{},
		    	dataType:"html",
		        success: function (data,textStatus) {
		        	alert("添加成功");
		        	window.location.reload();
		          }
		    });
        	
        });
    	var obj=$("table[id=edittable] tbody td:gt(5)");
    	obj.click(function(){
    		var tdobj=$(this);
    		var fieldClass=tdobj.attr("class");
    		if(fieldClass!='pagename'){
    		if(tdobj.children("input").length>0){
    			return false;
    		}
    		var text=tdobj.html();
    		tdobj.html("");
    		var inputobj=$("<input type='text'>").css("border-width","0")
    					.css("font-size","12px").css("text-align","center").width(tdobj.width())
    					.val(text).appendTo(tdobj);
    		inputobj.trigger("focus").trigger("select");
    		inputobj.click(function(){
    			return false;
    		});
    		inputobj.blur(function(){
    			var newText=$(this).val();
    			var input_blur=$(this);
    			var field=tdobj.attr("class");
    			if(fieldClass=='del'){
    					return false;
    			}
    			if(text!=newText){
    				var elementid=tdobj.siblings(".elementid").html();
    				var operationalid=tdobj.siblings(".operationalid").html();
    				var url="";
    				if(field=='classtype'){
    					url="UpdateElement?elementid="+elementid+"&classtype="+newText;
    					alert(url);
    				}else if(field=='elementindex'){
    					url="UpdateElement?elementid="+elementid+"&elementindex="+newText;
    				}else if(field=='content'){
    					url="UpdateOperational?operationalid="+operationalid+"&content="+newText;
    				}else if(field=='text'){
    					url="UpdateElement?elementid="+elementid+"&text="+newText;
    				}
    				$.get(url,null);
    			}else{
    				tdobj.html(newText);
    			}	
    		});
    		inputobj.keydown(function(event){
    			var code=event.keyCode;
    			var input_keydown=$(this);
    			switch(code){
    				case 13:
    				     alert(1);
    					var newText=input_keydown.val();
    					var field=tdobj.attr("class");
    					if(text!=newText){
		    				var elementid=tdobj.siblings(".elementid").html();
		    				var operationalid=tdobj.siblings(".operationalid").html();
		    				var url="";
		    				if(field=='classtype'){
		    					url="UpdateElement?elementid="+elementid+"&classtype="+newText;
		    				}else if(field=='elementindex'){
		    					url="UpdateElement?elementid="+elementid+"&elementindex="+newText;
		    				}else if(field=='content'){
		    					url="UpdateOperational?operationalid="+operationalid+"&content="+newText;
		    				}else if(field=='text'){
		    					url="UpdateElement?elementid="+elementid+"&text="+newText;
		    				}
		    				$.get(url,null);
			    			}else{
			    				tdobj.html(newText);
			    			}	
    					break;
    					case 27:
    						 tdobj.html("");
    						 tdobj.html(text);
    						 break;		
    			}
    		});
    	  }
    		
    	});
    });
    $(document).keydown(function(event){
    	switch(event.keyCode){
    		case 13:
    			return false;
    	}
    });
	function todo()
	{
       var pageindex=document.getElementById("pageindex");      
       var select_Value = pageindex.options[pageindex.selectedIndex].value;
       window.location.href="";
	}
	
</script>    
  </head>
  
<body style="">
  <table border="0" align="center" cellpadding="0" cellspacing="0" class="tb_title">
  <tr>
    <th align="left" valign="middle">元素信息</th>
    <td>&nbsp;</td>
  </tr>
  </table>
	<p></p>
	<p></p>
	<p></p>
<div>
	<div style="width:100%;">
		<div style="float:left;width:90%;"></div>
		<div style="float:right;width:10%;"><input type="button" value="返回" onclick="javascript:history.go(-1)"/></div>
		
	</div>
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0"  id="searchTable">
  <tr>
    <td height="30" background="<%=basePath %>resources/images/tab_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="30"><img src="<%=basePath %>resources/images/tab_03.gif" width="12" height="30" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="5%"><div align="center"><img src="<%=basePath %>resources/images/tb.gif" width="16" height="16" /></div></td>
                <td width="95%" class="STYLE1"></td>
              </tr>
            </table></td>
            <td width="54%"><table border="0" align="right" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60"><table width="87%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center">
                      <input type="checkbox" name="checkbox62" value="checkbox" />
                    </div></td>
                    <td class="STYLE1" style="line-height:20px;height:20px;"><div align="center">全选</div></td>
                  </tr>
                </table></td>
                <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="<%=basePath %>resources/images/tb.gif" width="14" height="14" onclick="showAddWin()"/></div></td>
                    <td class="STYLE1"><div align="center" style="line-height:20px;height:20px;"><a onclick="ShowDiv('MyDiv','fade')" href="#" mce_href="#">新增</a></div></td>
                  </tr>
                </table></td>
                <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="<%=basePath %>resources/images/del.gif" width="14" height="14" /></div></td>
                    <td class="STYLE1"><div align="center" style="line-height:20px;height:20px;">删除</div></td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="<%=basePath %>resources/images/tab_07.gif" width="16" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" background="<%=basePath %>resources/images/tab_12.gif">&nbsp;</td>
        <td>
    <form action="">
    	<input type="text" id="pageid" value="${pageid}" style="display:none;"/>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6" id="edittable">
          <tr>
          	 <td width="3%" height="20" background="<%=basePath %>resources/images/bg.gif" bgcolor="#FFFFFF"><div align="center">
              <input type="checkbox" name="checkbox" value="checkbox" />
            </div></td>
            <td width="" height="20" background="<%=basePath %>resources/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1" style="line-height:20px;height:20px;">页面名称</span></div></td>
            <td width="" height="20" background="<%=basePath %>resources/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1" style="line-height:20px;height:20px;">文本</span></div></td>
            <td width="" height="20" background="<%=basePath %>resources/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1" style="line-height:20px;height:20px;">元素类名</span></div></td>
            <td width="" height="20" background="<%=basePath %>resources/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1" style="line-height:20px;height:20px;">元素排序位置</span></div></td>
            <td width="" height="20" background="<%=basePath %>resources/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1" style="line-height:20px;height:20px;">元素操作</span></div></td>
            
            <td width="" height="20" background="<%=basePath %>resources/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1" style="line-height:20px;height:20px;" >操作</span></div></td>
          </tr> 
          <tbody>
         <c:forEach items="${elmentlist}" var="element" varStatus="vs">
          <tr class="STYLE1">
          <td height="20" bgcolor="#FFFFFF"><div align="center">
              <input type="checkbox" name="checkbox2" value="checkbox" />
            </div></td>
            <td height="20" bgcolor="#FFFFFF"  style="line-height:20px;height:20px;text-align:center;width:180px;" class="pagename">${element.pagename}</td>
            <td height="20" bgcolor="#FFFFFF"  style="line-height:20px;height:20px;text-align:center;width:100px;" class="text">${element.text}</td>
            <td height="20" bgcolor="#FFFFFF"  style="line-height:20px;height:20px;text-align:center;width:100px;" class="classtype">${element.classtype}</td>
            <td height="20" bgcolor="#FFFFFF"  style="line-height:20px;height:20px;text-align:center;width:180px;" class="elementindex">${element.elementindex}</td>
            <td height="20" bgcolor="#FFFFFF"  style="line-height:20px;height:20px;text-align:center;width:180px;" class="content">${element.content}</td>
           <c:choose>
           		<c:when test="${element.resultid==2}">
           			<td height="20" bgcolor="#FFFFFF"><div align="center" class="del">
		            </div>
		            </td>
           		</c:when>
           		<c:otherwise>
           			<td height="20" bgcolor="#FFFFFF"><div align="center" class="del"><span class="STYLE4">
		            <img src="<%=basePath %>resources/images/del.gif" align="absmiddle" width="16" height="20" /><a onclick="del(${element.elementid},${element.pageid},${element.operationalid})">删除</a></span>
		            </div>
            </td>
           		</c:otherwise>
           </c:choose>
            <td height="20" bgcolor="#FFFFFF"  style="line-height:20px;height:20px;text-align:center;display:none;" class="elementid">${element.elementid}</td>
            <td height="20" bgcolor="#FFFFFF"  style="line-height:20px;height:20px;text-align:center;display:none;" class="operationalid">${element.operationalid}</td>
          </tr>
         </c:forEach>
    	 </tbody>
        </table>
            </form>
        </td>
        <td width="8" background="<%=basePath %>resources/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table>
    </td></tr>
  <tr>
    <td height="35" background="<%=basePath %>resources/images/tab_19.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="<%=basePath %>resources/images/tab_18.gif" width="12" height="35" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4">&nbsp;&nbsp;共有条记录，当前第页</td>
            <td><table border="0" align="right" cellpadding="0" cellspacing="0">
               <tr>
                  <td width="40"><a href="showClientServlet?pageIndex=1"><img border="0" src="<%=basePath %>resources/images/first.gif" width="37" height="15" /></a></td>
                  <td width="45"><a href="showClientServlet?pageIndex=1"><img border="0" src="<%=basePath %>resources/images/back.gif" width="43" height="15" /></a></td>
                   <td width="45"><a href=""><img border="0" src="<%=basePath %>resources/images/back.gif" width="43" height="15" /></a></td>
                  <td width="45"><a href=""><img border="0" src="<%=basePath %>resources/images/next.gif" width="43" height="15" /></a></td>
                   <td width="45"><a href=""><img border="0" src="<%=basePath %>resources/images/next.gif" width="43" height="15" /></a></td>
                  <td width="40"><a href=""><img border="0" src="<%=basePath %>resources/images/last.gif" width="37" height="15" /></a></td>
                  <td width="100"><div align="center"><span class="STYLE1">转到第              
                    <select name="pageindex" id="pageindex" size="1" style="height:18px; width:36px; border:1px solid #999999;">
					  <option value=""></option>
					</select>
                    页 </span></div></td>
                  <td width="40"><a href="javascript:todo()"><img border="0" src="<%=basePath %>resources/images/go.gif" width="37" height="15" /></a></td>
                </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="<%=basePath %>resources/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
<div id="fade" class="black_overlay">
</div>
<div id="MyDiv" class="white_content">
<div style="text-align: right; cursor: default; height: 40px;">
<span style="font-size: 16px;" onclick="CloseDiv('MyDiv','fade')"><img src="<%=basePath %>resources/images/del.gif"></img></span>
</div>
   <div id="addElementDetail" >
	  		<h1 class="title_lb">新增元素信息</h1>
				<div class="span12" id="add_nodo">
					<p class="notice span3">输入元素操作信息</p>
					<div class="inputBox">
						<label for="classtype">元素类型:</label>
						<input type="text"	name="classtype" id="classtype" class="span6" onkeyup="queryTypeData(this,'GetElementClasstype')"/>
						<ul class="contentTip">
						</ul>
					</div>
					<div class="inputBox">
						<label> 元素文本:</label>
						<input	type="text" name="text" id="elementText" class="span6" />
					</div>
					<div class="inputBox">
						<label for="elementindex">同类元素排序位置:</label>
						<input type="text" name="elementindex" id="elementindex" class="span6" onchange="value=value.replace(/[^\d]/g,'') " onkeyup="value=value.replace(/[^\d]/g,'') "  onafterpaste="value=value.replace(/[^\d]/g,'')"/>
					</div>	
					<div class="inputBox">
						<label for="content"> 操作:</label>
						<input type="text" name="content" id="content" class="span6" onkeyup="queryContentData(this,'GetOperContent')"/>
						<ul class="operationalTip">
						</ul>
					</div>
					<div class="inputBox">
						<label for="resultid">操作结果：</label> 
						<select id="resultid" class="span6">
	                         <option value=1 >产生效果</option>
	                          <option value=3 >元素替换</option>
	                       </select>
	                     </div >
					<div class="submitBox">
						<input type="button" id="add" value="确定" class="ok" ></input>
					</div>
				</div>
				
			</div>
</div>
<script type="text/javascript" language="javascript">
	function queryTypeData(element,url){
            var uri=url+"?classtype="+$(element).val();
            $.ajax({
              type: "post",
              //查询参数
              data:{},
              url: uri,
              dataType:"json",
              success: function(data,status) {
                var objs=eval(data);
		        var obj=objs[0];
		        var elementList=obj.elementList;
		        if(elementList.length==0){
		        	$('.inputBox>ul.contentTip').fadeOut();
		        }else{
		        	$(element).parent().find('ul.contentTip').fadeIn("fast");
                	$(element).parent().find('ul.contentTip').children().remove();
	                for(var item in elementList){
	                    var _i=$("<li>"+elementList[item].classtype+"</li>");
	                    $(element).parent().find('ul.contentTip').append(_i);
	                }
                }
                $('.inputBox>ul.contentTip>li').each(function(index,element){
	                $(this).click(function(){
	                    $(element).parent().parent().find("input[type='text']").val($(element).text());
	                    // 清理缓存
	                    // $(element).parent().parent().find("ul.contentTip").children().remove();
	                    $('.inputBox>ul.contentTip').fadeOut();
	                })
           	      });
           	      document.onclick = hideTip;
              },
              error:function(data,status,e){
                alert("请求失败");
              }
            });
        }
         function queryContentData(element,url){
            var uri=url+"?content="+$(element).val();
            $.ajax({
              type: "post",
              //查询参数
              data:{},
              url: uri,
              dataType:"json",
              success: function(data,status) {
                var objs=eval(data);
		        var obj=objs[0];
		        var operList=obj.operList;
		        if(operList.length==0){
		        	$('.inputBox>ul.operationalTip').fadeOut();
		        }else{
		        	$(element).parent().find('ul.operationalTip').fadeIn("fast");
                    $(element).parent().find('ul.operationalTip').children().remove();
	                for(var item in operList){
	                    var _i=$("<li>"+operList[item].content+"</li>");
	                    $(element).parent().find('ul.operationalTip').append(_i);
	                }
                }
                $('.inputBox>ul.operationalTip>li').each(function(index,element){
	                $(this).click(function(){
	                    $(element).parent().parent().find("input[type='text']").val($(element).text());
	                    // 清理缓存
	                    // $(element).parent().parent().find("ul.contentTip").children().remove();
	                    $('.inputBox>ul.operationalTip').fadeOut();
	                })
           	      });
           	       document.onclick = hideOper;
              },
              error:function(data,status,e){
                alert("请求失败");
              }
            });
        }
         
         function hideTip(){
         	$('.inputBox>ul.contentTip').fadeOut();
         }
         function hideOper(){
         	$('.inputBox>ul.operationalTip').fadeOut();
         }
</script>
</body>
</html>