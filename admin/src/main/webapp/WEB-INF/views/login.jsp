<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Phonify</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="<s:url value="/resources/main.css" />">
        <link rel="stylesheet" href="<s:url value="/resources/login.css" />">
    </head>
    <body>
        <div id="wrapper" class="container">
            <header>
                <a href="<s:url value='/' />"><img src="<s:url value='/resources/images/phonify.svg' />"></a>
            </header>
            <section>
                <div class="wrapper">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <div class="well">
                        <form class="form-horizontal" method="post" action="<c:url value='/login' />">
                            <div class="form-group row">
                                <label class="col-md-3 control-label" for="username">User:</label>
                                <div class="col-md-9">
                                    <input type="text" name="username" class="form-control" id="username" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-3 control-label" for="password">Password:</label>
                                <div class="col-md-9">
                                    <input type="password" name="password" class="form-control" id="password"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3 col-md-offset-3">
                                    <button type="submit" class="btn">Login	</button>
                                </div>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>
