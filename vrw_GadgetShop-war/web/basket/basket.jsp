<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>





<c:if test="${not empty shoppingBasket.items}">

    <table>

        <tr>
            <th>Item</th>
            <th>Quantity</th>
            <th>Price</th>
        </tr>

        <c:forEach var="basketItem" items="${basketItems}">
            <tr>
                <td>${basketItem.item.name}</td>
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

</c:if>