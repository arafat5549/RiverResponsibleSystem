<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div  buttons="#dlg-buttons"><%--id="dlg" class="easyui-dialog" closed="true"--%>
	<form id="fm" method="post" style="margin: 2px">
		<input id="id" name="id" type="hidden">
		<table class="table">
			<tr>
				<td>
					<label>角色名称:</label>
					<input name="name" class="easyui-textbox"
						   data-options="required:true,missingMessage:'角色名称必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>描述:</label>
					<input name="remark" class="easyui-textbox">
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