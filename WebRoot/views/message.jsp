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
    
    <title>留言板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

      <link href="${path}/static/css/public.css" type="text/css" rel="stylesheet"/>
      <link href="${path}/static/css/else.css" type="text/css" rel="stylesheet"/>
      <link href="${path}/static/css/index.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="<%=basePath%>/static/css/common.css" type="text/css"/>
      <script src="${path}/static/sea-modules/sea.js"></script>
      <script src="${path}/static/seajs-config.js"></script>
      <!--导航引用js-->
      <script src="${path}/static/js/nav.js" type="text/javascript"></script>
  </head>
  <script type="text/javascript">
      seajs.use(['$', 'jquery-util'], function ($, jqueryUtil,pupZtree) {
          //全局变量
          window.$ = $;

          $.fn.serializeObject = function () {
              var o = {};
              var a = this.serializeArray();
              $.each(a, function () {
                  if (o[this.name]) {
                      if (!o[this.name].push) {
                          o[this.name] = [o[this.name]];
                      }
                      o[this.name].push(this.value || '');
                  } else {
                      o[this.name] = this.value || '';
                  }
              });
              return o;
          };

          //表单验证
          jqueryUtil.formValidate({
              form: "showForm",
              rules: {
                  "user_name": {required: true,maxlength:32},
                  "work_name": {required: true,maxlength:50},
                  "company_nam": {required: true,maxlength:100},
                  "company_youbian": {required: true,maxlength:20},
                  "company_tel": {required: true,maxlength:20},
                  "company_chuanzhen": {required: true,maxlength:50},
                  "company_address": {maxlength:100}
              },
              submitHandler: function (form) {
                  /*form.submit();*/
                  var mainParam = $('#showForm').serializeObject();
                  $.post('${path}/page/addMes',mainParam,function(){
                      alert("谢谢您的留言!");
                  })
                  return false;
              }
          });
      });
  </script>
  <body>
  	
  	<!-- 网页头部 -->
  	<%@include file="head.jsp"%>

    <div class="wrap moa">
        <img class="mar_b10" src="<%=basePath%>/static/images/listbanner.jpg"/>

        <div class="wl210 fl">
            <h2>技术交流</h2>
            <ul class="listnav">
                <li><a href="${path}/page/paper">论文专栏</a></li>
                <li><a href="${path}/page/data">产品资料</a></li>
                <li><a href="${path}/page/know">知识库</a></li>
                <li><a href="${path}/toView?view=message"  class="hover">留言板</a></li>
            </ul>
        </div>

        <div class="wr730 bor_e7e7e7 fr minheight600">
            <div class="listtit">
                <div class="listtittext fl">留言板</div>

                <div class="clear"></div>
            </div>
            <div class="listcont">
                <form id="showForm" method="post">
                    <table class="table-add">
                        <tr>
                            <td>您的姓名:</td>
                            <td class="tabRight required">
                                <input type="text" name="user_name"/>

                            </td>
                            <td>您的职务:</td>
                            <td class="tabRight required">
                                <input type="text" name="work_name"/>
                            </td>
                        </tr>
                        <tr>
                            <td>公司名称:</td>
                            <td class="tabRight required" colspan="3">
                                <input style="width:80%" type="text" name="company_nam"/>
                            </td>
                        </tr>
                        <tr>
                            <td>公司地址:</td>
                            <td class="tabRight" colspan="3">
                                <input style="width:80%" type="text" name="company_address"/>
                            </td>
                        </tr>
                        <tr>
                            <td>公司邮编:</td>
                            <td class="tabRight required">
                                <input type="text" name="company_youbian"/>
                            </td>
                            <td>电子邮件:</td>
                            <td class="tabRight">
                                <input type="text" name="email"/>
                            </td>
                        </tr>
                        <tr>
                            <td>公司电话:</td>
                            <td class="tabRight required">
                                <input type="text" name="company_tel"/>
                            </td>
                            <td>电子传真:</td>
                            <td class="tabRight required">
                                <input type="text" name="company_chuanzhen"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" style="text-align: center">您希望获得什么帮助</td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <textarea rows="8" cols="60" name="content"></textarea>
                            </td>
                        </tr>
                    </table>
                    <table style="width: 98%;margin-buttom: 5px;">
                        <tr>
                            <td align="center">
                                <input type="submit" id="submitBtn" value="保 存"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    </div>
	<%@include file="end.jsp"%>
  </body>
</html>
