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
        <link rel="stylesheet" href="<s:url value="/resources/productList.css" />">
        <script src="<s:url value='/resources/addToCart.js' />"></script>
    </head>
    <body>
        <div id="wrapper" class="container">
            <header>
                <a href="<s:url value='/' />"><img src="<s:url value='/resources/images/phonify.svg' />"></a>
                <a href="<s:url value='/cart' />" class="btn" role="button">My cart: <span id="itemsQuantity">${cartIndicator.itemsQuantity}</span> items <span id="subtotal">${cartIndicator.subtotal}</span>$</a>
            </header>
            <section>
                <h1>Phones</h1>
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
                            <td><a href="<s:url value='/productDetails/${phone.id}' />">${phone.model}</a></td>
                            <td>${phone.color}</td>
                            <td>${phone.displaySize}"</td>
                            <td>${phone.price}$</td>
                            <form method="post" action="<s:url value='/addToCart' />" id="form_${phone.id}">
                                <td>
                                    <input type="text" name="quantity" form="form_${phone.id}" value="1">
                                    <span class="errorMessage hidden"></span>
                                </td>
                                <td>
                                    <button type="submit" class="btn">Add to cart</button>
                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                </table>
            </section>
        </div>
    </body>
</html>