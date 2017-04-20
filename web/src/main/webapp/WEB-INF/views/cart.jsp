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
    </head>
    <body>
        <div id="wrapper" class="container">
            <header>
                <a href="<s:url value='/' />"><img src="<s:url value='/resources/images/phonify.svg' />"></a>
                <c:if test="${cartIndicator.itemsQuantity == null}" >
                    <c:set value="0" var="cartIndicator.itemsQuantity"/>
                </c:if>
                <a href="<s:url value='/cart' />" class="btn" role="button">My cart: <span id="itemsQuantity">${cartIndicator.itemsQuantity}</span> items <span id="subtotal">${cartIndicator.subtotal}</span>$</a>
            </header>
            <section>
                <h1>Cart</h1>
                <a href="<s:url value='/' />" class="btn aButton backToProductList" role="button">Back to product list</a>
                <a href="#" class="btn aButton order" role="button">Order</a>
                <form method="post" action="<s:url value='/cart/updateOrderItems' />">
                    <table class="table table-striped table-bordered">
                        <tr>
                            <th>Model</th>
                            <th>Color</th>
                            <th>Display Size</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach items="${orderItemList}" var="orderItem">
                            <tr>
                                <td><a href="<s:url value='/productDetails/${orderItem.phone.id}' />">${orderItem.phone.model}</a></td>
                                <td>${orderItem.phone.color}</td>
                                <td>${orderItem.phone.displaySize}"</td>
                                <td>${orderItem.phone.price}$</td>
                                <td>
                                    <c:set var="quantity" value="quantity_${orderItem.phone.id}"/>
                                    <input type="text" name="items[${orderItem.phone.id}].quantity" value="${empty requestScope[quantity] ? orderItem.quantity : requestScope[quantity]}">
                                    <c:set var="errorMessage" value="errorMessage_${orderItem.phone.id}"/>
                                    <span class="errorMessage">${requestScope[errorMessage]}</span>
                                </td>
                                <td>
                                    <button type="submit" class="btn" formaction="cart/deleteOrderItem/${orderItem.phone.id}">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <button type="submit" class="btn update">Update</button>
                    <a href="#" class="btn aButton order" role="button">Order</a>
                </form>
            </section>
        </div>
    </body>
</html>