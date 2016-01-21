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
    <link href="${path}/static/css/bootstrap-all.css" rel="stylesheet" type="text/css"/>
    <link href="${path}/js/category/main2.css" rel="stylesheet" type="text/css"/>
    <link href="${path}/js/category/vmcss2.css" rel="stylesheet" type="text/css"/>

    <script src="${path}/static/sea-modules/sea.js"></script>
    <script src="${path}/static/seajs-config.js"></script>
    <script type="text/javascript">
        seajs.use(['$', 'jquery-util'], function ($, jqueryUtil) {
            //全局变量
            window.$ = $;
            //表单验证
            jqueryUtil.formValidate({
                form: "showForm",
                rules: {
                    "cus_tel": {required: true, isTel: true}
                },
                submitHandler: function (form) {
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
<article class="breadcrumbvm " id="breadcrumb">
    <header>
        <a id="btn-back" href="javascript:history.go(-1);"></a>
        <span id="pageTitle">输入您的信息</span>
    </header>
</article>
<div class="hr-150-1"></div>
<article class="category">
<div class="well" style="max-width: 400px; margin: 0 auto 10px;">
    <form:form id="showForm" method="post" action="${path}/service/telcom/add">
        <fieldset style="margin-bottom: 10px;">
            <p class="muted">请输入您的联系方式，我们会在再<abbr title="HyperText Markup Language" class="initialism">24小时内</abbr>给您联系，谢谢您的配合，希望合作愉快。
            </p>
        </fieldset>

        <div class="input-prepend input-append">
            <span class="add-on">姓名</span>
            <input class="span2" id="cus_name" name="cus_name" placeholder="请输入您的姓名" type="text">
        </div>
        <div class="input-prepend input-append">
            <span class="add-on">电话</span>
            <input class="span2" id="cus_tel"  name="cus_tel" placeholder="请输入您的电话" type="text">
            <span class="add-on">必填</span>
        </div>
        <div class="input-prepend input-append">
            <span class="add-on">身份证号</span>
            <input class="span2" id="cus_card_id" name="cus_card_id" placeholder="请输入您的身份证号" type="text">
        </div>
        <div class="input-prepend input-append">
            <span class="add-on">地址</span>
            <input class="span2" id="cus_address" name="cus_address" placeholder="请输入您的地址" type="text">
        </div>

        <div class="input-prepend input-append">
            <span class="add-on">发展人</span>
            <input class="span2" id="sale_id" name="sale_id" placeholder="请输入发展人编号" type="text">
        </div>
        <div style="max-width: 400px; margin: 0 auto 10px;">
            <button class="btn btn-large btn-block btn-primary" id="submit" type="submit">确定</button>
        </div>
        <input type="hidden" name="chanpin_id" value="${chanpin_id}"/>
    </form:form>
</div>
</article>
</body>
</html>