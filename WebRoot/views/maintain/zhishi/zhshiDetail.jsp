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
        <form:form id="showForm" method="post" action="${path}/maintain/lunwen/add">
            <input id="id" name="id" value="${info.id}" type="hidden"/>
            <table width="90%" class="table-add">
                <tr>
                    <td width="20%" class="tabRight required">
                        标题
                    </td>
                    <td width="80%" style="text-align: left;">
                            ${info.title}
                    </td>

                </tr>
                <tr>
                    <td width="20%" class="tabRight required">
                       创建时间
                    </td>
                    <td width="30%" style="text-align: left;">
                            ${info.create_time_str}
                    </td>
                </tr>

                <tr>
                    <td width="20%" class="tabRight required">
                        内容
                    </td>
                    <td width="30%" style="text-align: left;">
                            ${info.content}
                    </td>
                </tr>

            </table>

        </form:form>
    </fieldset>
</div>
</body>
</html>