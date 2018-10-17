<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>nihao11</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <jsp:include page="../../top.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/imgInput/css/ssi-uploader.css" />
</head>
<body class="easyui-layout" data-options="fit:true,border:false">

<div data-options="region:'center',title:'',border:false">

    <!--
    <div id="grid"></div>
    <div id="toolBar" style="vertical-align: center">
        <div>
            <shiro:hasPermission name="新增设备">
                <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'"
                   onclick="addInfo()">新增1112</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="更新设备">
                <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'"
                   onclick="updateInfo()">更新</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="删除设备">
                <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"
                   onclick="deleteInfo()">删除</a>
            </shiro:hasPermission>
            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'"
               onclick="photoUpload()">上传图片</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'"
               onclick="cancelInfo()">清空选中</a>
            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'"
               onclick="cleanForm()">刷新111</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-download',plain:true"
               onclick="excelExport()">Excel导出</a>
        </div>

        <form id="form" style="margin-bottom: 0px">
            <input id="keyword" name="keyword"  class="easyui-textbox" data-options="prompt:'输入设备名称或序列号等'">
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
               onclick="searchForm()">查询</a>
            <label>品牌:</label>
            <input  name="brandId" class="easyui-combobox"
                    data-options="url:'${pageContext.request.contextPath}/api/brands/findAll',method:'get',
                    valueField:'id',textField:'name',editable:false,onChange:function(){ searchForm() } ">
            <label>分类:</label>
            <input  name="categoryId" class="easyui-combotree"
                    data-options="url:'${pageContext.request.contextPath}/api/categorys',method:'get',editable:false,onChange:function(){ searchForm() } ">
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
               onclick="cleanForm()">重置</a>
        </form>
    </div>

    <div id="p" class="easyui-panel" title="My Panel"
         style="width:500px;height:150px;padding:10px;background:#fafafa;"
         data-options="iconCls:'icon-save',closable:true,
                collapsible:true,minimizable:true,maximizable:true">
        <p>panel content.</p>
        <p>panel content.</p>
    </div>
    -->

</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.2/datagrid/datagrid-filter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/imgInput/js/ssi-uploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/deviceinfodata.js"></script>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
</script>
</body>

</html>
