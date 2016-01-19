$.ajaxSetup({
  cache : false
});
var tableWidth;
//表格宽度，按百分比计算
function fixWidth(percent){
	tableWidth =  document.body.clientWidth * percent * 0.01;
	return tableWidth;
}
//列宽度，按百分比计算
function fixColumnWidth(percent){
	return tableWidth * percent * 0.01;
}

function fixHeight(percent){
	return document.body.clientHeight * percent * 0.01;
}

//重新加载DataGrid
function reloadDataGrid(id,param){
	var oldParam = $("#"+id).datagrid("options").queryParams;
	if(param){
		for(var item in param){
			oldParam[item] = param[item];
		}
	}
	$("#"+id).datagrid("load",oldParam);
}

function initOptions(options){  
	if(!options.width && !options.fit){ options.width = fixWidth(98);}
	if(!options.pageSize){ options.pageSize = 20;}
	if(!options.pageNumber){ options.pageNumber = 1;}
	if(!options.nowrap){ options.nowrap = false;}
	if(!options.pageList){ options.pageList = [20,30,40,50,60];}
	if(options.singleSelect == "undefined"){ options.singleSelect = true;}
	if(!options.rownumbers){ options.rownumbers = true;}
	if(!options.method){ options.method = "get";}
	if(!options.height){ options.height = fixHeight(50);}
	//if(!options.multiSort){ options.multiSort = true;}
	if(!options.iconCls){ options.iconCls = 'icon-edit';}
	return initQueryParam(options);
}

//将Columns的field传入后台
//将页面URL传参放入queryParams中，传入后台
function initQueryParam(options){
	var columnWidthModel = options.columnWidthModel || "absolute";
	//如果是相对模式，则转化为百分比
	if(columnWidthModel == "relative"){
		var columns = options.columns[0];
		for(var i=0;i<columns.length;i++){
			var field = columns[i].field;
			//将制定宽度px转换为百分比
			columns[i].width = fixColumnWidth(columns[i].width);
		}
	}

	var urlParam =  $.http.getParam4Json();
	for(var item in urlParam){
		options.queryParams[item]=urlParam[item];
	}
	return options;
}
/**
 * 增加树形节点
 * divID:容器ID
 * parentID:父节点ID
 * id：新增节点ID
 */
function addTreeNode(divID,parentID,id,text,icon){
		var node = $('#'+divID).tree('find', parentID);
		if (node){
			$('#'+divID).tree('append', {
				parent: node.target,
				data: {
					id: id,
					text: text,
					iconCls:icon
				}
			});
		}
}
/**
 * 删除树形节点
 */
function removeTreeNode(divID,id){
		var node = $('#'+divID).tree('find', id);
		if (node){
			$('#'+divID).tree('remove',node.target);
		}
}

function reloadTree(divID){
	$('#'+divID).tree('reload');
}

jQuery.easyui = {

};

jQuery.easyui.datagrid = {
	/**
	 * DataGrid 行编辑扩展，如果需要编辑行支持，请先调用此方法开启扩展
	 */
	editorSupport : function(){
		$.extend($.fn.datagrid.methods, {
	           editCell: function(jq,param){
	               return jq.each(function(){
	                   var opts = $(this).datagrid('options');
	                   var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
	                   for(var i=0; i<fields.length; i++){
	                       var col = $(this).datagrid('getColumnOption', fields[i]);
	                       col.editor1 = col.editor;
	                       if (fields[i] != param.field){
	                           col.editor = null;
	                       }
	                   }
	                   $(this).datagrid('beginEdit', param.index);
	                   for(var i=0; i<fields.length; i++){
	                       var col = $(this).datagrid('getColumnOption', fields[i]);
	                       col.editor = col.editor1;
	                   }
	               });
	           }
		});
	},
	/**
	 * 开始编辑，datagridID：表格的ID，rowIndex:如果提供则开启制定行的编辑，如果不提供在开启整个表格的编辑
	 */
	beginEdit:function(datagridID,rowindex){
		var rows = $("#"+datagridID).datagrid("getRows");
		for(var i=0;i<rows.length;i++){
			var index = $("#dg").datagrid("getRowIndex",rows[i]);
			$("#dg").datagrid("beginEdit",index);
		}
	},
	/**
	 * 结束编辑，返回更新结果：{"updated":updated,"inserted":[],"deleted",[]}
	 * rowIndex:如果提供则结束指定行的编辑，如果不提供在结束整个表格的编辑
	 */
	endEdit:function(datagridid,rowindex){
		var rows = $("#"+datagridid).datagrid("getRows");
		for(var i=0;i<rows.length;i++){
			var index = $("#dg").datagrid("getRowIndex",rows[i]);
			if ($('#dg').datagrid('validateRow', index)){
	               $('#dg').datagrid('endEdit', index);
	       	}
		}
		var updated = $('#dg').datagrid('getChanges', "updated");
		return {"updated":updated,"inserted":[],"deleted":[]};
	},
	/**
	 * 重新加载datagrid，如：$.easyui.datagrid.reload("dg",{});
	 * id:表格ID，param：JSON格式的参数
	 *
	 */
	reload:function(id,param){
		var oldParam = $("#"+id).datagrid("options").queryParams;
		if(param){
			for(var item in param){
				oldParam[item] = param[item];
			}
		}
		$("#"+id).datagrid("load",oldParam);
	},
	/**
	 * 将DataGrid数据导出Excel
	 * datagrid_id:DataGrid的id
	 * query_param：查询条件
	 * action_url：form的action
	 */
	exportExcel:function(datagrid_id,query_param,action_url){
		var datagrid_options = $('#'+datagrid_id).datagrid('options');
		var form = document.getElementById("execlForm");
		if(!form){
			$(document.body).append('<form action="" method="post" id="execlForm"></form>');
		}
		form = document.getElementById("execlForm");
		var html = '';
		if(datagrid_options.frozenColumns.length > 0 && datagrid_options.frozenColumns[0].length > 0){
			html += '<input type="hidden" name="frozenColumns" value='+ JSON.stringify(datagrid_options.frozenColumns[0]) +' />';
		}
		html += '<input type="hidden" name="columns" value='+ JSON.stringify(datagrid_options.columns[0]) +' />';
		for(var item in query_param){
			html += '<input type="hidden" name="'+ item +'" value="'+ query_param[item] +'" />';
		}
		$(form).empty();
		$(form).append(html);
		form.action=action_url;
		form.target = window.top.openDialog(0,90,90,'导出Excel','',{"disableProgress":true});
		form.submit();
	}
};





