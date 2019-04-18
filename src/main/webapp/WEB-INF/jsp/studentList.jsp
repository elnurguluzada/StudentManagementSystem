<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Students</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
<body>

        <%--These columns are hidden when the table is initialisez first time: 5 6 7 10 11 12 13 14 20 21 22 23 24--%>
        Id: <input type="checkbox" id="id" onclick="toggleColumn(this, 0)" checked/> <br/>
        Name: <input type="checkbox" id="name" onclick="toggleColumn(this, 1)" checked/> <br/>
        Surname: <input type="checkbox" id="surname" onclick="toggleColumn(this, 2)" checked/> <br/>
        Father name: <input type="checkbox"  onclick="toggleColumn(this, 3)" checked/> <br/>
        Birth Date: <input type="checkbox"  onclick="toggleColumn(this, 4)" checked/> <br/>

        Birth place: <input type="checkbox"  onclick="toggleColumn(this, 5)" /> <br/>
        Living place: <input type="checkbox"  onclick="toggleColumn(this, 6)" /> <br/>
        Official address: <input type="checkbox"  onclick="toggleColumn(this, 7)" /> <br/>
        Email: <input type="checkbox"  onclick="toggleColumn(this, 8)" checked/> <br/>
        Phone number: <input type="checkbox"  onclick="toggleColumn(this, 9)" checked/> <br/>

        Parents phone number: <input type="checkbox"  onclick="toggleColumn(this, 10)" /> <br/>
        Entry year: <input type="checkbox"  onclick="toggleColumn(this, 11)" /> <br/>
        Graduation region: <input type="checkbox"  onclick="toggleColumn(this, 12)" /> <br/>
        Graduation school: <input type="checkbox"  onclick="toggleColumn(this, 13)" /> <br/>
        Entry ID number: <input type="checkbox"  onclick="toggleColumn(this, 14)" /> <br/>

        Entry score: <input type="checkbox"  onclick="toggleColumn(this, 15)" checked/> <br/>
        Section: <input type="checkbox"  onclick="toggleColumn(this, 16)" checked/> <br/>
        Faculty: <input type="checkbox"  onclick="toggleColumn(this, 17)" checked/> <br/>
        Profession: <input type="checkbox"  onclick="toggleColumn(this, 18)" checked/> <br/>
        Group: <input type="checkbox"  onclick="toggleColumn(this, 19)" checked/> <br/>

        Education type: <input type="checkbox"  onclick="toggleColumn(this, 20)" /> <br/>
        Id card number: <input type="checkbox"  onclick="toggleColumn(this, 21)" /> <br/>
        Id card Fin code: <input type="checkbox"  onclick="toggleColumn(this, 22)" /> <br/>
        Gender: <input type="checkbox"  onclick="toggleColumn(this, 23)" /> <br/>
        Social Status: <input type="checkbox"  onclick="toggleColumn(this, 24)" /> <br/>


    <form>


    </form>

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

        function toggleColumn( element, columnNum ) {
            myTable.column(columnNum).visible( element.checked );
        }

    </script>

</body>
</html>


<%--
<a href="/tutor/studentInfo?id=${student.id}">Info</a>
--%>