<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
      <link href="${path}/static/css/public.css" type="text/css" rel="stylesheet" />
      <link href="${path}/static/css/index.css" type="text/css" rel="stylesheet" />

      <!--导航引用js-->
      <script src="${path}/static/js/nav.js" type="text/javascript"></script>

      <script src="${path}/static/js/jquery1.42.min.js" type="text/javascript"></script>
      <script src="${path}/static/js/jquery.superslide.2.1.1.js" type="text/javascript"></script>
      <script type="text/javascript">
          $(function(){
              /*鼠标移过，左右按钮显示*/
              $(".lc_bannerimg").hover(function(){
                  $(this).find(".prev,.next").fadeTo("show",0.1);
              },function(){
                  $(this).find(".prev,.next").hide();
              })
              /*鼠标移过某个按钮 高亮显示*/
              $(".prev,.next").hover(function(){
                  $(this).fadeTo("show",0.7);
              },function(){
                  $(this).fadeTo("show",0.1);
              })
              $(".lc_bannerimg").slide({ titCell:".num ul" , mainCell:".bannerimgcont" , effect:"fold", autoPlay:true, delayTime:700 , autoPage:true });
          });
      </script>
      <!--导航引用js.end-->
  	</head>
  <body>
  
    <!-- 网页头部 -->
	<%@include file="head.jsp"%>

    <div class="wrap moa">
        <div class="lc_bannerimg">
            <ul class="bannerimgcont">
                <li><a href="#" target="_blank"><img src="${path}/static/images/lc_bannerimg1.jpg" /></a></li>
                <li><a href="#" target="_blank"><img src="${path}/static/images/lc_bannerimg1.jpg" /></a></li>
                <li><a href="#" target="_blank"><img src="${path}/static/images/lc_bannerimg1.jpg" /></a></li>
                <li><a href="#" target="_blank"><img src="${path}/static/images/lc_bannerimg1.jpg" /></a></li>
                <li><a href="#" target="_blank"><img src="${path}/static/images/lc_bannerimg1.jpg" /></a></li>
                <li><a href="#" target="_blank"><img src="${path}/static/images/lc_bannerimg1.jpg" /></a></li>
                <li><a href="#" target="_blank"><img src="${path}/static/images/lc_bannerimg1.jpg" /></a></li>
                <li><a href="#" target="_blank"><img src="${path}/static/images/lc_bannerimg1.jpg" /></a></li>
            </ul>
            <a class="prev" href="javascript:void(0)"></a>
            <a class="next" href="javascript:void(0)"></a>
            <div class="num">
                <ul></ul>
            </div>

        </div>
        <!--数字大图轮换.end-->

        <div class="menu fl">
            <div class="menutit">
                <ul><li class="hover">公司业绩</li></ul>
                <a href="${path}/page/aboutper" class="more fr">更多>></a>
                <div class="clear"></div>
            </div>
            <div class="listcont2">
                <%--<div id=brand_show>
                    <div class=brand-show-w>
                        <ul class=first>
                            <c:forEach items="${yejiList}" var="yeji">
                                <li><a href="${path}/page/yejiDetail?id=${yeji.id}"><img src="${path}${yeji.pic_path}"></a>
                                    <p><a href="${path}/page/yejiDetail?id=${yeji.id}">${yeji.name}</a></p></li>
                            </c:forEach>
                        </ul>
                    </div>
                    <a class=btn-prev href="javascript:void(0);"></a> <a
                        class=btn-next href="javascript:void(0);"></a> </div>--%>
                    <DIV id="demo" style="OVERFLOW: hidden;height: 100%"><!--修改显示区域的宽度-->
                        <TABLE cellSpacing=0 cellPadding=0 border=0 style="height: 100%">
                            <TBODY>
                            <TR>
                                <TD id=demo1>
                                    <!--滚动部分表格开始-->
                                    <table width="500" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <c:forEach items="${yejiList}" var="yeji">
                                                <td width="300" align="center">
                                                    <a href="${path}/page/yejiDetail?id=${yeji.id}">
                                                        <img src="<%=basePath%>${yeji.pic_path}" width="210" height="160" />
                                                         </a>
                                                    <p><a href="${path}/page/yejiDetail?id=${yeji.id}">${yeji.name}</a></p>
                                                </td>
                                            </c:forEach>

                                        </tr>
                                    </table>
                                    <!--滚动部分表格结束-->
                                </TD>
                                <TD id=demo2></TD></TR></TBODY></TABLE>

                    </DIV>

                    <SCRIPT>
                        var speed3_s=30 //速度数值越大速度越慢
                        demo2.innerHTML=demo1.innerHTML
                        function Marquee_s(){
                            if(demo2.offsetWidth-demo.scrollLeft<=0)
                                demo.scrollLeft-=demo1.offsetWidth
                            else{
                                demo.scrollLeft++
                            }
                        }
                        var MyMar_s=setInterval(Marquee_s,speed3_s)
                        document.getElementById("demo").onmouseover=function() {clearInterval(MyMar_s)}
                        document.getElementById("demo").onmouseout=function() {MyMar_s=setInterval(Marquee_s,speed3_s)}
                    </SCRIPT>
            </div>
        </div>
        <!--公司业绩.end-->

        <div class="menu fl" style="margin:0 15px;">
            <div class="menutit">
                <ul>
                    <li class="hover" id="menutitnav1" onmouseover="setTab('menutitnav',1,2)">解决方案</li>
                    <li id="menutitnav2" onmouseover="setTab('menutitnav',2,2)">最新产品</li>
                    <div class="clear"></div>
                </ul>
            </div>
            <div class="listcont2">
                <div class="pad20" id="con_menutitnav_1" style="display:block;">
                    <ul class="listtext">
                        <c:forEach items="${head_solutionList}" var="item">
                            <li><a href="${path}/page/solution?fenlei_id=${item.id}">${item.name}</a></li>
                        </c:forEach>
                    </ul>
                </div>
                <div id="con_menutitnav_2" style="display:none; height:100%">
                    <DIV id=demo5 style="OVERFLOW: hidden; height:100%"><!--修改显示区域的宽度-->
                        <TABLE cellSpacing=0 cellPadding=0 border=0 style=" height:100%">
                            <TBODY>
                            <TR>
                                <TD id=demo3>
                                    <!--滚动部分表格开始-->
                                    <table width="500" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <c:forEach items="${head_pic}" var="yeji">
                                                <td width="300" align="center">
                                                    <a href="${path}/page/proDetail?id=${yeji.id}">
                                                        <img src="<%=basePath%>${yeji.pic_path}" width="210" height="160" />
                                                    </a>
                                                    <p><a href="${path}/page/proDetail?id=${yeji.id}">${yeji.name}</a></p>
                                                </td>
                                            </c:forEach>

                                        </tr>
                                    </table>
                                    <!--滚动部分表格结束-->
                                </TD>
                                <TD id=demo4></TD></TR></TBODY></TABLE>

                    </DIV>

                    <SCRIPT>
                        var speed3=30 //速度数值越大速度越慢
                        demo4.innerHTML=demo3.innerHTML
                        function Marquee(){
                            if(demo4.offsetWidth-demo5.scrollLeft<=0)
                                demo5.scrollLeft-=demo3.offsetWidth
                            else{
                                demo5.scrollLeft++
                            }
                        }
                        var MyMar=setInterval(Marquee,speed3)
                        demo5.onmouseover=function() {clearInterval(MyMar)}
                        demo5.onmouseout=function() {MyMar=setInterval(Marquee,speed3)}
                    </SCRIPT>
                </div>
            </div>
        </div>
        <!--解决方案和最新产品.end-->

        <div class="menu2 fr">
            <div class="menu2tit">
                <span class="fl">新闻中心</span>
                <a href="${path}/page/news" class="more fr">更多>></a>
                <div class="clear"></div>
            </div>
            <div class="listcont2">
                <marquee scrollamount='1' scrolldelay='30' direction= 'UP' width='240' id='YiMing' height='190' onmouseover='this.stop()' onmouseout='this.start()'>
                <ul class="listtext pad20">
                    <c:forEach items="${newList}" var="new">
                        <li><a href="${path}/page/newDetail?id=${new.id}">${new.title}</a></li>
                    </c:forEach>
                </ul>
                </marquee>
            </div>
        </div>
        <!--新闻中心.end-->
        <div class="clear"></div>

        <div class="hzhb">
            <div class="hzhbtit"></div>
            <div class="wrap overflow">
                <ul class="hzhbcont">
                    <li><a href="http://www.grassvalley.com"><img src="${path}/static/images/grass.png" /></a></li>
                    <li><a href="http://pro.sony.com.cn/pro/hub/home"><img src="${path}/static/images/sony.png" /></a></li>
                    <li><a href="http://www.axon.tv/EN/home"><img src="${path}/static/images/axon.png" /></a></li>
                    <li><a href="http://www.orad.tv"><img src="${path}/static/images/orad.png" /></a></li>
                    <li><a href="http://www.tslproducts.com"><img src="${path}/static/images/products.png" /></a></li>
                    <li><a href="http://junger-audio.com"><img src="${path}/static/images/junger.png" /></a></li>
                    <div class="clear">
                    </div></ul>
            </div>
        </div>
        <!--合作伙伴.end-->
    </div>
	<!-- 尾部模块 -->
	<%@include file="end.jsp"%>
  </body>
</html>
