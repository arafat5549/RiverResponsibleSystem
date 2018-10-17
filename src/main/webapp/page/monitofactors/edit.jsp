<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<div buttons="#dlg-buttons"><%--id="dlg" class="easyui-dialog" closed="true"--%>
    <form id="fm" method="post" style="margin: 2px">
        <input id="id" name="id" type="hidden">
        <table class="table">
            <tr>
                <td>
                    <label>名称:</label>
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
                    <label>编号:</label>
                    <input name="sno" class="easyui-textbox" data-options="required:true,missingMessage:'编号必填'">
                </td>
            </tr>
            <tr>
                <td>
                    <label>单位:</label>
                    <input name="unit" class="easyui-textbox" data-options="required:true,missingMessage:'单位必填'">
                </td>
            </tr>
            <tr>
                <td>
                    <label>超标上限:</label>
                    <input name="overproofMax" class="easyui-textbox" data-options="required:true,missingMessage:'超标上限必填'">
                </td>
            </tr>
            <tr>
                <td>
                    <label>超标下限:</label>
                    <input name="overproofMin" class="easyui-textbox" data-options="required:true,missingMessage:'超标下限必填'">
                </td>
            </tr>
            <tr>
                <td>
                    <label>无效上限:</label>
                    <input name="invalidMax" class="easyui-textbox" data-options="required:true,missingMessage:'无效上限必填'">
                </td>
            </tr>
            <tr>
                <td>
                    <label>无效下限:</label>
                    <input name="invalidMin" class="easyui-textbox" data-options="required:true,missingMessage:'无效下限必填'">
                </td>
            </tr>
            <tr>
                <td>
                    <label>报警上限:</label>
                    <input name="warnMax" class="easyui-textbox" data-options="required:true,missingMessage:'报警上限必填'">
                </td>
            </tr>
            <tr>
                <td>
                    <label>报警下限:</label>
                    <input name="warnMin" class="easyui-textbox" data-options="required:true,missingMessage:'报警下限必填'">
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