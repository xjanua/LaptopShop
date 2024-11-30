<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>LocalHost</title>
            <!-- Latest compiled and minified CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <!-- Latest compiled JavaScript -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

            <!-- <link href="/css/demo.css" rel="stylesheet"> -->
        </head>

        <body>
            <h1>Hello from JSP</h1>
            <h2>
                ${eric} <!-- Display value of 'eric' -->
            </h2>
            <h3>
                ${HoiDanIt} <!-- Display value of 'HoiDanIt' -->
            </h3>

            <!-- Correctly placed the 'class' attribute in the button tag -->
            <button class="btn btn-warning">Submit</button>
        </body>

        </html>