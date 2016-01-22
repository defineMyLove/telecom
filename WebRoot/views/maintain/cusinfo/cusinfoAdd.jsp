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
                    "cus_name": {maxlength: 10},
                    "cus_card_id": {isIDCard: true},
                    "cus_address": {maxlength: 200},
                    "cus_tel": {required: true,isTel:true}
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
        <form:form id="showForm" method="post" action="${path}/maintain/cusinfo/add">
            <input id="id" name="id" value="${info.id}" type="hidden"/>
            <table width="90%" class="table-add">
                <tr>
                    <td width="20%" class="tabRight">
                        姓名
                    </td>
                    <td width="80%" style="text-align: left;">
                        <input type="text" class="text" name="cus_name" id="cus_name" value="${info.cus_name}"/>
                    </td>

                </tr>
                <tr>
                    <td width="20%" class="tabRight required">
                        电话
                    </td>
                    <td width="30%" style="text-align: left;">
                        <input type="text" class="text" name="cus_tel" id="cus_tel" value="${info.cus_tel}"/>
                    </td>
                </tr>

                <tr>
                    <td width="20%" class="tabRight">
                        身份证号
                    </td>
                    <td width="30%" style="text-align: left;">
                        <input type="text" class="text" name="cus_card_id" id="cus_card_id" value="${info.cus_card_id}"/>
                    </td>
                </tr>

                <tr>
                    <td width="20%" class="tabRight">
                        地址
                    </td>
                    <td width="30%" style="text-align: left;">
                        <input type="text" class="text" name="cus_address" id="cus_address" value="${info.cus_address}"/>
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