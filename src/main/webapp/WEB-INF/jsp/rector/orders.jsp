<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>

    <form action="/rector/file" method="post" enctype="multipart/form-data" >
        <input type="file" name="file1" placeholder="Upload a file"/> <br/><br/>
        <input type="submit" value="Submit File"/>
    </form>

</body>
</html>
