<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/views/common/taglibs.jsp" %>
<div class="bottom">
    河南金明源信息技术有限公司 版权所有&nbsp;&nbsp;&nbsp;&nbsp;客服群：221348637 &nbsp;&nbsp;&nbsp;&nbsp;服务热线：400-633-7959&nbsp;&nbsp;&nbsp;&nbsp;<br/>
    豫ICP备10210900号-5 ©2013 K-Source. All rights reserved.<br/> 为获得最佳浏览效果:建议使用IE6以上版本及1024*768分辨率

    <%
        //手机站、网站导航
    if(session.getAttribute("fromMobile")!=null){ %>
    <p style="margin-top: 5px;"><a href="${path}/?mobile=true">触屏版网站</a>&nbsp;|&nbsp;<a href="${path}/?mobile=false">电脑版网站</a></p>
    <%}%>
</div>
<div id="system_tip" ></div>
<c:if test="${!empty LOGIN_USER}">
<script type="text/javascript">
    seajs.use(['$','module/systemtip/systemtip'],function($,tip){
        tip.init('system_tip','${path}');
        tip.add(
                [{
                    id:'dengjiTip',
                    title:'未登记任何车辆',
                    langTitle:'您还未登记任何车辆',
                    tip:'您还未登记任何车辆,点击进行登记',
                    level:"important",
                    width:150,
                    url:'${path}/systemTip/dengji',
                    contentUrl:"${path}/ddc/toDengji",
                    widgetDefault:{tipable:true}
                },
                    {
                        id:'paizhaoTip',
                        title:'未申请牌照',
                        langTitle:'您的以下车辆还没有申请牌照',
                        width:110,
                        level:"warning",
                        url:'${path}/systemTip/paizhao',
                        contentUrl:"${path}/user/userCenter#cat3"
                    },
                    {
                        id:'xuanhaoTip',
                        title:'未选号',
                        langTitle:'您的以下车辆还没有选号',
                        level:"warning",
                        url:'${path}/systemTip/xuanhao',
                        contentUrl:"${path}/user/userCenter#cat3"
                    },
                    {
                        id:'zhifuTip',
                        title:'未支付',
                        langTitle:'您的以下车辆还没有支付',
                        level:"warning",
                        url:'${path}/systemTip/zhifu',
                        contentUrl:"${path}/user/userCenter#cat3"
                    }] );
    });
</script>
</c:if>