<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
    <head lang="en">
        <title>Phonify</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="<s:url value="/resources/main.css" />">
        <%--link .css file with the same name as page--%>
        <c:set var="fullPageName" value="${pageContext.request.getRequestURI()}"/>
        <c:set var="pageName" value="${fullPageName.substring(fullPageName.lastIndexOf('/') + 1, fullPageName.indexOf('.'))}"/>
        <link rel="stylesheet" href="<s:url value="/resources/${pageName}.css" />">
        <script src="<s:url value="/resources/${pageName}.js" />"></script>
    </head>
    <body>
        <div id="wrapper" class="container">
            <header>
                <img src="<s:url value="/resources/images/phonify.svg" />">
                <c:if test="${itemsQuantity == null}" >
                    <c:set value="0" var="itemsQuantity"/>
                </c:if>
                <button type="button" class="btn">My cart: <span id="itemsQuantity">0</span> items <span id="subtotal">0</span>$</button>
            </header>
            <section>
                <h1>Phones</h1>
                <form method="post" action="<s:url value="/addToCart" />">
                    <table class="table table-striped table-bordered">
                        <tr>
                            <th>Model</th>
                            <th>Color</th>
                            <th>Display Size</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach items="${phoneList}" var="phone">
                            <tr>
                                <td>${phone.model}</td>
                                <td>${phone.color}</td>
                                <td>${phone.displaySize}"</td>
                                <td>${phone.price}$</td>
                                <td>
                                    <input type="text" name="quantity" id="input_${phone.id}">
                                    <span class="errorMessage hidden"></span>
                                </td>
                                <td>
                                    <button type="submit" class="btn" id="button_${phone.id}">Add to cart</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </section>
        </div>
    </body>
</html>