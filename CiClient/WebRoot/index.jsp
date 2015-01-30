<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>测试报告</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath %>resources/js/FusionCharts.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/content/report.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/js/jquery-1.8.3.min.js"></script>
	
	<link rel="stylesheet" href="<%=basePath %>resources/css/report.css" type="text/css" />
	<style>
		.STYLE1 {font-size: 12px;
				 font-weight:500;
		}
		.title {font-size: 12px;
				 font-weight:400;
		}
		.STYLE2 {font-size: 14px;
				 font-weight:600;
				 font-family:NSimSun ;
				 color:#344BAA;
		}
	</style>
	<script>
	function MM_over(mmObj) {
		var mSubObj = mmObj.getElementsByTagName("div")[0];
		mSubObj.style.display = "block";
		mSubObj.style.backgroundColor = "#7da4f3";
	}
	function MM_out(mmObj) {
		var mSubObj = mmObj.getElementsByTagName("div")[0];
		mSubObj.style.display = "none";
		
	}
</script>
  </head>
  
  <body>
  <div style="height:100%;">
  <center>
    <table width="90%" height="100%" align="center" cellpadding="0" cellspacing="0" class="tb_form">
    <tr style="height:60px;">
	    <td>
	     <table width="96.5%" height="10%" align="center" cellpadding="0" cellspacing="0" class="tabletitle">
	     <tr class="title"><td><h1>应用商店测试报告</h1></td></tr>
	     </table>
	     </td>
    </tr>
    <tr style="height:10px;">
    	<td colspan="2">
    	</td>
    </tr>
    <tr style="height:180px;">
       <td colspan="2">
    	<table width="96.5%" height="100%" align="center" cellpadding="0" cellspacing="0" class="cszj">
	     <tr style="height:20%;"><td style="width:10px;background-color:#B92525;"></td>
	     <td class="cezjtitle">一、测试报告总结</td>
	     </tr>
	     <tr >
	     <td colspan="2" class="cszjtd"> 
	     <span class="title">
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 12px;font-weight:600;">应用商店（6.3.4）</span>第一次测试结束，测试通过。<br/><br/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、功能性测试：本次测试未发现BUG，测试通过。<br/><br/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、稳定性测试：10小时monkey脚本无crash、ANR，测试通过。
		</span>
	     </td>
	     </tr>
	     </table>
	    </td>
    </tr>
    <tr style="height:10px;">
    	<td colspan="2">
    	</td>
    </tr>
    <tr style="height:300px;">
     <td colspan="2">
     	<table width="96.5%" height="100%" align="center" cellpadding="0" cellspacing="0" class="cszj">
	     <tr style="height:12%;"><td style="width:10px;background-color:#B92525;"></td>
	     <td class="cezjtitle">二、UI测试统计图</td>
	     </tr>
	     <tr >
	     <td colspan="2" class="cszjtd" id="grid1"> 
	     	<script>
	     		var chart2= new FusionCharts("<%=basePath%>resources/Charts/Pie3D.swf", "grid2", "100%", "100%", "1", "0");
				strstart ="<chart palette='4' decimals='0' enableSmartLabels='1' enableRotation='0' bgColor='FFFFFF,FFFFFF' bgAlpha='40,100' bgRatio='0,100' bgAngle='360' showBorder='0' startingAngle='80' caption='测试通过率'>";
				var strend ="</chart>";
			    strstart =strstart+"<set label='测试通过' value='50' isSliced='1' /><set label='测试失败' value='30'/>";	
				strstart =strstart+strend;
				chart2.setDataXML(strstart);
			    chart2.render("grid1");
	     	</script>
	     </td>
	     </tr>
	     </table>
     </td>
    </tr>
    <tr style="height:10px;">
    	<td colspan="2">
    	</td>
    </tr>
    <tr style="height:250px;">
     <td colspan="2">
     	<table width="96.5%" height="100%" align="center" cellpadding="0" cellspacing="0" class="cszj">
	     <tr style="height:20%;"><td style="width:10px;background-color:#B92525;"></td>
	     <td class="cezjtitle"><span>三、UI测试各个模块通过率</span></td>
	     </tr>
	     <tr >
	     <td colspan="2" class="cszjtd"> 
	     	<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#EEEEEE" >
          <tr>
            <td width="20%" height="45"  bgcolor="#F7F7F7">
            	<div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块一</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：20</span></div>
            		</div>
            		</div>
            		</td>
            <td width="30%" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">90%</span></div></td>
            <td width="20%" height="50"  bgcolor="#F7F7F7">
            <div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块二</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：30</span></div>
            		</div>
            	</div></td>
            <td width="30" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">80%</span></div></td>
          </tr>
          <tr>
            <td width="20%" height="50"  bgcolor="#F7F7F7">
           <div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块三</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：45</span></div>
            		</div>
            	</div></td>
            <td width="30%" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">50%</span></div></td>
            <td width="20%" height="50"  bgcolor="#F7F7F7">
            <div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块四</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：47</span></div>
            		</div>
            	</div></td>
            <td width="30" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">60%</span></div></td>
          </tr>
          <tr>
            <td width="20%" height="50"  bgcolor="#F7F7F7">
            <div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块五</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：28</span></div>
            		</div>
            	</div></td>
            <td width="30%" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">70%</span></div></td>
            <td width="20%" height="50"  bgcolor="#F7F7F7">
            <div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块六</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：40</span></div>
            		</div>
            	</div></td>
            <td width="30" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">75%</span></div></td>
          </tr>
          <tr>
            <td width="20%" height="50"  bgcolor="#F7F7F7">
            <div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块七</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：30</span></div>
            		</div>
            	</div></td>
            <td width="30%" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">36%</span></div></td>
            <td width="20%" height="50"  bgcolor="#F7F7F7">
           <div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块八</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：46</span></div>
            		</div>
            	</div></td>
            <td width="30" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">85%</span></div></td>
          </tr>
	     		
	     	</table> 
	     </td>
	     </tr>
	     </table>
     </td>
    </tr>
    <tr style="height:10px;">
    	<td colspan="2">
    	</td>
    </tr>
    <tr style="height:300px;">
     <td colspan="2">
     	<table width="96.5%" height="100%" align="center" cellpadding="0" cellspacing="0" class="cszj">
	     <tr style="height:12%;"><td style="width:10px;background-color:#B92525;"></td>
	     <td class="cezjtitle">四、接口测试统计图</td>
	     </tr>
	     <tr >
	     <td colspan="2" class="cszjtd" id="grid123"> 
	     	<script>
	     		var chart2= new FusionCharts("<%=basePath%>resources/Charts/Pie3D.swf", "grid3", "100%", "100%", "1", "0");
				strstart ="<chart palette='4' decimals='0' enableSmartLabels='1' enableRotation='0' showPercentValues='1' bgColor='FFFFFF,FFFFFF' bgAlpha='40,100' bgRatio='0,100' bgAngle='360' showBorder='0' startingAngle='80' caption='测试通过率' subCaption='100个测试用例'>";
				var strend ="</chart>";
			    strstart =strstart+"<set label='测试通过'  value='50' isSliced='1' /><set label='测试失败' value='30'/>";	
				strstart =strstart+strend;
				chart2.setDataXML(strstart);
			    chart2.render("grid123");
	     	</script>
         </td>
	     </tr>
	     </table>
     </td>
    </tr>
    <tr style="height:10px;">
    	<td colspan="2">
    	</td>
    </tr>
    <tr style="height:250px;">
     <td colspan="2">
     	<table width="96.5%" height="100%" align="center" cellpadding="0" cellspacing="0" class="cszj">
	     <tr style="height:20%;"><td style="width:10px;background-color:#B92525;"></td>
	     <td class="cezjtitle">五、接口测试各个模块通过率</td>
	     </tr>
	     <tr >
	     <td colspan="2" class="cszjtd"> 
	     	<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#EEEEEE" >
          <tr>
            <td width="20%" height="45"  bgcolor="#F7F7F7">
            	<div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块一</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：20</span></div>
            		</div>
            	</div></td>
            <td width="30%" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">30%</span></div></td>
            <td width="20%" height="50"  bgcolor="#F7F7F7">
            <div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块二</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：20</span></div>
            		</div>
            	</div></td>
            <td width="30" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">90%</span></div></td>
          </tr>
          <tr>
            <td width="20%" height="50"  bgcolor="#F7F7F7">
           <div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块三</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：20</span></div>
            		</div>
            	</div></td>
            <td width="30%" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">85%</span></div></td>
            <td width="20%" height="50"  bgcolor="#F7F7F7">
            <div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块四</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：20</span></div>
            		</div>
            	</div></td>
            <td width="30" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">95%</span></div></td>
          </tr>
          <tr>
            <td width="20%" height="50"  bgcolor="#F7F7F7">
            <div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块五</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：20</span></div>
            		</div>
            	</div></td>
            <td width="30%" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">98%</span></div></td>
            <td width="20%" height="50"  bgcolor="#F7F7F7">
            <div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块六</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：20</span></div>
            		</div>
            	</div></td>
            <td width="30" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">80%</span></div></td>
          </tr>
          <tr>
            <td width="20%" height="50"  bgcolor="#F7F7F7">
            <div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块七</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：20</span></div>
            		</div>
            	</div></td>
            <td width="30%" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">100%</span></div></td>
            <td width="20%" height="50"  bgcolor="#F7F7F7">
           <div align="center">
            		<div style="float:left;margin-left:20px;">
            			<span class="STYLE1" style="line-height:50px;height:50px;width:60%;text-align:center;">模块八</span>
            		</div>
            		<div id="menu" onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="position:relative;float:right;">
            			<a id="adetail"><img src="<%=basePath %>resources/images/coin.jpg" width="20"/></a>
            			<div style="width:250px;height:25px;display:none;position:absolute;left:20px;top:15px;z-inde:5;" class="border"><span style="color:#FFFFFF;font-size: 12px;line-height:25px;">测试用例总数：20</span></div>
            		</div>
            	</div></td>
            <td width="30" height="50"  bgcolor="#FFFFFF"><div align="left" style="margin-left:15px;"><span class="STYLE2" style="line-height:50px;height:50px;">100%</span></div></td>
          </tr>
	     		
	     	</table> 
	     </td>
	     </tr>
	     </table>
     </td>
    </tr>
    </table>
   </center>
   </div>
  </body>
</html>
