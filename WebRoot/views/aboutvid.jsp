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

    <link href="${path}/static/css/public.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/static/css/else.css" type="text/css" rel="stylesheet"/>
    <link href="${path}/static/css/index.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="<%=basePath%>/static/sea-modules/arale/dialog/1.2.4/dialog.css" type="text/css"/>
  <script src="${path}/static/sea-modules/sea.js"></script>
  <script src="${path}/static/seajs-config.js"></script>
      <!--导航引用js-->
      <script src="${path}/static/js/nav.js" type="text/javascript"></script>
  <style type="text/css">
      .img-size{
          width: 92px;
          height: 64px;
      }
  </style>
  <script type="text/javascript">
      seajs.use(['$', 'dialog'], function ($, Dialog) {
          //全局变量
          window.$ = $;
          //表单验证
          new Dialog({
              trigger: '.j-image',
              height: '350px',
              width: '425px',
              effect: 'fade',
          }).before('show',function() {
                      this.set('content', '${path}/toView?view=/playerframe&param=file_path:'+this.activeTrigger.attr('data-id'));
                  });

      });
  </script>
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
                <li><a href="${path}/page/aboutper">公司业绩</a></li>
                <li><a href="${path}/page/aboutvid"  class="hover">公司视频</a></li>
            </ul>
        </div>
        <div class="wr730 bor_e7e7e7 fr minheight600">
            <div class="listtit">
                <div class="listtittext fl">公司视频</div>
                <p class="fr">您现在的位置：公司视频</p>

                <div class="clear"></div>
            </div>
            <div class="listcont">
                <display:table name="newList" uid="new" cellpadding="0"
                               cellspacing="0" requestURI="${path}/page/aboutvid">
                    <display:column>
                        <div class="thumbwrapper img-size" style="cursor: pointer">
                            <img class="img-size j-image" src="${path}${new.pic_path}" data-id="${path}${new.file_path}">
                        </div>
                    </display:column>
                </display:table>
            </div>
        </div>
    </div>
    <div class="clear"></div>
    </div>
	<%@include file="end.jsp"%>
  </body>
</html>
