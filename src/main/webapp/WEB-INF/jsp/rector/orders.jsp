<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="/css/main.css"/>
    <style>
        * {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 0;
            font-weight: 400;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-size: 1rem;
            line-height: 1.58;
            color: #333;
            background-color: #f4f4f4;
        }

        body:before {
            height: 50%;
            width: 100%;
            position: absolute;
            top: 0;
            left: 0;
            background: #128ff2;
            content: "";
            z-index: 0;
        }

        h1, h2, h3, h4, h5, h6 {
            margin-top: 20px;
            margin-bottom: 20px;
        }

        h1 {
            font-size: 1.7em;
        }

        a {
            color: #128ff2;
        }

        button {
            box-shadow: none;
            border: 1px solid transparent;
            font-size: 14px;
            outline: none;
            line-height: 100%;
            white-space: nowrap;
            vertical-align: middle;
            padding: 0.6rem 1rem;
            border-radius: 2px;
            transition: all 0.2s ease-in-out;
            cursor: pointer;
            min-height: 38px;
        }


        input {
            font-size: 1rem;
        }

        input[type="file"] {
            border: 1px solid #128ff2;
            padding: 6px;
            max-width: 100%;
        }

        .upload-container {
            max-width: 750px;
            margin-left: auto;
            margin-right: auto;
            background-color: #fff;
            box-shadow: 0 1px 11px rgba(0, 0, 0, 0.27);
            margin-top: 60px;
            min-height: 400px;
            position: relative;
            padding: 20px;
        }

        .upload-header {
            border-bottom: 1px solid #ececec;
        }

        .upload-header h2 {
            font-weight: 500;
        }

    </style>
</head>
<body>
<noscript>
    <h2>Sorry! Your browser doesn't support Javascript</h2>
</noscript>
<div class="upload-container">
    <div class="upload-header">
        <h2>File Upload</h2>
    </div>
    <div class="upload-content">
        <h3>Upload Single File</h3>
        <%--            <form id="singleUploadForm" name="singleUploadForm">--%>
        <%--                <input id="singleFileUploadInput" type="file" name="file" class="file-input" required />--%>
        <%--                <button type="submit" class="primary submit-btn">Submit</button>--%>
        <%--            </form>--%>
        <form action="/rector/file" method="post" enctype="multipart/form-data">
            <input type="file" name="file" placeholder="Upload a file"/> <br/><br/>
            <input type="submit" value="Submit File"/>
        </form>
    </div>
</div>
<%--<script src="/js/main.js"></script>--%>
<script>
    if (${fileExist == true}) {
        alert("There is a file with the same name")
    }
</script>
</body>
</html>