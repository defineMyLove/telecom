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
    
    <title>工作机会</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


      <link href="${path}/static/css/public.css" type="text/css" rel="stylesheet"/>
      <link href="${path}/static/css/else.css" type="text/css" rel="stylesheet"/>
      <link href="${path}/static/css/index.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="<%=basePath%>/static/css/common.css" type="text/css"/>
      <!--导航引用js-->
      <script src="${path}/static/js/nav.js" type="text/javascript"></script>
  </head>
  
  <body>
  	
  	<!-- 网页头部 -->
  	<%@include file="head.jsp"%>

    <div class="wrap moa">
        <img class="mar_b10" src="<%=basePath%>/static/images/listbanner.jpg"/>
        <div class="bor_e7e7e7  minheight600">
            <div class="listtit">
                <div class="listtittext fl">工作机会</div>
                <div class="clear"></div>
            </div>
            <div class="listcont">
                <c:forEach items="${newList}" var="new">
                    <fieldset class="fieldset">
                        <legend class="legend">${new.title}</legend>
                            ${new.content}
                    </fieldset>
                </c:forEach>
            </div> </div>
    </div>
    <div class="clear"></div>
    </div>
  	
  		<!-- 尾部模块 -->

	<%@include file="end.jsp"%>

  
  </body>
</html>
