<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <title>产品详情</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
    <!-- app css -->
    <link href="${path}/js/category/main2.css" rel="stylesheet" type="text/css"/>
    <link href="${path}/js/category/vmcss2.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<article class="breadcrumbvm" id="breadcrumb">
    <header>
        <a id="btn-back" href="javascript:history.go(-1);"></a>
        <span id="pageTitle">产品详情</span>
    </header>
</article>
<div id="tab" style="display: block;overflow:auto;font-size:16px;top:65px;bottom: 5em;position:absolute;max-width:720px;padding:5px;">
    ${info.content}
</div>
<section class="pro-property-action-area" id="pro-operation">
    <article class="pro-property-action">
        <a id="prdDetailBuyNow" href="javascript:window.open('${path}/service/telcom/input?chanpin_id=${info.id}');" class="button-buy"><span>立即下单</span></a>
    </article>
</section>
</body>
</html>