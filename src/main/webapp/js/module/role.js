var url;
var method;
var grid;
var gridColumns = [
//{field:'ck',checkbox:true },
{field:"id",			title:"ID",			align:"center",		hidden:true,editor:false},
{field:"name",			title:"角色名称",	align:"center",		width:150},
{field:"remark",			title:"描述",	align:"center",		width:1000}
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
    EasyUI.occDialog('新增角色', 320, 'auto','icon-add', 'page/role/edit.jsp');
    $('#fm').form('clear');
    method = 'POST';
}
function updateInfo(){
	var row = grid.datagrid('getSelected');
	if(row){
        EasyUI.occDialog('更新权限', 320,'auto', 'icon-edit', 'page/role/edit.jsp', function () {
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
		myForm.ajaxRequest(basePath+'api/roles',data,function (r) {
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
		$.messager.confirm('提示信息' , '确认删除角色【'+row.name+'】吗？', function(r){
			if(r){
				myForm.ajaxRequest(basePath+'api/roles',{_method:'DELETE',id:row.id},function (r) {
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
function getPermission(){
	var row = grid.datagrid('getSelected');
	if(row){
		//EasyUI.dialog('dlg-1','icon-edit','更新权限');
        EasyUI.occDialog('角色授权', 320, 500,'icon-edit', 'page/role/rolePermission.jsp',function () {
            $("#roleId").val(row.id);
            var tree = EasyUI.tree('roleResourceTree',basePath+'api/roles/'+row.id+'/permissions');
        });
	}else{
		$.messager.show({
			title:'提示信息',
			msg:'请选择一行更新!'
		});	
	}
}
function resourcesCommit(){
	$.messager.confirm('提示信息' , '是否保存权限？' , function(r){
		var nodes = $('#roleResourceTree').tree('getChecked');
		var permissionIds = new Array();
		for ( var j= 0; j < nodes.length; j++) {
            permissionIds.push(nodes[j].id);
	    }
	    var roleId = $("#roleId").val();
        $.ajax({
            type: 'POST',
            traditional:true,
            url:'api/roles/role_permission',
            data:{roleId:roleId,permissionIds:permissionIds},
            success: function(r){
                if(r.result){
                    $('.window-body').dialog("destroy");
                    $.messager.alert('信息',r.msg);
                    grid.datagrid('load', {});
                    grid.datagrid('unselectAll');
                }else{
                    $.messager.alert('警告',r.msg);
                }
            }
        });
		/*myForm.ajaxRequest('api/roles/role_permission', {roleId:roleId,permissionIds:permissionIds},function () {
            if(r.result){
                $.messager.alert('信息',r.msg);
                $('#dlg-1').dialog('close');
                grid.datagrid('load', {});
                grid.datagrid('unselectAll');
            }else{
                $.messager.alert('警告',r.msg);
            }
        });*/
	});
}
$(function(){
	grid = EasyUI.dataGrid('grid',gridColumns,basePath+"api/roles");
	grid.datagrid('enableFilter');
});