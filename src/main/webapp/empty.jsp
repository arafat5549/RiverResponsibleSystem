<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
    <base href="<%=basePath%>">
    <title>nihao11</title>
    <jsp:include page="../include/common/meta.jsp"/>
    <jsp:include page="../include/common/csslib.jsp"/>

</head>
<body>

<div class="page-content-wrapper">
    <div class="page-container">
    </div>
</div>

<jsp:include page="../include/common/jslib.jsp"/>
</body>
</html>
