var method;
var treeGrid;
var treeGridFrozenColumns = [
    {title: '编号', field: 'id', width: 40, align: 'center'}];
var treeGridColumns = [
    {field: 'text', title: '监测站名称', halign: 'center', width: 120, align: 'center'},
    {field: 'sno', title: '监测站编号', halign: 'center', width: 120, align: 'center'},
    {field: 'sectionNo', title: '断面编号', halign: 'center', width: 100, align: 'center'},
    {field: 'sectionName', title: '断面名称', halign: 'center', width: 100, align: 'center'},
    {field: 'functionType', title: '功能类型', halign: 'center', width: 100, align: 'center'},
    {field: 'type', title: '监测站类型', halign: 'center', width: 100, align: 'center'},
    {field: 'regionalism', title: '行政区划', halign: 'center', width: 100, align: 'center'},
    {field: 'drainageBasin', title: '所处流域', halign: 'center', width: 100, align: 'center'},
    {field: 'riverSystem', title: '所在水系', halign: 'center', width: 100, align: 'center'},
    {field: 'position', title: '所在位置', halign: 'center', width: 100, align: 'center'},
    {field: 'longitude', title: '经度', halign: 'center', width: 100, align: 'center'},
    {field: 'latitude', title: '纬度', halign: 'center', width: 100, align: 'center'},
    {field: 'integrator', title: '集成商', halign: 'center', width: 100, align: 'center'},
    {field: 'operator', title: '运营商', halign: 'center', width: 100, align: 'center'},
    {field: 'communication', title: '通讯方式', halign: 'center', width: 100, align: 'center'},
    {field: 'status', title: '站点状态', halign: 'center', width: 100, align: 'center'},
    {field: 'photo', title: '图片', halign: 'center', width: 45, align: 'center'},
    {field: 'username', title: '负责人', halign: 'center', width: 100, align: 'center'},
    {field: 'sort', title: '排序', halign: 'center', width: 40, align: 'center'},
    /*    {field: 'iconCls', title: '图标', halign: 'center', width: 100, align: "center"},*/
    {field: 'parentId', title: '上级资源ID', width: 75, hidden: true},
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
    EasyUI.occDialog('新增部门', 320, 500, 'icon-add', 'page/monitorsite/edit.jsp');
    $('#fm').form('clear');
    method = 'POST';
}

function updateInfo() {
    var node = treeGrid.treegrid('getSelected');
    if (node) {
        EasyUI.occDialog('更新部门', 320, 500, 'icon-edit', 'page/monitorsite/edit.jsp', function () {
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
        myForm.ajaxRequest(basePath + 'api/monitorsites', data, function (r) {
            if (r.result) {
                $('.window-body').dialog("destroy");
                $.messager.alert('信息', r.msg);
                treeGrid.treegrid('reload');
                treeGrid.treegrid('unselectAll');
                //资源grid更新的时候出发下下拉框的值也更新
                $("#addParent").combotree("reload", basePath + 'api/monitorsites');
                $("#queryParent").combotree("reload", basePath + 'api/monitorsites');
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
                myForm.ajaxRequest(basePath + 'api/monitorsites', {_method: 'DELETE', id: node.id}, function (r) {
                    if (r.result) {
                        $.messager.alert('信息', r.msg);
                        treeGrid.treegrid('load', {});
                        treeGrid.treegrid('unselectAll');
                        //资源grid更新的时候出发下下拉框的值也更新
                        $("#addParent").combotree("reload", basePath + 'api/monitorsites');
                        $("#queryParent").combotree("reload", basePath + 'api/monitorsites');
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
    treeGrid = $('#treeGrid').treegrid({
        url:  basePath + 'api/monitorsites',
        method: 'GET',
        idField: 'id',
        treeField: 'text',
        parentField: 'parentId',
        border: false,
        fit: true,
        fitColumns: false,
        animate: true,
        frozenColumns: [treeGridFrozenColumns],
        columns: [treeGridColumns],
        toolbar: '#toolbar',
        footer: '#footer'
    });
});