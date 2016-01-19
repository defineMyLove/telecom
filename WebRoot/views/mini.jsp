<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp" %>

<html>

<head>

<title> New Document </title>

<style type="text/css">
html,body{text-align:lift;margin:0; padding:0; background-color:#FFFFFF; font-family:"宋体", arial; font-size:12px; color:#333333;}
div,form,img,ul,ol,li,dl,dt,dd {margin: 0; padding: 0; border: 0;}
a{color:#333;}
</style>
 </head>

 <body>
  <DIV id=demo style="OVERFLOW: hidden; WIDTH:333px; HEIGHT: 248px; margin-top: 9px;"><!--修改显示区域的宽度-->

<TABLE cellSpacing=0 cellPadding=0 border=0>

<TBODY>

	<TR>

	<TD id=demo1>

	<!--滚动部分表格开始-->

		<table width="1000" border="0" cellspacing="0" cellpadding="0">

		  <tr>
              <c:forEach items="${yejiList}" var="yeji">

			<td width="300" align="center"><a href="#"><img src="<%=basePath%>${yeji.pic_path}" width="210" height="160" /><br/><br/>${yeji.name}</a></td>
              </c:forEach>

		  </tr>

		</table>

		<!--滚动部分表格结束-->

	</TD>

	<TD id=demo2></TD></TR></TBODY></TABLE>

</DIV>

<SCRIPT>

var speed3=30 //速度数值越大速度越慢

demo2.innerHTML=demo1.innerHTML

function Marquee(){

if(demo2.offsetWidth-demo.scrollLeft<=0)

demo.scrollLeft-=demo1.offsetWidth

else{

demo.scrollLeft++

}

}

var MyMar=setInterval(Marquee,speed3)

demo.onmouseover=function() {clearInterval(MyMar)}

demo.onmouseout=function() {MyMar=setInterval(Marquee,speed3)}

</SCRIPT>
 </body>
</html>