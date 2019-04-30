<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% request.setAttribute("currentYear", LocalDate.now().getYear()); %>

<form:form action="/tutor/updateStudent" method="post" modelAttribute="student" >

    <form:label path="id">Id</form:label>
    <form:input path="id"  readonly="true"/>
    <br/><br/>
    <form:label path="name">Name</form:label>
    <form:input path="name"/> <br/>
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
    <form:password path="password" readonly="true"/> <br/>
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
    <form:label path="entryYear">Entry Year</form:label>
    <form:select path="entryYear" id="entry-year" >
        <form:option value="${currentYear}"  id="year1" onclick="fillFaculty(this)" />
        <form:option value="${currentYear-1}" id="year2" onclick="fillFaculty(this)"/>
        <form:option value="${currentYear-2}" id="year3" onclick="fillFaculty(this)"/>
        <form:option value="${currentYear-3}" id="year4" onclick="fillFaculty(this)"/>
        <form:option value="${currentYear-4}" id="year5" onclick="fillFaculty(this)"/>
    </form:select>
    <br/><br/>
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
    <form:button>Update</form:button>

</form:form>
