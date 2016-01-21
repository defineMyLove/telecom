
<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html>
<!-- saved from url=(0034)http://www.80086.com:8888/publish/ -->
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>首页</title>
    <link href="${path}/js/index/bootstrap.min.css" rel="stylesheet">
    <link href="${path}/js/index/index.css" rel="stylesheet">
</head>
<body>
<div style="margin-bottom:5px;">

    <div class="row">
        <div class="col-xs-6">
            <a href="javascript:window.open('${path}/toView?view=phone/category');"  shopkey="7" title="电信业务"><img class="img-responsive"
                                                                                         src="${path}/js/index/tcdh3.png"></a>
        </div>
        <div class="col-xs-6">
            <a href="javascript:window.open('${path}/toView?view=phone/category');" shopkey="8" title="手机业务"><img class="img-responsive border_l"
                                                                                        src="${path}/js/index/tcdh4.png"></a>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-6">
            <a href="tel:18137869321"  shopkey="1" title="立即资讯"><img class="img-responsive"
                                                                                        src="${path}/js/index/tcdh1.png"></a>
        </div>
        <div class="col-xs-6">
            <a href="javascript:window.open('${path}/toView?view=phone/input');" shopkey="2" title="留下联系方式"><img class="img-responsive border_l"
                                                                                        src="${path}/js/index/tcdh2.png"></a>
        </div>
    </div>

    <div class="clear"></div>

</div>


<!------------电信------------>
<div class="product">
    <a href="javascript:;" class="product_menu shopPageProType" shopkey="7" title="电信">
        <img class="fl" src="${path}/js/index/sc_dianhuaka.png"><span>电信</span>

        <div class="more"></div>
        <div class="clear"></div>
    </a>

    <div class="row">
        <div class="col-xs-6">
            <a href="javascript:;" class="shopPageProItem" shopkey="125"
               title="史上最经济实惠的电信套餐来啦 只需59元，宽带免费带回家（含300分钟全国免费通话+1G国内流量）"><img class="img-responsive"
                                                                              src="${path}/js/index/dhk1.png"></a>
        </div>
        <div class="col-xs-6">
            <a href="javascript:;" class="shopPageProItem" shopkey="129"
               title="140元 你可以买到什么？=100M光纤宽带+3G国内流量+700分钟全国免费通话+4张副卡（成员互拨免300分钟）"><img class="img-responsive border_l"
                                                                                       src="${path}/js/index/dhk2.png"></a>
        </div>
    </div>
    <div class="clear"></div>
</div>
<!------------电话卡.end------------>
<!------------手机------------>
<div class="product">
    <a href="javascript:;" class="product_menu shopPageProType" shopkey="1" title="手机">
        <img class="fl" src="${path}/js/index/sc_sjico.png"><span>手机</span>

        <div class="more"></div>
        <div class="clear"></div>
    </a>

    <div class="row">
        <div class="col-xs-4">
            <a href="javascript:;" class="shopPageProItem" shopkey="26" title="iphone 6 （16G） 4800元起"><img
                    class="img-responsive" src="${path}/js/index/sj1.png"></a>

        </div>
        <div class="col-xs-4">
            <a href="javascript:;" class="shopPageProItem" shopkey="35" title="华为荣耀7 最热销的高配机"><img
                    class="img-responsive" src="${path}/js/index/sj2.png"></a>
        </div>
        <div class="col-xs-4">
            <a href="javascript:;" class="shopPageProItem" shopkey="46" title="红米note2(16G)淡粉色双卡双待"><img
                    class="img-responsive" src="${path}/js/index/sj3.png"></a>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-4">
            <a href="javascript:;" class="shopPageProItem" shopkey="87" title="三星S6 性能与美观，为你而生！"><img
                    class="img-responsive" src="${path}/js/index/sj4.png"></a>
        </div>
        <div class="col-xs-4">
            <a href="javascript:;" class="shopPageProItem" shopkey="74" title="魅族MX5"><img class="img-responsive"
                                                                                           src="${path}/js/index/sj5.png"></a>
        </div>
        <div class="col-xs-4">
            <a href="javascript:;" class="shopPageProItem" shopkey="103" title="X5Max  尊贵于型 有容于心！"><img
                    class="img-responsive" src="${path}/js/index/sj6.png"></a>
        </div>
    </div>
    <div class="clear"></div>
</div>
<!------------手机.end------------>

<script src="${path}/js/index/jquery.min.js"></script>
<script type="text/javascript">
    $(function(){
        //支持debug
        var shopApi=window.shopApi||{
            openHtml:function(link,title){
                alert("标题："+title+"\r\n链接："+link);
                window.open (link);
            },
            openProItem:function(itemId){
                alert('商品ID：'+itemId);
            },
            openProType:function(typeId){
                //alert('商品分类ID：'+typeId);
                if(typeId==7) {
                    window.open ("${path}/toView?view=phone/category");
                }
            },
            openBaojia:function(type){
                alert('报价分类'+type);
            }
        };

        //处理网页链接
        $('.shopPageLink').click(function(){
            var link = this.href;
            var title = this.title;
            shopApi.openHtml(link,title);
            return false;
        });

        //处理商品链接
        $('.shopPageProItem').click(function(){
            var itemId = $(this).attr('shopkey');
            shopApi.openProItem(itemId);
            return false;
        });

        //处理商品分类链接
        $('.shopPageProType').click(function(){
            var typeId = $(this).attr('shopkey');
            shopApi.openProType(typeId);
            return false;
        });

        //处理报价链接
        $('.shopPageBaojia').click(function(){
            var type = $(this).attr('shopkey');
            shopApi.openBaojia(type||'');
            return false;
        });
    });

</script>
</body>
</html>