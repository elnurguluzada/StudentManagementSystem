<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Students</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"><%--For datatable--%>
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.6/css/buttons.dataTables.min.css"><%--For datatable buttons--%>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css"><%--For jquery-ui (pop-up)--%>
    <link rel="stylesheet" href="https://cdn.datatables.net/plug-ins/1.10.19/sorting/numeric-comma.js"><%--For jquery-ui (pop-up)--%>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"><%--For jquery-ui (pop-up)--%>
</head>
<body>
<% request.setAttribute("currentYear", LocalDate.now().getYear() ); %>



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
        <th>Action</th>

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
        <th>Action</th>
        <%--todo social status id--%>
        <%--todo scholarship status--%>

    </tr>
    </tfoot>
</table>


<table id="groups"></table>


<label for="groupAmount">Select the amount of groups</label>
<select id ="groupAmount" name="groupAmount">
    <option value="1" >1</option>
    <option value="2">2</option>
    <option value="3">3</option>
    <option value="4">4</option>
    <option value="5">5</option>
    <option value="6">6</option>
    <option value="7">7</option>
    <option value="8">8</option>
    <option value="9">9</option>
    <option value="10">10</option>
    <option value="11">11</option>
    <option value="12">12</option>
    <option value="13">13</option>
    <option value="14">14</option>
    <option value="15">15</option>
</select> <br/>
<button id="ajaxSubmit" value="Select"  >Create Groups</button>

<br/><br/>

<button id="divide-students" onclick="$('#mini-form').toggle()">Fill groups</button>
<div id="mini-form">
    <form>
        <label for="entry-year">Entry Year</label>
        <select id="entry-year" >
            <option value="${currentYear}"  onclick="fillFaculty(this)" >${currentYear}</option>
            <option value="${currentYear-1}" onclick="fillFaculty(this)" >${currentYear-1}</option>
            <option value="${currentYear-2}" onclick="fillFaculty(this)" >${currentYear-2}</option>
            <option value="${currentYear-3}" onclick="fillFaculty(this)" >${currentYear-3}</option>
            <option value="${currentYear-4}" onclick="fillFaculty(this)" >${currentYear-4}</option>
        </select>
        <br/><br/>
        <label >Faculty</label>
        <select id="faculty-select-id">
            <option value="" label="Select one"/>
        </select> <br/>
        <br/><br/>
        <label >Profession</label>
        <select id="profession-select-id" >
            <option value="" label="Select one"/>
        </select> <br/>
        <br/><br/>
        <label>Section</label>
        <select id="section-select-id">
            <option value="" label="Select one"/>
        </select> <br/>
        <br/><br/>
        <label path="educationType">Education Type</label> <br/>
        <label path="educationType" >Eyani</label>
        <radiobutton path="educationType" value="Eyani"/> <br/>
        <label path="educationType" >Qiyabi</label>
        <radiobutton path="educationType" value="Qiyabi"/>
        <br/><br/>

    </form>
</div>


<div id="detailedStudentInformation" title="Student Information"></div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script><%--jQuery--%>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script><%--dataTables libraries--%>
<script src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script><%--buttons for dataTables--%>
<script src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.colVis.min.js "></script><%--buttons for dataTables--%>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script><%--popup--%>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script><%--popup--%>
<script>

    $(document).ready(function () {
        $("#detailedStudentInformation").dialog({autoOpen: false});
        createGroups();
        drawTable();
        $("#mini-form").hide();
    });

    function drawTable() {

        // Setup - add a text input to each footer cell
        $('#student-list-table tfoot th').each( function () {

            var title = $(this).text();
            if (title === 'Name' || title === 'Surname' || title === 'Father name' || title === 'Birth date' || title === 'Birth place' ||
                title === 'Living place' || title === 'Entry year' || title === 'Graduation region' || title === 'Entry score' ||
                title === 'Faculty' || title === 'Profession' || title === 'Group' || title === 'Section'){

                $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
            }
            else{
                $(this).html( '<input type="text" hidden placeholder="Search '+title+'" />' );
            }
        } );






        var myTable = $("#student-list-table").DataTable({
            "processing": true,
            "serverSide": true,
            "order": true,
            "ajax": "/tutor/getNotGroupedStudents",
            "dom": 'Bfrtip',
            // "initComplete": , it works when the table is initialized for the 1st time, but not when you move to next page
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
                    "defaultContent": "<button class='detailedInfo'>Detailed!</button> &nbsp " +
                        "<a><button class='updateInfo'>Update!</button></a>"
                }
            ]
        });





        //The draw event is fired whenever the table is redrawn on the page, at the same point as drawCallback.
        myTable.on('draw', function () {

            $(".detailedInfo").click(function () {

                var userId = myTable.row($(this).parents('tr')).data()[0]; //takes value of first column of the row in which button is clicked
                $("#detailedStudentInformation").load(
                    "/tutor/getStudentInfoPopup/" + userId,  // url from which data will be loaded
                    function () {                                   // function is executed when response comes from url
                        $("#detailedStudentInformation").dialog('open');
                    });

            });

            $(".updateInfo").click(function () {

                var userId = myTable.row($(this).parents('tr')).data()[0]; //takes value of first column of the row in which button is clicked
                window.location.href = "/tutor/updateStudent/" + userId;

                // var xhr = new XMLHttpRequest();
                // xhr.open('GET',"/tutor/updateStudent?userId="+userId,false);
                // xhr.send();

            });
        });

        // Apply the search
        myTable.columns().every( function () {
            var that = this;

            $( 'input', this.footer() ).on( 'keyup change', function () {
                if ( that.search() !== this.value ) {
                    that
                        .search( this.value )
                        .draw();
                }
            } );
        } );

    }

    function fillFaculty(element) {
        year = element.getAttribute("value");

        $.get("/tutor/getFaculties",
            {
                "year": year
            },
            function (data) {
                alert(data);

                $('#faculty-select-id')
                    .find('option')
                    .remove()
                    .end();

                for (var i = 0; i < Object.keys(data).length; i++) {
                    var option = document.createElement("option");
                    var valueAttr = document.createAttribute("value");
                    valueAttr.value = data[i];
                    var onclickAttr = document.createAttribute("onclick");
                    onclickAttr.value = 'fillProfession(this)';
                    option.setAttributeNode(valueAttr);
                    option.setAttributeNode(onclickAttr);
                    option.innerText = data[i];
                    document.getElementById("faculty").add(option);
                }
            }
        );

    }

    function fillProfession(element) {
        faculty = element.getAttribute("value");

        $.get("/tutor/getProfessions",
            {
                "year": year,
                "faculty": faculty
            },
            function (data) {
                alert(data);

                $('#profession-select-id')
                    .find('option')
                    .remove()
                    .end();

                for (var i = 0; i < Object.keys(data).length; i++) {
                    var option = document.createElement("option");
                    var valueAttr = document.createAttribute("value");
                    valueAttr.value = data[i];
                    var onclickAttr = document.createAttribute("onclick");
                    onclickAttr.value = 'fillSection(this)';
                    option.setAttributeNode(valueAttr);
                    option.setAttributeNode(onclickAttr);
                    option.innerText = data[i];
                    document.getElementById("profession").add(option);
                }
            });
    }

    function fillSection(element) {
        profession = element.getAttribute("value");
        $.get("/tutor/getSections",
            {
                "year": year,
                "faculty": faculty,
                "profession": profession
            },
            function (data) {
                alert(data);

                $('#section-select-id')
                    .find('option')
                    .remove()
                    .end();

                for (var i = 0; i < Object.keys(data).length; i++) {
                    var option = document.createElement("option");
                    var valueAttr = document.createAttribute("value");
                    valueAttr.value = data[i];
                    var onclickAttr = document.createAttribute("onclick");
                    onclickAttr.value = 'fillSection(this)';
                    option.setAttributeNode(valueAttr);
                    option.setAttributeNode(onclickAttr);
                    option.innerText = data[i];
                    document.getElementById("section").add(option);
                }
            })

    }

    function createGroups(){

        $("#ajaxSubmit").click( function(){
            var sel = document.getElementById("groupAmount");
            alert(sel.value);

            //todo get or post?
            $.get("/tutor/createGroup",
                {
                    "numberOfGroups": "sel.value"
                },
                function (data) {
                    alert(data);
                }
            )

        });

    }

</script>

</body>
</html>

