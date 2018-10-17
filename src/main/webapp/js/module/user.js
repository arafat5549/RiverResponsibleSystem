var url;
var method;
var grid;
var gridColumns = [
//{field:'ck',checkbox:true },
    {field: "id", title: "ID", align: "center", hidden: true, editor: false},
    {field: "fullname", title: "姓名", align: "center", width: 150},
    {field: "username", title: "用户账号", align: "center", width: 100},
    {
        field: "gender", title: "性别", align: "center", width: 100,
        formatter: function (value, row, index) {
            return row.gender == '0' ? '男' : '女';
        }
    },
    {field: "cellphone", title: "手机", align: "center", width: 100},
    {field: "telephone", title: "电话", align: "center", width: 100},
    {field: "birthday", title: "生日", align: "center", width: 100},
    {field: "email", title: "邮箱", align: "center", width: 200},
    {field: "updateDate", title: "最后操作时间", align: "center", width: 200},
    {
        field: "departmentId", title: "部门", align: "center", width: 100,
        formatter: function (value, row, index) {
            return row.departmentName;
        }
    }
    /*{field:"roleIds",title:"角色",align:"center",width:100,
        formatter: function(value,row,index){
            return row.roleNames;
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

function cancelInfo() {
    grid.datagrid("unselectAll");
}

function addInfo() {
    EasyUI.occDialog('新增用户', 320, 'auto', 'icon-add', 'page/user/add.jsp');
    $('#fm').form('clear');
    method = 'POST';
}

function updateInfo() {
    var node = grid.datagrid('getSelected');
    if (node) {
        EasyUI.occDialog("编辑用户", 320, 'auto', "icon-edit", 'page/user/edit.jsp', function () {
            $('#fm-1').form('load', node);
        });
        method = "PUT";
    } else {
        $.messager.show({
            title: '提示信息',
            msg: '请选择一行更新!'
        });
    }
}

function excelExport() {
    $('#form').form('submit', {url: basePath + 'api/users/export'});
}

function deleteInfo() {
    var row = grid.datagrid('getSelected');
    if (row) {
        $.messager.confirm('提示信息', '确认删除用户【' + row.fullname + '】吗？', function (r) {
            if (r) {
                myForm.ajaxRequest(basePath + 'api/users', {_method: 'DELETE', id: row.id}, function (r) {
                    if (r.result) {
                        $.messager.alert('信息', r.msg);
                        grid.datagrid('load', {});
                        grid.datagrid('unselectAll');
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

function updatePassword() {
    var node = grid.datagrid('getSelected');
    if (node) {
        EasyUI.occDialog("更新密码", 320, 'auto', "icon-edit", 'page/user/password.jsp', function () {
            node.password = "";
            $('#fm-3').form('clear');
            $('#fm-3').form('load', node);
        });
        method = "PUT";
    } else {
        $.messager.show({
            title: '提示信息',
            msg: '请选择一行更新!'
        });
    }
}

function commit(formId, dialogId) {
    var flag = $('#' + formId).form('validate');
    if (flag) {
        var data = myForm.serializeObject(formId);
        data['_method'] = method;
        myForm.ajaxRequest(basePath + 'api/users', data, function (r) {
            if (r.result) {
                $('.window-body').dialog("destroy");
                $.messager.alert('信息', r.msg);
                grid.datagrid('reload');
                grid.datagrid('unselectAll');
            } else {
                $.messager.alert('警告', r.msg);
            }
        })
    } else {
        $.messager.alert('警告', "请填写完整的信息");
    }
}


function updateRole() {
    var node = grid.datagrid('getSelected');
    if (node) {
        EasyUI.occDialog("角色授予", 320, 'auto', "icon-edit", 'page/user/userRole.jsp', function () {
            $('#fm-2').form('clear');
            $("#userId").val(node.id);
        });
        myForm.ajaxGetRequest(basePath + '/api/users/' + node.id + '/roleIds', {}, function (r) {
            $("#roleIds").combobox("setValues", r);
        })
    } else {
        $.messager.show({
            title: '提示信息',
            msg: '请选择一行更新!'
        });
    }
}

function roleCommit() {
    $.messager.confirm('提示信息', '是否授予角色？', function (flag) {
            if (flag) {
                var roleIds = $("#roleIds").combobox("getValues");
                var userId = $("#userId").val();
                $.ajax({
                    type: 'POST',
                    traditional: true,
                    url: basePath + '/api/users/user_roles',
                    data: {userId: userId, roleIds: roleIds},
                    success: function (r) {
                        if (r.result) {
                            $('.window-body').dialog("destroy");
                            $.messager.alert('信息', r.msg);
                            grid.datagrid('load', {});
                            grid.datagrid('unselectAll');
                        } else {
                            $.messager.alert('警告', r.msg);
                        }
                    }
                });
            }
        }
    );

}

$(function () {
    grid = EasyUI.dataGrid('grid', gridColumns, basePath + "api/users");
});