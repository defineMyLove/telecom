
function setTab(m,n){
var tli=document.getElementById("menu"+m).getElementsByTagName("li");
var mli=document.getElementById("main"+m).getElementsByTagName("ul");
for(i=0;i<tli.length;i++){
tli[i].className=i==n?"hover":"";
mli[i].style.display=i==n?"block":"none";
}
}//欢迎来到站长特效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。
/*第三种形式 利用一个背景层定位*/
var m3={0:"<a href='http://www.zzjs.net' target='_blank'>站.长特效网</a>，打造国内最帅的网页特效站！",1:"<a href='http://www.zzjs.net' target='_blank'>站长..特效网</a>，打造国内最帅的网页特效站！",2:"<a href='http://www.zzjs.net' target='_blank'>站长特..效网</a>，打造国内最帅的网页特效站！",3:"<a href='http://www.zzjs.net' target='_blank'>站长特效..网</a>，打造国内最帅的网页特效站！"}
function nowtab(m,n){
if(n!=0&&m3[0]=="")m3[0]=document.getElementById("main2").innerHTML;
document.getElementById("tip"+m).style.left=n*100+'px';
document.getElementById("main2").innerHTML=m3[n];
}//欢迎来到x站长特效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。
