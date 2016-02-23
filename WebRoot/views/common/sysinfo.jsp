<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>sysinfo.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
      <script src="${path}/static/sea-modules/sea.js"></script>
      <script src="${path}/static/seajs-config.js"></script>
	<script type="text/javascript">
        seajs.use(['$', 'jquery-util'], function ($, jqueryUtil) {
            ${autoScript};
        });
	</script>
  </head>
  
  <body>
  </body>
</html>
