<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Current users</title>
</head>
<body>
<c:forEach items="${requestScope.myUsers}" var="currentUser">
    <c:forEach items="${requestScope.myCars}" var="currentCar">
        <c:if test="${currentUser.id == currentCar.owner_id}">
            <h1>
                <tr>
                    <td><c:out value="${currentUser.id}"></c:out></td>
                    <td><c:out value="${currentUser.fio}"></c:out></td>
                    <td><c:out value="${currentUser.username}"></c:out></td>
                </tr>
            </h1>
        </c:if>
    </c:forEach>
</c:forEach>

</body>
</html>