<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <title>输入联系方式</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0" name="viewport">
    <meta name="renderer" content="webkit">
    <!-- app css -->
    <link href="${path}/static/css/myapp.min.css" rel="stylesheet" type="text/css"/>
    <script src="${path}/static/sea-modules/sea.js"></script>
    <script src="${path}/static/sea-modules/seajs-config.js"></script>
    <script type="text/javascript">
        seajs.use(['$', 'validateUtil'], function ($, jqueryUtil) {
            //全局变量
            window.$ = $;
            //表单验证
            jqueryUtil.formValidate({
                form: "showForm",
                 rules: {
                 "cus_tel": {required: true, isTel: true}
                 },
                submitHandler: function (form) {
                  /*  if ($.trim($('[name=cus_tel]').val()) == '') {
                        alert("电话号码必须填写");
                    }*/
                    form.submit();
                    window.history.go(-1);
                }
            });
        });

        function submitForm() {
            $('#submitBtn')[0].click();
        }
    </script>
</head>
<body>
<%--<header id="header">
    <ul class="header-inner">
        <li id="menu-trigger" data-trigger="#sidebar" class="open">
            <div class="line-wrap">
                <div class="line top"></div>
                <div class="line center"></div>
                <div class="line bottom"></div>
            </div>
        </li>

        <li class="logo hidden-xs">
            <a href="index.html">输入联系方式</a>
        </li>
    </ul>
</header>--%>
<section id="main">
    <section id="content">
        <div class="container">


            <div class="card">
                <div class="card-header">
                    <h2>输入联系方式
                        <small>谢谢您的配合，我们会在24小时内联系您。</small>
                    </h2>
                </div>
                <form:form id="showForm" method="post" action="${path}/service/telcom/add">
                    <div class="card-body card-padding">


                        <div class="form-group">
                            <div class="fg-line">
                                <input type="text" name="cus_name" id="cus_name" class="form-control"
                                       placeholder="请输入您的姓名">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="fg-line">
                                <input type="text" name="cus_tel" id="cus_tel" class="form-control"
                                       placeholder="请输入您的电话">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="fg-line">
                                <input type="text" name="cus_card_id" id="cus_card_id" class="form-control"
                                       placeholder="请输入您的身份证号">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="fg-line">
                                <input type="text" name="cus_address" id="cus_address" class="form-control"
                                       placeholder="请输入您的地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="fg-line">
                                <input type="text" name="sale_id" id="sale_id" class="form-control"
                                       placeholder="请输入发展人编号">
                            </div>
                        </div>
                        <div class="card-padding card-body">
                            <input type="submit" id="submitBtn" class="btn btn-primary btn-block waves-effect"
                                   value="确定"/>
                        </div>
                        <input type="hidden" name="chanpin_id" value="${chanpin_id}"/>
                    </div>
                </form:form>
            </div>
        </div>
    </section>
</section>


</body>
</html>