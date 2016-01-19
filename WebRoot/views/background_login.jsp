<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'background_login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>/static/css/style.css" type="text/css"></link>

  </head>
  
  <body>
  
	<div class="login">
	
	 	<img src="<%=basePath%>/static/images/background.png">
	 	
	 	    <form>
	 	    
				<b>用户名:</b> 
				<input type="text" name="firstname" />
				<br />
				<br />
				<b>密　码:</b> 
				<input type="text" name="lastname" /><br/>
				<input class="denglu" type="submit" value="登　陆" />
				
		    </form>
		    
	 	</img>
	 	
	 	
	</div>

  </body>
</html>
