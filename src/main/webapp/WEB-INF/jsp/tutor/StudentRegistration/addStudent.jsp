<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <title>Add Student</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css"><%--For jquery-ui (pop-up)--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css"><!--For Flatpickr-->
    <style> .error{ color: red; } </style>
</head>
<body>
<a href="/tutor/index">Go Back</a>
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
    <form:label path="birthDate">Birth Date</form:label>
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
    Female: <form:radiobutton path="gender" value="F"/> <br/>
    <small><form:errors path="gender" cssClass="error"/> </small>
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

    <hr/>
    
    <form:checkboxes path="socialStatusSet" items="${list}" itemValue="id" itemLabel="name" delimiter="<br/>"/>
    
    <hr/>

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
        <br/><br/>
    <form:label path="entryYear">Entry Year</form:label>
    <form:select path="entryYear" id="entry-year" >
        <form:option value="${currentYear}"  onclick="fillFaculty(this)" />
        <form:option value="${currentYear-1}" onclick="fillFaculty(this)"/>
        <form:option value="${currentYear-2}" onclick="fillFaculty(this)"/>
        <form:option value="${currentYear-3}" onclick="fillFaculty(this)"/>
        <form:option value="${currentYear-4}" onclick="fillFaculty(this)"/>
    </form:select>
        <br/><br/>
    <%--value attribute should be empty not 'Select one', otherwise spring validation will accept
    'Select one' as faculty name--%>
    <form:label path="faculty" >Faculty</form:label>
    <form:select path="faculty" id="faculty-select-id">
        <form:option value="" label="Select one"/>
    </form:select> <br/>
    <small><form:errors path="faculty" cssClass="error"/></small>
        <br/><br/>
    <form:label path="profession" >Profession</form:label>
    <form:select path="profession" id="profession-select-id" >
        <form:option value="" label="Select one"/>
    </form:select> <br/>
    <small><form:errors path="profession" cssClass="error"/></small>
        <br/><br/>
    <form:label path="section">Section</form:label>
    <form:select path="section" id="section-select-id">
        <form:option value="" label="Select one"/>
    </form:select> <br/>
    <small><form:errors path="section" cssClass="error"/> </small>
        <br/><br/>
    <form:label path="educationType">Education Type</form:label>
            <br/>
        <form:label path="educationType" >Eyani</form:label>
        <form:radiobutton path="educationType" value="Eyani"/>
            <br/>
        <form:label path="educationType" >Qiyabi</form:label>
        <form:radiobutton path="educationType" value="Qiyabi"/>
    <small><form:errors path="educationType" cssClass="error"/> </small> <br/>
        <br/><br/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <form:button>Submit</form:button>
    <button type="reset" >Reset</button>
</form:form>

<div id="dialog-success" title="Student Registration">Student was registered successfully</div>
<div id="dialog-fail" title="Student Registration">Error occurred while adding new student</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script><%--Inclode jQuery--%>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script><%--popup--%>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script><%--popup--%>
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script><!--For Flatpickr-->
<script>

    $(document).ready(function () {
        $("#dialog-success").hide();
        $("#dialog-fail").hide();
        dateInput();
        popup();
    });

    function dateInput() {
        flatpickr("#birth-date-id",
            {
                // "altInput": true,
                "dateFormat":"Y/m/d",
                // "altDate": "dd-mm-yyyy",
                "allowInput":true,
                "minDate": "1950/01/01",
                "maxDate": "today",
                // "defaultDate": "2000/01/01",
                "onOpen": function(selectedDates, dateStr, instance) {
                    instance.setDate(instance.input.value, false);
                }
            }
        );
}

    function popup() {

        $( "#dialog-success" ).dialog({ autoOpen: false });
        $( "#dialog-fail" ).dialog({ autoOpen: false });

        if (${requestScope.success== true}){
            $("#dialog-success").show();
            $( "#dialog-success" ).dialog( "open" );
        }
        else if ( ${requestScope.get("success") == false} ) {
            $("#dialog-fail").show();
            $( "#dialog-fail" ).dialog( "open" );
        }

    }

    let year;
    let faculty;
    let profession;
    let section;

    function fillFaculty(element) {
        year = element.getAttribute("value");

        let tutor = $.get("/tutor/getFaculties",
            {
                "year": year
            },
            function (data) {

                $('#faculty-select-id')
                    .find('option')
                    .remove()
                    .end();

                for(let i=0; i<Object.keys(data).length; i++){
                    let option = document.createElement("option");
                    let valueAttr = document.createAttribute("value");
                    valueAttr.value=data[i];
                    let onclickAttr = document.createAttribute("onclick");
                    onclickAttr.value='fillProfession(this)';
                    option.setAttributeNode(valueAttr);
                    option.setAttributeNode(onclickAttr);
                    option.innerText = data[i];
                    document.getElementById("faculty-select-id").add(option);
                }
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

    function fillProfession(element) {
        faculty = element.getAttribute("value");

        $.get("/tutor/getProfessions",
            {
                "year": year,
                "faculty": faculty
            },
            function (data) {

                $('#profession-select-id')
                    .find('option')
                    .remove()
                    .end();

                for(var i=0; i<Object.keys(data).length; i++){
                    var option = document.createElement("option");
                    var valueAttr = document.createAttribute("value");
                    valueAttr.value=data[i];
                    var onclickAttr = document.createAttribute("onclick");
                    onclickAttr.value='fillSection(this)';
                    option.setAttributeNode(valueAttr);
                    option.setAttributeNode(onclickAttr);
                    option.innerText = data[i];
                    document.getElementById("profession-select-id").add(option);
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

                $('#section-select-id')
                    .find('option')
                    .remove()
                    .end();

                for(var i=0; i<Object.keys(data).length; i++){
                    var option = document.createElement("option");
                    var valueAttr = document.createAttribute("value");
                    valueAttr.value=data[i];
                    option.setAttributeNode(valueAttr);
                    option.innerText = data[i];
                    document.getElementById("section-select-id").add(option);
                }
            })

    }

</script>
</body>
