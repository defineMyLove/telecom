<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta content="width=device-width,minimum-scale=1.00001,maximum-scale=1.00001,shrink-to-fit=no,user-scalable=no,minimal-ui"
      name="viewport">

<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="telephone=no" name="format-detection">
<!-- UC默认竖屏 ，UC强制全屏 -->
<meta name="full-screen" content="yes">
<meta name="browsermode" content="application">
<!-- QQ强制竖屏 QQ强制全屏 -->
<meta name="x5-orientation" content="portrait">
<meta name="x5-fullscreen" content="true">
<meta name="x5-page-mode" content="app">
<title>诺部落首页</title>
<link rel="stylesheet" href="${path}/js/qune/common-2015090714215161.css" type="text/css">
<link rel="stylesheet" href="${path}/js/qune/common-image2x-2015090714215161.css" type="text/css">
<link rel="stylesheet" href="${path}/js/qune/search-page.css" type="text/css">
<link href="${path}/static/css/myapp.min.css" rel="stylesheet">
<style tyle="text/css">
.qn_main {
    width: 100%;
    margin: 0 auto;
    min-width: 320px;
    max-width: 540px;
    position: relative;
}

.qn_main a:active p {
    color: #000;
}

.qn_slide, .qn_slide a {
    min-height: 100px;
    height: auto;
}

.qn_btop {
    border-top: none;
}

.qn_bubble .ctt {
    height: 72px;
}

.qn_main .qn_download {
    display: block !important;
}

.qn_download {
    background: #f2f2f2;
    height: 44px;
}

.qn_download .logo {
    background-size: 100% 100%;
    width: 33px;
    height: 33px;
    top: 5px;
    left: 25px
}

.qn_download .title {
    top: 5px;
    left: 79px
}

.qn_download .title .qn_mt3 {
    margin-top: 3px;
}

.qn_download .download_btn {
    background: #1ba9ba;
    width: 60px;
    height: 25px;
    line-height: 25px;
    border-radius: 3px;
    font-size: 12px;
    border: none;
    top: 10px;
    right: 15px;
}

.qn_download .download_btn a {
    color: #fff;
}

* {
    box-sizing: border-box;
}

html {
    font-size: 100px;
}

body {
    font-size: 12px;
}

.qn_pt16 {
    padding-top: 16px;
}

body {
    background-color: #fff;
}

.qn_nav {
    width: 100%;
    color: #fff;
    font-family: '黑体';
}

.qn_nav ul {
    border-box;
    border: 2px #fff solid;
}

.qn_nav li {
    display: -webkit-box;
    display: -ms-flexbox;
    display: -moz-box;
    display: box;
}

.qn_nav a {
    height: .56rem;
    color: #fff;
}

.qn_nav .hg2 a {
    height: 1.12rem;
    color: #fff;
}

.qn_nav .hg2 p {
    position: absolute;
    top: 6px;
    left: 10px;
}

.qn_main a:active p {
    color: #fff;
}

.qn_nav .flex1 {
    width: 25%;
    text-align: center;
    font-size: 0.12rem;
    position: relative;
}

.qn_nav .flex1 .pos {
    position: absolute;
    bottom: 3px;
    left: 0;
    right: 0;
    margin: auto;
}

.qn_nav .flex1 i {
    background: url(${path}/js/qune/hotel_index_icon3.png) no-repeat top left;
    background-size: 896px 35px;
    display: block;
}

.qn_nav .flex2 {
    width: 50%;
    line-height: .3rem;
    position: relative;
    padding: 0.11rem .2rem;
    font-size: 0.16rem
}

.qn_nav .flex2 span {
    display: block;
    position: relative;
    margin: 0 auto;
    width: 1.2rem;
}

.qn_nav .flex2 span:before {
    content: ' ';
    display: block;
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    margin: auto;
}

.qn_nav .item {
    display: block;
    box-sizing: border-box;
    border: 2px #fff solid;
}

.qn_nav .item.cur {
    border: 4px #fff solid;
}

.qn_nav .item span:before {
    background-size: 896px 35px;
}

.qn_nav .item.cur span {
    transform: scale(0.95);
    -moz-transform: scale(0.95);
    -webkit-transform: scale(0.95);
    -o-transform: scale(0.95);
}

.qn_nav .item.cur .pos {
    transform: scale(0.95);
    -moz-transform: scale(0.95);
    -webkit-transform: scale(0.95);
    -o-transform: scale(0.95);
    bottom: 2px;
}

.qn_slide a {
    width: auto;
}

.qn_nav .dujiatuan {
    background-color: #1489e6;
}

.qn_nav .dujiatuan.cur {
    background-color: #1489e6;
}

.qn_nav .dujiatuan span {
    padding-left: 40px;
}

.qn_nav .dujiatuan span:before {
    width: 30px;
    height: 29px;
    background-position: -730px 0;
    top: 0;
}

.qn_nav .dujia {
    background-color: #1489e6;
}

.qn_nav .dujia.cur {
    background-color: #1489e6;
}

.qn_nav .dujia span {
    padding-left: 40px;
}

.qn_nav .dujia span:before {
    width: 30px;
    height: 29px;
    background-position: -261px 0;
    top: 0;
}

.qn_nav .gonglue {
    background-color: #009688;
}

.qn_nav .gonglue.cur {
    background-color: #009688;
}

.qn_nav .gonglue span {
    padding-left: 38px;
}

.qn_nav .gonglue span:before {
    background-position: -527px 0;
    width: 25px;
    height: 30px;
    top: 0;
}

.qn_nav .tuan {
    background: #009688;
}

.qn_nav .tuan.cur {
    background-color: #009688;
}

.qn_nav .tuan span {
    padding-left: 38px;
}

.qn_nav .tuan span:before {
    background: url(${path}/js/qune/group_icon.png) no-repeat center;
    background-size: 27px 28px;
    background-position: 0 0;
    width: 27px;
    height: 28px;
    top: 0;
}

.qn_nav .hotel {
    background: #ff5555 url(${path}/js/qune/hotel_icon4.png) no-repeat center;
    background-size: 40px 56px;
}

.qn_nav .hotel.cur {
    background-color: #ff5555;
}

.qn_nav .flight {
    background: #7e57c2 url(${path}/js/qune/flight_icon3.png) no-repeat center;
    background-size: 55px 56px;
}

.qn_nav .flight.cur {
    background-color: #7f6cd1;
}

.qn_nav .jingdian {
    background-color: #ff9800;
}

.qn_nav .jingdian.cur {
    background-color: #ff9800;
}

.qn_nav .jingdian span {
    padding-left: 40px;
}

.qn_nav .jingdian span:before {
    background-position: -369px 0;
    width: 28px;
    height: 24px;
}

.qn_nav .yexiao {
    background-color: #ff5555;
}

.qn_nav .yexiao.cur {
    background-color: #ff5555;
}

.qn_nav .yexiao span {
    padding-left: 40px;
}

.qn_nav .yexiao span:before {
    background-position: -155px 0;
    width: 28px;
    height: 28px;
}

.qn_nav .gongyu {
    background-color: #ff5555;
}

.qn_nav .gongyu.cur {
    background-color: #ff5555;
}

.qn_nav .gongyu i {
    background-position: -208px 0;
    width: 27px;
    height: 26px;
    margin: 3px auto;
}

.qn_nav .cheche {
    background-color: #ff9800;
}

.qn_nav .cheche.cur {
    background-color: #ff9800;
}

.qn_nav .cheche span {
    padding-left: 40px;
}

.qn_nav .cheche span:before {
    background-position: -474px 0;
    width: 27px;
    height: 25px;
    top: 0;
}

.qn_nav .weekend {
    background-color: #ff9800;
}

.qn_nav .weekend.cur {
    background-color: #ff9800;
}

.qn_nav .weekend span {
    padding-left: 40px;
}

.qn_nav .weekend span:before {
    background: url(${path}/js/qune/weekend1.png) no-repeat center;
    background-size: 27px 28px;
    background-position: 0 0;
    width: 27px;
    height: 28px;
    top: 0;
}

.qn_nav .zuche {
    background-color: #7e57c2;
}

.qn_nav .zuche.cur {
    background-color: #7e57c2;
}

.qn_nav .zuche i {
    background: url(${path}/js/qune/jiesongji1.png) no-repeat top left;
    background-size: 26px 26px;
    background-position: 0 0;
    width: 26px;
    height: 26px;
    margin: 2px auto;
}

.qn_nav .flight_disc {
    background-color: #7e57c2;
}

.qn_nav .flight_disc.cur {
    background-color: #7e57c2;
}

.qn_nav .flight_disc i {
    background: url(${path}/js/qune/flight_disc1.png) no-repeat top left;
    background-size: 26px 26px;
    background-position: 0 0;
    width: 26px;
    height: 26px;
    margin: 2px auto;
}

.qn_nav .luotuobook {
    background-color: #1ba9ba;
}

.qn_nav .luotuobook.cur {
    background-color: #1ba9ba;
}

.qn_nav .luotuobook i {
    background-position: -880px 0;
    width: 16px;
    height: 26px;
    margin: 2px auto;
}

.qn_nav .train {
    background-color: #7eb63d;
}

.qn_nav .train.cur {
    background-color: #7eb63d;
}

.qn_nav .train span {
    padding-left: 40px;
}

.qn_nav .train span:before {
    background-position: -630px 0;
    width: 26px;
    height: 28px;
    top: 0;
}

.qn_nav .movie {
    background-color: #009688;
}

.qn_nav .movie.cur {
    background-color: #009688;
}

.qn_nav .movie span {
    padding-left: 40px;
}

.qn_nav .movie span:before {
    background-position: -780px 0;
    width: 26px;
    height: 28px;
    top: 0;
}

.qn_nav .qichepiao {
    background-color: #7eb63d;
}

.qn_nav .qichepiao.cur {
    background-color: #7eb63d;
}

.qn_nav .qichepiao span {
    padding-left: 40px;
}

.qn_nav .qichepiao span:before {
    background: url(${path}/js/qune/qiche.png) no-repeat center;
    background-size: 27px 28px;
    background-position: 0 0;
    width: 27px;
    height: 28px;
    top: 0;
}

.qn_nav .food {
    background-color: #1ba9ba;
}

.qn_nav .food.cur {
    background-color: #1ba9ba;
}

.qn_nav .food span {
    padding-left: 40px;
}

.qn_nav .food span:before {
    background-position: -830px 0;
    width: 28px;
    height: 28px;
    top: 10px;
}

.qn_nav .dangdiren {
    background-color: #4285F4;
}

.qn_nav .dangdiren.cur {
    background-color: #4285F4;
}

.qn_nav .dangdiren span {
    padding-left: 38px;
}

.qn_nav .dangdiren span:before {
    background: url(${path}/js/qune/daoyouwf.png) no-repeat top left;
    background-size: 30px 30px;
    background-position: 0 0;
    width: 30px;
    height: 30px;
    top: 0;
}

.qn_nav .qianzheng {
    background-color: #7eb63d;
}

.qn_nav .qianzheng.cur {
    background-color: #7eb63d;
}

.qn_nav .qianzheng i {
    background-position: -49px 0;
    width: 40px;
    height: 26px;
    margin: 2px auto;
}

.qn_nav .zdf {
    background-color: #ff5555;
}

.qn_nav .zdf i {
    background: url(${path}/js/qune/zdf_icon1.png) no-repeat top left;
    background-size: 26px 26px;
    background-position: 0 0;
    width: 26px;
    height: 27px;
    margin: 2px auto;
}

.qn_nav .item i.new {
    background: url(${path}/js/qune/icon_new1.png) no-repeat center;
    width: 22px;
    height: 10px;
    background-size: 22px 10px;
    top: 2px;
    right: 2px;
    position: absolute;
}

.ad img {
    width: 100%;
    height: 44px;
}

@media screen and (max-width: 320px) {
    html {
        font-size: 100px;
    }
}

.loading-div {
    text-align: center;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-left: -40px;
    margin-top: -40px;
    width: 80px;
    height: 80px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    background: rgba(0, 0, 0, 0.5);
    z-index: 2
}

.loading-div img {
    width: 50px;
    margin-top: 10px
}

.loading-div div {
    color: #FFF;
    font-size: 15px;
    margin-top: 2px
}

.mark2 {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.2);
    filter: alpha(opacity=20);
    z-index: 2
}

.hidden {
    display: none
}

.cl-update-page {
    display: flex;
    align-items: center;
    display: -webkit-box;
    -webkit-box-align: center;
    position: fixed;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.4);
    z-index: 50
}

.cl-update-box {
    width: 86%;
    margin: 0 auto;
    padding: 5px;
    background: rgba(255, 255, 255, 0.5);
    border-radius: 5px;
    -webkit-border-radius: 5px;
    z-index: 99;
}

.cl-update-sub {
    height: 100%;
    background: #fff;
}

.cl-update-title {
    background: #17A4BD;
    height: 40px;
    line-height: 40px;
    text-align: center;
    font-size: 18px;
    color: #fff;
}

.cl-update-content {
    padding: 10px;
    background: #fff;
}

.cl-update-content p {
    line-height: 20px;
    font-size: 14px;
}

.cl-update-content .qn_btn a {
    background: #f00;
    border-radius: 3px;
}

.scaleIn {
    -webkit-animation-name: scaleIn;
}

.scaleOut {
    -webkit-animation-name: scaleOut;
}

.animating {
    display: block;
    -webkit-animation-duration: 300ms;
    -webkit-animation-fill-mode: both;
    -webkit-animation-timing-function: ease-in-out
}

@-webkit-keyframes scaleIn {
    0% {
        opacity: 0;
        -webkit-transform: scale(.5)
    }
    100% {
        opacity: 1;
        -webkit-transform: scale(1)
    }
}

@-webkit-keyframes scaleOut {
    0% {
        opacity: 1;
        -webkit-transform: scale(1)
    }
    100% {
        opacity: 0;
        -webkit-transform: scale(.5)
    }
}

.m-search-page .search-bar.with-gray {
    background-color: #fff;
}

.m-search-page .search-bar.with-gray .input-wrapper {
    background-color: #ddd;
}

.tag_promotion {
    background: #FF6B62;
}

.tag_chain {
    background: #26BA54;
}

.tag_fiveDiscount {
    background: #FF6B62;
}

.tag_lmLowestPrice {
    background: #f60;
}

.tag_starticket_return {
    background: #ff0000;
}

.tag_signin {
    background: #2bba54
}
</style>


<script src="${path}/js/qune/hm.js"></script>
</head>
<body class="qn_skin_3w">
<div class="qn_main page-wrapper">
    <div class="qn_nav">
        <ul>
            <li>
                <a href="${path}/toView?view=/phone/category" class="item flex2 dangdiren">
                    <span>电信业务</span>
                </a>
                <a href="${path}/toView?view=/phone/input" class="item flex1 qianzheng">
                    <div class="pos">
                        <i></i>
                        <span>回访</span>
                    </div>
                </a>
                <a href="tel:${tel.tel}" class="item flex1 luotuobook">
                    <div class="pos">
                        <i></i>
                        <span>联系我们</span>
                    </div>

                </a>
            </li>
        </ul>
    </div>
    <div class="card">

        <div class="card-body">
            <div class="listview">
                <c:forEach var="bean" items="${list}">
                    <a class="lv-item" href="javascript:window.open('${path}/service/telcom/detail?id=${bean.id}','_self');">
                        <div class="media">
                            <div class="pull-left">
                                <img class="lv-img" src="${path}${bean.pic_path}" alt="">
                            </div>
                            <div class="media-body">
                                <div class="lv-title">${bean.name}</div>
                                <small class="lv-small">${bean.note}
                                </small>
                            </div>
                        </div>
                    </a>
                </c:forEach>
                <a class="lv-footer" href="${path}/toView?view=/phone/category">查看更多</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>