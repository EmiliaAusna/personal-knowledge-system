<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人知识系统‑登录</title>
</head>
<body>
<h2>个人知识系统登录</h2>
<p style="color: red;">${msg}</p>
<form action="${pageContext.request.contextPath}/user/login" method="post">
    <div>
        用户名：<input type="text" name="username" />
    </div>
    <div>
        密码：<input type="password" name="password" />
    </div>
    <div>
        <input type="submit" value="登录">
    </div>
</form>
</body>
</html>
