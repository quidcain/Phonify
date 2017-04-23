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
                    <c:url var="actionUrl" value="/order"/>
                    <sf:form cssClass="form-horizontal" method="post" action="${actionUrl}" modelAttribute="order" >
                        <div class="form-group row">
                            <sf:label path="firstName" cssClass="col-md-4 control-label">First Name</sf:label>
                            <div class="col-md-8">
                                <sf:input path="firstName" cssClass="form-control" placeholder="First Name" />
                                <sf:errors path="firstName" cssClass="errorMessage"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <sf:label path="lastName" cssClass="col-md-4 control-label">Last Name</sf:label>
                            <div class="col-md-8">
                                <sf:input path="lastName" cssClass="form-control" placeholder="First Name" />
                                <sf:errors path="lastName" cssClass="errorMessage"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <sf:label path="deliveryAddress" cssClass="col-md-4 control-label">Address</sf:label>
                            <div class="col-md-8">
                                <sf:input path="deliveryAddress" cssClass="form-control" placeholder="Address" />
                                <sf:errors path="deliveryAddress" cssClass="errorMessage"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <sf:label path="contactPhoneNo" cssClass="col-md-4 control-label">Phone</sf:label>
                            <div class="col-md-8">
                                <sf:input path="contactPhoneNo" cssClass="form-control" placeholder="Phone" />
                                <sf:errors path="contactPhoneNo" cssClass="errorMessage"/>
                            </div>
                        </div>
                        <sf:textarea path="additionalInfo" cssClass="form-control additionalInfo" placeholder="Additional information" maxlength="255"/>
                        <button type="submit" class="btn order">Order</button>
                    </sf:form>
                </div>
            </section>
        </div>
    </body>
</html>
