/**
 * 部门树选择器：支持多选和单选<br>
 * 支持多个trigger，如$('#trigger1,#trigger2')
 * @author gengzi 350049339@qq.com
 * 调用示例：departSelector($('#departName'),function(depart|checkDeparts[,noCheckDeparts]){
 * 				//单选时回调函数的参数为depart对象，多选时返回选择的depart数组和未选择的depart数组
 * 				//回调函数内，this指向trigger的DOM HtmlElement
 * 				.........
 * 			}[,{multiSelect:false,chkboxType:{ "Y": "ps", "N": "ps" },clearOnOpen:true:default|false,reAsyncOnOpen:true|false:default,cancelValue:'清除',cancelFun:function(){}]);
 */
define(function (require, exports, module) {

	var $ = require('$');
	var Dialog=require('artDialog/6.0.1/src/dialog.js');
	var appUtil=require('module/app-util/util.js');
	var selectorEvent = require('module/selectorEvent.js');
	require('ztree');
	var departSelector=function(trigger,callback,options){
		return new departSelector.create(trigger,callback,options);
	};
	departSelector.create=function(trigger,callback,options){
		var that=this;
		//覆盖默认配置
		$.extend(this, options);
		this.initLayout();
		if(!this.lazyLoad){
			this.initDepartTree();
		}
		this.dialog=this.Dialog({
			id:this.idPrefix+'dialog',
			content:this.$parendDiv[0],
			quickClose: true,
			autofocus:false,
			cancel:false,
			ok:false,
			button: [//用自定义button是避免清除时执行默认的cancel方法
				{
				    value: this.cancelValue||'取消',
				    callback: function () {
				    	if(that.cancelFun){
							that.cancelFun.call(that.CUR_TRIGGER_ELEMENT);
						}
						this.close();
						return false;
				    }
				},
				{
				    value: '确定',
				    callback: function () {
				    	var checkDeparts = that.treeObj.getCheckedNodes(true);
						var noCheckDeparts = that.treeObj.getCheckedNodes(false);
						if(that.multiSelect){
							if(checkDeparts.length==0){
								that.appUtil.showMsg('alert','请选择部门！',function(){that.showDialog();});
							}else{
								callback.call(that.CUR_TRIGGER_ELEMENT,checkDeparts,noCheckDeparts);
							}
						}else{
							if(that.treeObj.getSelectedNodes().length==0){
								that.appUtil.showMsg('alert','请选择部门！',function(){that.showDialog();});
							}else{
								var depart = that.treeObj.getSelectedNodes()[0];
								callback.call(that.CUR_TRIGGER_ELEMENT,depart);
							}
						}
						this.close();
						return false;
				    },
				    autofocus: true
				}
			],
			onshow:function(){
				that.$parendDiv.show();
				if(!that.treeObj && that.lazyLoad){
					that.initDepartTree();
				}
				if(that.clearOnOpen){
					if(that.multiSelect){
						that.treeObj.checkAllNodes(false);
					}else{
						that.treeObj.cancelSelectedNode();
					}
				}
				if(that.reAsyncOnOpen){
					that.treeObj.reAsyncChildNodes(that.getNodeByParam("departId", 0, null), "refresh");
				}
			}
		});
		//绑定trigger的弹出窗口事件
		this.triggerClick(trigger);
	};
	/*默认配置*/
	var parendDivId="departSelector";
	var idPrefix = parendDivId+'_';
	var config={//默认配置
			multiSelect:false,chkboxType:{ "Y": "ps", "N": "ps" },
			clearOnOpen:true,
			reAsyncOnOpen:false,
			cancelValue:false,//取消按钮文本
			cancelFun:false};//关闭窗口的回调函数
	config.parendDivId=parendDivId;
	config.idPrefix=idPrefix;
	config.treeUIid=idPrefix+"departTree";
	config.departTreeUrl=appUtil.getRootPath()+'/system/depart/tree.do';
	//继承selectorEvent
	$.extend(config, new selectorEvent());
	departSelector.prototype =departSelector.create.prototype = $.extend(config, {
		$:$,
		Dialog:Dialog,
		appUtil:appUtil,
		/**
		 * 对象
		 */
		treeObj:null,
		/**
		 * 初始化html
		 */
		initLayout:function(){
			var pagegHtmls=[
			     '<div id="',this.parendDivId,'" style="display:none;">',
						'<ul id="',this.treeUIid,'" class="ztree"></ul>',
				'</div>'
			].join("");
			$('body').append(pagegHtmls);
			this.$parendDiv=$('#'+this.parendDivId);
		},
		initDepartTree:function(){
			var that=this;
			var treeSetting={
					async:{
					 	enable:true,
					 	url:this.departTreeUrl,
					 	autoParam:["departId"],
					 	dataFilter : function(treeId, parentNode,
								responseData) {
							if (responseData) {
								for ( var i = 0; i < responseData.length; i++) {
									if(responseData[i].name){
										responseData[i].departName = responseData[i].name;
									}
								}
							}
							return responseData;
						}
					 },
					 callback:{
					 		onClick : function onTreeClick(e, treeId, node) {
								if (node.isParent == true
										|| node.isParent == 'true') {
									that.treeObj.expandNode(node, true, false,true);
								}
							},
					 		onAsyncSuccess : function(event, treeId,
									treeNode, msg) {
					 			//默认展开根节点
								if (treeNode==undefined) {
									that.treeObj.selectNode(that.treeObj.getNodes()[0]);
									that.treeObj.expandNode(that.treeObj.getNodes()[0], true,
											false, true);
								}
								//重置正确位置
								if(that.lazyLoad){
										that.dialog.reset();
								}
							}
						}
			};
			if(this.multiSelect){
				treeSetting.check={
					enable: true,
					chkStyle: "checkbox",
					chkboxType:this.chkboxType
				};
			}
			this.treeObj= $.fn.zTree.init($('#'+this.treeUIid),treeSetting);;
		}
	});
	module.exports = departSelector;
});