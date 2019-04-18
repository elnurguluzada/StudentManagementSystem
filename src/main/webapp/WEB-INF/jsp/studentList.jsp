<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
<body>


    <table id="student-list-table" class="display" style="width: 80%" > <!--display is a class in the imported dataTables.min.css-->
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Surname</th>
        </tr>
        </thead>
        <tfoot>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Surname</th>
            </tr>
        </tfoot>
    </table>



<%--include jQuery--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<%--Include dataTables libraries--%>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" ></script>

<script>
    $(document).ready(function(){
        $("#student-list-table").DataTable({
            "processing": true,
            "serverSide": true,
            "ordering": true,
            "ajax": "/tutor/getStudents"
        });
    });

</script>

</body>
</html>


<%--
<c:forEach items="${studentList}" var="student">
    <tr>
    <td>${student.id}</td>
    <td>${student.name}</td>
    <td>${student.surname}</td>
    <td>
<a href="/tutor/studentInfo?id=${student.id}">Info</a> &nbsp;
--%>