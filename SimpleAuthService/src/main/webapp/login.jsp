<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<form action="login" method="post">
    <p><input type="text" name="username"> Имя пользователя</p>
    <p><input type="password" name="password"> Пароль</p>
    <input type="submit" value="sign in" name="sign in">
    <input type="submit" value="sign up" name="signup">
</form>
</body>
</html>