Utils.pkg("ecWap.category");ecWap.category.cagetoryMap={};ecWap.category.init=function(b,a){ecWap.category.cagetoryMap[b]=ecWap.category.cagetoryMap[b]||[];ecWap.category.cagetoryMap[b]["children"]=a};ecWap.category.changeTab=function(d){$(d).parent().closest("ul").find("a").removeClass("current");$(d).addClass("current");var b="";var c=$(d).attr("data");var a=ecWap.category.cagetoryMap[c]["children"];$.each(a,function(e,g){var f="/category/detail-"+c+"-"+g.childId;b+="<li onclick=\"location.href='"+f+'\'"><div class="pro-panels"><p class="p-img"><img src="'+mediaPath+g.imagePath+g.imageName+'"></p><p class="p-name"><span>'+g.childName+"</span></p></div></li>"});$(".category-right ul").html(b)};$(".bottom-area").css("bottom","-20em");