<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="${path}/static/sea-modules/arale/calendar/0.9.0/calendar.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/common.css"/>
    <link href="${path}/static/css/bootstrap-all.css" rel="stylesheet" type="text/css"/>
    <link href="${path}/static/sea-modules/ligerui/1.2.0/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
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
                    { display: '月份', name: 'months', align: 'left'},
                    { display: '木筏ID', name: 'flagId', align: 'left'},
                    { display: '人数', name: 'personNum', align: 'left'}
                ],
                url: '${path}/maintain/flag/analysis',
                root: 'rows',
                record: 'total'
            });
            new Calendar({trigger: '#startTime', events: {
                'click [data-role=current-month]': function (ev) {
                    this.renderContainer('months');
                },
                'click [data-role=current-year]': function (ev) {
                    this.renderContainer('years');
                }
            }}).on('show selectYear',function () {
                        this.renderContainer('months');
                    }).on('selectMonth', function (date) {
                        this.hide();
                        this.output(date.format('YYYY-MM'));
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

    </script>
</head>
<body>
<div class="panel">
    <div class="div-search">
        <div class="title">标签统计
        </div>
        <form:form method="post" id="queryForm">
            <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                <span class="add-on">木筏ID</span>
                <input class="span2" name="raftId" id="raftId" type="text" placeholder="输入木筏ID">
            </div>
            <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                <span class="add-on">月份</span>
                <input class="span2" name="month" id="month" type="text" placeholder="输入月份">
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