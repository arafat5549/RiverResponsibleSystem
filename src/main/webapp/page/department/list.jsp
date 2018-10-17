<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <%@ include file="../../top.jsp" %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css"/>
    <style type="text/css">
    </style>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false">
        <div id="treeGrid"></div>
        <div id="toolbar">
            <div>
                <shiro:hasPermission name="新增部门">
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'"
                       onclick="addInfo()">新增</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="更新部门">
                    <a href="javascript:void(0);" class="easyui-linkbutton"
                       data-options="plain:true,iconCls:'icon-edit'" onclick="updateInfo()">更新</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="删除部门">
                    <a href="javascript:void(0);" class="easyui-linkbutton"
                       data-options="plain:true,iconCls:'icon-remove'" onclick="deleteInfo()">删除</a>
                </shiro:hasPermission>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'"
                   onclick="cancelFun()">清空选中</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'"
                   onclick="cleanFun()">刷新</a>
            </div>
            <form id="form" style="margin: 2px">
                <label>一级菜单:</label>
                <input id="queryParent" name="parentId" class="easyui-combotree"
                       data-options="url:'${pageContext.request.contextPath}/api/departments',method:'GET',editable:false">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
                   onclick="searchFun()">查询</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
                   onclick="cleanFun()">重置</a>
            </form>
        </div>
        <div id="footer" style="color:green;text-align:center">温馨提示:如果修改了部门数据,下拉框中的值不对应,请刷新页面</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module/department.js"></script>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
</script>
</body>
</html>