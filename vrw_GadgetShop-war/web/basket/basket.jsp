<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Shopping Basket</h2>

<c:choose>

    <c:when test="${not empty shoppingBasket.items}">
        <table id="basket">

            <tr>
                <th>Item</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>

            <c:forEach var="basketItem" items="${basketItems}">
                <tr>
                    <td><a href="ItemController/view?item=${basketItem.item.id}">${basketItem.item.name}</a></td>
                    <td>${basketItem.quantity}</td>
                    <td><fmt:formatNumber value="${basketItem.price}" type="currency" /></td>
                </tr>
            </c:forEach>

            <tr>
                <td></td>
                <td>Total:</td>
                <td><fmt:formatNumber value="${shoppingBasket.total}" type="currency" /></td>
            </tr>

        </table>

        <a href="...">Continue Shopping</a>
        <a href="basket/checkout">Proceed to Checkout</a>
    </c:when>
    <c:otherwise>
        <p>Your basket is empty</p>
    </c:otherwise>
</c:choose>