<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Students</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
<body>

        <%--These columns are hidden when the table is initialisez first time: 5 6 7 10 11 12 13 14 20 21 22 23 24--%>
        <%--id of checkboxes refer to column numbers--%>
        Id: <input type="checkbox" id="0" onclick="toggleColumn(this)" checked/> <br/>
        Name: <input type="checkbox" id="1" onclick="toggleColumn(this)" checked/> <br/>
        Surname: <input type="checkbox" id="2" onclick="toggleColumn(this)" checked/> <br/>
        Father name: <input type="checkbox" id="3" onclick="toggleColumn(this)" checked/> <br/>
        Birth Date: <input type="checkbox" id="4" onclick="toggleColumn(this)" checked/> <br/>

        Birth place: <input type="checkbox" id="5" onclick="toggleColumn(this)" /> <br/>
        Living place: <input type="checkbox" id="6" onclick="toggleColumn(this)" /> <br/>
        Official address: <input type="checkbox" id="7" onclick="toggleColumn(this)" /> <br/>
        Email: <input type="checkbox" id="8" onclick="toggleColumn(this)" checked/> <br/>
        Phone number: <input type="checkbox" id="9" onclick="toggleColumn(this)" checked/> <br/>

        Parents phone number: <input type="checkbox" id="10" onclick="toggleColumn(this)" /> <br/>
        Entry year: <input type="checkbox" id="11" onclick="toggleColumn(this)" /> <br/>
        Graduation region: <input type="checkbox" id="12" onclick="toggleColumn(this)" /> <br/>
        Graduation school: <input type="checkbox" id="13" onclick="toggleColumn(this)" /> <br/>
        Entry ID number: <input type="checkbox" id="14" onclick="toggleColumn(this)" /> <br/>

        Entry score: <input type="checkbox" id="15" onclick="toggleColumn(this)" checked/> <br/>
        Section: <input type="checkbox" id="16" onclick="toggleColumn(this)" checked/> <br/>
        Faculty: <input type="checkbox" id="17" onclick="toggleColumn(this)" checked/> <br/>
        Profession: <input type="checkbox" id="18" onclick="toggleColumn(this)" checked/> <br/>
        Group: <input type="checkbox" id="19" onclick="toggleColumn(this)" checked/> <br/>

        Education type: <input type="checkbox" id="20" onclick="toggleColumn(this)" /> <br/>
        Id card number: <input type="checkbox" id="21" onclick="toggleColumn(this)" /> <br/>
        Id card Fin code: <input type="checkbox" id="22" onclick="toggleColumn(this)" /> <br/>
        Gender: <input type="checkbox" id="23" onclick="toggleColumn(this)" /> <br/>
        Social Status: <input type="checkbox" id="24" onclick="toggleColumn(this)" /> <br/>

        <br/>
        <br/>
        <br/>

        <table id="student-list-table" class="display" style="width: 100%" > <!--display is a class in the imported dataTables.min.css-->
        <thead>
        <tr>
            <th>Id</th> <!--0-->
            <th>Name</th>
            <th>Surname</th>
            <th>Father name</th>
            <th>Birth date</th>
            <th>Birth place</th>
            <th>Living place</th>
            <th>Official address</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Parent phone Number</th><!--10th column-->
            <th>Entry year</th>
            <th>Graduation region</th>
            <th>Graduation school</th>
            <th>Entry Id number</th>
            <th>Entry score</th>
            <th>Section</th>
            <th>Faculty</th>
            <th>Profession</th>
            <th>Group</th>
            <th>Education type</th><!--20th column-->
        <%--<th>Education year</th>--%>
            <th>Id card number</th>
            <th>Id card fin code</th>
            <th>Gender</th>
            <th>Social Status</th>

            <%--todo social status id--%>
            <%--todo scholarship status--%>
            <%--<th>Action</th>--%>
        </tr>
        </thead>
        <tfoot>
            <tr>
                <th>Id</th><!--0-->
                <th>Name</th>
                <th>Surname</th>
                <th>Father name</th>
                <th>Birth date</th>
                <th>Birth place</th>
                <th>Living place</th>
                <th>Official address</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Parent phone Number</th> <!--10-->
                <th>Entry year</th>
                <th>Graduation region</th>
                <th>Graduation school</th>
                <th>Entry Id number</th>
                <th>Entry score</th>
                <th>Section</th>
                <th>Faculty</th>
                <th>Profession</th>
                <th>Group</th>
                <th>Education type</th> <!--20-->
                <%--<th>Education year</th>--%>
                <th>Id card number</th>
                <th>Id card fin code</th>
                <th>Gender</th>
                <th>Social Status</th>

                <%--todo social status id--%>
                <%--todo scholarship status--%>
                <%--<th>Action</th>--%>
            </tr>
        </tfoot>
    </table>



<%--include jQuery--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<%--Include dataTables libraries--%>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" ></script>

    <script>

        $(document).ready(
            drawTable()
        );

        var myTable;
        function drawTable(){

            myTable = $("#student-list-table").DataTable({
                "processing": true,
                "serverSide": true,
                "ordering": true,
                "ajax": "/tutor/getStudents",
                "columnDefs": [
                    {
                        "targets": [ 5 ], //todo complete it
                        "visible": false,
                        "searchable": false
                    },
                    {
                        "targets": [ 6 ], //todo complete it
                        "visible": false,
                        "searchable": false
                    },
                    {
                        "targets": [ 7 ], //todo complete it
                        "visible": false,
                        "searchable": false
                    },
                    {
                        "targets": [ 10 ], //todo complete it
                        "visible": false,
                        "searchable": false
                    },
                    {
                        "targets": [ 11 ], //todo complete it
                        "visible": false,
                        "searchable": false
                    },
                    {
                        "targets": [ 12 ], //todo complete it
                        "visible": false,
                        "searchable": false
                    },
                    {
                        "targets": [ 13 ], //todo complete it
                        "visible": false,
                        "searchable": false
                    },
                    {
                        "targets": [ 14 ], //todo complete it
                        "visible": false,
                        "searchable": false
                    },
                    {
                        "targets": [ 20 ], //todo complete it
                        "visible": false,
                        "searchable": false
                    },
                    {
                        "targets": [ 21 ], //todo complete it
                        "visible": false,
                        "searchable": false
                    },
                    {
                        "targets": [ 22 ], //todo complete it
                        "visible": false,
                        "searchable": false
                    },
                    {
                        "targets": [ 23 ], //todo complete it
                        "visible": false,
                        "searchable": false
                    },
                    {
                        "targets": [ 24 ], //todo complete it
                        "visible": false,
                        "searchable": false
                    }

                ]
            });

        }

        function toggleColumn( element ) {
            myTable.column(element.getAttribute("id")).visible( element.checked );
        }

    </script>

</body>
</html>


<%--
<a href="/tutor/studentInfo?id=${student.id}">Info</a>
--%>