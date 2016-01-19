<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/common.css"/>
    <script src="${path}/static/sea-modules/sea.js"></script>
    <script src="${path}/static/seajs-config.js"></script>
    <script charset="utf-8" src="${path}/static/js/kindeditor/kindeditor-min.js"></script>
    <script type="text/javascript">
        if('${msg}'){
            top.common.tip.notify({title:'${msg}'});
        }
        seajs.use(['$', 'jquery-util', 'ligerui'], function ($, jqueryUtil) {
            //全局变量
            window.$ = $;
            addfile() ;
            //表单验证
            jqueryUtil.formValidate({
                form: "showForm",
                rules: {
                    "name": {required: true,maxlength:50},
                    "des": {required: true}
                },
                submitHandler: function (form) {
                    form.submit();
                }
            });

            $("#add").click(
                    function () {
                        $("#showForm").attr("action", '${path}/maintain/product/add').submit();
                    }
            );
        });
        function submitForm(){
            document.getElementById("submitBtn").click();
        }
        function addfile() {
            var files = $("#files") ;

            var deletefile =  $("#deletefile") ;
            var context = "<div  style=\"cursor:pointer;\"><input type=\"file\" id=\"file\" name=\"file\">&nbsp;<span id=\"deletefile\"><img title=\"删除\" src=\"${path}/static/images/jian1.png\" style=\" cursor:pointer ;margin: 0px;padding:0px;\"></span></div>" ;
            $("#addfile").click(function() {
               /* if(files.find("input:file,.now_file").length>4){
                    alert("最多上传5个附件");
                    return false;
                }*/
                files.append(context) ;
            }) ;
            $("#deletefile").live("click",function() {
                $(this).parent("div").remove() ;
            }) ;
            return false;
        }

        function deleteFile(dom,id){
            $.ligerDialog.confirm("确定删除此产品图片么？",function(yes){
                if(yes){
                    $.getJSON("${path}/maintain/product/delFile",{id:id},function(){
                        $(dom).parent().remove();
                    });
                }
            });
        }
        KE.show({
            id: 'content',
            width: '80%',
            height: '300px',
            imageUploadJson:'${path}/maintain/upload/image'
        });
    </script>
    <style type="text/css">
        .now_file{
            border:1px solid #f78015;
            padding:5px;
            display: inline-block;
            margin: 5px;
        }
    </style>
</head>
<body>
<div class="panel">
    <fieldset class="fieldset">
        <form:form id="showForm" method="post" action="${path}/maintain/product/add" enctype="multipart/form-data">
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
                        简介
                    </td>
                    <td width="30%" style="text-align: left;">
                            <input type="text" class="text" name="des" id="des" value="${info.des}"/>
                    </td>
                </tr>
                <tr>
                    <td width="20%" class="tabRight required">
                        内容
                    </td>
                    <td width="30%" style="text-align: left;">
                        <textarea rows="20" cols="10" name="content" id="content">${info.content}</textarea>
                    </td>
                </tr>
                <tr>
                    <td width="20%" class="tabRight required">
                        附件
                    </td>
                    <td width="30%" style="text-align: left;">
                        <input type="file" id="atta" name="atta" style="margin: 0px;padding:0px;"/>
                        <br>
                    </td>
                </tr>

                <%--<tr>
                    <td width="20%" class="tabRight required">
                       附件
                    </td>
                    <td width="30%" style="text-align: left;">
                        <input type="file" id="atta" name="atta" style="margin: 0px;padding:0px;"/>
                        <br>
                    </td>
                </tr>--%>
                <tr>
                    <td width="20%" class="tabRight required">
                        图片
                    </td>
                    <td id="files"  width="30%" style="text-align: left;">
                        <div style="margin-bottom: 10px;">
                            <c:forEach var="pic" items="${fileList}">
                                <div class="now_file">
                                    ${pic.name}
                                    <a href="javascript:void(0);" onclick="deleteFile(this,'${pic.id}')">删除</a>
                                </div>
                            </c:forEach>
                        </div>

                        <input type="file" id="file" name="file" style="margin: 0px;padding:0px;"/>
                        <img title="添加"  id="addfile" src="${path}/static/images/jia1.png" style=" cursor:pointer ;margin: 0px;padding:0px;">
                        <br>

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