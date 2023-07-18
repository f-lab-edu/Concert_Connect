<!-- signup.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<h1>Sign up</h1>
<form action="user" method="POST">
    <input type="hidden" name="action" value="signup">
    <label for="id">ID:</label>
    <input type="text" name="id" required><br>
    <label for="password">Password:</label>
    <input type="password" name="password" required><br>
    <input type="submit" value="회원가입">
</form>
<a href="login.jsp">로그인</a>
</body>
</html>
