<!-- login.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="user" method="POST">
    <input type="hidden" name="action" value="login">
    <label for="id">ID:</label>
    <input type="text" name="id" required><br>
    <label for="password">Password:</label>
    <input type="password" name="password" required><br>
    <input type="submit" value="로그인">
</form>
<a href="signup.jsp">회원가입</a>
</body>
</html>
