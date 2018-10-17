<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<div  buttons="#dlg-buttons-1"><%--id="dlg-1" class="easyui-dialog" closed="true"--%>
    <form id="fm-1" method="post" style="margin: 2px">
        <input name="id" type="hidden">
        <table class="table">
            <tr>
                <td>
                    <label>用户账号:</label>
                    <input  name="username" class="easyui-textbox"
                           data-options="required:true,missingMessage:'用户账号必填'">
                </td>
            </tr>
            <tr>
                <td>
                    <label>姓名:</label>
                    <input name="fullname" class="easyui-textbox"
                           data-options="required:true,missingMessage:'姓名必填'">
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
                    <select class="easyui-combobox" name="gender" style="width:173px;">
                        <option value="0">男</option>
                        <option value="1">女</option>
                    </select>
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
<div id="dlg-buttons-1">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="commit('fm-1','dlg-1')"
       style="width:90px">确定</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$(this).closest('.window-body').dialog('destroy')" style="width:90px">取消</a>
</div>