<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>

</head>

<body>
<h1>欢迎登录!${user.username }</h1>
<form action="${pageContext.request.contextPath }/loginUser" method="post">
    <input type="text" name="username"><br>
    <input type="password" name="password"><br>
    <input type="submit" value="提交">
</form>


</body>
</html>--%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login/supersized.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login/style.css">
</head>
<body>
<div class="page-container">
    <h1>会员登录</h1>
    <form action="${pageContext.request.contextPath }/login" method="post">
        <input type="text" name="username" class="username" placeholder="用户账号">
        <input type="password" name="password" class="password" placeholder="密码">
        <button type="submit">登录</button>
        <div class="error"><span>+</span></div>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/module/login/supersized.3.2.7.min.js"></script>
<script src="${pageContext.request.contextPath}/js/module/login/supersized-init.js"></script>
<script src="${pageContext.request.contextPath}/js/module/login/scripts.js"></script>
</body>
</html>