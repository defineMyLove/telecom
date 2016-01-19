<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${path }/static/css/common.css"/>
    <link href="${path}/static/sea-modules/ligerui/1.2.0//skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <script type='text/javascript' src='${path}/maintain/dictionary/dicMap' charset="UTF-8"
            language="javascript"></script>
    <script src="${path}/static/sea-modules/sea.js"></script>
    <script src="${path}/static/seajs-config.js"></script>
    <script type="text/javascript">
        var manager;
        seajs.use(['$', 'ligerui','jquery-plugin/json/jquery.json'], function ($,ligerui) {
            //显示提示信息
            if ('${info}') {
                top.common.tip.notify({title: '${info}'});
            }
            manager = jQuery("#domaingrid").ligerGrid({
                rownumbers: true,
                columns: [
                    { display: '标题', name: 'title', align: 'left'},
                    { display: '创建时间', name: 'createTime', align: 'linkman' },
                    { display: '级别', name: 'level', align: 'tel'},
                    { display: '公告编号', name: 'id', hide: 'true'}  //隐藏
                ],
                url: '${path}/maintain/notice/list',
                root: 'rows',
                record: 'total',
                onBeforeShowData: renderFormData,
                toolbar: { items: [
                    { id: 'add', text: '添加', click: itemclick, icon: 'add'},
                    { line: true },
                    { id: 'update', text: '修改', click: itemclick, icon: 'modify'},
                    { line: true },
                    { id: 'del', text: '删除', click: itemclick, icon: 'delete'},
                    { line: true },
                    { id: 'detail', text: '公告详情', click: itemclick, icon: 'communication'}
                ]
                }
            });
            //查询按钮动作绑定
            $('#submitBut').click(function () {
                submitLiger();
            });
            //全局变量
            window.$ = $;
        })
        /**  字典数据转换**/
        function renderFormData(data) {
            jQuery.each(data.rows, function () {
                this.level = dicMap['noticeLevel_'+this.level].dtName;
                this.createTime = top.common.getDateStr(this.createTime);
            });
        }
        /** 查询动作 **/
        function submitLiger() {
            var mainParam = $('#queryForm').serializeObject();
            mainParam.page = manager.options.page;
            mainParam.pageSize = manager.options.pageSize;
            mainParam.sortName = manager.options.sortName;
            mainParam.sortOrder = manager.options.sortOrder;
            manager.loadServerData(mainParam, true); //刷新
        }
        function itemclick(item) {
            if ('add' === item.id) {
                top.addTab('noticeadd', '公告添加', '${path}/toView?view=/maintain/notice/noticeAdd');
            } else if ('update' === item.id) {
                var row = manager.getSelectedRow();
                if (!row) {
                    $.ligerDialog.warn("请选择一条记录");
                    return;
                }
                top.addTab('noticeupdate', '公告修改', '${path}/maintain/notice/updateUI?noticeId=' + row.id);
            }   else if ('detail' === item.id) {
                var row = manager.getSelectedRow();
                if (!row) {
                    $.ligerDialog.warn("请选择一条记录");
                    return;
                }
                top.addTab('noticedetail', '公告详情', '${path}/maintain/notice/detail?noticeId=' + row.id);
            }
            else if ('del' === item.id) {
                var row = manager.getSelectedRow();
                if (!row) {
                    $.ligerDialog.warn("请选择一条记录");
                    return;
                }
                $.ligerDialog.confirm("确定删除此公告？", function (yes) {
                    if (yes) {
                        $.getJSON("${path}/maintain/notice/delete", {noticeId: row.id}, function (data) {
                            top.common.tip.notify({title: 'del'});
                            submitLiger();
                        });
                    }
                });
            }
        }
    </script>
</head>
<body>
<div class="panel">
    <div class="div-search">
        <div class="title">公告查询</div>
        <form:form method="post" action="${path }/maintain/notice/list" onsubmit="isClearOrg()" id="queryForm">
            <table border="0" cellpadding="2" cellspacing="1" width="100%" class="searchform">
                <tr>
                    <td width="12%" align="right">
                        标题
                    </td>
                    <td width="20%" align="left">
                        <input type="text" name="title" class="text"/>
                    </td>
                    <td width="12%" align="right">
                        级别
                    </td>
                    <td width="20%" align="left">
                        <dic:getDictionary var="dicList" groupCode="noticeLevel"/>
                        <select id="level" name="level" style="cursor:pointer;" class="text">
                            <option value="">--请选择--</option>
                            <c:forEach var="dic" items="${dicList}">
                                <option value="${dic.dtCode}">${dic.dtName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="36%" align="left" rowspan="3" valign="middle">
                        <input type="button" value="查 询" class="btn btn-primary" onclick="submitLiger()"/>
                    </td>
                </tr>
                <td align="right">
                    &nbsp;
                </td>
                <td align="left">
                    &nbsp;
                </td>
                </tr>
            </table>
            <!-- 查询结束 -->
        </form:form>
    </div>
    <br>
    <div style="PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px"
         id=domaingrid></div>
</div>
</body>
</html>