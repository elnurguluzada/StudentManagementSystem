<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Groups</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"><%--For datatable--%>
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.6/css/buttons.dataTables.min.css"><%--For datatable buttons--%>
</head>
<body>

<table id="group-list-table" class="display" style="width: 100%"> <!--display is a class in the imported dataTables.min.css-->
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Year</th>
        <th>Faculty</th>
        <th>Profession</th>
        <th>Section</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Year</th>
        <th>Faculty</th>
        <th>Profession</th>
        <th>Section</th>
    </tr>
    </tfoot>
</table>

<div id="detailedStudentInformation" title="Student Information"></div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script><%--jQuery--%>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script><%--dataTables libraries--%>
<script src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script><%--buttons for dataTables--%>
<script src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.colVis.min.js "></script><%--buttons for dataTables--%>
<script>

    $(document).ready(function () {
        drawTable();
    });

    function drawTable() {

        var myTable = $("#group-list-table").DataTable({
            "processing": true,
            "serverSide": true,
            "ordering": true,
            "ajax": "/tutor/getGroups",
            "dom": 'Bfrtip',
            // "initComplete": , it works when the table is initialized for the 1st time, but not when you move to next page
            "buttons": [
                'colvis'
            ],
            "columnDefs": [
                {
                    "targets": [-1],
                    "visible": true,
                    "defaultContent": "<button class='detailed-button'>Detailed!</button>"
                }
            ]
        });

        //The draw event is fired whenever the table is redrawn on the page, at the same point as drawCallback.
        myTable.on('draw', function () {

            $(".detailed-button").click(function () {

                var groupId = myTable.row($(this).parents('tr')).data()[0]; //takes value of first column of the row in which button is clicked
                // $("#detailedStudentInformation").load(
                //     "/tutor/getStudentInfoPopup/" + userId,  // url from which data will be loaded
                //     function () {                                   // function is executed when response comes from url
                //         $("#detailedStudentInformation").dialog('open');
                //     });

            });

        });

        // Setup - add a text input to each footer cell
        $('#student-list-table tfoot th').each( function () {
            var title = $(this).text();
            if (title === 'Name' || title === 'Year' || title === 'Faculty' || title === 'Profession' || title === 'Section'){
                $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
            }
        } );

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

</script>

</body>
</html>
