var method;
var treeGrid;
var treeGridFrozenColumns = [
    {title: '编号', field: 'id', width: 40, align: 'center'}];
var treeGridColumns = [
    {field: 'text', title: '部门名称', halign: 'center', width: 100},
    {field: 'sort', title: '排序', halign: 'center', width: 40, align: 'center'},
    {field: 'iconCls', title: '图标', halign: 'center', width: 50, align: "center",},
    {field: 'parentId', title: '上级资源ID', width: 50, hidden: true},
    {field: 'state', title: '状态', width: 40, align: "center"}];

function searchFun() {
    treeGrid.treegrid('load', myForm.serializeObject('form'));
    treeGrid.treegrid("unselectAll");
}

function cleanFun() {
    $('#form').form("reset");
    treeGrid.treegrid('load', {});
    treeGrid.treegrid("unselectAll");
}

function cancelFun() {
    treeGrid.treegrid("unselectAll");
}

function addInfo() {
    EasyUI.occDialog('新增部门', 320,'auto', 'icon-add', 'page/department/edit.jsp');
    $('#fm').form('clear');
    method = 'POST';
}

function updateInfo() {
    var node = treeGrid.treegrid('getSelected');
    if (node) {
        EasyUI.occDialog('更新部门', 320,'auto', 'icon-edit', 'page/department/edit.jsp', function () {
            $('#fm').form('load', node);
        });
    } else {
        $.messager.show({
            title: '提示信息',
            msg: '请选择一行更新!'
        });
    }
    method = 'PUT';
}

function commitInfo() {
    var flag = $('#fm').form('validate');
    if (flag) {
        var data = myForm.serializeObject('fm');
        data['name'] = data.text;   //后台属性是name
        data['_method'] = method;
        myForm.ajaxRequest(basePath + 'api/departments', data, function (r) {
            if (r.result) {
                $('.window-body').dialog("destroy");
                $.messager.alert('信息', r.msg);
                treeGrid.treegrid('reload');
                treeGrid.treegrid('unselectAll');
                //资源grid更新的时候出发下下拉框的值也更新
                $("#addParent").combotree("reload", basePath + 'api/departments');
                $("#queryParent").combotree("reload", basePath + 'api/departments');
            } else {
                $.messager.alert('警告', r.msg);
            }
        });
    } else {
        $.messager.alert('警告', "请填写完整的信息");
    }
}

function deleteInfo() {
    var node = treeGrid.treegrid('getSelected');
    if (node) {
        $.messager.confirm('提示信息', '确认删除部门【' + node.text + '】吗？', function (r) {
            if (r) {
                myForm.ajaxRequest(basePath + 'api/departments', {_method: 'DELETE', id: node.id}, function (r) {
                    if (r.result) {
                        $.messager.alert('信息', r.msg);
                        treeGrid.treegrid('load', {});
                        treeGrid.treegrid('unselectAll');
                        //资源grid更新的时候出发下下拉框的值也更新
                        $("#addParent").combotree("reload", basePath + 'api/departments');
                        $("#queryParent").combotree("reload", basePath + 'api/departments');
                    } else {
                        $.messager.alert('警告', r.msg);
                    }
                });
            }
        });
    } else {
        $.messager.show({
            title: '提示信息',
            msg: '请选择一行删除!'
        });
    }
}

$(function () {
    treeGrid = EasyUI.treeGrid('treeGrid', treeGridFrozenColumns, treeGridColumns, basePath + 'api/departments');
});