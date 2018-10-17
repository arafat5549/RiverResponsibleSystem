<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div  buttons="#dlg-buttons"><%--id="dlg" class="easyui-dialog" closed="true"--%>
	<form id="fm" method="post" style="margin: 2px">
		<input id="id" name="id" type="hidden">
		<table class="table">
			<tr>
				<td>
					<label>设备名称:</label>
					<input name="name" class="easyui-textbox"
						   data-options="required:true,missingMessage:'品牌名称必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>序列号:</label>
					<input name="sno" class="easyui-textbox" data-options="required:true,missingMessage:'序列号必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>协议:</label>
					<input name="protocol" class="easyui-textbox" data-options="required:true,missingMessage:'协议必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>品牌:</label>
					<%--<input name="BRANDId" class="easyui-textbox" data-options="required:true,missingMessage:'协议必填'">--%>
					<input class="easyui-combobox" name="brandId"
						   data-options="valueField:'id',textField:'name',editable:false,method:'GET',url:'${pageContext.request.contextPath}/api/brands/findAll'">
				</td>
			</tr>
			<tr>
				<td>
					<label>分类:</label>
					<%--<input name="categoryId" class="easyui-textbox" data-options="required:true,missingMessage:'协议必填'">--%>
					<input  name="categoryId" class="easyui-combotree"
						   data-options="url:'${pageContext.request.contextPath}/api/categorys',method:'GET',editable:false">
				</td>
			</tr>
			<tr>
				<td>
					<label>测量上限:</label>
					<input name="measureMax" class="easyui-textbox" data-options="required:true,missingMessage:'测量上限必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>测量下限:</label>
					<input name="measureMin" class="easyui-textbox" data-options="required:true,missingMessage:'测量下限必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>数据单位:</label>
					<input name="dataUnit" class="easyui-textbox" data-options="required:true,missingMessage:'数据单位必填'">
				</td>
			</tr>
			<tr>
				<td>
					<label>供应商:</label>
					<input name="supplier" class="easyui-textbox" >
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