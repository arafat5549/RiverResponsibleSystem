<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div  buttons="#dlg-buttons-2"><%--id="dlg-2" class="easyui-dialog" closed="true"--%>
  	<form id="fm-2" method="post" style="margin: 2px">
       <input id="userId" name="id" type="hidden">
       <table class="table">
		<tr>
			<td>
				<label>角色:</label>
				<input id="roleIds" class="easyui-combobox" name="roleIds"
					data-options="valueField:'id',textField:'name',multiple:true,editable:false,checkbox:true,method:'GET',url:'${pageContext.request.contextPath}/api/roles/findAll'"/>
			</td>
		</tr>
	</table>
	</form>
</div>
<div id="dlg-buttons-2">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="roleCommit()" style="width:90px">确定</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$(this).closest('.window-body').dialog('destroy')" style="width:90px">取消</a>
</div>