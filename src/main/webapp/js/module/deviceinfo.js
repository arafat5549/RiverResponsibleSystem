var method;
var grid;
var gridColumns = [
{field:"id",			title:"ID",			align:"center",		hidden:true,editor:false},
{field:"name",			title:"设备名称",	align:"center",		width:150},
{field:"sno",			title:"序列号",	align:"center",		width:150},
{field:"protocol",			title:"协议",	align:"center",		width:80},
{field:"brandId",			title:"品牌",	align:"center",		width:80,
    formatter: function(value,row,index){
        return row.brandName;
    }},
{field:"categoryId",			title:"分类",	align:"center",		width:80,
    formatter: function(value,row,index){
        return row.categoryName;
    }},
{field:"deviceinfoPictureName",			title:"图片",	align:"center",		width:50,
    formatter: function(value,row,index){
	var src = 'files/small/'+row.deviceinfoPictureName;
	var imgHtml = "<a href='javascript:image_click(" + row.id + ")'><img src='" + src + "'> </a>";
	return imgHtml;
    }},
{field:"measureMin",			title:"测量下限",	align:"center",		width:80},
{field:"measureMax",			title:"测量上限",	align:"center",		width:80},
{field:"dataUnit",			title:"数据单位",	align:"center",		width:80},
{field:"supplier",			title:"供应商",	align:"center",		width:150},
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
    EasyUI.occDialog('新增设备', 320, 'auto','icon-add', 'page/deviceinfo/edit.jsp');
    $('#fm').form('clear');
    method = 'POST';
}
function updateInfo(){
	var row = grid.datagrid('getSelected');
	if(row){
        EasyUI.occDialog('更新设备', 320,'auto', 'icon-edit', 'page/deviceinfo/edit.jsp', function () {
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

function photoUpload(){
    var row = grid.datagrid('getSelected');
    if(row){
        myForm.ajaxGetRequest(basePath+"api/deviceinfos/getPhotoCountById",{id:row.id},function (r) {
            EasyUI.occDialog('上传图片', 550, 460,'icon-upload', 'page/deviceinfo/photoUpload.jsp?deviceinfoId='+row.id+"&count="+r);
        });
	}else{
        $.messager.show({
            title:'提示信息',
            msg:'请选择一行上传!'
        });
    }

}

function image_click(id) {
            $('<div/>').dialog({
                title: '图片浏览',
                width: 560,
                height: 460,
                iconCls: 'icon-upload',
                top: 100,
                closed: false,
                cache: false,
                href: 'page/deviceinfo/photoView.jsp?deviceinfoId='+id,
                modal: true,
                closable: true,
                onClose:function () {
                    $('.window-body').dialog("destroy");
                }
            });
}

function commitInfo(){
    var flag = $('#fm').form('validate');
	if(flag){
		var data = myForm.serializeObject('fm');
		data['_method'] = method;
		myForm.ajaxRequest(basePath+'api/deviceinfos',data,function (r) {
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

function excelExport() {
    $('#form').form('submit', {url: basePath + 'api/deviceinfos/export'});
}

function deleteInfo(){
	var row = grid.datagrid('getSelected');
	if(row){
		$.messager.confirm('提示信息' , '确认删除设备【'+row.name+'】吗？', function(r){
			if(r){
				myForm.ajaxRequest(basePath+'api/deviceinfos',{_method:'DELETE',id:row.id},function (r) {
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
	grid = EasyUI.dataGrid('grid',gridColumns,basePath+"api/deviceinfos");
});