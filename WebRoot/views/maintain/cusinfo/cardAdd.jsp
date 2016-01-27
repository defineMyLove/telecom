<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <title>个人资料</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="${path}/static/css/myapp.min.css" rel="stylesheet">
    <link href="${path}/static/sea-modules/arale/calendar/1.0.0/calendar.css" rel="stylesheet">
    <script type="text/javascript" src="${path}/static/sea-modules/sea.js"></script>
    <script type="text/javascript" src="${path}/static/sea-modules/seajs-config.js"></script>
    <script type="text/javascript">
        if ('${msg}') {
            top.common.tip.notify({title: '${msg}'});
        }
        seajs.use([ '$', 'app-util', 'json', 'validateUtil', 'avalon', "calendar", 'todc-bootstrap', 'lhgdialog', 'chosen'],
                function ($, appUtil, JSON, validateUtil, avalon, Calendar) {
                    $(function () {
                        $('.tag-select').chosen({
                            width: '90%',
                            allow_single_deselect: true
                        });
                        new Calendar({
                            trigger: '#expire_time',
                            range: ["YYYY-MM-DD", null]
                        });
                        //全局变量
                        window.$ = $;
                    /*    //表单验证
                        validateUtil.formValidate({
                            form: "showForm",
                            rules: {
                                "expire_time": {required: true}
                            },
                            submitHandler: function (form) {
                                form.submit();
                            }
                        });*/
                        //其他电话号码维护VM
                        var phontList =${phontListJson};
                        var otherPhoneVM = avalon.define({
                            $id: 'otherPhoneVM',
                            list: phontList.slice(0),
                            change: false,
                            changeUpdate: function () {
                                otherPhoneVM.change = true;
                                if (otherPhoneVM.list.size() == 0) {
                                    otherPhoneVM.addNew();
                                }
                            },
                            addNew: function () {
                                otherPhoneVM.list.unshift({
                                    vice_card_id: '',
                                    back_card_id: ''
                                });
                            },
                            save: function () {
                                var check = true;
                                var phoneArr = [];
                                for (var i = 0; i < otherPhoneVM.list.$model.length; i++) {
                                    var model = {};
                                    model.vice_card_id = otherPhoneVM.list.$model[i].vice_card_id;
                                    model.back_card_id = otherPhoneVM.list.$model[i].back_card_id;
                                    phone = $.trim(model.vice_card_id);
                                    if (phone == '' || !/^((13\d)|(15\d)|(18\d))\d{8}$/.test(phone)) {
                                        check = false;
                                        appUtil.messager.danger("请正确填写副卡手机号码！");
                                        break;
                                    }
                                    if ($.inArray(model, phoneArr) > -1) {
                                        check = false;
                                        appUtil.messager.danger("副卡重复！");
                                        break;
                                    }
                                    phoneArr.push(model);
                                }
                                if (!check) {
                                    return false;
                                }
                                //保存副卡值
                                $('#vice_card_str').val(JSON.stringify(phoneArr));
                                return true;
                            },
                            cancel: function () {
                                otherPhoneVM.list = phontList;
                                otherPhoneVM.change = false;
                            }
                        });
                        window.otherPhoneVM = otherPhoneVM;
                    });
                });

        function submitForm() {
            if (otherPhoneVM.save()) {
                $('#submitBtn')[0].click();
            }
        }
    </script>
    <style type="text/css">
        table td {
            vertical-align: middle !important;
        }
    </style>
</head>
<body>
<section id="content">
    <div class="container">

        <div class="row">
            <div class="col-sm-6">

                <!-- 其他联系电话 -->
                <div>
                    <div class="card">
                        <div class="card-header ch-alt">
                            <h2>主卡</h2>
                        </div>
                        <div class="card-body">
                            <form:form class="form-horizontal form-sm" cssStyle="margin:10px;" id="showForm"
                                       name="showForm"
                                       method="post"
                                       action="${path}/maintain/cusinfo/productUpdate">
                            <input name="id" type="hidden" value="${info.id}"/>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">选择套餐</label>

                                <div class="col-sm-9">
                                    <div class="fg-line">
                                        <select id="product_id" name="product_id" class="tag-select"
                                                data-placeholder="选择套餐" style="display: none;">
                                            <c:forEach items="${productList}" var="model">
                                                <option
                                                        <c:if test="${info.product_id==model.id}">selected="true"</c:if>
                                                        value="${model.id}">${model.name}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" value="${info.product_order_no}" class="form-control"
                                               name="product_order_no" maxlength="20">
                                        <input type="hidden" value="${info.product_type}" name="product_type">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">活动到期日期</label>

                                <div class="col-sm-9">
                                    <div class="fg-line">
                                        <input type="text" value="${info.expire_time}" class="form-control"
                                               name="expire_time" id="expire_time" maxlength="20">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">主卡</label>

                                <div class="col-sm-9">
                                    <div class="fg-line">
                                        <input type="text" class="form-control" value="${info.main_card_num}"
                                               name="main_card_num" maxlength="20">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">主卡背卡</label>

                                <div class="col-sm-9">
                                    <div class="fg-line">
                                        <input type="text" class="form-control" value="${info.back_card_num}"
                                               name="back_card_num" maxlength="20">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">宽带</label>

                                <div class="col-sm-9">
                                    <div class="fg-line">
                                        <input type="text" class="form-control" value="${info.broadband_id}"
                                               name="broadband_id" maxlength="20">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">猫</label>

                                <div class="col-sm-9">
                                    <div class="fg-line">
                                        <input type="text" class="form-control" value="${info.route_id}" name="route_id"
                                               maxlength="20">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">串号</label>

                                <div class="col-sm-9">
                                    <div class="fg-line">
                                        <input type="text" class="form-control" value="${info.serial_num}"
                                               name="serial_num" maxlength="20">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input id="vice_card_str" name="vice_card_str" type="hidden"/>
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
            </div>
            <div class="col-sm-6">
                <!-- 其他联系电话 -->
                <div ms-controller="otherPhoneVM">
                    <div class="card">
                        <div class="card-header ch-alt">
                            <h2>
                                副卡
                            </h2>
                            <ul class="actions">
                                <li><a href="javascript:;" title="修改" ms-click="changeUpdate"><i class="md md-edit"></i></a>
                                </li>
                                <li><a ms-visible="change" href="javascript:;" title="添加" ms-click="addNew"><i
                                        class="md md-add"></i></a></li>
                            </ul>
                        </div>
                        <div class="card-body">
                            <div class="alert alert-info" role="alert" style="display: none;"
                                 ms-visible="list.size()==0 && !change">
                                没有副卡，<a href="javascript:;" class="alert-link" ms-click="changeUpdate">添加副卡</a>。
                            </div>
                            <table class="table table-inner table-vmiddle" style="display: none;"
                                   ms-visible="change||list.size()">
                                <thead>
                                <tr>
                                    <th>副卡号</th>
                                    <th>副卡背卡号</th>
                                    <th style="width: 110px">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ms-repeat="list">
                                    <td>
                                        <span ms-visible="!change">{{el.vice_card_id}}</span>
                                        <input ms-visible="change" class="form-control"
                                               ms-duplex-number="el.vice_card_id">
                                    </td>
                                    <td>
                                        <span ms-visible="!change">{{el.back_card_id}}</span>
                                        <input ms-visible="change" class="form-control"
                                               ms-duplex-number="el.back_card_id">
                                    </td>
                                    <td>
                                        <ul class="actions">
                                            <li><a ms-visible="change" href="javascript:;" ms-click="$remove"><i
                                                    class="md md-delete"></i></a></li>
                                        </ul>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</section>
</body>
</html>