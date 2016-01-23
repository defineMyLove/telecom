<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<title>成员管理</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- app css -->
<link href="${path}/static/css/bootstrap-all.css" rel="stylesheet" type="text/css"/>
<link href="${path}/static/css/myapp.min.css" rel="stylesheet">
<script type="text/javascript" src="${path}/static/sea-modules/sea.js"></script>
<script type="text/javascript" src="${path}/static/sea-modules/seajs-config.js"></script>

<script type="text/javascript">
seajs.use([ '$', 'app-util', 'avalon', 'niceScroll', 'todc-bootstrap', 'chosen'],
        function ($, appUtil, avalon) {
            $(function () {
                $('#content .container').niceScroll({
                    cursorcolor: 'rgba(0,0,0,0.6)',
                    cursorborder: 0,
                    cursorborderradius: 0,
                    cursorwidth: '8px',
                    bouncescroll: true,
                    mousescrollstep: 100
                });

                var param = {};
                avalon.filters.state = function (isAdmin) {
                    //(0:新入库1:暂不处理2:完善数据,3:办理套餐4:归档)
                    if (isAdmin == 0) {
                        return '新入库';
                    } else if (isAdmin == 1) {
                        return '已办理';
                    } else if (isAdmin == 2) {
                        return '已归档';
                    }
                    return '';
                }

                var pageVM = avalon.define({
                    $id: 'pageVM',
                    list: [],
                    pageCount: [],
                    //分页参数
                    page: {
                        pageNumber: 1,
                        pageSize: 30,
                        totalRow: 0,
                        totalPage: 0
                    },
                    playSound: function (src) {
                        // $('#embed').remove();
                        // $('body').append('<embed id="embed" src="${path}/static/sound/chat_request.wav" autostart="true"  style="visibility: hidden" loop="false">');
                    },
                    addInfo: function (el) {
                        window.top.openDialog("完善信息", "${path}/maintain/cusinfo/addInfo?id=" + el.id, 40, 30, [
                            { text: '确定', onclick: function (item, dialog) {
                                window.top.submitForm()
                            }, cls: 'l-dialog-btn-highlight' },
                            { text: '取消', onclick: function (item, dialog) {
                                window.top.closeDialog();
                            }}
                        ]);
                    },
                    buchuli: function (el) {
                        appUtil.confirm("确认暂不处理客户（" + el.cus_name + "," + el.cus_tel + "）吗？", function () {
                            $.ajax({
                                async: true,
                                type: "POST",
                                url: "${path}/maintain/cusinfo/buchuli",
                                data: {id: el.id},
                                success: function (response) {
                                    if (response.result) {
                                        appUtil.messager.success(response.msg);
                                        pageVM.pageQuery(1);
                                    } else {
                                        appUtil.messager.danger(response.msg);
                                    }
                                }
                            });
                        });
                    },
                    banli: function (el) {
                        window.top.openDialog("办理套餐", "${path}/maintain/cusinfo/banliUI?id=" + el.product_id, 80, 80, [
                            { text: '确定', onclick: function (item, dialog) {
                                window.top.submitForm()
                            }, cls: 'l-dialog-btn-highlight' },
                            { text: '取消', onclick: function (item, dialog) {
                                window.top.closeDialog();
                            }}
                        ]);
                    },
                    guidang: function (el) {
                        appUtil.confirm("归档后不可更改，确认归档客户（" + el.cus_name + "," + el.cus_tel + "）吗？", function () {
                            $.ajax({
                                async: true,
                                type: "POST",
                                url: "${path}/maintain/cusinfo/guidang",
                                data: {id: el.product_id},
                                success: function (response) {
                                    if (response.result) {
                                        appUtil.messager.success(response.msg);
                                        pageVM.pageQuery(1);
                                    } else {
                                        appUtil.messager.danger(response.msg);
                                    }
                                }
                            });
                        });
                    },
                    chuli: function (el) {
                        appUtil.confirm("确认处理客户（" + el.cus_name + "," + el.cus_tel + "）吗？", function () {
                            $.ajax({
                                async: true,
                                type: "POST",
                                url: "${path}/maintain/cusinfo/chuli",
                                data: {id: el.id},
                                success: function (response) {
                                    if (response.result) {
                                        appUtil.messager.success(response.msg);
                                        pageVM.pageQuery(1);
                                    } else {
                                        appUtil.messager.danger(response.msg);
                                    }
                                }
                            });
                        });
                    },
                    log: function (el) {

                    },
                    pageQuery: function (pageNumber, param) {
                        if (pageNumber != 1 && (pageNumber > pageVM.page.totalPage || pageNumber < 1)) {
                            return;
                        }
                        var data = {
                            page: pageNumber,
                            pageSize: pageVM.page.pageSize
                        }
                        if (data != undefined && data != null) {
                            $.extend(data, param);
                        }
                        $.ajax({
                            async: true,
                            type: "POST",
                            url: "${path}/maintain/cusinfo/list",
                            data: data,
                            success: function (response) {
                                pageVM.page = {
                                    pageNumber: response.page,
                                    pageSize: response.pageSize,
                                    totalRow: response.total,
                                    totalPage: response.totalPage
                                };
                                pageVM.pageCount = [];
                                for (var i = 0; i < pageVM.page.totalPage; i++) {
                                    pageVM.pageCount.push("");
                                }
                                pageVM.list = response.rows;
                                pageVM.playSound();
                            }
                        });
                    },
                    remove: function (user, $remove) {
                        appUtil.confirm("确认移除该发展人（" + user.name + "）吗？", function () {
                            $.ajax({
                                async: true,
                                type: "POST",
                                url: "${path}/maintain/cusinfo/del",
                                data: {id: user.id},
                                success: function (response) {
                                    if (response.result) {
                                        appUtil.messager.success(response.msg);
                                        pageVM.pageQuery(1);
                                    } else {
                                        appUtil.messager.danger(response.msg);
                                    }
                                }
                            });
                        });
                    },
                    edit: function (user) {
                        window.top.openDialog("修改", "${path}/maintain/cusinfo/addUI?id=" + user.id, 80, 80, [
                            { text: '确定', onclick: function (item, dialog) {
                                window.top.submitForm()
                            }, cls: 'l-dialog-btn-highlight' },
                            { text: '取消', onclick: function (item, dialog) {
                                window.top.closeDialog();
                            }}
                        ]);
                    },
                    isAdd: false,
                    keyForAdd: '',
                    changePageSize: function ($event) {
                        pageVM.page.pageSize = parseInt(this.options[this.selectedIndex].value);
                        pageVM.pageQuery(1);
                    },
                    goPage: function ($event) {
                        pageVM.pageQuery(parseInt(this.options[this.selectedIndex].value));
                    },
                    filter: function (e) {
                        var obj = this;
                        if(obj.selectedIndex) { //下拉框处理
                            var selectedVal = obj.value;
                            if(selectedVal==0) {
                                delete param[obj.name];
                            }else{
                                param[obj.name] = selectedVal;
                            }
                        }else{
                            if ($.trim(obj.value) == "") {
                                delete param[obj.name];
                            } else {
                                param[obj.name] = obj.value;
                            }
                        }

                        pageVM.pageQuery(1, param);
                    }
                });
                window.pageVM = pageVM;
                pageVM.pageQuery(1);
                setInterval("pageVM.pageQuery(1)", 30 * 1000); //指定30秒刷新一次
            });
        });
</script>
<style type="text/css">
    table td {
        vertical-align: middle !important;
    }
</style>
</head>
<body>
<section id="content" ms-controller="pageVM" class="ms-controller">
    <div class="container">
        <div class="block-header">
            <h2>机会库</h2>
        </div>

        <div class="card">
            <%--  <div class="card-header ch-alt">
                  <a class="btn bgm-teal btn-float" href="javascript:;" title="添加" ms-click="showAdd(true)"><i
                          class="md md-add"></i></a>
              </div>--%>
            <div class="card-body card-padding">
                <table class="table table-vmiddle table-hover">
                    <thead>
                    <tr>
                        <th>客户姓名</th>
                        <th>客户电话</th>
                        <th>客户身份证号</th>
                        <th>客户地址</th>
                        <th>发展人编号</th>
                        <th>发展人姓名</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    <tr>
                        <td>
                            <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                                <input class="span2" ms-on-input="filter" name="cus_name" td type="text"
                                       placeholder="客户姓名">
                            </div>
                        </td>
                        <td>
                            <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                                <input class="span2" ms-on-input="filter" name="cus_tel" td type="text"
                                       placeholder="客户电话">
                            </div>
                        </td>
                        <td>
                            <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                                <input class="span2" ms-on-input="filter" name="cus_card_id" td type="text"
                                       placeholder="客户身份证号">
                            </div>
                        </td>
                        <td>
                            <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                                <input class="span2" ms-on-input="filter" name="cus_address" td type="text"
                                       placeholder="客户地址">
                            </div>
                        </td>
                        <td>
                            <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                                <input class="span2" ms-on-input="filter" name="sale_no" td type="text"
                                       placeholder="发展人编号">
                            </div>
                        </td>
                        <td>
                            <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                                <input class="span2" ms-on-input="filter" name="sale_name" td type="text"
                                       placeholder="发展人姓名">
                            </div>
                        </td>
                        <td>
                            <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                                <select name="state"  ms-on-change="filter" class="form-control">
                                    <option value="0">选择状态</option>
                                    <option value="1">新入库</option>
                                    <option value="2">已处理</option>
                                    <option value="3">归档</option>
                                </select>
                            </div>
                        </td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ms-repeat="list">
                        <td>{{el.cus_name}}</td>
                        <td>{{el.cus_tel}}</td>
                        <td>{{el.cus_card_id}}</td>
                        <td>{{el.cus_address}}</td>
                        <td>{{el.sale_id}}</td>
                        <td>{{el.sale_name}}</td>
                        <td>{{el.card_state|state}}</td>
                        <td>
                                <span ms-if="el.card_state==0">
                                <a class="btn btn-default" href="#" role="button" ms-click="addInfo(el)">完善信息</a>
                                <%--<button class="btn btn-danger" ms-click="buchuli(el)">暂不处理</button>--%>
                                <button class="btn btn-primary" ms-click="banli(el)">办理套餐</button>
                                </span>

                                  <span ms-if="el.card_state==1">
                                <button class="btn btn-default" ms-click="guidang(el)">归档</button>
                                      </span>

                            <span ms-if="el.card_state==4||el.card_state==3||el.card_state==2||el.card_state==1">
                                    <button class="btn btn-default" ms-click="log(el)">操作日志</button>
                        </span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="bootgrid-footer container-fluid">
                <div class="pager form-horizontal tablesorter-pager" data-column="0">
                    <button type="button" ms-class="disabled:page.pageNumber<2" class="btn first"
                            ms-click="pageQuery(1)">首页
                    </button>
                    <button type="button" ms-class="disabled:page.pageNumber<2" class="btn prev"
                            ms-click="pageQuery(page.pageNumber-1)">上一页
                    </button>
                    <span class="pagedisplay">{{page.pageNumber}} / {{page.totalPage}}</span>
                    <!-- this can be any element, including an input -->
                    <button type="button" class="btn next"
                            ms-class="disabled:page.pageNumber==page.totalPage||page.totalPage==0"
                            ms-click="pageQuery(page.pageNumber+1)">
                        下一页
                    </button>
                    <button type="button" class="btn last"
                            ms-class="disabled:page.pageNumber==page.totalPage||page.totalPage==0"
                            ms-click="pageQuery(page.totalPage)">末页
                    </button>
                    <select ms-on-change="changePageSize" class="pagesize input-medium" title="每页条数">
                        <option value="30" selected="selected">30</option>
                        <option value="50">50</option>
                        <option value="100">100</option>
                        <option value="500">500</option>
                        <option value="1000">1000</option>
                        <option value="2000">2000</option>
                    </select>
                    <select class="pagenum input-mini" ms-on-change="goPage" title="页码">
                        <option ms-repeat="pageCount">{{$index+1}}</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>