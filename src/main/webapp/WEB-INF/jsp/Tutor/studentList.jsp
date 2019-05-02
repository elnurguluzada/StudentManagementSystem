<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Students</title>
    <%--For datatable--%>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.6/css/buttons.dataTables.min.css">
    <%--For jquery-ui (pop-up)--%>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
</head>
<body>


<label for="section">Section</label>
<select id="section" onchange="filterRows(this)">
    <option value="az" onclick="filerRows(this)">az</option>
    <option value="rus" onclick="flterRows(this)">rus</option>
</select>

<br/>
<br/>
<br/>

<table id="student-list-table" class="display" style="width: 100%">
    <!--display is a class in the imported dataTables.min.css-->
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
        <th>Id card number</th>
        <th>Id card fin code</th>
        <th>Gender</th>
        <th>Social Status</th>
        <th>Action1</th>

        <%--todo social status id--%>
        <%--todo scholarship status--%>
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
        <th>Id card number</th>
        <th>Id card fin code</th>
        <th>Gender</th>
        <th>Social Status</th>
        <th>Action1</th>
        <%--todo social status id--%>
        <%--todo scholarship status--%>

    </tr>
    </tfoot>
</table>

<div id="detailedStudentInformation" title="Student Information"></div>


<%--include jQuery-----------------------------------------------------------------------%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<%--Include dataTables libraries--%>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.colVis.min.js "></script>
<%--popup--%>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>

    $(document).ready( function(){
        $("#detailedStudentInformation").dialog({autoOpen: false});
        $("#update-success").dialog({autoOpen: false});
        $("#update-fail").dialog({autoOpen: false});
        drawTable();
        popup();
    });
    var myTable;
    function drawTable() {

        myTable = $("#student-list-table").DataTable({
            "processing": true,
            "serverSide": true,
            "ordering": true,
            "ajax": "/tutor/getStudents",
            "dom": 'Bfrtip',
            "initComplete": function () { // initializing the table is completed

                $('#student-list-table tbody').on('click', 'button', function () { //is activated when button is clicked

                        $("#detailedStudentInformation").dialog({
                            autoOpen: false
                        });

                        var userId = myTable.row($(this).parents('tr')).data()[0]; //takes value of first column of the row in which button is clicked

                        $("#detailedStudentInformation").load(
                            "/tutor/getStudentInfoPopup?userId=" + userId,  // url from which data will be loaded
                            function () {                                   // function is executed when response comes from url
                                $("#detailedStudentInformation").dialog('open');
                        });
                    });

                // $(".detailedInfo").on('click', function () {
                //     var userId = myTable.row( $(this).parents('tr') ).data()[0];
                //     alert(userId);
                //     $("#detailedStudentInformation").load("/tutor/getStudentInfoPopup?userId=" + userId,
                //     function () {
                //         $("#detailedStudentInformation").dialog('open');
                //     })
                // });

            },
            "buttons": [
                'colvis'
            ],
            "columnDefs": [
                {
                    "targets": [5],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [6],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [7],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [10],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [11],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [12],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [13],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [14],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [20],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [21],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [22],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [23],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [24],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [-1],
                    "visible": true,
                    "defaultContent": "<button class='detailedInfo'>Detailed!</button>"
                }
            ]
        });

    }

    function filterRows(element) {
        var mySearchValue = element.getAttribute("id") + '=' + element.options[element.selectedIndex].value;
        alert(mySearchValue);
        $.post("/tutor/getStudents",
            {
                mySearchValue: mySearchValue
            }
        )
    }


</script>

</body>
</html>