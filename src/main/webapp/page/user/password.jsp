<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div  buttons="#dlg-buttons-3"><%--id="dlg-3" class="easyui-dialog" closed="true"--%>
	<form id="fm-3" method="post" style="margin: 2px">
    	<input id="id-3" name="id" type="hidden">
        <table class="table">
        	<tr>
				<td>
					<label>密码:</label>
					<input name="password" class="easyui-textbox" type="password"
						data-options="required:true,missingMessage:'密码必填',validType:'minLength[6]'">
				</td>
			</tr>
			<tr>
				<td>
					<label>确认密码:</label>
					<input name="eqpassword-3" class="easyui-textbox" type="password"
						data-options="required:true,missingMessage:'确认密码必填',validType:'eqPwd[\'#password-3\']'">
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="dlg-buttons-3">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="commit('fm-3','dlg-3')" style="width:90px">确定</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$(this).closest('.window-body').dialog('destroy')" style="width:90px">取消</a>
</div>