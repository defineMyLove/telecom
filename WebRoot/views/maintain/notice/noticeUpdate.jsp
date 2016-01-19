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
        seajs.use(['$', 'jquery-util'], function ($, jqueryUtil) {
            //全局变量
            window.$ = $;

            jqueryUtil.formValidate({
                form: "updateForm",
                rules: {
                    title: {required: true, maxlength: 100}
                },
                submitHandler: function (form) {
                    var isError = false;
                    $('input[name="file"]').each(
                            function () {
                                var temp = $(this).val().split("\\");
                                var attaName = temp[temp.length - 1];
                                if (attaName.length > 25) {
                                    isError = true;
                                    jqueryUtil.errorPlacement($('<label class="error" generated="true">附件文件名太长,必须小于25字符,请修改后再上传!</label>'), $(this));
                                    $(this).focus();
                                } else {
                                    jqueryUtil.success($(this), null);
                                }
                            });
                    if (isError) {
                        return false;
                    }
                    if (KE.isEmpty('content')) {
                        top.art.dialog.alert("请输入内容！");
                        return false;
                    } else {
                        KE.sync('content');
                        form.submit();
                    }
                }

            });
            addfile();
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
        function del(fileId) {//ajax删除附件
            var element = $('#' + fileId + '_Span');
            var text = $('[name=attaName]', element).text();
            if (confirm("确认删除" + text + "吗？")) {
                $.get("${path}/maintain/notice/delFile/" + fileId, function () {
                    element.remove();
                    if ($('#attaDiv>span').length === 0) {
                        $('#attaDiv').html('无');
                    }
                    ;
                });
            }
        }
        KE.show({
            id: 'content',
            width: '80%',
            height: '300px',
            imageUploadJson: '${path}/maintain/upload/image'
        });
    </script>
</head>
<body>

<div class="panel">
    <fieldset class="fieldset">
        <legend class="legend">通知公告修改</legend>
        <form:form id="updateForm" action="${path}/maintain/notice/update" method="post" enctype="multipart/form-data">
            <input type="hidden" value="${notice.id}" name="id"/>
            <table width="90%" class="table-add">
                <tr>
                    <td width="20%" class="tabRight">级别</td>
                    <td width="80%" style="text-align:left;">
                        <dic:getDictionary var="dictionary" groupCode="noticeLevel" dicCode="${notice.level}"/>
                            ${dictionary.dtName}
                    </td>
                </tr>
                <tr>
                    <td width="20%" class="tabRight required">标题</td>
                    <td width="80%" style="text-align:left;">
                        <input type="text" id="title" name="title" value="${notice.title}"
                               class="text"/><font color="red">*必填</font>
                    </td>
                </tr>
                <tr>
                    <td width="20%" class="tabRight required">内容</td>
                    <td width="80%" style="text-align:left;">
                        <textarea style="width:80%" id="content" name="content">
                                ${notice.content}
                        </textarea>
                    </td>
                </tr>
                <tr>
                    <td width="20%" class="tabRight">已添加附件：</td>
                    <td width="80%" style="text-align:left;">
                        <c:if test="${!empty publishInfoFiles }">
                            <div id="attaDiv">
                                <c:forEach items="${publishInfoFiles }" var="publishInfoFile">
									<span id="${publishInfoFile.noticeId }_Span">
										<a name="attaName"
                                           href="${path }/download/noticeFile/${publishInfoFile.id}">${publishInfoFile.attaName }</a>
										<a href="javascript:void(0);" onclick="del('${publishInfoFile.id}')"
                                           style="color: #FF6600;">删除</a>
										<br/>
									</span>
                                </c:forEach>
                            </div>
                        </c:if>
                        <c:if test="${empty publishInfoFiles }">
                            无
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td width="20%" class="tabRight">附件：</td>
                    <td width="80%" id="files" style="text-align:left;">
                        <input type="file" id="file" name="file" style="margin: 0px;padding:0px;"/>
                        <img title="添加" id="addfile" src="${path}/static/images/jia1.png"
                             style=" cursor:pointer ;margin: 0px;padding:0px;"/>
                        &nbsp;<font color="red">*文件大小限制在70M以内</font>
                        <br/>
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