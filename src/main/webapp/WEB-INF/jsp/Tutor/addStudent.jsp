<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <title>Add Student</title>
</head>
<h3>Add New Student Data</h3>
<body>

    <form:form action="/tutor/addStudent" method="post" modelAttribute="student" >

        <form:label path="name">Name</form:label>
        <form:input path="name" />
        <br/><br/>
        <form:label path="surname">Surname</form:label>
        <form:input path="surname" />
        <br/><br/>
        <form:label path="birthDate">Birth Date (dd-MM-yyyy)</form:label> <!--todo jQuery-->
        <form:input path="birthDate" />
        <br/><br/>
        <form:label path="birthPlace">Birth Place</form:label>
        <form:input path="birthPlace" />
        <br/><br/>
        <form:label path="email">Email</form:label>
        <form:input path="email" />
        <br/><br/>
        <form:label path="password">Password</form:label>
        <form:input path="password" />
        <br/><br/>
        <form:label path="phoneNumber">Phone Number</form:label>
        <form:input path="phoneNumber" />
        <br/><br/>
        <form:label path="faculty">Faculty</form:label>
        <form:input path="faculty" />
        <br/><br/>
        <form:select path="gender">
            <form:option value="M">Male</form:option>
            <form:option value="F">Female</form:option>
        </form:select>
        <br/><br/>
        <form:label path="fatherName">Father Name</form:label>
        <form:input path="fatherName" />
        <br/><br/>
        <form:label path="livingPlace">Living place</form:label>
        <form:input path="livingPlace" />
        <br/><br/>
        <form:label path="officialHome">Official Address</form:label>
        <form:input path="officialHome" />
        <br/><br/>
        <form:label path="idCardNumber">Id Card Number</form:label>
        <form:input path="idCardNumber" />
        <br/><br/>
        <form:label path="idCardFinCode">Id Card Fin Code</form:label>
        <form:input path="idCardFinCode" />
        <br/><br/>
        <form:label path="socialStatusId">Social Status id</form:label>
        <form:input path="socialStatusId" />
        <br/><br/>
        <form:label path="parentPhoneNumber">Parent Phone Number</form:label>
        <form:input path="parentPhoneNumber" />
        <br/><br/>
        <form:label path="graduatedRegion">Graduated Region</form:label>
        <form:input path="graduatedRegion" />
        <br/><br/>
        <form:label path="graduatedSchool">Graduated School</form:label>
        <form:input path="graduatedSchool" />
        <br/><br/>
        <form:label path="entryIdNumber">Entry Id Number</form:label>
        <form:input path="entryIdNumber" />
        <br/><br/>
        <form:label path="entryScore">Entry Score</form:label>
        <form:input path="entryScore" />
        <br/><br/>
        <form:label path="educationType">Education Type</form:label>
        <form:input path="educationType" />
        <br/><br/>
        <form:label path="presidentialScholarship">Presidential Scholarship</form:label>
        <form:checkbox path="presidentialScholarship"/>
        <br/><br/>
        <form:label path="dovletSifarisli">Dovlet Sifarisli</form:label>
        <form:checkbox path="dovletSifarisli"/>
        <br/><br/>
        ----------------------------------------------------
        <form:label path="entryYear">Entry Year</form:label>
        <form:select path="entryYear">
            <form:option value="2014" onclick="sendYear(2014)"/>
            <form:option value="2015" onclick="sendYear(2015)"/>
            <form:option value="2016" onclick="sendYear(2016)"/>
            <form:option value="2017" onclick="sendYear(2017)"/>
            <form:option value="2018" onclick="sendYear(2018)"/>
            <form:option value="2019" onclick="sendYear(2019)"/>
        </form:select>
        <br/><br/>
        <form:label path="profession">Profession</form:label>
        <form:select path="profession">
            <form:option value="Physics"/>
            <form:option value="Physics teacher"/>
        </form:select>
        <br/><br/>
        <form:label path="section">Section</form:label>
        <form:select path="section">
            <form:option value="az"/>
            <form:option value="rus"/>
        </form:select>
        <br/><br/>
        <%--<form:label path="group">Group</form:label>--%>
        <%--<form:input path="group" />--%>
        <%--<br/><br/>--%>
        <%--<form:label path="scholarshipStatus">Scholarship status</form:label>--%>
        <%--<form:input path="scholarshipStatus" />--%>
        <%--<br/><br/>--%>

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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        function sendYear( year) {
            $post("/tutor/getSections",
                {selectedYear: year},
                function () {
                    alert("Good Job")
                }
            )
        }
    </script>
</body>