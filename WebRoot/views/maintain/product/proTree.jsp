<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="${path}/static/sea-modules/sea.js"></script>
    <script src="${path}/static/seajs-config.js"></script>
    <script>
        var zTree;
        var setting = {
            async: {
                enable: true,
                url: '${path}/maintain/productclassify/toMain',
                autoParam: ['id']
            },
            data: {
                simpleData: {
                    enable: true,
                    pIdKey: 'up_id'
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {

                    window.frames['moduleFrame'].location.href = '${path}/maintain/product/getTreeSelected/' + treeNode.id;
                },
                onAsyncSuccess: asyncSuccess
            }};
        seajs.use(['$','ztree', 'ligerui'], function ($,ztree, ligerui) {
            zTree=$.fn.zTree.init($("#treeC"), setting);
            zTree.expandAll(true);
            //布局
            $("#layout1").ligerLayout({ leftWidth: 200,allowLeftCollapse: false  });
            $('#framecenter,#moduleFrame').height($(".l-layout-center").height());
            $('.ztree').height($('.l-layout-left').height()-25);

        });
        function asyncSuccess(event, treeId, treeNode, msg) {
            if (window.ztreedata && window.ztreedata.id) {
                zTree.selectNode(zTree.getNodeByParam("id", window.ztreedata.id));
                window.ztreedata.id = null;
            }
        }
        function submitLiger(){
            document.getElementById("moduleFrame").contentWindow.submitLiger();
        }
        function submitForm(){
            document.getElementById("moduleFrame").contentWindow.submitForm();
        }
    </script>
    <style type="text/css">
        .ztree {
            overflow: auto;
            height: 100%;
        }
    </style>
</head>
<body>
<div id="layout1" style="width:99.2%;height:99.2%; margin:0 auto; margin-top:4px; ">
    <div position="left" id="accordion1" title="产品树">
        <div id="treeC" class="ztree"></div>
    </div>
    <div position="center" id="framecenter" title="产品管理">
            <iframe frameborder="0" name="moduleFrame" id="moduleFrame" width="100%"></iframe>
    </div>
</div>
</body>
</html>