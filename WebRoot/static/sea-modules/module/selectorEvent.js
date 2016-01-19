/**
 * 通用选择树事件模型，其他模块需要继承调用 如custSelector.prototype=new selectorEvent();<br>
 * @author gengzi 350049339@qq.com
 */
define(function (require, exports, module) {
	var selectorEvent=function(){
		
	};
	selectorEvent.prototype={
			lazyLoad:true,//数据是否在弹出前加载
			dataFilter:null,//数据过滤条件，依赖各自模块,支持function动态获取参数值如：{userId:function(){..return userId;}}
			genHiddenQueryParam:function(){
				var queryParam={};
				if(this.dataFilter){
					this.$.each(this.dataFilter,function(key,val){
						if(typeof val=='function'){
							val=val();
						}
						queryParam[key]=val;
					});
				}
				return queryParam;
			},
			before:null,//前置方法，return false，不允许弹窗选择
			cancelValue:null,//自定义取消按钮文本
			cancelFun:null,//自定义取消方法
			//其他模块selector来继承
			trigger:null,
			tipModel:true,//是否以气泡浮层方式显示
			followElement:null,////dialog的气泡浮层跟随的元素，默认是$trigger
			//其他模块selector来继承
			dialog:null,
			//当前触发dialog弹窗的$trigger
			CUR_TRIGGER_ELEMENT:null,
			//弹框
			showDialog:function(el){
				if(this.before && this.before.call(el)===false){
					return;
				}
				if(el==undefined){
					el=this.CUR_TRIGGER_ELEMENT;
				}
				if(this.tipModel){
					if(this.followElement){
						this.dialog.show(this.followElement);
					}else{
						this.dialog.show(el);
					}
				}else{
					this.dialog.show();
					//this.dialog.reset();//重置正确位置
				}
			},
			//绑定触发弹框事件
			triggerClick:function($trigger){
				var that=this;
				$trigger.click(function(){
					that.CUR_TRIGGER_ELEMENT=this;
					that.showDialog(this);
				});
			},
			//添加新的触发器
			addTrigger:function($trigger){
				this.triggerClick($trigger);
			}
	};
	module.exports = selectorEvent;
});