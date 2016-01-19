<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/views/common/taglibs.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>文字从下向上显示</title>

    <link href="${path}/static/css/public.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/static/css/else.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/static/css/index.css" type="text/css" rel="stylesheet" />
    <!--导航引用js-->
    <script src="${path}/static/js/nav.js" type="text/javascript"></script>

    <style type="text/css">

        /* IE下的样式 */
       a.ellipsis{
            display: block;
            width:200px;/*对宽度的定义,根据情况修改*/
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }

    </style>
</head>

<body>
<table class="up" border="0" bordercolor="white"  cellpadding="5" cellspacing="0"  >
<tr>
<td style="padding-left:45px">

<script language=javascript>
document.write ("<marquee scrollamount='1' scrolldelay='30' direction= 'UP' width='240' id='YiMing' height='190' onmouseover='YiMing.stop()' onmouseout='YiMing.start()'  ")
document.write ("<p><font color='black' size='3px' line-height='30px'> ")
<c:forEach items="${newList}" var="new">
document.write ('<br><a title="${new.title}" class="ellipsis" href="${path}/page/newDetail?id=${new.id}">${new.title}</a>');
</c:forEach>
document.write ("</font>")
document.write ("</marquee> ")
</script>
</td>
</tr>
</table>
</body>
</html>
