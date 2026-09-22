<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人知识系统-注册</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: "Microsoft YaHei", Arial, sans-serif; background: #f3f4f6; color: #1f2937; display: flex; justify-content: center; align-items: center; min-height: 100vh; padding: 16px; }
        .reg-card { width: 100%; max-width: 400px; background: #ffffff; border: 2px solid #d1d5db; border-radius: 10px; padding: 30px 32px; box-shadow: 0 2px 10px rgba(0,0,0,0.08); }
        h2 { text-align: center; font-size: 22px; padding-bottom: 14px; border-bottom: 2px solid #2563eb; margin-bottom: 20px; }
        .msg { color: #dc2626; font-size: 14px; text-align: center; min-height: 20px; margin-bottom: 8px; }
        .form-row { margin-bottom: 16px; }
        label { display: block; font-size: 14px; margin-bottom: 6px; color: #374151; }
        input[type=text], input[type=password] { width: 100%; border: 1px solid #9ca3af; border-radius: 6px; padding: 10px 12px; font-size: 15px; }
        input[type=text]:focus, input[type=password]:focus { outline: none; border-color: #2563eb; box-shadow: 0 0 0 2px rgba(37,99,235,0.15); }
        .submit-btn { width: 100%; border: 1px solid #2563eb; background: #2563eb; color: #fff; border-radius: 6px; padding: 10px 12px; font-size: 16px; cursor: pointer; }
        .submit-btn:hover { background: #1d4ed8; }
        .footer { margin-top: 16px; text-align: center; font-size: 14px; }
    </style>
</head>
<body>
<div class="reg-card">
    <h2>个人知识系统注册</h2>
    <p class="msg">${msg}</p>
    <form action="${pageContext.request.contextPath}/user/register" method="post">
        <div class="form-row">
            <label for="username">用户名</label>
            <input type="text" id="username" name="username" value="${username}" required />
        </div>
        <div class="form-row">
            <label for="password">密码</label>
            <input type="password" id="password" name="password" required />
        </div>
        <div class="form-row">
            <label for="confirmPassword">确认密码</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required />
        </div>
        <button type="submit" class="submit-btn">注 册</button>
    </form>
    <p class="footer"><a href="${pageContext.request.contextPath}/user/toLogin">已有账号？返回登录</a></p>
</div>
</body>
</html>
