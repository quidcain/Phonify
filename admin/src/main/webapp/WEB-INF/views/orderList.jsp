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
        <link rel="stylesheet" href="<s:url value="/resources/orderList.css" />">
        <script src="<s:url value='/resources/addToCart.js' />"></script>
    </head>
    <body>
        <div id="wrapper" class="container">
            <header>
                <a href="<s:url value='/' />"><img src="<s:url value='/resources/images/phonify.svg' />"></a>
                <form action="<s:url value='/logout' />" method="post">
                    <button type="submit" class="btn">Logout</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                <h2 class="principal">${pageContext.request.userPrincipal.name}</h2>
            </header>
            <section>
                <h1>Orders</h1>
                <form method="post" action="<s:url value='/orderList' />">
                    <table class="table table-striped table-bordered">
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Address</th>
                            <th>Phone</th>
                            <th>Total Price</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach items="${orderList}" var="order">
                            <tr>
                                <td><a href="<s:url value='/orderDetails/${order.id}' />">${order.firstName}</a></td>
                                <td><a href="<s:url value='/orderDetails/${order.id}' />">${order.lastName}</a></td>
                                <td><a href="<s:url value='/orderDetails/${order.id}' />">${order.deliveryAddress}</a></td>
                                <td><a href="<s:url value='/orderDetails/${order.id}' />">${order.contactPhoneNo}</a></td>
                                <td><a href="<s:url value='/orderDetails/${order.id}' />">${order.totalPrice}</a></td>
                                <td>
                                    <select name="statuses[${order.id}]" class="form-control">
                                        <option value="AWAITING" ${order.status == 'AWAITING' ? 'selected' : ''}>awaiting</option>
                                        <option value="SHIPPED" ${order.status == 'SHIPPED' ? 'selected' : ''}>shipped</option>
                                        <option value="COMPLETED" ${order.status == 'COMPLETED' ? 'selected' : ''}>completed</option>
                                        <option value="CANCELLED" ${order.status == 'CANCELLED' ? 'selected' : ''}>cancelled</option>
                                    </select>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <button type="submit" class="btn update">Update</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </section>
        </div>
    </body>
</html>