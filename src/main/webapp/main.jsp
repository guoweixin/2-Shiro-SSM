
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%> ">
    <title>主页面</title>
</head>
<body>

<h1>主页面</h1>
<a href="users/logout">退出登录</a>

<a href="login/exit">退出登录2种用 logout方式</a>

<hr>
<shiro:hasRole name="stu">
    <a href="student.jsp">学生页面</a><br/>
</shiro:hasRole>

<shiro:hasRole name="tea">
    <a href="teacher.jsp">老师页面</a><br/>
</shiro:hasRole>
<shiro:hasRole name="stu">
    <shiro:hasRole name="tea">
        <a href="list.jsp">列表页面（需要stu和tea角色）</a><br/>
    </shiro:hasRole>
</shiro:hasRole>
<hr/>

<shiro:lacksRole name="stu">
    我没有stu角色<br/>
</shiro:lacksRole>

<shiro:lacksRole name="tea">
    我没有tea角色
</shiro:lacksRole>


</body>
</html>
