/**
 * 依赖：jquery,notify(js以及css)
 * 用法：
 * {
 * title:
 * text:
 *  type:success,error,info,normal
 * }
 * 1.默认显示 成功 弹出框
 * @param def
 */
define(function (require, exports, module) {
    var $ = require('$');
    require('jquery-plugin/pnotify/jquery.pnotify.min');
    /* var stack_topleft = {"dir1": "down", "dir2": "right", "push": "top"};
     var stack_bottomleft = {"dir1": "right", "dir2": "up", "push": "top"};
     var stack_custom = {"dir1": "right", "dir2": "down"};
     var stack_custom2 = {"dir1": "left", "dir2": "up", "push": "top"};
     var stack_bar_bottom = {"dir1": "up", "dir2": "right", "spacing1": 0, "spacing2": 0};*/
    var Notify=function(){
        this.def_conf = {
            addclass: 'pnotify-topmiddle',
            stack: {"dir1": "down", "dir2": "right", "push": "top", "spacing1": 0, "spacing2": 0},
            delay:2000   //1 秒
            //hide:false //不能关闭
        };

        this.notify=function(userDef){
            var title ="";
            if(userDef.hasOwnProperty('title')){  //默认的信息
                switch(userDef.title){
                    case 'add':
                        title='添加成功！';
                        break;
                    case 'update':
                        title='更新成功!';
                        break;
                    case 'del':
                        title='删除成功！';
                        break;
                    case 'delFail':
                        title='此模块下有子模块，不能删除';
                        userDef.type='error';
                    default:
                        title=userDef.title;
                }
                userDef.title = title;
            }
            var conf=jQuery.extend({},this.def_conf,userDef);
            if(!conf.hasOwnProperty('type')){
                conf.type='success';
            }
            $.pnotify(conf);
        }
    }
    module.exports=Notify;
});