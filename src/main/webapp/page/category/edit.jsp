<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div  buttons="#dlg-buttons"> <%--id="dlg" class="easyui-dialog" closed="true"--%>
	<form id="fm" method="post" style="margin: 2px">
		<input id="id" name="id" type="hidden">
		<table class="table" style="min-width: 400px">
			<tr>
				<td>
					<label>分类父节点:</label>
					<input id="addParent" name="parentId" class="easyui-combotree"
						   data-options="url:'${pageContext.request.contextPath}/api/categorys',method:'GET',editable:false">
				</td>
			</tr>
			<tr>
				<td>
					<label>分类名称:</label>
					<input id="text" name="text" class="easyui-textbox"
						   data-options="required:true,missingMessage:'部门名称必填'">
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
		</table>
	</form>
</div>

<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="commitInfo()"
	   style="width:90px">确定</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
	   onclick="javascript:$(this).closest('.window-body').dialog('destroy')" style="width:90px">取消</a>
</div>