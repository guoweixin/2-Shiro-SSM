
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%> ">
    <title>登录页面</title>
</head>
<body>
登录页面
<shiro:authenticated>
    登录成功。用户名是：<shiro:principal/>
</shiro:authenticated>

<shiro:notAuthenticated>
    <form action="users/login" method="post">
        <table align="center" width="800px">
                <tr>
                    <td>用户名：</td>
                    <td><input type="text" name="username"/></td>
                </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <div style="color:red;font-size: 12px;">${msg}</div>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value="登录"/>
                </td>
            </tr>
        </table>
    </form>
</shiro:notAuthenticated>



</body>
</html>
