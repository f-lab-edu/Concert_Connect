<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h1>로그인</h1>
<form action="/login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required ><br>
    <input type="submit" value="Login">
</form>

<p>회원가입 하러가기 => <a href="/signup">회원가입</a></p>
</body>
</html>
