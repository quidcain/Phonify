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
        <script src="<s:url value='/resources/addToCart.js' />"></script>
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
                <a href="<s:url value='/' />" class="btn" role="button" id="backToProductList">Back to product list</a>
                <h1>${phone.model}</h1>
                <table class="table table-striped table-bordered">
                    <tr>
                        <td>Color</td>
                        <td>${phone.color}</td>
                    </tr>
                    <tr>
                        <td>Display</td>
                        <td>${phone.displaySize}"</td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td>${phone.price}$</td>
                    </tr>
                    <%--<tr>
                        <td>Display</td>
                        <td>4"</td>
                    </tr>
					<tr>
                        <td>Length</td>
                        <td>14mm</td>
                    </tr>
					<tr>
                        <td>Width</td>
                        <td>56mm</td>
                    </tr>
					<tr>
                        <td>Color</td>
                        <td>black</td>
                    </tr>
					<tr>
                        <td>Price</td>
                        <td>100$</td>
                    </tr>
					<tr>
                        <td>Camera</td>
                        <td>12mp</td>
                    </tr>--%>
                </table>
                <form method="post" action="<s:url value='/addToCart' />">
                	<input type="text" name="quantity" id="input_${phone.id}">
                	<button type="submit" class="btn addToCart" id="button_${phone.id}">Add to cart</button>
                    <div class="errorMessage hidden"></div>
                </form>
            </section>
        </div>
    </body>
</html>
