<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    function toHome() {
        var path = '${path}';
        if (!path) {
            path = "/";
        }
        window.location.href = path;
    }
</script>
<div class="ui-grid-row" style="height:67px;">
    <div class="ui-grid-15 logo" onclick="toHome()" style="cursor: pointer;"></div>
    <div class="ui-grid-10 " style="margin:0 0 0 10px;">
        <a class="shenqing" href="${path}/ddc/toDdcIdReq"></a>
    </div>
</div>
<div class="menu">
    <a id="homeLink" href="${path}/">首页</a>
    <img src="${path}/static/images/line.jpg"/>
    <a id="ddcRegLink" href="${path}/ddc/toDengji">车辆登记</a>
    <img src="${path}/static/images/line.jpg"/>
    <a id="noticeLink" href="${path}/notice">通知公告</a>
    <img src="${path}/static/images/line.jpg"/>
    <a id="chahuoLink" href="${path}/chahuo">查获车辆公示</a>
    <img src="${path}/static/images/line.jpg"/>
    <a id="beidaoLink" href="${path}/beidao">被盗车辆公示</a>
</div>
