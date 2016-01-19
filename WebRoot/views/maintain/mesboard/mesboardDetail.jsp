<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/common.css"/>
</head>
<body>
<div class="panel">
    <fieldset class="fieldset">
            <table class="table-add">
                <tr>
                    <td>您的姓名:</td>
                    <td class="tabRight required">
                     ${info.user_name}
                    </td>
                    <td>您的职务:</td>
                    <td class="tabRight required">
                        ${info.work_name}
                    </td>
                </tr>
                <tr>
                    <td>公司名称:</td>
                    <td class="tabRight required" colspan="3">
                        ${info.company_nam}
                    </td>
                </tr>
                <tr>
                    <td>公司地址:</td>
                    <td class="tabRight" colspan="3">
                        ${info.company_address}
                    </td>
                </tr>
                <tr>
                    <td>公司邮编:</td>
                    <td class="tabRight required">
                        ${info.company_youbian}
                    </td>
                    <td>电子邮件:</td>
                    <td class="tabRight">
                        ${info.email}
                    </td>
                </tr>
                <tr>
                    <td>公司电话:</td>
                    <td class="tabRight required">
                        ${info.company_tel}
                    </td>
                    <td>电子传真:</td>
                    <td class="tabRight required">
                        ${info.company_chuanzhen}
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="text-align: center">您希望获得什么帮助</td>
                </tr>
                <tr>
                    <td colspan="4" class="tabRight required">
                        ${info.content}
                    </td>
                </tr>
            </table>

    </fieldset>
</div>
</body>
</html>