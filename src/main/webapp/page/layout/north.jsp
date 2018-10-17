<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript" charset="utf-8">
	function logoutFun(b) {
		window.location.href='${pageContext.request.contextPath}/logout';
	}
	function userInfoFun() {
		var time = new Date().getTime();
		obj = $('<div/>').dialog({
			href : 'common/getCurrentUserInfo?time='+time,
			modal : true,
			top:150,
			width:350,
			title : '个人信息',
			onClose:function(){
				$(this).dialog('destroy');
			}
		});
	}
</script>
<style>
 h1{font: 20px/20px Verdana;color: #fff;}
</style>
<div style="position:absolute;left:5px;">
<!-- 	<img alt="" src=""> -->
</div>
<div id="sessionInfoDiv" style="position: absolute;right: 5px;top:10px;">
</div>
<div style="position: absolute; right: 0px; bottom: 2px; ">
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'icon-advancedsettings2'">更换皮肤</a> 
<!-- 	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-help'">控制面板</a>  -->
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="iconCls:'icon-back'," onclick="logoutFun()">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="changeTheme('default');">默认</div>
	<div onclick="changeTheme('gray');">灰色</div>
	<div onclick="changeTheme('metro');">metro</div>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
<!-- 	<div onclick="userInfoFun();">个人信息</div> -->
<!-- 	<div class="menu-sep"></div> -->
	<div>
		<span>更换皮肤</span>
		<div style="width: 120px;">
			<div onclick="changeTheme('default');">默认</div>
			<div onclick="changeTheme('gray');">灰色</div>
			<div onclick="changeTheme('metro');">metro</div>
		</div>
	</div>
</div>