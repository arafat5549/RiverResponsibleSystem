var EasyUI = function () {
    return {
        dialog: function (id, icon, title) {
            return $('#' + id).dialog({
                top: 100,
                iconCls: icon,//"icon-add","icon-edit",
                modal: true
            }).dialog('open').dialog('setTitle', title);
        },
        occDialog: function (title,width,height,iconCls,href,fun) {
             $('<div/>').dialog({
                title: title,
                width: width,
                height: height,
                iconCls: iconCls,
                top: 100,
                closed: false,
                cache: false,
                href: href,
                modal: true,
                closable: false,
                onLoad:fun
            });
        },
        form: function (id) {
            return $('#' + id).form({
                success: function (data) {
                    $.messager.alert('Info', data, 'info');
                }
            });
        },
        dataGrid: function (id,columns,url) {
            return $('#' + id).datagrid({
                url : 			url,
                method:			"GET",
                title:   		"",
                toolbar:		"#toolBar",
                singleSelect:   true,
                border:			false,
                fit:			true,
                idField:		"id",
                autoRowHeight:	true,
                striped:		true,         //奇偶行颜色
                loadMsg:		"正在加载信息,请耐心的等待...",
                columns:		[columns],
                pagination:		true,
                pageSize:		15,
                pageList:		[5,10,15,20,50],
                rownumbers: 	true
            });
        },
        comboTree: function () {

        },
        treeGrid: function (id, frozenColumns, columns, url) {
            return $('#' + id).treegrid({
                url: url,
                method:'GET',
                idField: 'id',
                treeField: 'text',
                parentField: 'parentId',
                border: false,
                fit: true,
                fitColumns: true,
                animate: true,
                frozenColumns: [frozenColumns],
                columns: [columns],
                toolbar: '#toolbar',
                footer: '#footer'
            });
        },
        tree: function (id,url) {
            return $('#'+id).tree({
                url:url,
                method:'GET',
                idField : 'id',
                cascadeCheck:false,
                lines : true,
                checkbox : true,
                onCheck:function(node){
                    var  node1=$('#'+id).tree('getParent',node.target);
                    $('#'+id).tree('check', node1.target);
                }
            });
            
        }
    }
}();

var TreeGrid = function () {
    return {}

}();