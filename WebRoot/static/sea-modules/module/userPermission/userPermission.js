/**
 * 权限选择器，选择用户
 * @author gengzi 350049339@qq.com
 */
define(function (require, exports, module) {
	var $ = require('$');
	var appUtil=require('app-util');
	var userSelector=require('userSelector');
	var zui=require('zui');
	//加载依赖的样式
	var userPermission={
		USER_ID_ALL_COMP:-2,//标示userid是全部公司
		SELECTOR_ID:"userPermissionC",
		options:{},
		defaultOptions:{
			defaultQx:0,
			allCompText:'全公司',
			selfText:'我的客户',
			mySubText:'我的下属',
			display:'#userIdDiv',
			output:'#userId',
			initData:true
		},
		/**
		 * options:{
		 * 	userId:'',//用户id（*必传参数）
		 * 	isAdmin:false,//是否是管理员（*必传参数）
		 * 	defaultQx:0|1,//默认权限（0：我的xxxx（默认值）、1全公司(用户是管理员才有效)）
		 * 	allCompText:'全公司',//默认值
		 * 	selfText:'我的客户',//默认值
		 * 	mySubText:'我的下属',//默认值
		 * 	display:'#userIdDiv',//默认值
		 * 	output:'#userId',//默认值、简单回填userId到#userId里
		 * 	outputFun:function(userId){....}//代替output，自行回填和处理其他逻辑
		 *  initData:是否初始化数据，默认true
		 * }
		 */
		init:function(options){
			$.extend(this.options,this.defaultOptions);
			$.extend(this.options, options);
			//如果不是管理员，默认显示我的权限
			if(!options.isAdmin){
				this.options.defaultQx=0;
			}
			if(this.options.isAdmin){
				this.createAdminCom();
			}else{
				this.createNormalCom();
			}
		},
		//获取默认显示的控件文本
		getDefaultText:function(){
			return this.options.defaultQx==0?this.options.selfText:this.options.allCompText;
		},
		//创建管理员的权限组件
		createAdminCom:function(){
			seajs.importStyle([
			                   '#userPermission-userList{width:350px;background-color: #fff;font-size: 13px;overflow: hidden;display: block;border:none;border-top: solid #CCC 1px;}',
			                   '#userPermission-userList dl{margin:0;padding-top: 5px;padding-bottom:10;padding-left:5px;padding-right: 13px;}',
			                   '#userPermission-userList dl dd{line-height: 26px;float: left;display: inline;margin-left:10px;}',
			                   '#userPermission-userList dl dd a{color: #1155cc;display: block;text-align: center;text-decoration: none;}',
			                   '#userPermission-userList dl dd a:hover {color:#FF7200!important;}'].join(""));
			//初始化html
			var html=['<div class="btn-group" id="',this.SELECTOR_ID,'">',
		         '<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" id="userPermission-trigger">',
		         	'<span class="userPermissionName">',this.getDefaultText(),'</span>&nbsp;&nbsp;',
		            '<span class="caret"></span>',
		         '</button>',
		         '<ul class="dropdown-menu" role="menu" id="userPermission-menu">',
		          	'<li data-all="1"><a href="javascript:;">',this.options.allCompText,'</a></li>',
		          	'<li data-self="1"><a href="javascript:;">',this.options.selfText,'</a></li>',
		          	'<li data-alluser="1"><a href="javascript:;">选择用户</a></li>',
		          	'<li data-sub="1" class="dropdown-header">我的下属<i class="icon-arrow-down" style="display: inline-block;margin-left: 5px;"></i></li>',
		          	'<li>',
		          		'<div id="userPermission-userList"><dl></dl></div>',
		          	'</li>',
		         '</ul>',
			'</div>'].join("");
			$(html).appendTo(this.options.display);
			//绑定事件
			//全公司
			var that=this;
			if(that.options.allCompText){
				$('#userPermission-menu li[data-all="1"]').click(function(){
					$('#userPermission-trigger .userPermissionName').html(that.options.allCompText);
					if(that.options.outputFun==undefined){
						$(that.options.output).val(that.USER_ID_ALL_COMP);
					}else{
						that.options.outputFun(that.USER_ID_ALL_COMP);
					}
				});
			}else{
				$('#userPermission-menu li[data-all="1"]').remove();
			}
			//我的..
			$('#userPermission-menu li[data-self="1"]').click(function(){
				$('#userPermission-trigger .userPermissionName').html(that.options.selfText);
				if(that.options.outputFun==undefined){
					$(that.options.output).val(that.options.userId);
				}else{
					that.options.outputFun(that.options.userId);
				}
			});
			//选择用户
			userSelector($('#userPermission-menu li[data-alluser="1"]'),function(user){
				$('#userPermission-trigger .userPermissionName').html(user.userName);
				if(that.options.outputFun==undefined){
					$(that.options.output).val(user.userId);
				}else{
					that.options.outputFun(user.userId);
				}
			},{followElement:$('#'+this.SELECTOR_ID)[0]});
			//我的下属
			//获取下属列表
			$.ajax({
				async:true,
				data:{userId:this.options.userId},
				url:appUtil.getRootPath()+'/system/user/ajaxGetSubUserList.do',
				success:function(userList){
					$.each(userList,function(i,user){
						$('#userPermission-userList dl').append('<dd><a href="javascript:void(0)" data-userName="'+user.userName+'" data-userId="'+user.userId+'">'+user.userName+'</a></dd>');
					});
					if(userList.length==0){
						$('#userPermission-userList dl').append('<dd style="color:#8C8C8C;">没有下属</dd>');
					}
					$('#userPermission-userList a').click(function(){
						var $this=$(this);
						$('#userPermission-trigger .userPermissionName').html($this.attr('data-userName'));
						if(that.options.outputFun==undefined){
							$(that.options.output).val($this.attr('data-userId'));
						}else{
							that.options.outputFun($this.attr('data-userId'));
						}
					});
				}
			});
			//初始化默认值
			if(that.options.initData){
				this.initDefaultVal();
			}
		},
		//创建普通人员的权限组件
		createNormalCom:function(){
			seajs.importStyle([
			                   '#userPermission-userList{width:350px;background-color: #fff;font-size: 13px;overflow: hidden;display: block;border:none;border-top: solid #CCC 1px;}',
			                   '#userPermission-userList dl{margin:0;padding-top: 5px;padding-bottom:10;padding-left:5px;padding-right: 13px;}',
			                   '#userPermission-userList dl dd{line-height: 26px;float: left;display: inline;margin-left:10px;}',
			                   '#userPermission-userList dl dd a{color: #1155cc;display: block;text-align: center;text-decoration: none;}',
			                   '#userPermission-userList dl dd a:hover {color:#FF7200!important;}'].join(""));
			//初始化html
			var html=['<div class="btn-group" id="',this.SELECTOR_ID,'">',
			          '<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" id="userPermission-trigger">',
			         	'<span class="userPermissionName">',this.getDefaultText(),'&nbsp;</span>&nbsp;&nbsp;',
			            '<span class="caret"></span>',
			         '</button>',
				         '<ul class="dropdown-menu" role="menu" id="userPermission-menu">',
				          	'<li data-self="1"><a href="javascript:;">',this.options.selfText,'</a></li>',
				          	'<li data-sub="1" class="dropdown-header">我的下属<i class="icon-arrow-down" style="display: inline-block;margin-left: 5px;"></i></li>',
				          	'<li>',
				          		'<div id="userPermission-userList"><dl></dl></div>',
				          	'</li>',
				         '</ul>',
					'</div>'].join("");
			$(html).appendTo(this.options.display);
			//绑定事件
			var that=this;
			//我的..
			$('#userPermission-menu li[data-self="1"]').click(function(){
				$('#userPermission-trigger .userPermissionName').html(that.options.selfText);
				if(that.options.outputFun==undefined){
					$(that.options.output).val(that.options.userId);
				}else{
					that.options.outputFun(that.options.userId);
				}
			});
			//我的下属
			//获取下属列表
			$.ajax({
				async:true,
				data:{userId:this.options.userId},
				url:appUtil.getRootPath()+'/system/user/ajaxGetSubUserList.do',
				success:function(userList){
					$.each(userList,function(i,user){
						$('#userPermission-userList dl').append('<dd><a href="javascript:void(0)" data-userName="'+user.userName+'" data-userId="'+user.userId+'">'+user.userName+'</a></dd>');
					});
					if(userList.length==0){
						$('#userPermission-userList dl').append('<dd style="color:#8C8C8C;">没有下属</dd>');
					}
					$('#userPermission-userList a').click(function(){
						var $this=$(this);
						$('#userPermission-trigger .userPermissionName').html($this.attr('data-userName'));
						if(that.options.outputFun==undefined){
							$(that.options.output).val($this.attr('data-userId'));
						}else{
							that.options.outputFun($this.attr('data-userId'));
						}
					});
				}
			});
			//初始化默认值
			if(this.options.initData){
				this.initDefaultVal();
			}
		},
		//初始化页面控件默认值（目前只支持初始化全公司和自己。 TOTO：，选择具体用户暂不支持）
		initDefaultVal:function(){
			var _userId=this.options.defaultQx==0?this.options.userId:this.USER_ID_ALL_COMP;
			if(this.options.outputFun==undefined){
				$(this.options.output).val(_userId);
			}else{
				this.options.outputFun(_userId);
			}
		}
	};
	module.exports = userPermission;
});