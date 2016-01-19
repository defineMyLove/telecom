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

    <title>公司视频</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" href="<%=basePath%>/static/css/style.css" type="text/css"/>
    <script src="${path}/static/js/flowplayer/flowplayer-3.2.13.min.js"></script>
<style type="text/css">
    .img-size{
        width: 92px;
        height: 64px;
    }
</style>

<body>

<div style="padding-top: 50px">
    <a href="${path}${file_path}"
       style="display:block;width:425px;height:300px;"
       id="player">
    </a>

</div>
    <script language="JavaScript">
        flowplayer("player", "${path}/static/js/flowplayer/flowplayer-3.2.18.swf");
    </script>
</body>
</html>
