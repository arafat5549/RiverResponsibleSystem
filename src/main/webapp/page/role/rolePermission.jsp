<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div  buttons="#dlg-buttons-1"><%--id="dlg-1" class="easyui-dialog" closed="true"--%>
	<div id="roleResourceTree" style="min-width: 250px"></div>
	<input id="roleId" name="roleId" type="hidden"/>
</div>

<div id="dlg-buttons-1">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="resourcesCommit()"
	   style="width:90px">确定</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
	   onclick="javascript:$(this).closest('.window-body').dialog('destroy')" style="width:90px">取消</a>
</div>