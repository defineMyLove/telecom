<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/views/common/taglibs.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<html>
<head>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link href="${path}/static/css/public.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/static/css/else.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/static/css/index.css" type="text/css" rel="stylesheet"/>
    <link media="all" href="${path}/static/sea-modules/alice/table/1.0.1/table.css" rel="stylesheet">
    <link media="all" href="${path}/static/sea-modules/alice/paging/1.0.1/paging.css" rel="stylesheet">
    <link href="${path}/static/sea-modules/alice/form/1.0.2/form.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/static/sea-modules/alice/grid/1.0.0/grid.css" type="text/css" rel="stylesheet"/>
    <!--导航引用js-->
    <script src="${path}/static/js/nav.js" type="text/javascript"></script>
    <title>新闻中心</title>
    <style type="text/css">
        /*.ui-table-container th,.ui-table-container td{
            font-size: 14px;
        }*/
        .ui-paging {
            margin-top: 5px;
        }

        .img-size {
            width: 100px;
            height: 75px;
        }

        .bigimg-size {
            width: 400px;
            height: 300px;
        }
    </style>

</head>

<body>

<!-- 网页头部 -->
<%@include file="head.jsp" %>

<div class="wrap moa">
    <img class="mar_b10" src="${path}/static/images/listbanner.jpg"/>

    <div class="bor_e7e7e7  minheight600">
        <div class="listtit">
            <div class="listtittext fl">新闻中心</div>

            <div class="clear"></div>
        </div>
        <div class="listcont">
            <display:table name="newList" uid="new" cellpadding="0"
                           cellspacing="0" requestURI="${path }/page/news">
                <display:column title="标题">
                    <a href="${path}/page/newDetail?id=${new.id}">${new.title}</a>
                </display:column>
                <display:column title="日期" property="create_time_str"></display:column>
            </display:table>
        </div>
    </div>
</div>
<div class="clear"></div>
</div>
<!-- 尾部模块 -->
<%@include file="end.jsp" %>
</body>
</html>
