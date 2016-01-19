/**
 * 	用户选择器
 *  api:{
 *  	options:{contentPath:'',fileServer:'',zIndex:999}
 *  }
 */
define(function (require, exports, module) {
    var $ = require('$');
    var appUtil= require('app-util');
    var Popup = require('../../arale/popup/1.1.6/popup');
    var avalon =  require('avalon');
    var tpl = require('./tpl.html');
    var UUID=0;//groupUserSelector控件id生成器，来支持一多个实例
    var groupUserSelector=function(trigger,callback,options){
    	UUID++;
		return new groupUserSelector.create(trigger,callback,options);
	};
	groupUserSelector.create=function(trigger,callback,options){
		//覆盖默认配置
		$.extend(this, options);
		this.id="groupUserSelector"+UUID,
		this.vmId='groupUserSelectorVM'+UUID,
		this.trigger=trigger;
		this.callback=callback;
		var that=this;
		this.initLayout();
		//数据请求和填充avalon
		this.bindVM();
	}
	
	/*groupUserSelector原型*/
	function config(){
		this.$=$;
		this.Popup=Popup;
		this.avalon=avalon;
		this.tpl=tpl;
		this.$el=null;
		this.popup=null;
		this.curTrigger=null;
		this.vm=null;
		this.showUserCount = true;
		this.initLayout=function(){
			var that =this;
			that.tpl = that.tpl.replace('__groupUserSelector',that.id);
			that.tpl = that.tpl.replace('MS_CONTROLLER',that.vmId);
			that.$el = $(that.tpl).appendTo('body');
			var popupConfig={
		            trigger:that.trigger,
		            triggerType: 'click',
		            align: that.align||{
		                baseXY: [0, '100%+2']
		            },
		            element: '#' + that.id
		        };
			if(!!that.zIndex){
				popupConfig.zIndex=that.zIndex;
			}
			that.popup=new that.Popup(popupConfig).before('show', function(){
	        	that.curTrigger = this.activeTrigger;
	        });
		};
		this.bindVM=function(){
			var that =this;
			that.vm=avalon.define({
				$id:that.vmId,
				controllerId:that.vmId,//tpl模版ui的id前缀
				contextPath:that.contentPath,
				fileServer:that.fileServer,
				pindexArrLine1:['A','B','C','D','E','F','G','H','I','J','K','L','M','N'],//ui导航
				pindexArrLine2:['O','P','Q','R','S','T','U','V','W','X','Y','Z','#'],//ui导航
				$skipArray: ["controllerId","contextPath", "fileServer","pindexArrLine1","pindexArrLine2"],
				
				tabIndex:0,//tab页
				tab:function(index){that.vm.tabIndex=index},
				userCount:0,//用户数量
				userList:[],//{index:'A',items:[user1,user2]}
				selectedUserPindex:[],//ui导航索引可用列表
				toIndex:function(target,parent){//定位滚动条
					var container = $('#'+parent);
					var scrollTo = $('#'+target);
					if(container.length==0 || scrollTo.length==0){
						return;
					}
					container.animate({
					    scrollTop:scrollTo.offset().top - container.offset().top + container.scrollTop()
					});

				},
				/**人员或部门点击事件
				 * @param item 用户
				 */
				itemClick:function(type,item){
					//this指向触发弹出框元素的jquery封装对象
					that.callback.call(that.curTrigger,item);
				},
				closePopup:function(){//关闭弹出框
					that.popup.hide();
				}
			});
			avalon.scan(that.$el[0]);
			var pindexArr=['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','#'];
			//请求数据-全部用户
			var selectedPindex1=[];
			$.ajax({
				async:true,
				type:"POST",
				url:that.contentPath+"my/group/ajaxGetAllUser",
				success:function(userList){
					that.vm.userCount=userList.length;
					var userM ={};//{{'A':[user1,user2]},}
					$.each(userList,function(i,user){
						//处理默认头像
						if(!user.userPhoto){
							user.userPhoto=appUtil.getRootPath()+'/img/icon_addfriend.png';
						}else{
							user.userPhoto=that.fileServer+user.userPhoto;
						}
						var pindex = (user.pinyin||'#').toUpperCase().substr(0,1);
						if(pindexArr.indexOf(pindex)==-1){
							pindex='#';
						}
						if(!userM[pindex]){
							userM[pindex]=[user];
						}else{
							userM[pindex].push(user);
						}
						if(selectedPindex1.indexOf(pindex)==-1){
							selectedPindex1.push(pindex);
						}
					});
					//排序
					var userListP=[];
					$.each(pindexArr,function(i,pindex){
						if(!!userM[pindex]){
							userListP.push({index:pindex,items:userM[pindex]});
						}
					});
					that.vm.userList=userListP;
					that.vm.selectedUserPindex=selectedPindex1;
				}
			});
		};
	};
	groupUserSelector.prototype =groupUserSelector.create.prototype = new config();

    module.exports = groupUserSelector;
});