<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Current users</title>
</head>
<body>
<c:forEach items="${requestScope.myUsers}" var="currentUser">
    <h1>
        <tr>
            <td>
            <c:out value="${currentUser}"/>
            <td>
        </tr>
    </h1>
</c:forEach>
</body>
</html>