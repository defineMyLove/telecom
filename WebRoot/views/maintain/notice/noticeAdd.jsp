<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>公告信息添加页面</title>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/common.css"/>
    <link href="${path}/static/sea-modules/ligerui/1.2.0//skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <script src="${path}/static/sea-modules/sea.js"></script>
    <script src="${path}/static/seajs-config.js"></script>
    <script charset="utf-8" src="${path}/static/js/kindeditor/kindeditor-min.js"></script>
    <script type="text/javascript">
        if('${info}'){
            top.common.tip.notify({title:'${info}'});
        }
        seajs.use(['$','jquery-util','ligerui'], function ($, jqueryUtil) {
            //全局变量
            window.$=$;

            jqueryUtil.formValidate({
                form: "addForm",
                rules: {
                    level: {required: true},
                    title: {required: true, maxlength: 100}
                },
                submitHandler: function (form) {
                    var isError = false;
                    $('input[name="file"]').each(
                            function () {
                                var temp = $(this).val().split("\\");
                                var fileName = temp[temp.length - 1];
                                if (fileName.length > 25) {
                                    isError = true;
                                    jqueryUtil.errorPlacement($('<label class="error" generated="true">案件材料文件名太长,必须小于25字符,请修改后再上传!</label>'), $(this));
                                    $(this).focus();
                                } else {
                                    jqueryUtil.success($(this), null);
                                }
                            });

                    if (KE.isEmpty('content')) {
                        $.ligerDialog.warn("请输入内容！");
                        return false;
                    }
                    if (isError) {
                        return false;
                    }
                    KE.sync('content');
                    form.submit();
                }
            });
            addfile();

        });
        KE.show({
            id: 'content',
            width: '80%',
            height: '300px',
            imageUploadJson: '${path}/maintain/upload/image'
        });
        function addfile() {
            var files = $("#files");
            var deletefile = $("#deletefile");
            var context = "<div  style=\"cursor:pointer;\"><input type=\"file\" id=\"file\" name=\"file\">&nbsp;<span id=\"deletefile\"><img title=\"删除\" src=\"${path}/static/images/jian1.png\" style=\" cursor:pointer ;margin: 0px;padding:0px;\"></span></div>";
            $("#addfile").click(function () {
                files.append(context);
            });
            $("#deletefile").live("click", function () {
                $(this).parent("div").remove();
            });
            return false;
        }
    </script>
</head>
<div class="panel">
    <fieldset class="fieldset">
        <legend class="legend">通知公告添加</legend>
        <form:form id="addForm" action="${path}/maintain/notice/add" method="post" enctype="multipart/form-data">
            <table width="90%" class="table-add">
                <tr>
                    <td width="20%" class="tabRight required">级别</td>
                    <td width="80%" style="text-align:left;">
                        <dic:getDictionary var="dicList" groupCode="noticeLevel"/>
                        <select id="level" name="level" style="cursor:pointer;" class="text">
                            <option value="">--请选择--</option>
                            <c:forEach var="dic" items="${dicList}">
                                <option value="${dic.dtCode}">${dic.dtName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="20%" class="tabRight required">标题</td>
                    <td width="80%" style="text-align:left;">
                        <input type="text" id="title" name="title" class="text"/>
                    </td>
                </tr>
                <tr>
                    <td width="20%" class="tabRight required">内容</td>
                    <td width="80%" style="text-align:left;">
                        <textarea style="width:80%" id="content" name="content"></textarea>
                    </td>
                </tr>
                <tr>
                    <td width="20%" class="tabRight">附件</td>
                    <td width="80%" id="files" style="text-align:left;">
                        <input type="file" id="file" name="file" style="margin: 0px;padding:0px;"/>
                        <img title="添加" id="addfile" src="${path}/static/images/jia1.png"
                             style=" cursor:pointer ;margin: 0px;padding:0px;">
                        <br>
                    </td>
                </tr>
            </table>
            <table style="width:98%;margin-top: 5px;">
                <tr>
                    <td align="center">
                        <input type="submit" value="保&nbsp;存" class="btn btn-primary"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </fieldset>
</div>
</body>
</html>