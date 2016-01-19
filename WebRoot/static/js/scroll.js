// JavaScript Document

var kEvent=(function(){
	return {
		addEvent: function(element, name, observer, useCapture) {
			try{
				element=Dom.$id(element);
				if(element.addEventListener){
					if(name==='mouseenter')
						element.addEventListener('mouseover',withoutChildFunction(observer),useCapture);
					else if(name==='mouseleave')
					element.addEventListener('mouseout',withoutChildFunction(observer),useCapture);
					else
					//ele.addEventListener(type,func,false);
					element.addEventListener(name, observer, useCapture);
				}else if(element.attachEvent){
					element.attachEvent('on' + name, observer);
				}
			}catch(e){}
		}
	}
})();

var kEvent1=(function(){

return {
	addEvent: function(element, name, observer, useCapture) {
		try{
			element=Dom.$id(element);
			if(element.addEventListener){
				element.addEventListener(name, observer, useCapture);
			}else if(element.attachEvent){
				element.attachEvent('on' + name, observer);
			}
		}catch(e){}
	},
	
	delEvent: function(el, sType, fn) {
		if (window.removeEventListener) {
			el.removeEventListener(sType, fn, false);
		} else if (window.detachEvent) {
			el.detachEvent("on" + sType, fn);
		}
	},

	getTarget: function(ev) {
		var t = ev.target || ev.srcElement;
		return this.resolveTextNode(t);
	},
	
	resolveTextNode: function(node) {
		/* if (node && node.nodeName && 
				 "#TEXT" == node.nodeName.toUpperCase()) {*/
		if (node && 3 == node.nodeType) {
			return node.parentNode;
		} else {
			return node;
		}
	},
	
	getRelatedTarget: function(ev) {
		var t = ev.relatedTarget;
		if (!t) {
			if (ev.type == "mouseout") {
				t = ev.toElement;
			} else if (ev.type == "mouseover") {
				t = ev.fromElement;
			}
		}
		return this.resolveTextNode(t);
	},
	
	stopEvent: function(ev) {
		this.stopPropagation(ev);
		this.preventDefault(ev);
	},
	
	stopPropagation: function(ev) {
		if (ev.stopPropagation) {
			ev.stopPropagation();
		} else {
			ev.cancelBubble = true;
		}
	},
	
	preventDefault: function(ev) {
		if (ev.preventDefault) {
			ev.preventDefault();
		} else {
			ev.returnValue = false;
		}
	}
}


})();



/*
dom
*/
var Dom={
/*
node
*/
//$ -> getBy
    $id:function(nodeId){
    	if(typeof nodeId=='string'){
    		return document.getElementById(nodeId);
    	}else if(typeof nodeId=='object'){
    		return nodeId;
    	}else{
			return false;
		}
    },
/*
class
*/
	isClass:function(className, node){
		if((!node)||(!className)){return null;}
		var reFlag=false;
		if(node.className==className){
			reFlag=true;
		}
		return reFlag;
	},
	hasClass:function(className, node){
		if((!className)||(!node)||(!node.className)){return false;}
		return (new RegExp('(?:^|\\s+)' + className + '(?:\\s+|$)').test(node.className));
	},
	addClass:function(className, node){
		if((!className)||(!node)){return false;}
		if(Dom.hasClass(className, node)){return true;}
		var newClassName=node.className?node.className+' '+className:className;
		node.className=newClassName;
		return true;
	},
	removeClass:function(className, node){
		if(!(Dom.hasClass(className, node))){return;}
		var nodeClassName=node.className;
		if(nodeClassName==className){
			nodeClassName="";
		}else{
			nodeClassName=nodeClassName.replace(new RegExp('(?:^|\\s+)' + className + '(?:\\s+|$)', 'g'), "").replace(/^\s*/,"");
		}
		node.className=nodeClassName;
	}

};

function searchSon(obj){
	var o=obj.childNodes;
	var arro=[];
	for(var i=0;i<o.length;i++){
		if(o[i].nodeType==1){
			arro.push(o[i]);	
		}
	}
	return arro;
}



//品牌滚动 
var ascroll=[];
function mixScroll(rid, b){
	var inprogress = false,
		live_div = Dom.$id(rid);
	//if(!live_div.length) return;
	
	var items = live_div.getElementsByTagName("li"),
		btnPrev = searchSon(live_div)[1],
		btnNext = searchSon(live_div)[2],
		p_item = live_div.getElementsByTagName("ul"),
		p_itemP0 = p_item[0].parentNode,
		p_itemP = p_itemP0,
		itemNum = items.length,
		lastItem = items[itemNum-1],
		leftMax = items[0].offsetWidth*itemNum,
		leftMin = 0,
		areaWidth = p_itemP0.offsetWidth;
		
	p_item[0].innerHTML=p_item[0].innerHTML+p_item[0].innerHTML;
	if(!items.length || leftMax <= areaWidth){
	  return;
	}
	
	function roll_(page){
		return function(){
		  leftCur = p_itemP0.scrollLeft;
		  var leftTarget
		  if(page==-1){
			leftTarget=Math.max(leftMin, leftCur - areaWidth);
			if(leftCur<=leftMin){
			return false;
		  }  
		}else if(page==1){
			leftTarget=Math.min(leftMax-areaWidth, leftCur + areaWidth);
		  if(leftCur==leftMax-areaWidth||leftCur>leftMax){
			return false;
		  }
		}
		
		  var dur=parseInt(leftTarget/20);
		  for(var i=0;i<=20;i++){
				if(ascroll[i]){clearInterval(ascroll[i]);}
				ascroll[i]=setTimeout((function(y){
				return function(){
					p_itemP.scrollLeft=y*dur;
					if(y==20){
						p_itemP.scrollLeft=leftTarget;
					}
				}							  
				})(i),i*10);
			}
		}
	}
	
	kEvent.addEvent(btnPrev,"click",roll_(-1));
	kEvent.addEvent(btnNext,"click",roll_(1));
	
	var stimer=null;
	
	function autoScroll(r){
	  if(p_itemP.scrollLeft>leftMax){
		  p_itemP.scrollLeft=0;
	}else{
		p_itemP.scrollLeft+=1;
		}
	  
	}
	
	function fnPlay(){
		//alert("aa");
		stimer=setInterval(autoScroll,35);
	}
	function fnPause(){
		clearInterval(stimer);
	}
	kEvent.addEvent(live_div,"mouseenter",fnPause);
	kEvent.addEvent(live_div,"mouseleave",fnPlay);

	fnPlay();

}









var Dome={
	id:function(id,tag){
		var re=(typeof id!="string")?id:document.getElementById(id);
		if(tag){
			return re.getElementsByTagName(tag);
		}else{
			return re;
		}
	},
	hasClass:function(node,classname){
		if(!node||!classname||!node.className) {return false;}
		return (new RegExp('(?:^|\\s+)'+ classname+'(?:\\s+|$)').test(node.className));
	},
	addClass:function(node,classname){
		if(!node||!classname){return false;}
		if(Dome.hasClass(node,classname)) return true;
		var newclass=node.className ? node.className+" "+classname:classname;
		node.className=newclass;
		return true;		
	},
	removeClass:function(node,classname){
		if(!Dome.hasClass(node,classname))return true;
		var nodeClassName=node.className;
		if(nodeClassName==classname){
			node.className="";
		}else{
			nodeClassName=nodeClassName.replace(new RegExp('(?:^|\\s+)' + classname + '(?:\\s+|$)', 'g'), "").replace(/^\s*/,"");
			node.className=nodeClassName;
		}
	}
}
var vEvent=(function(){
return {
	addEvent: function(element, name, observer, useCapture) {
		try{
			element=Dome.id(element);
			if(element.addEventListener){
				if(name==='mouseenter')
					element.addEventListener('mouseover',withoutChildFunction(observer),useCapture);
				else if(name==='mouseleave')
				element.addEventListener('mouseout',withoutChildFunction(observer),useCapture);
				else
				//ele.addEventListener(type,func,false);
				element.addEventListener(name, observer, useCapture);
			}else if(element.attachEvent){
				element.attachEvent('on' + name, observer);
			}
		}catch(e){}
	},
	addmousewheel:function(element,observer){
		try{
			if(element.addEventListener){
				element.addEventListener("DOMMouseScroll",observer,false);
			}else if(element.attachEvent){
				element.attachEvent("onmousewheel",observer);
			}
		}catch(e){}
	}
}
})();
var withoutChildFunction=function(func){
	return function(e){
		var parent=e.relatedTarget;//上一响应mouseover/mouseout事件的元素
		while(parent!=this&&parent){//假如存在这个元素并且这个元素不等于目标元素（被赋予mouseenter事件的元素）
			try{
				parent=parent.parentNode;}//上一响应的元素开始往上寻找目标元素
			catch(e){
				break;
			}
 
		}
		if(parent!=this)//以mouseenter为例，假如找不到，表明当前事件触发点不在目标元素内
		func(e);//运行目标方法，否则不运行
	}
}

//品牌滚动
mixScroll("brand_show");
