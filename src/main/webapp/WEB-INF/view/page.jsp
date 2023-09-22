<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <th>mno</th>
        <th>memoText</th>
    </tr>
    <c:forEach var="memo" items="${list}">
    <tr>
        <td>${memo.mno}</td>
        <td>${memo.memoText}</td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
