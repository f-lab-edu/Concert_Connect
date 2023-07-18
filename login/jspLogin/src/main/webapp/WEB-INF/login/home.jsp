<!-- home.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%
    if (session.getAttribute("id") == null) {
        response.sendRedirect("http://localhost:8080/jspLogin/login.jsp");

    }
%>

<h1>안녕하세요, <%= session.getAttribute("id") %> 님</h1>
<p><a href="../logout.jsp">로그아웃</a></p>
</body>
</html>
