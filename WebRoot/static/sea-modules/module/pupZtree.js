define(function (require, exports, module) {
    var $ = require('$');
    var Popup = require('popup');
    require('ztree');
    var pupZtree=function(param){
        this.ztreeId=param.ztreeId;
        this.ztreeUI={};
        this.setting = param.setting;
        this.ztreeObj={};
        this.triggerId = param.triggerId;
        this.popElementId='ztreeDiv';
        //初始化
        this.ztreeUI=$("#"+this.ztreeId);
        this.ztreeUI.wrap(
            ['<div id="',
                this.popElementId,
                '" style="display:none;border: 1px solid #C3C1C1;background-color: #F7F7F7;"',
                'width="',$('#' + this.triggerId).width(),'px"></div>'
            ].join("")
        );
        //暴露ztree对象接口
        this.ztreeObj = $.fn.zTree.init(this.ztreeUI, this.setting);
        //暴露Popup对象接口
        this.popupObj = new Popup({
            trigger: '#' + this.triggerId,
            triggerType: 'click',
            align: {
                baseXY: [0, '100%+2']
            },
            element: '#'+this.popElementId
        });
    };

    module.exports = pupZtree;
});
