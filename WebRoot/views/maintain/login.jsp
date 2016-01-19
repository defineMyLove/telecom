<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="${path }/static/css/bootstrap-all.css"/>
    <script src="${path}/static/sea-modules/sea.js"></script>
    <script src="${path}/static/seajs-config.js"></script>
    <script type="text/javascript">

        seajs.use(['$','jquery-plugin/poshytip-1.1/jquery.poshytip.min'], function ($,poshytip) {
            window.$=$;

            //跳转提示信息
            var error = '${error}';
            if (error) {
                createLoginTip(error);
            }

            $('#loginForm').submit(function(){
                if ($.trim($('#userId').val()) == "" || $.trim($('#pwd').val()) == "") {
                    createLoginTip("请输入用户名或密码！");
                    return false;
                }
                this.submit();
            });

        });
        function createLoginTip(content){
            var element=$('#error');
            element.html(content);
            /*if(element.data('poshytipEd')){
                element.poshytip('update', content);
            }else{
                element.poshytip({
                    content:content,
                    className: 'tip-violet',
                    showOn: 'none',
                    alignTo: 'target',
                    alignX: 'inner-right'
                }).poshytip('show');
                element.data('poshytipEd',1);
            }*/
        }
    </script>
<style type="text/css">
    * {
        margin: 0;
        padding: 0;
    }

    body {
        background: #444 url(http://sandbox.runjs.cn/uploads/rs/418/nkls38xx/carbon_fibre_big.png)
    }

    .loginBox {
        width: 420px;
        height: 230px;
        padding: 0 20px;
        border: 1px solid #fff;
        color: #000;
        margin-top: 40px;
        border-radius: 8px;
        background: white;
        box-shadow: 0 0 15px #222;
        background: -moz-linear-gradient(top, #fff, #efefef 8%);
        background: -webkit-gradient(linear, 0 0, 0 100%, from(#f6f6f6), to(#f4f4f4));
        font: 12px/1.5em 'Microsoft YaHei';
        position: absolute;
        left: 50%;
        top: 50%;
        margin-left: -210px;
        margin-top: -115px;
    }

    .loginBox h2 {
        height: 45px;
        font-size: 20px;
        font-weight: normal;
    }

    .loginBox .left {
        border-right: 1px solid #ccc;
        height: 100%;
        padding-right: 20px;
    }
     .input{
        margin-left: 5px;
    }
</style>
</head>
<body>
<div class="container">
    <div class="loginBox row-fluid">
        <div >
            <form:form action="${path}/mainlogin/login" id="loginForm" >
            <h2>用户登录</h2>
            <p>用户名<input class="input" type="text" name="userId" id="userId" /></p>
            <p>密&nbsp;&nbsp;&nbsp;码<input class="input" type="password" name="pwd" id="pwd"/><span style="color:red;" id="error"></span></p>

                <div class="row-fluid">
                <%--<div class="span8 lh30"><label><input type="checkbox" name="rememberme"/>下次自动登录</label></div>--%>
                <div class="span1"><input type="submit" value=" 登录 " class="btn btn-primary"></div>
            </div>
            </form:form>
        </div>
        <%--<div class="span4 right" style="width:100px;">
            <h2>没有帐户？</h2>
           &lt;%&ndash; <div>


                <p><input type="button" value=" 注册 " class="btn"></p>
            </div>&ndash;%&gt;
        </div>--%>
    </div>
    <!-- /loginBox -->
</div>
<!-- /container -->
</body>
</html>
