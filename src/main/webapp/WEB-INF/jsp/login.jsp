<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

    <form action="/login/submit" method="post" >
        <label for="user-name-id">Username</label>
        <input id="user-name-id" type="text" name="username" /> <br/><br/>
        <label for="password-id">Password</label>
        <input id="password-id" type="password" name="password" /> <br/><br/>
        <input type="submit" name="submit" value="Sign in">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>


    <br/><br/>
    <c:if test="${isFailed==true}" >
        <h3> Authentication error occurred </h3>
    </c:if>

</body>
</html>
