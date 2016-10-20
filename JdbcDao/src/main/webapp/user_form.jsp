<%--
  Created by IntelliJ IDEA.
  User: Ayrat
  Date: 20.10.2016
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Add user</title>
</head>
<body>


<form action="users" method="post">
    <p><b>Заполните все поля</b></p>
    <p><input type="text" name="name" value=""> Имя<Br>
        <input type="text" name="city" value=""> Город<Br>
        <input type="text" name="age" value=""> Возраст</p>
    <p><input type="submit" value="Создать"></p>
</form>

<c:forEach items="${requestScope.myUsers}" var="currentUser">
    <h5>
        <tr>
            <td>
                    <c:out value="${currentUser}"/>
            <td>
        </tr>
    </h5>
</c:forEach>

</body>
</html>
