<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>简单的滑动门制作</title>
</head>

<body>
<!-- Tab切换效果 --> 
<script type="text/javascript">
function setTab(name,cursel,n){	
	for(i=1;i<=n;i++){
	var menu=document.getElementById(name+i);
	var con=document.getElementById("con_"+name+"_"+i);
	menu.className=i==cursel?"hover":"";
	con.style.display=i==cursel?"block":"none";
	}
}
</script> 

<!-- CSS定义 -->
<style type="text/css">


#tabs {
	width:330px;
	height: 240px;
	margin:5px auto;
	overflow:hidden;
}
.Menubox {
	height:35px;


}
.Menubox ul {
	list-style:none;
	padding:0;
	position:relative;
	margin-top: 0px;
}
.Menubox ul li {
	font-size: 150%;
	font-family: YouYuan;
	float:left;
	display:block;
	cursor: pointer;
	width:100px;
	height: 37px;
	text-align:center;
	color:#000;
	border-bottom:1px #ccc solid;
	
}
.Menubox ul li span {
	margin-top: 5px;
	display: block;
	border: 1px #FFF solid;
}
.Menubox ul li.hover {
	background:#FFF 4px 4px;
	color:#147AB8;
	font-weight:700;
	border-bottom:2px #147AB8 solid;
}
.Contentbox {
	margin:0px;
	padding:5px 13px;
	height:180px;
	font: 13px/150% Tahoma,"宋体",Sans-serif;
	border:0;
	border-top:none;
}

.Contentbox span{
	font-size: 150%;
	font-family: Simsun;
	line-height: 29px;
	margin-top: 0px;
	margin-left: 15px;
}


</style>

<!-- 滑动门源码 -->

<div id="tabs">
<div class="Menubox">
  <ul>
    <li id="menu1" onmouseover="setTab('menu',1,2)" class="hover"><span>解决方案</span></li>
    <li id="menu2" onmouseover="setTab('menu',2,2)" ><span>最新产品</span></li>
   
  </ul>
</div>

<div class="Contentbox">
  <div id="con_menu_1" class="hover">
  	<br/>
     <c:forEach items="${head_solutionList}" var="solution">
         <span><img src="<%=basePath%>/static/images/027.png"/>
         <a href="<%=basePath%>/page/solutionDetail">${solution.name}</a>
         </span><br/>
     </c:forEach>
  </div>
  
  <div id="con_menu_2" style="display:none">
  	<img style="height:195px; width:285px; margin-left:13px;" src="${path}${head_pic}"/>
  </div>
  
</div>
  
</div>
</body>
</html>
