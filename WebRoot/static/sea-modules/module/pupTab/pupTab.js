/**
 *  用于弹出一个tab选择框
 *  api:
 *  {
 *      url:请求参数路径，返回json  格式为[{id:'',name:'',chird:[{id:'',name:''},...]},...]
 *      nameId:显示对象id
 *      valueId:显示对象值id
 *  }
 */
define(function (require, exports, module) {
    require('./pop.css');
    var $ = require('$');
    var Tabs = require('../../arale/switchable/1.0.1/tabs');
    var Popup = require('../../arale/popup/1.1.5/popup');
    var pupTab = function (param) {
        if (param.json) {
            this.json = param.json;
        } else {
            this.url = param.url;
        }
        this.nameId = param.nameId;
        this.valueId = param.valueId;
        this.customClick = param.customClick;
        this.clear=param.clear;
        this.tabDivEleId = 'ddctype';
        this.uitabDivId = this.tabDivEleId+'_uitab';
        this.tabClass = 'ui-tab-item';
        this.contentBoxId =this.tabDivEleId+ '_panel';
        this.activeTriggerClass = 'ui-tab-item-current';
        this.woEle = '<div  id="' + this.tabDivEleId + '"></div>';
        this.divEle = '<div  class="ui-tab" id="'+this.uitabDivId+'"><ul class="ui-switchable-nav zm_fenlei"></ul></div>';
        this.contentBoxEle = '<div id="' + this.contentBoxId + '" class="ui-switchable-content"></div>';
        if (typeof this.json === 'undefined') {
            var tabDivEle = this.tabDivEle;
            $.getJSON(url, function (json) {
                $.each(json, function (i, n) {
                    tabDivEleHtml += '<li ><a href="javascript:void(0)" name="' + this.id + '">' + this.name + '</a></li>';
                    contentBoxEleHtml += '<div style="display: none;"><dl class="fenleilist clearfix">';
                    $.each(this.chird, function () {
                        contentBoxEleHtml += '<dd><a href="javascript:void(0)" style="width:42px;" name=' + this.id + '>' + this.name + '</a></dd>';
                    });
                    contentBoxEleHtml += '</dl></div>';
                });
            });
            $(this.woEle).append($(this.divEle).find('ul').append(tabDivEleHtml).parent()).append($(this.contentBoxEle).append(contentBoxEleHtml)).insertAfter('#' + this.nameId);
        } else {
            $(this.woEle).insertAfter('#' + this.nameId);
            $('#'+this.tabDivEleId).append($(this.divEle)).append($(this.contentBoxEle));
            var json = this.json;
            var that =this;
            $.each(json, function (i) {
                var parent = this;
                var tabDivEleHtml = '<li ><a href="javascript:void(0)" name="' + this.id + '">' + this.name + '</a></li>';
                $('#'+that.uitabDivId).find('ul').append(tabDivEleHtml);
                var contentBoxEleHtmlId ='content_'+ that.contentBoxId+i;
                var contentBoxEleHtml = '<div style="display: none;" id="'+contentBoxEleHtmlId+'"><dl class="fenleilist clearfix"></dl></div>';
                $('#'+that.contentBoxId).append(contentBoxEleHtml)
                $.each(this.chird, function (j) {
                    var node = this;
                    var contentBoxEleHtmlTid  = 'type2_' + i+j;
                    var contentBoxEleHtmlT = '<dd><a href="javascript:void(0)"  id="' +contentBoxEleHtmlTid + '">' + this.name + '</a></dd>';
                    $('#'+contentBoxEleHtmlId).find('dl').append(contentBoxEleHtmlT);
                    var tt =  $('#'+contentBoxEleHtmlTid);
                    tt.data('node',node);
                    tt.data('parent',parent);
                });
            });
        }

        var tem =
            new Tabs({
                element: '.ui-tab',
                triggers: '.ui-switchable-nav li',
                panels: '.ui-switchable-content div',
                activeIndex: 0
            });
        var popup = new Popup({
            trigger: '#' + this.nameId,
            triggerType: 'click',
            align: {
                baseXY: [0, '100%+2']
            },
            element: '#' + this.tabDivEleId
        });
        var nameId = this.nameId;
        var valueId= this.valueId;
        if(this.customClick){
            var that = this;
            $('#'+this.contentBoxId + ' a').click(function(){
                that.customClick($(this).data('node'),$(this).data('parent'));
                popup.hide();
            });
        }else{
            $('#'+this.contentBoxId + ' a').click(function () {
                var $this = $(this);
                var id = $this.attr('name');
                var name = $this.text();
                $('#'+nameId).val(name);
                $('#'+valueId).val(id);
                popup.hide();
            });
        }
        if(this.clear){
            var clearHtmlId='clear_'+this.tabDivEleId;
            var clearHtml = '<a href="javascript:;" id="'+clearHtmlId+'" style="display:block;position: absolute;top:10px;right:10px;">清除</a>';
            $('#'+this.tabDivEleId).append(clearHtml);
            var that = this;
            $('#' +clearHtmlId).click(function(){
                    that.clear();
            });
        }
    };

    module.exports = pupTab;
});