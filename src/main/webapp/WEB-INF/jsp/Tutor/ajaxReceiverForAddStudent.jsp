<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:forEach items="${optionsSet}" var="item">
    <option onclick="${functionToCall}" value="<c:out value='${item}'/>" ><c:out value='${item}'/> </option>
</c:forEach>
