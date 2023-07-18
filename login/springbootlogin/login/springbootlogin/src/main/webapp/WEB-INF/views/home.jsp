
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<h1>안녕하세요,${pageContext.request.userPrincipal.name}님</h1>
<a href="/logout">로그아웃</a>
</body>
</html>