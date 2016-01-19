<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="${path}/static/sea-modules/ligerui/1.2.0/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${path }/static/css/common.css"/>
    <script src="${path}/static/sea-modules/sea.js"></script>
    <script src="${path}/static/seajs-config.js"></script>
    <script type="text/javascript">
        var isDel = "${isDel}";
        var isLoadTree = "${isLoadTree}";
        if (isDel || isLoadTree) {
            var zTree = parent.zTree;
            zTree.reAsyncChildNodes(null, "refresh", null);
            zTree.expandAll(true);
        }
        seajs.use(['$', 'jquery-util', 'ligerui', 'ztree'], function ($, jqueryUtil, ligerui, ztree) {
            //显示提示信息
            if ('${message}') {
                top.common.tip.notify({title: '${message}'});
            }

            $('#tabDiv').ligerTab();
            $('#tabDiv').show();//优化效果
            //修改表单验证
            jqueryUtil.formValidate({
                form: "updateForm",
                rules: {
                    name: {required: true, maxlength: 40},
                    level: {required: true}
                },
                submitHandler: function (form) {
                    form.submit();
                }
            });
            //添加表单验证
            jqueryUtil.formValidate({
                form: "form_add",
                rules: {
                    id: {required: true, number: true, maxlength: 10,remote:'${path}/maintain/productclassify/checkId'},
                    name: {required: true, maxlength: 40},
                    level: {required: true}
                },
                submitHandler: function (form) {
                    form.submit();
                }
            });

            //删除动作
            $('#deleteKey').click(function () {
                var id = $("#id").val();
                if (confirm("是否确认删除编号为" + id + "的模块?")) {
                    window.location.href = '${path}/maintain/productclassify/del/' + id;
                }
            });
        })
    </script>
    </head>
<body>
<div class="panel">
    <fieldset class="fieldset">
        <legend class="legend">${product.name}</legend>
        <div id="tabDiv" style="display: none;">
            <div id="tabs-update" title="修改产品分类">
                <form:form action="${path}/maintain/productclassify/update" method="post" modelAttribute="product" id="updateForm">
                    <form:hidden path="id"/>
                    <form:hidden path="up_id"/>
                    <table class="table-add">
                        <tr>
                            <td class="tabRight required" style="width: 20%;">
                                名称
                            </td>
                            <td style="text-align: left;width:80%">
                                <form:input path="name" class="text"/>
                            </td>
                        </tr>
                    </table>
                    <table style="width:98%;margin-top: 5px;">
                        <tr>
                            <td align="center">
                                <input class="btn btn-primary" type="submit" value="保 存"/>
                                <input id="deleteKey" type="button" class="btn btn-primary" value="删 除">
                            </td>
                        </tr>
                    </table>
                </form:form>
            </div>
            <div id="tabs-addChild" title="添加子产品分类">
                <form id="form_add" action="${path}/maintain/productclassify/add"
                      method="post">
                    <table class="table-add">
                        <tr>
                            <td class="tabRight" style="width:20%">
                                上级菜单名称
                            </td>
                            <td style="text-align: left;width:80%">
                                <strong>${product.name } </strong>
                                <input type="hidden" id="up_id" name="up_id"
                                       value="${product.id }"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tabRight required" style="width:20%">
                                名称
                            </td>
                            <td style="text-align: left;width:80%">
                                <input type="text" class="text" name="name"/>
                            </td>
                        </tr>
                    </table>
                    <table style="width:98%;margin-top: 5px;">
                        <tr>
                            <td align="center">
                                <input type="submit" value="保 存" class="btn btn-primary"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </fieldset>
</div>
</body>
</html>