/**
 * 产品目录树选择器：支持多选和单选<br>
 * 支持多个trigger，如$('#trigger1,#trigger2')
 * @author gengzi 350049339@qq.com
 * 调用示例：catalogueSelector($('#departName'),function(catalogue|checkCatalogues[,noCheckCatalogues]){
 * 				//单选时回调函数的参数为catalogue对象，多选时返回选择的catalogue数组和未选择的catalogue数组
 * 				//回调函数内，this指向trigger的DOM HtmlElement
 * 				.........
 * 			}[,{multiSelect:false,chkboxType:{ "Y": "ps", "N": "ps" },clearOnOpen:(true:default)|false}}]);
 */
define(function (require, exports, module) {
	var $ = require('$');
	var Dialog=require('artDialog/6.0.1/src/dialog.js');
	var appUtil=require('module/app-util/util.js');
	var selectorEvent = require('module/selectorEvent.js');
	require('ztree');
	var catalogueSelector=function(trigger,callback,options){
		return new catalogueSelector.create(trigger,callback,options);
	};
	catalogueSelector.create=function(trigger,callback,options){
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
			cancel:function(){this.close();return false;},
			onshow:function(){
				that.$parendDiv.show();
				if(that.lazyLoad){
					that.initDepartTree();
				}
				if(that.clearOnOpen){
					if(that.multiSelect){
						that.treeObj.checkAllNodes(false);
					}else{
						that.treeObj.cancelSelectedNode();
					}
				}
			},
			ok:function(){
				var checkCatalogues = that.treeObj.getCheckedNodes(true);
				var noCheckCatalogues = that.treeObj.getCheckedNodes(false);
				if(that.multiSelect){
					if(checkCatalogues.length==0){
						that.appUtil.showMsg('alert','请选择产品目录！',function(){that.showDialog();});
					}else{
						callback.call(that.CUR_TRIGGER_ELEMENT,checkCatalogues,noCheckCatalogues);
					}
				}else{
					if(that.treeObj.getSelectedNodes().length==0){
						that.appUtil.showMsg('alert','请选择产品目录！',function(){that.showDialog();});
					}else{
						var catalogue = that.treeObj.getSelectedNodes()[0];
						if(catalogue.catalogId=='0'){
							that.appUtil.showMsg('alert','请选择产品目录！',function(){that.showDialog();});
						}else{
							callback.call(that.CUR_TRIGGER_ELEMENT,catalogue);
						}
					}
				}
				this.close();
				return false;
			}
		});
		//绑定trigger的弹出窗口事件
		this.triggerClick(trigger);
	};
	/*默认配置*/
	var parendDivId="catalogueSelector";
	var idPrefix = parendDivId+'_';
	var config={multiSelect:false,chkboxType:{ "Y": "ps", "N": "ps" },clearOnOpen:true};//默认配置
	config.parendDivId=parendDivId;
	config.idPrefix=idPrefix;
	config.treeUIid=idPrefix+"departTree";
	config.catalogueTreeUrl=appUtil.getRootPath()+'/sale/catalogue/tree.do';
	//继承selectorEvent
	$.extend(config, new selectorEvent());
	catalogueSelector.prototype =catalogueSelector.create.prototype = $.extend(config, {
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
			     '<div id="',this.parendDivId,'" style="display:none;width:200px;">',
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
					 	url:this.catalogueTreeUrl,
					 	autoParam:["catalogId"],
					 	dataFilter : function(treeId, parentNode,
								responseData) {
							if (responseData) {
								for ( var i = 0; i < responseData.length; i++) {
									if(responseData[i].name){
										responseData[i].catalogName = responseData[i].name;
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
	module.exports = catalogueSelector;
});