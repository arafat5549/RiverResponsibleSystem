<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div buttons="#dlg-buttons"><%--id="dlg" class="easyui-dialog" closed="true"--%>
	<form id="fm" method="post" style="margin: 2px">
		<input id="id" name="id" type="hidden">
		<table class="table" style="min-width: 400px">
			<tr>
				<td>
					<label>资源父节点:</label>
					<input id="addParent" name="parentId" class="easyui-combotree"
						   data-options="url:'${pageContext.request.contextPath}/api/permissions',method:'get',editable:false">
				</td>
			</tr>
			<tr>
				<td>
					<label>资源名称:</label>
					<input id="text" name="text" class="easyui-textbox"
						   data-options="required:true,missingMessage:'资源名称必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>资源路径:</label>
					<input id="url" name="url" class="easyui-textbox" style="width:280px">
				</td>
			</tr>
			<tr>
				<td>
					<label>排序:</label>
					<input id="sort" name="sort" class="easyui-textbox">
				</td>
			</tr>
			<tr>
				<td>
					<label>图标:</label>
					<input id="iconCls" name="iconCls" class="easyui-textbox">
				</td>
			</tr>
			<tr>
				<td>
					<label>类型:</label>
					<input id="type" name="type" class="easyui-combobox"
						   data-options="valueField:'id',textField:'text',required:true,missingMessage:'资源类型必填',editable:false,
										data: [{
											id:'0',text:'菜单'
										},{
											id:'1',text:'功能'
										}]">
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="commitInfo()"
	   style="width:90px">确定</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
	   onclick="javascript:$(this).closest('.window-body').dialog('destroy')" style="width:90px">取消</a>
</div>