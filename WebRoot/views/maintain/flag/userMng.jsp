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
    <!-- app css -->
    <link href="${path}/static/css/bootstrap-all.css" rel="stylesheet" type="text/css"/>
    <link href="${path}/static/css/myapp.min.css" rel="stylesheet">
    <script type="text/javascript" src="${path}/static/sea-modules/sea.js"></script>
    <script type="text/javascript" src="${path}/static/sea-modules/seajs-config.js"></script>

    <script type="text/javascript">
        seajs.use([ '$', 'app-util', 'avalon', 'todc-bootstrap', 'chosen'],
                function ($, appUtil, avalon) {
                    $(function () {
                        var param = {};
                        avalon.filters.isAdmin = function (isAdmin) {
                            if (isAdmin == 0) {
                                return '普通成员';
                            } else if (isAdmin == 1) {
                                return '管理员';
                            } else if (isAdmin == 2) {
                                return '任务调度员';
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
                                pageSize: 10,
                                totalRow: 0,
                                totalPage: 0
                            },
                            pageQuery: function (pageNumber,param) {
                                if (pageNumber != 1 && (pageNumber > pageVM.page.totalPage || pageNumber < 1)) {
                                    return;
                                }
                                var data ={
                                            pageNumber:pageNumber,
                                            pageSize:pageVM.page.pageSize
                                        }
                                if(data != undefined && data != null) {
                                    $.extend(data, param);
                                }
                                $.ajax({
                                    async: true,
                                    type: "POST",
                                    url: "${path}/maintain/flag/list",
                                    data: pageVM.data,
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
                                    }
                                });
                            },
                            remove: function (user, $remove) {
                                appUtil.confirm("确认从公司里移除该成员（" + user.userName + "）吗？", function () {
                                    $.ajax({
                                        async: true,
                                        type: "POST",
                                        url: "${base}my/comp/removeUser",
                                        data: {userId: user.userId},
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
                            isAdd: false,
                            keyForAdd: '',
                            showAdd: function (show) {
                                pageVM.isAdd = show;
                                pageVM.keyForAdd = '';
                            },
                            keyup: function ($event) {
                                if ($event.keyCode == 13) {
                                    pageVM.addUser();
                                }
                            },
                            filter: function(e) {
                                var obj = this;
                                if($.trim(obj.value)=="") {
                                    delete param[obj.name];
                                }else{
                                    param[obj.name] = obj.value;
                                }
                                pageVM.pageQuery(1, param);
                            },
                            addUser: function () {
                                var key = $.trim(pageVM.keyForAdd);
                                if (key == '') {
                                    appUtil.messager.danger("请输入诺部落号或者手机号。");
                                    return;
                                }
                                var btn = $("#addBtn").button('loading');
                                $.ajax({
                                    async: true,
                                    type: "POST",
                                    url: "${base}my/comp/addUser",
                                    data: {key: key},
                                    success: function (response) {
                                        btn.button('reset');
                                        if (response.result) {
                                            appUtil.messager.success(response.msg);
                                            pageVM.keyForAdd = '';
                                            pageVM.pageQuery(1);
                                        } else {
                                            appUtil.messager.danger(response.msg);
                                        }
                                    }
                                });
                            }
                        });
                        pageVM.pageQuery(1);
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
        <!-- <div class="block-header">
            <h2>成员管理</h2>
        </div> -->
        <div class="card" style="display: none;" ms-visible="isAdd">
            <div class="card-header ch-alt">
                <h2>添加成员
                    <small>请输入要添加的成员诺部落号或者手机号，回车键或者点击【添加】按钮完成操作。</small>
                </h2>
            </div>
            <div class="card-body card-padding">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="form-group fg-line">
                            <input type="text" class="form-control" ms-duplex-string="keyForAdd" ms-keyup="keyup"
                                   placeholder="诺部落号、手机号">
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <button type="button" class="btn btn-primary m-t-5 waves-effect" ms-click="addUser" id="addBtn">
                            添加
                        </button>
                        <button type="button" class="btn btn-primary m-t-5 m-l-10 waves-effect"
                                ms-click="showAdd(false)">取消
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <%--  <div class="card-header ch-alt">
                  <h2>成员管理</h2>
                  <c:if test="${isAdmin==1}">
                  <a class="btn bgm-teal btn-float" href="javascript:;" title="添加成员" ms-click="showAdd(true)"><i class="md md-add"></i></a>
                  </c:if>
              </div>--%>
            <div class="card-body card-padding">
                <table class="table table-vmiddle table-hover">
                    <thead>
                    <tr>
                        <th>标签ID</th>
                        <th>木筏ID</th>
                        <th>温度值</th>
                        <th>电池状态</th>
                        <th>状态</th>
                    </tr>
                    <tr>
                        <td>
                            <div class="input-prepend input-append"  style="display: inline-block;margin-right: 10px;">
                                <input class="span2" ms-on-input="filter" name="flag_id" td type="text" placeholder="输入标签ID">
                            </div>
                        </td>
                        <td>
                            <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                                <input class="span2"  ms-on-input="filter" name="raft_id" td type="text" placeholder="输入木筏ID">
                            </div>
                        </td>
                        <td>
                            <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                                <input class="span2"  ms-on-input="filter" name="tempetature" td type="text" placeholder="输入温度值">
                            </div>
                        </td>
                        <td>
                            <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                                <input class="span2"  ms-on-input="filter" name="buttery" td type="text" placeholder="输入电池状态">
                            </div>
                        </td>
                        <td>
                            <div class="input-prepend input-append" style="display: inline-block;margin-right: 10px;">
                                <input class="span2"  ms-on-input="filter" name="state" td type="text" placeholder="输入状态">
                            </div>
                        </td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ms-repeat="list">
                        <td>{{el.flag_id}}</td>
                        <td>{{el.raft_id}}</td>
                        <td>{{el.tempetature}}</td>
                        <td>{{el.buttery}}</td>
                        <td>{{el.state}}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="bootgrid-footer container-fluid">
                <%--<div class="row">
                    <div class="col-sm-6">
                        <ul class="pagination" style="margin: 0;">
                            <li class="prev active" ms-class="disabled:page.pageNumber<2"
                                ms-click="pageQuery(page.pageNumber-1)"><a class="button"><i
                                    class="md md-chevron-left"></i></a></li>
                            <li class="next active"
                                ms-class="disabled:page.pageNumber==page.totalPage||page.totalPage==0"
                                ms-click="pageQuery(page.pageNumber+1)"><a class="button"><i
                                    class="md md-chevron-right"></i></a></li>
                        </ul>
                    </div>
                    <div class="col-sm-6 infoBar">
                        <div class="infos">第{{page.pageNumber}}页，共{{page.totalPage}}页,{{page.totalRow}}条</div>
                    </div>
                </div>--%>
                <div class="pager form-horizontal tablesorter-pager" data-column="0">
                    <button type="button" ms-class="disabled:page.pageNumber<2" class="btn first" ms-click="pageQuery(1)">首页</button>
                    <button type="button" ms-class="disabled:page.pageNumber<2" class="btn prev" ms-click="pageQuery(page.pageNumber-1)">上一页
                    </button>
                    <span class="pagedisplay">{{page.pageNumber}} / {{page.totalPage}}</span>
                    <!-- this can be any element, including an input -->
                    <button type="button" class="btn next"  ms-class="disabled:page.pageNumber==page.totalPage||page.totalPage==0" ms-click="pageQuery(page.pageNumber+1)">
                        下一页
                    </button>
                    <button type="button" class="btn last"
                            ms-class="disabled:page.pageNumber==page.totalPage||page.totalPage==0" ms-click="pageQuery(page.totalPage)">末页</button>
                    <select class="pagesize input-mini" title="每页条数">
                        <option value="30" selected="selected">30</option>
                        <option value="50">50</option>
                        <option value="100">100</option>
                        <option value="500">100</option>
                        <option value="1000">1000</option>
                        <option value="2000">2000</option>
                    </select>
                    <select class="pagenum input-mini" title="页码">
                        <option ms-repeat="pageCount">{{$index+1}}</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>