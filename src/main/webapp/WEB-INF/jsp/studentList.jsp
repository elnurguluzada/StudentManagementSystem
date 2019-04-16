<%--
  Created by IntelliJ IDEA.
  User: elnurquluzade
  Date: 2019-04-15
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<div>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Surname</th>
        </tr>

        <c:forEach items="${studentList}" var="student">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.surname}</td>
                <td>
                    <a href="/tutor/studentInfo?id=${student.id}">Info</a> &nbsp;
                </td>
            </tr>
        </c:forEach>
    </table>


</div>

</body>
</html>
