<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/common.css"/>
    <script src="${path}/static/sea-modules/sea.js"></script>
    <script src="${path}/static/seajs-config.js"></script>
    <script type="text/javascript">
        if ('${msg}') {
            top.common.tip.notify({title: '${msg}'});
        }
        seajs.use(['$', 'jquery-util'], function ($, jqueryUtil) {
            //全局变量
            window.$ = $;
            //表单验证
            jqueryUtil.formValidate({
                form: "showForm",
                rules: {
                    "flag_id": {required: true, maxlength: 10},
                    "raft_id": {required: true, maxlength: 10}
                },
                submitHandler: function (form) {
                    form.submit();
                }
            });

        });

        function submitForm() {
            $('#submitBtn')[0].click();
        }
    </script>
</head>
<body>
<div class="panel">
    <fieldset class="fieldset">
        <form:form id="showForm" method="post" action="${path}/maintain/flag/add">
            <input id="id" name="id" value="${info.id}" type="hidden"/>
            <table width="90%" class="table-add">
                <tr>
                    <td width="20%" class="tabRight required">
                        标签ID
                    </td>
                    <td width="80%" style="text-align: left;">
                        <input type="text" class="text" name="flag_id" id="flag_id" value="${info.flag_id}"/>
                    </td>

                </tr>
                <tr>
                    <td width="20%" class="tabRight required">
                        木筏ID
                    </td>
                    <td width="30%" style="text-align: left;">
                        <input type="text" class="text" name="raft_id" id="raft_id" value="${info.raft_id}"/>
                    </td>
                </tr>
            </table>
            <table style="width: 98%;margin-buttom: 5px;">
                <tr>
                    <td align="center">
                        <input type="submit" id="submitBtn" value="保 存" style="display:none;"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </fieldset>
</div>
</body>
</html>