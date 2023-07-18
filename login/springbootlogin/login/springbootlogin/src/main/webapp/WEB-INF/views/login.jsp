<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>
<h1>로그인</h1>
<form action="/login" method="post">
    <label for="id">id:</label>
    <input type="text" id="id" name="id" required><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>
    <input type="submit" value="Login">
</form>

<p>회원가입으로 이동하기 <a href="/signup">회원 가입</a></p>
</body>
</html>