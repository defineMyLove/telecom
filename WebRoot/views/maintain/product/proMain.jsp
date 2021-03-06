<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="${path}/static/sea-modules/ligerui/1.2.0/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/common.css"/>
    <script src="${path}/static/sea-modules/sea.js"></script>
    <script src="${path}/static/seajs-config.js"></script>
    <script type="text/javascript">

        seajs.use(['$', 'ligerui'], function ($, ligerui) {
            //添加属性
            $.fn.serializeObject = function () {
                var o = {};
                var a = this.serializeArray();
                $.each(a, function () {
                    if (o[this.name]) {
                        if (!o[this.name].push) {
                            o[this.name] = [o[this.name]];
                        }
                        o[this.name].push(this.value || '');
                    } else {
                        o[this.name] = this.value || '';
                    }
                });
                return o;
            };
            //全局变量
            window.$ = $;

            $("#usergrid").ligerGrid({
                rownumbers: true,
                enabledSort: false,
                height: '80%',
                onBeforeShowData: renderFormData,
                columns: [
                    { display: '图片', name: 'atta_path', align: 'left'},
                    { display: '标题', name: 'name', align: 'left'},
                    { display: '创建时间', name: 'create_time', align: 'left'},
                    { display: '内容', name: 'content', align: 'left'}
                ],
                parms:{
                    fenlei_id:'${fenlei_id}'
                },
                url: '${path}/maintain/product/list',
                root: 'rows',
                record: 'total',
                toolbar: { items: [
                    { id: 'add', text: '添加', click: itemclick, icon: 'add' },
                    { line: true },
                    { id: 'update', text: '修改', click: itemclick, icon: 'modify' },
                    { line: true },
                    { id: 'del', text: '删除', click: itemclick, icon: 'delete'},
                    { line: true },
                    { id: 'detail', text: '详情', click: itemclick, icon: 'communication'}
                ]
                }
            });

        });

        function toUserAdd(form) {
            form.action = "${path}/maintain/userGongan/addUI";
            form.submit();
        }
        function isClearOrg() {
            var value = $("#xzqhCode").val();
            if ($.trim(value) == "") {
                $("[name='xzqhCode']").val("");
            }
        }
        /** 查询动作 **/
        function submitLiger() {
            var manager = $("#usergrid").ligerGetGridManager();
            var mainParam = $('#queryForm').serializeObject();
            mainParam.page = manager.options.page;
            mainParam.fenlei_id='${fenlei_id}';
            mainParam.pageSize = manager.options.pageSize;
            mainParam.sortName = manager.options.sortName;
            mainParam.sortOrder = manager.options.sortOrder;
            manager.loadServerData(mainParam, clause); //刷新
        }
        function clause() {
            return true;
        }
        /**  字典数据转换**/
        function renderFormData(data) {
            jQuery.each(data.rows, function () {
                this.create_time = top.common.getDateStr(this.create_time);
            });
        }
        /**  按钮动作**/
        function itemclick(item) {
            var manager = $("#usergrid").ligerGetGridManager();
            var rows = manager.getSelectedRow();

            if ('add' === item.id) {
                window.top.openDialog("添加功能","${path}/maintain/product/addUI?fenlei_id=${fenlei_id}",80,80, [
                    { text: '确定', onclick: function (item, dialog) {window.top.submitForm()},cls:'l-dialog-btn-highlight' },
                    { text: '取消', onclick: function (item, dialog) { window.top.closeDialog(); }}
                ]);
            } else if ('update' === item.id) {
                if (!rows) {
                    $.ligerDialog.warn("请选择一条记录");
                    return;
                }
                window.top.openDialog("修改功能","${path}/maintain/product/addUI?fenlei_id=${fenlei_id}&id="+rows.id,80,80, [
                    { text: '确定', onclick: function (item, dialog) {window.top.submitForm()},cls:'l-dialog-btn-highlight' },
                    { text: '取消', onclick: function (item, dialog) { window.top.closeDialog(); }}
                ]);
                }else if ('del' === item.id) {
                var row = manager.getSelectedRow();
                if (!row) {
                    $.ligerDialog.warn("请选择一条记录");
                    return;
                }
                $.ligerDialog.confirm("确定删除此数据么？",function(yes){
                    if(yes){
                        $.getJSON("${path}/maintain/product/del", {id: rows.id}, function (data) {
                            top.common.tip.notify({title:'del'});
                            submitLiger();
                        });
                    }
                });
            } else if ('detail' === item.id) {
                if (!rows) {
                    $.ligerDialog.warn("请选择一条记录");
                    return;
                }
                window.top.openDialog("详情","${path}/maintain/yeji/detail?id="+rows.id,80,80, [
                    { text: '取消', onclick: function (item, dialog) { window.top.closeDialog(); }}
                ]);
            }
        }
    </script>
</head>
<body>
<div class="panel">
    <div style="PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px"
         id=usergrid></div>
    <script type="text/javascript">

    </script>
</div>
</body>
</html>