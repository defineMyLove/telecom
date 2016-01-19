/**
 * 物料选择器，支持多选和单选；<br>
 * 支持多个trigger，如$('#trigger1,#trigger2')
 * @author gengzi 350049339@qq.com
 * 调用示例：saGoodSelector($('#goodId'),function(good or good[]){
 * 				//获取good.属性进行自定义处理、如果多选返回good数组
 * 				//回调函数内，this指向trigger的DOM HtmlElement
 * 				.........
 * 			}[,{multiSelect:true|(default:false),clearOnOpen:(true:default)|false}}]);
 */
define(function (require, exports, module) {
	var $ = require('$');
	var Dialog=require('artDialog/6.0.1/src/dialog.js');
	var appListPage=require('module/app-listPage/listPage.js');
	var sysDict=require('module/sysDict.js');
	var handlebars=require('handlebars');
	var selectorEvent = require('module/selectorEvent.js');
	var saGoodSelector=function(trigger,callback,options){
		return new saGoodSelector.create(trigger,callback,options);
	};
	saGoodSelector.create=function(trigger,callback,options){
		//覆盖默认配置
		$.extend(this, options);
		var that=this;
		this.initLayout();
		if(!this.lazyLoad){
			this.initCustTable();
		}
		this.dialog=this.Dialog({
			id:this.idPrefix+'dialog',
			content:this.$parendDiv[0],
			quickClose: true,
			cancel:function(){this.close();return false;},
			onshow:function(){
				that.$parendDiv.show();
				if(that.lazyLoad){
					that.initCustTable();
				}
				if(that.clearOnOpen){
					that.pageList.gridObj.deselect('all');
				}
			},
			ok:function(){
				var selectedRows = that.pageList.gridObj.selectedRows();
				if (selectedRows.length==0) {
					that.appListPage.util.showMsg('alert','请选择物料！',function(){that.showDialog();});
				}else{
					if(that.multiSelect){
						callback.call(that.CUR_TRIGGER_ELEMENT,selectedRows);
					}else{
						callback.call(that.CUR_TRIGGER_ELEMENT,selectedRows[0]);
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
	var parendDivId="saGoodSelector";
	var idPrefix = parendDivId+'_';
	var config={multiSelect:false,clearOnOpen:true};
	config.parendDivId=parendDivId;
	config.idPrefix=idPrefix;
	config.tableFormId=idPrefix+'tableForm';
	config.searchBtnId=idPrefix+'searchBtn';
	config.tbListId=idPrefix+'tbList';
	config.tbListPgId=idPrefix+'tbListPg';
	config.custPageSearchUrl=appListPage.util.getRootPath()+'/sale/good/list.do';
	//继承selectorEvent
	$.extend(config, new selectorEvent());
	saGoodSelector.prototype =saGoodSelector.create.prototype = $.extend(config, {
		$:$,
		appListPage:appListPage,
		Dialog:Dialog,
		handlebars:handlebars,
		sysDict:sysDict,
		/**
		 * 要构造的html dom id
		 */
		
		/**
		 * 对象
		 */
		pageList:null,
		/**
		 * 初始化html
		 */
		initLayout:function(){
			var pagegHtmls=[
			     '<div id="',this.parendDivId,'" style="display:none;width: 600px;overflow: hidden;" class="miniGrid">',
				    '<form id="',this.tableFormId,'" action="#">',
				    	'<div class="row" style="margin-bottom: 10px;">',
				    		'<div class="col-md-10 col-sm-10 col-xs-10">',
								'<div class="input-group">',
									'<span class="input-group-addon">物料名称</span>',
									'<input type="text" name="goodName" class="form-control">',
									'<span class="input-group-addon fix-border">物料类型</span>',
									'<select name="type" class="form-control">',
										'<option value="">---请选择---</option>',
										'{{#each goodTypeList}}',
										'<option value="{{key}}">{{text}}</option>',
										'{{/each}}',
									'</select>',
								'</div>',
							'</div>',
							'<div class="col-md-2 col-sm-2 col-xs-2">',
								'<input type="button" value="查询"  id="',this.searchBtnId,'" class="btn btn-primary"/>',
							'</div>',
						'</div>',
					'</form>',
			    	'<table id="',this.tbListId,'"></table>',
					'<div id="',this.tbListPgId,'"></div>',
				'</div>'
			].join("");
			var myTemplate = this.handlebars.compile(pagegHtmls);
			var goodTypeList = this.sysDict.getDictvalueList(this.sysDict.constant.DICT_GOOD_TYPE);
			$('body').append(myTemplate({goodTypeList:goodTypeList}));
			this.$parendDiv=$('#'+this.parendDivId);
		},
		initCustTable:function(){
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
				url:this.custPageSearchUrl,
				limitList:[10]
			},{
				height:'auto',
				width:'auto',
				fullWidthRows : true,
				checkCol : true,
				multiSelect:this.multiSelect,
				//indexCol : true,
				showBackboard : false,
				autoLoad : false,
				nowrap : true,
				cols : [
					{
						title:'物料名称',
						name:'goodName',
						width: 150
					},
					{
						title:'物料类型',
						width: 100,
						name:'type',
						renderer:function(val,item,rowIndex){
							return $('#'+that.tableFormId+' select[name="type"] option[value="'+val+'"]').text();
						}
					}
				],
				loadSuccess:function(){//重置正确位置
					if(that.lazyLoad){
						that.dialog.reset();
					}
				}
			});
		}
	});
	module.exports = saGoodSelector;
});