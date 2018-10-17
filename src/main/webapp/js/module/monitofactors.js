var method;
var grid;
var gridColumns = [
{field:"id",			title:"ID",			align:"center",		hidden:true,editor:false},
{field:"name",			title:"名称",	align:"center",		width:100},
{field:"ename",			title:"英文名",	align:"center",		width:100},
{field:"unit",			title:"单位",	align:"center",		width:100},
{field:"overproofMax",	title:"超标上限",align:"center",		width:100},
{field:"overproofMin",	title:"超标下限",	align:"center",		width:100},
{field:"invalidMax",	title:"无效上限",	align:"center",		width:100},
{field:"invalidMin",	title:"无效下限",	align:"center",		width:100},
{field:"warnMax",	title:"报警上限",	align:"center",		width:100},
{field:"warnMin",	title:"报警下限",	align:"center",		width:100},
{field:"updatePerson",			title:"最后操作人",	align:"center",		width:150},
{field:"updateDate",			title:"最后操作时间",	align:"center",		width:200}
/*{field:"resourceIds",	title:"权限",		align:"center",		width:350,
	formatter: function(value,row,index){
		return row.resourceNames;
	}
}*/];
function searchForm() {
	grid.datagrid('load', myForm.serializeObject('form'));
	grid.datagrid("unselectAll");
}
function cleanForm() {
	$('#form').form("reset");
	grid.datagrid('load', {});
	grid.datagrid("unselectAll");
}
function cancelInfo(){
	grid.datagrid("unselectAll");
}
function addInfo(){
    EasyUI.occDialog('新增品牌', 320, 'auto','icon-add', 'page/monitofactors/edit.jsp');
    $('#fm').form('clear');
    method = 'POST';
}
function updateInfo(){
	var row = grid.datagrid('getSelected');
	if(row){
        EasyUI.occDialog('更新品牌', 320,'auto', 'icon-edit', 'page/monitofactors/edit.jsp', function () {
            $('#fm').form('load', row);
        });
        method = "PUT";
	}else{
		$.messager.show({
			title:'提示信息',
			msg:'请选择一行更新!'
		});	
	}
}
function commitInfo(){
    var flag = $('#fm').form('validate');
	if(flag){
		var data = myForm.serializeObject('fm');
		data['_method'] = method;
		myForm.ajaxRequest(basePath+'api/monitofactorss',data,function (r) {
            if(r.result){
                $('.window-body').dialog("destroy");
                $.messager.alert('信息',r.msg);
                grid.datagrid('reload');
                grid.datagrid('unselectAll');
            }else{
                $.messager.alert('警告',r.msg);
            }
        })
	}else{
		$.messager.alert('警告',"请填写完整的信息"); 
	}
}
function deleteInfo(){
	var row = grid.datagrid('getSelected');
	if(row){
		$.messager.confirm('提示信息' , '确认删除品牌【'+row.name+'】吗？', function(r){
			if(r){
				myForm.ajaxRequest(basePath+'api/monitofactorss',{_method:'DELETE',id:row.id},function (r) {
                    if(r.result){
                        $.messager.alert('信息',r.msg);
                        grid.datagrid('load', {});
                        grid.datagrid('unselectAll');
                    }else{
                        $.messager.alert('警告',r.msg);
                    }
                });
			}
		});
	}else{
		$.messager.show({
			title:'提示信息',
			msg:'请选择一行删除!'
		});	
	}
}


$(function(){
	grid = EasyUI.dataGrid('grid',gridColumns,basePath+"api/monitofactorss");
});