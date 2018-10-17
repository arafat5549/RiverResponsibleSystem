var method;
var grid;
var gridColumns = [
    {field:"id",			title:"ID",			align:"center",		hidden:true,editor:false},
    {field:"dictionaryTypeId",		title:"DICT_ID",	align:"center",		hidden:true,editor:false},
    {field:"dictionaryType.name",		title:"字典类型",	align:"center",		width:200,
        formatter: function(value,row,index){
            return row.dictionaryType.name;
        }},
    {field:"dictionaryType.code",		title:"字典代码",	align:"center",		width:200,
        formatter: function(value,row,index){
            return row.dictionaryType.code;
        }},
    {field:"name",	title:"字典值数据",	align:"center",		width:100},
    {field:"sort",			title:"排序",		align:"center",		width:100}];
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
    EasyUI.occDialog('新增品牌', 320, 'auto','icon-add', 'page/dictionaryData/edit.jsp');
    $('#fm').form('clear');
    method = 'POST';
}
function updateInfo(){
	var row = grid.datagrid('getSelected');
	if(row){
        EasyUI.occDialog('更新品牌', 320,'auto', 'icon-edit', 'page/dictionaryData/edit.jsp', function () {
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
		myForm.ajaxRequest(basePath+'api/dictionaryDatas',data,function (r) {
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
				myForm.ajaxRequest(basePath+'api/dictionaryDatas',{_method:'DELETE',id:row.id},function (r) {
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
	grid = EasyUI.dataGrid('grid',gridColumns,basePath+"api/dictionaryDatas");
});