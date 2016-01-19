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
    
    <title>解决方案</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

      <link href="${path}/static/css/public.css" type="text/css" rel="stylesheet"/>
      <link href="${path}/static/css/else.css" type="text/css" rel="stylesheet"/>
      <link href="${path}/static/css/index.css" type="text/css" rel="stylesheet" />
      <!--导航引用js-->
      <script src="${path}/static/js/nav.js" type="text/javascript"></script>

      <style type="text/css">
          #ybskb {
              border-bottom: 1px solid #CCCCCC;
              float: left;
              height: 110px;
              margin-bottom: 30px;
              margin-right: 5px;
              text-align: left;
              width: 290px;
          }
          a {
              text-decoration: none;
              color: #000000
          }
          .lanzad {
              color: #000000;
              font-size: 12px;
              font-weight: bold;
              letter-spacing: -1px;
              line-height: 28px;
          }
      </style>
  </head>
  
  <body>
  	
  	<!-- 网页头部 -->
  	<%@include file="head.jsp"%>
    <div class="wrap moa">
        <img class="mar_b10" src="<%=basePath%>/static/images/listbanner.jpg"/>

        <div class="wl210 fl">
            <h2>解决方案</h2>
            <ul class="listnav">
                <c:forEach items="${solutionList}" var="solution">
                    <li><a href="${path}/page/solution?fenlei_id=${solution.id}" <c:if test="${solution.id==currId}"> class="hover"</c:if>>${solution.name}</a></li>
                </c:forEach>
            </ul>
        </div>

        <div class="wr730 bor_e7e7e7 fr minheight600">
            <div class="listtit">
                <div class="listtittext fl">解决方案</div>
                <p class="fr"></p>

                <div class="clear"></div>
            </div>
            <div class="listcont">
                <c:if test="${!empty infos}">
                    <c:forEach items="${infos}" var="info">
                        <div id="ybskb"><table width="290" border="0" cellspacing="0" cellpadding="0">
                            <tbody><tr>
                                <td align="left" class="lanzad" colspan="2"><a href="${path}/page/solutionDetail?id=${info.id}">${info.name}</a></td>
                            </tr>
                            <tr>
                                <td width="105" valign="top" height="80" align="center">
                                    <div id="ybsbk"><img width="92" height="64" src="${path}${info.pic_path}"></div></td>
                                <td width="185" valign="top" class="heiz">
                                </td>
                            </tr>
                            </tbody></table>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    </div>
	<%@include file="end.jsp"%>
  </body>
</html>
