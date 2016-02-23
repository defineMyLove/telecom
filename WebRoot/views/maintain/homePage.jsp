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
<link href="${path}/static/css/myapp.min.css" rel="stylesheet">
<script type="text/javascript" src="${path}/static/sea-modules/sea.js"></script>
<script type="text/javascript" src="${path}/static/sea-modules/seajs-config.js"></script>

<script type="text/javascript">
seajs.use([ '$', 'app-util', 'avalon', 'niceScroll', 'todc-bootstrap', 'chosen'],
        function ($, appUtil, avalon) {
            $(function () {
                $('.form-control-feedback').hide().click(function(){
                    $(this).hide().prev().val("");
                    delete param[$(this).hide().prev().attr('name')];
                    pageVM.pageQuery(1);
                });
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
                var dynInfoVM;  //套餐详情


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
                         $('#embed').remove();
                         $('body').append('<embed id="embed" src="${path}/static/sound/chat_request.wav" autostart="true"  style="visibility: hidden" loop="false">');
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
                        alert("开发中...");
                    },
                    pageQuery: function (pageNumber) {
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
                    //打开任务详情
                    openDetail: function (dyn) {
                        dynInfoVM.pageQuery(dyn);
                        $('#task-sidebar').addClass('toggled');
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
                        if (obj.selectedIndex) { //下拉框处理
                            var selectedVal = obj.value;
                            if (selectedVal == -1) {
                                delete param[obj.name];
                            } else {
                                param[obj.name] = selectedVal;
                            }
                        } else {
                            if ($.trim(obj.value) == "") {
                                delete param[obj.name];
                               $(obj).next("span.form-control-feedback").hide();  //隐藏输入框清楚按钮
                            } else {
                                $(obj).next("span.form-control-feedback").show();  //隐藏输入框清楚按钮
                                param[obj.name] = obj.value;
                            }
                        }
                        pageVM.pageQuery(1);
                    },
                    refresh:function(){
                        $('input.form-control').val('').next().hide();
                        $('select.form-control').val('-1').next().hide();
                        param = {};
                        pageVM.pageQuery(1);
                    }
                });

                dynInfoVM = avalon.define({
                    $id: 'dynInfoVM',
                    dyn: {},
                    viceList: [],
                    close: function () {
                        curDynamic = null;
                        $('#task-sidebar').removeClass('toggled');
                    },
                    pageQuery: function (dyn) {
                        dynInfoVM.dyn = {};
                        dynInfoVM.viceList = [];

                        var data = {
                            id: dyn.product_id
                        };
                        $.ajax({
                            async: true,
                            type: "POST",
                            url: "${path}/maintain/cusinfo/detail",
                            data: data,
                            success: function (response) {
                                dynInfoVM.dyn = response.info;
                                dynInfoVM.viceList = response.viceList;
                            }
                        });
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
<div class="card">
    <div class="card-header ch-alt">
        <%--<h2><small>右方对过滤条件进行一键删除</small></h2>--%>
        <ul class="actions">
            <li>
                <a ms-click="refresh">
                    <i class="md md-cached"></i>
                </a>
            </li>
        </ul>
    </div>
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
                <th>套餐类型</th>
                <th>套餐名称</th>
                <th>状态</th>
                <th style="min-width: 180px;">操作</th>
            </tr>
            <tr>
                <td>
                    <%-- <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                         <input class="span2" ms-on-input="filter" name="cus_name" td type="text"
                                placeholder="客户姓名">
                     </div>--%>
                    <div class="form-group">
                        <div class="fg-line">
                            <input type="text" ms-on-input="filter" name="cus_name" class="form-control"
                                   autocomplete="off"
                                   placeholder="客户姓名">
                            <span class="md md-close form-control-feedback"></span>
                        </div>
                    </div>
                </td>
                <td>
                    <%--  <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                          <input class="span2" ms-on-input="filter" name="cus_tel" td type="text"
                                 placeholder="客户电话">
                      </div>--%>
                    <div class="form-group">
                        <div class="fg-line">
                            <input type="text" ms-on-input="filter" name="cus_tel" class="form-control"
                                   autocomplete="off"
                                   placeholder="客户电话">
                            <span class="md md-close form-control-feedback"></span>
                        </div>
                    </div>
                </td>
                <td>
                    <%--   <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                           <input class="span2" ms-on-input="filter" name="cus_card_id" td type="text"
                                  placeholder="客户身份证号">
                       </div>--%>
                    <div class="form-group">
                        <div class="fg-line">
                            <input type="text" ms-on-input="filter" name="cus_card_id" class="form-control"
                                   autocomplete="off"
                                   placeholder="客户身份证号">
                            <span class="md md-close form-control-feedback"></span>
                        </div>
                    </div>
                </td>
                <td>
                    <%--    <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                            <input class="span2" ms-on-input="filter" name="cus_address" td type="text"
                                   placeholder="客户地址">
                        </div>--%>
                    <div class="form-group">
                        <div class="fg-line">
                            <input type="text" ms-on-input="filter" name="cus_address" class="form-control"
                                   autocomplete="off"
                                   placeholder="客户地址">
                            <span class="md md-close form-control-feedback"></span>
                        </div>
                    </div>
                </td>
                <td>
                    <%--   <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                           <input class="span2" ms-on-input="filter" name="sale_no" td type="text"
                                  placeholder="发展人编号">
                       </div>--%>
                    <div class="form-group">
                        <div class="fg-line">
                            <input type="text" ms-on-input="filter" name="sale_no" class="form-control"
                                   autocomplete="off"
                                   placeholder="发展人编号">
                            <span class="md md-close form-control-feedback"></span>
                        </div>
                    </div>
                </td>

                <td>
                    <%--  <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                          <input class="span2" ms-on-input="filter" name="sale_name" td type="text"
                                 placeholder="发展人姓名">
                      </div>--%>
                    <div class="form-group">
                        <div class="fg-line">
                            <input type="text" ms-on-input="filter" name="sale_name" class="form-control"
                                   autocomplete="off"
                                   placeholder="发展人姓名">
                            <span class="md md-close form-control-feedback"></span>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <div class="fg-line">
                            <input type="text" name="product_type" class="form-control" autocomplete="off"
                                   placeholder="套餐类型">
                            <span class="md md-close form-control-feedback"></span>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <div class="fg-line">
                            <input type="text" ms-on-input="filter" name="product_name" class="form-control"
                                   autocomplete="off"
                                   placeholder="套餐名称">
                            <span class="md md-close form-control-feedback"></span>
                        </div>
                    </div>
                </td>
                <td style="min-width: 50px;">
                    <%--   <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                           <select name="state"  ms-on-change="filter" class="form-control">
                               <option value="0">选择状态</option>
                               <option value="1">新入库</option>
                               <option value="2">已处理</option>
                               <option value="3">归档</option>
                           </select>
                       </div>--%>
                    <div class="form-group">
                        <select name="state" ms-on-change="filter" class="form-control">
                            <option value="-1">选择状态</option>
                            <option value="0">新入库</option>
                            <option value="1">已处理</option>
                            <option value="2">归档</option>
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
                <td>宽带</td>
                <td>{{el.product_name}}</td>
                <td>{{el.card_state|state}}</td>
                <td style="min-width: 180px;">
                    <ul class="actions">
                        <li ms-if="el.card_state==0"><a href="javascript:;" ms-click="addInfo(el)">完善</a></li>
                        <li ms-if="el.card_state==0"><a href="javascript:;" ms-click="banli(el)">办理</a></li>
                        <li ms-if="el.card_state==1"><a href="javascript:;" ms-click="guidang(el)">归档</a></li>
                        <a href="javascript:;" ms-click="openDetail(el)">详情</a>
                        <a href="javascript:;" ms-click="log(el)">日志</a>
                    </ul>
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
<!-- 任务详情 -->
<div id="task-sidebar" ms-controller="dynInfoVM">
    <div class="task-header">
        详细信息
        <ul class="actions pull-right">
            <li><a href="javascript:;" class="closeTask" ms-click="close"> <i class="md md-close"></i></a></li>
        </ul>
    </div>
    <div class="sidebar-inner">
        <div class="task-info" style="padding: 10px;">

            <div class="m-b-20">
                <p class="f-500 m-b-20 c-black">客户信息</p>
                <ul class="list-group">
                    <li class="list-group-item"><strong> 客户姓名:</strong><span class="badge">{{dyn.cus_name}}</span></li>
                    <li class="list-group-item"><strong> 客户电话:</strong><span class="badge">{{dyn.cus_tel}}</span></li>
                    <li class="list-group-item"><strong> 客户身份证号:</strong><span class="badge">{{dyn.cus_card_id}}</span>
                    </li>
                    <li class="list-group-item"><strong> 客户地址:</strong><span class="badge">{{dyn.cus_address}}</span>
                    </li>
                </ul>
            </div>

            <div class="m-b-20">
                <p class="f-500 m-b-20 c-black">发展人信息</p>
                <ul class="list-group">
                    <li class="list-group-item"><strong> 发展人编号:</strong><span class="badge">{{dyn.sale_id}}</span></li>
                    <li class="list-group-item"><strong> 发展人姓名:</strong><span class="badge">{{dyn.sale_name}}</span>
                    </li>
                </ul>
            </div>

            <div class="m-b-20">
                <p class="f-500 m-b-20 c-black">套餐信息</p>
                <ul class="list-group">
                    <li class="list-group-item"><strong> 套餐类型:</strong><span class="badge">宽带</span></li>
                    <li class="list-group-item"><strong> 套餐名称:</strong><span class="badge">{{dyn.product_name}}</span>
                    </li>
                    <li class="list-group-item"><strong> 活动到期日期:</strong><span class="badge">{{dyn.expire_time}}</span>
                    </li>
                    <li class="list-group-item"><strong> 主卡:</strong><span class="badge">{{dyn.main_card_num}}</span>
                    </li>
                    <li class="list-group-item"><strong> 主卡背卡:</strong><span class="badge">{{dyn.back_card_num}}</span>
                    </li>
                    <li class="list-group-item"><strong> 宽带:</strong><span class="badge">{{dyn.broadband_id}}</span>
                    </li>
                    <li class="list-group-item"><strong> 猫:</strong><span class="badge">{{dyn.route_id}}</span></li>
                    <li class="list-group-item"><strong> 串号:</strong><span class="badge">{{dyn.serial_num}}</span></li>
                </ul>
            </div>

            <div class="m-b-20" ms-visible="viceList.length > 0">
                <p class="f-500 m-b-20 c-black">副卡信息</p>
                <ul class="list-group">
                    <li class="list-group-item" ms-repeat="viceList">
                        <div><strong> 副卡号:</strong>{{el.vice_card_id}},<strong> 背卡号:</strong>{{el.vice_card_id}}</div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>