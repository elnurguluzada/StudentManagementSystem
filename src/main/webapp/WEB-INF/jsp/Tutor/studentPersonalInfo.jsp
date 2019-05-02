<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style>
        .error{ color: red; }
    </style>
</head>

<% request.setAttribute("currentYear", LocalDate.now().getYear()); %>

<form:form action="/tutor/updateStudent" method="post" modelAttribute="student" >
    <form:hidden path="id" />
    <br/><br/>
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
    <form:hidden path="password" />
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
    <br/><br/>
    <form:label path="entryYear">Entry Year</form:label>
    <form:select path="entryYear" id="entry-year" >
        <form:option value="${currentYear}"  onclick="fillFaculty(this)" />
        <form:option value="${currentYear-1}" onclick="fillFaculty(this)" />
        <form:option value="${currentYear-2}" onclick="fillFaculty(this)"/>
        <form:option value="${currentYear-3}" onclick="fillFaculty(this)"/>
        <form:option value="${currentYear-4}" onclick="fillFaculty(this)"/>
        <form:option value="${currentYear-5}" onclick="fillFaculty(this)"/>
        <form:option value="${currentYear-6}" onclick="fillFaculty(this)"/>
        <form:option value="${currentYear-7}" onclick="fillFaculty(this)"/>
    </form:select>
    <br/><br/>
    <%--value attribute should be empty not 'Select one', otherwise spring validation will accept
    'Select one' as faculty name--%>
    <form:label path="faculty" >Faculty</form:label>
    <form:select path="faculty" id="faculty-select-id">
        <form:option value="${student.faculty}"/>
    </form:select> <br/>
    <small><form:errors path="faculty" cssClass="error"/></small>
    <br/><br/>
    <form:label path="profession" >Profession</form:label>
    <form:select path="profession" id="profession-select-id" >
        <form:option value="${student.profession}" />
    </form:select> <br/>
    <small><form:errors path="profession" cssClass="error"/></small>
    <br/><br/>
    <form:label path="section">Section</form:label>
    <form:select path="section" id="section-select-id">
        <form:option value="${student.section}" />
    </form:select> <br/>
    <small><form:errors path="section" cssClass="error"/> </small>
    <br/><br/>
        <form:label path="educationType">Education Type</form:label> <br/>
        <form:label path="educationType" >Eyani</form:label>
        <form:radiobutton path="educationType" value="Eyani"/> <br/>
        <form:label path="educationType" >Qiyabi</form:label>
        <form:radiobutton path="educationType" value="Qiyabi"/>
    <br/><br/>
    <small><form:errors path="educationType" cssClass="error"/> </small> <br/>
    <form:button>Update</form:button>
</form:form>

<%--<div id="update-success" title="Student update">Student was updated successfully</div>--%>
<%--<div id="update-fail" title="Student update">An error occurred while updating</div>--%>

<%--include jQuery-----------------------------------------------------------------------%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<%--popup--%>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>

    $(document).ready(function () {
        // $("#update-success").hide();
        // $("#update-fail").hide();

        setEntryYear();
        popup();
        dateInput();
        alert('ready5');
    });

    function setEntryYear() {
        var select = document.getElementById("entry-year");
        for (var i = 0; i < select; i++){
            var option = select.options[i];
            if ( parseInt(option.value) === parseInt(${student.entryYear})) {
                option.selected = true;
            }
        }
    }

    function dateInput() {
        flatpickr("#birth-date-id",
            {
                // "altInput": true,
                "dateFormat":"Y/m/d",
                // "altDate": "dd-mm-yyyy",
                "allowInput":true,
                "minDate": "1950/01/01",
                "maxDate": "today",
                "onOpen": function(selectedDates, dateStr, instance) {
                    instance.setDate(instance.input.value, false);
                }
            }
        );
    }

    function popup() {
        // $("#dialog-success" ).dialog({ autoOpen: false });
        // $("#dialog-fail" ).dialog({ autoOpen: false });

        <c:if test="${success == true}" >
            // $("#update-success").show();
            alert("Student was updated successfully");
            // $("#dialog-success").dialog("open");
        </c:if>
        <c:if test="${success == false}" >
            // $("#update-fail").show();
            alert("An error occurred while updating");
            // $("#dialog-fail").dialog("open");
        </c:if>

    }

    var year;
    var faculty;
    var profession;
    var section;

    function fillFaculty(element) {
        year = element.getAttribute("value");

        $.post("/tutor/getFaculties",
            {
                "year": year
            },
            function (data) {
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

        $.post("/tutor/getProfessions",
            {
                "year": year,
                "faculty": faculty
            },
            function (data) {
                $("#profession-select-id").html(data);
            });
    }

    function fillSection(element) {
        profession = element.getAttribute("value");
        $.post("/tutor/getSections",
            {
                "year": year,
                "faculty":faculty,
                "profession":profession
            },
            function (data) {
                $("#section-select-id").html(data);
            })

    }

</script>
