<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <title>Add Student</title>
    <%--For jquery-ui (pop-up)--%>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
    <!--For Flatpickr-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

    <style>
        .error{ color: red; }
    </style>
</head>
<body>

<h3>Add New Student Data</h3>
<% request.setAttribute("currentYear", LocalDate.now().getYear() ); %>

<form:form action="/tutor/addStudent" method="post" modelAttribute="student" >

    <form:label path="name">Name</form:label>
    <form:input path="name" /> <br/>
    <small><form:errors path="name" cssClass="error"/></small>
    <br/><br/>
    <form:label path="surname">Surname</form:label>
    <form:input path="surname" /> <br/>
    <small><form:errors path="surname" cssClass="error"/></small>
    <br/><br/>
    <form:label path="birthDate">Birth Date <small>(dd/MM/yyyy)</small></form:label> <!--todo jQuery-->
    <form:input path="birthDate" id="birth-date-id"/> <br/>
    <small><form:errors path="birthDate" cssClass="error"/></small>
    <br/><br/>
    <form:label path="birthPlace">Birth Place</form:label>
    <form:input path="birthPlace" /> <br/>
    <small><form:errors path="birthPlace" cssClass="error"/></small>
    <br/><br/>
    <form:label path="email">Email</form:label>
    <form:input path="email" /> <br/>
    <small><form:errors path="email" cssClass="error"/></small>
    <br/><br/>
    <form:label path="password">Password</form:label>
    <form:password path="password" /> <br/>
    <small><form:errors path="password" cssClass="error"/></small>
    <br/><br/>
    <form:label path="phoneNumber">Phone Number</form:label>
    <form:input path="phoneNumber" /> <br/>
    <small><form:errors path="phoneNumber" cssClass="error"/></small>
    <br/><br/>
    <form:label path="gender">Gender</form:label> <br/>
    Male: <form:radiobutton path="gender" value="M"/>
    Female: <form:radiobutton path="gender" value="F"/>
    <br/><br/>
    <form:label path="fatherName">Father Name</form:label>
    <form:input path="fatherName" /> <br/>
    <small><form:errors path="fatherName" cssClass="error"/></small>
    <br/><br/>
    <form:label path="livingPlace">Living place</form:label>
    <form:input path="livingPlace" /> <br/>
    <small><form:errors path="livingPlace" cssClass="error"/></small>
    <br/><br/>
    <form:label path="officialHome">Official Address</form:label>
    <form:input path="officialHome" /> <br/>
    <small><form:errors path="officialHome" cssClass="error"/></small>
    <br/><br/>
    <form:label path="idCardNumber">Id Card Number</form:label>
    <form:input path="idCardNumber" /> <br/>
    <small><form:errors path="idCardNumber" cssClass="error"/></small>
    <br/><br/>
    <form:label path="idCardFinCode">Id Card Fin Code</form:label>
    <form:input path="idCardFinCode" /> <br/>
    <small><form:errors path="idCardFinCode" cssClass="error"/></small>
    <br/><br/>
    <label>Social status: </label> <br/><br/>
    <label >Sehid ailesi: </label>
    <form:checkbox path="socialStatusSet" value="1" /> <br/>
    <label>Qacqin: </label>
    <form:checkbox path="socialStatusSet" value="2" /> <br/>
    <label>Asagi teminatli: </label>
    <form:checkbox path="socialStatusSet" value="3" />
    <br/><br/>
    <form:label path="parentPhoneNumber">Parent Phone Number</form:label>
    <form:input path="parentPhoneNumber" /> <br/>
    <small><form:errors path="parentPhoneNumber" cssClass="error"/></small>
    <br/><br/>
    <form:label path="graduatedRegion">Graduated Region</form:label>
    <form:input path="graduatedRegion" />  <br/>
    <small><form:errors path="graduatedRegion" cssClass="error"/></small>
    <br/><br/>
    <form:label path="graduatedSchool">Graduated School</form:label>
    <form:input path="graduatedSchool" /> <br/>
    <small><form:errors path="graduatedSchool" cssClass="error"/></small>
    <br/><br/>
    <form:label path="entryIdNumber">Entry Id Number</form:label>
    <form:input path="entryIdNumber" /> <br/>
    <small><form:errors path="entryIdNumber" cssClass="error"/></small>
    <br/><br/>
    <form:label path="entryScore">Entry Score</form:label>
    <form:input path="entryScore" /> <br/>
    <small><form:errors path="entryScore" cssClass="error"/></small>
    <br/><br/>
    <form:label path="presidentialScholarship">Presidential Scholarship</form:label>
    <form:checkbox path="presidentialScholarship"/>
    <br/><br/>
    <form:label path="dovletSifarisli">Dovlet Sifarisli</form:label>
    <form:checkbox path="dovletSifarisli"/>
    <br/><br/>
    ----------------------------------------------------
    <%-- It would be better if curre --%>
    <br/><br/>
    <form:label path="entryYear">Entry Year</form:label>
    <form:select path="entryYear" id="entry-year" >
        <form:option value="${currentYear}"  id="year1" onclick="fillFaculty(this)" />
        <form:option value="${currentYear-1}" id="year2" onclick="fillFaculty(this)"/>
        <form:option value="${currentYear-2}" id="year3" onclick="fillFaculty(this)"/>
        <form:option value="${currentYear-3}" id="year4" onclick="fillFaculty(this)"/>
        <form:option value="${currentYear-4}" id="year5" onclick="fillFaculty(this)"/>
    </form:select>
    <br/><br/>
    <%--todo take from DB--%>
    <form:label path="faculty" >Faculty</form:label>
    <form:select path="faculty" id="faculty-select-id">
        <form:option value="Select one" />
    </form:select>
    <small><form:errors path="faculty" cssClass="error"/></small>
    <br/><br/>
    <form:label path="profession" >Profession</form:label>
    <form:select path="profession" id="profession-select-id" >
        <form:option value="Select one"/>
    </form:select>
    <br/><br/>
    <form:label path="section">Section</form:label>
    <form:select path="section" id="section-select-id">
        <form:option value="Select one"/>
    </form:select>
    <br/><br/>
    <%--todo What if there isn't qiyabi type in that faculty--%>
    <form:label path="educationType">Education Type</form:label>
    <form:select path="educationType" id="education-type-select-id">
        <form:option value="Select one" />
    </form:select>
    <br/><br/>
    <%--
        private String fatherName;
        private LocalDate birthDate;
        private String birthPlace;
        private String livingPlace;
        private String officialHome;
        private String idCardNumber;
        private String idCardFinCode;
        private String socialStatusId;
        private String parentPhoneNumber;
        private String graduatedRegion;
        private String graduatedSchool;
        private int entryIdNumber;
        private int entryScore;
        private String educationType;
        private boolean presidentialScholarship;            // true -> prezident teqaudcusu
        private boolean dovletSifarisli;                    // true -> dovlet sifarisli false -> odenisli
        private int educationYear;
        private String profession;
        private String section;
        private String group;
        private int scholarshipStatus;
    --%>

    <form:button>Submit</form:button>
</form:form>

<div id="dialog-success" title="Student Registration">Student was registered successfully</div>
<div id="dialog-fail" title="Student Registration">Err</div>

<%--Inclode jQuery--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script
<%--popup--%>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<%--Flat date--%>

<%-- Pop-up after submitting --%>
<script>

    $(document).ready(function () {
        dateInput();
        popup();
    });

    function dateInput() {
        flatpickr("#birth-date-id",
            {
                "dateFormat":"d/m/Y",
                "allowInput":true,
                "minDate": "01/01/1950",
                "maxDate": "today",
                "onOpen": function(selectedDates, dateStr, instance) {
                    instance.setDate(instance.input.value, false);
                }
            }
        );
}

    function popup() {
        $( "#dialog-success" ).dialog({ autoOpen: false });
        $( "#dialog-fail" ).dialog({ autoOpen: false });

        console.log( ${requestScope.success} );
        console.log( ${requestScope.success== true} );

        if (${requestScope.success== true}){
            $( "#dialog-success" ).dialog( "open" );
        }
        else if ( ${requestScope.get("success") == false} ) {
            $( "#dialog-fail" ).dialog( "open" );
        }

    }

    var year;
    var faculty;
    var profession;
    var section;

    function fillFaculty(element) {
        year = element.getAttribute("value");
        alert(year);

        $.post("/tutor/getFaculties",
            {
                "year": year
            },
            function (data) {
                alert("came back!");
                $("#faculty-select-id").html(data);
        }
        );


        <%--var facultySelect = document.getElementById("facultyId");--%>

        <%--for(var i=0; i<facultyArray.length; i++){--%>
        <%--var option = document.createElement("option");--%>
        <%--var valueAttr = document.createAttribute("value");--%>
        <%--var onclickAttr = document.createAttribute("onclick");--%>
        <%--valueAttr.value=facultyArray[i];--%>
        <%--onclickAttr.value='fillProfession(this)';--%>
        <%--option.setAttributeNode(valueAttr);--%>
        <%--option.setAttributeNode(onclickAttr);--%>
        <%--option.innerText = facultyArray[i];--%>
        <%--facultySelect.add(option);--%>
        <%--}--%>

    }

    function fillProfession(element){
        faculty = element.getAttribute("value");
        alert(faculty);

        $.post("/tutor/getProfessions",
            {
                "year": year,
                "faculty": faculty
            },
        function (data) {
            alert("came back");
            $("#profession-select-id").html(data);
        });

        // var professionArray = ['Physics', 'Physics teacher'];
        //
        // var professionSelect = document.getElementById("professionId");
        //
        // for(var i=0; i<professionArray.length; i++){
        //     var option = document.createElement("option");
        //     var valueAttr = document.createAttribute("value");
        //     var onclickAttr = document.createAttribute("onclick");
        //     valueAttr.value=professionArray[i];
        //     onclickAttr.value='fillSection(this)';
        //     option.setAttributeNode(valueAttr);
        //     option.setAttributeNode(onclickAttr);
        //     option.innerText = professionArray[i];
        //     professionSelect.add(option);
        // }
    }
    
    function fillSection(element) {
        profession = element.getAttribute("value");
        alert(profession);

        $.post("/tutor/getSections",
            {
                "year": year,
                "faculty":faculty,
                "profession":profession
            },
            function (data) {
            alert("came back");
                $("#section-select-id").html(data);
            })

        // var sectionArray = ['Az', 'Rus'];
        //
        // var sectionSelect = document.getElementById("sectionId");
        //
        // for(var i=0; i<sectionArray.length; i++){
        //     var option = document.createElement("option");
        //     var valueAttr = document.createAttribute("value");
        //     var onclickAttr = document.createAttribute("onclick");
        //     valueAttr.value=sectionArray[i];
        //     onclickAttr.value='fillEducationType(this)';
        //     option.setAttributeNode(valueAttr);
        //     option.setAttributeNode(onclickAttr);
        //     option.innerText = sectionArray[i];
        //     sectionSelect.add(option);
        // }
    }

    function fillEducationType(element) {
        section = element.getAttribute("value");
        // alert(section);

        var educationTypeArray = ['Eyani', 'Qiyabi'];

        var educationTypeSelect = document.getElementById("educationTypeId");

        for(var i=0; i<educationTypeArray.length; i++){
            var option = document.createElement("option");
            var valueAttr = document.createAttribute("value");
            valueAttr.value=educationTypeArray[i];
            option.setAttributeNode(valueAttr);
            option.innerText = educationTypeArray[i];
            educationTypeSelect.add(option);
        }
    }

</script>

<%--Getting sections accordinging to selected year WIP --%>
<%--<script>--%>
    <%--$(document).ready(function () {--%>
        <%--var currentYear = new Date().getFullYear();--%>

        <%--$("#entry-year").change(function(){--%>

            <%--var year = $(this).find("option:selected").attr("year");--%>

            <%--$.post("/tutor/getSectionList",--%>
                <%--{selectedYear: year},--%>
                <%--function () {--%>
                    <%--alert("Good Job")--%>
                <%--}--%>
            <%--)--%>
        <%--});--%>
    <%--})--%>
<%--</script>--%>

</body>
