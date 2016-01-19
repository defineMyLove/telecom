/**
 * 产品选择器；<br>
 * 支持多个trigger，如$('#trigger1,#trigger2')
 * @author gengzi 350049339@qq.com
 * 调用示例：productSelector($('#userName'),function(product or product[],catalogue){
 * 				//回调函数内，this指向trigger的DOM HtmlElement
 * 				//获取product.属性进行自定义处理、如果多选返回product数组；
 * 				//catalogue:是产品分类信息
 * 				.........
 * 			}[,{multiSelect:true|(default:false),clearOnOpen:(true:default)|false}]);
 */
define(function (require, exports, module) {
	var $ = require('$');
	var Dialog=require('artDialog/6.0.1/src/dialog.js');
	var appListPage=require('module/app-listPage/listPage.js');
	var selectorEvent = require('module/selectorEvent.js');
	require('ztree');
	var productSelector=function(trigger,callback,options){
		return new productSelector.create(trigger,callback,options);
	};
	productSelector.create=function(trigger,callback,options){
		var that=this;
		//覆盖默认配置
		$.extend(this, options);
		this.initLayout();
		if(!this.lazyLoad){
			that.initDepartTree();
			that.initUserTable();
		}
		this.dialog=this.Dialog({
			id:this.idPrefix+'dialog',
			content:this.$parendDiv[0],
			quickClose: true,
			cancel:function(){this.close();return false;},
			onshow:function(){
				that.$parendDiv.show();
				if(that.lazyLoad){
					that.initDepartTree();
					that.initUserTable();
				}
				if(that.clearOnOpen){
					that.pageList.gridObj.deselect('all');
				}
			},
			ok:function(){
				var selectedRows = that.pageList.gridObj.selectedRows();
				var catalogue = that.treeObj.getSelectedNodes()[0];
				if (selectedRows.length==0) {
					that.appListPage.util.showMsg('alert','请选择产品！',function(){that.showDialog();});
				}else{
					if(that.multiSelect){
						callback.call(that.CUR_TRIGGER_ELEMENT,selectedRows,catalogue);
					}else{
						callback.call(that.CUR_TRIGGER_ELEMENT,selectedRows[0],catalogue);
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
	var parendDivId="productSelector";
	var idPrefix = parendDivId+'_';
	var config={multiSelect:false,clearOnOpen:true};//默认配置
	config.parendDivId=parendDivId;
	config.idPrefix=idPrefix;
	config.treeUIid=idPrefix+"departTree";
	config.tableFormId=idPrefix+'tableForm';
	config.searchBtnId=idPrefix+'searchBtn';
	config.tbListId=idPrefix+'tbList';
	config.tbListPgId=idPrefix+'tbListPg';
	config.catalogueTreeUrl=appListPage.util.getRootPath()+'/sale/catalogue/tree.do';
	config.productPageSearchUrl=appListPage.util.getRootPath()+'/sale/product/list.do';
	//继承selectorEvent
	$.extend(config, new selectorEvent());
	productSelector.prototype =productSelector.create.prototype = $.extend(config, {
		$:$,
		appListPage:appListPage,
		Dialog:Dialog,
		/**
		 * 要构造的html dom id
		 */
		
		/**
		 * 对象
		 */
		treeObj:null,
		pageList:null,
		/**
		 * 初始化html
		 */
		initLayout:function(){
			var pagegHtmls=[
			     '<div id="',this.parendDivId,'" style="display:none;" class="miniGrid">',
					'<div style="width:150px;float: left;margin:5px;">',
						'<ul id="',this.treeUIid,'" class="ztree"></ul>',
					'</div>',
					'<div style="width:550px;overflow: hidden;">',
					    '<div style="height:auto;padding-top:5px;">',
				        	'<form id="',this.tableFormId,'" action="#">',
				        		'<input type="hidden" name="catalogId">',
					        	'<div class="row" style="margin-bottom: 10px;">',
						    		'<div class="col-md-10 col-sm-10 col-xs-10">',
										'<div class="input-group">',
											'<span class="input-group-addon">产品名称</span>',
											'<input type="text" name="proName" class="form-control">',
											'<span class="input-group-addon fix-border">条形码</span>',
											'<input type="text" name="barCode" class="form-control">',
										'</div>',
									'</div>',
									'<div class="col-md-2 col-sm-2 col-xs-2">',
										'<input type="button" value="查询"  id="',this.searchBtnId,'" class="btn btn-primary"/>',
									'</div>',
								'</div>',
							'</form>',
					    '</div>',
				    	'<table id="',this.tbListId,'"></table>',
						'<div id="',this.tbListPgId,'"></div>',
					'</div>',
				'</div>'
			].join("");
			$('body').append(pagegHtmls);
			this.$catalogIdInput = $('#'+this.tableFormId+' input[name="catalogId"]');
			this.$parendDiv=$('#'+this.parendDivId);
		},
		initDepartTree:function(){
			if(this.treeObj){
				return;
			}
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
								if(node.catalogId=='0'){
									that.$catalogIdInput.val('');
								}else{
									that.$catalogIdInput.val(node.catalogId);
								}
								that.treeObj.selectNode(node);
								that.pageList.paginatorObj.reload(true);//刷新用户表格
							},
					 		onAsyncSuccess : function(event, treeId,
									treeNode, msg) {
					 			//默认展开根节点
								if (treeNode==undefined) {
									that.treeObj.selectNode(that.treeObj.getNodes()[0]);
									that.treeObj.expandNode(that.treeObj.getNodes()[0], true,
											false, true);
								}
								if(that.lazyLoad){
									that.dialog.reset();
								}
							}
						}
			};
			this.treeObj= $.fn.zTree.init($('#'+this.treeUIid),treeSetting);;
		},
		initUserTable:function(){
			if(this.pageList){
				return;
			}
			var that=this;
			this.pageList=appListPage.pageList({
				tbList:'#'+this.tbListId,
				searchForm:'#'+this.tableFormId,
				searchBtn:'#'+this.searchBtnId,
				autoload:true,
				tbListPg:'#'+this.tbListPgId,
				url:this.productPageSearchUrl,
				limitList:[10]
			},{
				height : 'auto',
				width:'auto',
				fullWidthRows : true,
				checkCol : true,
				multiSelect:this.multiSelect,
				//indexCol : true,
				showBackboard:false,
				autoLoad : false,
				nowrap : true,
				loadSuccess:function(){//重置正确位置
					if(that.lazyLoad){
						that.dialog.reset();
					}
				},
				cols : [
					{
						title:'产品名称',
						name:'proName',
						width: 150
					},
					{
						title:'条形码',
						name:'barCode',
						width: 110
					},
					{
						title:'产品规格',
						width: 60,
						name:'specification',
						hidden:false
					},
					{
						title:'型号',
						width: 60,
						name:'model',
						hidden:false
					},
					{
						title:'价格(￥)',
						width: 50,
						name:'price',
						hidden:false,
						renderer:function(val,item,rowIndex){
							if(val!=null){
								if(item.mesureId){
									return val+'/'+item.measureName;
								}else{
									return val;
								}
							}else{
								return '<span style="color:#808080;">无</span>';
							}
						}
					}
				]
			});
		}
	});
	module.exports = productSelector;
});