<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>业务处理异常</title>
    <link href="${path}/static/css/index.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/static/css/list.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/static/sea-modules/alice/tipbox/1.1.0/tipbox.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/static/sea-modules/alice/button/1.1.1/button.css" type="text/css" rel="stylesheet"/>
    <link media="all" href="${path}/static/sea-modules/alice/iconfont/1.0.0/iconfont.css" rel="stylesheet">
    <style type="text/css">
        a {
            color: #55A4F2;
            text-decoration: none;
        }
        a:hover{
            text-decoration:underline;
        }
    </style>
</head>
<body class="bodycont">
<div class="list_cont">
    <div class="list_cont_tit"></div>
    <div class="list_cont_ul"
         style="padding-bottom:8px; padding-left:30px; padding-right:30px; padding-top:5px; width:940px;">
        <div class="ui-tipbox ui-tipbox-error">
            <div class="ui-tipbox-icon">
                <i class="iconfont" title="出错">&#xF045;</i>
            </div>
            <div class="ui-tipbox-content">
                <h3 class="ui-tipbox-title">业务处理异常</h3>
                <p class="ui-tipbox-explain">${msg}</p>
                <p class="ui-tipbox-explain">
                    <a href="${path}/">去首页</a>
                    <c:if test="${!empty LOGIN_USER}">| <a href="${path}/user/userCenter">去用户中心</a> </c:if>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>