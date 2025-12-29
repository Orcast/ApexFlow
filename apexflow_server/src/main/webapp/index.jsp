<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户认证系统</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .welcome-container {
            text-align: center;
            color: white;
            background-color: rgba(255, 255, 255, 0.1);
            padding: 40px;
            border-radius: 10px;
            backdrop-filter: blur(10px);
        }
        .btn {
            display: inline-block;
            padding: 12px 30px;
            margin: 10px;
            background-color: white;
            color: #667eea;
            text-decoration: none;
            border-radius: 25px;
            font-weight: bold;
            transition: all 0.3s ease;
        }
        .btn:hover {
            background-color: #f8f9fa;
            transform: translateY(-2px);
            box-shadow: 0 4px 15px rgba(0,0,0,0.2);
        }
        h1 { margin-bottom: 30px; }
    </style>
</head>
<body>
<div class="welcome-container">
    <h1>欢迎使用用户认证系统</h1>
    <p>这是一个演示用户注册和登录功能的示例系统</p>
    <div style="margin-top: 30px;">
        <a href="${pageContext.request.contextPath}/login" class="btn">登录</a>
        <a href="${pageContext.request.contextPath}/register" class="btn">注册</a>
    </div>
</div>
</body>
</html>