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
        <div class="listtit"><div class="listtittext fl">产品销售</div>
            <p class="fr">
                您现在的位置：<a href="${path}/page/products">产品销售</a>>
                <a href="${path}/page/product?id=${classifyInfo.id}">${classifyInfo.name}</a>>
                ${info.name}
            </p>
            <div class="clear"></div>
        </div>

        <div class="prolistcont">
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
                <div>
                <div style="text-align: center;margin: 0 auto;">
                        <a class="probtn" href="${path}/download/file?fileName=${info.atta_name}&url=${info.atta_path}">资料下载</a>
                        <a class="probtn" href="mailto:ldt@ldt.cn">价格咨询</a>
                </div>
                </div>
            </div>
            <div style="clear: both;">
                ${info.content}
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<%@include file="end.jsp"%>
</body>
</html>
