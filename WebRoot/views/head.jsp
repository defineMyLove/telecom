<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp" %>


<script  type="text/javascript">
    function setTab(name,cursel,n){
        for(i=1;i<=n;i++){
            var menu=document.getElementById(name+i);
            var con=document.getElementById("con_"+name+"_"+i);
            menu.className=i==cursel?"hover":"";
            con.style.display=i==cursel?"block":"none";
        }
    }


    var timeout         = 500;
    var closetimer		= 0;
    var ddmenuitem      = 0;

    function jsddm_open()
    {	jsddm_canceltimer();
        jsddm_close();
        ddmenuitem = $(this).find('ul').eq(0).css('visibility', 'visible');}

    function jsddm_close()
    {	if(ddmenuitem) ddmenuitem.css('visibility', 'hidden');}

    function jsddm_timer()
    {	closetimer = window.setTimeout(jsddm_close, timeout);}

    function jsddm_canceltimer()
    {	if(closetimer)
    {	window.clearTimeout(closetimer);
        closetimer = null;}}

    $(document).ready(function()
    {	$('#jsddm > li').bind('mouseover', jsddm_open);
        $('#jsddm > li').bind('mouseout',  jsddm_timer);});

    document.onclick = jsddm_close;
</script>
<div class="head moa">
    <div class="logo fl"></div>
    <div class="lctit fl"></div>
    <div class="lc_select fr">
        <form method="post" action="${path}/page/prosearch">
        <table width="330" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="lc_selectinput"><input type="text" name="name" value="请输入关键字" /></td>
                <td><input type="submit" class="lc_selectbtn" value=""/></td>
            </tr>
        </table>
        </form>
    </div>
    <div class="clear"></div>
</div>
<!--head.end-->
<div class="nav">
    <div class="wrap moa">
        <ul id="jsddm" class="fl+">
            <li><a class="hover" href="${path}">首页</a></li>
            <li><a href="${path}/toView?view=aboutint">关于我们</a>
                <ul>
                    <li ><a href="${path}/toView?view=aboutint">公司简介</a></li>
                    <li ><a href="${path}/page/aboutque">公司资质</a></li>
                    <li ><a href="${path}/page/aboutper">公司业绩</a></li>
                    <li ><a href="${path}/page/aboutvid">公司视频</a></li>
                </ul></li>
            <li><a href="${path}/page/news">新闻中心</a></li>
            <li><a href="${path}/page/products">产品销售</a></li>
            <li><a href="${path}/page/solutions">解决方案</a>
                <ul>
                    <c:forEach items="${head_solutionList}" var="solution">
                        <li><a  style="width:150px;" href="${path}/page/solution?fenlei_id=${solution.id}">${solution.name}</a></li>
                    </c:forEach>
                </ul>
            </li>
            <li><a href="${path}/page/paper">技术交流</a>
                <ul>
                    <li ><a href="${path}/page/paper">论文专栏</a></li>
                    <li ><a href="${path}/page/data">产品资料</a></li>
                    <li ><a href="${path}/page/know">知识库</a></li>
                    <li ><a href="${path}/toView?view=message">留言板</a></li>
                </ul>
            </li>
            <li><a href="${path}/page/job">工作机会</a></li>
            <li><a href="${path}/toView?view=contactus">联系我们</a></li>
        </ul>
        <a class="lc_email fr" href="http://mail.ldt.cn" target="_blank" >进入公司邮箱</a>
        <div class="clear"></div>
    </div>
</div>
<!--nav.end-->
