<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String basePath = request.getContextPath();%>

<%--<%@ include file="top.jsp"%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="top.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
</head>
<body id="indexLayout" class="easyui-layout">

<div data-options="region:'north',href:'${pageContext.request.contextPath}/page/layout/north.jsp',split:true"
     style="height:60px;overflow:hidden;background: url(image/bg_header_2.jpg);">
</div>
<div data-options="region:'south',href:'${pageContext.request.contextPath}/page/layout/south.jsp',split:true"
     style="height:27px;overflow:hidden;">
</div>
<div data-options="region:'west',title:'',href:'${pageContext.request.contextPath}/page/layout/west.jsp',iconCls:'icon-chart-curve',split:true"
     style="width: 200px;overflow: hidden;">
</div>
<div data-options="region:'center',title:'',href:'${pageContext.request.contextPath}/page/layout/center.jsp'"
     style="overflow: hidden;">
</div>





</body>

</html>