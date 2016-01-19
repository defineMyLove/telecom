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

        seajs.use(['$','ligerui'], function ($) {
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
                    { display: '姓名', name: 'user_name', align: 'left', frozen: true},
                    { display: '职务', name: 'work_name', align: 'left', frozen: true},
                    { display: '公司名称', name: 'company_nam', align: 'left',frozen: true},
                    { display: '公司地址', name: 'company_address', align: 'left',frozen: true},
                    { display: '邮编', name: 'company_youbian', align: 'left',frozen: true},
                    { display: '电子邮件', name: 'email', align: 'left'},
                    { display: '公司电话', name: 'company_tel', align: 'left'},
                    { display: '电子传真', name: 'company_chuanzhen', align: 'left'}
                ],
                url: '${path}/maintain/mesboard/list',
                root: 'rows',
                record: 'total',
                toolbar: { items: [
                    { id: 'del', text: '删除', click: itemclick, icon: 'delete'},
                    { line: true },
                    { id: 'detail', text: '详情', click: itemclick, icon: 'communication'}
                ]
                }
            });

        });

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
                window.top.openDialog("添加功能","${path}/maintain/mesboard/addUI",80,80, [
                    { text: '确定', onclick: function (item, dialog) {window.top.submitForm()},cls:'l-dialog-btn-highlight' },
                    { text: '取消', onclick: function (item, dialog) { window.top.closeDialog(); }}
                ]);
            } else if ('update' === item.id) {
                if (!rows) {
                    $.ligerDialog.warn("请选择一条记录");
                    return;
                }
                window.top.openDialog("添加功能","${path}/maintain/mesboard/addUI?id="+rows.id,80,80, [
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
                        $.getJSON("${path}/maintain/mesboard/del", {id: rows.id}, function (data) {
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
                window.top.openDialog("详情","${path}/maintain/mesboard/detail?id="+rows.id,80,80, [
                    { text: '取消', onclick: function (item, dialog) { window.top.closeDialog(); }}
                ]);
            }
        }
    </script>
</head>
<body>
<div class="panel">
    <div class="div-search">
        <div class="title">留言板查询
        </div>
        <form:form method="post" id="queryForm">
            <table border="0" cellpadding="2" cellspacing="1" width="100%" class="searchform">
                <tr>
                    <td width="12%" align="right">
                        标题
                    </td>
                    <td width="20%" align="left">
                        <input type="text" name="title"  class="text"/>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
            <!-- 查询结束 -->
            <table style="width: 98%;margin-buttom: 5px;">
                <tr>
                    <td align="center">
                        <input type="button" value="查 询" onclick="submitLiger()" class="btn btn-primary"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
    <div style="PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px"
         id=usergrid></div>
    <script type="text/javascript">

    </script>
</div>
</body>
</html>