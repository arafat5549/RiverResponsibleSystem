<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div  buttons="#dlg-buttons"> <%--id="dlg" class="easyui-dialog" closed="true"--%>
	<form id="fm" method="post" style="margin: 2px">
		<input id="id" name="id" type="hidden">
		<table class="table" style="min-width: 400px">
			<tr>
				<td>
					<label>分类父节点:</label>
					<input id="addParent" name="parentId" class="easyui-combotree"
						   data-options="url:'${pageContext.request.contextPath}/api/monitorsites',method:'GET',editable:false">
				</td>
			</tr>
			<tr>
				<td>
					<label>监测点名称:</label>
					<input id="text" name="text" class="easyui-textbox"
						   data-options="required:true,missingMessage:'部门名称必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>监测点编号:</label>
					<input  name="sno" class="easyui-textbox"
						   data-options="required:true,missingMessage:'监测点编号必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>切面编号:</label>
					<input  name="sectionNo" class="easyui-textbox"
							data-options="required:true,missingMessage:'切面编号必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>切面名称:</label>
					<input  name="sectionName" class="easyui-textbox"
							data-options="required:true,missingMessage:'切面名称必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>(切面)功能类型:</label>
					<input  name="functionType" class="easyui-textbox"
							data-options="required:true,missingMessage:'功能类型必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>站点类型:</label>
					<input  name="type" class="easyui-textbox"
							data-options="required:true,missingMessage:'站点类型必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>所属行政区划:</label>
					<input  name="regionalism" class="easyui-textbox"
							data-options="required:true,missingMessage:'所属行政区划必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>所属流域:</label>
					<input  name="drainageBasin" class="easyui-textbox"
							data-options="required:true,missingMessage:'所属流域必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>所在水系:</label>
					<input  name="riverSystem" class="easyui-textbox"
							data-options="required:true,missingMessage:'所在水系必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>所处位置:</label>
					<input  name="position" class="easyui-textbox"
							data-options="required:true,missingMessage:'部门名称必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>所在经度:</label>
					<input  name="longitude" class="easyui-textbox"
							data-options="required:true,missingMessage:'经度必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>所在纬度:</label>
					<input  name="latitude" class="easyui-textbox"
							data-options="required:true,missingMessage:'所在纬度必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>集成商:</label>
					<input  name="integrator" class="easyui-textbox"
							data-options="required:true,missingMessage:'集成商必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>运营商:</label>
					<input  name="operator" class="easyui-textbox"
							data-options="required:true,missingMessage:'运营商必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>通讯方式:</label>
					<input  name="communication" class="easyui-textbox"
							data-options="required:true,missingMessage:'通讯方式必填'">
				</td>
			</tr>
			<%--<tr>
				<td>
					<label>状态:</label>
					<input  name="status" class="easyui-textbox">
				</td>
			</tr>--%>
			<tr>
				<td>
					<label>站点照片:</label>
					<input  name="photo" class="easyui-textbox">
				</td>
			</tr>
			<tr>
				<td>
					<label>负责人:</label>
					<input class="easyui-combobox" name="userId"
						   data-options="valueField:'id',textField:'fullname',multiple:true,editable:false,checkbox:true,method:'GET',url:'${pageContext.request.contextPath}/api/users/findAll'"/>
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