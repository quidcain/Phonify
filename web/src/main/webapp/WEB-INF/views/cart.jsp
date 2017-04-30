<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
                <a href="<s:url value='/order' />" class="btn aButton order" role="button">Order</a>
                <c:url var="actionUrl" value="/cart"/>
                <sf:form method="post" action="${actionUrl}" modelAttribute="updateCartItemsForm" >
                <%--<form method="post" action="<s:url value='/cart' />">--%>
                    <table class="table table-striped table-bordered">
                        <tr>
                            <th>Model</th>
                            <th>Color</th>
                            <th>Display Size</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach items="${cartItemList}" var="cartItem">
                            <tr>
                                <td><a href="<s:url value='/productDetails/${cartItem.phone.id}' />">${cartItem.phone.model}</a></td>
                                <td>${cartItem.phone.color}</td>
                                <td>${cartItem.phone.displaySize}"</td>
                                <td>${cartItem.phone.price}$</td>
                                <td>
                                    <c:set var="rejectedValue" value="${updateCartItemsForm.items[cartItem.phone.id].quantity}"/>
                                    <input type="text" name="items[${cartItem.phone.id}].quantity" value="${empty rejectedValue ? cartItem.quantity : rejectedValue}">
                                    <sf:errors path="items[${cartItem.phone.id}].quantity" cssClass="errorMessage"/>
                                </td>
                                <td>
                                    <button type="submit" class="btn" formaction="cart/deleteCartItem/${cartItem.phone.id}">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <button type="submit" class="btn update">Update</button>
                    <a href="<s:url value='/order' />" class="btn aButton order" role="button">Order</a>
                <%--</form>--%>
                </sf:form>
            </section>
        </div>
    </body>
</html>