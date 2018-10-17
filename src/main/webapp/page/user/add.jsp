<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div id="dlg" buttons="#dlg-buttons"> <%--id="dlg" class="easyui-dialog" closed="true"--%>
	<form id="fm" method="post" style="margin: 2px;">
    	<input id="id" name="id" type="hidden">
		<table class="table">
			<tr>
				<td>
					<label>用户账号:</label>
					<input name="username" class="easyui-textbox"
						data-options="required:true,missingMessage:'用户账号必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>用户名称:</label>
					<input name="fullname" class="easyui-textbox"
						data-options="required:true,missingMessage:'用户名称必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>密码:</label>
					<input  name="password" class="easyui-textbox" type="password"
						data-options="required:true,missingMessage:'密码必填',validType:'minLength[6]'">
				</td>
			</tr>
			<tr>
				<td>
					<label>确认密码:</label>
					<input  name="eqpassword" class="easyui-textbox" type="password"
						data-options="required:true,missingMessage:'确认密码必填',validType:'eqPwd[\'#password\']'">
				</td>
			</tr>
			<tr>
				<td>
					<label>邮箱:</label>
					<input  name="email" class="easyui-textbox"
						data-options="required:true,missingMessage:'邮箱必填',validType:'email'">
				</td>
			</tr>
			<tr>
				<td>
					<label>手机:</label>
					<input  name="cellphone" class="easyui-textbox"
							data-options="required:true,missingMessage:'手机必填',validType:'phoneNum'">
				</td>
			</tr>
			<tr>
				<td>
					<label>电话:</label>
					<input  name="telephone" class="easyui-textbox"
						data-options="required:true,missingMessage:'电话必填',validType:'phoneNum'">
				</td>
			</tr>
			<tr>
				<td>
					<label>生日:</label>
					<input  name="birthday" class="easyui-datebox" >
				</td>
			</tr>
			<tr>
				<td>
					<label>性别:</label>
					<input  name="gender" class="easyui-textbox"
						   data-options="required:true,missingMessage:'性别必填',validType:'phoneNum'">
				</td>
			</tr>
			<tr>
				<td>
					<label>部门:</label>
					<input  name="departmentId" class="easyui-combotree"
						data-options="url:'${pageContext.request.contextPath}/api/departments',method:'get',editable:false,required:true,missingMessage:'部门必填'">
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="commit('fm','dlg')" style="width:90px">确定</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$(this).closest('.window-body').dialog('destroy')" style="width:90px">取消</a>
</div>
  
  