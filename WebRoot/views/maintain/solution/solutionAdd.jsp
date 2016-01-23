<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/common.css"/>
    <script src="${path}/static/sea-modules/sea.js"></script>
    <script type="text/javascript" src="${path}/static/sea-modules/seajs-config.js"></script>
    <!-- 编辑器配置文件 -->
    <script type="text/javascript" src="${path}/js/ueditor1_4_3/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="${path}/js/ueditor1_4_3/ueditor.all.js"></script>
    <script type="text/javascript">
        if ('${msg}') {
            top.common.tip.notify({title: '${msg}'});
        }
        seajs.use([ '$', 'app-util', 'validateUtil', 'todc-bootstrap'], function ($, appUtil, validateUtil) {
            $(function () {
                //初始化文本编辑器
                var ue = UE.getEditor('ueditor');

                //全局变量
                window.$ = $;
                //表单验证
                validateUtil.formValidate({
                    form: "showForm",
                    rules: {
                        "title": {required: true, maxlength: 50}
                    },
                    submitHandler: function (form) {
                        if (!ue.hasContents()) {
                            appUtil.showMsg("alert", "请填写公告内容。", function () {
                                ue.focus();
                            });
                            return false;
                        }
                        $('#content').val(ue.getContent());
                        form.submit();
                    }
                });

                $("#add").click(
                        function () {
                            $("#showForm").attr("action", '${path}/maintain/solution/add').submit();
                        }
                );
            });
        });
        function submitForm() {
            document.getElementById("submitBtn").click();
        }
    </script>
</head>
<body>
<div class="panel">
    <fieldset class="fieldset">
        <form:form id="showForm" method="post" action="${path}/maintain/solution/add" enctype="multipart/form-data">
            <input id="id" name="id" value="${info.id}" type="hidden"/>
            <input id="id" name="fenlei_id" value="${fenlei_id}" type="hidden"/>
            <table width="90%" class="table-add">
                <tr>
                    <td width="20%" class="tabRight required">
                        标题
                    </td>
                    <td width="80%" style="text-align: left;">
                        <input type="text" class="text" name="name" id="name" value="${info.name}"/>
                    </td>

                </tr>
                <tr>
                    <td width="20%" class="tabRight required">
                        简短描述
                    </td>
                    <td width="80%" style="text-align: left;">
                        <input type="text" class="text" name="desc" id="desc" value="${info.desc}"/>
                    </td>

                </tr>
                <tr>
                    <td width="20%" class="tabRight required">
                        内容
                    </td>
                    <td width="30%" style="text-align: left;">
                        <input id="content" name="content" type="hidden"/>
                        <!-- 加载编辑器的容器 -->
                        <script id="ueditor" name="ueditor" type="text/plain" style="width:100%;height:500px;">
                                ${info.content}
                        </script>
                    </td>
                </tr>
                <tr>
                    <td width="20%" class="tabRight required">
                        图片
                    </td>
                    <td width="80%" style="text-align: left;">
                        <input type="file" class="text" name="file" id="file"/>
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