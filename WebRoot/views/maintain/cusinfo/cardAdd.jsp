<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="${path}/static/css/myapp.min.css" rel="stylesheet">
    <script type="text/javascript" src="${path}/static/sea-modules/sea.js"></script>
    <script type="text/javascript" src="${path}/static/sea-modules/seajs-config.js"></script>

    <script type="text/javascript">
        if ('${msg}') {
            top.common.tip.notify({title: '${msg}'});
        }
        seajs.use([ '$', 'app-util', 'avalon', 'niceScroll', 'todc-bootstrap', 'chosen'],
                function ($, appUtil, avalon) {
                    //全局变量
                    window.$ = $;
                    //表单验证
                    /*jqueryUtil.formValidate({
                        form: "showForm",
                        rules: {
                            "cus_name": {maxlength: 10},
                            "cus_card_id": {isIDCard: true},
                            "cus_address": {maxlength: 200},
                            "cus_tel": {required: true, isTel: true}
                        },
                        submitHandler: function (form) {
                            form.submit();
                        }
                    });
*/
                });

        function submitForm() {
            $('#submitBtn')[0].click();
        }
    </script>
</head>
<body>
<div class="card">
    <div class="card-header ch-alt">
        <h2>机会库</h2>
    </div>
    <div class="card-body">
        <form:form id="showForm" method="post" action="${path}/maintain/cusinfo/add">
            <input id="id" name="id" value="${info.id}" type="hidden"/>

            <div class="form-group">
                <label class="col-sm-3 control-label">选择套餐</label>

                <div class="col-sm-9">
                    <div class="fg-line">
                        <input type="text" class="form-control" name="familyName" maxlength="20">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">活动到期日期</label>

                <div class="col-sm-9">
                    <div class="fg-line">
                        <input type="text" class="form-control" name="familyName" maxlength="20">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">主卡</label>

                <div class="col-sm-9">
                    <div class="fg-line">
                        <input type="text" class="form-control" name="familyName" maxlength="20">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">主卡背卡</label>

                <div class="col-sm-9">
                    <div class="fg-line">
                        <input type="text" class="form-control" name="familyName" maxlength="20">
                    </div>
                </div>
            </div>

            <table style="width: 98%;margin-buttom: 5px;">
                <tr>
                    <td align="center">
                        <input type="submit" id="submitBtn" value="保 存" style="display:none;"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
</body>
</html>