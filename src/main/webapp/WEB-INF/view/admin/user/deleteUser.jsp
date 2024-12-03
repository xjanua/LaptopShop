<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Delete User ${id}</title>
                <!-- Latest compiled and minified CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            </head>

            <body>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-12 mx-auto">
                            <div class="d-flex justify-content-between">
                                <h3>Delete the User with ID: ${id}</h3>
                            </div>
                            <hr>
                            <div class="alert alert-danger">
                                Are you sure you want to delete this user? This action cannot be undone.
                            </div>
                            <form:form method="post" action="/admin/user/delete" modelAttribute="newUser">
                                <div class="mb-3" style="display: none;">
                                    <label class="form-label">ID:</label>
                                    <form:input value="${id}" type="text" class="form-control" path="id" />
                                </div>
                                <button class="btn btn-danger">Confirm</button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </body>

            </html>