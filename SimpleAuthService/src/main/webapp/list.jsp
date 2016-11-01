<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User cars</title>
</head>
<body>
<form action="list" method="post">
    <p><input type="submit" value="logout" name="logout"></p>
    <p><input type="text" name="model"> Модель</p>
    <p><input type="text" name="mileage"> Пробег</p>
    <p><input type="submit" value="add car" name="addcar"></p>
</form>

<tr>
    <td>  car_id  </td>
    <td>  model  </td>
    <td>  mileage  </td>
    <td>  owner_id  </td>
</tr>
<c:forEach items="${requestScope.myCars}" var="currentCar">
    <%--<tr>--%>
        <%--<td><c:out value="car cycle"/></td>--%>
    <%--</tr>--%>
        <h3>
            <tr>
                <td><c:out value="${currentCar.id}"/></td>
                <td><c:out value="${currentCar.model}"/></td>
                <td><c:out value="${currentCar.mileage}"/></td>
                <td><c:out value="${currentCar.owner_id}"/></td>
            </tr>
        </h3>
</c:forEach>

</body>
</html>