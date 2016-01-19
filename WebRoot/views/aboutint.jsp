<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/views/common/taglibs.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
    <title>公司简介</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link href="${path}/static/css/public.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/static/css/else.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/static/css/index.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="${path}/static/sea-modules/jquery/jquery-1.8.3.min.js"/>
    <!--导航引用js-->
    <script src="${path}/static/js/nav.js" type="text/javascript"></script>
</head>
<body>

<!-- 网页头部 -->
<%@include file="head.jsp" %>

<div class="wrap moa">
    <img class="mar_b10" src="<%=basePath%>/static/images/listbanner.jpg"/>

    <div class="wl210 fl">
        <h2>关于我们</h2>
        <ul class="listnav">
            <li><a href="${path}/toView?view=aboutint"  class="hover">公司简介</a></li>
            <li><a href="${path}/page/aboutque">公司资质</a></li>
            <li><a href="${path}/page/aboutper">公司业绩</a></li>
            <li><a href="${path}/page/aboutvid">公司视频</a></li>
        </ul>
    </div>

    <div class="wr730 bor_e7e7e7 fr minheight600">
        <div class="listtit">
            <div class="listtittext fl">公司简介</div>
            <p class="fr">您现在的位置：公司简介</p>

            <div class="clear"></div>
        </div>
        <div class="listcont">
        </div>
    </div>
    <div class="clear"></div>
</div>
<!-- 尾部模块 -->
<%@include file="end.jsp" %>
</body>
</html>
