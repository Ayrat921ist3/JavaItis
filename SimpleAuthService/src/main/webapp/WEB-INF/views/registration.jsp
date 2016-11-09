<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<form method="post" action="registration">
    <p><input type="text" name="username"> Имя пользователя</p>
    <p><input type="password" name="password"> Пароль</p>
    <p><input type="text" name="fio"> ФИО</p>
    <input type="submit" value="sign up">
    <input type="submit" value="sign in" name="signin">
</form>
</body>
</html>