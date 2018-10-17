<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div  buttons="#dlg-buttons-1"><%--id="dlg-1" class="easyui-dialog" closed="true"--%>
	<form id="fm" method="post" style="margin: 2px">
		<input id="id" name="id" type="hidden">
		<table class="table">
			<tr>
				<td>
					<label>字典类型:</label>
					<input  name="dictionaryTypeId" class="easyui-combobox"
						   data-options="valueField:'id',textField:'name',method:'GET',required:true,missingMessage:'字典类型必填',
		        						url:'${pageContext.request.contextPath}/api/dictionaryTypes/findAll',
		        						<%--onSelect:onSelect,onHidePanel:onHidePanel--%>" />
				</td>
			</tr>
			<tr>
				<td>
					<label>字典值数据:</label>
					<input  name="name" class="easyui-textbox"
						   data-options="required:true,missingMessage:'字典值数据必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>排序:</label>
					<input  name="sort" class="easyui-textbox"
						   data-options="required:true,missingMessage:'排序必填'">
				</td>
			</tr>
		</table>
	</form>
</div>

<div id="dlg-buttons-1">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="commitInfo()" style="width:90px">确定</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$(this).closest('.window-body').dialog('destroy')" style="width:90px">取消</a>
</div>
