<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="${path}/static/sea-modules/ligerui/1.2.0/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${path}/static/sea-modules/arale/calendar/0.9.0/calendar.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/common.css"/>
    <link href="${path}/static/css/bootstrap-all.css" rel="stylesheet" type="text/css"/>
    <script src="${path}/static/sea-modules/sea.js"></script>
    <script src="${path}/static/seajs-config.js"></script>
    <script type="text/javascript">

        seajs.use(['$', 'ligerui', 'calendar'], function ($, ligerui, Calendar) {
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
                    { display: '木筏ID', name: 'RaltId', align: 'left'},
                    { display: '发排时间', name: 'startTimeStr', align: 'left'},
                    { display: '结束时间', name: 'endTimeStr', align: 'left'},
                    { display: '人数', name: 'personCount', align: 'left'},
                    { display: '时长', name: 'stanTime', align: 'left'},
                    { display: '状态', name: 'state', align: 'left'}
                ],
                url: '${path}/maintain/flag/query',
                root: 'rows',
                record: 'total'
            });

            new Calendar({trigger: '#startTime'});
            new Calendar({trigger: '#endTime'});
        });


        function isClearOrg() {
            var value = $("#xzqhCode").val();
            if ($.trim(value) == "") {
                $("[name='xzqhCode']").val("");
            }
        }


        var dateLength;
        /** 查询动作 **/
        function submitLiger() {
            //判断时长
            if (isNaN($('#dateLength').val())) {
                alert("时长必须为数字");
                return;
            } else {
                dateLength = parseInt($('#dateLength').val());
            }
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
                var dateCha = top.common.getDateDiff(this.startTime, this.endTime, "minute");
                this.stanTime = dateCha;
                if (dateLength) {
                    if (dateCha > dateLength) {
                        this.stanTime = "<font color='blue'>" + dateCha + "</font>";
                        this.state = "<font color='blue'>超时</font>";
                    } else if (dateCha < dateLength) {
                        this.stanTime = "<font color='red'>" + dateCha + "</font>";
                        this.state = "<font color='red'>提前</font>";
                    } else {
                        this.state = "正常";
                    }
                }
            });
        }
        /**  按钮动作**/
        function itemclick(item) {
            var manager = $("#usergrid").ligerGetGridManager();
            var rows = manager.getSelectedRow();

            if ('add' === item.id) {
                window.top.openDialog("添加功能", "${path}/maintain/flag/addUI", 80, 80, [
                    { text: '确定', onclick: function (item, dialog) {
                        window.top.submitForm()
                    }, cls: 'l-dialog-btn-highlight' },
                    { text: '取消', onclick: function (item, dialog) {
                        window.top.closeDialog();
                    }}
                ]);
            } else if ('update' === item.id) {
                if (!rows) {
                    $.ligerDialog.warn("请选择一条记录");
                    return;
                }
                window.top.openDialog("添加功能", "${path}/maintain/flag/addUI?id=" + rows.id, 80, 80, [
                    { text: '确定', onclick: function (item, dialog) {
                        window.top.submitForm()
                    }, cls: 'l-dialog-btn-highlight' },
                    { text: '取消', onclick: function (item, dialog) {
                        window.top.closeDialog();
                    }}
                ]);
            } else if ('del' === item.id) {
                var row = manager.getSelectedRow();
                if (!row) {
                    $.ligerDialog.warn("请选择一条记录");
                    return;
                }
                $.ligerDialog.confirm("确定删除此数据么？", function (yes) {
                    if (yes) {
                        $.getJSON("${path}/maintain/flag/del", {id: rows.id}, function (data) {
                            top.common.tip.notify({title: 'del'});
                            submitLiger();
                        });
                    }
                });
            } else if ('detail' === item.id) {
                if (!rows) {
                    $.ligerDialog.warn("请选择一条记录");
                    return;
                }
                window.top.openDialog("详情", "${path}/maintain/flag/detail?id=" + rows.id, 80, 80, [
                    { text: '取消', onclick: function (item, dialog) {
                        window.top.closeDialog();
                    }}
                ]);
            }
        }
    </script>
</head>
<body>
<div class="panel">
    <div class="div-search">
        <div class="title">标签查询
        </div>
        <form:form method="post" id="queryForm">
            <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                <span class="add-on">木筏ID</span>
                <input class="span2" name="raft_id" id="raft_id" type="text" placeholder="输入木筏ID">
            </div>
            <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                <span class="add-on">时长</span>
                <input class="span2" name="dateLength" id="dateLength" type="text" placeholder="输入时长">
                <span class="add-on">分钟</span>
            </div>
            <div class="input-prepend input-append" style="display: inline-block">
                <span class="add-on">时间</span>
                <input class="span2" name="startTime" id="startTime" type="text" placeholder="发排时间">
                <input class="span2" name="endTime" id="endTime" type="text" placeholder="结束时间">
            </div>
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