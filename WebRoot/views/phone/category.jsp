<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <title>产品分类</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0" name="viewport">
    <meta name="renderer" content="webkit">
    <!-- app css -->
    <link href="${path}/js/category/main2.css" rel="stylesheet" type="text/css"/>
    <link href="${path}/js/category/vmcss2.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${path}/static/sea-modules/sea.js"></script>
    <script type="text/javascript" src="${path}/static/sea-modules/seajs-config.js"></script>
    <script type="text/javascript">
        seajs.use([ '$', 'app-util', 'avalon', 'niceScroll', 'todc-bootstrap', 'chosen'],
                function ($, appUtil, avalon) {
                    var pageVM = avalon.define({
                        $id: 'pageVM',
                        list: [],
                        pageCount: [],
                        //分页参数
                        page: {
                            pageNumber: 1,
                            pageSize: 30,
                            totalRow: 0,
                            totalPage: 0
                        },
                        pageQuery: function () {
                            $.ajax({
                                async: true,
                                type: "POST",
                                url: "${path}/service/productList",
                                success: function (response) {
                                    for(var i =0; i<response.length;i++) {
                                        response[i].current=false;
                                    }
                                    if(response.length!=0) { //默认选中第一个
                                        response[0].current=true;
                                    }
                                    pageVM.list = response;
                                    if(pageVM.list.length!=0) { //默认选中第一个
                                        pageVM.list[0].current=true;
                                        contentVM.list = pageVM.list[0].infoList;
                                    }
                                }
                            });
                        },
                        changeTab:function(el){
                            for(var i =0; i<pageVM.list.length;i++) {
                                pageVM.list[i].current = false;
                                if(pageVM.list[i].id==el.id) {
                                    contentVM.list = pageVM.list[i].infoList;
                                    pageVM.list[i].current = true;
                                }
                            }
                        }
                    });
                    window.pageVM = pageVM;
                    pageVM.pageQuery();

                    var contentVM = avalon.define({
                        $id: 'contentVM',
                        list: [],
                        pageCount: [],
                        //分页参数
                        page: {
                            pageNumber: 1,
                            pageSize: 30,
                            totalRow: 0,
                            totalPage: 0
                        },
                        detail:function(el){
                            window.open("${path}/service/detail?id="+el.id,'_self');
                        }
                    });
                    window.pageVM = pageVM;
                });
    </script>
    <style type="text/css">
        table td {
            vertical-align: middle !important;
        }

        .lv-title {
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            font-size: 16px;
            color: #000;
            margin-bottom: .1rem;
        }

       .lv-small{
           font-size: 12px;
           color: #A9A9A9;
           overflow: hidden;
           text-overflow: ellipsis;
           white-space: nowrap;
           width: 100%;
       }

    </style>
</head>
<body>
<article class="breadcrumbvm " id="breadcrumb">
    <header>
        <a id="btn-back" href="javascript:history.go(-1);"></a>
        <span id="pageTitle">产品分类</span>
    </header>
    <%--<!-- 21030531-捷径栏-start -->
    <section class="shortcut" id="shortcut-btn">
        <ul>
            <!-- 仅展示筛选按钮 -->
            <li id="btn-cart">
                <a id="prdDetailShoppingCart"
                   onclick="_hmt.push([&#39;_trackEvent&#39;, &#39;click icon2&#39;, &#39;click&#39;, &#39;记录【购物车】图标按钮的点击次数&#39;])"
                   href="http://m.vmall.com/shoppingCart" class="icon-shoppingCart" title="购物车">
                    <span id="cartNum">0</span>
                </a>
            </li>
            <!-- 仅展示首页按钮-->
        </ul>
    </section>--%>
    <!-- 21030531-捷径栏-end -->
</article>
<div class="hr-150-1"></div>
<article class="category">
    <!-- 20140722-一级分类选择-start -->
    <section class="category-left" ms-controller="pageVM" class="ms-controller">
        <ul>
            <li ms-repeat="list">
                <a href="javascript:;" ms-click="changeTab(el);return false;" data="{{el.id}}" ms-class-current="el.current">
                    <span>{{el.name}}</span>
                </a>
            </li>
        </ul>
    </section>
    <!-- 20140722-一级分类选择-end -->

    <!-- 20140722-二级分类选择-start -->
    <section class="category-right" ms-controller="contentVM" class="ms-controller">
        <ul>
            <li ms-repeat="list" ms-click="detail(el)" location.href="javascript;;">
                <div class="pro-panels">
                    <p class="p-img"><img ms-src="el.pic_path"></p>
                    <p class="p-name"><div>{{el.name}}</div><div><small class="lv-small">&nbsp;&nbsp;&nbsp;&nbsp; {{el.note}}</small></div></p>
                </div>
            </li>
        </ul>
    </section>
    <!-- 20140722-二级分类选择-end -->
</article>
</body>
</html>