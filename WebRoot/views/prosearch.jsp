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

    <title>产品销售</title>

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
        #cpxsz {
            background-image: url("../images/tb10.gif");
            background-position: left center;
            background-repeat: no-repeat;
            color: #4C4C4C;
            float: left;
            line-height: 22px;
            padding-left: 30px;
            text-align: left;
            width: 600px;
            list-style-type: none;
            margin: 0 auto;
            font-size: 12px;
        }

    </style>
</head>

<body>

<!-- 网页头部 -->
<%@include file="head.jsp"%>

<div class="wrap moa">
    <img class="mar_b10" src="${path}/static/images/listbanner.jpg" />
    <div class="wl130 fl">
        <ul class="prolistnav">
            <c:forEach items="${newList}" var="new">
                <li><a href="${path}/page/product?id=${new.id}">${new.name}</a></li>
            </c:forEach>
        </ul>
    </div>

    <div class="wr810 bor_e7e7e7 fr minheight700">
        <div class="listtit"><div class="listtittext fl">产品搜索</div></div>
        <div class="prolistcont">
            <c:if test="${empty pros}">
                您好，您所搜索的信息暂不存在！
            </c:if>
            <c:if test="${!empty pros}">
            <c:forEach items="${pros}" var="pro">
                <div style="float: left;height:auto;margin-right: 10px;margin-bottom: 10px;">
                    <a href="${path}/page/proDetail?id=${pro.id}">
                    <img width="180" border="0" src="${path}${pro.pic_path}">
                    </a>
                    <p align="center"><a href="${path}/page/proDetail?id=${pro.id}">${pro.name}</a></p>
                </div>
            </c:forEach>
            </c:if>
        </div>
    </div>
    <div class="clear"></div>
</div>
<%@include file="end.jsp"%>
</body>
</html>
