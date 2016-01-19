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

    <title>业绩详情</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link href="${path}/static/css/public.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/static/css/else.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/static/css/index.css" type="text/css" rel="stylesheet" />
    <link media="all" href="${path}/static/sea-modules/alice/table/1.0.1/table.css" rel="stylesheet">
    <link media="all" href="${path}/static/sea-modules/alice/paging/1.0.1/paging.css" rel="stylesheet">
    <link href="${path}/static/sea-modules/alice/form/1.0.2/form.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/static/sea-modules/alice/grid/1.0.0/grid.css" type="text/css" rel="stylesheet"/>
    <!--导航引用js-->
    <script src="${path}/static/js/nav.js" type="text/javascript"></script>
</head>

<body>

<!-- 网页头部 -->
<%@include file="head.jsp"%>
<div class="wrap moa">
    <img class="mar_b10" src="<%=basePath%>/static/images/listbanner.jpg"/>

    <div class="wl210 fl">
        <h2>关于我们</h2>
        <ul class="listnav">
            <li><a href="${path}/toView?view=aboutint">公司简介</a></li>
            <li><a href="${path}/page/aboutque">公司资质</a></li>
            <li><a href="${path}/page/aboutper" class="hover">公司业绩</a></li>
            <li><a href="${path}/page/aboutvid">公司视频</a></li>
        </ul>
    </div>
    <div class="wr730 bor_e7e7e7 fr minheight600">
        <div class="listtit">
            <div class="listtittext fl">公司业绩</div>
            <p class="fr">${info.name}</p>
            <div class="clear"></div>
        </div>
        <div class="listcont">
            <div>
                <div style="border:1px solid #e7e7e7;">
                    <DIV id=demo style="OVERFLOW: hidden; margin-top: 9px;"><!--修改显示区域的宽度-->
                        <TABLE cellSpacing=0 cellPadding=0 border=0>
                            <TBODY>
                            <TR>
                                <TD id=demo1>
                                    <!--滚动部分表格开始-->
                                    <table width="1000" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <c:forEach items="${proFile}" var="yeji">
                                                <td width="300" align="center">
                                                    <img src="<%=basePath%>${yeji.path}" width="210" height="160" />
                                                </td>
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
                </div>
            </div>
            <div style="clear: both;">
                ${info.content}
            </div>
        </div>
    </div>
</div>
<div class="clear"></div>
</div>
<!-- 尾部模块 -->
<%@include file="end.jsp"%>
</body>
</html>
