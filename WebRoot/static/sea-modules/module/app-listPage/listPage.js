/**
 * 列表页封装，同时加载工具类
 * @author gengzi 350049339@qq.com
 *  api:
 *  {
 		
 *  }
 */
define(function (require, exports, module) {
	var $=require('$');
	var appUtil = require('app-util');
	var listPage={util:appUtil};
	//ajax分页搜索
	require('bootstrap-mmGrid');
	listPage.pageList=function(pageOpts,gridOpts){
		return new listPage.pageList.create(pageOpts,gridOpts);
	};
	listPage.pageList.create=function(pageOpts,gridOpts){
		var that = this;
		//使用分页
		this.defaultOpts={
			limitList:[10,20,30],
			fullPageStyle:true//分页展示方式：完整展示|只展示上下页
		};
		if(pageOpts.limitList===undefined){
			pageOpts.limitList=this.defaultOpts.limitList;
		}
		if(pageOpts.fullPageStyle===undefined){
			pageOpts.fullPageStyle=this.defaultOpts.fullPageStyle;
		}
		//TODO:重构公用三处相同的ajax查询操作
		//分页对象
		this.paginatorObj = $(pageOpts.tbListPg).myMMPaginator({
				fullPageStyle:pageOpts.fullPageStyle,
				limitList : pageOpts.limitList,
				loadFunction : [function(pageParam){
					if(pageOpts.before){pageOpts.before()}
					var data = $(pageOpts.searchForm).serialize();
					var pageParamStr = "";
					$.each(pageParam,function(key,val){
						pageParamStr+=key+"="+val+"&";
					});
					data+='&'+pageParamStr;
					that.gridObj._showLoading();
					$.ajax({
						async:true,
						type:"POST",
						data:data,
						url:pageOpts.url,
						success:function(response){//TODO:可配置从response对象的其他属性中读取data
							that.gridObj.load(response);
							that.gridObj._hideLoading();
						}
					});
				}]
		});
		var plugins = [ this.paginatorObj];
		gridOpts.plugins=plugins;
		this.$tbList = $(pageOpts.tbList);
		//构造表格
		this.gridObj = this.$tbList.mmGrid(gridOpts);
		//默认查询第一页
		this.onQuery1 = function(){
			if(pageOpts.before){pageOpts.before()}
			//第一页
			var data = $(pageOpts.searchForm).serialize();
			var pageParamStr = 
				$.fn.myMMPaginator.defaults.pageParamName+'=1&'
				+$.fn.myMMPaginator.defaults.limitParamName+'='+pageOpts.limitList[0];
			data+='&'+pageParamStr;
			that.gridObj._showLoading();
			$.ajax({
				async:true,
				type:"POST",
				data:data,
				url:pageOpts.url,
				success:function(response){//TODO:可配置从response对象的其他属性中读取data
					that.gridObj.load(response);
					that.gridObj._hideLoading();
				}
			});
		};
		//按钮触发查询
		if(pageOpts.searchBtn){
			$(pageOpts.searchBtn).click(this.onQuery1);
		}
		//表单enter触发查询
		if(pageOpts.searchForm){
			$(pageOpts.searchForm).bind('keypress',function(event){
				 if(event.keyCode == "13")
				 {
					 that.onQuery1();
					 event.preventDefault();
				 }
			 });
		}
		//自动查询
		if(pageOpts.autoload){
			that.onQuery1();
		}
	}
	module.exports = listPage;
});