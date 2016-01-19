<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <link href="css/public.css" type="text/css" rel="stylesheet" />
    <link href="css/index.css" type="text/css" rel="stylesheet" />
    <title>北京力超科技</title>
    <!--导航引用js-->
    <script src="js/nav.js" type="text/javascript"></script>
    <script type="text/javascript">
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
    <!--导航引用js.end-->
    <!--数字大图轮换引用js-->
    <script src="js/jquery1.42.min.js" type="text/javascript"></script>
    <script src="js/jquery.superslide.2.1.1.js" type="text/javascript"></script>
    <!--数字大图轮换引用js.end-->
    <script  language="JavaScript" type="text/javascript">
        function setTab(name,cursel,n){
            for(i=1;i<=n;i++){
                var menu=document.getElementById(name+i);
                var con=document.getElementById("con_"+name+"_"+i);
                menu.className=i==cursel?"hover":"";
                con.style.display=i==cursel?"block":"none";
            }
        }
    </script>
</head>

<body>
<div class="head moa">
    <div class="logo fl"></div>
    <div class="lctit fl"></div>
    <div class="lc_select fr">
        <table width="330" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="lc_selectinput"><input type="text" value="请输入关键字" /></td>
                <td><input type="button" class="lc_selectbtn" value=" " /></td>
            </tr>
        </table>
    </div>
    <div class="clear"></div>
</div>
<!--head.end-->
<div class="nav">
    <div class="wrap moa">
        <ul id="jsddm" class="fl">
            <li><a href="index.html" class="hover">首页</a></li>
            <li><a href="#">关于我们</a>
                <ul>
                    <li><a href="#" title="公司简介公司简介" target="_blank">公司简介公司简介</a></li>
                    <li><a href="#" title="公司资质" target="_blank">公司资质</a></li>
                    <li><a href="#" title="公司资质" target="_blank">公司业绩</a></li>
                    <li><a href="#" title="公司视频" target="_blank">公司视频</a></li>
                </ul></li>
            <li><a href="#">新闻中心</a></li>
            <li><a href="#">产品销售</a></li>
            <li><a href="#">解决方案</a></li>
            <li><a href="#">技术交流</a>
                <ul>
                    <li><a href="#" title="论文专栏" target="_blank">论文专栏</a></li>
                    <li><a href="#" title="产品资料" target="_blank">产品资料</a></li>
                    <li><a href="#" title="产品资料" target="_blank">产品资料</a></li>
                    <li><a href="#" title="产品资料" target="_blank">产品资料</a></li>
                </ul>
            </li>
            <li><a href="#">工作机会</a></li>
            <li><a href="#">联系我们</a></li>
        </ul>
        <a class="lc_email fr" href="#">进入公司邮箱</a>
        <div class="clear"></div>
    </div>
</div>
<!--nav.end-->

<div class="wrap moa">
    <div class="lc_bannerimg">
        <ul class="bannerimgcont">
            <li><a href="#" target="_blank"><img src="images/lc_bannerimg1.jpg" /></a></li>
            <li><a href="#" target="_blank"><img src="images/lc_bannerimg1.jpg" /></a></li>
            <li><a href="#" target="_blank"><img src="images/lc_bannerimg1.jpg" /></a></li>
            <li><a href="#" target="_blank"><img src="images/lc_bannerimg1.jpg" /></a></li>
            <li><a href="#" target="_blank"><img src="images/lc_bannerimg1.jpg" /></a></li>
            <li><a href="#" target="_blank"><img src="images/lc_bannerimg1.jpg" /></a></li>
            <li><a href="#" target="_blank"><img src="images/lc_bannerimg1.jpg" /></a></li>
            <li><a href="#" target="_blank"><img src="images/lc_bannerimg1.jpg" /></a></li>
        </ul>
        <a class="prev" href="javascript:void(0)"></a>
        <a class="next" href="javascript:void(0)"></a>
        <div class="num">
            <ul></ul>
        </div>
        <script>
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
        </script>
    </div>
    <!--数字大图轮换.end-->

    <div class="menu fl">
        <div class="menutit">
            <ul><li class="hover">公司业绩</li></ul>
            <a href="#" class="more fr">更多>></a>
            <div class="clear"></div>
        </div>
        <div class="listcont">
            <DIV id=brand_show>
                <DIV class=brand-show-w>
                    <UL class=first>
                        <LI><a href="#"><IMG src="images/scrollimg.jpg"></a>
                            <p><a href="#">业绩业绩文字标题业绩文字标题业绩文字标题业绩文字标题文字标题</a>1</p></LI>
                        <LI><a href="#"><IMG src="images/scrollimg.jpg"></a>
                            <p><a href="#">业绩文字标题2</a></p></LI>
                        <LI><a href="#"><IMG src="images/scrollimg.jpg"></a>
                            <p><a href="#">业绩文字标题3</a></p></LI>
                    </UL>
                </DIV>
                <A class=btn-prev href="javascript:void(0);"></A> <A
                    class=btn-next href="javascript:void(0);"></A> </DIV>
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
        <div class="listcont">
            <div class="pad20" id="con_menutitnav_1" style="display:block;">
                <ul class="listtext">
                    <li><a href="#">播室视音播室视音频系统播室视音频系统播室视音频系统频系统</a></li>
                    <li><a href="#">转播车系统</a></li>
                    <li><a href="#">播出及总控系统</a></li>
                    <li><a href="#">矩阵及信号调度系统</a></li>
                    <li><a href="#">数字电视前端DVB系统</a></li>
                    <li><a href="#">信号传输系统</a></li>
                    <li><a href="#">播室视音播室视音频系统播室视音频系统播室视音频系统频系统</a></li>
                    <li><a href="#">转播车系统</a></li>
                    <li><a href="#">播出及总控系统</a></li>
                </ul>
            </div>
            <div class="pad20" id="con_menutitnav_2" style="display:none;"><img src="images/scrollimg.jpg" /></div>
        </div>
    </div>
    <!--解决方案和最新产品.end-->
    <div class="menu2 fr">
        <div class="menu2tit">
            <span class="fl">新闻中心</span>
            <a href="#" class="more fr">更多>></a>
            <div class="clear"></div>
        </div>
        <div class="listcont2">
            <ul class="listtext pad20">
                <c:forEach items="${newList}" var="new">
                <li><a href="${path}/page/newDetail?id=${new.id}">${new.name}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!--新闻中心.end-->
    <div class="clear"></div>

    <div class="hzhb">
        <div class="hzhbtit"></div>
        <div class="wrap overflow">
            <ul class="hzhbcont"><li><img src="images/lc_hzimg1.jpg" /></li><li><img src="images/lc_hzimg2.jpg" /></li><li><img src="images/lc_hzimg3.jpg" /></li><li><img src="images/lc_hzimg4.jpg" /></li><li><img src="images/lc_hzimg5.jpg" /></li><li><img src="images/lc_hzimg6.jpg" /></li><div class="clear"></div></ul>
        </div>
    </div>
    <!--合作伙伴.end-->
</div>
<div class="footer">
    <div class="aboutus">
        <div class="aboutuscont moa">
            <dl><dt><img src="images/lc_cpico1.jpg" /></dt><dd><a href="#">关于我们</a></dd></dl><span>|</span>
            <dl><dt><img src="images/lc_cpico2.jpg" /></dt><dd><a href="#">联系我们</a></dd></dl><span>|</span>
            <dl><dt><img src="images/lc_cpico3.jpg" /></dt><dd><a href="#">招贤纳士</a></dd></dl><span>|</span>
            <dl><dt><img src="images/lc_cpico4.jpg" /></dt><dd><a href="#">版权声明</a></dd></dl>
            <div class="clear"></div>
        </div>
    </div>
    <div class="copyright">
        <p>ICP备12047723号 京公网安备11010802011426 </p>
        <p>北京力超科技有限公司  技术支持：文字文字文字</p>
    </div>
</div>
<script type=text/javascript charset=utf-8 src="js/scroll.js"></script>
</body>
</html>
