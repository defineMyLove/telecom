/**
 * seajs配置文件入口
 * @author gengzi 350049339@qq.com
 */
seajs.config({
    alias: {
    	//gallery
        '$': 'jquery/1.10.2/jquery',
        '$-debug': 'jquery/1.10.2/jquery',
        'jquery':'jquery/1.10.2/jquery',
        'nivo-slider':'jquery-plugin/nivo-slider/jquery.nivo.slider.pack',
        'artDialogV6':'artDialog/6.0.1/src/dialog-plus.js',
        'handlebars':'gallery/handlebars/1.0.2/handlebars.js',
        'json':'gallery/json/1.0.3/json.js',
        'moment':'gallery/moment/2.6.0/moment.js',
        'sortable':'jquery-plugin/sortable/0.9.10/sortable.js',
        'jSort':'jquery-plugin/jquery.jsort.0.4.js',
        'poshytip':'jquery-plugin/poshytip-1.1/jquery.poshytip.min.js',
        'niceScroll':'jquery-plugin/nicescroll/jquery.nicescroll.min.js',
        'lhgdialog':'jquery-plugin/lhgdialog/lhgdialog.js',
        'jRating':'jquery-plugin/jRating/jRating.jquery.js',
        'chosen':'jquery-plugin/chosen/1.1.0/chosen.jquery.min.js',
        'input-mask':'jquery-plugin/input-mask.min.js',
        'bootstrap-select':'jquery-plugin/bootstrap-select.min.js',
        //arale、alice
        'tip':'arale/tip/1.2.2/tip',
        'popup':'arale/popup/1.1.6/popup',
        'calendar':'arale/calendar/1.0.0/calendar',
        'dialog':'arale/dialog/1.2.4/dialog',
        'upload':'arale/upload/1.0.1/upload',
        'tabs':'arale/switchable/1.0.1/tabs',
        'position':'arale/position/1.0.1/position',
        'nameStorage':'arale/name-storage/1.0.0/name-storage.js',
        'slide':'arale/switchable/1.0.1/slide',
        //other
        'ztree':'zTree/js/jquery.ztree.all-3.5.min.js',
        'pretty-photo':'atans/pretty-photo/3.1.5/js/jquery.prettyPhoto.js',
        'pretty-photo-css':'atans/pretty-photo/3.1.5/css/prettyPhoto.css',
        'zui':'zui/js/zui.js',
        'todc-bootstrap':'todc-bootstrap/js/bootstrap.min.js',
        'avalon':'avalon/avalon.min.js',
        'nprogress':'nprogress/0.1.6/nprogress.js',
        'autosize':'jquery-plugin/jquery.autosize.js',
        'jquery-strip':'jquery-plugin/strip/strip.pkgd.js',
        'jplayer':'jquery-plugin/jplayer/jquery.jplayer.js',
        'jsonSelect':'jsonselect.js',
        //自定义模块
        'pupTab':'module/pupTab/pupTab.js',
        'pupZtree':'module/pupZtree',
        'validateUtil':'module/validate/validateUtil.js',
        'bootstrap-mmGrid':'jquery-plugin/mmGrid/loadMyMmGrid.js',
        'citySelect':'module/citySelect/citySelect.js',
        'groupUserSelector':'module/groupUserSelector/groupUserSelector.js',
        'sysDict':'module/sysDict.js',
        'custSelector':'module/custSelector/custSelector.js',
        'productSelector':'module/productSelector/productSelector.js',
        'departSelector':'module/departSelector/departSelector.js',
        'catalogueSelector':'module/catalogueSelector/catalogueSelector.js',
        'saGoodSelector':'module/saGoodSelector/saGoodSelector.js',
        'userPermission':'module/userPermission/userPermission.js',
        //列表页
        'app-util':'module/app-util/util.js',
        'app-listPage':'module/app-listPage/listPage.js',
        'num-Util':'module/app-util/numUtil.js',
        'ligerui':'ligerui/1.2.0/js/ligerui.all.js'
    }
});
seajs.use('seajs/seajs-style/1.0.2/seajs-style.js');
/*seajs.use('seajs/seajs-css/1.0.1/seajs-css.js');*/
seajs.use('seajs/seajs-text.js');
//ajax全局loading配置
seajs.use(['$','nprogress'], function ($,NProgress) {
	$(document).ajaxStart(function(evt, request, settings) {
		//弹窗内不使用NProgress效果
		if(!(window.frameElement && !!window.frameElement.api)){
			NProgress.set(0.4) 
		}
	}).ajaxStop(function(evt, request, settings) {
			NProgress.done();
	});
});