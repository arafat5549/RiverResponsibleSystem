<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div  buttons="#dlg-buttons"><%--id="dlg" class="easyui-dialog" closed="true"--%>
	<form id="fm" method="post" style="margin: 2px">
		<input id="id" name="id" type="hidden">
		<table class="table">
			<tr>
				<td>
					<label>品牌名称:</label>
					<input name="name" class="easyui-textbox"
						   data-options="required:true,missingMessage:'品牌名称必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>英文名称:</label>
					<input name="ename" class="easyui-textbox">
				</td>
			</tr>
			<tr>
				<td>
					<label>网址:</label>
					<input name="website" class="easyui-textbox" data-options="required:true,missingMessage:'网址必填'">
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