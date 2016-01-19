/**
 * 工具类封装
 * @author gengzi 350049339@qq.com
 *  api:
 *  {
 		
 *  }
 */
define(function (require, exports, module) {
	var $=require('$');
	var appUtil={
			debug:true,//未实现
			projectName:''//警告：部署的项目路径（示例："/kx"；跟路径部署时填为空），发布时一定要确定修改！！！！
	};
	
	/**
	 * Format string
	 *  
	 * @param  object|array args
	 * @return string
	 */
	String.prototype.format = function(args)
	{
	    var result = this;
	    if (arguments.length > 0)
	    {
	        var reg;
	        if (arguments.length == 1 && typeof(args) == "object")
	        {
	            for (var key in args)
	            {
	                if (args[key] != undefined)
	                {
	                    reg = new RegExp("({" + key + "})", "g");
	                    result = result.replace(reg, args[key]);
	                }
	            }
	        }
	        else
	        {
	            for (var i = 0; i < arguments.length; i++)
	            {
	                if (arguments[i] != undefined)
	                {
	                    reg = new RegExp("({[" + i + "]})", "g");
	                    result = result.replace(reg, arguments[i]);
	                }
	            }
	        }
	    }
	    return result;
	};
	
	/**
	 * 显示消息，并可执行回掉函数
	 * showMsg和confirm：依赖window.jquery；页面调用不依赖lhgdialog(按需加载)；支持无限锁屏弹窗
	 */
	appUtil.showMsg=function(type,msg,onclose){//type:'alert','success','error'
		if(arguments.length==1){
			msg = arguments[0];
			type='alert';
		}
		if(msg.indexOf('<')==-1){
			var style="font-size:13px;";
			if(msg.length<20)
				style+="width:250px;text-align: left;";
			msg='<div style="'+style+'">'+msg+'</div>';
		}
		//依赖window.$对象，如果在打开的窗口中弹出，调用opener.$；否则异步按需加载lhgdialog
		//window.$是在jquery模块中导出到window里的
		if(window.frameElement && window.frameElement.api){
			window.frameElement.api.opener.$.dialog.alertMore(type,msg,onclose,window.frameElement.api);
		}else{
			if($.dialog){
				$.dialog.alertMore(type,msg,onclose);
			}else{
				require.async('lhgdialog',function(){
					$.dialog.alertMore(type,msg,onclose);
				});
			}
		}
	};
	/**
	 * 显示确认框，并可执行回掉函数
	 * @param msg	内容
	 * @param okFun		确定按钮回调
	 * @param cancelFun	取消按钮回调
	 * @param myCloseFun	右上角关闭按钮回调（不配置，默认调用cancelFun）
	 */
	appUtil.confirm=function(msg,okFun,cancelFun,myCloseFun){
		if(msg.indexOf('<')==-1){
			var style="font-size:13px;";
			if(msg.length<20)
				style+="width:250px;text-align: center;";
			msg='<div style="'+style+'">'+msg+'</div>';
		}
		if(window.frameElement && window.frameElement.api){
			window.frameElement.api.opener.$.dialog.confirm(msg, okFun, cancelFun,myCloseFun,window.frameElement.api);
		}else{
			if($.dialog){
				$.dialog.confirm(msg, okFun, cancelFun,myCloseFun);
			}else{
				require.async('lhgdialog',function(){
					$.dialog.confirm(msg, okFun, cancelFun,myCloseFun);
				});
			}
		}
	};
	//模拟单选模型
	appUtil.checkOneModel=function(selector){
		$(selector).click(function(){
			if(this.checked){
				$(selector).attr("checked",false);
			}
			this.checked=true;
		});
	}
	//模拟多选模型
	appUtil.checkMoreModel=function(triggerSelector,targetSelector){
		$(triggerSelector).click(function(){
			$(targetSelector).attr("checked",this.checked);
		});
	}
	// 动态加载js文件
	appUtil.loadJs=function(url, callback) {
			var done = false;
			var script = document.createElement('script');
			script.type = 'text/javascript';
			script.language = 'javascript';
			script.src = url;
			if (callback) {
				script.onload = script.onreadystatechange = function() {
					if (!done && (!script.readyState || script.readyState == 'loaded' || script.readyState == 'complete')) {
						done = true;
						script.onload = script.onreadystatechange = null;
						if (callback) {
							callback.call(script);
						}
					}
				};
			}
			document.getElementsByTagName("head")[0].appendChild(script);
	};
	// 动态加载css文件
	appUtil.loadCss =function(url, callback) {
		var link = document.createElement('link');
		link.rel = 'stylesheet';
		link.type = 'text/css';
		link.media = 'screen';
		link.href = url;
		document.getElementsByTagName('head')[0].appendChild(link);
		if (callback) {
			callback.call(link);
		}
	};
	/**
     * 时间格式化 返回格式化的时间
     * @param date 要格式化的data对象或者毫秒数
     * @param fomat {string} 格式化字符串，可选，默认‘YYYY-MM-DD’例如：'YYYY年MM月DD日 hh时mm分ss秒 星期' 'YYYY/MM/DD week' (中文为星期，英文为week)
     * @return {string} 返回格式化的字符串
     * 
     * 例子:
     * formatDate(new Date("january 01,2012"));
     * formatDate(new Date());
     * formatDate('YYYY年MM月DD日 hh时mm分ss秒 星期 YYYY-MM-DD week');
     * formatDate(new Date("january 01,2012"),'YYYY年MM月DD日 hh时mm分ss秒 星期 YYYY/MM/DD week');
     * 
     * 格式：   
     *    YYYY：4位年,如1993
　　 *　　YY：2位年,如93
　　 *　　MM：月份
　　 *　　DD：日期
　　 *　　hh：小时
　　 *　　mm：分钟
　　 *　　ss：秒钟
　　 *　　星期：星期，返回如 星期二
　　 *　　周：返回如 周二
　　 *　　week：英文星期全称，返回如 Saturday
　　 *　　www：三位英文星期，返回如 Sat
     */
    appUtil.formatDate=function(date, format) {
    	if(!date){
    		return "";
    	}
    	if(date.constructor!=Date){
    		date=new Date(date*1);
    	}
    	if(format==undefined){
    		format='YYYY-MM-DD';
    	}
        if (arguments.length < 2 && !date.getTime) {
            format = date;
            date = new Date();
        }
        typeof format != 'string' && (format = 'YYYY年MM月DD日 hh时mm分ss秒');
        var week = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', '日', '一', '二', '三', '四', '五', '六'];
        return format.replace(/YYYY|YY|MM|DD|hh|mm|ss|星期|周|www|week/g, function(a) {
            switch (a) {
            case "YYYY": return date.getFullYear();
            case "YY": return (date.getFullYear()+"").slice(2);
            case "MM": return date.getMonth() + 1<10?'0'+(date.getMonth() + 1):date.getMonth() + 1;
            case "DD": return date.getDate()<10?'0'+date.getDate():date.getDate();
            case "hh": return date.getHours() <10 ? '0' +date.getHours():date.getHours();
            case "mm": return date.getMinutes()<10 ? '0' +date.getMinutes():date.getMinutes();
            case "ss": return date.getSeconds()<10 ? '0' +date.getSeconds():date.getSeconds();
            case "星期": return "星期" + week[date.getDay() + 7];
            case "周": return "周" +  week[date.getDay() + 7];
            case "week": return week[date.getDay()];
            case "www": return week[date.getDay()].slice(0,3);
            }
        });
    };
    /**
     * 自动把json数据绑定到表单或者div等容器里的控件（input、span、div）,同时支持以dom的name属性或id属性<br>
     * 目前不支持多值的checkbox绑定！<br>
     * 支持字典表绑定转换：如
     * 例子：<input type="text" name="xx"></input>，{"xx":'xx的值'}<br>
     * 		<input type="radio" name="xx" value="1"></input>，<input type="radio" name="xx" value="2"></input>{"xx":'1'}<br>
     * 		<input type="checkbox" name="xx" value="1"></input>，<input type="checkbox" name="xx" value="2"></input>{"xx":'1'}<br>
     * 		<span id="xx" convert="1:单选;2:多选"></span>，{"xx":'1'}<br>
     * @param selectorId要填充数据的dom容器id，可以是form id、div id等...
     * @param jsonObj 要绑定的json格式数据
     */
    appUtil.bindFormData=function(selectorId, jsonObj,excludes) {
		try {
			// TODO:excludes排除不需要显示的列
			for ( var key in jsonObj) {
				var $obj = $('#'+selectorId + ' :input[name="' + key+'"]');
				if($obj.length==0){
					$obj=$('#'+selectorId + ' :input[id="' + key+'"]');
					if($obj.length==0){
						$obj=$('#'+selectorId + ' span[name="' + key+'"]');
						if($obj.length==0){
							$obj=$('#'+selectorId + ' span[id="' + key+'"]');
							if($obj.length==0){
								$obj=$('#'+selectorId + ' div[name="' + key+'"]');
								if($obj.length==0){
									$obj=$('#'+selectorId + ' div[id="' + key+'"]');
								}
							}
						}
					}
				}
				var obj=$obj[0];
				if (obj) {
					var tagName = obj.tagName.toLowerCase();
					if (tagName == "div" || tagName == "span") {
						var convert = $obj.attr('data-convert');//<span id="xx" convert="1:单选;2:多选"></span>
						if(convert){
							var convertArray = convert.split(';');
							var dataConvert={};
							$.each(convertArray,function(i,item){
								var itemData = item.split(':');
								var keyVal=$.trim(itemData[0]);
								var keyDisplay=$.trim(itemData[1]);
								dataConvert['key_'+keyVal]=keyDisplay;
							});
							$obj.html(dataConvert['key_'+jsonObj[key]]);
						}else{
							$obj.html(jsonObj[key]);
						}
						continue;
					}else if (tagName == "input") {
						if (obj.type.toLowerCase() == "checkbox" || obj.type.toLowerCase()=='radio') {
							$obj.attr('checked',false);
							$obj.filter('[value='+jsonObj[key]+']').attr('checked',true);
						}else{
							$obj.val(jsonObj[key]);
						}
					}else if(tagName == "select"){
						$obj.val(jsonObj[key]);
					}else{
						$obj.val(jsonObj[key]);
					}
				}
			}
		} catch (e) {
			alert("客户端绑定错误:\n" + "URL:\n\t" + this._curPage + "\nmessage:\n\t" + e.message);
		}
	};
	//js获取项目根路径，如： http://localhost:8083/progect
	appUtil.getRootPath=function (){
	    return appUtil.projectName;
	};
	//js获取项目根路径，如： http://localhost:8083/progect
	appUtil.getProjectName=function (){
		return appUtil.projectName
	};
	/**
	 * 序列话表单对象，如：{userId:'1',userName:'xxxx',...}
	 */
	appUtil.serializeForm=function (formId){
		var formDataArray = $("#"+formId).serializeArray();
		var formDataObj={};
		$.each(formDataArray,function(i,elementData){
			if(formDataObj[elementData.name]!=undefined){
				var value=formDataObj[elementData.name];
				if(value.constructor == Array){
					value.push(elementData.value);
				}else{
					formDataObj[elementData.name]=[value];
					formDataObj[elementData.name].push(elementData.value);
				}
			}else{
				formDataObj[elementData.name]=elementData.value;
			}
		});
		return formDataObj;
	};
	
	appUtil.PrettyTime = function(dateTimeStamp) {
		var minute = 1000 * 60;
		var hour = minute * 60;
		var day = hour * 24;
		var halfamonth = day * 15;
		var month = day * 30;
		var now = new Date().getTime();
		var diffValue = now - dateTimeStamp;
		/*if (diffValue < 0) {
		}*/
		var monthC = diffValue / month;
		var weekC = diffValue / (7 * day);
		var dayC = diffValue / day;
		var hourC = diffValue / hour;
		var minC = diffValue / minute;
		var result="";
		if (monthC >= 1) {
			result = parseInt(monthC) + "个月前";
		} else if (weekC >= 1) {
			result = parseInt(weekC) + "周前";
		} else if (dayC >= 1) {
			result = parseInt(dayC) + "天前";
		} else if (hourC >= 1) {
			result = parseInt(hourC) + "个小时前";
		} else if (minC >= 1) {
			result = parseInt(minC) + "分钟前";
		} else
			result = "刚刚";
		return result;
	};
	
	//引自zui Messager
	;(function($, window, document, Math)
	{
	    var id       = 0;
	    var template = '<div class="messager messager-{type} {placement}" id="messager{id}" style="display:none"><div class="messager-content">{message}</div><button class="close-messager">&times;</button></div>';

	    function Messager()
	    {
	        this.show = function(message, type, placement, time, parent)
	        {
	            $('.messager').hide();

	            id++;
	            type = type || 'default';
	            time = time || 2000;
	            parent = parent || 'body';
	            placement = placement || 'top';
	            var msg = $(template.format({message: message, type: type, placement: placement, id: id})).appendTo(parent);
	            msg.find('.close-messager').click(function(){$(this).closest('.messager').fadeOut();});

	            if(placement == 'top' || placement == 'bottom')
	            {
	                msg.css('left', ($(parent).width() - msg.width() - 50)/2);
	            }

	            msg.fadeIn();

	            setTimeout(function(){$('#messager' + id).fadeOut(function(){$(this).remove()});}, time);

	            return msg;
	        }

	        this.primary = function(message, placement, time, parent)
	        {
	            return this.show(message, 'primary', placement, time, parent);
	        }

	        this.success = function(message, placement, time, parent)
	        {
	            return this.show('<i class="icon-ok-sign icon"></i> ' + message, 'success', placement, time, parent);
	        }

	        this.info = function(message, placement, time, parent)
	        {
	            return this.show('<i class="icon-info-sign icon"></i> ' + message, 'info', placement, time, parent);
	        }

	        this.warning = function(message, placement, time, parent)
	        {
	            return this.show('<i class="icon-warning-sign icon"></i>' + message, 'warning', placement, time, parent);
	        }

	        this.danger = function(message, placement, time, parent)
	        {
	            return this.show('<i class="icon-exclamation-sign icon"></i>' + message, 'danger', placement, time, parent);
	        }

	        this.important = function(message, placement, time, parent)
	        {
	            return this.show(message, 'important', placement, time, parent);
	        }

	        this.special = function(message, placement, time, parent)
	        {
	            return this.show(message, 'special', placement, time, parent);
	        }
	    }

	    var messager = new Messager();
	    appUtil.messager = messager;
	})($,window,document,Math);
	
	module.exports = appUtil;
});