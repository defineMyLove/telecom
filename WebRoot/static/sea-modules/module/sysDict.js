/**
 * 字典表数据获取
 * @author gengzi 350049339@qq.com
 */
define(function (require, exports, module) {
	var $=require('$');
	var appUtil=require('module/app-util/util.js');
	var sysDict={
			constant:{CUSTOMER_TYPE:"custtype",DICT_GOOD_TYPE:"good",DICT_MEASURE:"measure"},
			ajax_getDictvalueListURL:appUtil.getRootPath()+'/system/sysdict/ajax_getSysdictList.do',
			cacheDictvalue:{},//{code:xx,dictvalueList:[]}
			cacheDictvalueMap:{},//{code:xx,dictvalueMap:{_valueId,text}}
			getDictvalueList:function(code){
				var dictvalueList=this.cacheDictvalue[code];
				if(dictvalueList){
					return dictvalueList;
				}
				this.ajax_getDictvalueList(code);
				return this.cacheDictvalue[code];
			},
			getDictvalue:function(code,key){
				if(this.cacheDictvalueMap[code]===undefined){
					this.ajax_getDictvalueList(code);
				}
				return this.cacheDictvalueMap[code]['_'+key];
			},
			ajax_getDictvalueList:function(code){
				var that =this;
				//ajax 同步获取，并存入缓存
				$.ajax({
					async:false,
					type:"POST",
					data:{code:code},
					url:this.ajax_getDictvalueListURL,
					success:function(response){
						if(that.cacheDictvalueMap[code]===undefined){
							that.cacheDictvalueMap[code]={};
						}
						$.each(response,function(i,dict){
							that.cacheDictvalueMap[code]['_'+dict.key]=dict.text;
						});
						that.cacheDictvalue[code]=response;
					}
				});
			}
	};
	module.exports = sysDict;
});