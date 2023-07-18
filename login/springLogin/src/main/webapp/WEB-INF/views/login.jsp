
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>
<h1>로그인</h1>
<form action="/login" method="post">
    <label for="id">ID:</label>
    <input type="text" id="id" name="id" required><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>
    <input type="submit" value="로그인">
</form>
<input type="button" value="회원가입" onClick="location.href='signup'">
</body>
</html>