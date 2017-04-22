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
    <link rel="stylesheet" href="<s:url value="/resources/order.css" />">
</head>
<body>
<div id="wrapper" class="container">
    <header>
        <a href="<s:url value='/' />"><img src="<s:url value='/resources/images/phonify.svg' />"></a>
    </header>
    <section>
        <h1>Order</h1>
        <a href="<s:url value='/' />" class="btn backToCart" role="button">Back to cart</a>
        <table class="table table-striped table-bordered">
            <tr>
                <th>Model</th>
                <th>Color</th>
                <th>Display Size</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
            <c:forEach items="${order.orderItems}" var="orderItem">
                <tr>
                    <td><a href="<s:url value='/productDetails/${orderItem.phone.id}' />">${orderItem.phone.model}</a></td>
                    <td>${orderItem.phone.color}</td>
                    <td>${orderItem.phone.displaySize}"</td>
                    <td>${orderItem.quantity}</td>
                    <td>${orderItem.phone.price}$</td>
                </tr>
            </c:forEach>
            <tr class="price">
                <td></td>
                <td></td>
                <td></td>
                <td>Subtotal</td>
                <td>${order.subtotal}$</td>
            </tr>
            <tr class="price">
                <td></td>
                <td></td>
                <td></td>
                <td>Delivery</td>
                <td>${order.deliveryPrice}$</td>
            </tr>
            <tr class="price">
                <td></td>
                <td></td>
                <td></td>
                <td>TOTAL</td>
                <td>${order.totalPrice}$</td>
            </tr>
        </table>
        <div class="customerInfo">
            <form class="form-horizontal" method="post" action="<s:url value='#' />">
                <div class="form-group row">
                    <span class="col-md-4">First Name</span>
                    <div class="col-md-8">
                        <span id="firstName">${order.firstName}</span>
                    </div>
                </div>
                <div class="form-group row">
                    <span class="col-md-4">Last Name</span>
                    <div class="col-md-8">
                        <span id="lastName">${order.lastName}</span>
                    </div>
                </div>
                <div class="form-group row">
                    <span class="col-md-4">Address</span>
                    <div class="col-md-8">
                        <span id="deliveryAddress">${order.deliveryAddress}</span>
                    </div>
                </div>
                <div class="form-group row">
                    <span class="col-md-4">Phone</span>
                    <div class="col-md-8">
                        <span id="contactPhoneNo">${order.contactPhoneNo}</span>
                    </div>
                </div>
                <div class="additionalInfo">${order.additionalInfo}</div>
                <a href="<s:url value='/' />" class="btn backToCart" role="button">Back to shopping</a>
            </form>
        </div>
    </section>
</div>
</body>
</html>
